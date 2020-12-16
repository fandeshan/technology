package priv.fandeshan.demo.eurekaorderservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.fandeshan.demo.eurekaorderservice.converter.OrderConverter;
import priv.fandeshan.demo.eurekaorderservice.dto.ItemDto;
import priv.fandeshan.demo.eurekaorderservice.dto.OrderDto;
import priv.fandeshan.demo.eurekaorderservice.feign.IGoodsFeignClient;
import priv.fandeshan.demo.eurekaorderservice.feign.vo.ItemDetailVo;
import priv.fandeshan.demo.eurekaorderservice.mapper.entitys.TbOrder;
import priv.fandeshan.demo.eurekaorderservice.mapper.entitys.TbOrderItem;
import priv.fandeshan.demo.eurekaorderservice.mapper.persistence.TbOrderItemMapper;
import priv.fandeshan.demo.eurekaorderservice.mapper.persistence.TbOrderMapper;
import priv.fandeshan.demo.eurekaorderservice.service.IOrderService;
import priv.fandeshan.demo.tools.api.ResultInfo;
import priv.fandeshan.demo.tools.enums.ResultCode;
import priv.fandeshan.demo.tools.exception.BizException;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;

    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private IGoodsFeignClient goodsFeignClient;

    @Transactional
    @Override
    public String createOrder(OrderDto orderDto) {

        /**
         * 1、锁库存
         * 2、查询商品信息
         * 3、创建订单
         */
        ResultInfo resultInfo = goodsFeignClient.decreaseStock(orderConverter.itemDto2StockDtoList(orderDto.getItems()));
        //如果返回信息不是成功，则抛出异常
        if (!ResultCode.SUCCESS.getCode().equals(resultInfo.getCode())){
            throw new BizException(resultInfo.getMessage());
        }
        List<Long> ids = orderDto.getItems().stream().map(itemDto -> Long.parseLong(itemDto.getItemId())).collect(Collectors.toList());
        ResultInfo<List<ItemDetailVo>> itemsList = goodsFeignClient.getItemsByIds(ids);
        BigDecimal totalPrice = new BigDecimal(0);
        String orderId = UUID.randomUUID().toString().replace("-","");
        for (ItemDto itemDto : orderDto.getItems()){
            for (ItemDetailVo itemDetailVo: itemsList.getData()){
                if (itemDetailVo.getId().toString().equals(itemDto.getItemId())){
                    BigDecimal singleFee = itemDetailVo.getPrice().multiply(BigDecimal.valueOf(itemDto.getNum()));
                    totalPrice = totalPrice.add(singleFee);
                    TbOrderItem tbOrderItem = new TbOrderItem();
                    tbOrderItem.setId(UUID.randomUUID().toString().replace("-",""));
                    tbOrderItem.setItemId(itemDetailVo.getId());
                    tbOrderItem.setNum(itemDetailVo.getNum());
                    tbOrderItem.setOrderId(orderId);
//                    tbOrderItem.setPicPath();
                    tbOrderItem.setStatus(1);
                    tbOrderItem.setPrice(itemDetailVo.getPrice());
                    tbOrderItem.setTitle(itemDetailVo.getTitle());
                    tbOrderItem.setTotalFee(singleFee);
                    tbOrderItemMapper.insert(tbOrderItem);
                }
            }
        }
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderId(orderId);
        tbOrder.setPayment(totalPrice);
        tbOrder.setPaymentTime(new Date());
        tbOrder.setStatus(0);
        tbOrder.setUserId(1000000l);
        tbOrderMapper.insert(tbOrder);
        return orderId;
    }
}
