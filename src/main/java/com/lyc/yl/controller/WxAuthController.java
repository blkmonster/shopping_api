package com.lyc.yl.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.lyc.yl.annotation.LoginUser;
import com.lyc.yl.entity.User;
import com.lyc.yl.entity.UserInfo;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.UserService;
import com.lyc.yl.util.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lyc.yl.result.WxResponseCode.*;


/**
 * 微信用户表控制层
 */
@RestController
@RequestMapping("/wx/auth")
@Validated
public class WxAuthController {
    @Resource
    private UserService userService;
    @Resource
    private WxMaService wxService;


    /**
     * 账号登录
     *
     * @param body    请求内容，{ username: xxx, password: xxx }
     * @param request 请求对象
     * @return 登录结果
     */
    @PostMapping("login")
    public Object login(@RequestBody String body, HttpServletRequest request) {
        String username = JacksonUtil.parseString(body, "username");
        String password = JacksonUtil.parseString(body, "password");
        if (username == null || password == null) {
            return ResponseUtil.badArgument();
        }

        List<User> userList = userService.queryByUsername(username);
        User user = null;
        if (userList.size() > 1) {
            return ResponseUtil.serious();
        } else if (userList.size() == 0) {
            return ResponseUtil.fail(AUTH_INVALID_ACCOUNT, "账号不存在");
        } else {
            user = userList.get(0);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, user.getPassword())) {
            return ResponseUtil.fail(AUTH_INVALID_ACCOUNT, "账号密码不对");
        }

        // 更新登录情况
        user.setLastLoginTime(LocalDateTime.now());
        user.setLastLoginIp(IpUtil.getIpAddr(request));
        if (userService.updateById(user) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        // userInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(username);
        userInfo.setAvatarUrl(user.getAvatar());

        // token
        String token = UserTokenManager.generateToken(user.getId());

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", token);
        result.put("userInfo", userInfo);
        return ResponseUtil.ok(result);
    }

    /**
     * 登录状态
     * @param userName
     * @return
     */
    @PostMapping("login_state")
    public Object loginState(@RequestBody String userName){
        String username = JacksonUtil.parseString(userName, "username");
        int i = userService.loginState(username);
        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("state",i);
        return ResponseUtil.ok(result);
    }


    /**
     * 微信登录
     *
     * @param userInfo 请求内容，{ code: xxx, userInfo: xxx }
     * @param request     请求对象
     * @return 登录结果
     */
    @PostMapping("login_by_weixin")
    public Object loginByWeixin(@RequestBody UserInfo userInfo, HttpServletRequest request) {
        String code = userInfo.getCode();
        if (code == null || (userInfo.getAvatarUrl() == null && userInfo.getNickName() == null)) {
            return ResponseUtil.badArgument();
        }

        String sessionKey = null;
        String openId = null;
        try {
            WxMaJscode2SessionResult result = this.wxService.getUserService().getSessionInfo(code);
            sessionKey = result.getSessionKey();
            openId = result.getOpenid();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (sessionKey == null || openId == null) {
            return ResponseUtil.fail();
        }

        User user = userService.queryByOid(openId);
        if (user == null) {
            user = new User();
            user.setUsername(openId);
            user.setPassword(openId);
            user.setWeixinOpenid(openId);
            user.setAvatar(userInfo.getAvatarUrl());
            user.setNickname(userInfo.getNickName());
            user.setUserLevel((byte) 0);
            user.setStatus((byte) 0);
            user.setLastLoginTime(LocalDateTime.now());
            user.setLastLoginIp(IpUtil.getIpAddr(request));
            user.setSessionKey(sessionKey);

            userService.add(user);

        } else {
            user.setAvatar(userInfo.getAvatarUrl());
            user.setNickname(userInfo.getNickName());
            user.setLastLoginTime(LocalDateTime.now());
            user.setLastLoginIp(IpUtil.getIpAddr(request));
            user.setSessionKey(sessionKey);
            if (userService.updateById(user) == 0) {
                return ResponseUtil.updatedDataFailed();
            }
        }


        // token
        String token = UserTokenManager.generateToken(user.getId());

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", token);
        result.put("userInfo", userInfo);
        // 设置用户ID
        result.put("userId",user.getId());
        return ResponseUtil.ok(result);
    }




    /**
     * 账号密码重置
     *
     * @param body    请求内容
     *                {
     *                password: xxx,
     *                mobile: xxx
     *                code: xxx
     *                }
     *                其中code是手机验证码，目前还不支持手机短信验证码
     * @param request 请求对象
     * @return 登录结果
     * 成功则 { errno: 0, errmsg: '成功' }
     * 失败则 { errno: XXX, errmsg: XXX }
     */
    @PostMapping("reset")
    public Object reset(@RequestBody String body, HttpServletRequest request) {
        String password = JacksonUtil.parseString(body, "password");
        String mobile = JacksonUtil.parseString(body, "mobile");
        String code = JacksonUtil.parseString(body, "code");

        if (mobile == null || code == null || password == null) {
            return ResponseUtil.badArgument();
        }

        //判断验证码是否正确
//        String cacheCode = CaptchaCodeManager.getCachedCaptcha(mobile);
//        if (cacheCode == null || cacheCode.isEmpty() || !cacheCode.equals(code))
//            return ResponseUtil.fail(AUTH_CAPTCHA_UNMATCH, "验证码错误");

        List<User> userList = userService.queryByMobile(mobile);
        User user = null;
        if (userList.size() > 1) {
            return ResponseUtil.serious();
        } else if (userList.size() == 0) {
            return ResponseUtil.fail(AUTH_MOBILE_UNREGISTERED, "手机号未注册");
        } else {
            user = userList.get(0);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);

        if (userService.updateById(user) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        return ResponseUtil.ok();
    }



    /**
     * 修改密码
     *
     * @param userId
     * @param body
     * @return
     */
    @PostMapping("resertPassword")
    public Object resertPassword(@LoginUser Integer userId, @RequestBody String body) {
        String password = JacksonUtil.parseString(body, "password");
        if(userId == null || StringUtils.isBlank(password)){
            return ResponseUtil.badArgument();
        }
        User user = new User();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);
        user.setId(userId);

        if (userService.updateById(user) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok();
    }


    @PostMapping("logout")
    public Object logout(@LoginUser  Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        return ResponseUtil.ok();
    }

    @GetMapping("info")
    public Object info(Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        User user = userService.findById(userId);
        // 根据用户电话号码取得avarto数据
        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("nickName", user.getNickname());
        data.put("avatar", user.getAvatar());
        data.put("gender", user.getGender());
        data.put("mobile", user.getMobile());

        return ResponseUtil.ok(data);
    }

    /**
     * 查询用户等级
     *
     * @param userId
     * @return
     */
    @GetMapping("level")
    public Object level(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        User user = userService.findById(userId);
        return ResponseUtil.ok(user.getUserLevel());
    }

}
