<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>花卉销售系统</title>
    <#include "common.ftl">
    <style type="text/css">
        #left{
            position: absolute;
        }
        #right{
            position: relative;
            left: 200px;
            top: 20px;
            color: #ffffff;
            font-size: 20px;

        }
        #space{
            margin-left: 250px;
            width: 800px;
            height: 50px;
            color: #ffffff;
        }
        #space a :hover{
            color: #93D1FF;
        }
    </style>

</head>
<body class="layui-layout-body layuimini-all">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header">
        <div class="layui-logo">
            <a href="">
                <img src="images/face.jpg" alt="logo">
                <h1>花卉销售系统</h1>
            </a>
        </div>
        <a>
            <div class="layuimini-tool"><i title="展开" class="fa fa-outdent" data-side-fold="1"></i></div>
        </a>

        <div id="space">
            <a href="#">
                <marquee id="right" crollamount="6">特别公告:本系统为测试版本,若使用过程中发生漏洞或不足之处,请及时联系技术主管欧阳祖鑫,联系方式:666666</marquee>
            </a>
        </div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;" data-check-screen="full"><i class="fa fa-arrows-alt"></i></a>
            </li>
            <li class="layui-nav-item layuimini-setting">
                <a href="javascript:;">${(user.realname)!""}</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx}/user/toSettingPage" data-title="基本资料" data-icon="fa fa-gears">基本资料</a>
                    </dd>
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx}/user/toPasswordPage" data-title="修改密码" data-icon="fa fa-gears">修改密码</a>
                    </dd>
                    <dd>
                        <a href="javascript:;" class="login-out">退出登录</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item layuimini-select-bgcolor mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;"></a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <#if permissions?? >
        <div class="layui-side-scroll layui-left-menu">

                <ul class="layui-nav layui-nav-tree layui-left-nav-tree layui-this" id="currency">
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips" ><i class="fa fa-home"></i><span class="layui-left-nav"> 后台首页</span></a>
                    </li>
                    <#if permissions ? seq_contains(2)>
                        <li class="layui-nav-item">
                            <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-street-view"></i><span class="layui-left-nav"> 基础管理</span> <span class="layui-nav-more"></span></a>
                            <dl class="layui-nav-child">
                                    <dd>
                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-1" data-tab="Bus_Customer/index" target="_self"><i class="fa fa-tty"></i><span class="layui-left-nav"> 客户管理</span></a>
                                    </dd>
                                    <dd>
                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-2" data-tab="bus_car/index" target="_self"><i class="fa fa-ellipsis-h"></i><span class="layui-left-nav"> 花卉管理</span></a>
                                    </dd>
                            </dl>
                        </li>
                    </#if>

                    <#if permissions ? seq_contains(3)>
                        <li class="layui-nav-item">
                            <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-flag"></i><span class="layui-left-nav"> 业务管理</span> <span class="layui-nav-more"></span></a><dl class="layui-nav-child">
                                <dd>
                                    <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-4" data-tab="bus_rent/index" target="_self"><i class="fa fa-user-times"></i><span class="layui-left-nav"> 订单管理</span></a>
                                </dd>

                                    <dd>
                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-3" data-tab="bus_car/info" target="_self"><i class="fa fa-exchange"></i><span class="layui-left-nav"> 花卉详情</span></a>
                                    </dd>
<#--                                    <dd>-->
<#--                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-4" data-tab="customer_loss/index" target="_self"><i class="fa fa-user-times"></i><span class="layui-left-nav"> 出租单管理1</span></a>-->
<#--                                    </dd>-->
                                    <#--<dd>
                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-5" data-tab="customer_loss/index" target="_self"><i class="fa fa-user-times"></i><span class="layui-left-nav"> 汽车入库</span></a>
                                    </dd>
                                    <dd>
                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-6" data-tab="customer_loss/index" target="_self"><i class="fa fa-user-times"></i><span class="layui-left-nav"> 检查单管理</span></a>
                                    </dd>-->
                            </dl>
                        </li>
                    </#if>
                    <#if permissions ? seq_contains(4)>
                        <li class="layui-nav-item">
                            <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-desktop"></i><span class="layui-left-nav"> 系统管理</span> <span class="layui-nav-more"></span></a>
                            <dl class="layui-nav-child">
                                    <#--<dd>
                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-7" data-tab="customer_serve/index/1" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 菜单管理</span></a>
                                    </dd>-->
                                    <dd>
                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-8" data-tab="role/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 角色管理</span></a>
                                    </dd>
                                    <dd>
                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-9" data-tab="user/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 用户管理</span></a>
                                    </dd>
                                    <dd>
                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-10" data-tab="logInfo/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 日志管理</span></a>
                                    </dd>
<#--                                    <dd>-->
<#--                                        <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="customer_serve/index/5" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 系统公告</span></a>-->
<#--                                    </dd>-->
                            </dl>
                        </li>
                    </#if>
<#--                    <#if permissions ? seq_contains(5)>-->
<#--                    <li class="layui-nav-item">-->
<#--                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-home"></i><span class="layui-left-nav"> 统计分析</span> <span class="layui-nav-more"></span></a><dl class="layui-nav-child">-->

<#--                            <dd>-->
<#--                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-12" data-tab="report/0" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 客户地区统计</span></a>-->
<#--                            </dd>-->
<#--                            <dd>-->
<#--                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="report/1" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 公司年度月份销售</span></a>-->
<#--                            </dd>-->
<#--                            <dd>-->
<#--                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-14" data-tab="sale_chance/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 业务员年度销售</span></a>-->
<#--                            </dd>-->

<#--                        </dl>-->
<#--                    </li>-->
<#--                    </#if>-->

                    <span class="layui-nav-bar" style="top: 201px; height: 0px; opacity: 0;"></span>
                </ul>
        </div>

        </#if>
    </div>

    <div class="layui-body">
        <div class="layui-tab" lay-filter="layuiminiTab" id="top_tabs_box">
            <ul class="layui-tab-title" id="top_tabs">
                <li class="layui-this" id="layuiminiHomeTabId" lay-id="welcome"><i class="fa fa-home"></i> <span>首页</span></li>
            </ul>

            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"> <i class="fa fa-dot-circle-o"></i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-page-close="other"><i class="fa fa-window-close"></i> 关闭其他</a></dd>
                        <dd><a href="javascript:;" data-page-close="all"><i class="fa fa-window-close-o"></i> 关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div id="layuiminiHomeTabIframe" class="layui-tab-item layui-show">
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript" src="${ctx}/js/main.js"></script>
</body>
</html>
