package priv.fandeshan.demo.eurekaorderservice.feign.dto;

import lombok.Data;

@Data
public class ItemStockDto {

    /**
     * 商品id
     */
    private Long itemId;

    /**
     * 数量
     */
    private Integer num;

}
