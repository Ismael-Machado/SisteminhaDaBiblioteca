package biblioteca.gui;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import biblioteca.dao.ItemEmprestimoDAO;
import biblioteca.dao.UsuarioDAO;
import biblioteca.dao.VolumeLivroDAO;
import biblioteca.dao.livroDAO;
import biblioteca.empty.ItemEmprestimo;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TabelaDinamicaEmprestimo extends JDialog {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	protected JButton btnNovo;
	protected JButton btnEncerrar;
	protected JButton btnVolta;

	public static void carregaTodosDados(DefaultTableModel md) {
		md.setRowCount(0);
		UsuarioDAO udao = new UsuarioDAO();
		livroDAO ldao = new livroDAO();
		VolumeLivroDAO vldao = new VolumeLivroDAO();
		ItemEmprestimoDAO iedao = new ItemEmprestimoDAO();
		List<ItemEmprestimo> lista = iedao.listar();
		for (ItemEmprestimo ie : lista) {
				md.addRow(new Object[] {
						ie.getId(),
						udao.getUsuario(ie.getIdUsuario()).getUsuario(),
						ldao.getLivro(vldao.getExemplar(ie.getIdVolume()).getCodigoLivro()).getTitulo()
				});
		}
	}
	public static void carregaDadosSelecionados(DefaultTableModel md, String nome) {
		md.setRowCount(0);
		UsuarioDAO udao = new UsuarioDAO();
		livroDAO ldao = new livroDAO();
		VolumeLivroDAO vldao = new VolumeLivroDAO();
		ItemEmprestimoDAO iedao = new ItemEmprestimoDAO();
		List<ItemEmprestimo> lista = iedao.listar();
		for (ItemEmprestimo ie : lista) {
			if(ie.isStatus()){
				md.addRow(new Object[] {
						ie.getId(),
						udao.getUsuario(ie.getIdUsuario()).getUsuario(),
						ldao.getLivro(vldao.getExemplar(ie.getIdVolume()).getCodigoLivro()).getTitulo()
				});
			}
		}
	}
		
	public TabelaDinamicaEmprestimo(String nome,int id) {
		setTitle("LISTA DE EMPRESTIMOS");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		model = new DefaultTableModel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnNovo = new JButton("Novo");
		btnNovo.setEnabled(false);
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroEmprestimo ce = new CadastroEmprestimo();
				dispose();
				ce.setVisible(true);
				
			}
		});
		
		btnEncerrar = new JButton("Encerrar");
		btnEncerrar.setEnabled(false);
		btnEncerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linha = table.getSelectedRow();
				if(linha!=-1) {
					int id = (int)table.getValueAt(linha, 0);
					if(Operacoes.encerrar(id))
						carregaTodosDados(model);
				}else
					JOptionPane.showMessageDialog(null, "item nao selecionado");
			}
		});
		
		btnVolta = new JButton("Volta");
		btnVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEncerrar, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVolta, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE))
					.addGap(33))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNovo)
						.addComponent(btnEncerrar)
						.addComponent(btnVolta))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		
		table = new JTable(model);
		model.addColumn("Codigo Emprestimo");
		model.addColumn("Usuario");
		model.addColumn("Livro");
		switch (nome.toLowerCase()) {
		case "*":
			carregaTodosDados(model);
			break;
		default:
			carregaDadosSelecionados(model, nome);
			break;
		}
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		setModal(true);
	}
}
