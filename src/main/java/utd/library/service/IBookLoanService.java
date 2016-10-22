package utd.library.service;

import utd.library.entity.BookLoans;

public interface IBookLoanService {

	boolean add(BookLoans bookLoans);

	BookLoans update(int loanId);

	boolean payDue(int cardId);
}
