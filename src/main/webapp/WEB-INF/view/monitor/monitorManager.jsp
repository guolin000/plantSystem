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
	<title>监测管理</title>
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
			<label class="layui-form-label">监测记录编号:</label>
			<div class="layui-input-inline" style="padding: 5px">
				<input type="text" name="recordId" autocomplete="off" class="layui-input layui-input-inline"
					   placeholder="请输入监测记录编号" style="height: 30px;border-radius: 10px">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">监测设备:</label>
			<div class="layui-input-inline" style="padding: 5px">
				<input type="text" name="equipmentName" autocomplete="off" class="layui-input layui-input-inline"
					   placeholder="请输入监测设备" style="height: 30px;border-radius: 10px">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">监测人员:</label>
			<div class="layui-input-inline" style="padding: 5px">
				<input type="text" name="loginName" autocomplete="off" class="layui-input layui-input-inline"
					   placeholder="请输入监测人员" style="height: 30px;border-radius: 10px">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">监测时间:</label>
			<div class="layui-input-inline" style="padding: 5px">
				<input type="date" name="monitorTime" autocomplete="off" class="layui-input layui-input-inline"
					   placeholder="请输入监测时间" style="height: 30px;border-radius: 10px">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">监测地点:</label>
			<div class="layui-input-inline" style="padding: 5px">
				<input type="text" name="monitorSite" autocomplete="off" class="layui-input layui-input-inline"
					   placeholder="请输入监测地点" style="height: 30px;border-radius: 10px">
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
	<c:if test="${true}"> <!--role == 2 || role == 4-->
		<button type="button" class="layui-btn layui-btn-sm layui-btn-radius" lay-event="add">新增</button>
		<button type="button" class="layui-btn layui-btn-sm layui-btn-radius" lay-event="import" id="import">批量导入</button>
	</c:if>
</div>

<div id="newsBar" style="display: none;">
	<c:if test="${true}"> <!--role == 2 || role == 4-->
		<a class="layui-btn layui-btn-normal layui-btn-xs layui-btn-radius" lay-event="addValue">录入</a>
		<a class="layui-btn layui-btn-warm layui-btn-xs layui-btn-radius" lay-event="viewNews">查看</a>
		<a class="layui-btn layui-btn-xs layui-btn-radius" lay-event="edit">修改</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-radius" lay-event="del">删除</a>
	</c:if>
</div>

<!-- 添加和修改的弹出层-->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
	<form class="layui-form" lay-filter="dataFrm" id="dataFrm" style="margin-right: 20px">
		<div class="layui-form-item">
			<label class="layui-form-label">监测记录编号:</label>
			<div class="layui-input-block">
				<input type="text" name="recordId" placeholder="请输入监测记录编号" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">监测设备:</label>
			<div class="layui-input-block">
				<input type="text" name="equipmentId" placeholder="请输入监测设备(1-4)" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">监测人员:</label>
			<div class="layui-input-block">
				<input type="text" name="userId" placeholder="请输入监测人员(1-4)" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">监测时间:</label>
			<div class="layui-input-block">
				<input type="date" name="monitorTime" placeholder="请输入监测时间" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">监测地点:</label>
			<div class="layui-input-block">
				<input type="text" name="monitorSite" placeholder="请输入监测地点" autocomplete="off" class="layui-input">
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

<%--查看div--%>
<div id="viewNewsDiv" style="padding: 10px;display: none">

</div>

