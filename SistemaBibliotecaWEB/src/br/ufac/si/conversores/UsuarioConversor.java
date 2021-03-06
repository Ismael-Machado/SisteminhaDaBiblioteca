package br.ufac.si.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufac.si.entidades.Usuario;
import br.ufac.si.gerentes.UsuarioGerente;
import br.ufac.si.recursos.ExibirMensagem;


@FacesConverter(value="usuarioConversor", forClass=Usuario.class)
public class UsuarioConversor implements Converter {
	UsuarioGerente ug = new UsuarioGerente();
	
	@Override
	public Object getAsObject(FacesContext f, UIComponent comp, String value) {
		if(value == null || value.isEmpty()) {
			return null;
		}
		
		if(ug.buscarUsuarioPorCPF(value) != null)
			return ug.buscarUsuarioPorCPF(value);
		return null;
					
	}

	@Override
	public String getAsString(FacesContext f, UIComponent comp, Object value) {
		if(value == null || !(value instanceof Usuario))
			return "";
		return String.valueOf(((Usuario)value).getCPF());
	}

}
