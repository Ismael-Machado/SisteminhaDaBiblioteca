package biblioteca.gui;

import java.util.List;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import biblioteca.dao.VolumeLivroDAO;
import biblioteca.dao.livroDAO;
import biblioteca.empty.Livro;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TabelaDinamicaLivro extends JDialog {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	protected JButton btnNovo;
	protected JButton btnExcluir;
	protected JButton btnAlterar;
	protected JButton btnVolta;
	protected JButton btnSelecionar;
	protected static String aux="";

	public static void carregaTodosDados(DefaultTableModel md) {
		md.setRowCount(0);
		livroDAO ldao = new livroDAO();
		VolumeLivroDAO vldao = new VolumeLivroDAO();
		List<Livro> lista = ldao.listar();
		for (Livro l : lista) {
			md.addRow(new Object[] {
					l.getLivroCodigo(),
					l.getAutor(),
					l.getEditora(),
					l.getTitulo(),
					l.getAno(),
					vldao.getQuantidade(l.getLivroCodigo())
			});
		}
	}
	public static void carregaDadosSelecionados(DefaultTableModel md,String titulo) {
		md.setRowCount(0);
		livroDAO ldao = new livroDAO();
		VolumeLivroDAO vldao = new VolumeLivroDAO();
		List<Livro> lista = ldao.listar(titulo);
		for (Livro l : lista) {
			md.addRow(new Object[] {
					l.getLivroCodigo(),
					l.getAutor(),
					l.getEditora(),
					l.getTitulo(),
					l.getAno(),
					vldao.getQuantidade(l.getLivroCodigo())
			});
		}
	}
	public static void carregaDadosUnico(DefaultTableModel md,int id) {
		md.setRowCount(0);
		VolumeLivroDAO vldao = new VolumeLivroDAO();
		livroDAO ldao = new livroDAO();
		Livro l =ldao.getLivro(id);
		md.addRow(new Object[] {
				l.getLivroCodigo(),
				l.getAutor(),
				l.getEditora(),
				l.getTitulo(),
				l.getAno(),
				vldao.getQuantidade(l.getLivroCodigo())
		});
	}
	/*
	 * caso o parametro do titulo do tipo String seje um '*', liste todos os dados, senao
	 * listar dados de acordo com a String.
	 * */
	public TabelaDinamicaLivro(String titulo,int id) {
		setTitle("LISTA DE LIVROS");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 950, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		model = new DefaultTableModel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnNovo = new JButton("Novo");
		btnNovo.setEnabled(false);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setEnabled(false);

		btnVolta = new JButton("Volta");
		
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				livroDAO ldao =  new livroDAO();
				CadastrarLivro cl =  new CadastrarLivro(ldao.IdMax()+1);
				carregaTodosDados(model);
			}
		});		

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linha = table.getSelectedRow();
				if(linha!=-1) {
					int valor = (int)table.getValueAt(linha, 0);
					livroDAO ldao = new livroDAO();
					ldao.deletar(valor);
					carregaTodosDados(model);
				}
			}
		});

		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linha = table.getSelectedRow();
				if(linha!=-1) {
					int valor = (int)table.getValueAt(linha, 0);
					livroDAO ldao = new livroDAO();
					AlterarLivro al = new AlterarLivro(ldao.getLivro(valor));
					dispose();
				}
			}
		});
		
		btnVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnSelecionar = new JButton("Selecionar");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nomeLivro = "";
				int linha = table.getSelectedRow();
				if(linha!=-1) 
					nomeLivro = ""+table.getValueAt(linha, 3);
				aux=nomeLivro;
				dispose();
				
			}
		});
		btnSelecionar.setEnabled(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnSelecionar, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAlterar, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVolta, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 882, Short.MAX_VALUE))
					.addGap(33))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSelecionar)
						.addComponent(btnNovo)
						.addComponent(btnExcluir)
						.addComponent(btnAlterar)
						.addComponent(btnVolta))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		
		table = new JTable(model);
		model.addColumn("Codigo do Livro");
		model.addColumn("Autor");
		model.addColumn("Editora");
		model.addColumn("Titulo");
		model.addColumn("Ano");
		model.addColumn("quantidade de Livros");
		if(titulo.toLowerCase()=="*")
			carregaTodosDados(model);
		else if(id!=-1)
			carregaDadosUnico(model, id);
		else
			carregaDadosSelecionados(model, titulo);
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		setModal(true);
	}
}
