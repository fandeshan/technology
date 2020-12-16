package priv.fandeshan.demo.eurekaorderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import priv.fandeshan.demo.eurekaorderservice.dto.OrderDto;
import priv.fandeshan.demo.eurekaorderservice.service.IOrderService;
import priv.fandeshan.demo.tools.api.ResultInfo;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping
    public ResultInfo insertOrder(@RequestBody @Validated OrderDto orderDto, BindingResult bindingResult){
        orderDto.validData(bindingResult);
        String orderId = orderService.createOrder(orderDto);
        return new ResultInfo.Builder<>().setData(orderId).buildOk();
    }

    @GetMapping
    public ResultInfo listOrders(){
        return new ResultInfo.Builder<>().buildOk();
    }
}
