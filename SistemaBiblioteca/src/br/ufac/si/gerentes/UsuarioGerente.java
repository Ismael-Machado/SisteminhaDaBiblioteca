package br.ufac.si.gerentes;

import java.util.List;

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

		public void removerUsuario(Usuario u) {

			em.getTransaction().begin();
			em.remove(u);
			em.getTransaction().commit();

		}

		public void alterarUsuario(Usuario usuario) {
			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();
		}

		public Usuario buscarUsuario(long id) {
			return em.find(Usuario.class, id);
		}
		
		//consultas JPQL
		
		@SuppressWarnings("unchecked")
		public List<Usuario> buscarTodos(){
			return em.createNamedQuery("Usuario.todos").getResultList();	
		}
		@SuppressWarnings("unchecked")
		public List<Usuario> buscarTodosPorNome(){
			return em.createNamedQuery("Usuario.todosPorNome").getResultList();
		}
		@SuppressWarnings("unchecked")
		public List<Usuario> buscarTodosPorNomeContendo(String nome){
			return em
					.createNamedQuery("Usuario.todosPorNomeContendo")
					.setParameter("nome", "%"+nome+"%")
					.getResultList();
		}
		
		@SuppressWarnings("unchecked")
		public Usuario buscarUsuarioPorCPF(String cpf){
			try {
				return (Usuario) em
						.createNamedQuery("Usuario.contendoCPF")
						.setParameter("cpf", cpf) //"%"+termo+"%"
						.getSingleResult();
			} catch (Exception e) {
				return null;
			}
			
		}
		
//		@SuppressWarnings("unchecked")
//		public Usuario autenticar(String cpf, String senha){
//			try {
//				return (Usuario) em
//						.createNamedQuery("Usuario.autentica")
//						.setParameter("cpf", cpf)
//						.setParameter("senha", senha)
//						.getSingleResult();
//			} catch (Exception e) {
//				return null;
//			}
//			
//		}

		public void encerrar() {
			em.close();
			emf.close();
		}
}

