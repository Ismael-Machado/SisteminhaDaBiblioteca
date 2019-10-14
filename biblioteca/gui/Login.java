package biblioteca.gui;

import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import biblioteca.empty.Funcionario;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Login extends JDialog {

	private JPanel contentPane;
	private JTextField tfCode;
	private JPasswordField pfSenha;
	private JLabel lblLogin,lblUsuario,lblSenha;
	private JButton btnEntrar,btnSair;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login(){
		setResizable(false);
		setTitle("SISTEMA BIBLIOTECARIO");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Arial", Font.BOLD, 15));
		lblUsuario = new JLabel("USUARIO:");
		lblUsuario.setFont(new Font("Arial", Font.BOLD, 12));
		lblSenha = new JLabel("SENHA:");
		lblSenha.setFont(new Font("Arial", Font.BOLD, 12));
		
		tfCode = new JTextField();
		tfCode.setColumns(10);
		
		pfSenha = new JPasswordField();
		
		btnEntrar = new JButton("ENTRAR");
		btnEntrar.setFont(new Font("Arial", Font.BOLD, 12));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = tfCode.getText();
				String senha = pfSenha.getText();
				Funcionario funcionario = null;
				if(funcionario.Autenticar(nome,senha)) {
					Home tela = new Home();
					tela.setVisible(true);
					dispose();
				}
			}
		});
		
		btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Arial", Font.BOLD, 12));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(JDialog.EXIT_ON_CLOSE);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(89)
					.addComponent(lblLogin)
					.addGap(90))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnEntrar)
						.addComponent(lblUsuario)
						.addComponent(lblSenha))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pfSenha, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
						.addComponent(tfCode)
						.addComponent(btnSair, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(11))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLogin)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsuario)
						.addComponent(tfCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(pfSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSenha))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEntrar)
						.addComponent(btnSair))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		this.pack();
		this.setLocationRelativeTo(null);
	}
}
