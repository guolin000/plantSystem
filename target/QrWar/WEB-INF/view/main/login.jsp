<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="loginHtml">
<head>
	<meta charset="utf-8">
	<title>登录-图书管理系统</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<link rel="icon" href="/resources/favicon6.ico">
	<link rel="stylesheet" href="/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="/resources/css/public.css" media="all" />
</head>
<body class="loginBody">
<br>
<h1 align="center"  style="color:LightSteelBlue ; font-size: 65px;font-family: 方正粗黑宋简体,微软雅黑;">园 林 植 物 管 理 系 统</h1>
	<form class="layui-form" id="loginFrm" method="post" action="/login/login.action" style="height: 525px;width: 300px;">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
			<legend>请登录</legend>
		</fieldset>
		<label class="layui-form-label">请选择身份</label>
		<div class="layui-form-item input-item">
			<select name="rid">
				<option value="" selected="selected">请选择身份</option>
				<option value="1">管理员</option>
				<option value="2">上级主管部门</option>
				<option value="3">养护人员</option>
				<option value="4">监测人员</option>
			</select>
		</div>
		<div class="layui-form-item input-item">
			<label for="loginName">用户名</label>
			<input type="text" placeholder="请输入用户名" autocomplete="off" name="loginName" id="loginName" class="layui-input" lay-verify="required">
		</div>
		<div class="layui-form-item input-item">
			<label for="password">密码</label>
			<input type="password" placeholder="请输入密码" autocomplete="off" name="password" id="password" class="layui-input" lay-verify="required">
		</div>

		<div class="layui-form-item layui-row" style="text-align: center;color: #2b1010;">
			${error}
		</div>
		<div class="layui-form-item">
			<button class="layui-btn layui-block" lay-filter="login" lay-submit>登录</button>
			<br/>
			<a class="layui-btn layui-block magt3" lay-filter="register" href="/login/toRegister.action">注册</a>
		</div>

		<div class="layui-form-item layui-row" style="text-align: right;color: blue;">
			<a style="text-align: right " href="/login/toRestPwd.action">重置密码</a>
		</div>
	</form>
	<script type="text/javascript" src="/resources/layui/layui.js"></script>
	<script type="text/javascript" src="/resources/js/cache.js"></script>
	<script type="text/javascript">
	layui.use(['form','layer','jquery'],function(){
	    var form = layui.form,
	        layer = parent.layer === undefined ? layui.layer : top.layer
	        $ = layui.jquery;
	    //登录按钮
	    form.on("submit(login)",function(data){
	        $(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
	        setTimeout(function(){
	           $("#loginFrm").submit();
	        },500);
	        return false;
	    })

	    //表单输入效果
	    $(".loginBody .input-item").click(function(e){
	        e.stopPropagation();
	        $(this).addClass("layui-input-focus").find(".layui-input").focus();
	    })
	    $(".loginBody .layui-form-item .layui-input").focus(function(){
	        $(this).parent().addClass("layui-input-focus");
	    })
	    $(".loginBody .layui-form-item .layui-input").blur(function(){
	        $(this).parent().removeClass("layui-input-focus");
	        if($(this).val() != ''){
	            $(this).parent().addClass("layui-input-active");
	        }else{
	            $(this).parent().removeClass("layui-input-active");
	        }
	    })
		$(".magt3").click(function(e){
		<%--<a href="/login/toLogin.action" class="signOut"><i class="seraph icon-tuichu"></i><cite>退出</cite></a>--%>
		})
	})

	</script>
</body>
</html>
