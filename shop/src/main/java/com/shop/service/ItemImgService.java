package com.shop.service;


import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

//상품 이미지를 업로드하고, 상품 이미지 정보를 저장하는 클래스를 서비스 패키지에 생성

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {
    //application.properties 파일에 등록한 itemimglocation 값을 불러와  itemimglocation 변수에 저장
    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;

    private final FileService fileService;


    //파일 업로드
    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception{
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //사용자가 상품의 이미지를 등록했다면 저장할 경로와 파일의 이름, 파일을 파일의 바이트 배열을 파일 업로드 파라미터로
        //uploadFile 메소드를 호출합니다. 호출 결과 로컬에 저장된 파일의 이름을 imgName 변수에 저장하빈다,
        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());

            //저장한 상품 이미지를 불러올 경로를 설정, 외부 리소스를 불러오는 urlPatterns로 WebMvcConfig 클래스에
            //shop 아래 item 폴더에 이미지를 저장하므로 상품 이미지를 불러오는 경로를 붙여준다.
            imgUrl = "/images/item/" + imgName;
        }

        //상품 이미지 정보 저장

        //imgName : 실제 로컬에 저장된 상품 이미지 파일의 이름
        //oriimgName 업로드 했던 상품 이미지 파일의 원래 이름
        //imgUrl 업로드 결과 로컬에 저장된 상품 이미지 파일을 불러오는 경로
        itemImg.updateItemImg(oriImgName,imgName,imgUrl);
        itemImgRepository.save(itemImg);
    }
}
