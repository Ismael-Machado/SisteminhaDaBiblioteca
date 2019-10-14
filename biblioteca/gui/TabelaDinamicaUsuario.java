package biblioteca.gui;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import biblioteca.dao.UsuarioDAO;
import biblioteca.empty.Usuario;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TabelaDinamicaUsuario extends JDialog {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	protected JButton btnNovo;
	protected JButton btnAlterar;
	protected JButton btnDesativar;
	protected JButton btnVolta;
	protected JButton btnSelecionar;
	protected static String aux="";

	public static void carregaTodosDados(DefaultTableModel md) {
		md.setRowCount(0);
		UsuarioDAO ud = new UsuarioDAO();
		List<Usuario> lista = ud.listar();
		for (Usuario u : lista) {
			if(!u.isStatus())
				continue;
			else {
				md.addRow(new Object[] {
						u.getUsuarioCod(),
						u.getUsuario(),
						u.getNome(),
						u.getCPF(),
						u.getEndereco(),
						u.getTelefone(),
						u.isStatus()
				});
			}
		}
	}
	public static void carregaDadosSelecionados(DefaultTableModel md, String nome) {
		md.setRowCount(0);
		UsuarioDAO ud = new UsuarioDAO();
		List<Usuario> lista = ud.listar(nome);
		for (Usuario u : lista) {
			if(!u.isStatus())
				continue;
			else {
				md.addRow(new Object[] {
						u.getUsuarioCod(),
						u.getUsuario(),
						u.getNome(),
						u.getCPF(),
						u.getEndereco(),
						u.getTelefone(),
						u.isStatus()
				});
			}
		}
	}
		
	public TabelaDinamicaUsuario(String nome) {
		setTitle("LISTA DE USUARIOS");
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
				UsuarioDAO udao = new UsuarioDAO();
				CadastroUsuario cu = new CadastroUsuario(udao.IdMax()+1);
				carregaTodosDados(model);
			}
		});
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				if(linha!=-1) {
					String usuario = ""+table.getValueAt(linha, 1);
					UsuarioDAO udao = new UsuarioDAO();
					AlterarUsuario au = new AlterarUsuario(udao.getUsuario(usuario));
				}else
					JOptionPane.showMessageDialog(null, "Usuario nao selecionado");
			}
		});
		
		btnDesativar = new JButton("Desativar");
		btnDesativar.setEnabled(false);
		btnDesativar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linha = table.getSelectedRow();
				if(linha!=-1) {
					int id = (int)table.getValueAt(linha, 0);
					if(Operacoes.deletar(id))
						carregaTodosDados(model);
				}else
					JOptionPane.showMessageDialog(null, "Usuario nao selecionado");
			}
		});
		
		btnVolta = new JButton("Volta");
		btnVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnSelecionar = new JButton("Selecionar");
		btnSelecionar.setEnabled(false);
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usuario = "";
				int linha = table.getSelectedRow();
				if(linha!=-1) 
					usuario = ""+table.getValueAt(linha, 1);
				aux=usuario;
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnSelecionar, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAlterar, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDesativar, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVolta, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
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
						.addComponent(btnAlterar)
						.addComponent(btnDesativar)
						.addComponent(btnVolta)
						.addComponent(btnSelecionar))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		
		table = new JTable(model);
		model.addColumn("Codigo do Usuario");
		model.addColumn("Usuario");
		model.addColumn("Nome");
		model.addColumn("CPF");
		model.addColumn("Endereco");
		model.addColumn("Telefone");
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
