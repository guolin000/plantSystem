<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="loginHtml">
<head>
	<meta charset="utf-8">
	<title>注册-图书管理系统</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<link rel="icon" href="/resources/favicon6.ico">
	<link rel="stylesheet" href="/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="/resources/css/public.css" media="all" />
</head>
<body class="loginBody" >
<br>
<h1 align="center" style="color:LightSteelBlue ; font-size: 65px;font-family: 方正粗黑宋简体,微软雅黑;">图 书 借 阅 管 理 系 统</h1>
	<form class="layui-form" id="loginFrm" method="post" action="/login/register.action" style="height: 615px">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
			<legend>注册</legend>
		</fieldset>
		<div class="layui-form-item layui-row" style="text-align: center;color: red;">
			${error}
		</div>
		<div class="layui-form-item input-item">
			<label for="loginname">用户名</label>
			<input type="text" placeholder="请输入用户名" autocomplete="off" name="loginname" id="loginname" class="layui-input" lay-verify="required">
		</div>
		<div class="layui-form-item input-item">
			<label for="pwd">密码</label>
			<input type="password" placeholder="请输入密码" autocomplete="off" name="pwd" id="pwd" class="layui-input" lay-verify="required">
		</div>
		<div class="layui-form-item input-item">
			<label for="loginname">姓名</label>
			<input type="text" placeholder="请输入姓名" autocomplete="off" name="realname" id="realname" class="layui-input" lay-verify="required">
		</div>
		<div class="layui-form-item input-item">
			<label for="phone">联系电话</label>
			<input type="text" placeholder="请输入联系电话" autocomplete="off" name="phone" id="phone" class="layui-input" lay-verify="required|phone">
		</div>
		<div class="layui-form-item input-item">
			<label for="email">邮箱</label>
			<input type="text" placeholder="请输入邮箱" autocomplete="off" name="email" id="email" class="layui-input" lay-verify="required|email">
		</div>
		<div class="layui-form-item input-item">
			<label for="address">地址</label>
			<input type="text" placeholder="请输入地址" autocomplete="off" name="address" id="address" class="layui-input" lay-verify="required">
		</div>
		<div class="layui-form-item">
			<div class="layui-input-inline">
				<input type="radio" name="sex" value="1" checked="checked" title="男">
				<input type="radio" name="sex" value="0" title="女">
			</div>
		</div>
		<div class="layui-form-item" style="margin-top: 20px">
			<input type="hidden" name="img" id="img">
			<img  id="mobileCoverImg" class="originalImg"  style="height: 50px;min-width: 50px;"/>
			<button type="button" class="layui-btn" id="uploadImage" style="margin-top: -12px">
				<i class="layui-icon">&#xe67c;</i>上传
			</button>
		</div>
		<div class="layui-form-item">
			<button class="layui-btn layui-block" lay-filter="login" lay-submit>注册</button>
			<br>
			<a class="layui-btn layui-block magt3"  href="toLogin.action">返回到登录页</a>
		</div>
	</form>
	<script type="text/javascript" src="/resources/layui/layui.js"></script>
	<script type="text/javascript" src="/resources/js/cache.js"></script>
	<script type="text/javascript">
	layui.use(['form','layer','jquery', 'upload'],function(){
	    var form = layui.form,
	        layer = parent.layer === undefined ? layui.layer : top.layer
		var upload = layui.upload;
	        $ = layui.jquery;
	    //登录按钮
	    form.on("submit(login)",function(data){
	        $(this).text("注册中...").attr("disabled","disabled").addClass("layui-disabled");
	        setTimeout(function(){
	           $("#loginFrm").submit();
	        },1000);
	        return false;
	    })

		upload.render({
			elem: '#uploadImage',
			url: '/file/uploadFile.action',
			method: "post",
			acceptMime: 'images/*',
			field: "mf",
			done: function (res, index, upload) {
				console.log(res.data)
				$('#mobileCoverImg').attr('src', "/file/downloadFile.action?path=" + res.data.src);
				$('#img').val(res.data.src);/*给用户记录img的值*/
			}
		});

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
