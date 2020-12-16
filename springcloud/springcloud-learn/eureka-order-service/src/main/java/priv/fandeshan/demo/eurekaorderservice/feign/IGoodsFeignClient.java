package priv.fandeshan.demo.eurekaorderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import priv.fandeshan.demo.eurekaorderservice.feign.dto.ItemStockDto;
import priv.fandeshan.demo.eurekaorderservice.feign.vo.ItemDetailVo;
import priv.fandeshan.demo.tools.api.ResultInfo;

import java.util.List;

@FeignClient(value = "mall-goods-services")
public interface IGoodsFeignClient {
    /**
     * 锁定并扣减库存
     */
    @PutMapping(value ="/mall-goods-services/items/stock",consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultInfo decreaseStock(@RequestBody List<ItemStockDto> itemStockDtos);


    /**
     * 根据商品id查询商品列表
     * @param ids id集合
     * @return 商品列表
     */
    @GetMapping(value = "/mall-goods-services/items/{ids}")
    ResultInfo<List<ItemDetailVo>> getItemsByIds(@PathVariable("ids")List<Long> ids);
}
