package biblioteca.gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import biblioteca.dao.UsuarioDAO;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class PesquisarUsuario extends JDialog {

	private JPanel contentPane;
	private JFormattedTextField textField;
	protected JButton btnLista;
	protected JButton btnVolta;
	protected TabelaDinamicaUsuario tdu=null;
	public PesquisarUsuario(String operacao) {
		setResizable(false);
		setTitle(operacao.toUpperCase()+" USUARIO");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 348, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JRadioButton rdbtnCpf = new JRadioButton("CPF");
		
		JRadioButton rdbtnNome = new JRadioButton("Nome");
		
		JRadioButton rdbtnUsuario = new JRadioButton("USUARIO");
		rdbtnUsuario.setSelected(true);
		
		ButtonGroup grup = new ButtonGroup();
		grup.add(rdbtnCpf);
		grup.add(rdbtnNome);
		grup.add(rdbtnUsuario);

		textField = new JFormattedTextField();
		textField.setColumns(10);

		JButton btnExecutar = new JButton(operacao);
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usuario = "";
				UsuarioDAO udao = new UsuarioDAO();
				if(textField.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Campo Vazio!");
				else {
					if(rdbtnUsuario.isSelected()) {
						if(udao.consultar(textField.getText())) 
							usuario =  textField.getText();
					}else if(rdbtnCpf.isSelected()) 
						usuario=udao.getUsuario(textField.getText().replaceAll("[^0-9]", "")).getUsuario();
					else {
						if(udao.consulta(textField.getText()))
							usuario = textField.getText();
					}

					if(!usuario.isEmpty()) {
						tdu = new TabelaDinamicaUsuario(usuario);					
						int i;
						switch(operacao.toLowerCase()) {
						case "alterar":
							tdu.btnAlterar.setEnabled(true);
							tdu.setVisible(true);
							dispose();
							break;
						case "desativar":
							tdu.btnDesativar.setEnabled(true);
							tdu.setVisible(true);
							dispose();
							break;
						case "ativar":
							Operacoes.ativar(udao.getUsuario(usuario).getUsuarioCod());
							dispose();
							break;
						case "pesquisar":
							tdu.btnSelecionar.setEnabled(true);
							tdu.setVisible(true);
							dispose();
							break;
						case "pesquisar ":
							tdu.setVisible(true);
							dispose();
							break;
						default:
							JOptionPane.showMessageDialog(null, "Ocorreu Um Erro, tente repetir a opera��o e"
									+ " entre em contato com o suporte explicando o Ocorreido");
						}
					}else 
						JOptionPane.showMessageDialog(null, "Usuario nao encontrado no Sistema!");
				}
			}
		});

		btnVolta = new JButton("Voltar");
		btnVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnLista = new JButton("LISTA DE USUARIOS");
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TabelaDinamicaUsuario tdu = new TabelaDinamicaUsuario("*");
				switch (operacao.toLowerCase()) {
				case "alterar":
					tdu.btnAlterar.setEnabled(true);
					break;
				case "desativar":
					tdu.btnDesativar.setEnabled(true);
					break;
				case "pesquisar":
					tdu.btnSelecionar.setEnabled(true);
					break;
				}
				tdu.setVisible(true);
				dispose();
			}
		});
		
		JLabel lblImagem = new JLabel("");
		ImageIcon icon = new ImageIcon(getClass().getResource("../img/pesquisar_pessoa.png"));
		lblImagem.setIcon(icon);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(rdbtnUsuario)
								.addGap(14)
								.addComponent(rdbtnNome)
								.addGap(4)
								.addComponent(rdbtnCpf))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnExecutar, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnVolta, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addComponent(textField)
							.addComponent(btnLista, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(lblImagem))
					.addContainerGap(57, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblImagem)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnUsuario)
						.addComponent(rdbtnNome)
						.addComponent(rdbtnCpf))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnExecutar)
						.addComponent(btnVolta))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLista)
					.addGap(53))
		);
		contentPane.setLayout(gl_contentPane);
		setModal(true);
	}
}
