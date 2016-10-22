package utd.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import utd.library.entity.Borrower;
import utd.library.service.IBorrowerService;

@Controller
public class BorrowerController {

	@Autowired
	private IBorrowerService iBorrowerService;

	@RequestMapping(value = "/borrower/getByCardId/{cardId}", method = RequestMethod.GET)
	public ResponseEntity<String> getBookByIsbn(@PathVariable("cardId") int cardId) throws JsonProcessingException {
		Borrower borrower = iBorrowerService.getByCardId(cardId);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(borrower);
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}

	@RequestMapping(value = "/borrower", method = RequestMethod.POST)
	public ResponseEntity<String> addBook(@RequestBody Borrower borrower, UriComponentsBuilder builder)
			throws JsonProcessingException {
		Borrower borrower2 = iBorrowerService.add(borrower);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(borrower2);
		return new ResponseEntity<String>(json, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/borrower", method = RequestMethod.PUT)
	public ResponseEntity<Borrower> update(@RequestBody Borrower borrower) {
		iBorrowerService.update(borrower);
		return new ResponseEntity<Borrower>(borrower, HttpStatus.OK);
	}

}