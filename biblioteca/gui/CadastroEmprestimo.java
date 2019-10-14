package biblioteca.gui;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import biblioteca.dao.ItemEmprestimoDAO;
import biblioteca.dao.UsuarioDAO;
import biblioteca.dao.VolumeLivroDAO;
import biblioteca.dao.livroDAO;
import biblioteca.empty.ItemEmprestimo;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class CadastroEmprestimo extends JDialog {

	private JPanel contentPane;
	private JTextField tfUsuario;
	private JTextField tfLivro1;
	private JTextField tfLivro2;
	private JTextField tfLivro3;
	private JTextField tfLivro4;
	private JTextField tfLivro5;
	private JButton btnAdd1;
	private JButton btnAdd2;
	private JButton btnAdd3;
	private JButton btnAdd4;
	private JButton btnAdd5;
	private JLabel lblLivros;
	private JButton btnCadastrar;
	private JButton btnVolta;
	private int quantLivros;
	private JButton btnLimparLivros;
	private final int MaxLivro = 5;
	private String titulo;
	private JLabel lblEmprestimo;
	private JLabel lblDevolucao;
	private int quantDiasEmprestimo;
	private SimpleDateFormat sdf;
	List<String> titulos;

	public CadastroEmprestimo() {
		setTitle("CADASTRAMENTO DE EMPRESTIMO");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 704, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblDadosDoEmprestimo = new JLabel("DADOS DO EMPRESTIMO");
		lblDadosDoEmprestimo.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lblUsuario = new JLabel("Usuario");
		
		lblLivros = new JLabel("Livros");

		tfUsuario = new JTextField();
		tfUsuario.setEditable(false);
		tfUsuario.setColumns(10);

		tfLivro1 = new JTextField();
		tfLivro1.setEditable(false);
		tfLivro1.setColumns(10);

		tfLivro2 = new JTextField();
		tfLivro2.setEditable(false);
		tfLivro2.setColumns(10);

		tfLivro3 = new JTextField();
		tfLivro3.setEditable(false);
		tfLivro3.setColumns(10);

		tfLivro4 = new JTextField();
		tfLivro4.setEditable(false);
		tfLivro4.setColumns(10);

		tfLivro5 = new JTextField();
		tfLivro5.setEditable(false);
		tfLivro5.setColumns(10);
		
		JButton btnAdd1 = new JButton("Add");
		btnAdd1.setEnabled(false);
		
		btnAdd2 = new JButton("Add");
		btnAdd2.setEnabled(false);
		
		btnAdd3 = new JButton("Add");
		btnAdd3.setEnabled(false);
		
		btnAdd4 = new JButton("Add");
		btnAdd4.setEnabled(false);
		
		btnAdd5 = new JButton("Add");
		btnAdd5.setEnabled(false);
		
		quantDiasEmprestimo = 10;
		Calendar c = Calendar.getInstance();
		Date dataEmprestimo = c.getTime();
		sdf = new SimpleDateFormat();
		c.set(Calendar.DATE,(c.get(Calendar.DATE)+quantDiasEmprestimo));
		c.set(Calendar.HOUR_OF_DAY,23);
		c.set(Calendar.MINUTE,59);
		Date dataDevolucao = c.getTime();
		lblEmprestimo = new JLabel(sdf.format(dataEmprestimo));
		lblDevolucao = new JLabel(sdf.format(dataDevolucao));

		JButton btnPesquisarUsuario = new JButton("Pesquisar");
		btnPesquisarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PesquisarUsuario pu = new PesquisarUsuario(btnPesquisarUsuario.getText());
				pu.btnLista.setVisible(false);
				pu.setVisible(true);
				String usuario = pu.tdu.aux; 
				UsuarioDAO udao = new UsuarioDAO();
				ItemEmprestimoDAO iedao = new ItemEmprestimoDAO();
				if(!usuario.equals("")) {
					quantLivros = iedao.getQuantidade(udao.getUsuario(usuario).getUsuarioCod());
					if(quantLivros==MaxLivro)
						JOptionPane.showMessageDialog(null, "Usuario ja possui mais que 5 Livros Emprestados");
					else {
						titulos = new ArrayList<>();
						tfUsuario.setText(usuario);
						btnAdd1.setEnabled(true);
						btnPesquisarUsuario.setEnabled(false);
					}	
				}
			}
		});

		btnAdd1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				livroDAO ldao = new livroDAO();
				VolumeLivroDAO vldao = new VolumeLivroDAO();
				if(quantLivros!=MaxLivro) {
					PesquisarLivro pl = new PesquisarLivro(btnAdd1.getText());
					pl.setVisible(true);
					titulo = pl.tdl.aux;
					if(!titulo.isEmpty()) {
						int i = vldao.getQuantDisponivel(ldao.getLivro(titulo).getLivroCodigo());
						if(i>1) {
							tfLivro1.setText(titulo);
							btnAdd2.setEnabled(true);
							btnAdd1.setEnabled(false);
							btnLimparLivros.setEnabled(true);
							quantLivros++;
							titulos.add(tfLivro1.getText());
						}else
							JOptionPane.showMessageDialog(null, "Livro Indisponivel para Emprestimo");
					}
				}else
					JOptionPane.showMessageDialog(null, "Impossivel adicionar mais livros");
			}
		});

		btnAdd2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				livroDAO ldao = new livroDAO();
				VolumeLivroDAO vldao = new VolumeLivroDAO();
				if(quantLivros!=MaxLivro) {
					PesquisarLivro pl = new PesquisarLivro(btnAdd1.getText());
					pl.setVisible(true);
					titulo = pl.tdl.aux;
					if(!titulo.isEmpty()) {
						int i = vldao.getQuantDisponivel(ldao.getLivro(titulo).getLivroCodigo());
						if(i>1) {
							tfLivro2.setText(titulo);
							btnAdd3.setEnabled(true);
							btnAdd2.setEnabled(false);
							quantLivros++;
							titulos.add(tfLivro1.getText());
						}
					}
				}else
					JOptionPane.showMessageDialog(null, "Impossivel adicionar mais livros");
			}
		});

		btnAdd3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				livroDAO ldao = new livroDAO();
				VolumeLivroDAO vldao = new VolumeLivroDAO();
				if(quantLivros!=MaxLivro) {
					PesquisarLivro pl = new PesquisarLivro(btnAdd1.getText());
					pl.setVisible(true);
					titulo = pl.tdl.aux;
					if(!titulo.isEmpty()) {
						int i = vldao.getQuantDisponivel(ldao.getLivro(titulo).getLivroCodigo());
						if(i>1) {
							tfLivro3.setText(titulo);
							btnAdd4.setEnabled(true);
							btnAdd3.setEnabled(false);
							quantLivros++;
							titulos.add(tfLivro1.getText());
						}
					}
				}else
					JOptionPane.showMessageDialog(null, "Impossivel adicionar mais livros");
			}
		});

		btnAdd4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				livroDAO ldao = new livroDAO();
				VolumeLivroDAO vldao = new VolumeLivroDAO();
				if(quantLivros!=MaxLivro) {
					PesquisarLivro pl = new PesquisarLivro(btnAdd1.getText());
					pl.setVisible(true);
					titulo = pl.tdl.aux;
					if(!titulo.isEmpty()) {
						int i = vldao.getQuantDisponivel(ldao.getLivro(titulo).getLivroCodigo());
						if(i>1) {
							tfLivro4.setText(titulo);
							btnAdd5.setEnabled(true);
							btnAdd4.setEnabled(false);
							quantLivros++;
							titulos.add(tfLivro1.getText());
						}
					}
				}else
					JOptionPane.showMessageDialog(null, "Impossivel adicionar mais livros");
			}
		});

		btnAdd5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				livroDAO ldao = new livroDAO();
				VolumeLivroDAO vldao = new VolumeLivroDAO();
				if(quantLivros!=MaxLivro) {
					PesquisarLivro pl = new PesquisarLivro(btnAdd1.getText());
					pl.setVisible(true);
					titulo = pl.tdl.aux;
					if(!titulo.isEmpty()) {
						int i = vldao.getQuantDisponivel(ldao.getLivro(titulo).getLivroCodigo());
						if(i>1) {
							tfLivro5.setText(titulo);
							btnAdd5.setEnabled(false);
							quantLivros++;
							titulos.add(tfLivro1.getText());
						}
					}
				}else
					JOptionPane.showMessageDialog(null, "Impossivel adicionar mais livros");
			}
		});

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ItemEmprestimoDAO iedao = new ItemEmprestimoDAO();
				UsuarioDAO udao = new UsuarioDAO();
				VolumeLivroDAO vldao = new VolumeLivroDAO();
				livroDAO ldao = new livroDAO();
				if(!tfUsuario.getText().equals("") && !titulos.isEmpty()) {
					int idUsuario = udao.getUsuario(tfUsuario.getText()).getUsuarioCod();
					for (String s : titulos) {
						int idLivro = ldao.getLivro(s).getLivroCodigo();
						int idVolume = vldao.getIdDisponivel(idLivro);
						vldao.Emprestar(idVolume);
						ItemEmprestimo ie = new ItemEmprestimo(idVolume,idUsuario,dataEmprestimo,dataDevolucao);
						iedao.inserir(ie);
					}
					JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
					dispose();	
				}

			}
		});

		btnVolta = new JButton("Volta");
		btnVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnLimparLivros = new JButton("Limpar Livros");
		btnLimparLivros.setEnabled(false);
		btnLimparLivros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsuarioDAO udao = new UsuarioDAO();
				ItemEmprestimoDAO iedao = new ItemEmprestimoDAO();
				quantLivros = iedao.getQuantidade(udao.getUsuario(tfUsuario.getText()).getUsuarioCod());
				tfLivro1.setText("");
				tfLivro2.setText("");
				tfLivro3.setText("");
				tfLivro4.setText("");
				tfLivro5.setText("");
				btnAdd1.setEnabled(true);
				btnAdd2.setEnabled(false);
				btnAdd3.setEnabled(false);
				btnAdd4.setEnabled(false);
				btnAdd5.setEnabled(false);
			}
		});
		
		JLabel lblImagem = new JLabel("");
		ImageIcon icon = new ImageIcon(getClass().getResource("../img/emprestimo.png"));
		lblImagem.setIcon(icon);
		
		JLabel lblDataDeEmprestimo = new JLabel("Data e Hora de Emprestimo");
		
		JLabel lblDataDeDevoluao = new JLabel("Data e Hora de Devoluçao");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblUsuario, Alignment.TRAILING)
												.addComponent(lblLivros))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addComponent(tfLivro3, Alignment.TRAILING)
												.addComponent(tfLivro2, Alignment.TRAILING)
												.addComponent(tfLivro1)
												.addComponent(tfLivro4, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
												.addComponent(tfLivro5, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
												.addComponent(tfUsuario)))
										.addComponent(btnCadastrar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnVolta, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnPesquisarUsuario, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(btnAdd5, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnAdd4, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnAdd3, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnAdd2, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnAdd1))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnLimparLivros))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblDataDeEmprestimo)
											.addGap(18))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblEmprestimo, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblDevolucao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGap(206))
										.addComponent(lblDataDeDevoluao, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(522))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(101)
							.addComponent(lblDadosDoEmprestimo)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblDadosDoEmprestimo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDataDeEmprestimo)
								.addComponent(lblDataDeDevoluao))
							.addGap(1)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDevolucao)
								.addComponent(lblEmprestimo))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblUsuario)
										.addComponent(tfUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblLivros)
										.addComponent(tfLivro1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfLivro2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfLivro3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfLivro4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfLivro5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnPesquisarUsuario, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnLimparLivros, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(btnAdd1, GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnAdd2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnAdd3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnAdd4, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnAdd5, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))))))
						.addComponent(lblImagem, 0, 0, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrar)
						.addComponent(btnVolta))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
