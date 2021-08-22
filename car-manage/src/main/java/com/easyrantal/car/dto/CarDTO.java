package com.easyrantal.car.dto;


import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description
 * @Author Peter Ke
 * @Date $ $
 */
@Data
public class CarDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @ApiParam(name="carId",type="int",defaultValue="1023")
    private int carId;


    private String carModel;

    private String carColour;

    private BigDecimal carPricePerDay;

    private String carRentStatus;


}
