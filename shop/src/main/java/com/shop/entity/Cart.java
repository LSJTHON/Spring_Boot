package com.shop.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cart")
@Getter
@Setter
@ToString
public class Cart {


    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO) //자동으로 번호 증가
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") //외래키 설정이라 봄
    private Member member;


}
