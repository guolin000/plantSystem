<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <meta charset="utf-8">
  <title>药剂信息列表</title>
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
      <label class="layui-form-label">科编号:</label>
      <div class="layui-input-inline" style="padding: 5px">
        <input type="text" name="familyId" autocomplete="off" class="layui-input layui-input-inline"
               placeholder="请输入科编号" style="height: 30px;border-radius: 10px">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">科名:</label>
      <div class="layui-input-inline" style="padding: 5px">
        <input type="text" name="familyName" autocomplete="off" class="layui-input layui-input-inline"
               placeholder="请输入科名" style="height: 30px;border-radius: 10px">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">植物数量:</label>
      <div class="layui-input-inline" style="padding: 5px">
        <input type="text" name="plantNum" autocomplete="off" class="layui-input layui-input-inline"
               placeholder="请输入植物数量" style="height: 30px;border-radius: 10px">
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
      ,trigger: 'click'// 增加这个参数解决
    });
    laydate.render({
      elem: '#returnTime',
      type: 'datetime'
      ,trigger: 'click'// 增加这个参数解决
    });

    //渲染数据表格
    tableIns = table.render({
      elem: '#newsTable'   //渲染的目标对象
      , url: '/plant/familyPlantCounts.action' //数据接口
      , title: '植物信息'//数据导出来的标题
      , toolbar: "#newsToolBar"   //表格的工具条
      , height: 'full-155'
      , cellMinWidth: 120 //设置列的最小默认宽度
      , page: true  //是否启用分页
      , cols: [[   //列表数据
        {field: 'familyId', title: '科编号', align: 'center',width:350}
        , {field: 'familyName', title: '科名', align: 'center',width: 350}
        , {field: 'plantNum', title: '植物数量', align: 'center',width: 350}
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
        url: "/plant/familyPlantCounts.action?" + params,
        page: {curr: 1}
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
