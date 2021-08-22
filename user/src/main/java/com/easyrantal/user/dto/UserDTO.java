package com.easyrantal.user.dto;


import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description
 * @Author Peter Ke
 * @Date $ $
 */
@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiParam(name="userId",type="int",defaultValue="123")
    private Integer userId;

    @NotNull
    @ApiParam(name="userName",type="String",defaultValue="peter")
    private String userName;

    @NotNull
    @ApiParam(name="userPassword",type="String",defaultValue="666666")
    private String userPassword;

    @NotNull
    @ApiParam(name="userPhone",type="String",defaultValue="18723233456")
    private String userPhone;

    @NotNull
    @ApiParam(name="userAddress",type="String",defaultValue="china shenzhen")
    private String userAddress;

    @NotNull
    @ApiParam(name="userEmail",type="String",defaultValue="23423423@qq.com")
    private String userEmail;


}
