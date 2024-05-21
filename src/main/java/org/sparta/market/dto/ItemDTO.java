package org.sparta.market.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemDTO {
    private Long id;
    private String username;
    private String title;
    private String content;
    private int price;
}