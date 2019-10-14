package biblioteca.empty;

import java.util.Date;

import biblioteca.interfaces.Autenticavel;
import sun.security.util.Password;

public class Funcionario extends Pessoa implements Autenticavel {
	private int codigoFuncionario;
	private Password senha;
	
	Funcionario(String nome,String cpf,int rg,Endereco endereco,String telefone,
			Date dtNascimento,int codigoFuncionario,Password senha){
		super(nome,cpf,rg,endereco,telefone,dtNascimento);
		this.codigoFuncionario = codigoFuncionario;
		this.senha = senha;
	}
	public int getCodigoFuncionario() {
		return codigoFuncionario;
	}
	public void setCodigoFuncionario(int codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}
	public Password getSenha() {
		return senha;
	}
	public void setSenha(Password senha) {
		this.senha = senha;
	}
	public boolean Autenticar(String login,String senha) {
		if(login.equals("admin") && senha.equals("admin"))
			return true;
		return false;
	}
}
