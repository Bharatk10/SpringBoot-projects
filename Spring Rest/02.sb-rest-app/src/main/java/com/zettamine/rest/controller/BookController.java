package com.zettamine.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.rest.entity.Book;

@RestController
@RequestMapping("/library")
public class BookController {
	
	
	@GetMapping(path="/book/{id}",produces = {"application/json","application/xml"})
	public ResponseEntity<?> getBookDetails(@PathVariable Integer id) {
		
		
		Book book = BookRepository.getAllBooks().stream().filter(bk->bk.getBookId().equals(id)).findAny().orElse(null);
		
		if(book == null) {
			return new ResponseEntity<String>("The Book is not available",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Book>(book,HttpStatus.OK); 
		
	}
	private Object getBookId() {
		// TODO Auto-generated method stub
		return null;
	}
	@GetMapping("/books")
	public ResponseEntity<?> getAllBooks() {
		
		List<Book> books = BookRepository.getAllBooks();
		
		return new ResponseEntity<List<Book>>(books,HttpStatus.OK); 
		
	}
	@PostMapping(path = "/addbook")
	public ResponseEntity<String> saveBook(@RequestBody Book book){
		
		if(book.getBookId() == null || book.getBookName() == null || book.getBookPrice() == null)
			return new  ResponseEntity<String>("In Book some fields are null",HttpStatus.BAD_REQUEST);
		
		BookRepository.SaveBook(book);
		return new ResponseEntity<String>("Book added successflly",HttpStatus.OK);
		
	}

}
