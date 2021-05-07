package com.example.supermarket.demo.supermarket;


import com.example.supermarket.demo.domain.Supermarket;
import com.example.supermarket.demo.service.SupermarketService;
import com.example.supermarket.demo.supermarket.dto.SupermarketCreateOrUpdateDTO;
import com.example.supermarket.demo.supermarket.dto.SupermarketDTO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/supermarket")
@AllArgsConstructor
public class SupermarketResource {

    @Autowired
    private final SupermarketService service;

    @RequestMapping(method = RequestMethod.GET , value = "/{id}")
    public ResponseEntity<Supermarket> findById(@PathVariable Long id){
        JsonNode limit_card;
        Double car_value;
        car_value = service.findById(id).getValue();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/creditCard/1", String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode genericJson = mapper.readTree(responseEntity.getBody());
            limit_card = genericJson.get("limit");
            System.out.println("\n>> LIMITE DISPONIVEL NO CARTÃO <<>> \n" + limit_card);
            System.out.println(">> VALOR DO PRODUTO <<>> \n" + car_value);

            if(car_value < limit_card.doubleValue()) {
                System.out.println("\n\n>> TRANSAÇÃO APROVADA! <<\n");
            } else {
                System.out.println("\n\n>> TRANSAÇÃO RECUSADA! <<\n");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(service.findById(id));

    }


    @PostMapping
    public ResponseEntity<SupermarketDTO> create(@Valid @RequestBody SupermarketCreateOrUpdateDTO dto){
        SupermarketDTO SupermarketDTO = Optional.of(dto)
                .map(SupermarketCreateOrUpdateDTO::to)
                .map(service::create)
                .map(com.example.supermarket.demo.supermarket.dto.SupermarketDTO::from).get();
        return ResponseEntity.ok().body(SupermarketDTO);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<SupermarketDTO> update(
            @Valid @RequestBody SupermarketCreateOrUpdateDTO dto,
            @PathVariable Long id){
        dto.setId(id);
        SupermarketDTO supermarketDTO = Optional.of(dto)
                .map(SupermarketCreateOrUpdateDTO::to)
                .map(service::update)
                .map(SupermarketDTO::from).get();
        return ResponseEntity.ok().body(supermarketDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<SupermarketDTO> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
