package com.ohgiraffers.springlastteam.price;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PriceDTO {

    private String itemName;

    private String marketName;

    private String price;

}