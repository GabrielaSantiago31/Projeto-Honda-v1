package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Moto;
import model.Venda;
import model.Vendedor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import dao.ConnectionFactory;
import dao.MotosDao;
import dao.VendasDao;
import dao.VendedoresDao;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.DefaultComboBoxModel;

public class CadastroVendasTela extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfTotal;
	private JTextField tfQtdMotos;
	
	VendedoresDao cadastroVendedores = new VendedoresDao();
	MotosDao cadastroMotos = new MotosDao();
	VendasDao cadastroVendas = new VendasDao();
	ConnectionFactory conexao = new ConnectionFactory();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
						if("Nimbus".equals(info.getName())) {
							javax.swing.UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
					CadastroVendasTela frame = new CadastroVendasTela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public CadastroVendasTela() {
		initialize();
	}
	/**
	 * Create the frame.
	 */
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(192, 192, 192), null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Titulo
		JLabel lblTitulo = new JLabel("Registro de Vendas");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(134, 11, 207, 22);
		contentPane.add(lblTitulo);
		
		//Label quantidade de motos
		JLabel lblQtdMotos = new JLabel("Qtd.");
		lblQtdMotos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQtdMotos.setToolTipText("quantidade");
		lblQtdMotos.setBounds(10, 174, 72, 19);
		contentPane.add(lblQtdMotos);
		
		//Quantidade de motos
		tfQtdMotos = new JTextField();
		tfQtdMotos.setText("1");
		tfQtdMotos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if(!caracteres.contains(e.getKeyChar()+"")) {
					e.consume();
				}
			}
		});
		tfQtdMotos.setBounds(58, 167, 96, 26);
		contentPane.add(tfQtdMotos);
		tfQtdMotos.setColumns(10);
		
		//Label Vendedor
		JLabel lblVendedor = new JLabel("Vendedor");
		lblVendedor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblVendedor.setBounds(10, 48, 91, 22);
		contentPane.add(lblVendedor);
		
		//Label Moto
		JLabel lblMoto = new JLabel("Moto");
		lblMoto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMoto.setBounds(10, 88, 72, 14);
		contentPane.add(lblMoto);
		
		//Label Pagamento
		JLabel lblPagamento = new JLabel("Pagamento");
		lblPagamento.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPagamento.setBounds(10, 120, 123, 21);
		contentPane.add(lblPagamento);
		
		//Label Total
		JLabel lblTotalTag = new JLabel("Total");
		lblTotalTag.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalTag.setBounds(261, 172, 49, 14);
		contentPane.add(lblTotalTag);
		
		//Vendedor
		JComboBox<Vendedor> cbxVendedor = new JComboBox<Vendedor>();
		List<Vendedor> v = cadastroVendedores.lista(conexao.getConnection());
		for(int i = 0; i < v.size(); i++) {
			cbxVendedor.addItem(v.get(i));
		}
		cbxVendedor.setBounds(102, 44, 324, 25);
		contentPane.add(cbxVendedor);
		
		//Total
		tfTotal = new JTextField();
		tfTotal.setEditable(false);
		tfTotal.setForeground(new Color(255, 0, 0));
	    tfTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfTotal.setText("0");
		tfTotal.setBounds(330, 167, 96, 26);
		tfTotal.setColumns(10);
		contentPane.add(tfTotal);
		
		
		//Moto
		JComboBox<Moto> cbxMoto = new JComboBox<Moto>();
		List<Moto> m = cadastroMotos.lista(conexao.getConnection());
		tfTotal.setText(m.get(0).getPreco().toString());
		for(int i = 0; i < m.size(); i++) {
			cbxMoto.addItem(m.get(i));   
		}
		cbxMoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				for(int i = 0; i < m.size(); i++) {
					if(cbxMoto.getSelectedItem() == m.get(i)) {
						if(tfQtdMotos.getText() != "") {
							int qtd = Integer.parseInt(tfQtdMotos.getText());
							tfTotal.setText(m.get(i).getPreco().multiply(new BigDecimal(qtd)).toString());
						}else {
							tfTotal.setText(m.get(i).getPreco().toString());
						}	
					}	
		
				}
			}
		});
		
		
		cbxMoto.setBounds(102, 80, 324, 25);
		contentPane.add(cbxMoto);
		
		//Pagamento
		JComboBox<String> cbxPagamento = new JComboBox<String>();
		cbxPagamento.setBounds(102, 120, 324, 25);
		String[] formasPagamento = {"A vista", "Credito 1x", "Credito 2x", "Credito 3x", "Credito 4x", "Credito 5x", "Credito 6x"
						,"Credito 7x", "Credito 8x", "Credito 9x", "Credito 10x", "Credito 11x", "Credito 12x"};
		for(int i = 0; i < formasPagamento.length; i++) {
			cbxPagamento.addItem(formasPagamento[i]);
		}
		contentPane.add(cbxPagamento);
		
		
		JButton btnFinalizar = new JButton("Finalizar a venda");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Vendedor vendedor = (Vendedor)cbxVendedor.getSelectedItem();
				    Moto moto = (Moto)cbxMoto.getSelectedItem();
				    int quantidade = Integer.parseInt(tfQtdMotos.getText());
				    String pagamento = (String) cbxPagamento.getSelectedItem();
					BigDecimal total = new BigDecimal(tfTotal.getText());
					LocalDateTime ldt = LocalDateTime.now();
					cadastroVendas.registra(conexao.getConnection(), new Venda(vendedor, moto, quantidade, total, pagamento, ldt));
					JOptionPane.showMessageDialog(btnFinalizar, "Venda finalizada!");
					escreveDadosNaTabela();
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
		});	
		btnFinalizar.setBackground(UIManager.getColor("Button.light"));
		btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnFinalizar.setBounds(261, 426, 165, 26);
		contentPane.add(btnFinalizar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 204, 415, 211);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSurrendersFocusOnKeystroke(false);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		table.setBorder(UIManager.getBorder("ScrollPane.border"));
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Vendedor", "Moto", "Quantidade", "Pagamento", "Total"
			}
		));
		escreveDadosNaTabela();
		
		JButton btnExcluir = new JButton("Excluir último registro");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel modelo = (DefaultTableModel)table.getModel();
				int i = table.getSelectedRow();
				
				if(i >= 0) {
					cadastroVendas.remove(conexao.getConnection(), (int) table.getValueAt(i, 0));
					modelo.removeRow(i);
				}else {
					System.out.println("Não foi possível remover o vendedor selecionado");
				}
					
			}
		});
	
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExcluir.setBackground(UIManager.getColor("Button.light"));
		btnExcluir.setBounds(10, 426, 165, 26);
		
		contentPane.add(btnExcluir);	
		
		
		
		
	}
	
	public void escreveDadosNaTabela() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setNumRows(0);
		for(Venda v : cadastroVendas.lista(conexao.getConnection())) {
			modelo.addRow(new Object[] {
					v.getId(),
					v.getVendedor(),
					v.getMoto(),
					v.getQtdMotosVendidas(),
					v.getFormaPagamento(),
					v.getTotVenda()
			});
		}
	}
	
	public void limparCampos() {
		tfQtdMotos.setText("");
		tfTotal.setText("");
		
		
	}
}