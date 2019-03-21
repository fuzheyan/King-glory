package com.db.common.vo;

import java.util.List;

public class SysRoleMenuVo {
    private String name;
    /**角色备注*/
    private String note;
    /**角色对应的菜单id*/
    private List<Integer> menuIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Integer> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Integer> menuIds) {
        this.menuIds = menuIds;
    }

}
