package utd.library.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import utd.library.dao.IBookDAO;
import utd.library.entity.Authors;
import utd.library.entity.Book;

@Repository
public class BookDAO implements IBookDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public List<Book> getAllBooks() {
		String hql = "FROM Book";
		return (List<Book>) hibernateTemplate.find(hql);
	}

	@Override
	public Book getBookByIsbn(String isbn) {
		Book book = hibernateTemplate.get(Book.class, isbn);
		return book;
	}

	@Override
	public List<Book> getBookByTitle(String title) {
		Criteria c = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Book.class);
		c.add(Restrictions.like("title", title + "%", MatchMode.END));
		List<Book> books = c.list();
		return books;
	}

	@Override
	public List<Book> getBooksByAuthorName(String title) {
		Criteria c = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Book.class);
		c.createAlias("authers", "au").add(Restrictions.like("au.name", title + "%", MatchMode.END));
		List<Book> books = c.list();
		return books;
	}

	@Override
	public List<Book> getBooksByKey(String key) {
		Criteria c = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Book.class);
		c.add(Restrictions.like("title", key, MatchMode.END));
		c.createAlias("authers", "au").add(Restrictions.like("au.name", key, MatchMode.END));
		List<Book> books = c.list();
		return books;
	}

	@Override
	public Book addBook(Book book) {

		Criteria c = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Book.class);
		c.add(Restrictions.eq("isbn", book.getIsbn()));
		List<Book> existingBooks = c.list();
		if (existingBooks == null || !existingBooks.isEmpty()) {
			book.setIsbn(null);
			return book;
		} else {
			Set<Authors> authors = book.getAuthers();
			Set<Authors> updatedauthors = new HashSet<>(0);
			for (Authors authors2 : authors) {
				authors2.setAuthorId(0);
				Set<Book> books = new HashSet<>(0);
				books.add(book);
				authors2.setBooks(books);
				hibernateTemplate.save(authors2);
				updatedauthors.add(authors2);
			}
			hibernateTemplate.save(book);
		}
		return book;
	}

	@Override
	public void updateBook(Book book) {
		Book record = getBookById(book.getIsbn());
		record.setTitle(book.getTitle());
		hibernateTemplate.update(record);
	}

	private Book getBookById(String isbn) {
		return hibernateTemplate.get(Book.class, isbn);
	}

}
