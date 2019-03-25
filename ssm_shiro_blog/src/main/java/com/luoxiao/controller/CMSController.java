package com.luoxiao.controller;

import com.luoxiao.model.LogModal;
import com.luoxiao.model.User;
import com.luoxiao.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luoxiao.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author
 */
@Controller
public class CMSController {

    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;


    /**
     * 角色页面
     *
     * @return
     */
    @RequestMapping("cms/permissionManage")
    public String toPermissionPage() {
        return "cms/permissionManage";
    }

    @RequestMapping("cms/userList")
    public String userList() {
        return "cms/userList";
    }


    @ResponseBody
    @RequestMapping("cms/AllUser")
    public List<User> getAllUser() {
        return userService.selectAll();
    }

    /**日志展示*/
    @RequestMapping("cms/Logs")
    @ResponseBody
    public List<LogModal> doLogList(){
        List<LogModal> list=logService.findPageLog();
//        System.out.println(list);
        return list;
    }

}
