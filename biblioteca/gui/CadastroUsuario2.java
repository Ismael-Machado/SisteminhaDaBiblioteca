package biblioteca.gui;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import com.toedter.calendar.JDateChooser;

import biblioteca.dao.UsuarioDAO;
import biblioteca.empty.Pessoa;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JFormattedTextField;

import java.awt.FlowLayout;
import java.awt.Font;

public class CadastroUsuario2 extends JDialog {

	private JPanel painelForm,typeUser,painelPrincipal;
	private JFormattedTextField tfNome,tfRg,tfCpf,tfTelefone;
	private MaskFormatter mkCpf,mkTelefone,mkNome,mkRg;
	private JDateChooser jdc;
	private JTextField tfEndereco;
	private JButton btnContinuar,btnVolta;
	private JRadioButton rbGraduacao,rbPosGraduacao
					,rbTecnico,rbProfessor;
	private ButtonGroup bg;
	private final String alfabeto = "abcdefghijklmnopqrstuvxzwyç ";
	private final String numerico = "0123456789";
	private JLabel lblDataDeNascimento,lblRg,lblImage,lblEndereco
					,lblNome,lblTelefone;
	private ImageIcon imagem;
	
	public CadastroUsuario2(){
		setResizable(false);
		setTitle("CADASTRAMENTO DE USUARIO");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 641, 307);
		painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
		painelPrincipal.setBorder(BorderFactory.createTitledBorder("FORMULARIO"));
		painelForm = new JPanel();
		painelForm.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painelPrincipal);
		
		//TypeUser
		rbGraduacao = new JRadioButton("Graduação");
		rbGraduacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rbGraduacao.isSelected()) {
					painelPrincipal.add(painelForm);
					painelPrincipal.revalidate();
				}
			}
		});
		rbPosGraduacao = new JRadioButton("Pos Graduação");
		rbPosGraduacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rbPosGraduacao.isSelected()) {
					painelPrincipal.add(painelForm);
					painelPrincipal.revalidate();
				}
			}
		});
		rbTecnico = new JRadioButton("Técnico");
		rbTecnico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rbTecnico.isSelected()) {
					painelPrincipal.add(painelForm);
					painelPrincipal.revalidate();
				}
			}
		});
		rbProfessor = new JRadioButton("Professor");
		rbProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rbProfessor.isSelected()) {
					painelPrincipal.add(painelForm);
					painelPrincipal.revalidate();
				}
			}
		});
		bg = new ButtonGroup();
		bg.add(rbGraduacao);
		bg.add(rbPosGraduacao);
		bg.add(rbTecnico);
		bg.add(rbProfessor);
		typeUser = new JPanel(new FlowLayout());
		typeUser.add(rbGraduacao);
		typeUser.add(rbPosGraduacao);
		typeUser.add(rbTecnico);
		typeUser.add(rbProfessor);
		//Data de Nascimento
		jdc = new JDateChooser();
		jdc.setDateFormatString("dd/MM/yyyy");
		//Máscaras do campos
		mkCpf=null;
		mkTelefone=null;
		mkNome = null;
		mkRg = null;
		try{
			mkCpf = new MaskFormatter("###.###.###-##");
			mkTelefone = new MaskFormatter("(##)9 ####-####");
			mkNome = new MaskFormatter("**************************************************");
			mkRg = new MaskFormatter("********************");
		}catch(ParseException pe) {
			System.exit(-1);
		}
		mkCpf.setPlaceholderCharacter('_');
		mkTelefone.setPlaceholderCharacter('_');
		mkNome.setValidCharacters(alfabeto+alfabeto.toUpperCase());
		mkRg.setValidCharacters(numerico);
		
		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Dialog", Font.BOLD, 12));
		tfNome = new JFormattedTextField(mkNome);
		tfNome.setColumns(15);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Dialog", Font.BOLD, 12));
		tfCpf = new JFormattedTextField(mkCpf);
		tfCpf.setColumns(11);
		
		lblEndereco = new JLabel("Endereço:");
		lblEndereco.setFont(new Font("Dialog", Font.BOLD, 12));
		
		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Dialog", Font.BOLD, 12));
		tfTelefone = new JFormattedTextField(mkTelefone);
		tfTelefone.setColumns(10);

		lblImage = new JLabel();
		imagem = new ImageIcon(getClass().getResource("../img/cad_usuario.jpeg"));
		lblImage.setIcon(imagem);
		
		btnVolta = new JButton("Volta");
		btnVolta.setFont(new Font("Dialog", Font.BOLD, 12));
		btnVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Dialog", Font.BOLD, 12));
		
		lblRg = new JLabel("RG:");
		lblRg.setFont(new Font("Dialog", Font.BOLD, 12));
		
		tfRg = new JFormattedTextField(mkRg);
		tfRg.setColumns(10);
		
		tfEndereco = new JTextField();
		tfEndereco.setColumns(10);
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsuarioDAO udao = new UsuarioDAO();
				String nome = tfNome.getText();
				Date dt = jdc.getDate();
				int rg = 0;
				try{
					rg = Integer.parseInt(tfRg.getText());
				}catch (Exception e) {
					// TODO: handle exception
					System.out.println("erro");
				}
				String cpf = tfCpf.getText().replaceAll("[^0-9]", "");
				String telefone = tfTelefone.getText().replaceAll("[^0-9]", "");
				String endereco = tfEndereco.getText();
				
				if(nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() 
						|| endereco.isEmpty()) {
					System.out.println("ola");
				}else if(udao.consultar(tfCpf.getText().replaceAll("[^0-9]", ""))) {
					JOptionPane.showMessageDialog(null, "Usuario ja cadastrado!");
				}else {
					Pessoa p = new Pessoa(nome, cpf, rg, endereco, telefone, dt);
					udao.insert(p);
					dispose();
				}
			}
		});
		
		GroupLayout gl_painelForm = new GroupLayout(painelForm);
		gl_painelForm.setHorizontalGroup(
			gl_painelForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelForm.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_painelForm.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_painelForm.createSequentialGroup()
							.addGroup(gl_painelForm.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRg)
								.addComponent(lblCpf))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_painelForm.createParallelGroup(Alignment.LEADING)
								.addComponent(tfCpf, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfRg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_painelForm.createSequentialGroup()
							.addGroup(gl_painelForm.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTelefone)
								.addComponent(lblEndereco))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_painelForm.createParallelGroup(Alignment.LEADING)
								.addComponent(tfEndereco, GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
								.addComponent(tfTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnVolta, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
						.addComponent(btnContinuar, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
						.addGroup(gl_painelForm.createSequentialGroup()
							.addComponent(lblDataDeNascimento)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jdc, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
						.addGroup(gl_painelForm.createSequentialGroup()
							.addComponent(lblNome)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfNome, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblImage, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_painelForm.setVerticalGroup(
			gl_painelForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelForm.createSequentialGroup()
					.addGroup(gl_painelForm.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblImage, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_painelForm.createSequentialGroup()
							.addGroup(gl_painelForm.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNome)
								.addComponent(tfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_painelForm.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDataDeNascimento)
								.addComponent(jdc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(23)
							.addGroup(gl_painelForm.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCpf)
								.addComponent(tfCpf, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_painelForm.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefone)
								.addComponent(tfTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_painelForm.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEndereco)
								.addComponent(tfEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVolta)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnContinuar))
						.addGroup(Alignment.LEADING, gl_painelForm.createSequentialGroup()
							.addGap(46)
							.addGroup(gl_painelForm.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRg)
								.addComponent(tfRg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(53, Short.MAX_VALUE))
		);
		painelPrincipal.add(typeUser);
		painelForm.setLayout(gl_painelForm);
		setModal(true);
		setVisible(true);
	}
}
