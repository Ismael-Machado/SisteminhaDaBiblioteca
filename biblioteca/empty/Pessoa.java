package biblioteca.empty;

import java.util.Date;

public class Pessoa{
	private static int contador;
	private String nome;
	private String cpf;
	private int rg;
	private Endereco endereco;
	private String telefone;
	private Date dtNascimento;
	private boolean status;//informa se a pessoa está ativa ou não no Sistema
	
	//Construtor
	public Pessoa(String nome,String cpf,int rg,
			Endereco endereco,String telefone,Date dtNascimento) {
		contador++;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.endereco = endereco;
		this.telefone = telefone;
		this.dtNascimento = dtNascimento;
		this.status = true;
	}
	//Metodos Gets and Sets
	public static int getContador() {
		return contador;
	}
	public static void setContador(int contador) {
		Pessoa.contador = contador;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getRg() {
		return rg;
	}
	public void setRg(int rg) {
		this.rg = rg;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Date getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	//valida CPF
	public static boolean validaCPF(String cpf) {	
		cpf = cpf.replaceAll("[^0-9]", "");
		if(cpf.length()==11) {
			int i,j,k=9,soma=0;
			while(k<11) {
				j=k+1;
				for(i=0;i<k;i++,j--)
					soma+=Integer.parseInt(""+cpf.charAt(i))*j;
				soma=soma%11;
				if(soma<2)
					if(!(Integer.parseInt(""+cpf.charAt(k))==0))
						return false;
					else {
						if(!(Integer.parseInt(""+cpf.charAt(k))==11-soma))
							return false;					
					}
				k++;
			}
			return true;
		}else
			return false;
	}
}
