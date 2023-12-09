layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    //添加验证规则
    form.verify({
        // oldPwd : function(value, item){
        //     if(value != "123456"){
        //         return "密码错误，请重新输入！";
        //     }
        // },
        newPwd : function(value, item){
            if(value.length < 3){
                return "密码长度不能小于3位";
            }
        },
        confirmPwd : function(value, item){
            if(!new RegExp($("#newPwd").val()).test(value)){
                return "两次输入密码不一致，请重新输入！";
            }
        }
    })
})