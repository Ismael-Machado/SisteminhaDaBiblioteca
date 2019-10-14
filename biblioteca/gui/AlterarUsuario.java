package biblioteca.gui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import biblioteca.dao.UsuarioDAO;
import biblioteca.empty.Usuario;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class AlterarUsuario extends JDialog {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfCpf;
	private JTextField tfEndereco;
	private JTextField tfTelefone;
	private JTextField tfUsuario;
	
	/**
	 * Create the frame.
	 */
	public AlterarUsuario(Usuario u) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setTitle("SISTEMA BIBLIOTECARIO");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Máscaras do campos
		MaskFormatter mkCpf=null;
		MaskFormatter mkTelefone=null;
		try{
			mkCpf = new MaskFormatter("###.###.###-##");
			mkTelefone = new MaskFormatter("(##)####-####");
		}catch(ParseException pe) {
			System.exit(-1);
		}
		mkCpf.setPlaceholderCharacter('_');
		mkTelefone.setPlaceholderCharacter('_');
	
		JLabel label = new JLabel("Codigo do Usuario:");
		
		JLabel lblNome = new JLabel("Nome:");
		
		JLabel lblCpf = new JLabel("CPF:");
		
		JLabel lblEndereo = new JLabel("Endereco:");
		
		JLabel lblTelefone = new JLabel("Telefone:");
		
		JLabel lblUsuario = new JLabel("Usuario:");
		

		JLabel lblCodigoDeUsuario = new JLabel(Integer.toString(u.getUsuarioCod()));		

		tfUsuario = new JTextField(u.getUsuario());
		tfUsuario.setColumns(10);
		
		tfNome = new JTextField(u.getNome());
		tfNome.setColumns(10);
		
		tfCpf = new JTextField(u.getCPF());
		tfCpf.setColumns(10);
		
		tfEndereco = new JTextField(u.getEndereco());
		tfEndereco.setColumns(10);
		
		tfTelefone = new JTextField(u.getTelefone());
		tfTelefone.setColumns(10);
		
		JLabel lblAlterar = new JLabel("ALTERACAO DE DADOS DO USUARIO");
		
		JButton btnVolta = new JButton("Volta");
		btnVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

			
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				u.setNome(tfNome.getText());
				u.setCPF(tfCpf.getText());
				u.setEndereco(tfEndereco.getText());
				u.setTelefone(tfTelefone.getText());
				UsuarioDAO ud = new UsuarioDAO();
				ud.atualizar(u);
				dispose();
			}
		});
		
		JLabel lblImagem = new JLabel("");
		ImageIcon icon = new ImageIcon(getClass().getResource("../img/cad_usuario.jpeg"));
		lblImagem.setIcon(icon);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(147)
							.addComponent(lblCodigoDeUsuario))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblUsuario)
										.addComponent(lblNome)
										.addComponent(lblCpf)
										.addComponent(lblEndereo))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(tfCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(label)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblTelefone)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(tfTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnVolta)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnAlterar)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblImagem))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(77)
							.addComponent(lblAlterar)))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(lblAlterar)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCodigoDeUsuario)
								.addComponent(label))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUsuario)
								.addComponent(tfUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNome)
								.addComponent(tfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCpf)
								.addComponent(tfCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEndereo)
								.addComponent(tfEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefone)
								.addComponent(tfTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnVolta)
								.addComponent(btnAlterar)))
						.addComponent(lblImagem, 0, 0, Short.MAX_VALUE))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		setModal(true);
		setVisible(true);
	}
}
