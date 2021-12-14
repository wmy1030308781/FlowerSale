<!DOCTYPE html>
<html>
<head>
    <title>用户管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >

    <table id="userList" class="layui-table"  lay-filter="users"></table>

    <script type="text/html" id="toolbarDemo">
            <a class="layui-btn layui-btn-danger delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                批量删除
            </a>
        </div>
    </script>

    <!--操作-->
    <script id="userListBar" type="text/html">
        <a class="layui-btn layui-btn-xs layui-btn-danger " lay-event="del">删除</a>
    </script>
</form>

<script type="text/javascript" src="${ctx}/js/logInfo/logInfo.js"></script>
</body>
</html>