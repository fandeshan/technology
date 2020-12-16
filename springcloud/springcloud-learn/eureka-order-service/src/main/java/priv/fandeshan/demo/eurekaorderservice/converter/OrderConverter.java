package priv.fandeshan.demo.eurekaorderservice.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import priv.fandeshan.demo.eurekaorderservice.dto.ItemDto;
import priv.fandeshan.demo.eurekaorderservice.feign.dto.ItemStockDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderConverter {

    @Mappings({@Mapping(target = "itemId",expression = "java(Long.parseLong(itemDto.getItemId()))")})
    ItemStockDto itemDto2StockDto(ItemDto itemDto);

    List<ItemStockDto> itemDto2StockDtoList(List<ItemDto> itemDtos);

}
