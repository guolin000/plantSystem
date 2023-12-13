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
			<label class="layui-form-label">指标名称:</label>
			<div class="layui-input-inline" style="padding: 5px">
				<input type="text" name="indicatorName" autocomplete="off" class="layui-input layui-input-inline"
					   placeholder="请输入指标名称" style="height: 30px;border-radius: 10px">
			</div>
		</div>
		<div class="layui-inline">
			<label class="layui-form-label">植物名称:</label>
			<div class="layui-input-inline" style="padding: 5px">
				<input type="text" name="plantName" autocomplete="off" class="layui-input layui-input-inline"
					   placeholder="请输入植物名称" style="height: 30px;border-radius: 10px">
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
	<c:if test="${true}"> <!--role == 2 || role == 4-->
		<a class="layui-btn layui-btn-normal layui-btn-xs layui-btn-radius" lay-event="viewByDay">日表</a>
		<a class="layui-btn layui-btn-warm layui-btn-xs layui-btn-radius" lay-event="viewByMonth">月表</a>
		<a class="layui-btn layui-btn-xs layui-btn-xs layui-btn-radius" lay-event="viewByYear">年表</a>
	</c:if>
</div>

<%--查看div--%>
<div class="echarts" style="display: none;width: 500px;height: 500px" id="echarts">

</div>

