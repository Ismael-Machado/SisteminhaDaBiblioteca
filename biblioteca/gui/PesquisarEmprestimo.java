package biblioteca.gui;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import biblioteca.dao.ItemEmprestimoDAO;
import biblioteca.dao.UsuarioDAO;
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

public class PesquisarEmprestimo extends JDialog {

	private JPanel contentPane;
	private JTextField textField;
	protected TabelaDinamicaEmprestimo tde;
	
	public PesquisarEmprestimo(String operacao) {
		setTitle(operacao.toUpperCase()+" EMPRESTIMO");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 350, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JRadioButton rdbtnCodigo = new JRadioButton("Codigo Exemplar");
		rdbtnCodigo.setSelected(true);
		JRadioButton rdbtnUsuario = new JRadioButton("Usuario");
		ButtonGroup grup = new ButtonGroup();
		grup.add(rdbtnCodigo);
		grup.add(rdbtnUsuario);
		
		JButton btnExecutar = new JButton(operacao);
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = "";
				int id = -1;
				ItemEmprestimoDAO iedao = new ItemEmprestimoDAO();
				UsuarioDAO udao = new UsuarioDAO();
				if(textField.getText().isEmpty()) 
					JOptionPane.showMessageDialog(null, "Campo Vazio!");
				else {
					if(rdbtnCodigo.isSelected()) {
						if(iedao.consultar(Integer.parseInt(textField.getText())))
							id = Integer.parseInt(textField.getText());
					}else if(udao.consultar(textField.getText()))
						usuario = textField.getText();
					
					if(!usuario.isEmpty() || id!=-1) {
						tde = new TabelaDinamicaEmprestimo(usuario,id);
						switch(operacao.toLowerCase()) {
						case "encerrar":
							tde.btnEncerrar.setEnabled(true);
							tde.setVisible(true);
							dispose();
							break;
						default:
							JOptionPane.showMessageDialog(null, "Ocorreu Um Erro com a operecao escolhida"
									+ " entre em contato com o suporte explicando o Ocorrido");
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
		ImageIcon icon = new ImageIcon(getClass().getResource("../img/pesquisar_emprestimo.jpg"));
		lblImage.setIcon(icon);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnExecutar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVoltar, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
						.addComponent(textField, 270, 270, 270)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(rdbtnCodigo, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnUsuario, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblImage, GroupLayout.PREFERRED_SIZE, 270, Short.MAX_VALUE))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImage, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnUsuario)
						.addComponent(rdbtnCodigo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExecutar)
						.addComponent(btnVoltar))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
