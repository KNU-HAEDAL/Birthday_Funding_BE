package team.haedal.gifticionfunding.global.validation;

import team.haedal.gifticionfunding.entity.gifticon.GifticonUpdate;

public class GifticonUpdateValidator {
    public static boolean validate(GifticonUpdate gifticonUpdate){
        if(gifticonUpdate.getBrand() != null && gifticonUpdate.getBrand().isBlank()){
            throw new IllegalArgumentException("브랜드는 필수 입력 값입니다.");
        }
        if(gifticonUpdate.getDescription() != null && gifticonUpdate.getDescription().isBlank()){
            throw new IllegalArgumentException("설명은 필수 입력 값입니다.");
        }
        if(gifticonUpdate.getImageUrl() != null && gifticonUpdate.getImageUrl().isBlank()){
            throw new IllegalArgumentException("이미지 URL은 필수 입력 값입니다.");
        }
        if(gifticonUpdate.getName() != null && gifticonUpdate.getName().isBlank()){
            throw new IllegalArgumentException("상품명은 필수 입력 값입니다.");
        }
        if(gifticonUpdate.getPrice() != null && gifticonUpdate.getPrice() < 1){
            throw new IllegalArgumentException("가격은 1원 이상이어야 합니다.");
        }
        if(gifticonUpdate.getStock() != null && gifticonUpdate.getStock() < 1){
            throw new IllegalArgumentException("재고는 1 이상이어야 합니다.");
        }

        return true;
    }






}

