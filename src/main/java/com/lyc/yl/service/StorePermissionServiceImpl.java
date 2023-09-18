package com.lyc.yl.service;


import com.lyc.yl.dao.StorePermissionDao;
import com.lyc.yl.entity.LitemallPermission;
import com.lyc.yl.entity.LitemallPermissionExample;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 权限表(StorePermission)表服务实现类
 *
 * @author makejava
 * @since 2022-12-20 16:17:02
 */
@Service
public class StorePermissionServiceImpl {
    @Resource
    private StorePermissionDao permissionMapper;


    public Set<String> queryByRoleIds(Integer[] roleIds) {
        Set<String> permissions = new HashSet<String>();
        if(roleIds.length == 0){
            return permissions;
        }

        LitemallPermissionExample example = new LitemallPermissionExample();
        example.or().andRoleIdIn(Arrays.asList(roleIds)).andDeletedEqualTo(false);
        List<LitemallPermission> permissionList = permissionMapper.selectByExample(example);

        for(LitemallPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }


    public Set<String> queryByRoleId(Integer roleId) {
        Set<String> permissions = new HashSet<String>();
        if(roleId == null){
            return permissions;
        }

        LitemallPermissionExample example = new LitemallPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        List<LitemallPermission> permissionList = permissionMapper.selectByExample(example);

        for(LitemallPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }

    public Set<String> queryByRoleId(List<Integer> roleIds) {
        Set<String> permissions = new HashSet<String>();
        if(roleIds == null || roleIds.isEmpty()){
            return permissions;
        }

        LitemallPermissionExample example = new LitemallPermissionExample();
        example.or().andRoleIdIn(roleIds).andDeletedEqualTo(false);
        List<LitemallPermission> permissionList = permissionMapper.selectByExample(example);

        for(LitemallPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }

    public boolean checkSuperPermission(Integer roleId) {
        if(roleId == null){
            return false;
        }

        LitemallPermissionExample example = new LitemallPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andPermissionEqualTo("*").andDeletedEqualTo(false);
        return permissionMapper.countByExample(example) != 0;
    }

    public boolean checkSuperPermission(List<Integer> roleIds) {
        if(roleIds == null || roleIds.isEmpty()){
            return false;
        }

        LitemallPermissionExample example = new LitemallPermissionExample();
        example.or().andRoleIdIn(roleIds).andPermissionEqualTo("*").andDeletedEqualTo(false);
        return permissionMapper.countByExample(example) != 0;
    }

    public void deleteByRoleId(Integer roleId) {
        LitemallPermissionExample example = new LitemallPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        permissionMapper.logicalDeleteByExample(example);
    }

    public void add(LitemallPermission litemallPermission) {
        litemallPermission.setAddTime(LocalDateTime.now());
        litemallPermission.setUpdateTime(LocalDateTime.now());
        permissionMapper.insertSelective(litemallPermission);
    }
}
