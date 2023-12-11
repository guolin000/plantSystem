<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset="utf-8">
  <title>省份管理</title>
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
      <label class="layui-form-label">省份编号:</label>
      <div class="layui-input-inline" style="padding: 5px">
        <input type="text" name="provinceId" autocomplete="off" class="layui-input layui-input-inline"
               placeholder="请输入科目编号" style="height: 30px;border-radius: 10px">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">省份名称:</label>
      <div class="layui-input-inline" style="padding: 5px">
        <input type="text" name="provinceName" autocomplete="off" class="layui-input layui-input-inline"
               placeholder="请输入科目名称" style="height: 30px;border-radius: 10px">
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
<div style="display: none;" id="newsToolBar">
  <c:if test="${role==1}">
    <button type="button" class="layui-btn layui-btn-sm layui-btn-radius" lay-event="add">新增省份</button>
  </c:if>
</div>

<div id="newsBar" style="display: none;">
  <c:if test="${role==1}">
    <a class="layui-btn layui-btn-xs layui-btn-radius" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-radius" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs layui-btn-radius" lay-event="genus">增加市</a>
  </c:if>
</div>
<!-- 修改省份的弹出层-->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
  <form class="layui-form" lay-filter="dataFrm" id="dataFrm" style="margin-right: 20px">
    <div class="layui-form-item">
      <label class="layui-form-label">省份编号:</label>
      <div class="layui-input-block">
        <input type="text" name="provinceId" readonly placeholder="请输入科目编号" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">省份名称:</label>
      <div class="layui-input-block">
        <input type="text" name="provinceName" placeholder="请输入科目名称" autocomplete="off" class="layui-input">
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
<!-- 添加市的弹出层-->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv2">
  <form class="layui-form" lay-filter="dataFrm2" id="dataFrm2" style="margin-right: 20px">
    <div class="layui-form-item">
      <label class="layui-form-label">省份编号:</label>
      <div class="layui-input-block">
        <input type="text" id="provinceId" readonly name="provinceId" placeholder="请输入科目编号" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">市编号:</label>
      <div class="layui-input-block">
        <input type="text" id="cityId" name="genusId" placeholder="请输入市编号" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">市名称:</label>
      <div class="layui-input-block">
        <input type="text" id="cityName" name="genusName" placeholder="请输入市名称" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <div class="layui-input-block" style="text-align: center;padding-right: 120px">
        <button type="button"
                class="layui-btn layui-btn-normal layui-btn-md layui-icon layui-icon-release layui-btn-radius"
                lay-filter="doSubmit2" lay-submit="">提交
        </button>
        <button type="reset" id="dataFrmResetBtn2"
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

    //渲染数据表格
    tableIns = table.render({
      elem: '#newsTable'   //渲染的目标对象
      , url: '.action' //数据接口
      , title: '植物分类信息'//数据导出来的标题
      , toolbar: "#newsToolBar"   //表格的工具条
      , height: 'full-220'
      , cellMinWidth: 120 //设置列的最小默认宽度
      , page: true  //是否启用分页
      , cols: [[   //列表数据
        {field: 'provinceId', title: '省份编号', align: 'center'}
        , {field: 'provinceName', title: '省份名称', align: 'center'}
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
        url: ".action?" + params,
        page: {curr: 1}
      })
    });

    //监听头部工具栏事件
    table.on("toolbar(newsTable)", function (obj) {
      switch (obj.event) {
        case 'add':
          openAddProvince();
          break;
      }
    });
    //监听行工具事件
    table.on('tool(newsTable)', function (obj) {
      var data = obj.data; //获得当前行数据
      var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

      if (layEvent === 'del') { //删除
        layer.confirm('真的删除【' + data.provinceName+ '】这个省份以及对应的信息么？', function (index) {
          //向服务端发送删除指令
          $.post(".action", {familyId: data.familyId}, function (res) {
            layer.msg(res.msg);
            //刷新数据表格
            tableIns.reload();
          })
        });
      } else if (layEvent === 'edit') { //修改
        //修改，打开修改界面
        openUpdateNews(data);
      }else if(layEvent='genus')
      {
        //打开添加市的页面
        openGenusNews(data);
      }
    });

    var url;
    var mainIndex;

    //打开添加省的框
    function openAddNews() {
      mainIndex = layer.open({
        type: 1,
        title: '添加科目信息',
        content: $("#saveOrUpdateDiv"),
        area: ['800px', '540px'],
        success: function (index) {
          //清空表单数据
          $("#dataFrm")[0].reset();
          url = "/sort/addFamily.action";
        }
      });
    }

    //打开市的添加框
    function openGenusNews(data) {
      mainIndex = layer.open({
        type: 1,
        title: '添加市信息',
        content: $("#saveOrUpdateDiv2"),
        area: ['800px', '540px'],
        success: function (index) {
          $("#provinceId").val(data.provinceId);
          url = "/sort/.action";
        }
      });
    }

    //打开修改框
    function openUpdateNews(data) {
      mainIndex = layer.open({
        type: 1,
        title: '修改省份信息',
        content: $("#saveOrUpdateDiv"),
        area: ['800px', '540px'],
        success: function (index) {
          form.val("dataFrm", data);
          // $('#mobileCoverImg').attr('src', "/file/downloadFile.action?path=" + data.img);
          url = ".action";
        }
      });
    }
    //添加省份提交
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

    //添加市提交
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
  });

</script>
<style type="text/css">
  .layui-table img {
    max-width: 100%
  }
</style>
</body>
</html>
