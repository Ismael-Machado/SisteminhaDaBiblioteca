package biblioteca.empty;
public class Usuario {
	
	private int usuarioCod;
	private String usuario;
	private String nome;
	private String CPF;
	private String endereco;
	private String telefone;
	private boolean status;
	
	//Construtor
	public Usuario(String user,String nome, String cpf, String endereco,String telefone) {
		this.usuario = user;
		this.nome = nome;
		this.CPF = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
		this.status = true;
	}
	public Usuario(int usuarioCod, String user,String nome, String cpf, String endereco,String telefone,boolean status) {
		this(user,nome,cpf,endereco,telefone);
		this.usuarioCod = usuarioCod;
		this.status = status;
	}
	//Metodos Gets and Sets
	public int getUsuarioCod() {
		return usuarioCod;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public void setUsuarioCod(int usuarioCod) {
		this.usuarioCod = usuarioCod;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
