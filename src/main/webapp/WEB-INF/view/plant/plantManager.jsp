<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <title>图书管理</title>
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
                       placeholder="请输入植物名称" style="height: 30px;border-radius: 10px">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">植物别名:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="alias" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入植物别名" style="height: 30px;border-radius: 10px">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">形态特点:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="feature" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入形态特点" style="height: 30px;border-radius: 10px">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">应用价值:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="value" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入应用价值" style="height: 30px;border-radius: 10px">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">栽培要点:</label>
            <div class="layui-input-inline" style="padding: 5px">
                <input type="text" name="point" autocomplete="off" class="layui-input layui-input-inline"
                       placeholder="请输入栽培要点" style="height: 30px;border-radius: 10px">
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
    <c:if test="${admin!=null}">
        <button type="button" class="layui-btn layui-btn-sm layui-btn-radius" lay-event="add">新增</button>
    </c:if>
</div>

<div id="newsBar" style="display: none;">
    <c:if test="${role==1}">
        <a class="layui-btn layui-btn-warm layui-btn-xs layui-btn-radius" lay-event="viewNews">配图</a>
        <a class="layui-btn layui-btn-warm layui-btn-xs layui-btn-radius" lay-event="addPicture">添加配图</a>
        <a class="layui-btn layui-btn-xs layui-btn-radius" lay-event="edit">修改</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-radius" lay-event="del">删除</a>
    </c:if>
</div>

<!-- 添加和修改的弹出层-->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
    <form class="layui-form" lay-filter="dataFrm" id="dataFrm" style="margin-right: 20px">
        <div class="layui-form-item">
            <label class="layui-form-label">植物编号:</label>
            <div class="layui-input-block">
                <input type="text" name="plantId" placeholder="请输入植物编号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">植物名称:</label>
            <div class="layui-input-block">
                <input type="text" name="plantName" placeholder="请输入植物名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">植物别名:</label>
            <div class="layui-input-block">
                <input type="text" name="alias" placeholder="请输入植物名称" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">形态特征:</label>
            <div class="layui-input-block">
                <input type="text" name="feature" placeholder="请输入形态特征" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">应用价值:</label>
            <div class="layui-input-block">
                <input type="text" name="value" placeholder="请输入应用价值" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">栽培要点:</label>
            <div class="layui-input-block">
                <input type="text" name="point"  placeholder="请输入栽培要点" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">种名:</label>
            <div class="layui-input-block">
                <input type="text" name="speciesName" placeholder="请输入植物名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">属名:</label>
            <div class="layui-input-block">
                <input type="text" name="genusName" placeholder="请输入植物名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">科名:</label>
            <div class="layui-input-block">
                <input type="text" name="familyName" placeholder="请输入植物名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">病虫害名称:</label>
            <div class="layui-input-block">
                <input type="text" name="diseaseName" placeholder="请输入病虫害名称" autocomplete="off" class="layui-input">
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

<%--配图div--%>
<div id="viewNewsDiv" style="padding: 10px;display: none" id="showPicturesDiv">
    <form class="layui-form" lay-filter="dataFrm2" id="dataFrm2" style="margin-right: 20px">

        <%--		<div class="layui-form-item" style="margin-top: 20px">--%>
        <%--			<label class="layui-form-label">图片</label>--%>
        <%--			<img id="mobileCoverImg1" class="originalImg"  style="height: 50px;min-width: 50px;"/>--%>
        <%--		</div>--%>
        <div class="layui-row">
            <c:forEach items="${pictures}" var="picture">
                <div class="layui-col-md4">
                    <img src="${picture.location}" alt="图片" style="width:100%">
                    <p>描述: ${picture.description}</p>
                    <p>拍摄地点: ${picture.location}</p>
                    <p>拍摄人: ${picture.photographer}</p>
                </div>
            </c:forEach>
        </div>
    </form>
