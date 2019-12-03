package br.ufac.si.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.*;

import br.ufac.si.entidades.*;
import br.ufac.si.gerentes.*;
import br.ufac.si.recursos.ExibirMensagem;

@ManagedBean(name="devolucaoControlador")
@SessionScoped
public class DevolucaoControlador {
	private DevolucaoGerente dg;
	private Devolucao devolucao = new Devolucao();
	private String chave = "";
	
	//Construtor
	public DevolucaoControlador() {
		dg = new DevolucaoGerente();
	}

	
	//Metodos Set e Get
	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}
	
	
	
	public Devolucao getDevolucao() {
		return devolucao;
	}


	public void setDevolucao(Devolucao devolucao) {
		this.devolucao = devolucao;
	}


	//Metodo que retorna Lista de Devoluções do banco
	public List<Devolucao> getDevolucoes(){
		return dg.buscarTodosPorNomeContendo(chave);
	}
	
	
}