<%--录入div--%>
<div style="display: none;padding: 20px" id="addValueDiv">
	<form class="layui-form" lay-filter="dataFrm" id="valueFrm" style="margin-right: 20px">
		<div class="layui-form-item">
			<label class="layui-form-label">监测记录编号:</label>
			<div class="layui-input-block">
				<input type="text" name="recordId" placeholder="请输入监测记录编号" autocomplete="off" class="layui-input" readonly="readonly">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">植物编号:</label>
			<div class="layui-input-block">
				<input type="text" name="plantId" placeholder="请输入植物编号(1001-1010)" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">监测指标编号:</label>
			<div class="layui-input-block">
				<input type="text" name="indicatorId" placeholder="请输入监测指标编号(1-4)" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">监测指标值:</label>
			<div class="layui-input-block">
				<input type="text" name="indicatorValue" placeholder="监测指标值" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block" style="text-align: center;padding-right: 120px">
				<button type="button"
						class="layui-btn layui-btn-normal layui-btn-md layui-icon layui-icon-release layui-btn-radius"
						lay-filter="doSubmit2" lay-submit="">提交
				</button>
				<button type="reset" id="valueFrmResetBtn"
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
			, url: '${pageContext.request.contextPath}/monitorRecordList.action' //数据接口
			, title: '监测记录'//数据导出来的标题
			, toolbar: "#newsToolBar"   //表格的工具条
			, height: 'full-175'
			, cellMinWidth: 120 //设置列的最小默认宽度
			, page: true  //是否启用分页
			, cols: [[   //列表数据
				{field: 'recordId', title: '监测记录编号', align: 'center'}
				, {field: 'equipmentName', title: '监测设备', align: 'center'}
				, {field: 'loginName', title: '监测人员', align: 'center'}
				, {field: 'monitorTime', title: '监测时间', align: 'center'}
				, {field: 'monitorSite', title: '监测地点', align: 'center'}
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
				url: "monitorRecordList.action?" + params,
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
				layer.confirm('真的删除【' + data.recordId + '】这条记录么？', function (index) {
					//向服务端发送删除指令
					$.post("deleteMonitorRecord.action", {recordId: data.recordId}, function (res) {
						layer.msg(res.msg);
						//刷新数据表格
						tableIns.reload();
					})
				});
			} else if (layEvent === 'edit') { //修改
				//修改，打开修改界面
				openUpdateNews(data);
			} else if (layEvent === 'viewNews') {//查看
				viewNews(data);
			} else if (layEvent === 'addValue') {//录入
				openAddValue(data);
			}
		});

		var url;
		var mainIndex;

		//打开添加框
		function openAddNews() {
			mainIndex = layer.open({
				type: 1,
				title: '添加监测记录',
				content: $("#saveOrUpdateDiv"),
				area: ['800px', '540px'],
				success: function (index) {
					//清空表单数据
					$("#dataFrm")[0].reset();
					url = "addMonitorRecord.action";
				}
			});
		}

		//打开修改页面
		function openUpdateNews(data) {
			mainIndex = layer.open({
				type: 1,
				title: '修改监测记录',
				content: $("#saveOrUpdateDiv"),
				area: ['800px', '540px'],
				success: function (index) {
					form.val("dataFrm", data);
					url = "updateMonitorRecord.action";
				}
			});
		}

		//打开录入监测指标值页面
		function openAddValue(data) {
			mainIndex = layer.open({
				type: 1,
				title: '录入监测指标值',
				content: $("#addValueDiv"),
				area: ['800px', '540px'],
				success: function (index) {
					form.val("dataFrm", data);
					url = "addMonitorValue.action";
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


		//保存
		form.on("submit(doSubmit2)", function (obj) {
			//序列化表单数据
			var params = $("#valueFrm").serialize();
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
			$.ajax({
				url:"checkMonitorValue.action",
				type: 'POST',
				data: { 'recordId': data.recordId },
				success: function(data) {
					var content = '<div align="center">';
					content += '<table style="width: 50%; border: 1px solid #dddddd; border-collapse: collapse; margin-top: 20px;">';
					content += '<thead>';
					content += '<tr style="background-color: #f2f2f2;">';
					content += '<th style="border: 1px solid #dddddd; padding: 8px; white-space: nowrap;">植物</th>';
					content += '<th style="border: 1px solid #dddddd; padding: 8px; white-space: nowrap;">监测指标</th>';
					content += '<th style="border: 1px solid #dddddd; padding: 8px; white-space: nowrap;">监测指标值</th>';
					content += '<th style="border: 1px solid #dddddd; padding: 8px; white-space: nowrap;">操作</th>';
					content += '</tr>';
					content += '</thead>';
					content += '<tbody>';
					data.forEach(function(monitorValueInfo) {
						content += '<tr>';
						content += '<td style="border: 1px solid #dddddd; padding: 8px; white-space: nowrap;">' + monitorValueInfo.plantName + '</td>';
						content += '<td style="border: 1px solid #dddddd; padding: 8px; white-space: nowrap;">' + monitorValueInfo.indicatorName + '</td>';
						content += '<td style="border: 1px solid #dddddd; padding: 8px; white-space: nowrap;">' + monitorValueInfo.indicatorValue + '</td>';
						content += '<td style="border: 1px solid #dddddd; padding: 8px; white-space: nowrap;"><button class="layui-btn layui-btn-danger" onclick="deleteMonitorValue(' + monitorValueInfo.recordId + ',' + monitorValueInfo.plantId + ',' + monitorValueInfo.indicatorId + ')">删除</button></td>';
						content += '</tr>';
					});
					content += '</tbody>';
					content += '</table>';
					content += '</div>';
					layer.open({
						type: 1,
						title: '监测记录指标值',
						area: ['400px', 150+data.length*50+'px'], // 宽高
						content: content
					});
				},

			});
		}
	});

	function addMonitorValue(recordId,plantId,indicatorId,indicatorValue) {
		console.log(recordId);
		console.log(plantId);
		console.log(indicatorId);
		console.log(indicatorValue);
		layui.jquery.ajax({
			url: 'addMonitorValue.action', // 替换为你的删除接口
			type: 'POST',
			data: {
				'recordId': recordId,
				'plantId': plantId,
				'indicatorId': indicatorId,
				'indicatorValue': indicatorValue
			},
			success: function(response) {
				alert('添加成功');
				document.location.reload();//当前页面
			},
			error: function(error) {
				alert('添加失败');
			}
		});
	}

	function deleteMonitorValue(recordId,plantId,indicatorId) {
		layui.jquery.ajax({
			url: 'deleteMonitorValue.action', // 替换为你的删除接口
			type: 'POST',
			data: {
				'recordId': recordId,
				'plantId': plantId,
				'indicatorId': indicatorId,
			},
			success: function(response) {
				alert('删除成功');
				document.location.reload();//当前页面
			},
			error: function(error) {
				alert('删除失败');
			}
		});
	}

</script>
<style type="text/css">
	.layui-table img{
		max-width:100%
	}
</style>
</body>
</html>
