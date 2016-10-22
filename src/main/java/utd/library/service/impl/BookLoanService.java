package utd.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import utd.library.dao.IBookLoanDAO;
import utd.library.entity.BookLoans;
import utd.library.service.IBookLoanService;

@Service
@Transactional
public class BookLoanService implements IBookLoanService {

	@Autowired
	private IBookLoanDAO iBookLoanDAO;

	@Override
	public boolean add(BookLoans bookLoans) {
		return iBookLoanDAO.add(bookLoans);
	}

	@Override
	public BookLoans update(int loanId) {
		return iBookLoanDAO.update(loanId);
	}

	@Override
	public boolean payDue(int cardId) {
		return iBookLoanDAO.payDue(cardId);
	}

}
