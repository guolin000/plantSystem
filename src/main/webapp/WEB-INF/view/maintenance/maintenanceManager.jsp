<!doctype html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<head>
	<meta charset="utf-8">
	<title>养护管理</title>
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
			<label class="layui-form-label">养护任务编号:</label>
			<div class="layui-input-inline" style="padding: 5px">
				<input type="text" name="taskId" autocomplete="off" class="layui-input layui-input-inline"
					   placeholder="请输入养护任务编号" style="height: 30px;border-radius: 10px">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">任务名称:</label>
			<div class="layui-input-inline" style="padding: 5px">
				<input type="text" name="taskName" autocomplete="off" class="layui-input layui-input-inline"
					   placeholder="请输入任务名称" style="height: 30px;border-radius: 10px">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">任务描述:</label>
			<div class="layui-input-inline" style="padding: 5px">
				<input type="text" name="taskDescription" autocomplete="off" class="layui-input layui-input-inline"
					   placeholder="请输入任务描述" style="height: 30px;border-radius: 10px">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">养护时间:</label>
			<div class="layui-input-inline" style="padding: 5px">
				<input type="date" name="maintenanceTime" autocomplete="off" class="layui-input layui-input-inline"
					   placeholder="请输入养护时间" style="height: 30px;border-radius: 10px">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">养护地点:</label>
			<div class="layui-input-inline" style="padding: 5px">
				<input type="text" name="maintenanceSite" autocomplete="off" class="layui-input layui-input-inline"
					   placeholder="请输入养护地点" style="height: 30px;border-radius: 10px">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">养护人员:</label>
			<div class="layui-input-inline" style="padding: 5px">
				<input type="text" name="maintainerName" autocomplete="off" class="layui-input layui-input-inline"
					   placeholder="请输入养护人员" style="height: 30px;border-radius: 10px">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">养护对象:</label>
			<div class="layui-input-inline" style="padding: 5px">
				<input type="text" name="plantName" autocomplete="off" class="layui-input layui-input-inline"
					   placeholder="请输入养护对象" style="height: 30px;border-radius: 10px">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">创建人员:</label>
			<div class="layui-input-inline" style="padding: 5px">
				<input type="text" name="creatorName" autocomplete="off" class="layui-input layui-input-inline"
					   placeholder="请输入创建人员" style="height: 30px;border-radius: 10px">
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
	<c:if test="${true}"> <!--role == 2 || role == 3-->
		<button type="button" class="layui-btn layui-btn-sm layui-btn-radius" lay-event="add">新增</button>
		<button type="button" class="layui-btn layui-btn-sm layui-btn-radius" lay-event="import" id="import">批量导入</button>
	</c:if>
</div>

<div id="newsBar" style="display: none;">
	<c:if test="${true}"> <!--role == 2 || role == 3-->
		<a class="layui-btn layui-btn-warm layui-btn-xs layui-btn-radius" lay-event="viewNews">完成</a>
		<a class="layui-btn layui-btn-xs layui-btn-radius" lay-event="edit">修改</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-radius" lay-event="del">删除</a>
	</c:if>
</div>

<!-- 添加和修改的弹出层-->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
	<form class="layui-form" lay-filter="dataFrm" id="dataFrm" style="margin-right: 20px">
		<div class="layui-form-item">
			<label class="layui-form-label">养护任务编号:</label>
			<div class="layui-input-block">
				<input type="text" name="taskId" placeholder="请输入养护任务编号" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">任务名称:</label>
			<div class="layui-input-block">
				<input type="text" name="taskName" placeholder="请输入任务名称" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">任务描述:</label>
			<div class="layui-input-block">
				<input type="text" name="taskDescription" placeholder="请输入任务名称" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">养护时间:</label>
			<div class="layui-input-block">
				<input type="date" name="maintenanceTime" placeholder="请输入养护时间" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">养护地点:</label>
			<div class="layui-input-block">
				<input type="text" name="maintenanceSite" placeholder="请输入养护地点" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">养护人员:</label>
			<div class="layui-input-block">
				<input type="text" name="uid" placeholder="请输入养护人员(1-4)" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">养护对象:</label>
			<div class="layui-input-block">
				<input type="text" name="plantId" placeholder="请输入养护对象(1001-1010)" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">创建人员:</label>
			<div class="layui-input-block">
				<input type="text" name="creator" placeholder="请输入创建人员(1-4)" autocomplete="off" class="layui-input">
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

