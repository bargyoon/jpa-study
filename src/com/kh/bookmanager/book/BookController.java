package com.kh.bookmanager.book;

import java.util.List;

public class BookController {
	
	private BookService bookService =  new BookService();

	public List<Book> searchBookByTitle(String keyword) {
		return bookService.findBookByTitle(keyword);
	}

	public List<Book> searchBookWithRank() {
		return bookService.findBookWithRank();
	}

	public List<Book> searchAllBooks() {
		return bookService.findAllBooks();
		
	}

	public boolean registBook(Book book) {
		return bookService.persistBook(book);
		
	}

	public boolean modifyBook(Book book) {
		return bookService.modifyBook(book);
	}

	public boolean deleteBook(int bkIdx) {
		return bookService.removeBook(bkIdx);
		
	}
	
	
}
