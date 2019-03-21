package com.db.sys.controller;

import com.db.common.vo.JsonResult;
import com.db.common.vo.PageObject;
import com.db.sys.entity.SysLog;
import com.db.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/log/")
@Controller
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("doLogListUI")
    public String doLogListUI() {
        return "sys/log_list";

    }

    @RequestMapping("doFindPageObjects")
    @ResponseBody
    public JsonResult doFindPageObjects(Integer pageCurrent, String username) {
        PageObject<SysLog> pageObject = sysLogService.findPageObject(username, pageCurrent);
        return new JsonResult(pageObject);
    }

    @RequestMapping("doDeleteObjects")
    @ResponseBody
    public JsonResult doDeleteObjects(Integer... ids) {
        sysLogService.deleteObjects(ids);
        return new JsonResult("delete ok");
    }


}
