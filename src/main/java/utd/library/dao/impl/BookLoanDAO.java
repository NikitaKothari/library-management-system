package utd.library.dao.impl;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import utd.library.dao.IBookLoanDAO;
import utd.library.entity.Book;
import utd.library.entity.BookLoans;
import utd.library.entity.Borrower;
import utd.library.entity.Fines;

@Repository
public class BookLoanDAO implements IBookLoanDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public BookLoans update(int loanId) {

		BookLoans bookLoans = hibernateTemplate.get(BookLoans.class, loanId);

		Borrower borrower = hibernateTemplate.get(Borrower.class, bookLoans.getBorrower().getCardId());
		bookLoans.setBorrower(borrower);
		Set<BookLoans> bookLoans2 = borrower.getBookLoans();
		bookLoans2.remove(bookLoans);

		Book book = hibernateTemplate.get(Book.class, bookLoans.getBook().getIsbn());
		bookLoans.setBook(book);
		Set<BookLoans> bookLoans3 = book.getBookLoans();
		bookLoans3.remove(bookLoans);

		bookLoans.setDateIn(new Date(new java.util.Date().getTime()));
		if ((bookLoans.getDateIn().getTime() + 24 * 60 * 60 * 1000) > bookLoans.getDueDate().getTime()) {
			Double fineAmt = (double) (bookLoans.getDateIn().getTime() - bookLoans.getDueDate().getTime());
			fineAmt = fineAmt / (24 * 60 * 60 * 1000);
			fineAmt = fineAmt * 0.25;
			Fines fines = new Fines();
			fines.setBookLoans(bookLoans);
			fines.setLoanId(bookLoans.getLoanId());
			fines.setFineAmt(fineAmt);
			fines.setPaid(false);
			hibernateTemplate.save(fines);
			bookLoans.setFine(fines);
		}

		bookLoans2.add(bookLoans);
		borrower.setBookLoans(bookLoans2);
		hibernateTemplate.update(borrower);

		bookLoans3.add(bookLoans);
		book.setBookLoans(bookLoans3);
		book.setAvailable(true);
		hibernateTemplate.update(book);
		hibernateTemplate.update(bookLoans);
		return bookLoans;
	}

	@Override
	public boolean add(BookLoans bookLoans) {

		Borrower borrower = hibernateTemplate.get(Borrower.class, bookLoans.getBorrower().getCardId());
		bookLoans.setBorrower(borrower);
		Set<BookLoans> bookLoans2 = borrower.getBookLoans();

		bookLoans2.add(bookLoans);
		borrower.setBookLoans(bookLoans2);
		hibernateTemplate.update(borrower);

		Book book = hibernateTemplate.get(Book.class, bookLoans.getBook().getIsbn());
		bookLoans.setBook(book);
		bookLoans2 = book.getBookLoans();
		bookLoans2.add(bookLoans);
		book.setBookLoans(bookLoans2);
		book.setAvailable(false);
		hibernateTemplate.update(book);

		bookLoans.setFine(new Fines());
		bookLoans.setDateIn(new Date(0));
		bookLoans.setDateOut(new Date(new java.util.Date().getTime()));
		bookLoans.setDueDate(new Date(new java.util.Date().getTime() + 14 * 24 * 60 * 60 * 1000));

		hibernateTemplate.save(bookLoans);
		return true;
	}

	@Override
	public boolean payDue(int cardId) {
		Borrower borrower = hibernateTemplate.get(Borrower.class, cardId);
		Set<BookLoans> bookLoans = borrower.getBookLoans();
		Set<BookLoans> updatedBookLoans = new HashSet<>(0);
		for (BookLoans bookLoans2 : bookLoans) {
			if (bookLoans2.getFine() != null) {
				Fines fines = bookLoans2.getFine();
				fines.setFineAmt(0);
				fines.setPaid(true);
				hibernateTemplate.update(fines);
				bookLoans2.setFine(fines);
				hibernateTemplate.update(bookLoans2);
			}
			updatedBookLoans.add(bookLoans2);
		}
		borrower.setBookLoans(updatedBookLoans);
		hibernateTemplate.update(borrower);
		return true;
	}

}