</div>
<%--添加图片的弹出层--%>
<div style="display: none;padding: 20px" id="addPictureDiv">
    <form class="layui-form" lay-filter="dataFrm3" id="dataFrm3" style="margin-right: 20px">

        <div class="layui-form-item">
            <label class="layui-form-label">植物编号:</label>
            <div class="layui-input-block">
                <input type="text" name="plantId"  readonly placeholder="请输入图书编号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">配图编号:</label>
            <div class="layui-input-block">
                <input type="text" name="pictureId" lay-verify="required" placeholder="请输入图书编号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">拍摄地点:</label>
            <div class="layui-input-block">
                <input type="text" name="location" placeholder="请输入图书编号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">拍摄人:</label>
            <div class="layui-input-block">
                <input type="text" name="photographer" placeholder="请输入图书编号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">描述:</label>
            <div class="layui-input-block">
                <input type="text" name="description" placeholder="请输入图书编号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" style="margin-top: 20px">
            <label class="layui-form-label">图片</label>
            <button type="button" class="layui-btn" id="mobileTest1" style="margin-top: -12px">
                <i class="layui-icon">&#xe67c;</i>上传
            </button>
            <input type="hidden" name="path" id="img">
            <img  id="mobileCoverImg" class="originalImg"  style="height: 50px;min-width: 50px;"/>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: center;padding-right: 120px">
                <button type="button"
                        class="layui-btn layui-btn-normal layui-btn-md layui-icon layui-icon-release layui-btn-radius"
                        lay-filter="doSubmit2" lay-submit="">提交
                </button>
                <button type="reset" id="dataFrmResetBtn1"
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
            , url: '/plant/plantList.action' //数据接口
            , title: '植物信息'//数据导出来的标题
            , toolbar: "#newsToolBar"   //表格的工具条
            , height: 'full-185'
            , cellMinWidth: 120 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                {field: 'plantId', title: '植物编号', align: 'center'}
                , {field: 'plantName', title: '植物名称', align: 'center'}
                , {field: 'alias', title: '植物别名', align: 'center'}
                , {field: 'feature', title: '形态特征', align: 'center'}
                , {field: 'value', title: '应用价值', align: 'center'}
                , {field: 'point', title: '栽培要点', align: 'center'}
                , {field: 'speciesName', title: '种名', align: 'center'}
                , {field: 'genusName', title: '属名', align: 'center'}
                , {field: 'familyName', title: '科名', align: 'center'}
                , {field: 'diseaseId', title: '是否患病', align: 'center',templet: function (d) {
                        return d.diseaseName== null ? '不患病' : '患病';
                    }}
                , {field: 'diseaseName', title: '病名', align: 'center'}
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
                url: "/plant/plantList.action?" + params,
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
                layer.confirm('真的删除【' + data.plantName + '】这个植物以及对应的信息么？', function (index) {
                    //向服务端发送删除指令
                    $.post("/plant/deletePlant.action", {plantId: data.plantId}, function (res) {
                        layer.msg(res.msg);
                        //刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if (layEvent === 'edit') { //修改
                //修改，打开修改界面
                openUpdateNews(data);
            } else if (layEvent === 'viewNews') {//配图
                viewNews(data);
            }else if(layEvent === 'addPicture'){ //添加配图
                openAddPicture(data);
            }
        });

        var url;
        var mainIndex;

        //打开添加框
        function openAddNews() {
            mainIndex = layer.open({
                type: 1,
                title: '添加植物信息',
                content: $("#saveOrUpdateDiv"),
                area: ['800px', '540px'],
                success: function (index) {
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    url = "/plant/addPlant.action";
                }
            });
        }
        //打开添加配图层
        function openAddPicture(data) {
            mainIndex = layer.open({
                type: 1, // 弹出层类型
                title: '添加新图片', // 标题
                area: ['600px', '400px'], // 宽高
                content: $('#addPictureDiv'), // 弹出层内容
                success: function (index) {
                    //清空表单数据
                    form.val("dataFrm3", data);

                    url = "/plant/addPlantPicture.action";
                }
            });
        }
        //保存配图
        form.on("submit(doSubmit2)", function (obj) {
            //序列化表单数据
            var params = $("#dataFrm3").serialize();
            $.post(url, params, function (obj) {
                layer.msg(obj.msg);
                //关闭弹出层
                $("#dataFrm3")[0].reset();
                layer.close(mainIndex);
                //刷新数据 表格
                tableIns.reload();
            })
        });
        //打开修改页面
        function openUpdateNews(data) {
            mainIndex = layer.open({
                type: 1,
                title: '修改植物信息',
                content: $("#saveOrUpdateDiv"),
                area: ['800px', '540px'],
                success: function (index) {
                    form.val("dataFrm", data);
                    // $('#mobileCoverImg').attr('src', "/file/downloadFile.action?path=" + data.img);
                    url = "/plant/updatePlant.action";
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


        //配图
        function viewNews(data) {

            $.ajax({
                url:"/plant/showPictures.action",
                type: 'POST',
                data: { 'plantId': data.plantId },
                success: function(data) {
                    var content = '';
                    data.forEach(function(picture) {
                        // $('#mobileCoverImg').attr('src', "downloadFile?path=" + res.data.src);
                        // content += '<div align="center"><img src="' + 'downloadFile?path='+picture.path + '" style="width:200px;min-height: 200px;"><p>拍摄人: ' + picture.photographer + '</p><p>拍摄地点: ' + picture.location + '</p><p>描述: ' + picture.description + '</p></div>';
                        content += '<div align="center">';
                        content += '<img src="' + '/file/downloadFile.action?path=' + picture.path + '" style="width:200px;min-height: 200px;">';
                        content += '<p>拍摄人: ' + picture.photographer + '</p>';
                        content += '<p>拍摄地点: ' + picture.location + '</p>';
                        content += '<p>描述: ' + picture.description + '</p>';
                        content += '<button class="layui-btn layui-btn-danger" onclick="deletePicture(' + picture.pictureId + ')">删除</button>';
                        content += '</div>';

                    });
                    layer.open({
                        type: 1,
                        title: '植物配图',
                        area: ['800px', '600px'], // 宽高
                        content: content
                    });
                },

            });

            // mainIndex = layer.open({
            // 	type: 1,
            // 	title: '查看图书信息',
            // 	content: $("#viewNewsDiv"),
            // 	area: ['700px', '540px'],
            // 	success: function (index) {
            // 		form.val("dataFrm2", data);
            // 		$('#mobileCoverImg1').attr('src', "/file/downloadFile.action?path=" + data.img);
            // 	}
            // });
        }




    });
    function deletePicture(pictureId) {
        console.log(pictureId)
        jquery.ajax({
            url: '/plant/deletePicture.action', // 替换为你的删除接口
            type: 'POST',
            data: { 'pictureId': pictureId },
            success: function(response) {
                // 处理删除成功的情况，比如刷新图片列表或显示一个通知
                alert('图片删除成功');
                // 重新加载或更新视图
            },
            error: function(error) {
                // 处理错误情况
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
