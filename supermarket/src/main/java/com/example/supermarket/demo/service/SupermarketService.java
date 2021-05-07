package com.example.supermarket.demo.service;

import com.example.supermarket.demo.domain.Supermarket;
import com.example.supermarket.demo.supermarket.SupermarketResource;
import com.example.supermarket.demo.exception.BusinessException;
import com.example.supermarket.demo.repository.SupermarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class SupermarketService {


    @Autowired
    private SupermarketRepository repository;

    @Autowired
    private SupermarketResource supermarketResource;

    public void findCreditCardById() {
    }

  public Supermarket findById(Long id){
      return repository.findByIdAndDeletedIsFalse(id)
              .orElseThrow(() -> new BusinessException("Supermercado não encontrado"));
  }

    public Supermarket create(Supermarket entity){
        return repository.save(entity);
    }

    public Supermarket update(Supermarket entity){
        return repository.save(entity);
    }

    public void delete(Long id){
        Supermarket supermarket = findById(id);
        supermarket.setDeleted(true);
        repository.save(supermarket);
    }
}
