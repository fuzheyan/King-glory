package com.luoxiao.service;

import com.luoxiao.model.LogModal;

import java.util.List;
import java.util.Map;

/**
 * Created by losho on 2018-09-11.
 */
public interface LogService {

   void insertLog(LogModal log);

   List<LogModal> findPageLog();


}
