package utd.library.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;;

@Entity
@Table(name = "fines")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "loanId")
public class Fines implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "fineAmt")
	private double fineAmt;

	@Column(name = "paid")
	private boolean paid;

	@Id
	@Column(name = "loanId")
	private int loanId;

	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	public BookLoans bookLoans;

	public BookLoans getBookLoans() {
		return bookLoans;
	}

	public void setBookLoans(BookLoans bookLoans) {
		this.bookLoans = bookLoans;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public double getFineAmt() {
		return fineAmt;
	}

	public void setFineAmt(double fineAmt) {
		this.fineAmt = fineAmt;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

}