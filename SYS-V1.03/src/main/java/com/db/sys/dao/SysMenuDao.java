package com.db.sys.dao;

import com.db.common.vo.Node;
import com.db.sys.entity.SysLog;
import com.db.sys.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 菜单的数据层对象
 */
public interface SysMenuDao {
    /**
     * 查询所有菜单
     * 一行记录映射一个map
     * 多行记录存储到List
     * @return
     */
    List<Map<String, Object>> findObjects();

    List<Node> findZtreeMenuNodes();


    int getChildCount(Integer id);

    int deleteObject(Integer id);

    int insertObject(SysMenu entity);

    int updateObject(SysMenu entity);

    int deleteObjectsByRoleId(Integer roleId);

    List<String> findPermissions(@Param("menuIds") Integer[] menuIds);


}
