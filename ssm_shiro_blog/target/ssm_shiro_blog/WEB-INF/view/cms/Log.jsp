<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>日志查询</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/style/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/style/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/style/bootstrap-table/bootstrap-table.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/style/css/userList.css">
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

    $(function () {
        var userId = null;
        $("#date1").datetimepicker({
            format: 'yyyy-mm-dd',
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0
        })
    });

    $(document).ready(function () {
        $('#table1').bootstrapTable({
            method: "get",
            columns: [
                {
                    field: 'id',
                    title: 'ID',
                    align: "center"
                },
                {
                    field: 'username',
                    title: '用户名',
                    align: "center"
                },
                {
                    field: 'ip',
                    title: 'IP地址',
                    align: "center"
                },
                {
                    field: 'time',
                    title: '时间',
                    align: "center"
                    ,
                    formatter: function (value, row, index) {
                        return changeDateTimeFormat(value);
                    }
                },
                {
                    field: 'operation',
                    title: '操作',
                    width: "15%",
                    align: "center",
                    events: operateEvents1
                },
                {
                    field: 'result',
                    title: '结果',
                    width: "15%",
                    align: "center",
                    events: operateEvents1
                }
            ],
            pagination: true,
            pageList: [10, 15],
            search: false,
            showHeader: true,
            pageNumber: 1,
            striped: true,
            url: '${pageContext.request.contextPath }/cms/Logs',
            onLoadSuccess: function () {  //加载成功时执行
                console.info("加载成功");
            },
            onLoadError: function () {  //加载失败时执行
                console.info("加载数据失败");
            }
        });
    })

    //转换时间日期格式(时间戳转换为datetime格式)
    function changeDateTimeFormat(cellval) {
        var dateVal = cellval + "";
        if (cellval != null) {
            var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

            var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
            var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
            var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

            return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
        }
    }

    // //转换日期格式为年月
    // function changeDateFromat(cellval) {
    //     var dateVal = cellval + "";
    //     if (cellval != null) {
    //         var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
    //         var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    //         var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    //         return date.getFullYear() + "-" + month + "-" + currentDate;
    //     }
    // }
    /*删除操作*/
    window.operateEvents1 = {
        "click .btn-delete": function () {
            <%--userId = row.id;--%>
            <%--$('#cfmMsg').html('确定要删除名为  ' + row.username + '  的用户吗?');--%>
            <%--$('#myModal1 .confirmDelete').click(function () {--%>
                <%--$.ajax({--%>
                    <%--type: "post",--%>
                    <%--url: "${pageContext.request.contextPath}/deleteUser",--%>
                    <%--data: {"id": userId,"date":new Date().getTime()},--%>
                    <%--success: function (data, status) {--%>
                        <%--if (status == "success") {--%>
                            <%--new PNotify({--%>
                                <%--title: '删除成功',--%>
                                <%--delay: 1500,--%>
                                <%--text: '您已成功删除名为' + row.username + '的用户!',--%>
                                <%--text: '删除成功!',--%>
                                <%--type: 'success'--%>
                            <%--});--%>
                        <%--}--%>
                        <%--$("#table1").bootstrapTable('refresh');--%>
                    <%--},--%>
                    <%--error: function () {--%>
                        <%--new PNotify({--%>
                            <%--title: '删除失败',--%>
                            <%--text: '删除失败!',--%>
                            <%--delay: 1500,--%>
                            <%--type: 'fail'--%>
                        <%--});--%>
                    <%--}--%>
                <%--})--%>
            //     operateFormatter();
            // })
            // $("#table1").bootstrapTable('refresh');
        }
    }

</script>

<body>
<div class="search-wrap">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-2"><p class="search-title">日志查询</p></div>
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

        <div class="col-md-12">
            <table id="table1"></table>
        </div>

    </div>
</div>
<!-- 删除用户模态框（Modal） -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">删除用户</h4>
            </div>
            <div class="modal-body" id="cfmMsg"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default cancelDelete" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger confirmDelete" data-dismiss="modal">确认</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>