<%--详情div--%>
<div id="viewNewsDiv" style="padding: 10px;display: none">
	<form class="layui-form" lay-filter="dataFrm2" id="dataFrm2" style="margin-right: 20px">

		<div class="layui-form-item" style="margin-top: 20px">
			<label class="layui-form-label">详情</label>
		</div>

	</form>
</div>
<!--plugins-->
<%--分页以及模糊查询登录不可删除--%>
<script src="assets/plugins/datatable/js/jquery.dataTables.min.js"></script>
<script src="assets/plugins/datatable/js/dataTables.bootstrap5.min.js"></script>
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
			, url: '${pageContext.request.contextPath}/maintenanceTaskList.action' //数据接口
			, title: '养护任务'//数据导出来的标题
			, toolbar: "#newsToolBar"   //表格的工具条
			, height: 'full-175'
			, cellMinWidth: 120 //设置列的最小默认宽度
			, page: true  //是否启用分页
			, cols: [[   //列表数据
				{field: 'taskId', title: '养护任务编号', align: 'center'}
				, {field: 'taskName', title: '任务名称', align: 'center'}
				, {field: 'taskDescription', title: '任务描述', align: 'center'}
				, {field: 'maintenanceTime', title: '养护时间', align: 'center'}
				, {field: 'maintenanceSite', title: '养护地点', align: 'center'}
				, {field: 'maintainerName', title: '养护人员', align: 'center'}
				, {field: 'plantName', title: '养护对象', align: 'center'}
				, {field: 'creatorName', title: '创建人员', align: 'center'}
				, {field: 'creationTime', title: '创建时间', align: 'center'}
				, {field: 'updateTime', title: '更新时间', align: 'center'}
				, {field: 'taskStatus', title: '任务状态', align: 'center',templet: function (d) {
						return d.taskStatus === 0 ? '未完成' : '已完成';
					}}
				, {fixed: 'right', title: '操作', toolbar: '#newsBar', align: 'center', width: 220}
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
				url: "maintenanceTaskList.action?" + params,
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
		// 导入文件
		upload.render({
			elem: '#import',
			url: '/file/importExcel.action',
			method: "post",
			accept: 'file',
			exts: 'xls|excel|xlsx' ,  //只允许excel
			done: function (res, index, upload) {
				console.log(res.data)
				layer.msg(res.msg);
				//刷新数据 表格
				tableIns.reload();
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
				layer.confirm('真的删除【' + data.taskId + '】这项任务么？', function (index) {
					//向服务端发送删除指令
					$.post("deletePlant", {taskId: data.taskId}, function (res) {
						layer.msg(res.msg);
						//刷新数据表格
						tableIns.reload();
					})
				});
			} else if (layEvent === 'edit') { //修改
				//修改，打开修改界面
				openUpdateNews(data);
			} else if (layEvent === 'viewNews') {//查看
				// viewNews(data);
				completeTask(data);
			}
		});

		var url;
		var mainIndex;

		//打开添加框
		function openAddNews() {
			mainIndex = layer.open({
				type: 1,
				title: '添加养护任务',
				content: $("#saveOrUpdateDiv"),
				area: ['800px', '540px'],
				success: function (index) {
					//清空表单数据
					$("#dataFrm")[0].reset();
					url = "addMaintenanceTask.action";
				}
			});
		}

		//打开修改页面
		function openUpdateNews(data) {
			mainIndex = layer.open({
				type: 1,
				title: '修改养护任务',
				content: $("#saveOrUpdateDiv"),
				area: ['800px', '540px'],
				success: function (index) {
					form.val("dataFrm", data);
					url = "updateMaintenanceTask.action";
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


		//查看
		function viewNews(data) {
			mainIndex = layer.open({
				type: 1,
				title: '查看养护任务',
				content: $("#viewNewsDiv"),
				area: ['700px', '540px'],
				success: function (index) {
					form.val("dataFrm2", data);
					$('#mobileCoverImg1').attr('src', "/file/downloadFile.action?path=" + data.img);
				}
			});
		}


		//完成
		function completeTask(taskId){
			if(confirm("确定完成?")){
				$.ajax({
					url:"completeMaintenanceTask",
					data:{"taskId": taskId},
					type:"post",
					dataType:"json",
					success:function (data) {
						if (data) {
							alert("完成成功");
							document.location.reload();//当前页面
						}else{
							alert("完成失败");
						}
					}
				});
			}
		}

	});

</script>
<style type="text/css">
	.layui-table img{
		max-width:100%
	}
</style>
</body>
</html>
