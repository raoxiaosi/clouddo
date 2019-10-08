package com.bootdo.clouddoadmin.controller;

import com.bootdo.clouddoadmin.pojo.domain.UserDO;
import com.bootdo.clouddoadmin.pojo.vo.UserVO;
import com.bootdo.clouddoadmin.pojo.vo.do2vo.UserConvert;
import com.bootdo.clouddoadmin.service.RoleService;
import com.bootdo.clouddoadmin.service.UserService;
import com.bootdo.clouddoadmin.utils.MD5Utils;
import com.bootdo.clouddoadmin.utils.SecuityUtils;
import com.bootdo.clouddocommon.annotation.Log;
import com.bootdo.clouddocommon.context.FilterContextHandler;
import com.bootdo.clouddocommon.dto.LoginUserDTO;
import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * 系统用户信息
 * @author bootdo
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;
    @Resource
	private RoleService roleService;

	/**
	 * 登录的当前用户，前台需要验证用户登录的页面可以调用此方法
	 * @return
	 */
    @GetMapping("/currentUser")
	public LoginUserDTO currentUser(){
		LoginUserDTO loginUserDTO = new LoginUserDTO();
		loginUserDTO.setUserId(SecuityUtils.getCurrentUser().getId().toString());
		loginUserDTO.setUsername(FilterContextHandler.getUsername());
		loginUserDTO.setName(SecuityUtils.getCurrentUser().getName());
		return loginUserDTO;
	}

	/**
	 * 根据用户id获取用户
	 * @param id
	 * @return
	 */
    @GetMapping("{id}")
	public ApiResult get(@PathVariable("id") Long id ){
		UserVO userVO = UserConvert.INSTANCE.userToVo(userService.get(id));
    	return ApiResult.ok().put("data",userVO);
	}

	/**
	 * 分页查询用户
	 * @param params
	 * @return
	 */
	@Log("获取用户列表")
    @GetMapping()
	public ApiResult listByPage(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<UserVO> userVOList = UserConvert.INSTANCE.userListToVoList((userService.list(query)));
        int total = userService.count(query);
        PageUtils pageUtil = new PageUtils(userVOList, total);
        return ApiResult.ok().put("page",pageUtil);
    }

	/**
	 * 增加用户
	 * @param user
	 * @return
	 */
	@PostMapping()
	public ApiResult save(@RequestBody UserDO user) {
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		return ApiResult.operate(userService.save(user) > 0);
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@PutMapping()
	public ApiResult update(@RequestBody UserDO user) {
		return ApiResult.operate(userService.update(user) > 0);
	}


	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@DeleteMapping()
	public ApiResult remove(Long id) {
		return ApiResult.operate (userService.remove(id) > 0);
	}

	@PostMapping("/batchRemove")
	@ResponseBody
	public ApiResult batchRemove(@RequestParam("ids[]") Long[] userIds) {
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return ApiResult.ok();
		}
		return ApiResult.error();
	}

	@PostMapping("/exits")
	@ResponseBody
	public boolean exits(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.exits(params);
	}

	@GetMapping("/tokenUser")
	public Principal user(Principal user){
		return user;
	}
}
