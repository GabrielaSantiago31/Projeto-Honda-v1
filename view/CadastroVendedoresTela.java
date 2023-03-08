package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import dao.VendedoresDao;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CadastroVendedoresTela extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfCpf;
	private JTextField tfSalario;
	private JRadioButton btnFeminino;
	private JRadioButton btnMasculino;
	private ButtonGroup btnSexo;
	private JTextArea taObservacoes;
	private JComboBox cbAnoContratacao;
	
	VendedoresDao cadastro = new VendedoresDao();
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
					CadastroVendedoresTela frame = new CadastroVendedoresTela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public CadastroVendedoresTela() {
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
		
		//Nome
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setBounds(10, 43, 74, 20);
		contentPane.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789!@#%$&*()-_/><:;[]{}?";
				if(caracteres.contains(e.getKeyChar()+"")) {
					e.consume();
				}
			}
		});
		tfNome.setBounds(75, 40, 351, 30);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		//Titulo
		JLabel lblTitulo = new JLabel("Cadastro de Vendedores");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(114, 10, 207, 13);
		contentPane.add(lblTitulo);
		
		//CPF
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCpf.setBounds(10, 83, 45, 13);
		contentPane.add(lblCpf);
		
		tfCpf = new JTextField();
		tfCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if(!caracteres.contains(e.getKeyChar()+"")) {
					e.consume();
				}
			}
		});
		tfCpf.setBounds(75, 76, 351, 30);
		contentPane.add(tfCpf);
		tfCpf.setColumns(10);
		
		//Salario
		JLabel lblSalario = new JLabel("Salario");
		lblSalario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSalario.setBounds(10, 119, 74, 20);
		contentPane.add(lblSalario);
		
		tfSalario = new JTextField();
		tfSalario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if(!caracteres.contains(e.getKeyChar()+"")) {
					e.consume();
				}
			}
		});
		tfSalario.setBounds(75, 112, 351, 30);
		contentPane.add(tfSalario);
		tfSalario.setColumns(10);
		
		//Ano de Contratacao
		JLabel lblAnos = new JLabel("Ano de Contratação");
		lblAnos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAnos.setBounds(10, 155, 156, 20);
		contentPane.add(lblAnos);
		
		LocalDate localDate = LocalDate.now();
		int anoAtual = localDate.getYear();
		
		JComboBox cbAnoContratacao = new JComboBox();
		for(int i = 0; i <= 59; i++) {
			cbAnoContratacao.addItem(String.valueOf(anoAtual - i));
		}
		
		cbAnoContratacao.setBounds(152, 156, 74, 22);
		contentPane.add(cbAnoContratacao);
		
		//Area de observações
		taObservacoes = new JTextArea();
		taObservacoes.setToolTipText("Observações");
		taObservacoes.setBounds(10, 189, 416, 71);
		contentPane.add(taObservacoes);
		
		//Sexo
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSexo.setBounds(269, 159, 45, 13);
		contentPane.add(lblSexo);
		
		
		btnFeminino = new JRadioButton("F");
		btnFeminino.setBounds(318, 157, 45, 21);
		contentPane.add(btnFeminino);
		
		btnMasculino = new JRadioButton("M");
		btnMasculino.setBounds(365, 157, 56, 21);
		contentPane.add(btnMasculino);
		
		btnSexo = new ButtonGroup();
		btnSexo.add(btnFeminino);
		btnSexo.add(btnMasculino);
		btnMasculino.setActionCommand("M");
		btnFeminino.setActionCommand("F");
		btnFeminino.setSelected(true);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nome = tfNome.getText();
				    String cpf = tfCpf.getText();
				    BigDecimal salario = new BigDecimal(tfSalario.getText());
					String sexo = (btnSexo.getSelection().getActionCommand());
					int anoContratacao = Integer.parseInt((String) cbAnoContratacao.getSelectedItem());
					String observacoes = taObservacoes.getText();
					cadastro.adiciona(conexao.getConnection(), new Vendedor(nome,cpf,sexo,salario,anoContratacao,observacoes));
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
		btnCadastrar.setBounds(341, 271, 85, 26);
		contentPane.add(btnCadastrar);
		
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				DefaultTableModel modelo = (DefaultTableModel) table.getModel();
				if(i != -1) {
					modelo.setValueAt(tfNome.getText(), i, 1);
					modelo.setValueAt(tfCpf.getText(), i, 2);
					modelo.setValueAt(new BigDecimal(tfSalario.getText()), i, 4);
					modelo.setValueAt(taObservacoes.getText(), i, 6);
					
					int id = (int) modelo.getValueAt(i, 0);
					String nome = (String) modelo.getValueAt(i, 1);
					String cpf = (String) modelo.getValueAt(i, 2);
					String sexo = (String) modelo.getValueAt(i, 3);
					BigDecimal salario = (BigDecimal) modelo.getValueAt(i, 4);
					int anoContratacao = (int) modelo.getValueAt(i, 5);
					String observacoes = (String) modelo.getValueAt(i, 6);
					
					Vendedor vendedor = new Vendedor(nome, cpf, sexo, salario, anoContratacao, observacoes);
					vendedor.setId(id);
					cadastro.altera(conexao.getConnection(), vendedor);
					limparCampos();
				}else {
					JOptionPane.showMessageDialog(null, "ERRO");
				}
				
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalvar.setBackground(UIManager.getColor("Button.light"));
		btnSalvar.setBounds(341, 346, 85, 26);
		contentPane.add(btnSalvar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 271, 320, 140);
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
				"ID", "Marca", "Modelo", "Cor", "Cilindrada", "Preco", "Obs"
			}
		));
		escreveDadosNaTabela();
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel modelo = (DefaultTableModel)table.getModel();
					int i = table.getSelectedRow();
					if(i != -1) {
						tfNome.setText(modelo.getValueAt(i, 1).toString());
					    tfCpf.setText(modelo.getValueAt(i, 2).toString());
					    tfSalario.setText(modelo.getValueAt(i, 4).toString());
						taObservacoes.setText(modelo.getValueAt(i, 6).toString());
						//cbAnoContratacao.getSelectedItem()
						//btnSexo.getSelection().getActionCommand()
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
		btnAlterar.setBounds(341, 309, 85, 26);
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
		btnExcluir.setBounds(341, 383, 85, 26);
		contentPane.add(btnExcluir);	
	}
	
	public void escreveDadosNaTabela() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setNumRows(0);
		for(Vendedor v : cadastro.lista(conexao.getConnection())) {
			modelo.addRow(new Object[] {
					v.getId(),
					v.getNome(),
					v.getCpf(),
					v.getSexo(),
					v.getSalario(),
					v.getAnoContratacao(),
					v.getObservacoes()	
			});
		}
	}
	
	
	public void limparCampos() {
		tfNome.setText("");
		tfCpf.setText("");
		tfSalario.setText("");
		taObservacoes.setText("");
	}
}