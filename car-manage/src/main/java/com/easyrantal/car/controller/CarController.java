package com.easyrantal.car.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.easyrantal.car.dto.CarDTO;
import com.easyrantal.car.dto.Constant;
import com.easyrantal.car.dto.MsgResponse;
import com.easyrantal.car.entity.Car;
import com.easyrantal.car.mapper.CarMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author Peter Ke
 * @Date
 */
@Slf4j
@RestController
@RequestMapping(value="/car")
@CrossOrigin(origins = "*",maxAge = 3600)
public class CarController {

    @Resource
    private CarMapper carMapper;



    /**
    * @Description
    * @Author  Peter Ke
    * @Date   2021/8/22 上午9:36
    * @Param
    * @Return      
    * @Exception   
    *
    */
    @ApiOperation(value = "query car list")
    @RequestMapping(value="car-list",method = RequestMethod.GET)
    public Object queryCarList(){

        QueryWrapper query=new QueryWrapper();
        List<Car> carList=carMapper.selectList(query);
        return MsgResponse.toSuccess(carList,"query success");

    }

    /**
     * @Description
     * @Author  Peter Ke
     * @Date   2021/8/21 下午8:37
     * @Param
     * @Return
     * @Exception
     *
     */
    @ApiOperation(value = "car update")
    @RequestMapping(value="car-update",method = RequestMethod.POST)
    public MsgResponse<Car> carUpdate(@ApiParam(value="user login param") @Validated @RequestBody CarDTO carDTO){
        /**
         * check if number has been registered
         */
        Car car=carMapper.selectById(carDTO.getCarId());

        if(car==null){
            return MsgResponse.response(null,Constant.ERROR_CAR_NOT_EXIST,"the car does not exist");
        }
        if(car.getCarRentStatus().equals(Constant.ALREADY_RENTAL)){
            return MsgResponse.response(null,Constant.ERROR_CAR_RENTAL,"the car has been rental");
        }
        UpdateWrapper<Car> updateWrapper=new UpdateWrapper<Car>();
        updateWrapper.set("car_rent_status",Constant.ALREADY_RENTAL);
        updateWrapper.eq("car_id",car.getCarId());
        carMapper.update(car,updateWrapper);
        return MsgResponse.toSuccess(car, "update car success");
    }
}
