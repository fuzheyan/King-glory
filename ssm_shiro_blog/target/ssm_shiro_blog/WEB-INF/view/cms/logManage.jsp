<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/style/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/style/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/style/bootstrap-table/bootstrap-table.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/style/css/userRoleList.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/style/pnotify/pnotify.custom.min.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath }/style/datetimepicker/css/bootstrap-datetimepicker.min.css">
<script src="${pageContext.request.contextPath}/style/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/style/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/style/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${pageContext.request.contextPath}/style/pnotify/pnotify.custom.min.js"></script>
<script src="${pageContext.request.contextPath }/style/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/style/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/style/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<script>
    $(document).ready(function () {
        $('#table1').bootstrapTable({
            columns: [
                {
                    field: 'id',
                    title: 'ID',
                    align: "center",
                    width: "5%"
                },
                {
                    field: 'username',
                    title: '用户名',
                    align: "center",
                    width: "10%"
                },
                {   field: 'ip',
                    title: 'IP地址',
                    align: "center",
                    width: "10%"

                },
                {
                    field: 'time',
                    title: '登录时间',
                    width: "5%",
                    align: "center",
                    formatter: operateFormatter,
                    events: operateEvents1
                },
                {
                    field: 'option',
                    title: '执行操作',
                    align: "center",
                    width: "20%"
                },


            ],
            url: '${pageContext.request.contextPath }/cms/userRoleList',
            pagination: true,
            pageList: [10, 15],
            search: false,
            showHeader: true,
            pageNumber: 1,
            striped: true
        })
    })

    function operateFormatter() {
        return [
            // '<button type="button" class="btn btn-sm btn-primary btn-edit" data-toggle="modal" data-target="#myModal">修改</button>' + '&nbsp;'
        ].join('');
    }

    window.operateEvents1 = {
        "click .btn-edit": function (e, value, row, index) {
            $('#myModalLabel').html(row.username);
            $('#currId').val(row.id);
            var userRoleStr = row.roles;
            $('input[type = "checkbox"]').removeAttr("checked");//初始化,清空
            $('input[name="userRoles"]').each(function () {
                for (var i = 0; i < userRoleStr.length; i++) {
                    console.log($(this).val);
                    if (userRoleStr[i] == $(this).val()) {
                        $(this).prop("checked", true);
                    }
                }
            })
        }
    }

    function updateRoles(){
        var userId =  $('#currId').val();
        var roles = [];
        console.log(userId);//当前用户id
        $('input[name="userRoles"]').each(function(){
            if($(this).is(":checked")){
                roles.push($(this).val());
            }
        })
        console.log(roles);
        var roleStr = roles.join(",");
        $.ajax({
            url:"${pageContext.request.contextPath }/updateRoles",
            method:"GET",
            data:{
                "userId" : userId,
                "roleStr" : roleStr
            },
            async:true,
            success:function(data){
                console.log("success");
                $("#table1").bootstrapTable('refresh');
            }

        })
    }

</script>
<body>
<div class="search-wrap">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-2"><p class="search-title">日志查询(王者荣耀)</p></div>
        <div class="col-md-2">
            <input type="text" class="form-control" id="exampleInputName2" placeholder="请输入关键字.">
        </div>
        <div class="col-md-2">
            <div class="form-group">
                <div class='input-group date' id='date1'>
                    <input type='text' class="form-control"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
        </div>
        <div class="col-md-2">
            <button type="button" class="btn btn-primary">查找</button>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
<%--user list--%>
<div class="result-wrap">
    <div class="row">
        <div class="col-md-10">
            <table id="table1"></table>
        </div>
    </div>
</div>

</body>
</html>