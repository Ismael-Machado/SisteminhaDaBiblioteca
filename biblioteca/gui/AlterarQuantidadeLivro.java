package biblioteca.gui;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import biblioteca.dao.VolumeLivroDAO;
import biblioteca.dao.livroDAO;
import biblioteca.empty.VolumeLivro;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AlterarQuantidadeLivro extends JDialog {

	private JPanel contentPane;
	private JTextField tfLivro;
	private JTextField tfQuantAntiga;
	private JTextField tfQuantNova;
	private JButton btnAdd1;
	private JLabel lblQuantidade;
	private JButton btnExecutar;
	private JButton btnVolta;
	private int quantVolumes;
	private int quantLivrosDisponiveis;
	private int codigoLivro;
	List<String> titulos;

	public AlterarQuantidadeLivro(String operacao) {
		setTitle(operacao.toUpperCase()+" DE LIVROS");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100,600,205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblTitle = new JLabel(operacao.toUpperCase());
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lblLivro = new JLabel("Livro:");
		
		lblQuantidade = new JLabel("Quantidade:");

		tfLivro = new JTextField();
		tfLivro.setEditable(false);
		tfLivro.setColumns(10);

		tfQuantAntiga = new JTextField();
		tfQuantAntiga.setEditable(false);
		tfQuantAntiga.setColumns(10);

		tfQuantNova = new JTextField();
		tfQuantNova.setColumns(10);


		JButton btnPesquisarUsuario = new JButton("Pesquisar");
		btnPesquisarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PesquisarLivro pl = new PesquisarLivro(btnPesquisarUsuario.getText());
				pl.setVisible(true);
				String titulo = pl.tdl.aux;
				if(!titulo.isEmpty()) {
					VolumeLivroDAO vldao = new VolumeLivroDAO();
					livroDAO ldao = new livroDAO();
					codigoLivro = ldao.getLivro(titulo).getLivroCodigo();
					quantVolumes = vldao.getQuantidade(codigoLivro);
					tfLivro.setText(titulo);
					tfQuantAntiga.setText(quantVolumes+"");	
				}
			}
		});

		btnExecutar = new JButton("Alterar");
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VolumeLivroDAO vldao = new VolumeLivroDAO();
				if(!tfLivro.getText().isEmpty() && !tfQuantNova.getText().isEmpty()) {
					quantLivrosDisponiveis = vldao.getQuantDisponivel(codigoLivro);
					int i = Integer.parseInt(tfQuantNova.getText());
					if(!tfQuantAntiga.getText().isEmpty()) {
						if(i>quantVolumes) {
							VolumeLivro vl = new VolumeLivro(codigoLivro, i-quantVolumes);
							vldao.inserir(vl);
							JOptionPane.showMessageDialog(null, "Alterado com Sucesso!!");
						}else if(i<quantVolumes){
							if(i>=(quantVolumes-quantLivrosDisponiveis)) {
								int j = quantVolumes-i;
								while(j>0) {
									vldao.Excluir(vldao.getIdDisponivel(codigoLivro));
									j--;
								}
								JOptionPane.showMessageDialog(null, "Alterado com Sucesso!!");
							}else
								JOptionPane.showMessageDialog(null, "Impossivel Alterar a quantidade de Livros");
						}
						dispose();
					}	
				}
			}
		});

		btnVolta = new JButton("Volta");
		btnVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JLabel lblImagem = new JLabel("");
		ImageIcon icon = new ImageIcon(getClass().getResource("../img/pesquisar_livro.jpg"));
		lblImagem.setIcon(icon);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 259, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnPesquisarUsuario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblQuantidade)
										.addComponent(lblLivro))
									.addGap(12)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(tfQuantAntiga, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(tfQuantNova, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
										.addComponent(tfLivro, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnExecutar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnVolta, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(58)
							.addComponent(lblTitle)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblImagem, 0, 0, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTitle)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tfLivro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLivro))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnPesquisarUsuario, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tfQuantAntiga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfQuantNova, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQuantidade))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnExecutar)
								.addComponent(btnVolta))))
					.addGap(114))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
