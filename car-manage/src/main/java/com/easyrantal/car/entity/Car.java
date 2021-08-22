package com.easyrantal.car.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description
 * @Author Peter Ke
 * @Date $ $
 */
@Data
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "car_id",type = IdType.AUTO)
    private int carId;

    private String carModel;

    private String carColour;

    private BigDecimal carPricePerDay;

    private String carRentStatus;
}
