<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody" bgcolor="#00ffff">
<form class="layui-form" style="width:80%;">
    <#-- 设置营销机会的ID -->
    <input type="hidden" name="id" value="${(busCustomer.identity)!}">

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">身份证号码</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="identity" id="identity"  value="${(busCustomer.identity)!}" placeholder="请输入身份证号码">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">客户名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="custname"
                   id="custname" value="${(busCustomer.custname)!}" placeholder="请输入客户名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="sex"
                   lay-verify="required"  value="${(busCustomer.sex)!}" placeholder="请输入性别">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">地址</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="address" value="${(busCustomer.address)!}" id="address" placeholder="请输入地址">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="phone" value="${(busCustomer.phone)!}" id="phone" placeholder="请输入电话">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">工作</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="career" value="${(busCustomer.career)!}"
                   placeholder="请输入工作">
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateSaleChance">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/busCustomer/add_BusCustomer.js"></script>
</body>
</html>