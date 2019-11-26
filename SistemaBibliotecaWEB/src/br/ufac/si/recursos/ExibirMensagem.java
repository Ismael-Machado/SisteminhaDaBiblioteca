package br.ufac.si.recursos;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ExibirMensagem {
	public static void sucesso(String msg) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, facesMessage);
	}
	
	public static void error(String msg) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, facesMessage);
	}
	
//	public static String getParam(String nome) {
//		FacesContext facesContext = FacesContext.getCurrentInstance();
//		ExternalContext externalContext = facesContext.getExternalContext();
//		Map<String, String> parametros = externalContext.getRequestParameterMap();
//		
//		return parametros.get(nome);
//	}
}
