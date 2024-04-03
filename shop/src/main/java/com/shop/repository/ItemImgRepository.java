package com.shop.repository;

import com.shop.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//상품 이미지 정보를 저장하기 위해서 repository 패키지에 JpaRepository를 상속받는 인터페이스를 만듬
public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {
    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);

}
