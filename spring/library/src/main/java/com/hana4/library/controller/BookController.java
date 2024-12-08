package com.hana4.library.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hana4.library.dto.BookDTO;
import com.hana4.library.service.BookService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/book")
public class BookController {
	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/list")
	public String getBooks(Model model) {
		List<BookDTO> books = bookService.getAllBooks();
		model.addAttribute("books", books);
		return "book/list";
	}

	@GetMapping("/read")
	public String readBook(@RequestParam("bno") int bno, Model model) {
		BookDTO book = bookService.getBookById(bno);
		model.addAttribute("book", book);
		return "book/read";
	}

	@PostMapping("/borrow")
	public String borrowBook(@RequestParam int bno, @RequestParam String borrowerId,
		Model model) {
		BookDTO book = bookService.borrowBook(bno, borrowerId);
		model.addAttribute("book", book);
		return "book/result";
	}

	@GetMapping("/result")
	public String resultBook(@RequestParam("bno") int bno, Model model) {
		BookDTO book = bookService.getBookById(bno);
		model.addAttribute("book", book);
		return "book/result";
	}

	@GetMapping("/changeLanguage")
	public String changeLanguage(@RequestParam("lang") String lang, HttpSession session) {
		Locale locale = new Locale(lang);
		session.setAttribute("locale", locale);
		return "redirect:/book/list";
	}

	// @GetMapping("/changeLanguage")
	// public String changeLanguage(@RequestParam("lang") String lang, HttpSession session, HttpServletRequest request) {
	// 	Locale locale = new Locale(lang);
	// 	session.setAttribute("locale", locale);
	// 	String referer = request.getHeader("referer");
	// 	return "redirect:" + referer;
	// }
}
