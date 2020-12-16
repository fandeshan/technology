package priv.fandeshan.demo.mymallgoodsservices.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import priv.fandeshan.demo.mymallgoodsservices.domain.ItemStockDo;
import priv.fandeshan.demo.mymallgoodsservices.dto.ItemStockDto;
import priv.fandeshan.demo.mymallgoodsservices.mapper.entitys.TbItem;
import priv.fandeshan.demo.mymallgoodsservices.vo.ItemDetailVo;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemConverter {

    @Mappings({})
    ItemDetailVo tbItemDetail2Vo(TbItem tbItem);

    List<ItemDetailVo> itemDetail2VoList(List<TbItem> tbItemList);

    @Mappings({})
    ItemStockDo itemStockDto2Do(ItemStockDto itemStockDto);

    List<ItemStockDo> itemStockDtos2DoList(List<ItemStockDto> itemStockDtos);
}
