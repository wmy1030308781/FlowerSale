<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">订单号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="rentid" id="rentid"  value="${(busrent.saleid)!}" readonly="readonly">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">身份证号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="identity"
                   id="identity" value="${(busrent.identity)!}" readonly="readonly">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">花名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="carnumber"
                   id="carnumber" value="${(busrent.carnumber)!}" readonly="readonly">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">出售价格</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="price"
                   id="price" value="${(busrent.price)!}" readonly="readonly">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">售卖状态</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="rentflag"
                   id="rentflag" value="${(busrent.rentflag)!}" readonly="readonly">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">下单时间</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="begindate"
                   id="begindate" value="${(begindate)!}" readonly="readonly">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">送达时间</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="returndate"
                   id="returndate" value="${(returndate)!}" readonly="readonly">
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">关闭</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/busRent/add_update.js"></script>
</body>
</html>