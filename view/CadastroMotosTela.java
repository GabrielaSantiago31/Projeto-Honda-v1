package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Moto;
import model.Vendedor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.ConnectionFactory;
import dao.MotosDao;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CadastroMotosTela extends JFrame {

	private JPanel contentPane;
	private JTextField tfMarca;
	private JTextField tfModelo;
	private JTextField tfCor;
	private JComboBox cbCilindrada;
	private JComboBox cbAno;
	private JTextField tfPreco;
	private JTextField tfQtd;
	private JTextArea taObservacoes;
	
	MotosDao cadastro = new MotosDao();
	ConnectionFactory conexao = new ConnectionFactory();
	
	private JTable table;
	
	
	
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
					CadastroMotosTela frame = new CadastroMotosTela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public CadastroMotosTela() {
		initialize();
	}
	/**
	 * Create the frame.
	 */
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(192, 192, 192), null));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Marca
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMarca.setBounds(10, 43, 74, 20);
		contentPane.add(lblMarca);
		
		tfMarca = new JTextField();
		tfMarca.setBounds(75, 40, 351, 30);
		contentPane.add(tfMarca);
		tfMarca.setColumns(10);
		
		//Titulo
		JLabel lblTitulo = new JLabel("Cadastro de Motos");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(134, 11, 207, 13);
		contentPane.add(lblTitulo);
		
		//Modelo
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModelo.setBounds(10, 83, 74, 13);
		contentPane.add(lblModelo);
		
		tfModelo = new JTextField();
		tfModelo.setBounds(75, 76, 351, 30);
		contentPane.add(tfModelo);
		tfModelo.setColumns(10);
		
		//Cor
		JLabel lblCor = new JLabel("Cor");
		lblCor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCor.setBounds(10, 119, 74, 20);
		contentPane.add(lblCor);
		
		tfCor = new JTextField();
		tfCor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789?*@!$";
				if(caracteres.contains(e.getKeyChar()+"")) {
					e.consume();
				}
			}
		});
		tfCor.setBounds(75, 112, 151, 30);
		contentPane.add(tfCor);
		tfCor.setColumns(10);
		
		//Cilindrada
		JLabel lblCilindrada = new JLabel("CC");
		lblCilindrada.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCilindrada.setBounds(182, 154, 55, 20);
		contentPane.add(lblCilindrada);
		
		JComboBox cbCilindrada = new JComboBox();
		for(int i = 50; i <= 650; i+=10) {
			cbCilindrada.addItem(String.valueOf(i));
		}
		
		cbCilindrada.setBounds(214, 153, 67, 27);
		contentPane.add(cbCilindrada);
		
		//Preco
		
		JLabel lblPreco = new JLabel("Preço");
		lblPreco.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPreco.setBounds(10, 155, 49, 20);
		contentPane.add(lblPreco);
		
		tfPreco = new JTextField();
		tfPreco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if(!caracteres.contains(e.getKeyChar()+"")) {
					e.consume();
				}
			}
		});
		tfPreco.setColumns(10);
		tfPreco.setBounds(75, 150, 85, 30);
		contentPane.add(tfPreco);
		
		//Quantidade
		
		JLabel lblQtd = new JLabel("Qtd. ");
		lblQtd.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQtd.setBounds(236, 120, 107, 18);
		contentPane.add(lblQtd);
		
		tfQtd = new JTextField();
		tfQtd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if(!caracteres.contains(e.getKeyChar()+"")) {
					e.consume();
				}
			}
		});
		
		tfQtd.setToolTipText("Quantidade");
		tfQtd.setColumns(10);
		tfQtd.setBounds(282, 112, 144, 30);
		contentPane.add(tfQtd);
		
		//Ano
		
		JLabel lblAno = new JLabel("Ano");
		lblAno.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAno.setBounds(316, 154, 49, 20);
		contentPane.add(lblAno);
		
		LocalDate localDate = LocalDate.now();
		int anoAtual = localDate.getYear();
		JComboBox cbAno = new JComboBox();
		cbAno.setBounds(359, 153, 67, 27);

		for(int i = anoAtual; i >= 1963; i--) {
			cbAno.addItem(String.valueOf(i));
		}
		contentPane.add(cbAno);
		
		
		//Area de observações
		taObservacoes = new JTextArea();
		taObservacoes.setToolTipText("Observações");
		taObservacoes.setBounds(10, 201, 416, 60);
		contentPane.add(taObservacoes);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String marca = tfMarca.getText();
				    String modelo = tfModelo.getText();
				    String cor = tfCor.getText();
					int cilindrada = Integer.parseInt((String)cbCilindrada.getSelectedItem());
					int ano = Integer.parseInt((String)cbAno.getSelectedItem());
					BigDecimal preco = new BigDecimal(tfPreco.getText());
					int quantidade = Integer.parseInt(tfQtd.getText());
					String observacoes = taObservacoes.getText();
					cadastro.adiciona(conexao.getConnection(), new Moto(marca,modelo,cor,cilindrada,ano,preco,quantidade,observacoes));
					JOptionPane.showMessageDialog(btnCadastrar, "Cadastrado com sucesso");
					escreveDadosNaTabela();
					limparCampos();
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
		});	
		btnCadastrar.setBackground(UIManager.getColor("Button.light"));
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrar.setBounds(341, 272, 85, 26);
		contentPane.add(btnCadastrar);
		
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(i != -1) {
					model.setValueAt(tfMarca.getText(), i, 1);
					model.setValueAt(tfModelo.getText(), i, 2);
					model.setValueAt(tfCor.getText(), i, 3);
					model.setValueAt(cbCilindrada.getSelectedItem().toString(), i, 4);
					model.setValueAt(cbAno.getSelectedItem().toString(), i, 5);
					model.setValueAt(tfPreco.getText(), i, 6);
					model.setValueAt(tfQtd.getText(), i, 7);
					model.setValueAt(taObservacoes.getText(), i, 8);
					
					int id = (int) model.getValueAt(i, 0);
					String marca = (String) model.getValueAt(i, 1);
					String modelo = (String) model.getValueAt(i, 2);
					String cor = (String) model.getValueAt(i, 3);
					int cilindrada = Integer.parseInt((String)model.getValueAt(i, 4));
					int ano = Integer.parseInt((String) model.getValueAt(i, 5));
					BigDecimal preco = new BigDecimal((String)model.getValueAt(i, 6));
					int quantidade = Integer.parseInt((String) model.getValueAt(i, 7));
					String observacoes = (String) model.getValueAt(i, 8);
					
					Moto moto = new Moto(marca, modelo, cor, cilindrada, ano, preco, quantidade, observacoes);
					moto.setId(id);
					cadastro.altera(conexao.getConnection(), moto);
					limparCampos();
				}else {
					JOptionPane.showMessageDialog(null, "ERRO");
				}
				
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalvar.setBackground(UIManager.getColor("Button.light"));
		btnSalvar.setBounds(341, 349, 85, 26);
		contentPane.add(btnSalvar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 272, 320, 140);
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
			},
			new String[] {
				"ID", "Marca", "Modelo", "Cor", "Cilindrada", "Ano", "Pre\u00E7o", "Qtd", "Obs"
			}
		));
		escreveDadosNaTabela();
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					int i = table.getSelectedRow();
					if(i != -1) {
						tfMarca.setText(model.getValueAt(i, 1).toString());
					    tfModelo.setText(model.getValueAt(i, 2).toString());
					    tfCor.setText(model.getValueAt(i, 3).toString());
					    cbCilindrada.setSelectedItem(model.getValueAt(i, 4).toString());
					    cbAno.setSelectedItem(model.getValueAt(i, 5).toString());
					    tfPreco.setText(model.getValueAt(i, 6).toString());
					    tfQtd.setText(model.getValueAt(i, 7).toString());
						taObservacoes.setText(model.getValueAt(i, 8).toString());
						//cbAnoContratacao.getSelectedItem()
					}else {
						JOptionPane.showMessageDialog(null, "Selecione um vendedor!");
					}
					
				}catch(Exception ex) {
					System.out.println("Não foi possível fazer a alteração");
				}
			}
		});
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAlterar.setBackground(UIManager.getColor("Button.light"));
		btnAlterar.setBounds(341, 312, 85, 26);
		contentPane.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel modelo = (DefaultTableModel)table.getModel();
				int i = table.getSelectedRow();
				
				if(i >= 0) {
					cadastro.remove(conexao.getConnection(), (int) table.getValueAt(i, 0));
					modelo.removeRow(i);
				}else {
					System.out.println("Não foi possível remover o vendedor selecionado");
				}
					
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExcluir.setBackground(UIManager.getColor("Button.light"));
		btnExcluir.setBounds(341, 386, 85, 26);
		contentPane.add(btnExcluir);
		
		
	}
	
	public void escreveDadosNaTabela() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		for(Moto m : cadastro.lista(conexao.getConnection())) {
			model.addRow(new Object[] {
					m.getId(),
					m.getMarca(),
					m.getModelo(),
					m.getCor(),
					m.getCilindrada(),
					m.getAno(),
					m.getPreco(),
					m.getQuantidade(),
					m.getObservacoes()	
			});
		}
	}
	
	public void limparCampos() {
		tfMarca.setText("");
		tfModelo.setText("");
		tfCor.setText("");
		tfPreco.setText("");
		tfQtd.setText("");
		taObservacoes.setText("");
	}
}