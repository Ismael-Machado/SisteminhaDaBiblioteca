package biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import biblioteca.dao.UsuarioDAO;
import biblioteca.dao.VolumeLivroDAO;
import biblioteca.dao.livroDAO;
import biblioteca.empty.Livro;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AlterarLivro extends JDialog {

	private JPanel contentPane;
	private JTextField tfTitulo;
	private JTextField tfAutor;
	private JTextField tfEditora;
	private JTextField tfAno;

	public AlterarLivro(Livro l) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("SISTEMA BIBLIOTECARIO");
		setBounds(100, 100, 450, 259);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCadastramentoDeLivro = new JLabel("ALTERACAO DE DADOS DO LIVRO");
		
		JLabel label = new JLabel("Codigo do Livro:");
		
		JLabel lblTitulo = new JLabel("Titulo:");
		
		JLabel lblAutor = new JLabel("Autor");
		
		JLabel lblEditora = new JLabel("Editora:");
		
		JLabel lblAno = new JLabel("Ano:");
		
		JLabel lblCodigoDoLivro = new JLabel(l.getLivroCodigo()+"");
	
		tfTitulo = new JTextField(l.getTitulo());
		tfTitulo.setColumns(10);
		
		tfAutor = new JTextField(l.getAutor());
		tfAutor.setColumns(10);
		
		tfEditora = new JTextField(l.getEditora());
		tfEditora.setColumns(10);
		
		tfAno = new JTextField(Integer.toString(l.getAno()));
		tfAno.setColumns(10);
		
		VolumeLivroDAO vld = new VolumeLivroDAO();
		
		JButton btnVolta = new JButton("Volta");
		btnVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				l.setAutor(tfAutor.getText());
				l.setEditora(tfEditora.getText());
				l.setTitulo(tfTitulo.getText());
				l.setAno(Integer.parseInt(tfAno.getText()));
				livroDAO ldao = new livroDAO();
				ldao.atualizar(l);
				dispose();
			}
		});
		
		JLabel lblImagem = new JLabel("");
		ImageIcon icon = new ImageIcon(getClass().getResource("../img/cad_livro.jpg"));
		lblImagem.setIcon(icon);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblCodigoDoLivro))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblAno)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(tfAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblAutor)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(tfAutor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblTitulo)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(tfTitulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblEditora)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(tfEditora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnVolta)
									.addGap(18)
									.addComponent(btnAlterar)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 214, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(87)
							.addComponent(lblCadastramentoDeLivro)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblCadastramentoDeLivro)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
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
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnVolta)
								.addComponent(btnAlterar)))
						.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		setModal(true);
		setVisible(true);
	}
}
