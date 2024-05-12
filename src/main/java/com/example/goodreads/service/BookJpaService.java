package com.example.goodreads.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.*;

import com.example.goodreads.model.Book;
import com.example.goodreads.repository.BookJpaRepository;
import com.example.goodreads.repository.BookRepository;

@Service
public class BookJpaService implements BookRepository {

	@Autowired
	private BookJpaRepository bookJpaRepository;

	@Override
	public ArrayList<Book> getBooks() {
		List<Book> booksList = bookJpaRepository.findAll();
		ArrayList<Book> books = new ArrayList<>(booksList);
		return books;
	}

	@Override
	public Book getBookById(int bookId) {
		try {
			Book book = bookJpaRepository.findById(bookId).get();
			return book;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Book addBook(Book book) {
		if (book.getName() != null && book.getImageUrl() != null) {
			bookJpaRepository.save(book);
			return book;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public Book updateBook(int bookId, Book book) {
		try {
			Book newBook = bookJpaRepository.findById(bookId).get();
			if (book.getName() != null) {
				newBook.setName(book.getName());
			}
			bookJpaRepository.save(newBook);
			return newBook;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void deleteBook(int bookId) {
		try {
			bookJpaRepository.deleteById(bookId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

}
