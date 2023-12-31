<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>用户管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <link rel="icon" href="/resources/favicon6.ico">
    <link rel="stylesheet" href="/resources/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/resources/css/public.css" media="all"/>
    <link rel="stylesheet" href="/resources/layui_ext/dtree/dtree.css">
    <link rel="stylesheet" href="/resources/layui_ext/dtree/font/dtreefont.css">
    <style>

    </style>
</head>
<body class="childrenBody">

<!-- 数据表格开始 -->
<table class="layui-hide" id="userTable" lay-filter="userTable"></table>

<div id="userToolBar" style="display: none;">
    <button type="button" class="layui-btn layui-btn-sm layui-btn-radius" lay-event="add">增加用户</button>
</div>
<div id="userBar" style="display: none;">
    <c:if test="${role==1}">
    <a class="layui-btn layui-btn-xs layui-btn-radius" lay-event="edit">修改</a>
    <a id="delebtn" class="layui-btn layui-btn-danger layui-btn-xs layui-btn-radius" lay-event="del">删除</a>
    </c:if>
</div>

<!-- 添加的弹出层-->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
    <form class="layui-form" lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">角色:</label>
                <div class="layui-input-inline">
                    <select name="rid" id="rid" lay-verify="required">
                        <option value="">请选择角色</option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">用户名:</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id">
                    <input type="text" name="loginName" lay-verify="required" placeholder="请输入用户名"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">密码:</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" lay-verify="required" placeholder="请输入密码"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">确认密码:</label>
                <div class="layui-input-inline">
                    <input type="password" name="password1" lay-verify="required|passwordMatch" placeholder="请输入密码"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: center;padding-right: 120px">
                <button type="button"
                        class="layui-btn layui-btn-normal layui-btn-md layui-icon layui-icon-release layui-btn-radius"
                        lay-filter="doSubmit" lay-submit="">提交
                </button>
                <button type="reset"
                        class="layui-btn layui-btn-warm layui-btn-md layui-icon layui-icon-refresh layui-btn-radius">重置
                </button>
            </div>
        </div>
    </form>
</div>
<!-- 修改的弹出层-->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv2">
    <form class="layui-form" lay-filter="dataFrm2" id="dataFrm2">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">用户编号:</label>
                <div class="layui-input-inline">
                    <input type="text" name="userId" id="userId" lay-verify="required"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">用户名:</label>
                <div class="layui-input-inline">
                    <input type="text" name="loginName" id="loginName" lay-verify="required" placeholder="请输入用户名"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">密码:</label>
                <div class="layui-input-inline">
                    <input type="text" name="password" id="password1" lay-verify="required" placeholder="请输入密码"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">确认密码:</label>
                <div class="layui-input-inline">
                    <input type="text" name="password1" lay-verify="required|passwordMatchOne" placeholder="请输入密码"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: center;padding-right: 120px">
                <button type="button"
                        class="layui-btn layui-btn-normal layui-btn-md layui-icon layui-icon-release layui-btn-radius"
                        lay-filter="doSubmit2" lay-submit="">提交
                </button>
                <button type="reset"
                        class="layui-btn layui-btn-warm layui-btn-md layui-icon layui-icon-refresh layui-btn-radius">重置
                </button>
            </div>
        </div>
    </form>
</div>


