package baseclasses.control.gui;

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

import baseclasses.Warehouse;
import util.InputOutput;
import util.MenuOption;
import util.Stocks;

import static util.Constants.*;

/**
 * Classe principal da interface gráfica de controle de estoque. Implementa as
 * funcionalidades de menu e janelas para operações no estoque.
 */
public class IgStockControl extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;

	private Warehouse[] stocks;

	/**
	 * Construtor que cria a interface gráfica principal.
	 * 
	 * @param stocks Array de estoques gerenciados pela aplicação.
	 */
	public IgStockControl(Warehouse[] stocks) {

		super(PROGRAM_TITLE);
		this.stocks = stocks;

		setupFrame();
		setupMenu();

		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	/**
	 * Configura as propriedades básicas da janela principal.
	 */
	private void setupFrame() {

		setSize(SIZE_IGMENU[0], SIZE_IGMENU[1]);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		BackgroundImage panelBackgroundImage = new BackgroundImage();
		getContentPane().add(panelBackgroundImage, BorderLayout.CENTER);
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

		JMenu productMenu = new JMenu(PRODUCT_LABEL);
		productMenu.setMnemonic(KeyEvent.VK_P);

		productMenu.add(createMenuItem(MenuOption.REGISTER.getTitle(), MenuOption.REGISTER.getMnemonic(), e -> register()));
		productMenu.add(createMenuItem(MenuOption.SEARCH.getTitle(), MenuOption.SEARCH.getMnemonic(), e -> search()));
		productMenu.add(new JSeparator());
		productMenu.add(createMenuItem(MenuOption.CLOSE.getTitle(), MenuOption.CLOSE.getMnemonic(), e -> System.exit(0)));

		return productMenu;
	}

	/**
	 * Cria o menu de opções de estoques.
	 * 
	 * @return JMenu configurado com opções de estoques.
	 */
	private JMenu createStockOptions() {

		JMenu stockMenu = new JMenu(STOCK_LABEL);
		stockMenu.setMnemonic(KeyEvent.VK_E);

		stockMenu.add(createMenuItem(MenuOption.QUANTITY_OF_PRODUCTS.getTitle(), MenuOption.QUANTITY_OF_PRODUCTS.getMnemonic(), e -> quantityProducts()));
		stockMenu.add(createMenuItem(MenuOption.AMOUNT.getTitle(), MenuOption.AMOUNT.getMnemonic(), e -> amount()));
		stockMenu.add(new JSeparator());
		stockMenu.add(createMenuItem(MenuOption.REPORT.getTitle(), MenuOption.REPORT.getMnemonic(), e -> reportByEstoque()));

		return stockMenu;
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
	private void register() {
		new IgRegister(this, stocks);
	}

	/**
	 * Abre a janela de pesquisa de produto.
	 * 
	 * Exibe mensagem de erro se não houver produtos cadastrados.
	 */
	private void search() {
		
		if (!verifyStock())
			noProductsRegister(this);
		else
			new IgSearch(this, stocks);
	}

	/**
	 * Abre a janela para exibir a quantidade total de produtos em cada estoque.
	 * Exibe mensagem de erro se não houver produtos cadastrados.
	 */
	private void quantityProducts() {
		
		if (!verifyStock())
			noProductsRegister(this);
		else {
			new IgQuery(this, QUANTITY_PRODUCTS_STOCK, 
				String.format(FORMAT_QUANTITY_PRODUCTS, stocks[Stocks.FOOD.ordinal()].quantityProductsStock()),
				String.format(FORMAT_QUANTITY_PRODUCTS, stocks[Stocks.CLEANING.ordinal()].quantityProductsStock()),
				String.format(FORMAT_QUANTITY_PRODUCTS, stocks[Stocks.HYGIENE.ordinal()].quantityProductsStock()));
		}
	}

	/**
	 * Abre a janela para exibir o valor total dos produtos em cada estoque. Exibe
	 * mensagem de erro se não houver produtos cadastrados.
	 */
	private void amount() {
		
		if (!verifyStock())
			noProductsRegister(this);
		else {
			new IgQuery(this, TOTAL_STOCK_VALUE, 
					String.format(FORMAT_STOCK_VALUE, stocks[Stocks.FOOD.ordinal()].amount()),
					String.format(FORMAT_STOCK_VALUE, stocks[Stocks.CLEANING.ordinal()].amount()),
					String.format(FORMAT_STOCK_VALUE, stocks[Stocks.HYGIENE.ordinal()].amount()));
		}
	}

	/**
	 * Abre a janela para gerar um relatório de um estoque selecionado.
	 */
	private void reportByEstoque() {
		
		if (!verifyStock())
			noProductsRegister(this);
	}

	/**
	 * Verifica se há produtos cadastrados em algum dos estoques.
	 * 
	 * @return true se houver produtos cadastrados, false caso contrário.
	 */
	private boolean verifyStock() {

		int products = 0;

		for (int index = 0; index < stocks.length; index++)
			products += stocks[index].size();

		return (products != 0) ? true : false;
	}

	/**
	 * Exibe uma mensagem de erro indicando que não há produtos cadastrados.
	 * 
	 * @param component Componente onde a caixa de diálogo será exibida.
	 */
	public static void noProductsRegister(Component component) {
		InputOutput.msgError(component, MSG_ERROR_NOT_REGISTERED_PRODUCTS, PROGRAM_TITLE);
	}

	/**
	 * JPanel para adicionar a imagem de fundo.
	 */
	private static class BackgroundImage extends JPanel {

		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Image image = new ImageIcon(getClass().getResource(URL_IMG)).getImage();
			g.drawImage(image, 0, 0, this);
		}

	}// class BackgroundImage

}// class IgControleEstoque
