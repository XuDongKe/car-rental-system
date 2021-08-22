package com.easyrantal.order.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description
 * @Author Peter Ke
 * @Date $ $
 */
@Data
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "order_id",type = IdType.AUTO)
    private int orderId;

    private int userId;

    private String userName;

    private int carId;

    private String carModel;

    private String beginRentalTime;

    private String returnTime;

    private String createTime;

    private BigDecimal orderAmount;

    private String orderStatus;

    private String updateTime;

    private String rentalAddress;

}
