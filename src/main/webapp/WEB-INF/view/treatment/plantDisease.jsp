<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <title>患病植物</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <link rel="icon" href="/resources/images/favicon6.ico">
    <link rel="stylesheet" href="/resources/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/resources/css/public.css" media="all"/>
    <link rel="stylesheet" href="/resources/layui_ext/dtree/dtree.css">
    <link rel="stylesheet" href="/resources/layui_ext/dtree/font/dtreefont.css">
</head>
<body class="childrenBody">

<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">植物编号:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="plantId" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入植物编号" style="height: 30px;border-radius: 10px">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">植物名称:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="plantName" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入植物名称" style="height: 30px;border-radius: 10px">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">病虫害:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="diseaseName" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入植物别名" style="height: 30px;border-radius: 10px">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">防治方法:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="treatMethod" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入形态特点" style="height: 30px;border-radius: 10px">
            </div>
        </div>

        <div class="layui-inline" style="margin-left: 50px">
            <button type="button"
                    class="layui-btn layui-btn-normal layui-icon layui-icon-search layui-btn-radius layui-btn-sm"
                    id="doSearch">查询
            </button>
            <button type="reset"
                    class="layui-btn layui-btn-warm layui-icon layui-icon-refresh layui-btn-radius layui-btn-sm">重置
            </button>
        </div>
    </div>
</form>

<!-- 数据表格开始 -->
<table class="layui-hide" id="newsTable" lay-filter="newsTable"></table>

<!-- 行工具栏 -->
<div id="newsBar" style="display: none;">
    <c:if test="${role == 1 || role == 2}">
        <a class="layui-btn layui-btn-warm layui-btn-xs layui-btn-radius" lay-event="viewNews">防治</a>
    </c:if>
</div>
<!-- 添加防治的弹出层-->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
    <form class="layui-form" lay-filter="dataFrm" id="dataFrm" style="margin-right: 20px">
        <div class="layui-form-item">
            <label class="layui-form-label">植物编号:</label>
            <div class="layui-input-block">
                <input type="text" id="plantId" name="plantId" readonly placeholder="请输入植物编号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">药剂编号:</label>
            <div class="layui-input-block">
                <select name="medicamentId" id="medicament" lay-verify="required">
                    <option value="">请选择药剂</option>
                </select>
<%--                <input type="text" name="medicamentId" placeholder="请输入药剂编号" autocomplete="off" class="layui-input">--%>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">设置养护人员:</label>
            <div class="layui-input-block">
                <select name="userId" id="maintainer" lay-verify="required">
                    <option value="">请选择养护人员</option>
                </select>
<%--                <input type="text" name="userId" placeholder="请输入养护人员编号" autocomplete="off" class="layui-input">--%>
            </div>
        </div>
<%--        <div class="layui-form-item">--%>
<%--            <label class="layui-form-label">创建时间:</label>--%>
<%--            <div class="layui-input-block">--%>
<%--                <input type="text" id="createTime" name="createTime" placeholder="请输入创建时间" autocomplete="off" class="layui-input">--%>
<%--            </div>--%>
<%--        </div>--%>

        <div class="layui-form-item">
            <label class="layui-form-label">创建人员:</label>
            <div class="layui-input-block">
                <input type="text" name="creator" readonly placeholder="请输入创建人员" autocomplete="off" class="layui-input" value="${uid}">
            </div>
        </div>

<%--        <div class="layui-form-item">--%>
<%--            <label class="layui-form-label">更新时间:</label>--%>
<%--            <div class="layui-input-block">--%>
<%--                <input type="text" id="updateTime" name="updateTime" placeholder="请输入更新时间" autocomplete="off" class="layui-input">--%>
<%--            </div>--%>
<%--        </div>--%>

        <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: center;padding-right: 120px">
                <button type="button"
                        class="layui-btn layui-btn-normal layui-btn-md layui-icon layui-icon-release layui-btn-radius"
                        lay-filter="doSubmit" lay-submit="">提交
                </button>
                <button type="reset" id="dataFrmResetBtn"
                        class="layui-btn layui-btn-warm layui-btn-md layui-icon layui-icon-refresh layui-btn-radius">重置
                </button>
            </div>
        </div>
    </form>
</div>
<script src="/resources/layui/layui.js"></script>

