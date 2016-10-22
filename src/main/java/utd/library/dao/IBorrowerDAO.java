package utd.library.dao;

import utd.library.entity.Borrower;

public interface IBorrowerDAO {

	Borrower getByCardId(int cardId);

	Borrower add(Borrower borrower);

	boolean update(Borrower borrower);

}
