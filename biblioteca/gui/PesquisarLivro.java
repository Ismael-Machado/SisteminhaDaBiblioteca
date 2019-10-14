package biblioteca.gui;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import biblioteca.dao.livroDAO;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class PesquisarLivro extends JDialog {

	private JPanel contentPane;
	private JTextField textField;
	protected TabelaDinamicaLivro tdl = null;
	
	public PesquisarLivro(String operacao) {
		setTitle(operacao.toUpperCase()+" LIVRO");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 340, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JRadioButton rdbtnCodigo = new JRadioButton("Codigo");
		rdbtnCodigo.setSelected(true);
		JRadioButton rdbtnTitulo = new JRadioButton("Titulo");
		ButtonGroup grup = new ButtonGroup();
		grup.add(rdbtnCodigo);
		grup.add(rdbtnTitulo);
		
		JButton btnExecutar = new JButton(operacao);
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = "";
				int id = -1;
				boolean status = true;
				livroDAO ldao = new livroDAO();
				if(textField.getText().isEmpty()) 
					JOptionPane.showMessageDialog(null, "Campo Vazio!");
				else {
					if(operacao.toLowerCase().equals("ativar"))
						status = false;
					if(rdbtnCodigo.isSelected()) {
						id = ldao.consultar(Integer.parseInt(textField.getText()),status);
					}else if(ldao.consultar(textField.getText(),status))
						titulo = textField.getText();
					
					if(!titulo.isEmpty() || id!=-1) {
						tdl = new TabelaDinamicaLivro(titulo,id);
						switch(operacao.toLowerCase()) {
						case "alterar":
							tdl.btnAlterar.setEnabled(true);
							tdl.setVisible(true);
							dispose();
							break;
						case "desativar":
							tdl.btnExcluir.setEnabled(true);
							tdl.setVisible(true);
							dispose();
							break;
						case "ativar":
							if(id!=-1)
								titulo=ldao.getLivro(id).getTitulo();
							Operacoes.ativarLivro(titulo);
							dispose();
							break;
						case "pesquisar":
							tdl.btnSelecionar.setEnabled(true);
							tdl.setVisible(true);
							dispose();
							break;
						case "add":
							tdl.btnSelecionar.setEnabled(true);
							tdl.setVisible(true);
							dispose();
							break;
						default:
							JOptionPane.showMessageDialog(null, "Ocorreu Um Erro, tente repetir a opera��o e"
									+ " entre em contato com o suporte explicando o Ocorreido");
						}
					}else 
						JOptionPane.showMessageDialog(null, "Livro nao encontrado no Sistema!");
				}
			}
		});
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel lblImage = new JLabel("");
		ImageIcon icon = new ImageIcon(getClass().getResource("../img/pesquisar_livro.jpg"));
		lblImage.setIcon(icon);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblImage)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(15)
									.addComponent(rdbtnCodigo, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
									.addComponent(rdbtnTitulo, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnExecutar, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnVoltar, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)))
							.addGap(28))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImage)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnTitulo)
						.addComponent(rdbtnCodigo))
					.addGap(8)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExecutar)
						.addComponent(btnVoltar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
