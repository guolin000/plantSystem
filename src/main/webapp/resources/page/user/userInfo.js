var form, $;
layui.use(['form','layer','upload','laydate'],function(){
    form = layui.form;
    $ = layui.jquery;
    var layer = parent.layer === undefined ? layui.layer : top.layer;


    $.post("/user/getUser.action", function (res) {
        var userInfo = res.data
        console.log('userInfo', userInfo)
        $("#userid").val(userInfo.userid); //用户名
        $(".loginname").val(userInfo.loginname); //用户名
        $(".realname").val(userInfo.realname); //用户名
        // $(".userSex input[value="+userInfo.sex+"]").attr("checked","checked"); //性别
        $('input[name="sex"][value='+userInfo.sex+']').prop("checked",true)
        form.render('radio')
        $(".phone").val(userInfo.phone); //手机号
        $(".email").val(userInfo.email); //邮箱
        $(".address").val(userInfo.address); //地址
        $(".deptName").val(userInfo.deptName); //工作单位
    })

    //提交个人资料
    form.on("submit(changeUser)",function(data){
        var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});

        setTimeout(function(){
            layer.close(index);
            layer.msg("提交成功！");
            var params = $("#userInfoForm").serialize();
            $.post("/user/updateUser.action", params, function (res) {
                layer.msg(res.msg);
                //刷新数据表格
                tableIns.reload();
            })


        },1000);
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

    //修改密码
    form.on("submit(changePwd)",function(data){
        var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            var params = "";
            var pwd = $("#newPwd").val();
            params = "pwd=" + pwd;
            // $("#pwdForm").submit();
            $.post("/user/updateUserPwd.action", params, function (res) {
                // layer.msg(res.msg);
                //刷新数据表格
                tableIns.reload();
            })

            layer.close(index);
            layer.msg("密码修改成功！");
            $(".pwd").val('');
        },1000);
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
})
