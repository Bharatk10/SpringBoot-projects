package com.zettamine.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zettamine.boot.Repo.BookRepository;
import com.zettamine.boot.entity.Book;

@Controller
@RequestMapping("/zettalibrary")
public class BookController {
	
	@Autowired
	BookRepository bookRepo;
	
	@GetMapping("/books")
	public String getAllBooks(Model model) {
		
		List<Book> books = bookRepo.getAllBooks();
		
		model.addAttribute("books", books);
		return "books";
		
	}
	@GetMapping("/view")
	public String getBookById(Model model) {
		
		List<Book> books = bookRepo.getAllBooks();
		
		model.addAttribute("books", books);
		return "books";
		
	}

}
