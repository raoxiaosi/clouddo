package com.bootdo.clouddoadmin.controller;

import com.bootdo.clouddoadmin.service.MenuService;
import com.bootdo.clouddoadmin.service.TokenService;
import com.bootdo.clouddoadmin.service.UserService;
import com.bootdo.clouddoadmin.utils.SecuityUtils;
import com.bootdo.clouddocommon.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

/**
 * @author bootdo 1992lcg@163.com
 * @version V1.0
 */
@RequestMapping()
@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    MenuService menuService;
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("/router")
    ApiResult router() {
        return ApiResult.ok().put("router", menuService.RouterDTOsByUserId(SecuityUtils.getCurrentUser().getId()));
    }


    @RequestMapping("/logout")
    ApiResult logout(String token) {
        consumerTokenServices.revokeToken(token);
        return ApiResult.ok();
    }


}
