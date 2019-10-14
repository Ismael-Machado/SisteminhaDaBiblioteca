package biblioteca.empty;

import java.util.Date;

import sun.security.util.Password;

public class Estudante extends Pessoa{
	private int numberMatricula;
	private Password senha;
	Estudante(String nome,String cpf,int rg,Endereco endereco,String telefone,
			Date dtNascimento,int nMatricula,Password senha){
		super(nome,cpf,rg,endereco,telefone,dtNascimento);
		this.numberMatricula = nMatricula;
		this.senha = senha;
	}
	/*Estudante(String nome, String cpf,String rg,String endereco,String telefone,
			Date dtNascimento,int nMatricula,Password senha){
		super(nome,cpf,rg,endereco,telefone,dtNascimento);
		this.numberMatricula = nMatricula;
		this.senha = senha;
	}*/
	public int getNumberMatricula() {
		return numberMatricula;
	}
	public void setNumberMatricula(int numberMatricula) {
		this.numberMatricula = numberMatricula;
	}
	public Password getSenha() {
		return senha;
	}
	public void setSenha(Password senha) {
		this.senha = senha;
	}
}
