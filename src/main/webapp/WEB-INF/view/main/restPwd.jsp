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
<br><br>
<h1 align="center"  style="color:LightSteelBlue ; font-size: 50px">图书管理系统</h1>
	<br><br>
	<form class="layui-form" id="loginFrm">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
			<legend>登录</legend>
		</fieldset>
		<div class="layui-form-item input-item">
			<label for="email">邮箱</label>
			<input type="text" placeholder="请输入账户邮箱" autocomplete="off" name="email" id="email" class="layui-input" lay-verify="required|email">
			<div class="layui-word-aux" style="padding-top: 4px;">
				<button type="button" class="layui-btn layui-btn-sm" id="countdown-btn">获取验证码</button>
			</div>
		</div>
		<div class="layui-form-item input-item">
			<label for="code">验证码</label>
			<input type="text" placeholder="请输入验证码" autocomplete="off" name="code" id="code" class="layui-input" lay-verify="required">
		</div>
		<div class="layui-form-item">
			<button type="button" class="layui-btn layui-btn-block" style="width: 100%" id="rest-btn">重置密码</button>
		</div>
		<div class="layui-form-item layui-row" style="text-align: center;color: red;">
			${error}
		</div>
	</form>
	<script type="text/javascript" src="/resources/layui/layui.js"></script>
	<script type="text/javascript" src="/resources/js/cache.js"></script>
	<script type="text/javascript">
	layui.use(['form','layer','jquery'],function(){
	    var form = layui.form,
	        layer = parent.layer === undefined ? layui.layer : top.layer
	        $ = layui.jquery;

		$("#rest-btn").click(function() {
			var email = $("#email").val();
			var code = $("#code").val();
			if (code == '') {
				layer.msg("请输入验证码！");
				return false;
			}
			$.get("/login/restPwd.action?email=" + email + "&code=" + code,function(data){
				console.log(data)
				layer.msg(data.msg);
			})
	    })

		$("#countdown-btn").click(function() {
			var email = $("#email").val();
			if (email == '') {
				layer.msg("请输入正确的邮箱！");
				return false;
			}
			let regex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/; // 手机号正则表达式
			if (!regex.test(email)) { // 验证手机号
				layer.msg("请输入正确的邮箱！");
				return false;
			}
			console.log(email)
			$.get("/login/sendEmail.action?email=" + email,function(data){
				console.log(data.data)
				layer.msg(data.msg);
			})
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
