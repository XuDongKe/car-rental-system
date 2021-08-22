package com.easyrantal.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easyrantal.user.dto.Constant;
import com.easyrantal.user.dto.MsgResponse;
import com.easyrantal.user.dto.UserDTO;
import com.easyrantal.user.entity.User;
import com.easyrantal.user.mapper.UserMapper;
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
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Peter Ke
 * @Date
 */
@Slf4j
@RestController
@RequestMapping(value="/user")
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserController {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
    * @Description
    * @Author  Peter Ke
    * @Date   2021/8/21 下午8:37
    * @Param
    * @Return      
    * @Exception   
    *
    */
    @ApiOperation(value = "User login")
    @RequestMapping(value="login",method = RequestMethod.POST)
    public Object login( HttpServletResponse response,
                         @ApiParam(name="userPhone") @RequestParam(value = "userPhone",required = true) String userPhone,
                         @ApiParam(name="password")  @RequestParam(value = "password",required = true) String password){

        User user = new User();
        user.setUserPhone(userPhone);
        user.setUserPassword(password);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.setEntity(user);
        int  i=userMapper.selectCount(wrapper);
        user=userMapper.selectOne(wrapper);
        if(user!=null) {
            log.info("{} login",user.getUserName());
            //cache the user's token
            String a=UUID.randomUUID().toString();
            String token=user.getUserPhone()+a;
            redisTemplate.opsForValue().set(token,user);
            redisTemplate.expire(token,1800, TimeUnit.SECONDS);
            Cookie cookie = new Cookie("token",token);
            response.addCookie(cookie);
            log.info(" login user token: {},value: {}",token,redisTemplate.opsForValue().get(token));
            return MsgResponse.toSuccess(null,"login success");
        }
        return MsgResponse.response(null, Constant.ERROR_LOGIN_FAIL,"login fail");

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
    @ApiOperation(value = "User register")
    @RequestMapping(value="register",method = RequestMethod.POST)
    public Object register(@ApiParam(value="user login param") @Validated @RequestBody  UserDTO userDTO){
        /**
         * check if number has been registered
         */
        User user = new User();
        user.setUserPhone(userDTO.getUserPhone());
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.setEntity(user);
        int  j=userMapper.selectCount(wrapper);
        if(j>0)
            return MsgResponse.response(null, Constant.ERROR_REGISTER_FAIL,"register fail,the phone number has been registered");
        user = new User();
        BeanUtils.copyProperties(userDTO,user);
        int i=userMapper.insert(user);
        if(i>0)
            return MsgResponse.toSuccess(null,"register success");
        return MsgResponse.response(null, Constant.ERROR_REGISTER_FAIL,"register fail");

    }
}
