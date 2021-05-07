package com.example.supermarket.demo.supermarket.dto;

import com.example.supermarket.demo.domain.Supermarket;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.Optional;

@Data
public class SupermarketCreateOrUpdateDTO {

    private Long id;

    @NotBlank(message = "Produto não informado")
    //@Length(min = 0, max = 16, message = "Modelo invalido")
    private String product;

    @NotBlank(message = "Ano não informado")
    //@Length(min = 0, max = 16, message = "Ano invalido")
    private String year;

    @NotBlank(message = "Marca não informada")
    //@Length(min = 0, max = 16, message = "Marca invalida")
    private String brand;

    @NotBlank(message = "Categoria não informada")
    //@Length(min = 0, max = 16, message = "Cor invalida")
    private String category;

    private Double value;

    public static Supermarket to(SupermarketCreateOrUpdateDTO dto){
        if(Objects.isNull(dto)){
            return null;
        }

        Supermarket entity = new Supermarket();

        Optional.ofNullable(dto.getProduct())
                .ifPresent(entity::setProduct);

        Optional.ofNullable(dto.getYear())
                .ifPresent(entity::setYear);

        Optional.ofNullable(dto.getBrand())
                .ifPresent(entity::setBrand);

        Optional.ofNullable(dto.getCategory())
                .ifPresent(entity::setCategory);

        Optional.ofNullable(dto.getValue())
                .ifPresent(entity::setValue);

        return entity;
    }

}
