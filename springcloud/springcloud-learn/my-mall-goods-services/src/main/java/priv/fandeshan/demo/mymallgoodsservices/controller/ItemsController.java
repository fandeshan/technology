package priv.fandeshan.demo.mymallgoodsservices.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import priv.fandeshan.demo.mymallgoodsservices.converter.ItemConverter;
import priv.fandeshan.demo.mymallgoodsservices.domain.ItemStockDo;
import priv.fandeshan.demo.mymallgoodsservices.dto.ItemStockDto;
import priv.fandeshan.demo.mymallgoodsservices.enums.GoodsExceptionCode;
import priv.fandeshan.demo.mymallgoodsservices.mapper.entitys.TbItem;
import priv.fandeshan.demo.mymallgoodsservices.service.IItemService;
import priv.fandeshan.demo.mymallgoodsservices.vo.ItemDetailVo;
import priv.fandeshan.demo.tools.api.ResponseResult;
import priv.fandeshan.demo.tools.api.ResultInfo;
import priv.fandeshan.demo.tools.exception.BizException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/items")
@Slf4j
@ResponseResult
public class ItemsController {

    @Autowired
    IItemService iItemService;

    @Autowired
    ItemConverter itemConverter;

    @PutMapping(value ="/stock")
    ResultInfo decreaseStock(@RequestBody List<ItemStockDto> itemStockDtos){
        log.info("begin ItemsController.decreaseStock：{}",itemStockDtos);
        List<ItemStockDo> itemStockDos = itemConverter.itemStockDtos2DoList(itemStockDtos);
        boolean rst = iItemService.decreaseStock(itemStockDos);
        return new ResultInfo.Builder<>().buildOk();
    }

    @GetMapping(value = "/{ids}")
    ResultInfo<List<ItemDetailVo>> getItemsByIds(@PathVariable("ids")List<Long> ids){
        log.info("begin ItemsController.getItemsByIds：{}",ids);
        throw new BizException(GoodsExceptionCode.ITEMS_NOT_ENOUGH,11);
//        List<TbItem> itemsList = iItemService.findItemsByIds(ids);
//        List<ItemDetailVo> itemDetailVoList = itemConverter.itemDetail2VoList(itemsList);
//        return new ResultInfo.Builder<>().setData(itemDetailVoList).buildOk();
    }
    @GetMapping(value = "/testNoResult")
    Map testNoResult(){
        log.info("begin ItemsController.testNoResult");
        Map result = new HashMap();
        result.put("t1","v1");
        result.put("t2","v2");
        return result;
    }
}