<script src="/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.extend({
        dtree: '/resources/layui_ext/dist/dtree'
    }).use(['jquery', 'layer', 'form', 'table', 'dtree'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var dtree = layui.dtree;
        //渲染数据表格
        tableIns = table.render({
            elem: '#userTable'   //渲染的目标对象
            , url: '/user/findPage.action' //数据接口
            , title: '用户数据表'//数据导出来的标题
            , toolbar: "#userToolBar"   //表格的工具条
            , height: 'full-120'
            , cellMinWidth: 200 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                // {type: 'checkbox', fixed: 'left'},
                 {field: 'userId', title: '用户编号', align: 'center', width: '150'}
                , {field: 'loginName', title: '用户名', align: 'center', width: '150'}
                , {
                    field: 'role', title: '用户身份', align: 'center', width: '150', templet: function (d) {
                        if (d.rid == 1) {
                            return '管理员';
                        } else if (d.rid == 2) {
                            return '主管人员';
                        } else if (d.rid == 3) {
                            return '养护人员';
                        }else if (d.rid == 4) {
                            return '监测人员';
                        }
                    }
                }
                , {fixed: 'right', title: '操作', toolbar: '#userBar', align: 'center', width: '200'}
            ]],
            done: function (data, curr, count) {
                //不是第一页时，如果当前返回的数据为0那么就返回上一页
                if (data.data.length == 0 && curr != 1) {
                    tableIns.reload({
                        page: {
                            curr: curr - 1
                        }
                    })
                }
            }
        })



        //监听头部工具栏事件
        table.on("toolbar(userTable)", function (obj) {
            switch (obj.event) {
                case 'add':
                    openAddUser();
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(userTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'del') { //删除
                if(data.rid===1){
                    $('#delebtn').hide();
                }
                layer.confirm('真的删除【' + data.loginName + '】这个用户么？', function (index) {
                    //向服务端发送删除指令
                    $.post("/user/delete.action", {userId: data.userId}, function (res) {
                        layer.msg(res.msg);
                        //刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if (layEvent === 'edit') { //修改
                //修改，打开修改界面
                $('#rid2').prop('disabled', true);
                openUpdateUser(data);
            }
        });

        var url;
        var mainIndex;

        //打开添加页面
        function openAddUser() {
            mainIndex = layer.open({
                type: 1,
                title: '添加',
                content: $("#saveOrUpdateDiv"),
                area: ['700px', '580px'],
                success: function (index) {
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    url = "/user/save.action";
                }
            });
        }
        //加载角色选择下拉列表
        $.get("/user/loadAllForSelect.action", function (res) {
            var data = res.data;
            var dom = $("#rid");
            var html = '<option value="0">请选择角色</option>'
            $.each(data, function (index, item) {
                // 跳过管理员
                if (index > 0) {
                    html += '<option value="' + item.roleId + '">' + item.roleName + '</option>'
                }
            });
            dom.html(html);
            form.render("select");
        })

        // 自定义密码验证规则
        form.verify({
            passwordMatch: function(value) {
                var passwordValue = $('input[name=password]').val();
                if(value !== passwordValue){
                    return '两次输入的密码不一致';
                }
            }
        });
        // 自定义密码验证规则
        form.verify({
            passwordMatchOne: function(value) {
                var passwordValue = $('input[id=password1]').val();
                if(value !== passwordValue){
                    return '两次输入的密码不一致';
                }
            }
        });
        //打开修改页面
        function openUpdateUser(data) {

            mainIndex = layer.open({
                type: 1,
                title: '修改',
                content: $("#saveOrUpdateDiv2"),
                area: ['700px', '580px'],
                success: function (index) {
                    // form.val("dataFrm2", data);
                    //清空表单数据
                    $("#dataFrm2")[0].reset();
                    $("#userId").val(data.userId);
                    $("#loginName").val(data.loginName);
                    url = "/user/save.action";
                }
            });
        }
        //提交
        form.on("submit(doSubmit)", function (obj) {
            //序列化表单数据
            var params = $("#dataFrm").serialize();
            $.post(url, params, function (obj) {
                layer.msg(obj.msg);
                //关闭弹出层
                layer.close(mainIndex)
                //刷新数据 表格
                tableIns.reload();
            });
            return false; // 阻止表单跳转
        });
        //提交
        form.on("submit(doSubmit2)", function (obj) {
            //序列化表单数据
            var params = $("#dataFrm2").serialize();
            $.post(url, params, function (obj) {
                layer.msg(obj.msg);
                //关闭弹出层
                layer.close(mainIndex)
                //清空表单数据
                $("#dataFrm2")[0].reset();
                //刷新数据 表格
                tableIns.reload();
            });
            return false; // 阻止表单跳转
        });
    });

</script>
</body>
</html>
