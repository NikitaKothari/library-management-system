package utd.library.service;

import utd.library.entity.Borrower;

public interface IBorrowerService {
	Borrower getByCardId(int cardId);

	Borrower add(Borrower borrower);

	boolean update(Borrower borrower);

}
