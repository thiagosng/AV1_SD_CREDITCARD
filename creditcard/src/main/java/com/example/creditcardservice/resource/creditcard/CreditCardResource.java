package com.example.creditcardservice.resource.creditcard;

import com.example.creditcardservice.resource.creditcard.dto.CreditCardCreateOrUpdateDTO;
import com.example.creditcardservice.resource.creditcard.dto.CreditCardDTO;
import com.example.creditcardservice.resource.creditcard.dto.CreditCardRequestTokenDTO;
import com.example.creditcardservice.resource.creditcard.dto.CreditCardTokenDTO;
import com.example.creditcardservice.service.CreditCardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/creditCard")
@AllArgsConstructor
public class CreditCardResource {

    private CreditCardService service;

    @PostMapping
    public ResponseEntity<CreditCardDTO> create(@Valid @RequestBody CreditCardCreateOrUpdateDTO dto){
        CreditCardDTO creditCardDTO = Optional.of(dto)
                .map(CreditCardCreateOrUpdateDTO::to)
                .map(service::create)
                .map(CreditCardDTO::from).get();
        return ResponseEntity.ok().body(creditCardDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CreditCardDTO> get(@PathVariable Long id) {
        CreditCardDTO dto = Optional.of(service.findById(id))
                .map(creditCard -> CreditCardDTO.from(creditCard)).get();
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CreditCardDTO> update(
            @Valid @RequestBody CreditCardCreateOrUpdateDTO dto,
            @PathVariable Long id){
        dto.setId(id);
        CreditCardDTO creditCardDTO = Optional.of(dto)
                .map(CreditCardCreateOrUpdateDTO::to)
                .map(service::update)
                .map(CreditCardDTO::from).get();
        return ResponseEntity.ok().body(creditCardDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CreditCardDTO> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/requestToken")
    public ResponseEntity<CreditCardTokenDTO> requestToken(
            @Valid @RequestBody CreditCardRequestTokenDTO dto){
        CreditCardTokenDTO creditCardTokenDTO = Optional.of(dto)
                .map(CreditCardRequestTokenDTO::to)
                .map(service::requestToken)
                .map(CreditCardTokenDTO::from).get();
        return ResponseEntity.ok().body(creditCardTokenDTO);
    }

}
