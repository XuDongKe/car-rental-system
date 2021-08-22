package com.easyrantal.order.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class OrderDTO implements  Serializable {
    private static final long serialVersionUID = 1L;

    private int orderId;

    private int userId;

    private String userName;

    @NotNull
    private int carId;

    @NotNull
    private String carModel;

    @NotNull
    private String beginRentalTime;

    @NotNull
    private String returnTime;

    private String createTime;

    private BigDecimal orderAmount;

    private String orderStatus;

    private String updateTime;

    private String rentalAddress;

}
