package utd.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import utd.library.dao.IBorrowerDAO;
import utd.library.entity.Borrower;
import utd.library.service.IBorrowerService;

@Service
@Transactional
public class BorrowerService implements IBorrowerService {

	@Autowired
	private IBorrowerDAO iBorrowerDAO;

	@Override
	public Borrower getByCardId(int cardId) {
		return iBorrowerDAO.getByCardId(cardId);
	}

	@Override
	public Borrower add(Borrower borrower) {
		return iBorrowerDAO.add(borrower);
	}

	@Override
	public boolean update(Borrower borrower) {
		return iBorrowerDAO.update(borrower);
	}

}
