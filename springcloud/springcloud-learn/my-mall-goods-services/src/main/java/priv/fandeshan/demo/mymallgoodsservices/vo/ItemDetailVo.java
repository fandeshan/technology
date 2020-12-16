package priv.fandeshan.demo.mymallgoodsservices.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDetailVo {

    private Long id;

    private String title;

    private BigDecimal price;

    private Integer num;

    private Long cid;

}
