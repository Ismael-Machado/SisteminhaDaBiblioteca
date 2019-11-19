package br.ufac.si.controladores;

import java.util.Locale;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

@ManagedBean(name="idiomaControlador")
@SessionScoped
public class IdiomaControlador {
	private Locale localidade = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	
	private String linguagem = "pt";
	
	public Locale getLocalidade() {
		return localidade;
	}
	
	public String getLinguagem() {
		return linguagem;
	}
	
	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

	public void mudar() {
		this.localidade = new Locale(linguagem);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(localidade);
	}
}
