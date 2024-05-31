package example.cashcard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //configured to listen for and handle HTTP requests
@RequestMapping("/cashcards") // from url starting with
class CashCardController {
	
	@GetMapping("/{requestedId}") // GET requests that match cashcards/{requestedID} will be handled by this method.
//	private ResponseEntity<String> findById() {
	private ResponseEntity<CashCard> findById(@PathVariable Long requestedId) {
//	      return ResponseEntity.ok("{}");
		if (requestedId.equals(99L)) {
	        CashCard cashCard = new CashCard(99L, 123.45);
	        return ResponseEntity.ok(cashCard);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	   }
}
