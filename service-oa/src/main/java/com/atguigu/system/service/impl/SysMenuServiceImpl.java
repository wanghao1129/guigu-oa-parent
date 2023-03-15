package com.atguigu.system.service.impl;

import com.atguigu.common.execption.GuiguException;
import com.atguigu.common.result.ResultCodeEnum;
import com.atguigu.model.system.SysMenu;
import com.atguigu.system.util.MenuHelper;
import com.atguigu.system.mapper.SysMenuMapper;
import com.atguigu.system.service.SysMenuService;
import com.atguigu.vo.system.AssignMenuVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findNodes() {
        //全部权限列表
        List<SysMenu> sysMenuList = this.list();
        if (CollectionUtils.isEmpty(sysMenuList)) {
            return null;
        }

        //构建树形数据
        List<SysMenu> result = MenuHelper.buildTree(sysMenuList);
        return result;
    }

    @Override
    public List<SysMenu> findSysMenuByRoleId(Long roleId) {
        return null;
    }

    @Override
    public void doAssign(AssignMenuVo assignMenuVo) {

    }

    @Override
    public boolean removeById(Serializable id) {
        int count = this.count(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getParentId, id));
        if (count > 0) {
            throw new GuiguException(201,"菜单不能删除");
        }
        sysMenuMapper.deleteById(id);
        return false;
    }

}
