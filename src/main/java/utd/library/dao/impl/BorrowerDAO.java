package utd.library.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import utd.library.dao.IBorrowerDAO;
import utd.library.entity.Borrower;

@Repository
public class BorrowerDAO implements IBorrowerDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public Borrower getByCardId(int cardId) {
		Borrower borrower = hibernateTemplate.get(Borrower.class, cardId);
		return borrower;
	}

	@Override
	public Borrower add(Borrower borrower) {
		Criteria c = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(Borrower.class);
		c.add(Restrictions.eq("ssn", borrower.getSsn()));
		List<Borrower> existingBorrower = c.list();
		if (existingBorrower == null || !existingBorrower.isEmpty()) {
			borrower.setSsn(null);
			return borrower;
		} else {
			hibernateTemplate.save(borrower);
		}
		return borrower;
	}

	@Override
	public boolean update(Borrower borrower) {
		Borrower record = getByCardId(borrower.getCardId());
		record.setBookLoans(borrower.getBookLoans());
		hibernateTemplate.update(record);
		return true;
	}

}
