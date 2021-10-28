package com.kh.bookmanager.book;

import java.util.List;

import javax.persistence.EntityManager;

public class BookRepository {
	public List<Book> findAllBooks(EntityManager em) {
		return em.createQuery("select b from Book b", Book.class).getResultList();
	}
	public List<Book> findBookWithRank(EntityManager em) {
		return em.createQuery("select c from Book c order by c.rentCnt desc", Book.class).setMaxResults(5).getResultList();
	}
	public List<Book> findBookByTitle(EntityManager em, String keyword) {
		// TODO Auto-generated method stub
		return em.createQuery("select b from Book b where b.title LIKE '%'||:keyword||'%'", Book.class).setParameter("keyword",keyword).getResultList();
	}
}