<script type="text/javascript">
    var tableIns;
    layui.use(['jquery', 'layer', 'form', 'table', 'laydate', 'upload'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var laydate = layui.laydate;
        var upload = layui.upload;

        //渲染时间
        laydate.render({
            elem: '#createTime',
            type: 'datetime'
            ,trigger: 'click'// 增加这个参数解决
        });
        laydate.render({
            elem: '#updateTime',
            type: 'datetime'
            ,trigger: 'click'// 增加这个参数解决
        });

        //渲染数据表格
        tableIns = table.render({
            elem: '#newsTable'   //渲染的目标对象
            , url: '/disease/diseaseInfoList.action' //数据接口
            , title: '植物信息'//数据导出来的标题
            , toolbar: "#newsToolBar"   //表格的工具条
            , height: 'full-185'
            , cellMinWidth: 120 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                {field: 'plantId', title: '植物编号', align: 'center'}
                , {field: 'plantName', title: '植物名称', align: 'center'}
                , {field: 'diseaseName', title: '病虫害名称', align: 'center'}
                , {field: 'treatMethod', title: '防治方法', align: 'center'}
                , {fixed: 'right', title: '操作', toolbar: '#newsBar', align: 'center', width: 270}
            ]],
            done:function (data, curr, count) {
                //不是第一页时，如果当前返回的数据为0那么就返回上一页
                if(data.data.length==0&&curr!=1){
                    tableIns.reload({
                        page:{
                            curr:curr-1
                        }
                    })
                }
                //动态监听表头高度变化，冻结行跟着改变高度
                $(".layui-table-header  tr").resize(function () {
                    $(".layui-table-header  tr").each(function (index, val) {
                        $($(".layui-table-fixed .layui-table-header table tr")[index]).height($(val).height());
                    });
                });
                //初始化高度，使得冻结行表头高度一致
                $(".layui-table-header  tr").each(function (index, val) {
                    $($(".layui-table-fixed .layui-table-header table tr")[index]).height($(val).height());
                });
                //动态监听表体高度变化，冻结行跟着改变高度
                $(".layui-table-body  tr").resize(function () {
                    $(".layui-table-body  tr").each(function (index, val) {
                        $($(".layui-table-fixed .layui-table-body table tr")[index]).height($(val).height());
                    });
                });
                //初始化高度，使得冻结行表体高度一致
                $(".layui-table-body  tr").each(function (index, val) {
                    $($(".layui-table-fixed .layui-table-body table tr")[index]).height($(val).height());
                });
            }
        });

        //模糊查询
        $("#doSearch").click(function () {
            var params = $("#searchFrm").serialize();
            //alert(params);
            tableIns.reload({
                url: "/disease/diseaseInfoList.action?" + params,
                page: {curr: 1}
            })
        });


        //监听行工具事件
        table.on('tool(newsTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'viewNews') {//打开防治
                viewNews(data);
            }
        });

        var url;
        var mainIndex;
        var data1;
        //打开防治弹出层
        function viewNews(data) {
            mainIndex = layer.open({
                type: 1,
                title: '添加防治信息',
                content: $("#saveOrUpdateDiv"),
                area: ['800px', '540px'],
                success: function (index) {
                    data1=data;
                    $("#plantId").val(data.plantId);

                    url = "/disease/addTreatment.action";
                }
            });
        }
        //加载药剂下拉列表
        $.get("/disease/loadAllMedicamentForSelect.action", function (res) {
            var data = res.data;
            var dom = $("#medicament");
            var html = '<option value="-1">请选择药剂</option>'
            $.each(data, function (index, item) {
                html += '<option value="' + item.medicamentId + '">' + item.medicamentName + '</option>'
            });
            dom.html(html);
            form.render("select");
        })
        //加载养护人员下拉列表
        $.get("/disease/loadAllMaintainerForSelect.action", function (res) {
            var data = res.data;
            var dom = $("#maintainer");
            var html = '<option value="-1">请选择养护人员</option>'
            $.each(data, function (index, item) {
                html += '<option value="' + item.userId + '">' + item.loginName + '</option>'
            });
            dom.html(html);
            form.render("select");
        })
        //提交
        form.on("submit(doSubmit)", function (obj) {
            //序列化表单数据
            var params = $("#dataFrm").serialize();
            $.post(url, params, function (obj) {
                layer.msg(obj.msg);
                //关闭弹出层
                $("#dataFrm")[0].reset();
                layer.close(mainIndex);
                //刷新数据 表格
                tableIns.reload();
            })
        });
    });

</script>
<style type="text/css">
    .layui-table img{
        max-width:100%
    }
</style>

</body>
</html>
