package biblioteca.gui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import biblioteca.dao.VolumeLivroDAO;
import biblioteca.dao.livroDAO;
import biblioteca.empty.Livro;
import biblioteca.empty.VolumeLivro;

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
import java.awt.Font;
import javax.swing.SwingConstants;

public class CadastrarLivro extends JDialog {

	private JPanel contentPane;
	private JTextField tfTitulo;
	private JTextField tfAutor;
	private JTextField tfEditora;
	private JTextField tfAno;
	private JTextField tfQuantExem;

	public CadastrarLivro(int id) {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("CADASTRAMENTO DE LIVRO");
		setModal(true);
		setBounds(100, 100, 568, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		
		JLabel lblCadastramentoDeLivro = new JLabel("FORMULARIO");
		lblCadastramentoDeLivro.setFont(new Font("Arial Black", Font.BOLD, 16));
		
		JLabel label = new JLabel("Codigo do Livro:");
		label.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblEditora = new JLabel("Editora:");
		lblEditora.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblAno = new JLabel("Ano:");
		lblAno.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblCodigoDoLivro = new JLabel(""+id);
		lblCodigoDoLivro.setFont(new Font("Arial", Font.BOLD, 14));
		
		tfTitulo = new JTextField();
		tfTitulo.setFont(new Font("Arial", Font.PLAIN, 12));
		tfTitulo.setColumns(10);
		
		tfAutor = new JTextField();
		tfAutor.setFont(new Font("Arial", Font.PLAIN, 12));
		tfAutor.setColumns(10);
		
		tfEditora = new JTextField();
		tfEditora.setFont(new Font("Arial", Font.PLAIN, 12));
		tfEditora.setColumns(10);
		
		tfAno = new JTextField();
		tfAno.setFont(new Font("Arial", Font.PLAIN, 12));
		tfAno.setColumns(10);
		
		JLabel lblQuantidadeDeExemplares = new JLabel("Quantidade de Exemplares");
		lblQuantidadeDeExemplares.setFont(new Font("Arial", Font.PLAIN, 12));
		
		tfQuantExem = new JTextField();
		tfQuantExem.setFont(new Font("Arial", Font.PLAIN, 12));
		tfQuantExem.setColumns(10);
		
		JLabel lblImagem = new JLabel();
		ImageIcon icon = new ImageIcon(getClass().getResource("../img/cad_livro.jpg"));
		lblImagem.setIcon(icon);
		
		JButton btnVolta = new JButton("Volta");
		btnVolta.setFont(new Font("Arial", Font.PLAIN, 12));
		btnVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JButton btnCadastra = new JButton("Cadastrar");
		btnCadastra.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCadastra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Livro livro = new Livro(id,
									tfAutor.getText(),
									tfEditora.getText(),
									tfTitulo.getText(),
									Integer.parseInt(tfAno.getText()));
				livroDAO ldao = new livroDAO();
				ldao.insert(livro);
				VolumeLivro vl = new VolumeLivro(id,
												Integer.parseInt(tfQuantExem.getText()));
				VolumeLivroDAO vldao = new VolumeLivroDAO();
				vldao.inserir(vl);
				JOptionPane.showMessageDialog(null, "Livro Cadastrado com Sucesso!");
				dispose();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(191)
							.addComponent(lblCadastramentoDeLivro))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnVolta, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnCadastra, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblQuantidadeDeExemplares)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(tfQuantExem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(lblTitulo)
											.addComponent(lblAutor)
											.addComponent(lblEditora)
											.addComponent(lblAno))
										.addGap(9)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addComponent(tfEditora, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
											.addComponent(tfAutor, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
											.addComponent(tfTitulo)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(tfAno, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
												.addGap(108)))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblCodigoDoLivro)))
							.addGap(20)
							.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 242, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCadastramentoDeLivro)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblImagem))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(lblCodigoDoLivro))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTitulo)
								.addComponent(tfTitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAutor)
								.addComponent(tfAutor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEditora)
								.addComponent(tfEditora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAno)
								.addComponent(tfAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblQuantidadeDeExemplares)
								.addComponent(tfQuantExem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnVolta)
								.addComponent(btnCadastra))))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
}
