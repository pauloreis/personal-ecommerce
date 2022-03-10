package br.com.personal.ecommerce.util;

import br.com.personal.ecommerce.domain.ProductPrize;

import java.time.LocalDateTime;

public class ProductPrizeCreator {
    public static ProductPrize createProductPrizeToBeSave(){
        return ProductPrize.builder()
                .prize(900.00)
                .startDate(LocalDateTime.now())
                .creatAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
    }

    public static ProductPrize createProductPrizeValid(){
        return ProductPrize.builder()
                .id(1L)
                .prize(900.00)
                .startDate(LocalDateTime.now())
                .build();
    }

    public static ProductPrize createProductPrizeInvalid(){
        return ProductPrize.builder()
                .id(1L)
                .prize(900.00)
                .startDate(LocalDateTime.now().minusDays(1L))
                .endDate(LocalDateTime.now())
                .creatAt(LocalDateTime.now().minusDays(1L))
                .updateAt(LocalDateTime.now())
                .build();
    }
}
