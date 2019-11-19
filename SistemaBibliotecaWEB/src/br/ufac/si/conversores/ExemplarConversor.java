package br.ufac.si.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufac.si.controladores.*;
import br.ufac.si.entidades.Exemplar;
import br.ufac.si.gerentes.ExemplarGerente;


@FacesConverter(value="exemplarConversor", forClass=Exemplar.class)
public class ExemplarConversor implements Converter {
	ExemplarGerente eg = new ExemplarGerente();
	
	@Override
	public Object getAsObject(FacesContext f, UIComponent comp, String value) {
		long exemplar = Integer.valueOf(value);
		if(value == null || value.isEmpty())
			return null;
		return eg.buscarExemplar(exemplar, EmprestimoControlador.livro.getId());
	}

	@Override
	public String getAsString(FacesContext f, UIComponent comp, Object value) {
		if(value == null || !(value instanceof Exemplar))
			return "";
		return String.valueOf(((Exemplar)value).getExemplar());
	}

}
