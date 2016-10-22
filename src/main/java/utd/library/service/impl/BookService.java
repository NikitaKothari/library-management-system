package utd.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import utd.library.dao.IBookDAO;
import utd.library.entity.Book;
import utd.library.service.IBookService;

@Service
@Transactional
public class BookService implements IBookService {

	@Autowired
	private IBookDAO iBookDAO;

	@Override
	public List<Book> getAllBooks() {
		return iBookDAO.getAllBooks();
	}

	@Override
	public Book getBookByIsbn(String isbn) {
		return iBookDAO.getBookByIsbn(isbn);
	}

	@Override
	public List<Book> getBookByTitle(String title) {
		return iBookDAO.getBookByTitle(title);
	}

	@Override
	public Book addBook(Book book) {
		return iBookDAO.addBook(book);
	}

	@Override
	public void updateBook(Book book) {
		iBookDAO.updateBook(book);
	}

	@Override
	public List<Book> getBooksByAuthorName(String authorName) {
		return iBookDAO.getBooksByAuthorName(authorName);
	}

	@Override
	public List<Book> getBooksByKey(String key) {
		return iBookDAO.getBooksByKey(key);
	}

}
