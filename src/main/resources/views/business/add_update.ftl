<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">身份证号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="identity"
                   id="identity" value="${(busrent.identity)!}" placeholder="请输入身份证号">
        </div>
    </div>



    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">下单时间</label>
        <div class="layui-input-block">
        <input type="number" name="time" class="layui-input
							searchVal" placeholder="下单时间" />
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdatebusrent"
                    id="OkBtn" >
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/busRent/add_update.js"></script>
</body>
</html>