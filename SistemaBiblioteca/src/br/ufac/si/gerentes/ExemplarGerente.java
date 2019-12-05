package br.ufac.si.gerentes;

import javax.persistence.*;
import br.ufac.si.entidades.*;

public class ExemplarGerente {
	private EntityManagerFactory emf;
	private EntityManager em;

	public ExemplarGerente() {
		emf = Persistence.createEntityManagerFactory("Biblioteca");
		em = emf.createEntityManager();
	}

	//Gerenciando Exemplares

	public void incluirExempares(Livro livro, int quant) {
		
		long ex = livro.getQuantidade();
		for(long i = 1; i <= quant; i++) {
			ex++;	
			Exemplar exemplar = new Exemplar(livro, ex, 1); //1 = exemplar disponivel
			em.getTransaction().begin();
			em.persist(exemplar);
			em.getTransaction().commit();
		}
		livro.setQuantidade(livro.getQuantidade()+quant);
		em.getTransaction().begin();
		em.merge(livro);
		em.getTransaction().commit();
		
	}

	public void removerExemplar(Livro livro, Exemplar e) {
		em.getTransaction().begin();
		em.remove(e);
		em.getTransaction().commit();
		
		livro.setQuantidade(livro.getQuantidade()-1);
		em.getTransaction().begin();
		em.merge(livro);
		em.getTransaction().commit();

	}

	public void alterarExemplar(Exemplar exemplar) {
		em.getTransaction().begin();
		em.merge(exemplar);
		em.getTransaction().commit();
	}

	public Exemplar buscarExemplar(long id) {
		return em.find(Exemplar.class, id);
	}


	public void encerrar() {
		em.clear();
		emf.close();
	}
	
	//Consultas JPQL
	
	@SuppressWarnings("unchecked")
	public Exemplar buscarExemplar(long livro_id, long exemplar){
			return (Exemplar) em
					.createQuery("SELECT a FROM Exemplar a WHERE a.livro = "+livro_id+" AND a.exemplar = "+exemplar)
					.getSingleResult();
		
		
		
	}
}
