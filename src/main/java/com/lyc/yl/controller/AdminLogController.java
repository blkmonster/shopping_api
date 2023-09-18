package com.lyc.yl.controller;

import com.lyc.yl.annotation.Order;
import com.lyc.yl.annotation.RequiresPermissionsDesc;
import com.lyc.yl.annotation.Sort;
import com.lyc.yl.entity.LitemallLog;
import com.lyc.yl.entity.StoreAdmin;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.StoreLogServiceImpl;
import com.lyc.yl.util.IpUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 操作日志表(StoreLog)表控制层
 *
 * @author makejava
 * @since 2022-12-20 10:01:29
 */
@RestController
@RequestMapping("/admin/log")
public class AdminLogController {
    public static final  Integer LOG_TYPE_GENERAL = 0;
    public static final  Integer LOG_TYPE_AUTH = 1;
    public static final  Integer LOG_TYPE_ORDER = 2;
    public static final  Integer LOG_TYPE_OTHER = 3;
    public static final  Integer LOG_TYPE_INTERFACE = 4;
    /**
     * 服务对象
     */
    @Resource
    private StoreLogServiceImpl logService;

    @RequiresPermissions("admin:log:list")
    @RequiresPermissionsDesc(menu = {"系统管理", "操作日志"}, button = "查询")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<LitemallLog> logList = logService.querySelective(name, page, limit, sort, order);
        return ResponseUtil.okList(logList);
    }

    public void logGeneralSucceed(String action) {
        logAdmin(LOG_TYPE_GENERAL, action, true, "", "");
    }

    public void logGeneralSucceed(String action, String result) {
        logAdmin(LOG_TYPE_GENERAL, action, true, result, "");
    }

    public void logGeneralFail(String action, String error) {
        logAdmin(LOG_TYPE_GENERAL, action, false, error, "");
    }

    public void logAuthSucceed(String action) {
        logAdmin(LOG_TYPE_AUTH, action, true, "", "");
    }

    public void logAuthSucceed(String action, String result) {
        logAdmin(LOG_TYPE_AUTH, action, true, result, "");
    }

    public void logAuthFail(String action, String error) {
        logAdmin(LOG_TYPE_AUTH, action, false, error, "");
    }

    public void logOrderSucceed(String action) {
        logAdmin(LOG_TYPE_ORDER, action, true, "", "");
    }

    public void logOrderSucceed(String action, String result) {
        logAdmin(LOG_TYPE_ORDER, action, true, result, "");
    }

    public void logOrderFail(String action, String error) {
        logAdmin(LOG_TYPE_ORDER, action, false, error, "");
    }

    public void logOtherSucceed(String action) {
        logAdmin(LOG_TYPE_OTHER, action, true, "", "");
    }

    public void logOtherSucceed(String action, String result) {
        logAdmin(LOG_TYPE_OTHER, action, true, result, "");
    }

    public void logOtherFail(String action, String error) {
        logAdmin(LOG_TYPE_OTHER, action, false, error, "");
    }

    public void logInterfaceSucceed(String action, String result) {
        logAdmin(LOG_TYPE_INTERFACE, action, true, result, "");
    }

    public void logInterfaceFail(String action, String error) {
        logAdmin(LOG_TYPE_INTERFACE, action, false, error, "");
    }

    public void logAdmin(Integer type, String action, Boolean succeed, String result, String comment) {
        LitemallLog log = new LitemallLog();

        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null) {
            StoreAdmin admin = (StoreAdmin) currentUser.getPrincipal();
            if (admin != null) {
                log.setAdmin(admin.getUsername());
            } else {
                log.setAdmin("匿名用户");
            }
        } else {
            log.setAdmin("匿名用户");
        }

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (request != null) {
            log.setIp(IpUtil.getIpAddr(request));
        }

        log.setType(type);
        log.setAction(action);
        log.setStatus(succeed);
        log.setResult(result);
        log.setComment(comment);
        logService.add(log);
    }

}

