package com.db.sys.dao;

import com.db.sys.entity.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysLogDao {
    int deleteObject(@Param("ids")Integer... ids);
    Map<String, Object> findById(Integer id);

    List<SysLog> findPageObject(
            @Param("username") String username,
            @Param("startIndex") Integer startIndex,
            @Param("pageSize") Integer pageSiza
    );

    int getRowCount(@Param("username") String username);
    int insertObjct(SysLog log);
}
