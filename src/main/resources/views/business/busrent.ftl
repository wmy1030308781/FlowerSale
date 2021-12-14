<!DOCTYPE html>
<html>
<head>
    <title>订单管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="rentid"
                           class="layui-input
							searchVal" placeholder="订单号" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="identity" class="layui-input
							searchVal" placeholder="身份证号" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="carnumber" class="layui-input
							searchVal" placeholder="花名" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="begindate" class="layui-input
							searchVal" placeholder="下单时间" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="returndate" class="layui-input
							searchVal" placeholder="送达时间" />
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="busRentList" class="layui-table"  lay-filter="busrent">
    </table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                添加
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                删除
            </a>
        </div>
    </script>


    <!--操作-->
    <script id="busrentBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="stl" lay-event="stl">查看详情</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>

</form>
<script type="text/javascript" src="${ctx}/js/busRent/bus_rent.js"></script>
</body>
</html>