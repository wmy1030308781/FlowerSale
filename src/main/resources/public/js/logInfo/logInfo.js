layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * ⽤户列表展示
     */
    var tableIns = table.render({
        elem: '#userList',
        url : ctx+'/logInfo/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: 'ID', align:'center',minWidth:150},
            {field: 'loginname', title: '登录名称', minWidth:100, align:"center"},
            {field: 'loginip', title: '登录IP', minWidth:100, align:'center'},
            {field: 'logintime', title: '登录时间', minWidth:100, align:'center'},
            {title: '操作', minWidth:150, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });


    /**
     * 头部工具栏事件
     */
    table.on("toolbar(users)",function (obj){
       var checkStatus = table.checkStatus(obj.config.id);
       switch (obj.event){
           case "del" :
               deleteUser(checkStatus.data);
               break;
       }
    });

    /**
     * 行监听事件
     */
    table.on("tool(users)",function (obj){
        var layEvent = obj.event;
        //监听编辑事件
        if (layEvent === "del"){
            //监听删除事件
            layer.confirm("确定删除当前用户？",{icon : 3,title : "用户管理"},function (index){
               $.post(ctx + "/logInfo/delete",{ids:obj.data.id},function (data){
                  if (data.code == 200){
                      layer.msg("操作成功！",{icon : 6});
                      tableIns.reload();
                  }else {
                      layer.msg(data.msg,{icon : 5});
                  }
               });
            });
        }
    });



    /**
     * 批量删除用户
     * @param datas
     */
    function deleteUser(datas){
        if (datas.length == 0){
            layer.msg("请选择删除记录！",{icon : 5});
            return;
        }
        layer.confirm('确定删除选中的用户记录？',{
           btn : ['确定','取消']    //按钮
        },function (index){
            layer.close(index);
            var ids = "ids=";
            for (var i=0;i<datas.length;i++){
                if (i<datas.length-1){
                    ids = ids + datas[i].id + "&ids=";
                }else {
                    ids = ids + datas[i].id;
                }
            }
            //发送ajax请求
            $.ajax({
                type : "post",
                url : ctx + "/logInfo/delete",
                data : ids,
                dataType : "json",
                success : function (data){
                    if (data.code == 200){
                        layer.msg(data.msg,{icon : 6});
                        tableIns.reload();
                    }else {
                        layer.msg(data.msg,{icon : 5});
                    }
                }
            })
        });
    }

});
