package com.lyc.yl.controller;

import com.lyc.yl.entity.Cart;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.CartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 购物车管理层
 * @author zhaoxin
 */
@RestController
@RequestMapping("/wx/cart")
public class WxCartController {

    @Resource
    private CartService cartService;

    /**
     * 添加购物车
     *
     * @param cart
     * @return
     */
    @PostMapping("/add")
    public Object add(@RequestBody Cart cart) {
        if (cart.getGoodsSn() == null) {
            return ResponseUtil.badArgument();
        }
        if (cart.getUserId() == null ) {
            return ResponseUtil.unlogin();
        }
        //有则更新数量，无则添加
        cartService.insertOrUpdate(cart);

        return ResponseUtil.ok();
    }

    /**
     * 购物车物品数量
     *
     * @param cart
     * @return
     */
    @GetMapping("/count")
    public Object count(Cart cart) {
        if (cart.getUserId() == null ) {
            return ResponseUtil.badArgument();
        }
        int total = cartService.count(cart);
        return ResponseUtil.ok(total);
    }

    /**
     * 查询购物车
     *
     * @param cart
     * @return
     */
    @GetMapping("/index")
    public Object index(Cart cart) {
        if (cart.getUserId() == null) {
            return ResponseUtil.badArgument();
        }
        List<Cart> carts = cartService.index(cart);
        return ResponseUtil.okList(carts);
    }

    /**
     * 修改购物车
     *
     * @param cart
     * @return
     */
    @PostMapping("/update")
    public Object update(@RequestBody Cart cart) {
        if (cart.getUserId() == null || cart.getGoodsSn() == null) {
            return ResponseUtil.badArgument();
        }
        cartService.addOrCut(cart);
        return ResponseUtil.ok();
    }

    /**
     * 删除购物车
     *
     * @param cart
     * @return
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody Cart cart) {
        if (cart.getId() == null) {
            return ResponseUtil.badArgument();
        }
        cartService.delete(cart.getId());
        return ResponseUtil.ok();
    }

    /**
     * 勾选购物车
     *当checked为1时选择，否则取消
     * @param cart
     * @return
     */
    @PostMapping("/checked")
    public Object checked(@RequestBody Cart cart) {
        if (cart.getChecked() == null) {
            return ResponseUtil.badArgument();
        }
        cartService.checked(cart);
        return ResponseUtil.ok();
    }

}

