package biblioteca.gui;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.text.MaskFormatter;
import com.toedter.calendar.JDateChooser;
import biblioteca.dao.UsuarioDAO;
import biblioteca.empty.Pessoa;
import biblioteca.gui.Templates.TemplateForm;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;

public class CadastroUsuario extends JFrame {

	private JPanel painelForm,typeUser,painelPrincipal;
	private JFormattedTextField tfNome,tfRg,tfCpf,tfTelefone;
	private MaskFormatter mkCpf,mkTelefone,mkNome,mkRg;
	private JDateChooser jdc;
	private JTextField tfLogradouro;
	private JButton btnContinuar,btnVolta;
	private JRadioButton rbGraduacao,rbPosGraduacao
	,rbTecnico,rbProfessor;
	private ButtonGroup bg;
	private final String alfabeto = "abcdefghijklmnopqrstuvxzwyç ";
	private final String numerico = "0123456789";
	private JLabel lblDataDeNascimento,lblRg,lblEndereco
	,lblNome,lblTelefone;
	private JLabel lblLogradouro;
	private JLabel lblN;
	private JTextField tfNumResidencia;
	private JTextField tfBairro;
	private JTextField tfCidade;
	private JLabel lblDadosPessoais;

	public CadastroUsuario(){
		setResizable(false);
		setTitle("CADASTRAMENTO DE USUARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Objetos do Painel Principal
		File f = new File("//media//tiago//Dados//eclipse-workspace//tese-1//src//biblioteca//img//cad_usuario.png");
		try {
			Templates.FundoBg(f);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("não achou o arquivo");
		}
		TemplateForm painelPrincipal = new TemplateForm();
		painelPrincipal.setBorder(BorderFactory.createTitledBorder("INFORMACOES BASICAS"));
		setSize(763, 700);
		setContentPane(painelPrincipal);

		//Objetos do Painel de Formulario
		painelForm = new JPanel();
		//Mascaras do campos
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

		lblDadosPessoais = new JLabel("DADOS PESSOAIS");
		lblDadosPessoais.setFont(new Font("Tahoma", Font.BOLD, 11));

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Dialog", Font.BOLD, 12));
		tfNome = new JFormattedTextField(mkNome);
		tfNome.setColumns(15);

		//Data de Nascimento
		lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Dialog", Font.BOLD, 12));
		jdc = new JDateChooser();
		jdc.setDateFormatString("dd/MM/yyyy");

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Dialog", Font.BOLD, 12));
		tfCpf = new JFormattedTextField(mkCpf);
		tfCpf.setColumns(11);

		lblRg = new JLabel("RG:");
		lblRg.setFont(new Font("Dialog", Font.BOLD, 12));
		tfRg = new JFormattedTextField(mkRg);
		tfRg.setColumns(10);

		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Dialog", Font.BOLD, 12));
		tfTelefone = new JFormattedTextField(mkTelefone);
		tfTelefone.setColumns(10);

		lblEndereco = new JLabel("ENDERECO");
		lblEndereco.setFont(new Font("Dialog", Font.BOLD, 12));

		lblLogradouro = new JLabel("Logradouro:");
		tfLogradouro = new JTextField();
		tfLogradouro.setColumns(10);

		lblN = new JLabel("Nº");
		tfNumResidencia = new JTextField();
		tfNumResidencia.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro:");		
		tfBairro = new JTextField();
		tfBairro.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade:");
		tfCidade = new JTextField();
		tfCidade.setColumns(10);

		btnVolta = new JButton("Volta");
		btnVolta.setFont(new Font("Dialog", Font.BOLD, 12));
		btnVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

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
				String endereco = tfLogradouro.getText();

				if(nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() 
						|| endereco.isEmpty()) {
					System.out.println("ola");
				}else if(!Pessoa.validaCPF(cpf)) {
					System.out.println("CPF Inválido");
				}
				else if(udao.consultar(tfCpf.getText().replaceAll("[^0-9]", ""))) {
					JOptionPane.showMessageDialog(null, "Usuario ja cadastrado!");
				}else {
					//					Pessoa p = new Pessoa(nome, cpf, rg, endereco, telefone, dt);
					//					udao.insert(p);
					//					dispose();
				}
			}
		});
		painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));

		//TypeUser
		typeUser = new JPanel();
		typeUser.setBorder(BorderFactory.createTitledBorder("Selecione o tipo de usuario"));
		rbGraduacao = new JRadioButton("Graduação");
		rbPosGraduacao = new JRadioButton("Pos Graduação");
		rbTecnico = new JRadioButton("Técnico");
		rbProfessor = new JRadioButton("Professor");

		bg = new ButtonGroup();
		bg.add(rbGraduacao);
		bg.add(rbPosGraduacao);
		bg.add(rbTecnico);
		bg.add(rbProfessor);

		typeUser.add(rbGraduacao);
		typeUser.add(rbPosGraduacao);
		typeUser.add(rbTecnico);
		typeUser.add(rbProfessor);
		//FimTypeUser

		GroupLayout gl_painelForm = new GroupLayout(painelForm);
		gl_painelForm.setHorizontalGroup(
			gl_painelForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelForm.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_painelForm.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_painelForm.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_painelForm.createSequentialGroup()
								.addGroup(gl_painelForm.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_painelForm.createSequentialGroup()
										.addComponent(lblNome)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(tfNome, GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE))
									.addGroup(gl_painelForm.createSequentialGroup()
										.addComponent(lblLogradouro)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(tfLogradouro, GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)))
								.addContainerGap())
							.addGroup(gl_painelForm.createSequentialGroup()
								.addComponent(lblDadosPessoais)
								.addGap(313))
							.addGroup(gl_painelForm.createSequentialGroup()
								.addComponent(lblTelefone)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(tfTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(536, Short.MAX_VALUE))
							.addGroup(gl_painelForm.createSequentialGroup()
								.addComponent(lblN)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(tfNumResidencia, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblBairro)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(tfBairro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblCidade)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(tfCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(247, Short.MAX_VALUE))
							.addGroup(gl_painelForm.createSequentialGroup()
								.addGroup(gl_painelForm.createParallelGroup(Alignment.TRAILING)
									.addComponent(typeUser, GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
									.addGroup(gl_painelForm.createSequentialGroup()
										.addComponent(btnVolta, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnContinuar, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)))
								.addContainerGap())
							.addGroup(gl_painelForm.createSequentialGroup()
								.addComponent(lblDataDeNascimento)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jdc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblRg)
								.addGap(19)
								.addComponent(tfRg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblCpf)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(tfCpf, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(135, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, gl_painelForm.createSequentialGroup()
							.addComponent(lblEndereco)
							.addGap(331))))
		);
		gl_painelForm.setVerticalGroup(
			gl_painelForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelForm.createSequentialGroup()
					.addComponent(lblDadosPessoais)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelForm.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelForm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDataDeNascimento)
						.addComponent(jdc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_painelForm.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblRg)
							.addComponent(tfRg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCpf)
							.addComponent(tfCpf, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelForm.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_painelForm.createSequentialGroup()
							.addGroup(gl_painelForm.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefone)
								.addComponent(tfTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(27))
						.addGroup(gl_painelForm.createSequentialGroup()
							.addComponent(lblEndereco)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_painelForm.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLogradouro)
						.addComponent(tfLogradouro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_painelForm.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblN)
						.addComponent(tfNumResidencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBairro)
						.addComponent(tfBairro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCidade)
						.addComponent(tfCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(typeUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_painelForm.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVolta)
						.addComponent(btnContinuar))
					.addContainerGap(383, Short.MAX_VALUE))
		);
		painelForm.setLayout(gl_painelForm);
		painelPrincipal.add(painelForm);
		//Fim Painel Informacoes b�sicas

		setLocationRelativeTo(null);
//		setModal(true);
		setVisible(true);
	}
}
