package baseclasses.control.gui;

import static util.Constants.CLOSE_BUTTON;
import static util.Constants.CLOSE_BUTTON_TIP;
import static util.Constants.QUERY_TITLE;
import static util.Constants.SIZE_IGQUERY;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;
import util.Stocks;

/**
 * IgQuery é um JDialog que exibe os resultados da consulta das informações de
 * estoque. Ele mostra valores para os estoques de alimentos, limpeza e higiene.
 */
public class IgQuery extends JDialog implements Serializable {

	private static final long serialVersionUID = 1L;

	private JTextField foodJTextField;
	private JTextField cleaningJTextField;
	private JTextField hygieneJTextField;

    /**
     * Constrói o diálogo IgQuery com valores especificados.
     * 
     * @param menu     o frame pai
     * @param title    o título do diálogo
     * @param food     o valor do estoque de alimentos
     * @param cleaning o valor do estoque de limpeza
     * @param hygiene  o valor do estoque de higiene
     */
	public IgQuery(IgStockControl menu, String title, String food, String cleaning, String hygiene) {

		initComponent(menu);
		configureEventListeners();

		addTitleTypeQuery(title);
		addDashboardInformation();
		addButtonPanel();

		addValues(food, cleaning, hygiene);

		setVisible(true);
	}

    /**
     * Adiciona o painel de informações com detalhes do estoque.
     */
	private void addDashboardInformation() {

		JPanel panelInformation = new JPanel();

		panelInformation.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelInformation.setBounds(22, 53, 294, 73);
		getContentPane().add(panelInformation);
		panelInformation.setLayout(new MigLayout("", "[][grow]", "[grow][grow][grow]"));
		getContentPane().add(panelInformation);

		foodJTextField = createTextField(panelInformation, Stocks.FOOD.getTitle(), Stocks.FOOD.ordinal());
		cleaningJTextField = createTextField(panelInformation, Stocks.CLEANING.getTitle(), Stocks.CLEANING.ordinal());
		hygieneJTextField = createTextField(panelInformation, Stocks.HYGIENE.getTitle(), Stocks.HYGIENE.ordinal());
	}

    /**
     * Cria um JTextField com um JLabel correspondente e o adiciona ao painel especificado.
     * 
     * @param panel o painel ao qual o JLabel e JTextField serão adicionados
     * @param label o texto do JLabel
     * @param row   a linha na qual os componentes serão adicionados
     * @return o JTextField criado
     */
	private JTextField createTextField(JPanel panel, String label, int row) {

		JLabel jLabel = new JLabel(label);
		jLabel.setFont(new Font("Arial", Font.BOLD, 12));
		panel.add(jLabel, "cell 0 " + row + ",alignx trailing");

		JTextField textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 12));
		textField.setBorder(null);
		textField.setEditable(false);
		textField.setColumns(10);
		panel.add(textField, "cell 1 " + row + ",growx");

		return textField;
	}

    /**
     * Adiciona o título da consulta ao diálogo.
     * 
     * @param title o título da consulta
     */
	private void addTitleTypeQuery(String title) {

		JLabel titleLabel = new JLabel(title);
		titleLabel.setBounds(0, 24, 337, 18);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 15));
		getContentPane().add(titleLabel);
	}

    /**
     * Adiciona o painel de botões ao diálogo.
     */
	private void addButtonPanel() {

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(17, 137, 306, 33);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPanel);

		JButton closeButton = new JButton(CLOSE_BUTTON);
		closeButton.setToolTipText(CLOSE_BUTTON_TIP);
		closeButton.setMnemonic(KeyEvent.VK_F);
		closeButton.setBackground(Color.WHITE);
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeButton.addActionListener(e -> close());
		buttonPanel.add(closeButton);

		getRootPane().setDefaultButton(closeButton);
	}

    /**
     * Inicializa os componentes do diálogo.
     * 
     * @param menu o frame pai
     */
	private void initComponent(IgStockControl menu) {

		setTitle(QUERY_TITLE);
		setSize(SIZE_IGQUERY[0], SIZE_IGQUERY[1]);

		setLocationRelativeTo(menu);

		setModal(true);
		setLayout(null);
		setResizable(false);
	}

    /**
     * Define os valores dos campos nos JTextFields.
     * 
     * @param food     o valor do estoque de alimentos
     * @param cleaning o valor do estoque de limpeza
     * @param hygiene  o valor do estoque de higiene
     */
	private void addValues(String food, String cleaning, String hygiene) {
		foodJTextField.setText(food);
		cleaningJTextField.setText(cleaning);
		hygieneJTextField.setText(hygiene);
	}

	/**
	 * Fecha o JDialog
	 */
	private void close() {
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
}// class IgQuery
