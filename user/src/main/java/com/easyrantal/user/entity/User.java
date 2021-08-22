package com.easyrantal.user.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author Peter Ke
 * @Date $ $
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String userPassword;

    private String userPhone;

    private String userAddress;

    private String userEmail;
}
