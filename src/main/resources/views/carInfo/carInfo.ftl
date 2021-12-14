<!DOCTYPE html>
<html>
<head>
    <title>用户管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text"  name="identity" class="layui-input searchVal" placeholder="请输入身份证号" style="width: 300px" />
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i>
                    查询
                </a>

            </div>
        </form>
    </blockquote>

    <table id="carInfoList" class="layui-table"  lay-filter="cars"></table>
    <img alt="" style="display:none;" id="displayImg" src="" height="500" width="500"/>

    <!--操作-->
    <script id="userListBar" type="text/html">
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="show" onclick="showLarge()">查看清晰图</a>

    </script>
</form>
<script type="text/javascript" src="${ctx}/js/carInfo/carInfo.js"></script>

<script type="text/javascript">

    /*弹出图片*/
    function showLarge() {
        var url = "../images/car.jpg";
        var imgHtml = "<img src='" + url + "' style='width:100%; max-width:100%;'/>";
        // 创建对象
        var img = new Image();
        img.src = url;
        layer.open({
            type: 1,
            shade: 0.5,
            area: ["889px", "500px"],
            shadeClose: true,
            scrollbar: false,
            title: false, //显示标题
            content: imgHtml, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function () {
                //layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', { time: 5000, icon: 6 });
            }
        });
    }
</script>

</body>
</html>