import java.util.*;
import javax.faces.bean.*;
import br.ufac.si.entidades.*;
import br.ufac.si.gerentes.*;

@ManagedBean(name="livroControlador")
@SessionScoped
public class LivroControlador {

	private LivroGerente lg;
	private Livro livro;
	private String chave = "";
	
	public LivroControlador() {
		lg = new LivroGerente();
	}

	public String incluir() {
		this.livro = new Livro();
		return "livroInclusao";
	}
	
	public String editar(Livro livro) {
		this.livro = livro;
		return "livroEdicao";
	}
	
	public String excluir(Livro livro) {
		this.livro = livro;
		return "livroExclusao";
	}
	
	public String adicionar() {
		lg.incluirLivro(livro);
		return "livroGerenciamento";
	}
	
	public String atualizar() {
		lg.alterarLivro(livro);
		return "livroGerenciamento";
	}

	public String remover() {
		lg.removerLivro(livro.getId());
		return "livroGerenciamento";
	}
	
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public List<Livro> getLivros(){
		return lg.buscarTodosPorNomeContendo(chave);
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}
}
