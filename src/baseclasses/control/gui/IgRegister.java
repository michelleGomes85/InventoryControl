package baseclasses.control.gui;

import static util.Constants.REGISTRATION_TITLE;
import static util.Constants.MSG_ERROR_EMPTY_FIELDS;
import static util.Constants.MSG_ERROR_INVALID_VALUE;
import static util.Constants.MSG_REGISTERED_PRODUCT;
import static util.Constants.MSG_UPDATE_PRODUCT;
import static util.Constants.SIZE_IGREGISTER;
import static util.Constants.CANCEL_BUTTON;
import static util.Constants.CANCEL_BUTTON_TIP;
import static util.Constants.RECORD_BUTTON;
import static util.Constants.RECORD_BUTTON_TIP;
import static util.Constants.EMPTY;
import static util.InputOutput.convertDoubleValue;
import static util.InputOutput.convertIntegerValue;
import static util.InputOutput.msgConfirm;
import static util.InputOutput.msgError;
import static util.InputOutput.msgInfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.Optional;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import baseclasses.Product;
import baseclasses.Warehouse;
import util.Stocks;
import util.LabelRegister;

/**
 * Classe responsável pela interface gráfica de cadastro de produtos.
 */
public class IgRegister extends JDialog implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField nameTextField;
	private JTextField amountTextField;
	private JTextField priceTextField;
	private JComboBox<String> stockComboBox;
	
	private int typeStock = Stocks.FOOD.ordinal();
	
	private Warehouse stock;
	private Warehouse[] stocks;
	
	/**
	* Construtor que inicializa a interface de cadastro de produtos.
	* 
	* @param menu Instância do menu de controle de estoque.
	* @param stocks Array de almoxarifados disponíveis.
	*/
	public IgRegister(IgStockControl menu, Warehouse[] stocks) {
		
		this.stocks = stocks;
		this.stock = stocks[Stocks.FOOD.ordinal()];
		
		initComponent(menu);
		configureEventListeners();
		setVisible(true);
	}

	/**
	* Método responsável por inicializar os componentes da interface.
	* @param menu Instância do menu de controle de estoque.
	*/
	private void initComponent(IgStockControl menu) {
		
		setTitle(REGISTRATION_TITLE);
		setSize(SIZE_IGREGISTER[0], SIZE_IGREGISTER[1]);
		setLocationRelativeTo(menu);
		
		setModal(true);
		setResizable(false);
		
		JPanel dataPanel = createDadosPanel();
		getContentPane().add(dataPanel, BorderLayout.CENTER);
		
		JPanel buttonPane = createButtonPanel();
		getContentPane().add(buttonPane, BorderLayout.SOUTH);	
	}

	/**
	* Cria e configura o painel de dados do formulário.
	* @return JPanel configurado com os campos de entrada de dados.
	*/
	private JPanel createDadosPanel() {
	
		JPanel dataPanel = new JPanel();
		
		JLabel stockLabel = createLabel(LabelRegister.STOCKS);
		stockComboBox = createEstoqueComboBox(stockLabel);
		
		JLabel nameLabel = createLabel(LabelRegister.NAME);
		nameTextField = createTextField(nameLabel);
		
		JLabel amountLabel = createLabel(LabelRegister.AMOUNT);
		amountTextField = createTextField(amountLabel);
		
		JLabel priceLabel = createLabel(LabelRegister.PRICE);
		priceTextField = createTextField(priceLabel);
		
		GroupLayout layout = new GroupLayout(dataPanel);
		dataPanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
		    
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addComponent(stockLabel)
			.addComponent(nameLabel)
			.addComponent(amountLabel)
			.addComponent(priceLabel))
		    
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addComponent(stockComboBox)
			.addComponent(nameTextField)
			.addComponent(amountTextField)
			.addComponent(priceTextField)));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
		    
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(stockLabel)
			.addComponent(stockComboBox))
			
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(nameLabel)
			.addComponent(nameTextField))
		    
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(amountLabel)
			.addComponent(amountTextField))
		    
		    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(priceLabel)
			.addComponent(priceTextField)));
			
		return dataPanel;
	}
	
	 /**
	* Cria um JLabel configurado.
	* @param labelCadastro Enum que contém o título e o mnemonic do label.
	* @return JLabel configurado.
	*/
	private JLabel createLabel(LabelRegister labelCadastro) {
		
		JLabel label = new JLabel(labelCadastro.getTitle());
		label.setDisplayedMnemonic(labelCadastro.getMnemonic());
			
		return label;
	}
	
	/**
	* Cria um JComboBox configurado para selecionar o estoque.
	* @param stockLabel JLabel associado ao JComboBox.
	* @return JComboBox configurado.
	*/
	private JComboBox<String> createEstoqueComboBox(JLabel stockLabel) {
	
		JComboBox<String> comboBox = new JComboBox<>(Stocks.getString());
		comboBox.setBackground(Color.WHITE);
		comboBox.setForeground(Color.BLACK);
		comboBox.setMaximumRowCount(3);
		comboBox.setSelectedIndex(0);
		stockLabel.setLabelFor(comboBox);
		comboBox.addItemListener((e) -> typeStock = comboBox.getSelectedIndex());
		
		return comboBox;
	}
	
	/**
	* Cria um JTextField configurado.
	* @param label JLabel associado ao JTextField.
	* @return JTextField configurado.
	*/
	private JTextField createTextField(JLabel label) {
		JTextField textField = new JTextField();
		label.setLabelFor(textField);
		textField.setColumns(10);
		return textField;
	}
	
	/**
	* Cria e configura o painel de botões do formulário.
	* @return JPanel configurado com os botões.
	*/
	private JPanel createButtonPanel() {
		
		JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		JButton cancelButton = createButton(CANCEL_BUTTON, KeyEvent.VK_C, CANCEL_BUTTON_TIP, (e) -> cancel());
		buttonPane.add(cancelButton);
		
		JButton recordButton = createButton(RECORD_BUTTON, KeyEvent.VK_G, RECORD_BUTTON_TIP, (e) -> recordProduct());
		buttonPane.add(recordButton);
		
		getRootPane().setDefaultButton(cancelButton);
		
		return buttonPane;
	}
	
	/**
	* Cria um JButton configurado.
	* 
	* @param text Texto do botão.
	* @param mnemonic Tecla de atalho do botão.
	* @param toolTip Dica de ferramenta do botão.
	* @param actionListener Listener de ação do botão.
	* @return JButton configurado.
	*/
	private JButton createButton(String text, int mnemonic, String toolTip, java.awt.event.ActionListener actionListener) {
	
		JButton button = new JButton(text);
		button.setBackground(Color.WHITE);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setMnemonic(mnemonic);
		button.setToolTipText(toolTip);
		button.addActionListener(actionListener);
		return button;
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
	
	/**
	* Registra um produto no estoque após validar os campos.
	*/
	private void recordProduct() {
	
		if (emptyFields()) {
		    msgError(this, MSG_ERROR_EMPTY_FIELDS, REGISTRATION_TITLE);
		    nameTextField.requestFocus();
		    return;
		}
		
		Product product = productInformation();
		if (product == null) {
		    msgError(this, MSG_ERROR_INVALID_VALUE, REGISTRATION_TITLE);
		    return;
		}
		
		Optional<Product> productSearch = productExists();
		
		if (productSearch.isPresent()) {
			if (msgConfirm(MSG_UPDATE_PRODUCT, REGISTRATION_TITLE) == JOptionPane.YES_OPTION)
				stock.updateData(productSearch.get(), product);
		} else {
			stock.add(product);
		    msgInfo(this, MSG_REGISTERED_PRODUCT, REGISTRATION_TITLE);
		}
		
		resetFields();
	}
	
	/**
	* Obtém as informações do produto a partir dos campos de entrada.
	* @return Produto objeto Produto, ou null se os dados forem inválidos.
	*/
	private Product productInformation() {
	
		determineStock();
		Integer amount = convertIntegerValue(amountTextField.getText());
		Double price = convertDoubleValue(priceTextField.getText());
		
		if (amount == null || price == null)
		    return null;
		
		return new Product(nameTextField.getText(), amount, price);
	}
	
	/**
	* Determina o estoque selecionado pelo usuário.
	*/
	private void determineStock() {
		stock = stocks[typeStock];
	}
	
	/**
	* Verifica se o produto já existe nos estoques.
	* @return Optional<Produto> contendo o produto, se existir.
	*/
	private Optional<Product> productExists() {
		for (Warehouse almoxarifado : stocks) {
		    Optional<Product> search = almoxarifado.consult(nameTextField.getText());
		    if (search.isPresent())
			return search;
		}
		
		return Optional.empty();
	}
	
	/**
	* Verifica se os campos de entrada estão vazios.
	* @return true se algum campo estiver vazio, false caso contrário.
	*/
	private boolean emptyFields() {
		return nameTextField.getText().isBlank() || amountTextField.getText().isBlank() || priceTextField.getText().isBlank();
	}
	
	/**
	* Reseta os campos do formulário para seus valores iniciais.
	*/
	private void resetFields() {
		stockComboBox.setSelectedIndex(Stocks.FOOD.ordinal());
		nameTextField.setText(EMPTY);
		amountTextField.setText(EMPTY);
		priceTextField.setText(EMPTY);
		
		stockComboBox.requestFocus();
	}
	
	/**
	* Cancela a operação e fecha a janela.
	*/
	private void cancel() {
		dispose();
	}
    
}//class IgCadastro
