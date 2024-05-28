package baseclasses.control.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import baseclasses.Product;
import baseclasses.Warehouse;

import static util.Constants.*;
import static util.InputOutput.*;

/**
 * A classe IgSearch representa uma caixa de diálogo para pesquisa de produtos
 * no estoque do almoxarifado.
 */
public class IgSearch extends JDialog implements Serializable {

	private static final long serialVersionUID = 1L;

	private JTextField searchTextField;
	private Warehouse[] stocks;

	 /**
     * Constrói um diálogo IgSearch.
     *
     * @param menu o menu pai do controle de estoque.
     * @param stocks um array de objetos Warehouse contendo as informações do estoque.
     */
	public IgSearch(IgStockControl menu, Warehouse[] stocks) {

		this.stocks = stocks;

		initComponent(menu);
		configureEventListeners();
		setVisible(true);
	}

    /**
     * Inicializa os componentes da interface.
     *
     * @param menu o menu pai do controle de estoque.
     */
	private void initComponent(IgStockControl menu) {

		setTitle(SEARCH_TITLE);
		setSize(SIZE_IGSEARCH[0], SIZE_IGSEARCH[1]);

		setLocationRelativeTo(menu);
		setModal(true);
		setResizable(false);

		JPanel panelPesquisa = createDatasPanel();
		getContentPane().add(panelPesquisa, BorderLayout.CENTER);

		JPanel buttonPane = createButtonPanel();
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	}

    /**
     * Cria o painel de pesquisa.
     *
     * @return o painel de pesquisa.
     */
	private JPanel createDatasPanel() {

		JPanel panelSearch = new JPanel();
		panelSearch.setLayout(null);

		JLabel produtoLabel = createLabel(PRODUCT_LABEL + TWO_DOTS, 12, 6, 50, 16);
		panelSearch.add(produtoLabel);

		searchTextField = createTextField(12, 25, 269, 25, PRODUCT_LABEL_TIP);
		produtoLabel.setLabelFor(searchTextField);
		panelSearch.add(searchTextField);

		return panelSearch;
	}


    /**
     * Cria um rótulo (JLabel) com as propriedades especificadas.
     *
     * @param text o texto do rótulo.
     * @param x a posição x do rótulo.
     * @param y a posição y do rótulo.
     * @param width a largura do rótulo.
     * @param height a altura do rótulo.
     * @return o rótulo criado.
     */
	private JLabel createLabel(String text, int x, int y, int width, int height) {
		JLabel label = new JLabel(text);
		label.setBounds(x, y, width, height);
		return label;
	}

    /**
     * Cria um campo de texto (JTextField) com as propriedades especificadas.
     *
     * @param x a posição x do campo de texto.
     * @param y a posição y do campo de texto.
     * @param width a largura do campo de texto.
     * @param height a altura do campo de texto.
     * @param toolTip a dica de ferramenta do campo de texto.
     * @return o campo de texto criado.
     */
	private JTextField createTextField(int x, int y, int width, int height, String toolTip) {
		JTextField textField = new JTextField();
		textField.setMargin(new Insets(0, 5, 0, 0));
		textField.setToolTipText(toolTip);
		textField.setBounds(x, y, width, height);
		textField.setColumns(10);
		return textField;
	}

    /**
     * Cria o painel de botões.
     *
     * @return o painel de botões.
     */
	private JPanel createButtonPanel() {
		JPanel panelBotoes = new JPanel();
		panelBotoes.setBounds(0, 50, 291, 32);

		JButton cancelarButton = createButton(CANCEL_BUTTON, CANCEL_BUTTON_TIP, KeyEvent.VK_C, (e) -> cancel());
		panelBotoes.add(cancelarButton);

		JButton pesquisarButton = createButton(SEARCH_BUTTON, SEARCH_BUTTON_TIP, KeyEvent.VK_P, (e) -> search());
		panelBotoes.add(pesquisarButton);

		getRootPane().setDefaultButton(pesquisarButton);

		return panelBotoes;
	}

    /**
     * Cria um botão (JButton) com as propriedades especificadas.
     *
     * @param text o texto do botão.
     * @param toolTip a dica de ferramenta do botão.
     * @param mnemonic o atalho de teclado do botão.
     * @param actionListener o listener de ação do botão.
     * @return o botão criado.
     */
	private JButton createButton(String text, String toolTip, int mnemonic, ActionListener actionListener) {

		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(92, 26));
		button.setToolTipText(toolTip);
		button.setBackground(Color.WHITE);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setMnemonic(mnemonic);
		button.addActionListener(actionListener);

		return button;
	}

    /**
     * Realiza a pesquisa de um produto pelo nome.
     */
	private void search() {

		String searchField = searchTextField.getText();

		if (searchField.isBlank())
			msgError(this, MSG_ERROR_EMPTY_FIELDS, SEARCH_TITLE);
		else {

			boolean find = false;

			for (Warehouse warehouse : stocks) {

				Optional<Product> product = warehouse.consult(searchField);

				if (product.isPresent()) {
					find = true;
					String productInformation = warehouse.getTypeStock() + NEW_LINE + product.get().toString();
					msgInfo(this, productInformation, SEARCH_TITLE);
				}
			}

			if (!find)
				msgError(this, MSG_ERROR_PRODUCT_NOT_FOUND, SEARCH_TITLE);
		}

		resetFields();
	}

	/**
	 * Limpa o conteúdo dos campos, da interface.
	 */
	private void resetFields() {
		searchTextField.setText(EMPTY);
		searchTextField.requestFocus();
	}

    /**
     * Cancela a operação e fecha o diálogo.
     */
	private void cancel() {
		dispose();
	}

	/**
	 * Configura os listeners de eventos da janela.
	 */
	private void configureEventListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}

}// class IgSearch
