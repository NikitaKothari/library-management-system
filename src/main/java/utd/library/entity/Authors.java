package utd.library.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "authors", catalog = "library")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "authorId")
public class Authors implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "authorId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int authorId;

	@Column(name = "name")
	private String name;

	@ManyToMany(targetEntity = Book.class, fetch = FetchType.EAGER, mappedBy = "authers")
	private Set<Book> books = new HashSet<Book>(0);

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}