<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>属目管理</title>
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
            <label class="layui-form-label">种目编号:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="speciesId" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入种目编号" style="height: 30px;border-radius: 10px">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">种目名称:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="speciesName" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入种目名称" style="height: 30px;border-radius: 10px">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">属目名称:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="genusName" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入属目名称" style="height: 30px;border-radius: 10px">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">科目名称:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="familyName" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入科目名称" style="height: 30px;border-radius: 10px">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">生长环境:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="environment" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入生长环境" style="height: 30px;border-radius: 10px">
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
<div id="newsBar" style="display: none;">
    <c:if test="${role==1}">
        <a class="layui-btn layui-btn-xs layui-btn-radius" lay-event="edit">修改</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-radius" lay-event="del">删除</a>
        <a class="layui-btn layui-btn-normal layui-btn-xs layui-btn-radius" lay-event="region">分布区域</a>
    </c:if>
</div>

<!-- 添加和修改的弹出层-->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
    <form class="layui-form" lay-filter="dataFrm" id="dataFrm" style="margin-right: 20px">
        <div class="layui-form-item">
            <label class="layui-form-label">种目编号:</label>
            <div class="layui-input-block">
                <input type="text" name="speciesId" placeholder="请输入种目编号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">种目名称:</label>
            <div class="layui-input-block">
                <input type="text" name="speciesName" placeholder="请输入种目名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">属目名称:</label>
            <div class="layui-input-block">
                <input type="text" name="genusName" placeholder="请输入属目名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">科目名称:</label>
            <div class="layui-input-block">
                <input type="text" name="familyName" placeholder="请输入科目名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">形态特征:</label>
            <div class="layui-input-block">
                <input type="text" name="feature" placeholder="请输入形态特征" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">应用价值:</label>
            <div class="layui-input-block">
                <input type="text" name="value" placeholder="请输入应用价值" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">栽培技术要点:</label>
            <div class="layui-input-block">
                <input type="text" name="point" placeholder="请输入栽培技术要点" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">生长环境:</label>
            <div class="layui-input-block">
                <input type="text" name="point" placeholder="请输入生长环境" autocomplete="off" class="layui-input">
            </div>
        </div>
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
<!-- 分布区域的弹出层-->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv2">
<%--    <form class="layui-form" lay-filter="dataFrm2" id="dataFrm2" style="margin-right: 20px">--%>
<%--        <div class="layui-form-item">--%>
<%--            <label class="layui-form-label">省名:</label>--%>
<%--            <div class="layui-input-block">--%>
<%--                <input type="text" id="provinceName" name="provinceName" readonly placeholder="请输入省名" autocomplete="off" class="layui-input">--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="layui-form-item">--%>
<%--            <label class="layui-form-label">市名:</label>--%>
<%--            <div class="layui-input-block">--%>
<%--                <input type="text" id="cityName" name="cityName" readonly placeholder="请输入市名" autocomplete="off" class="layui-input">--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="layui-form-item">--%>
<%--            <label class="layui-form-label">县名:</label>--%>
<%--            <div class="layui-input-block">--%>
<%--                <input type="text" id="countyName" name="countyName" readonly placeholder="请输入县名" autocomplete="off" class="layui-input">--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="layui-form-item">--%>
<%--            <div class="layui-input-block" style="text-align: center;padding-right: 120px">--%>
<%--                <button type="button"--%>
<%--                        class="layui-btn layui-btn-danger layui-btn-md layui-icon layui-icon-release layui-btn-radius"--%>
<%--                        lay-filter="doSubmit2" lay-submit="">删除--%>
<%--                </button>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </form>--%>
</div>
<%--查看div--%>
<div id="viewNewsDiv" style="padding: 10px;display: none">

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
            elem: '#publishTime',
            type: 'date'
            , trigger: 'click'// 增加这个参数解决
        });
        laydate.render({
            elem: '#returnTime',
            type: 'datetime'
            , trigger: 'click'// 增加这个参数解决
        });

        //渲染数据表格
        tableIns = table.render({
            elem: '#newsTable'   //渲染的目标对象
            , url: '/species/speciesList.action' //数据接口
            , title: '种目分类信息'//数据导出来的标题
            , toolbar: "#newsToolBar"   //表格的工具条
            , height: 'full-220'
            , cellMinWidth: 120 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                {field: 'speciesId', title: '种目编号', align: 'center'}
                ,{field: 'speciesName', title: '种目名称', align: 'center'}
                , {field: 'genusName', title: '属目名称', align: 'center'}
                ,{field: 'familyName', title: '科目名称', align: 'center'}
                ,{field: 'feature', title: '形态特征', align: 'center'}
                ,{field: 'value', title: '应用价值', align: 'center'}
                ,{field: 'point', title: '栽培技术要点', align: 'center'}
                ,{field: 'environment', title: '生长环境', align: 'center'}
                , {fixed: 'right', title: '操作', toolbar: '#newsBar', align: 'center', width: 270}
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
                url: "/species/speciesList.action?" + params,
                page: {curr: 1}
            })
        });

        upload.render({
            elem: '#mobileTest1',
            url: '/file/uploadFile.action',
            method: "post",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
            acceptMime: 'images/*',
            field: "mf",
            done: function (res, index, upload) {
                console.log(res.data)
                $('#mobileCoverImg').attr('src', "/file/downloadFile.action?path=" + res.data.src);
                $('#img').val(res.data.src);
            }
        });

        //监听头部工具栏事件
        table.on("toolbar(newsTable)", function (obj) {
            switch (obj.event) {
                case 'add':
                    openAddNews();
                    break;
            }
        });
        //监听行工具事件
        table.on('tool(newsTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

            if (layEvent === 'del') { //删除
                layer.confirm('真的删除【' + data.speciesName+ '】这个植物以及对应的信息么？', function (index) {
                    //向服务端发送删除指令
                    $.post("/species/deleteSpecies.action", {speciesId: data.speciesId}, function (res) {
                        layer.msg(res.msg);
                        //刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if (layEvent === 'edit') { //修改
                //修改，打开修改界面
                openUpdateNews(data);
            }else if(layEvent=='add')
            {
                //增加，打开增加界面
                openAddNews(data);
            }else if(layEvent=='region')
            {
                //打开分布区域页面
                viewNews(data);
            }
        });
        var url;
        var mainIndex;
        //打开添加框
        function openAddNews() {
            mainIndex = layer.open({
                type: 1,
                title: '添加科目信息',
                content: $("#saveOrUpdateDiv"),
                area: ['800px', '540px'],
                success: function (index) {
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    url = "/species/addSpecies.action";
                }
            });
        }
        //查看分布区域
        function viewNews(data) {
            $.ajax({
                url:"/species/showRegion.action",
                type: 'POST',
                data: { 'speciesId': data.speciesId },
                success: function(data) {
                    var content = '<div align="center">';
                    content += '<table style="width: 50%; border: 1px solid #dddddd; border-collapse: collapse; margin-top: 20px;">';
                    content += '<thead>';
                    content += '<tr style="background-color: #f2f2f2;">';
                    content += '<th style="border: 1px solid #dddddd; padding: 8px; white-space: nowrap;">省名</th>';
                    content += '<th style="border: 1px solid #dddddd; padding: 8px; white-space: nowrap;">市名</th>';
                    content += '<th style="border: 1px solid #dddddd; padding: 8px; white-space: nowrap;">县名</th>';
                    content += '</tr>';
                    content += '</thead>';
                    content += '<tbody>';
                    data.forEach(function(regionInfo) {
                        content += '<tr>';
                        content += '<td style="border: 1px solid #dddddd; padding: 8px; white-space: nowrap;">' + regionInfo.provinceName + '</td>';
                        content += '<td style="border: 1px solid #dddddd; padding: 8px; white-space: nowrap;">' + regionInfo.cityName + '</td>';
                        content += '<td style="border: 1px solid #dddddd; padding: 8px; white-space: nowrap;">' + regionInfo.countyName + '</td>';
                        content += '</tr>';
                    });
                    content += '</tbody>';
                    content += '</table>';
                    content += '</div>';
                    layer.open({
                        type: 1,
                        title: '分布区域',
                        area: ['400px', 150+data.length*50+'px'], // 宽高
                        content: content
                    });
                },

            });
        }
    });

        //打开修改页面
        function openUpdateNews(data) {
            mainIndex = layer.open({
                type: 1,
                title: '修改科目信息',
                content: $("#saveOrUpdateDiv"),
                area: ['800px', '540px'],
                success: function (index) {
                    form.val("dataFrm", data);
                    // $('#mobileCoverImg').attr('src', "/file/downloadFile.action?path=" + data.img);
                    url = "/species/updateSpecies.action";
                }
            });
        }
        //保存
        form.on("submit(doSubmit)", function (obj) {
            //序列化表单数据
            var params = $("#dataFrm").serialize();
            $.post(url, params, function (obj) {
                layer.msg(obj.msg);
                //关闭弹出层
                layer.close(mainIndex);
                //刷新数据 表格
                tableIns.reload();
            })
        });
        //保存
        form.on("submit(doSubmit2)", function (obj) {
            //序列化表单数据
            var params = $("#dataFrm2").serialize();
            $.post(url, params, function (obj) {
                layer.msg(obj.msg);
                //关闭弹出层
                layer.close(mainIndex);
                //刷新数据 表格
                tableIns.reload();
            })
    });
</script>
<style type="text/css">
    .layui-table img {
        max-width: 100%
    }
</style>
</body>
</html>
