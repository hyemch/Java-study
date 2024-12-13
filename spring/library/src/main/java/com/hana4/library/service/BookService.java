package com.hana4.library.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hana4.library.dao.BookDAO;
import com.hana4.library.dto.BookDTO;

@Service
public class BookService {
	private final BookDAO bookDAO;

	public BookService(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public List<BookDTO> getAllBooks() {
		return bookDAO.findAllBooks();
	}

	public BookDTO getBookById(int bno) {
		BookDTO book = bookDAO.findBookById(bno);
		if (book == null) {
			throw new RuntimeException("해당 도서를 찾을 수 없습니다.");
		}
		return book;
	}

	public BookDTO borrowBook(int bno, String borrowerId) {
		BookDTO book = bookDAO.findBookById(bno);
		if (book == null) {
			throw new RuntimeException("해당 도서가 존재하지 않습니다.");
		}
		if (book.isAvailability()) {
			book.setAvailability(false);
			book.setBorrowerId(borrowerId);
			book.setStartDate(LocalDateTime.now());
			book.setEndDate(LocalDateTime.now().plusDays(7));
			bookDAO.updateBookStatus(book);
		} else {
			throw new IllegalStateException("대출 불가 Book is not available!");
		}
		return book;
	}
}
