package br.ufac.si.gerentes;

import javax.persistence.*;

import br.ufac.si.entidades.Livro;

public class LivroGerente {

	private EntityManagerFactory emf;
	private EntityManager em;

	public LivroGerente() {
		emf = Persistence.createEntityManagerFactory("Biblioteca");
		em = emf.createEntityManager();
	}

	//Gerenciando Livros

	public void incluirLivro(Livro livro) {
		em.getTransaction().begin();
		em.persist(livro);
		em.getTransaction().commit();
	}

	public long removerLivro(long id) {
		Livro livro = em.find(Livro.class, id);
		long idLivro = livro.getId();

		em.getTransaction().begin();
		em.remove(livro);
		em.getTransaction().commit();

		return idLivro;
	}

	public void alterarLivro(Livro livro) {
		em.getTransaction().begin();
		em.merge(livro);
		em.getTransaction().commit();
	}

	public Livro buscarLivro(long id) {
		return em.find(Livro.class, id);
	}

	public void encerrar() {
		em.clear();
		emf.close();
	}
}
