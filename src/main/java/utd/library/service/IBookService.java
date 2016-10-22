package utd.library.service;

import java.util.List;

import utd.library.entity.Book;

public interface IBookService {
	List<Book> getAllBooks();

	Book getBookByIsbn(String isbn);

	List<Book> getBookByTitle(String title);

	List<Book> getBooksByAuthorName(String authorName);

	List<Book> getBooksByKey(String key);

	Book addBook(Book book);

	void updateBook(Book book);
}
