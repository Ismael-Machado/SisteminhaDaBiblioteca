package br.ufac.si.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufac.si.entidades.Livro;
import br.ufac.si.gerentes.LivroGerente;


@FacesConverter(value="livroConversor", forClass=Livro.class)
public class LivroConversor implements Converter {
	LivroGerente lg = new LivroGerente();
	
	@Override
	public Object getAsObject(FacesContext f, UIComponent comp, String value) {
		if(value == null || value.isEmpty())
			return null;
		return lg.buscarLivro(Integer.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext f, UIComponent comp, Object value) {
		if(value == null || !(value instanceof Livro))
			return "";
		return String.valueOf(((Livro)value).getId());
	}

}
