package com.lyc.yl.controller;

import com.lyc.yl.annotation.Order;
import com.lyc.yl.annotation.RequiresPermissionsDesc;
import com.lyc.yl.annotation.Sort;
import com.lyc.yl.entity.LitemallPermission;
import com.lyc.yl.entity.LitemallRole;
import com.lyc.yl.entity.PermVo;
import com.lyc.yl.entity.StoreAdmin;
import com.lyc.yl.result.AdminResponseCode;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.StoreAdminServiceImpl;
import com.lyc.yl.service.StorePermissionServiceImpl;
import com.lyc.yl.service.StoreRoleServiceImpl;
import com.lyc.yl.util.JacksonUtil;
import com.lyc.yl.util.Permission;
import com.lyc.yl.util.PermissionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.*;

import static com.lyc.yl.result.AdminResponseCode.ROLE_NAME_EXIST;
import static com.lyc.yl.result.AdminResponseCode.ROLE_USER_EXIST;

/**
 * 角色表(StoreRole)表控制层
 *
 * @author makejava
 * @since 2022-12-20 16:16:33
 */
@RestController
@RequestMapping("/admin/role")
public class AdminRoleController {
    @Autowired
    private StoreRoleServiceImpl roleService;
    @Autowired
    private StorePermissionServiceImpl permissionService;
    @Autowired
    private StoreAdminServiceImpl adminService;

