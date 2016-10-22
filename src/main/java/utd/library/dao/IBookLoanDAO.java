package utd.library.dao;

import utd.library.entity.BookLoans;

public interface IBookLoanDAO {

	boolean add(BookLoans bookLoans);

	BookLoans update(int loanId);

	boolean payDue(int cardId);
}
