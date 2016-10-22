package utd.library.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "book_loans")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "loanId")
public class BookLoans implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "loanId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int loanId;

	@Column(name = "dueDate")
	@Type(type = "date")
	private Date dueDate;

	@Column(name = "dateOut")
	@Type(type = "date")
	private Date dateOut;

	@Column(name = "dateIn")
	@Type(type = "date")
	private Date dateIn;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "isbn", nullable = false, updatable = false)
	private Book book;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cardId", nullable = false, updatable = false)
	private Borrower borrower;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "bookLoans")
	private Fines fine;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public Fines getFine() {
		return fine;
	}

	public void setFine(Fines fine) {
		this.fine = fine;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public Date getEmail() {
		return dueDate;
	}

	public void setEmail(Date email) {
		this.dueDate = email;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

}