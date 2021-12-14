<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#-- 设置营销机会的ID -->
    <input type="hidden" name="hcarnumber" value="${(busCar.flowernumber)!}">

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">花名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="carnumber" id="carnumber"  value="${(busCar.flowernumber)!}" placeholder="请输入花名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">种类</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="cartype"
                   id="cartype" value="${(busCar.flowertype)!}" placeholder="请输入花的种类">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">颜色</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="color"
                   lay-verify="required"  value="${(busCar.color)!}" placeholder="请输入花的颜色">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">进价</label>
        <div class="layui-input-block">
            <input type="number" class="layui-input" lay-verify="required"
                   name="price" value="${(busCar.purchaseprice)!}" id="price" placeholder="请输入进价">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">售价 </label>
        <div class="layui-input-block">
            <input type="number" class="layui-input" lay-verify="required"
                   name="rentprice" value="${(busCar.price)!}" id="rentprice" placeholder="请输入售价">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">库存</label>
        <div class="layui-input-block">
            <input type="number" class="layui-input" lay-verify="required" name="deposit" value="${(busCar.deposit)!}"
                   placeholder="请输入库存">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">花卉备注</label>
        <div class="layui-input-block">
                    <textarea placeholder="请输入出售描述信息" name="description" class="layui-textarea">${(busCar.description)!}
                  	</textarea>
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateBusCar">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/busCar/add_update.js"></script>
</body>
</html>