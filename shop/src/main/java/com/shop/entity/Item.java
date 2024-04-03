package com.shop.entity;


import com.shop.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;


//DB 테이블생성 : 담을 그릇을 생성성한다
@Entity
@Getter
@Table(name="item") // 테이블 명
@Setter
@ToString
public class Item {
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;                            //상품코드


    @Column(nullable = false,length = 50) //not null varchar(50)
    private String itemNm;                      //상품명

    @Column(name="price",nullable = false)
    private int price;                          //가격

    @Column(nullable = false)
    private int stockNumber;                    //재고 수량

    @Lob
    @Column(nullable = false)
    private String itemDetail;                  //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;      //상품 판매 상태

    @ManyToMany
    @JoinTable(
            name = "member_item",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Member> member;

    private LocalDateTime regTime;              //등록 시간

    private LocalDateTime updateTime;           //수정 시간
}
