package priv.fandeshan.demo.mymallgoodsservices.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import priv.fandeshan.demo.mymallgoodsservices.domain.ItemStockDo;
import priv.fandeshan.demo.mymallgoodsservices.enums.GoodsExceptionCode;
import priv.fandeshan.demo.mymallgoodsservices.mapper.entitys.TbItem;
import priv.fandeshan.demo.mymallgoodsservices.mapper.entitys.TbItemExample;
import priv.fandeshan.demo.mymallgoodsservices.mapper.persistence.TbItemMapper;
import priv.fandeshan.demo.mymallgoodsservices.service.IItemService;
import priv.fandeshan.demo.tools.exception.BizException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    TbItemMapper tbItemMapper;

    @Override
    public List<TbItem> findItemsByIds(List<Long> ids) {
        TbItemExample tbItemExample = new TbItemExample();
        tbItemExample.createCriteria().andIdIn(ids);
        return tbItemMapper.selectByExample(tbItemExample);
    }

    @Transactional
    @Override
    public boolean decreaseStock(List<ItemStockDo> itemStockDos) {
        List<Long> ids = itemStockDos.stream().map(ItemStockDo::getItemId).collect(Collectors.toList());
        List<TbItem> tbItemList = tbItemMapper.selectStockForUpdate(ids);
        if (tbItemList == null || tbItemList.isEmpty()) {
            throw new BizException(GoodsExceptionCode.ITEMS_NOT_FOUND);
        }
        if (ids.size() != tbItemList.size()){
            throw new BizException(GoodsExceptionCode.SOME_ITEMS_NOT_EXIST);
        }
        itemStockDos.forEach(itemStockDo -> {
            tbItemList.forEach(item -> {
                if (Objects.equals(item.getId(),itemStockDo.getItemId())){
                    if (item.getNum() < itemStockDo.getNum()){
                        throw new BizException(GoodsExceptionCode.ITEMS_NOT_ENOUGH,itemStockDo.getItemId());
                    }
                }
                TbItem tbItem = new TbItem();
                tbItem.setId(itemStockDo.getItemId());
                tbItem.setNum(itemStockDo.getNum());
                int row = tbItemMapper.decreaseStock(tbItem);
                if (row < 1) {
                    throw new BizException(GoodsExceptionCode.ITEMS_NOT_ENOUGH,itemStockDo.getItemId());
                }
            });
        });
        return true;
    }
}
