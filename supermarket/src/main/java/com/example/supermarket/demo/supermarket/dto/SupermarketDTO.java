package com.example.supermarket.demo.supermarket.dto;

import com.example.supermarket.demo.domain.Supermarket;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

@Data
public class SupermarketDTO implements Serializable {

    private String product;
    private String year;
    private Double value;

    public static SupermarketDTO from(Supermarket entity){
        if(Objects.isNull(entity)){
            return null;
        }
        SupermarketDTO dto = new SupermarketDTO();

        Optional.ofNullable(entity.getProduct())
                .ifPresent(dto::setProduct);

        Optional.ofNullable(entity.getYear())
                .ifPresent(dto::setYear);

        Optional.ofNullable(entity.getValue())
                .ifPresent(dto::setValue);

        return dto;
    }
}
