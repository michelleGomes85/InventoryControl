package baseclasses.control.gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import util.Stocks;

import static util.Constants.*;

/**
 * A classe IgMenu representa um diálogo de menu permite aos usuários 
 * selecionar uma opção a partir de uma lista de estoques. 
 * A opção selecionada é então retornada.
 */
public class IgMenu extends JDialog implements Serializable {

	private static final long serialVersionUID = 1L;

	private Stocks[] stocks;
	private final AtomicReference<String> selectedOption = new AtomicReference<>(null);

	/**
	 * Constrói um novo diálogo IgMenu.
	 *
	 * @param menu o controle de menu pai
	 * @param stocks um array de Stocks representando as opções do menu
	 */
	public IgMenu(IgStockControl menu, Stocks[] stocks) {

		this.stocks = stocks;

		initComponent(menu);
		configureEventListeners();

		addDashboardInformation();

		setVisible(true);
	}
	
	/**
	 * Inicializa os componentes do diálogo.
	 *
	 * @param menu o controle de menu pai
	 */
	private void initComponent(IgStockControl menu) {

		setTitle(MENU_TITLE);
		setSize(SIZE_IGMENU[0], SIZE_IGMENU[1]);

		setLocationRelativeTo(menu);

		setModal(true);
		setLayout(null);
		setResizable(false);
	}

	/**
	 * Adiciona o conteúdo principal ao diálogo, incluindo o painel de opções.
	 */
	private void addDashboardInformation() {

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelLabel = new JPanel();
		contentPane.add(panelLabel, BorderLayout.NORTH);

		JLabel labelOpcoes = new JLabel(MENU_LABEL);
		labelOpcoes.setFont(new Font("Dialog", Font.BOLD, 12));
		labelOpcoes.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabel.add(labelOpcoes);

		JPanel optionsPanel = new JPanel();
		contentPane.add(optionsPanel, BorderLayout.CENTER);

		addMenuoptions(optionsPanel);
	}

	/**
	 * Adiciona opções de menu como botões ao painel fornecido.
	 *
	 * @param optionsPanel o painel ao qual os botões serão adicionados
	 */
	private void addMenuoptions(JPanel optionsPanel) {

		for (Stocks stock : stocks) {

			JButton button = new JButton(stock.getTitle());
			button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			button.setToolTipText(stock.getTitle());
			button.setMnemonic(stock.getKeyEvent());

			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedOption.set(stock.getTitle());
					dispose();
				}
			});

			optionsPanel.add(button);
		}
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
	 * Retorna a opção selecionada pelo usuário.
	 *
	 * @return a opção selecionada
	 */
	public String getSelectedOption() {
		return selectedOption.get();
	}

}// class IgMenu
