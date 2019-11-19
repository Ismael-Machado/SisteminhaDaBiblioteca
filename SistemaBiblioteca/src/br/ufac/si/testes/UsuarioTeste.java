package br.ufac.si.testes;

import br.ufac.si.entidades.Usuario;
import br.ufac.si.gerentes.UsuarioGerente;

public class UsuarioTeste {

	public static void main(String [] args) {
//		Adicionar Usuario
		UsuarioGerente usuG = new UsuarioGerente();
		Usuario usuario = new Usuario();
		usuario.setNome("Roniel Martins");
		usuario.setEmail("roniefx@gmail.com");
		usuario.setFone("68984052003");
		usuario.setSexo("masculino");
//		usuario.setLogin("ronielmartins54");
		usuario.setSenha("123456");
		usuG.incluirUsuario(usuario);
		
		usuG.encerrar();
		
	}
}
