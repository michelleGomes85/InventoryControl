package classesbase.controle.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import classesbase.Almoxarifado;
import util.EntradaESaida;
import util.MenuOption;

import static util.ConstantesControleEstoque.*;

/**
 * Classe principal da interface gráfica de controle de estoque. Implementa as
 * funcionalidades de menu e janelas para operações no estoque.
 */
public class IgControleEstoque extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;

	private Almoxarifado[] estoques;

	/**
	 * Construtor que cria a interface gráfica principal.
	 * 
	 * @param estoques Array de estoques gerenciados pela aplicação.
	 */
	public IgControleEstoque(Almoxarifado[] estoques) {

		super(TITULO_PROGRAMA);
		this.estoques = estoques;

		setupFrame();
		setupMenu();

		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}// construtor

	/**
	 * Configura as propriedades básicas da janela principal.
	 */
	private void setupFrame() {

		setSize(TAMANHO_IGMENU[0], TAMANHO_IGMENU[1]);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		ImagemFundo painelImagemFundo = new ImagemFundo();
		getContentPane().add(painelImagemFundo, BorderLayout.CENTER);
	}

	/**
	 * Configura a barra de menus e seus itens.
	 */
	private void setupMenu() {

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuBar.add(createProductOptions());
		menuBar.add(createStockOptions());
	}

    /**
     * Cria o menu de opções de produtos.
     * 
     * @return JMenu configurado com opções de produtos.
     */
	private JMenu createProductOptions() {

		JMenu produtoMenu = new JMenu("Produtos");
		produtoMenu.setMnemonic(KeyEvent.VK_P);

		produtoMenu.add(createMenuItem(MenuOption.CADASTRAR.getTitle(), MenuOption.CADASTRAR.getMnemonic(), e -> cadastrarProduto()));
		produtoMenu.add(createMenuItem(MenuOption.PESQUISAR.getTitle(), MenuOption.PESQUISAR.getMnemonic(), e -> pesquisarProduto()));
		produtoMenu.add(new JSeparator());
		produtoMenu.add(createMenuItem(MenuOption.SAIR.getTitle(), MenuOption.SAIR.getMnemonic(), e -> System.exit(0)));

		return produtoMenu;
	}

	/**
	 * Cria o menu de opções de estoques.
	 * 
	 * @return JMenu configurado com opções de estoques.
	 */
	private JMenu createStockOptions() {

		JMenu estoqueMenu = new JMenu("Estoques");
		estoqueMenu.setMnemonic(KeyEvent.VK_E);

		estoqueMenu.add(createMenuItem(MenuOption.QUANTIDADE_PRODUTOS.getTitle(), MenuOption.QUANTIDADE_PRODUTOS.getMnemonic(), e -> quantidadeProdutosEstoque()));
		estoqueMenu.add(createMenuItem(MenuOption.VALOR_TOTAL.getTitle(), MenuOption.VALOR_TOTAL.getMnemonic(), e -> valorTotalEstoque()));
		estoqueMenu.add(new JSeparator());
		estoqueMenu.add(createMenuItem(MenuOption.RELATORIO.getTitle(), MenuOption.RELATORIO.getMnemonic(), e -> relatorioPorEstoque()));

		return estoqueMenu;
	}

	/**
	 * Cria um item de menu.
	 * 
	 * @param title    Título do item de menu.
	 * @param mnemonic Tecla de atalho para o item de menu.
	 * @param listener ActionListener associado ao item de menu.
	 * @return JMenuItem configurado.
	 */
	private JMenuItem createMenuItem(String title, int mnemonic, ActionListener listener) {

		JMenuItem menuItem = new JMenuItem(title);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(mnemonic, InputEvent.CTRL_DOWN_MASK));
		menuItem.setMnemonic(mnemonic);
		menuItem.addActionListener(listener);

		return menuItem;
	}

	/**
	 * Abre a janela de cadastro de produto.
	 */
	private void cadastrarProduto() {
		new IgCadastro(this, estoques);
	}

	/**
	 * Abre a janela de pesquisa de produto.
	 * 
	 * Exibe mensagem de erro se não houver produtos cadastrados.
	 */
	private void pesquisarProduto() {
		
		if (!verificarEstoque())
			msgErroSemProdutosCadastrados(this);
	}

	/**
	 * Abre a janela para exibir a quantidade total de produtos em cada estoque.
	 * Exibe mensagem de erro se não houver produtos cadastrados.
	 */
	private void quantidadeProdutosEstoque() {
		
		if (!verificarEstoque())
			msgErroSemProdutosCadastrados(this);
	}

	/**
	 * Abre a janela para exibir o valor total dos produtos em cada estoque. Exibe
	 * mensagem de erro se não houver produtos cadastrados.
	 */
	private void valorTotalEstoque() {
		
		if (!verificarEstoque())
			msgErroSemProdutosCadastrados(this);
	}

	/**
	 * Abre a janela para gerar um relatório de um estoque selecionado.
	 */
	private void relatorioPorEstoque() {
		
	}

	/**
	 * Verifica se há produtos cadastrados em algum dos estoques.
	 * 
	 * @return true se houver produtos cadastrados, false caso contrário.
	 */
	private boolean verificarEstoque() {

		int produtosCadastrados = 0;

		for (int indice = 0; indice < estoques.length; indice++)
			produtosCadastrados += estoques[indice].tamanho();

		return (produtosCadastrados != 0) ? true : false;
	}

	/**
	 * Exibe uma mensagem de erro indicando que não há produtos cadastrados.
	 * 
	 * @param posicao Componente onde a caixa de diálogo será exibida.
	 */
	public static void msgErroSemProdutosCadastrados(Component posicao) {
		EntradaESaida.msgErro(posicao, MSG_ERROR_NOT_REGISTERED_PRODUCTS, TITULO_PROGRAMA);
	}

	/**
	 * JPanel para adicionar a imagem de fundo.
	 */
	private static class ImagemFundo extends JPanel {

		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Image imagem = new ImageIcon(getClass().getResource(URL_IMG)).getImage();
			g.drawImage(imagem, 0, 0, this);
		}

	}// class ImagemFundo

}// class IgControleEstoque
