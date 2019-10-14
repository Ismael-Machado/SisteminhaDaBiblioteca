package biblioteca.gui;

import javax.swing.JOptionPane;

import biblioteca.dao.ItemEmprestimoDAO;
import biblioteca.dao.UsuarioDAO;
import biblioteca.dao.VolumeLivroDAO;
import biblioteca.dao.livroDAO;
import biblioteca.empty.ItemEmprestimo;

public class Operacoes {
	public static boolean deletar(int id) {
		UsuarioDAO udao = new UsuarioDAO();
		int i = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do usuario ");
		if(i==0) {
			udao.deletar(id);
			return true;
		}
		return false;
	}
	public static boolean encerrar(int id) {
		ItemEmprestimoDAO iedao = new ItemEmprestimoDAO();
		VolumeLivroDAO vldao = new VolumeLivroDAO();
		int i = JOptionPane.showConfirmDialog(null, "Confirma o encerramento do Emprestimo ");
		if(i==0) {
			ItemEmprestimo ie = iedao.getItemEmprestimo(id);
			iedao.deletar(id);
			vldao.Encerrar(ie.getIdVolume());
			JOptionPane.showMessageDialog(null, "Encerrado com Sucesso!!");
			return true;
		}
		return false;
	}
	public static boolean ativar(int id) {
		UsuarioDAO udao = new UsuarioDAO();
		int i = JOptionPane.showConfirmDialog(null, "Confirma a ativacao do usuario ");
		if(i==0) {
			udao.ativarCadastro(id);
			return true;
		}
		return false;
	}
	public static boolean ativarLivro(String titulo) {
		livroDAO ldao = new livroDAO();
		int i = JOptionPane.showConfirmDialog(null, "Confirma a ativacao do Livro");
		if(i==0) {
			ldao.ativarCadastro(titulo);
			return true;
		}
		return false;
	}
}
