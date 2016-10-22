package utd.library.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "book", catalog = "library")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "isbn")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "isbn")
	private String isbn;

	@Column(name = "title")
	private String title;

	@Column(name = "isAvailable")
	private boolean isAvailable;

	@Column(name = "url")
	private String url;

	@Column(name = "numberOfPages")
	private int numberOfPages;

	@Column(name = "publication")
	private String publication;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "book_authers", catalog = "library", joinColumns = {
			@JoinColumn(name = "isbn", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "authorId", nullable = false, updatable = false) })
	private Set<Authors> authers = new HashSet<Authors>(0);

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
	private Set<BookLoans> bookLoans = new HashSet<BookLoans>(0);

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public Set<BookLoans> getBookLoans() {
		return bookLoans;
	}

	public void setBookLoans(Set<BookLoans> bookLoans) {
		this.bookLoans = bookLoans;
	}

	public Set<Authors> getAuthers() {
		return authers;
	}

	public void setAuthers(Set<Authors> authers) {
		this.authers = authers;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}