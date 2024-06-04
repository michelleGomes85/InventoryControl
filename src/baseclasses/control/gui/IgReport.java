package baseclasses.control.gui;

import static util.Constants.CLOSE_BUTTON;
import static util.Constants.CLOSE_BUTTON_TIP;
import static util.Constants.HEADER_TABLE;
import static util.Constants.REPORT_TITLE;
import static util.Constants.SIZE_IGREPORT;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import baseclasses.Product;
import baseclasses.Warehouse;
import net.miginfocom.swing.MigLayout;

import static util.Constants.*;

/**
 * Classe que representa um relatório de estoque em uma janela de diálogo.
 * Esta classe exibe informações sobre produtos em estoque e seu valor total.
 */
public class IgReport extends JDialog implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	private DefaultTableModel defaultTableModel;
	private JTextField quantityProductsTextField;
	private JTextField totalValueTextField;
	private Warehouse stock;
	
	/**
     * Construtor da classe IgReport.
     *
     * @param menu  O controle de estoque do qual esta janela de relatório faz parte.
     * @param stock O estoque a ser exibido no relatório.
     */
	public IgReport(IgStockControl menu, Warehouse stock) {
		
		this.stock = stock;
		
		initComponent(menu);
		configureEventListeners();
		
		addDashboardInformation();
		
		setVisible(true);
	}

    /**
     * Adiciona informações ao painel principal do relatório.
     */
	private void addDashboardInformation() {
		
	    JPanel tableProductsPanel =	createTableProducts();
	    getContentPane().add(tableProductsPanel, BorderLayout.CENTER);
	    
		JPanel dateStockPanel = createDataTable();
		getContentPane().add(dateStockPanel, BorderLayout.SOUTH);
		
		addProducts();
		addInfomationLabel();
	}

    /**
     * Inicializa os componentes da janela de relatório.
     *
     * @param menu O controle de estoque do qual esta janela de relatório faz parte.
     */
	private void initComponent(IgStockControl menu) {
		
		setTitle(REPORT_TITLE);
		setSize(SIZE_IGREPORT[0], SIZE_IGREPORT[1]);
		
		setLocationRelativeTo(menu);
		
		setModal(true);
		setResizable(false);
	}
	
    /**
     * Cria o painel da tabela de produtos.
     *
     * @return O painel da tabela de produtos criado.
     */
	private JPanel createTableProducts() {
		
		JPanel tableProductsPanel = new JPanel();
		tableProductsPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Produtos do Estoque", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tableProductsPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 414, 134);
		tableProductsPanel.add(scrollPane);
		
		JTable tableProducts = createTable();
		scrollPane.setViewportView(tableProducts);
		
		return tableProductsPanel;
	}

    /**
     * Cria a tabela de produtos.
     *
     * @return A tabela de produtos criada.
     */
	private JTable createTable() {
		
		JTable tableProducts = new JTable();
		
		tableProducts.setShowVerticalLines(true);
		tableProducts.setShowHorizontalLines(true);
		
		defaultTableModel = new DefaultTableModel(HEADER_TABLE, stock.report().length)
		{
			private static final long serialVersionUID = 1L;
			
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		
		tableProducts.setModel(defaultTableModel);
		configureTableColumns(tableProducts);
		
		return tableProducts;
	}
	
    /**
     * Configura as colunas da tabela de produtos.
     *
     * @param tableProducts A tabela de produtos a ser configurada.
     */
	private void configureTableColumns(JTable tableProducts) {
		
		tableProducts.getColumnModel().getColumn(0).setPreferredWidth(115);
		tableProducts.getColumnModel().getColumn(1).setPreferredWidth(90);
		tableProducts.getColumnModel().getColumn(2).setPreferredWidth(82);
		
		tableProducts.getColumnModel().getColumn(0).setResizable(false);
		tableProducts.getColumnModel().getColumn(1).setResizable(false);
		tableProducts.getColumnModel().getColumn(2).setResizable(false);
		
		DefaultTableCellRenderer colunaTableCellRenderer = new DefaultTableCellRenderer();
		colunaTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		tableProducts.getColumnModel().getColumn(0).setCellRenderer(colunaTableCellRenderer);
		tableProducts.getColumnModel().getColumn(1).setCellRenderer(colunaTableCellRenderer);
		tableProducts.getColumnModel().getColumn(2).setCellRenderer(colunaTableCellRenderer);
	}
	
	/**
     * Cria o painel de dados com campos de quantidade e valor total.
     *
     * @return O painel de dados criado.
     */
	private JPanel createDataTable() {
		
		JPanel dateTablePanel = new JPanel();
		dateTablePanel.setLayout(new MigLayout("", "[][grow][]", "[][]"));
		
		JLabel quantitityProductsLabel = new JLabel(QUANTITY_PRODUCTS_LABEL);
		quantitityProductsLabel.setFont(new Font("Arial", Font.BOLD, 12));
		dateTablePanel.add(quantitityProductsLabel, "cell 0 0,alignx trailing");
		
		quantityProductsTextField = new JTextField();
		dateTablePanel.add(quantityProductsTextField, "cell 1 0");
		quantityProductsTextField.setFont(new Font("Arial", Font.PLAIN, 12));
		quantityProductsTextField.setBorder(null);
		quantityProductsTextField.setEditable(false);
		quantityProductsTextField.setColumns(10);
		
		JLabel totalValueLabel = new JLabel(TOTAL_VALUE_LABEL);
		totalValueLabel.setFont(new Font("Arial", Font.BOLD, 12));
		dateTablePanel.add(totalValueLabel, "cell 0 1,alignx trailing");
		
		totalValueTextField = new JTextField();
		dateTablePanel.add(totalValueTextField, "flowx,cell 1 1");
		totalValueTextField.setFont(new Font("Arial", Font.PLAIN, 12));
		totalValueTextField.setBorder(null);
		totalValueTextField.setEditable(false);
		totalValueTextField.setColumns(10);
		
		JButton fecharButton = createCloseButton();
		dateTablePanel.add(fecharButton, "cell 2 1");
		
		return dateTablePanel;
	}
	
	/**
     * Cria o botão de fechar a janela.
     *
     * @return O botão de fechar criado.
     */
	private JButton createCloseButton() {
		
		JButton closeButton = new JButton(CLOSE_BUTTON);
		closeButton.setFont(new Font("Arial", Font.PLAIN, 12));
		closeButton.setToolTipText(CLOSE_BUTTON_TIP);
		closeButton.setMnemonic(KeyEvent.VK_F);
		closeButton.setBackground(Color.WHITE);
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeButton.addActionListener((e) -> close());
		getRootPane().setDefaultButton(closeButton);
		
		return closeButton;
	}

    /**
     * Adiciona os produtos do estoque na tabela de produtos.
     */
	private void addProducts() {
		
		Product[] products = stock.report();
		
		for(int lin = 0; lin < products.length; lin++) {
			defaultTableModel.setValueAt(products[lin].getName(), lin, 0);
			defaultTableModel.setValueAt(products[lin].getAmount(), lin, 1);
			defaultTableModel.setValueAt(String.format(FORMAT_STOCK_VALUE_TABLE, products[lin].getPrice()), lin, 2);
		}
	}
	
	/**
     * Adiciona informações de quantidade e valor total dos produtos.
     */
	private void addInfomationLabel() {
		quantityProductsTextField.setText(String.format(FORMAT_QUANTITY_PRODUCTS_TABLE, stock.quantityProductsStock()));
		totalValueTextField.setText(String.format(FORMAT_STOCK_VALUE_TABLE, stock.amount()));
	}
	
    /**
     * Fecha a janela de diálogo.
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
	
}//class IgReport