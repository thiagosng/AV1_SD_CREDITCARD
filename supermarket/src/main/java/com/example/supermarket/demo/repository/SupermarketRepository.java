package com.example.supermarket.demo.repository;


import com.example.supermarket.demo.domain.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SupermarketRepository extends JpaRepository<Supermarket, Long> {

    public Optional<Supermarket> findByIdAndDeletedIsFalse(Long id);

    public Optional<Supermarket> findById(Long id);




}