    @RequiresPermissions("admin:role:list")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "角色查询")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<LitemallRole> roleList = roleService.querySelective(name, page, limit, sort, order);
        return ResponseUtil.okList(roleList);
    }

    @GetMapping("/options")
    public Object options() {
        List<LitemallRole> roleList = roleService.queryAll();

        List<Map<String, Object>> options = new ArrayList<>(roleList.size());
        for (LitemallRole role : roleList) {
            Map<String, Object> option = new HashMap<>(2);
            option.put("value", role.getId());
            option.put("label", role.getName());
            options.add(option);
        }

        return ResponseUtil.okList(options);
    }

    @RequiresPermissions("admin:role:read")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "角色详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        LitemallRole role = roleService.findById(id);
        return ResponseUtil.ok(role);
    }


    private Object validate(LitemallRole role) {
        String name = role.getName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }

        return null;
    }

    @RequiresPermissions("admin:role:create")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "角色添加")
    @PostMapping("/create")
    public Object create(@RequestBody LitemallRole role) {
        Object error = validate(role);
        if (error != null) {
            return error;
        }

        if (roleService.checkExist(role.getName())) {
            return ResponseUtil.fail(ROLE_NAME_EXIST, "角色已经存在");
        }

        roleService.add(role);

        return ResponseUtil.ok(role);
    }

    @RequiresPermissions("admin:role:update")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "角色编辑")
    @PostMapping("/update")
    public Object update(@RequestBody LitemallRole role) {
        Object error = validate(role);
        if (error != null) {
            return error;
        }

        roleService.updateById(role);
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:role:delete")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "角色删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody LitemallRole role) {
        Integer id = role.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }

        // 如果当前角色所对应管理员仍存在，则拒绝删除角色。
        List<StoreAdmin> adminList = adminService.all();
        for (StoreAdmin admin : adminList) {
            Integer[] roleIds = admin.getRoleIds();
            for (Integer roleId : roleIds) {
                if (id.equals(roleId)) {
                    return ResponseUtil.fail(ROLE_USER_EXIST, "当前角色存在管理员，不能删除");
                }
            }
        }

        roleService.deleteById(id);
        return ResponseUtil.ok();
    }


    @Autowired
    private ApplicationContext context;
    private List<PermVo> systemPermissions = null;
    private Set<String> systemPermissionsString = null;

    private List<PermVo> getSystemPermissions() {
        final String basicPackage = "com.lyc.yl";
        if (systemPermissions == null) {
            List<Permission> permissions = PermissionUtil.listPermission(context, basicPackage);
            systemPermissions = PermissionUtil.listPermVo(permissions);
            systemPermissionsString = PermissionUtil.listPermissionString(permissions);
        }
        return systemPermissions;
    }

    // 非超级管理员屏蔽的权限
    private List<String> filter = Arrays.asList("操作日志", "通知管理", "小程序配置", "其他", "统计管理", "对象存储", "品牌制造商", "优惠券管理", "团购管理", "专题管理",
            "用户收藏", "用户足迹", "搜索历史", "意见反馈", "通用问题", "关键词", "商品评论", "售后管理", "品牌管理", "类目管理", "评论管理");
    private  List<String> filterApi = Arrays.asList("POST /admin/order/delete", "POST /admin/order/pay",
            "POST /admin/order/ship", "POST /admin/order/refund",
            "POST /admin/order/reply");
    private List<PermVo> filterSysPermissions(List<PermVo> list){
        if (list == null || list.size() == 0) {
            return null;
        }
        List<PermVo> result = new ArrayList<>();
        for (PermVo permVo : list) {
            if (!filter.contains(permVo.getLabel()) && !filterApi.contains(permVo.getApi())) {
                permVo.setChildren(filterSysPermissions(permVo.getChildren()));
                result.add(permVo);
            }
        }
        return result;
    }

    /**
     * 管理员的权限情况
     *
     * @return 系统所有权限列表、角色权限、管理员已分配权限
     */
    @RequiresPermissions("admin:role:permission:get")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "权限详情")
    @GetMapping("/permissions")
    public Object getPermissions(Integer roleId) {
        List<PermVo> systemPermissionsTemp = getSystemPermissions();

        // 这里需要注意的是，如果存在超级权限*，那么这里需要转化成当前所有系统权限。
        // 之所以这么做，是因为前端不能识别超级权限，所以这里需要转换一下。
        Set<String> assignedPermissions = null;
        if (permissionService.checkSuperPermission(roleId)) {
            getSystemPermissions();
            assignedPermissions = systemPermissionsString;
        } else {
            assignedPermissions = permissionService.queryByRoleId(roleId);
        }


        Subject currentUser = SecurityUtils.getSubject();
        StoreAdmin currentAdmin = (StoreAdmin) currentUser.getPrincipal();
        Integer[] roles = currentAdmin.getRoleIds();
        List<Integer> roleIds = Arrays.asList(roles);
        Set<String> curPermissions = null;
        // 非超级管理员屏蔽部分权限
        if (!roleIds.contains(1)) {
            systemPermissionsTemp = filterSysPermissions(systemPermissionsTemp);
        }else {
            systemPermissions = null;
            systemPermissionsTemp = getSystemPermissions();
        }
        if (!permissionService.checkSuperPermission(roleIds)) {
            curPermissions = permissionService.queryByRoleId(roleIds);
        }


        Map<String, Object> data = new HashMap<>();
        data.put("systemPermissions", systemPermissionsTemp);
        data.put("assignedPermissions", assignedPermissions);
        data.put("curPermissions", curPermissions);
        return ResponseUtil.ok(data);
    }


    /**
     * 更新管理员的权限
     *
     * @param body
     * @return
     */
    @RequiresPermissions("admin:role:permission:update")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "权限变更")
    @PostMapping("/permissions")
    public Object updatePermissions(@RequestBody String body) {
        Integer roleId = JacksonUtil.parseInteger(body, "roleId");
        List<String> permissions = JacksonUtil.parseStringList(body, "permissions");
        if (roleId == null || permissions == null) {
            return ResponseUtil.badArgument();
        }

        // 如果修改的角色是超级权限，则拒绝修改。
        if (permissionService.checkSuperPermission(roleId)) {
            return ResponseUtil.fail(AdminResponseCode.ROLE_SUPER_SUPERMISSION, "当前角色的超级权限不能变更");
        }

        // 先删除旧的权限，再更新新的权限
        permissionService.deleteByRoleId(roleId);
        for (String permission : permissions) {
            LitemallPermission litemallPermission = new LitemallPermission();
            litemallPermission.setRoleId(roleId);
            litemallPermission.setPermission(permission);
            permissionService.add(litemallPermission);
        }
        return ResponseUtil.ok();
    }


}

