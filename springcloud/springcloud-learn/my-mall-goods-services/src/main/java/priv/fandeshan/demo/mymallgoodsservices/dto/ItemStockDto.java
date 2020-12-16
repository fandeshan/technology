package priv.fandeshan.demo.mymallgoodsservices.dto;

import lombok.Data;
import priv.fandeshan.demo.mymallgoodsservices.enums.GoodsResultCode;

import javax.validation.constraints.NotNull;

@Data
public class ItemStockDto {

    /**
     * 商品id
     */
    @NotNull( message = "{priv.fandeshan.demo.goods.valication.itemIdNotNull}")
    private Long itemId;

    /**
     * 数量
     */
    @NotNull( message = "{priv.fandeshan.demo.goods.valication.numsNotNull}")
    private Integer num;

}
