package com.db.sys.service;

import com.db.common.vo.PageObject;
import com.db.sys.entity.SysLog;
import org.apache.ibatis.annotations.Param;

public interface SysLogService {

    PageObject<SysLog> findPageObject(
            String username,
            Integer pageCurrent);

    int deleteObjects(Integer... ids);

}
