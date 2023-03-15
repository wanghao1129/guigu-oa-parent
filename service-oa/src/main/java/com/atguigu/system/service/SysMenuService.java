package com.atguigu.system.service;


import com.atguigu.model.system.SysMenu;
import com.atguigu.vo.system.AssignMenuVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {

    /**
     * 菜单树形数据
     * @return
     */
    List<SysMenu> findNodes();

    List<SysMenu> findSysMenuByRoleId(Long roleId);

    void doAssign(AssignMenuVo assignMenuVo);
}
