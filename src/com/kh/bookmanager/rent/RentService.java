package com.kh.bookmanager.rent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.kh.bookmanager.book.Book;
import com.kh.bookmanager.common.jpaTemplate.JpaTemplate;
import com.kh.bookmanager.member.Member;

public class RentService {

	public void returnBook(String rbIdx) {
		// TODO Auto-generated method stub
		
	}

	public boolean insertRentInfo(String userId, List<Long> bkIdxs) {
		EntityManager em = JpaTemplate.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Rent rent = new Rent();
			//대출건 제목
			List<Book> books = new ArrayList<>();
			for (Long bkIdx : bkIdxs) {
				books.add(em.find(Book.class, bkIdx));
			}
			
			String title = books.size() > 1 ? books.get(0).getTitle() + " 외 " + (books.size()-1) + "권"
					: books.get(0).getTitle();
			
			
			
			//대출자정보
			Member member = em.find(Member.class, userId);
			
			List<RentBook> rentBooks = new ArrayList<RentBook>();
			for (Book book : books) {
				RentBook rentBook = new RentBook();
				rentBook.setBook(book);
				rentBooks.add(rentBook);
				
			}
			
			rent.setMember(member);
			rent.setRentBooks(rentBooks);
			rent.setTitle(title);
			rent.setRentBookCnt(rentBooks.size());
			em.persist(rent);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		
		return false;
	}
	
	
	
	
}
