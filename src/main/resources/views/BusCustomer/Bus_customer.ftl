<!DOCTYPE html>
<html>
<head>
    <title>客户管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="custname"
                           class="layui-input
searchVal" placeholder="客户名" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="address" class="layui-input
searchVal" placeholder="地址" />
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="career" class="layui-input
searchVal" placeholder="工作" />
                </div>
                    <div class="layui-input-inline">
                        <input type="text" name="sex" class="layui-input
searchVal" placeholder="性别" />
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i> 搜索
                </a>
            </div>
        </form>
    </blockquote>
    <!-- 数据表格 -->
    <table id="saleChanceList" class="layui-table" lay-filter="saleChances">

    </table>
    <!-- 头部工具栏 -->
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
    <!-- 行工具栏 -->
    <script id="saleChanceListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>
</form>
<script type="text/javascript" src="${ctx}/js/busCustomer/busCustomer.js"></script>
</body>
</html>
