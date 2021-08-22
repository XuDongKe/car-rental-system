package com.easyrantal.order.api;

import com.easyrantal.car.dto.CarDTO;
import com.easyrantal.car.entity.Car;
import com.easyrantal.order.dto.MsgResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "car")
public interface CarApi {
    /**
    * @Description
    * @Author  Peter Ke
    * @Date   2021/8/22 下午4:36
    * @Param  CarDTO
    * @Return    Object
    * @Exception
    *
    */
    @PostMapping(value ="/car/car-update")
    MsgResponse<Car> carUpdate(@RequestBody CarDTO carDTO);
}
