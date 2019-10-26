package br.ufac.si.gerentes;

import javax.persistence.*;

import br.ufac.si.entidades.*;

public class UsuarioGerente {
	
	private EntityManagerFactory emf;
	private EntityManager em;

	public UsuarioGerente() {
		emf = Persistence.createEntityManagerFactory("Biblioteca");
		em = emf.createEntityManager();
	}
	
	//Gerenciando Usuarios

		public void incluirUsuario(Usuario u) {
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
		}

		public long removerUsuario(long id) {
			Usuario usuario = em.find(Usuario.class, id);
			long idUsu = usuario.getId();

			em.getTransaction().begin();
			em.remove(usuario);
			em.getTransaction().commit();

			return idUsu;
		}

		public void alterarUsuario(Usuario usuario) {
			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();
		}

		public Usuario buscarUsuario(long id) {
			return em.find(Usuario.class, id);
		}

		public void encerrar() {
			em.close();
			emf.close();
		}
}

