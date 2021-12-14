<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="userid" type="hidden" value="${(user.userid)!}"/>
    <input name="sexVal" type="hidden" value="${(user.sex)!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input realname"
                   lay-verify="required" name="realname" id="realname"  value="${(user.realname)!}" placeholder="请输入用户姓名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">登录名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input loginname"
                   lay-verify="required" name="loginname" id="loginname" value="${(user.loginname)!}" placeholder="请输入登录名">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">身份证号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input identity"
                   lay-verify="required" name="identity" id="identity" value="${(user.identity)!}" placeholder="请输入身份证号">
        </div>
    </div>


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail"
                   lay-verify="phone" name="phone" value="${(user.phone)!}" id="phone" placeholder="请输入手机号">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">地址</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input loginname"
                   lay-verify="required" name="address" id="address" value="${(user.address)!}" placeholder="地址">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
           <#assign sex="${(user.sex)! 1}">
            <#if sex =="1">
             <input type="radio" name="sex"  checked value="1" title="男">
             <input type="radio" name="sex"    value="0" title="女">
            </#if>
            <#if sex =="0">
            <input type="radio" name="sex"    value="1" title="男">
             <input type="radio" name="sex"   checked value="0" title="女">
            </#if>

        </div>
    </div>



<#--    <div class="layui-form-item layui-row layui-col-xs12">-->
<#--        <label class="layui-form-label">邮箱</label>-->
<#--        <div class="layui-input-block">-->
<#--            <input type="text" class="layui-input userEmail"-->
<#--                   lay-verify="email" name="email" value="${(user.email)!}"-->
<#--                   id="email"-->
<#--                   placeholder="请输入邮箱">-->
<#--        </div>-->
<#--    </div>-->



    <div class="magb15 layui-col-md4 layui-col-xs12">
        <label class="layui-form-label">角色</label>
        <div class="layui-input-block">
            <select name="roleIds"  xm-select="selectId">
            </select>
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateUser">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="${ctx}/js/user/add_update.js"></script>
</body>
</html>