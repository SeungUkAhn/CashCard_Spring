package example.cashcard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/cashcards")
public class CashCardController {
    private CashCardRepository cashCardRepository;
    public CashCardController(CashCardRepository cashCardRepository){   //생성자(별도의 생성 코드 없이도 실행 시 스프링이 처리)
        this.cashCardRepository = cashCardRepository;
    }
    @GetMapping("/{requestedId}")
    public ResponseEntity<CashCard> findById(@PathVariable Long requestedId) {
        Optional<CashCard> cashCardOptional = cashCardRepository.findById(requestedId); //Optional 객체 생성 후 ID로 CashCard 찾기
        if(cashCardOptional.isPresent()) {  //해당 ID가 존재하는 경우
            CashCard cashCard = new CashCard(requestedId, 0.0);
            return ResponseEntity.ok(cashCardOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
