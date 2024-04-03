package com.shop.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


//DTO : 데이터 전송 객체 : List 형태의 객체를 View로 전달하는 역할
@Getter
@Setter
public class ItemDto {
    private Long id;

    private String itemNm;

    private Integer price;

    private String itemDetail;

    private String sellStatCd;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;
}
