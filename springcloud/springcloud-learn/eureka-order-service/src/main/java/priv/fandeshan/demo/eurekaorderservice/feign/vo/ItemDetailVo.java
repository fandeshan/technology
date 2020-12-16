package priv.fandeshan.demo.eurekaorderservice.feign.vo;

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
