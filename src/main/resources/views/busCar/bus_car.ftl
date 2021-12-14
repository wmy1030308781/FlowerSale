<!DOCTYPE html>
<html>
<head>
    <title>花卉管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="carnumber"
                           class="layui-input
							searchVal" placeholder="花名" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="cartype" class="layui-input
							searchVal" placeholder="种类" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="color" class="layui-input
							searchVal" placeholder="颜色" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="description" class="layui-input
							searchVal" placeholder="花卉备注" />
                </div>
                <div class="layui-input-inline">
                    <select name="isrenting"  id="isrenting">
                        <option value="" >出售状态</option>
                        <option value="0">未出售</option>
                        <option value="1" >已出售</option>
                    </select>
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>

    <!-- 数据表格 -->
    <table id="busCarList" class="layui-table"  lay-filter="busCars">
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
    <script id="busCarListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>

</form>

<script type="text/javascript" src="${ctx}/js/busCar/bus_car.js"></script>
</body>
</html>