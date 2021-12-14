layui.use(['form', 'jquery', 'jquery_cookie', 'table'], function () {
    var form = layui.form,
        layer = layui.layer,
        table = layui.table,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    /**
     * 用户列表展示
     */
    var tableIns = table.render({
        elem: '#userList', // 表格绑定的ID
        url: ctx + '/user/list', // 访问数据的地址,应该是渲染表格的时候就会发送请求
        cellMinWidth: 95,
        page: true, // 开启分页
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",//头部工具栏
        id: "userListTable",
        cols: [[
            {type: "checkbox", fixed: "center"},
            {field: "userid", title: '编号', fixed: "true"},
            {field: 'realname', title: '用户姓名', align: "center"},
            {field: 'loginname', title: '登录名称', align: 'center'},
            {field: 'identity', title: '身份证号', align: 'center'},
            {field: 'phone', title: '手机号码', align: 'center'},
            {field: 'address', title: '用户地址', align: 'center'},
            {
                field: 'sex', title: '性别', align: 'center', templet: function (d) {
                    return formatterState(d.sex);//引入样式
                }
            },

            {title: '操作', templet: '#userListBar', fixed: "right", align: "center", minWidth: 150}//尾部工具栏
        ]]
    });


    /**
     * 判断男女
     * @param state
     * @returns {string}
     */
    function formatterState(sex) {
        if (sex == 0) {
            return "<div style='color: red'>女</div>";
        } else if (sex == 1) {
            return "<div style='color: blue'>男</div>";
        }
    }

    /**
     *表格搜索功能
     */
    $(".search_btn").click(function () {
        table.reload('userListTable',{
            //重载所需的数据
            where:{
                realname:$("input[name=realname]").val(),
                loginname:$("input[name=loginname]").val(),
                phone:$("input[name=phone]").val(),
                address:$("input[name=address]").val(),
                identity:$("input[name=identity]").val()
            },
            page: {
                curr:1
            }
        })
    });

    //头部工具栏
    table.on('toolbar(users)',function (obj) {
        var ids=table.checkStatus(obj.config.id)//获取选中的元素
        console.log(ids)
        //确定触发的事件
        switch (obj.event){
            case 'add':
                openUpdateOrSavePage();
            break;
            case 'del':
                deleteUser(ids.data)//调用删除的函数并发送数据
            break;
        }
    });

    //行内工具栏
    table.on('tool(users)',function (obj){
        console.log(obj.data.userid+"<<<");
        //alert(obj.data+""+obj.data.);
        switch (obj.event){
            case 'edit':
                openUpdateOrSavePage(obj.data.userid);
                break;
            case 'del':
                // console.log(obj.data.id)
                layer.confirm("您确定要删除吗?",{
                    btn:["是","否"]
                },function (index) {
                    layer.close(index)//关闭确认框
                    // console.log(key.toString())
                    $.ajax({
                        type:"post",
                        url:ctx+"/user/delete",
                        data:{
                            "userId":obj.data.userid
                        },
                        success:function (res) {
                            if(res.code==200){
                                layer.msg("删除成功!",{icon:6})
                                tableIns.reload();
                            }else {
                                layer.msg(res.msg,{icon:5})
                            }
                        }
                    });
                })
                break;
        }
    });

    /**
     * 打开更新或添加页面
     */
    function openUpdateOrSavePage(id){
        var title="<h2>用户添加</h2>";
        var url=ctx+"/user/toUpdateOrSavePage";
        console.log(url);
        if(id){
            title="<h2>用户更新</h2>"
            url+="?userId="+id
            console.log(url);
        }
        //打开弹出层
        layer.open({
            title:title,//标题
            content:url,//路径
            type: 2,//类型为iframe
            maxmin:true,//可大可小
            area:["600px","400px"]
        })
    }

    /**
     * 删除的函数
     */
    function deleteUser(ids){
        if(ids.length==0){
            layer.msg("请选择要删除的列");
            return ;
        }

        layer.confirm("您确定要删除吗?",{
            btn:["是","否"]
        },function (index) {
            layer.close(index)//关闭确认框
            var key=[];//准备请求参数

            for(var x in ids){
                // console.log(ids[x].id)
                key.push(ids[x].userid);//只取出数据中的id
            }
            // console.log(key.toString())
            $.ajax({
                type:"post",
                url:ctx+"/user/delete",
                data:{
                    "userId":key.toString()
                },
                success:function (res) {
                    if(res.code==200){
                        layer.msg("删除成功!",{icon:6})
                        tableIns.reload();
                    }else {
                        layer.msg(res.msg,{icon:5})
                    }
                }
            });
        })

    }



});