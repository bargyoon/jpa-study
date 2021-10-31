package com.kh.bookmanager.book;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.kh.bookmanager.common.jpaTemplate.JpaTemplate;

public class BookService {
	
	BookRepository bookRepository = new BookRepository();
	
	
	public List<Book> findBookByTitle(String keyword) {
		EntityManager em = JpaTemplate.createEntityManager();
		List<Book> books = new ArrayList<Book>();
		try {
			books = bookRepository.findBookByTitle(em, keyword);
		} finally {
			em.close();
		}

		return books;
	}

	public List<Book> findBookWithRank() {
		EntityManager em = JpaTemplate.createEntityManager();
		List<Book> books = new ArrayList<Book>();
		try {
			books = bookRepository.findBookWithRank(em);
		} finally {
			em.close();
		}

		return books;
	}

	public List<Book> findAllBooks() {
		EntityManager em = JpaTemplate.createEntityManager();
		List<Book> books = new ArrayList<Book>();
		try {
			books = bookRepository.findAllBooks(em);
		} finally {
			em.close();
		}

		return books;
	}

	public boolean persistBook(Book book) {
		EntityManager em = JpaTemplate.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean res = false;
		tx.begin();
		try {
			em.persist(book);
			tx.commit();
			res = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		return res;
	}

	public boolean modifyBook(Book book) {
		EntityManager em = JpaTemplate.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean res = false;
		tx.begin();
		try {
			Book bookEntity = em.find(Book.class,book.getBkIdx());
			if(bookEntity == null) return false;
			bookEntity.setInfo(book.getInfo());
			tx.commit();
			res = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		return res;
	}

	public boolean removeBook(int bkIdx) {
		EntityManager em = JpaTemplate.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean res = false;
		tx.begin();
		try {
			Book book = em.find(Book.class, (long) bkIdx);
			if(book == null) return false;
			em.remove(book);
			tx.commit();
			res = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		return res;
	}
	
	
}
