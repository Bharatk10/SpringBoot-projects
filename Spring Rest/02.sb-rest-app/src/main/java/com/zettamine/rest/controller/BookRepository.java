package com.zettamine.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zettamine.rest.entity.Book;

@Repository
public class BookRepository {
	
	static List<Book> books = new ArrayList<>();
	
	public static List<Book> getAllBooks(){
		
		
		
		books.add(new Book(101,"Core Java",146.23f));
		books.add(new Book(102,"Python",129.23f));
		books.add(new Book(103,"Java full Stack",278.23f));
		
		return books;
		
	}
	
	public static void SaveBook(Book book) {
		
		books.add(book);
	}

}
