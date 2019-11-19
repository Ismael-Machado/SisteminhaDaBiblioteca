package br.ufac.si.gerentes;

import java.util.List;

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

//	public void incluirExempares(Livro livro, int quant) {
//		em.getTransaction().begin();
//		for(long i = 1; i <= quant; i++) {
//			Exemplar exemplar = new Exemplar(livro, i);
//			em.persist(exemplar);
//		}
//
//		em.getTransaction().commit();
//	}

//	public void incluirExemplar(Livro livro, long exemplar) {
//		em.getTransaction().begin();
//		Exemplar ex = new Exemplar(livro, exemplar);
//		em.persist(ex);
//		em.getTransaction().commit();
//
//	}

	public void removerExemplar(Exemplar e) {
//		Exemplar e = em.find(Exemplar.class, id);
//		long exem = e.getId();

		em.getTransaction().begin();
		em.remove(e);
		em.getTransaction().commit();

//		return exem;
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
	public Exemplar buscarExemplar(long exemplar, long id_livro){
		return (Exemplar) em
				.createQuery("SELECT a FROM Exemplar a WHERE a.livro = "+id_livro+" AND a.exemplar = "+exemplar)
				.getSingleResult();
	}
}
