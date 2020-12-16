package priv.fandeshan.demo.mymallgoodsservices.service;

import priv.fandeshan.demo.mymallgoodsservices.domain.ItemStockDo;
import priv.fandeshan.demo.mymallgoodsservices.dto.ItemStockDto;
import priv.fandeshan.demo.mymallgoodsservices.mapper.entitys.TbItem;

import java.util.List;

public interface IItemService {

    List<TbItem> findItemsByIds(List<Long> ids);

    boolean decreaseStock(List<ItemStockDo> itemStockDos);
}
