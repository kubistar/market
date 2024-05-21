package org.sparta.market.dto;

import lombok.Getter;

@Getter
public class ItemRequestDTO {

    private String username;
    private String title;
    private String content;
    private int price;
}
