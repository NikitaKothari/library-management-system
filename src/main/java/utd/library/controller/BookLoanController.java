package utd.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import utd.library.entity.BookLoans;
import utd.library.service.IBookLoanService;

@Controller
public class BookLoanController {

	@Autowired
	private IBookLoanService iBookLoanService;

	@RequestMapping(value = "/bookLoans", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookLoans> addBookLoans(@RequestBody BookLoans bookLoans) {
		iBookLoanService.add(bookLoans);
		return new ResponseEntity<BookLoans>(bookLoans, HttpStatus.OK);
	}

	@RequestMapping(value = "/bookLoans/{loanId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookLoans> updateBookLoan(@PathVariable("loanId") int loanId) {
		BookLoans bookLoansUpdated = iBookLoanService.update(loanId);
		return new ResponseEntity<BookLoans>(bookLoansUpdated, HttpStatus.OK);
	}

	@RequestMapping(value = "/payDue/{cardId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> payDue(@PathVariable("cardId") int cardId) {
		boolean isPayDue = iBookLoanService.payDue(cardId);
		return new ResponseEntity<String>("" + isPayDue, HttpStatus.OK);
	}

}