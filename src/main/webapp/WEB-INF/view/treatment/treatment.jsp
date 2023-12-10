<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <meta charset="utf-8">
  <title>防治任务</title>
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
      <label class="layui-form-label">任务编号:</label>
      <div class="layui-input-inline" style="padding: 5px">
        <input type="text" name="treatmentId" autocomplete="off" class="layui-input layui-input-inline"
               placeholder="请输入任务编号" style="height: 30px;border-radius: 10px">
      </div>
    </div>
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
               placeholder="请输入形态特点" style="height: 30px;border-radius: 10px">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">病虫害名称:</label>
      <div class="layui-input-inline" style="padding: 5px">
        <input type="text" name="diseaseName" autocomplete="off" class="layui-input layui-input-inline"
               placeholder="请输入病虫害名称" style="height: 30px;border-radius: 10px">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">请输入养护人员用户名:</label>
      <div class="layui-input-inline" style="padding: 5px">
        <input type="text" name="loginName" autocomplete="off" class="layui-input layui-input-inline"
               placeholder="请输入用户名" style="height: 30px;border-radius: 10px">
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">养护状态:</label>
      <div class="layui-input-inline" style="padding: 5px">
        <input type="text" name="status" autocomplete="off" class="layui-input layui-input-inline"
               placeholder="0-正在进行中 1-已结束" style="height: 30px;border-radius: 10px">
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

</div>
<!-- 行工具栏 -->
<div id="newsBar" style="display: none;">
  <c:if test="${role==2}">
    {{# if(d.status==0){  }}
    <a class="layui-btn layui-btn-xs layui-btn-radius" lay-event="edit">修改</a>
    {{#  }}}
    <a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-radius" lay-event="del">删除</a>
  </c:if>

  <c:if test="${role==3}">
    {{# if(d.status==0){  }}
    <a class="layui-btn layui-btn-warm layui-btn-xs layui-btn-radius" lay-event="updateStatus">已养护</a>
    {{#  }}}
  </c:if>
</div>
<!-- 添加/修改药剂的弹出层-->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
  <form class="layui-form" lay-filter="dataFrm" id="dataFrm" style="margin-right: 20px">
    <div class="layui-form-item">
      <label class="layui-form-label">任务编号:</label>
      <div class="layui-input-block">
        <input type="text" id="treatmentId" name="diseaseId" readonly placeholder="请输入植物编号" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">植物编号:</label>
      <div class="layui-input-block">
        <input type="text" name="plantId" placeholder="请输入植物名称" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">药剂编号:</label>
      <div class="layui-input-block">
        <input type="text" name="medicamentId" placeholder="请输入养护人员编号" autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">创建时间:</label>
      <div class="layui-input-block">
        <input type="text" id="createTime" name="duration" placeholder="请输入植物名称" autocomplete="off" class="layui-input">
      </div>
    </div>

    <div class="layui-form-item">
      <label class="layui-form-label">更新时间:</label>
      <div class="layui-input-block">
        <input type="text" id="updateTime" name="updateTime" readonly placeholder="请输入形态特征" autocomplete="off" class="layui-input" value="${uid}">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">养护人员编号:</label>
      <div class="layui-input-block">
        <input type="text" name="userId" placeholder="请输入形态特征" autocomplete="off" class="layui-input" value="${uid}">
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
      , url: '/disease/treatmentInfoList.action' //数据接口
      , title: '植物信息'//数据导出来的标题
      , toolbar: "#newsToolBar"   //表格的工具条
      , height: 'full-185'
      , cellMinWidth: 100 //设置列的最小默认宽度
      , page: true  //是否启用分页
      , cols: [[   //列表数据
        {field: 'treatmentId', title: '任务编号', align: 'center'}
        ,{field: 'plantId', title: '植物编号', align: 'center'}
        , {field: 'plantName', title: '植物名称', align: 'center'}
        , {field: 'diseaseName', title: '病虫害', align: 'center'}
        , {field: 'medicamentName', title: '使用药剂', align: 'center'}
        , {field: 'dosage', title: '用量', align: 'center'}
        , {field: 'duration', title: '作用期限', align: 'center'}
        , {field: 'createTime', title: '创建时间', align: 'center'}
        , {field: 'updateTime', title: '更新时间', align: 'center'}
        , {field: 'loginName', title: '养护人员', align: 'center'}
        , {field: 'status', title: '养护状态', align: 'center',templet: function (d) {
            return d.status == 0 ? '正在进行中' : '已结束';
          }}
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
        url: "/disease/treatmentInfoList.action?" + params,
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
        layer.confirm('真的删除任务【' + data.treatmentId + '】吗？', function (index) {
          //向服务端发送删除指令
          $.post("/disease/deleteTreatment.action", {treatmentId: data.treatmentId}, function (res) {
            layer.msg(res.msg);
            //刷新数据表格
            tableIns.reload();
          })
        });
      } else if (layEvent === 'edit') { //修改
        //修改，打开修改界面
        openUpdate(data);
      }else if(layEvent == 'updateStatus') {
        updateStatus(data.treatmentId,1);
      }
    });

    function updateStatus(treatmentId,status){
        layer.confirm('是否确认该操作？',function (index){
          $.post("/disease/updateStatus.action",{treatmentId:treatmentId,status:status},function (res){
              layer.msg(res.msg);

              tableIns.reload();
          })
        });
    }



    var url;
    var mainIndex;

    //打开修改页面
    function openUpdate(data) {
      mainIndex = layer.open({
        type: 1,
        title: '修改防治信息',
        content: $("#saveOrUpdateDiv"),
        area: ['800px', '540px'],
        success: function (index) {
          form.val("dataFrm", data);
          // $('#mobileCoverImg').attr('src', "/file/downloadFile.action?path=" + data.img);
          url = "/disease/updateTreatment.action";
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
    form.on("submit(doSubmit1)", function (obj) {
      //序列化表单数据
      var params = $("#dataFrm1").serialize();
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
  .layui-table img{
    max-width:100%
  }
</style>

</body>
</html>
