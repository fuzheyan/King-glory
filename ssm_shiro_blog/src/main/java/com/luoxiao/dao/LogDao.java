package com.luoxiao.dao;

import com.luoxiao.model.LogModal;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by losho on 2018-09-11.
 */
public interface LogDao {

    public void insertLog(LogModal log);

    List<LogModal> findPageLogs();



}
