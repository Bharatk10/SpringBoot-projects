package com.zettamine.boot.Repo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zettamine.boot.entity.Book;

@Repository
public class BookRepository {

	public List<Book> getAllBooks(){
		
		List<Book> books = Arrays.asList(new Book(1001,"Java",230.23f),
				new Book(1002,"Python",130.23f),
				new Book(1003,"C",200.12f));
		
		return books;
	}
}
