package com.lyc.yl.controller;

import com.lyc.yl.entity.Address;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.AddressService;
import com.lyc.yl.util.JacksonUtil;
import com.lyc.yl.util.RegexUtil;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户收货地址服务
 */
@RestController
@RequestMapping("/wx/address")
@Validated
public class WxAddressController {
	@Resource
	private AddressService addressService;


	/**
	 * 用户收货地址列表
	 *
	 * @param userId 用户ID
	 * @return 收货地址列表
	 */
	@GetMapping("list")
	public Object list(Integer userId) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		Address address = new Address();
		address.setUserId(userId);
		List<Address> addressList = addressService.queryList(address);
		return ResponseUtil.okList(addressList);
	}

	/**
	 * 收货地址详情
	 *
	 * @param address
	 * @return 收货地址详情
	 */
	@GetMapping("detail")
	public Object detail(Address address) {
		return ResponseUtil.ok(addressService.query(address));
	}

	//验证地址信息
	private Object validate(Address address) {
		String name = address.getName();
		if (StringUtils.isEmpty(name)) {
			return ResponseUtil.badArgument();
		}

		Integer sex = address.getSex();
		if (sex == null) {
			return ResponseUtil.badArgument();
		}

		// 测试收货手机号码是否正确
		String mobile = address.getTel();
		if (StringUtils.isEmpty(mobile)) {
			return ResponseUtil.badArgument();
		}
		if (!RegexUtil.isMobileSimple(mobile)) {
			return ResponseUtil.badArgument();
		}

		String addressDetail = address.getAddressDetail();
		if (StringUtils.isEmpty(addressDetail)) {
			return ResponseUtil.badArgument();
		}

		String areaCode = address.getAreaCode();
		if (StringUtils.isEmpty(areaCode)) {
			return ResponseUtil.badArgument();
		}

		Integer isDefault = address.getIsDefault();
		if (isDefault == null) {
			return ResponseUtil.badArgument();
		}
		return null;
	}

	/**
	 * 添加或更新收货地址
	 *
	 * @param address 用户收货地址
	 * @return 添加或更新操作结果
	 */
	@PostMapping("save")
	public Object save(@RequestBody Address address) {
		Integer userId = address.getUserId();
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		Object error = validate(address);
		if (error != null) {
			return error;
		}

		//添加
		if (address.getId() == null || address.getId().equals(0)) {
			if (address.getIsDefault() == 1) {
				// 重置其他收货地址的默认选项
				addressService.resetDefault(userId);
			}

			address.setId(null);
			address.setUserId(userId);
			addressService.add(address);
		} else {//修改
			Address Address = addressService.query(address);
			if (Address == null) {
				return ResponseUtil.badArgumentValue();
			}

			if (address.getIsDefault() == 1) {
				// 重置其他收货地址的默认选项
				addressService.resetDefault(userId);
			}

			address.setUserId(userId);
			addressService.update(address);
		}
		return ResponseUtil.ok(address.getId());
	}

	/**
	 * 删除收货地址
	 *
	 * @param body 收货地址id，{ id: xxx }
	 * @return 删除操作结果
	 */
	@PostMapping("delete")
	public Object delete(@RequestBody String body) {
		Integer id = JacksonUtil.parseInteger(body, "id");
		if (id == null) {
			return ResponseUtil.badArgument();
		}
		Address address = new Address();
		address.setId(id);
		Address Address = addressService.query(address);
		if (Address == null) {
			return ResponseUtil.badArgumentValue();
		}

		addressService.delete(id);
		return ResponseUtil.ok();
	}
}