<script src="/resources/layui/layui.js"></script>
<script src="/resources/echarts/js/echarts.min.js"></script>
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
			, url: '${pageContext.request.contextPath}/monitorStatisticsList.action' //数据接口
			, title: '监测统计'//数据导出来的标题
			, toolbar: "#newsToolBar"   //表格的工具条
			, height: 'full-175'
			, cellMinWidth: 120 //设置列的最小默认宽度
			, page: true  //是否启用分页
			, cols: [[   //列表数据
				{field: 'indicatorId', title: '指标编号', align: 'center'}
				, {field: 'indicatorName', title: '指标名称', align: 'center'}
				, {field: 'plantId', title: '植物编号', align: 'center'}
				, {field: 'plantName', title: '植物名称', align: 'center'}
				, {field: 'averageValue', title: '平均值', align: 'center'}
				, {field: 'maximumValue', title: '最大值', align: 'center'}
				, {field: 'minimumValue', title: '最小值', align: 'center'}
				, {fixed: 'right', title: '明细', toolbar: '#newsBar', align: 'center', width: 220}
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
				url: "monitorStatisticsList.action?" + params,
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
				layer.confirm('真的删除【' + data.indicatorId + '】这条记录么？', function (index) {
					//向服务端发送删除指令
					$.post("deleteMonitorRecord.action", {indicatorId: data.indicatorId}, function (res) {
						layer.msg(res.msg);
						//刷新数据表格
						tableIns.reload();
					})
				});
			} else if (layEvent === 'edit') { //修改
				//修改，打开修改界面
				openUpdateNews(data);
			} else if (layEvent === 'viewByDay') {//查看
				viewByDay(data);
			} else if (layEvent === 'viewByMonth') {//查看
				viewByMonth(data);
			} else if (layEvent === 'viewByYear') {//查看
				viewByYear(data);
			}
		});

		var url;
		var mainIndex;

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
		function viewByDay(data) {

			layer.open({
				type: 1,
				title: '按日查看',
				area: ['500px', '550px'],
				content: $("#echarts"),
				success: function (index) {
					$.ajax({
						cache : true,
						type : "post",
						url : "selectByDay.action",
						data: {
							'indicatorId': data.indicatorId,
							'plantId': data.plantId
						},
						async : false,
						success : function(e) {

							if (e) {
								var list = eval(e);
								console.log(list);
								if(list.length > 0){

									var names = [
										'2023-12-01', '2023-12-02', '2023-12-03', '2023-12-04', '2023-12-05', '2023-12-06', '2023-12-07', '2023-12-08', '2023-12-09', '2023-12-10',
										'2023-12-11', '2023-12-12', '2023-12-13', '2023-12-14', '2023-12-15', '2023-12-16', '2023-12-17', '2023-12-18', '2023-12-19', '2023-12-20',
										'2023-12-21', '2023-12-22', '2023-12-23', '2023-12-24', '2023-12-25', '2023-12-26', '2023-12-27', '2023-12-28', '2023-12-29', '2023-12-30', '2023-12-31'
									];

									var counts = Array(names.length).fill(0); // Initialize counts array with zeros

									for (var i = 0; i < list.length; i++) {
										var index = names.indexOf(list[i].name);
										if (index !== -1) {
											counts[index] = list[i].count;
										}
									}

									echarts.init(document.getElementById('echarts')).setOption({
										title: {
											text: '统计/按日',
											left: 'center'
										},
										tooltip: {},
										xAxis: {
											type: 'category',
											data: names
										},
										yAxis: {
											type: 'value'
										},
										series: [{
											type: 'line',
											data: counts,
											// 最大值和最小值
											markPoint: {
												data: [
													{
														type: 'max'
													},
													{
														type: 'min'
													}
												]
											},
											// 平均值
											markLine: {
												data: [
													{
														type: 'average'
													}
												]
											},
											// 线条控制
											smooth: true,
											lineStyle: {
												color: 'green'
											},
											// 填充风格
											areaStyle: {
												color: 'pink'
											}
										}]
									});
								}

							} else {
								alert("查询失败");
							}
						}
					})
				},
			});
		}

		//查看
		function viewByMonth(data) {

			layer.open({
				type: 1,
				title: '按月查看',
				area: ['500px', '550px'],
				content: $("#echarts"),
				success: function (index) {
					$.ajax({
						cache : true,
						type : "post",
						url : "selectByMonth.action",
						data: {
							'indicatorId': data.indicatorId,
							'plantId': data.plantId
						},
						async : false,
						success : function(e) {
							if (e) {
								var list = eval(e);
								console.log(list);
								if(list.length > 0){

									var names = [
										// '2021-1', '2021-2', '2021-3', '2021-4', '2021-5', '2021-6', '2021-7', '2021-8', '2021-9', '2021-10', '2021-11', '2021-12',
										// '2022-1', '2022-2', '2022-3', '2022-4', '2022-5', '2022-6', '2022-7', '2022-8', '2022-9', '2022-10', '2022-11', '2022-12',
										'2023-1', '2023-2', '2023-3', '2023-4', '2023-5', '2023-6', '2023-7', '2023-8', '2023-9', '2023-10', '2023-11', '2023-12'
									];

									var counts = Array(names.length).fill(0); // Initialize counts array with zeros

									for (var i = 0; i < list.length; i++) {
										var index = names.indexOf(list[i].name);
										if (index !== -1) {
											counts[index] = list[i].count;
										}
									}

									echarts.init(document.getElementById('echarts')).setOption({
										title: {
											text: '统计/按月',
											left: 'center'
										},
										tooltip: {},
										xAxis: {
											type: 'category',
											data: names
										},
										yAxis: {
											type: 'value'
										},
										series: [{
											type: 'line',
											data: counts,
											// 最大值和最小值
											markPoint: {
												data: [
													{
														type: 'max'
													},
													{
														type: 'min'
													}
												]
											},
											// 平均值
											markLine: {
												data: [
													{
														type: 'average'
													}
												]
											},
											// 线条控制
											smooth: true,
											lineStyle: {
												color: 'green'
											},
											// 填充风格
											areaStyle: {
												color: 'pink'
											}
										}]
									});
								}

							} else {
								alert("查询失败");
							}
						}
					})
				},
			});
		}

		//查看
		function viewByYear(data) {

			layer.open({
				type: 1,
				title: '按年查看',
				area: ['500px', '550px'],
				content: $("#echarts"),
				success: function (index) {
					$.ajax({
						cache : true,
						type : "post",
						url : "selectByYear.action",
						data: {
							'indicatorId': data.indicatorId,
							'plantId': data.plantId
						},
						async : false,
						success : function(e) {
							if (e) {
								var list = eval(e);
								console.log(list);
								if(list.length > 0){

									var names = ['2019','2020', '2021', '2022','2023'];
									var counts = Array(names.length).fill(0); // Initialize counts array with zeros

									for (var i = 0; i < list.length; i++) {
										var index = names.indexOf(list[i].name);
										if (index !== -1) {
											counts[index] = list[i].count;
										}
									}

									echarts.init(document.getElementById('echarts')).setOption({
										title: {
											text: '统计/按年',
											left: 'center'
										},
										tooltip: {},
										xAxis: {
											type: 'category',
											data: names
										},
										yAxis: {
											type: 'value'
										},
										series: [{
											type: 'line',
											data: counts,
											// 最大值和最小值
											markPoint: {
												data: [
													{
														type: 'max'
													},
													{
														type: 'min'
													}
												]
											},
											// 平均值
											markLine: {
												data: [
													{
														type: 'average'
													}
												]
											},
											// 线条控制
											smooth: true,
											lineStyle: {
												color: 'green'
											},
											// 填充风格
											areaStyle: {
												color: 'pink'
											}
										}]
									});
								}

							} else {
								alert("查询失败");
							}
						}
					})
				}
			});
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
