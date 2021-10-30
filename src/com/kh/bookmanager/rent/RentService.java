package com.kh.bookmanager.rent;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.kh.bookmanager.book.Book;
import com.kh.bookmanager.common.jpaTemplate.JpaTemplate;
import com.kh.bookmanager.member.Member;

public class RentService {
	
	private RentRepository rentRepository = new RentRepository();
	
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
				rentBook.setState("대출");
				rentBooks.add(rentBook);
				
			}
			
			rent.setMember(member);
			rent.changeRentBooks(rentBooks);
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

	public List<Rent> findRentByUserId(String userId) {
		
		EntityManager em = JpaTemplate.createEntityManager();
		List<Rent> rents = new ArrayList<Rent>();
		try {
			rents = rentRepository.findRentByUserId(em,userId);
		} finally {
			em.close();
		}
		
		return rents;
	}
	
	public Boolean returnBook(Long rmIdx) {
		EntityManager em = JpaTemplate.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			RentBook rentBook = em.find(RentBook.class, rmIdx);
			rentBook.setState("반납");
			rentBook.setReturnDate(LocalDateTime.now());
			if(rentBook.getRent().getRentBooks().stream().allMatch(e -> e.getState().equals("반납"))) {
				rentBook.getRent().setIsReturn(true);
			}
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

	public Boolean extensionRentBook(Long rbIdx) {
		EntityManager em = JpaTemplate.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			RentBook rentBook = em.find(RentBook.class, rbIdx);
			if(rentBook.getState().equals("반납")) {
				return false;
			}
			rentBook.setState("연장");
			rentBook.setExtensionCnt(rentBook.getExtensionCnt()+1);
			rentBook.setReturnDate(rentBook.getReturnDate().plusDays(7));
			rentBook.getReturnDate();
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
