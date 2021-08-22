package com.easyrantal.order.controller;


import com.easyrantal.car.dto.CarDTO;
import com.easyrantal.car.entity.Car;
import com.easyrantal.order.api.CarApi;
import com.easyrantal.order.dto.Constant;
import com.easyrantal.order.dto.MsgResponse;
import com.easyrantal.order.dto.OrderDTO;
import com.easyrantal.order.entity.Order;
import com.easyrantal.order.mapper.OrderMapper;
import com.easyrantal.user.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * @Description
 * @Author Peter Ke
 * @Date
 */
@Slf4j
@RestController
@RequestMapping(value="/order")
@CrossOrigin(origins = "*",maxAge = 3600)
public class OrderController {

    @Resource
    private OrderMapper orderMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    CarApi carApi;

    /**
    * @Description
    * @Author  Peter Ke
    * @Date   2021/8/22 上午9:36
    * @Param
    * @Return      
    * @Exception   
    *
    */
    @ApiOperation(value = "rental car")
    @RequestMapping(value="rental-car",method = RequestMethod.POST)
    public Object queryCarList(@ApiParam @Validated @RequestBody OrderDTO orderDTO, HttpServletRequest request){

        User user=new User();
        Cookie[] cookies=request.getCookies();
        if (cookies==null||cookies.length==0){
            return MsgResponse.response(null, Constant.ERROR_NOT_LOGIN,"please login first");
        }
        for(Cookie c:cookies){
            if(c.getName().equals("token")){
                user= (User) redisTemplate.opsForValue().get(c.getValue());
                break;
            }
        }
        if(user==null){
           return MsgResponse.response(null, Constant.ERROR_NOT_LOGIN,"please login first");
        }
        CarDTO carDto=new CarDTO();
        carDto.setCarId(orderDTO.getCarId());
        MsgResponse<Car> resp=carApi.carUpdate(carDto);
        if(!Constant.SUCCESS_CODE.equals(resp.getErrorCode())){
            return resp;
        }
        Order order =new Order();
        BeanUtils.copyProperties(order,orderDTO);
        order.setUserId(user.getUserId());
        order.setUserName(user.getUserName());
        order.setOrderStatus(Constant.ALREADY_IN_FORCE);
        int orderId=Integer.getInteger(UUID.randomUUID().toString());
        order.setOrderId(orderId);
//        UpdateWrapper updateWrapper=new UpdateWrapper();
//        updateWrapper.setEntity(order);
        orderMapper.insert(order);

        //List<Car> carList=carMapper.selectList(query);
        return MsgResponse.toSuccess("","rental car success");

    }


}
