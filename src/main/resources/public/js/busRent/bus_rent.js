layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 营销机会列表展示
     */
    var  tableIns = table.render({
        elem: '#busRentList', // 表格绑定的ID
        url : ctx + '/bus_rent/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "busRentListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "saleid", title:'订单号',fixed:"true"},
            {field: 'identity', title: '身份证号',align:"center"},
            // {field: 'flowernumber', title: '花名',  align:'center'},
            // {field: 'price', title: '出售价格', align:'center'},
            {field: 'saleflag', title: '订单状态', align:'center',templet:function(d){
                    return formatterRentflag(d.saleflag);}},
            {field: 'begindate', title: '下单时间',  align:'center'},
            {field: 'saledate', title: '送达时间', align:'center'},
            {title: '操作', templet:'#busrentBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });

    /**
     * 格式化归还状态
     *  0 - 未分配
     *  1 - 已分配
     *  其他 - 未知
     * @param state
     * @returns {string}
     */
    function formatterRentflag(rentflag){
        // layer.msg(rentflag)
        if(rentflag==0) {
            return "<div style='color: red'>未送达</div>";
        } else if(rentflag==1) {
            return "<div style='color: green'>已送达</div>";
        } else {
            return "<div style='color: yellow'>丢包</div>";
        }
    }

    /**
     * 搜索按钮点击事件
     * */
    $(".search_btn").click(function(){
        //上述方法等价于
        // layer.msg("你点了一下搜索按钮")
        table.reload('busRentListTable', {
            where: { //设定异步数据接口的额外参数，任意设
                "saleid":$("input[name='rentid']").val(),
                "identity": $("input[name='identity']").val(),
                "flowernumber":$("input[name='carnumber']").val(),
                "begindate": $("input[name='begindate']").val(),
                "saledate": $("input[name='returndate']").val(),
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        }); //只重载数据
    });

    /***
     * 绑定头部工具栏
     */
    table.on('toolbar(busrent)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus.data)
        switch(obj.event){
            case 'add':
                // alert("你点了一下添加");
                /***
                 * 添加事件发生
                 */
                openAddOrUpdateBusrentDialog();
                break;
            case 'del':
                deleteBusrent(checkStatus.data);
                break;

        };
    });

    /***
     * 添加事件
     */
    function openAddOrUpdateBusrentDialog(rentid){
        var title="<h3>订单-添加</h3>";
        var url=ctx+"/bus_rent/addOrUpdateBusrentPage";

        //判断
        if(rentid){
            title="<h3>订单-查看</h3>";
            url=ctx+"/bus_rent/select?rentid="+rentid;
            layui.layer.open({
                title: title,
                type: 2,
                content: url,
                area: ["500px", "550px"],
                maxmin: true
            })
        }else {
            /*弹出层*/
            layui.layer.open({
                title: title,
                type: 2,
                content: url,
                area: ["500px", "350px"],
                maxmin: true
            })
        }
    }

    /**
     * 删除
     */
    function deleteBusrent(data){
        if(data.length==0){
            layer.msg("请选择要删除的数据?");
            return ;
        }
        //发送ajax删除
        layer.confirm("真的删除吗？",{
            btn:["确定","取消"],
        },function(index){
            //关闭弹出框
            layer.close(index);
            //收集数据
            var ids=[];
            //循环
            for (var i = 0; i <data.length ; i++) {
                ids.push(data[i].saleid);
            }
            //ids=1&ids=2
            console.log(ids.toString()+"<<<要删除的东西")
            //发送ajax删除
            $.ajax({
                type:"post",
                url:ctx+"/bus_rent/delete",
                data:{"ids":ids.toString()},
                dataType:"json",
                success:function (data){
                    if(data.code==200){
                        //重新加载列表
                        tableIns.reload();
                    }else{
                        //删除失败的提醒
                        layer.msg(data.msg);
                    }
                }
            });
        });
    }

    /*绑定行内工具栏*/
    //监听行工具事件
    table.on('tool(busrent)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                //关闭弹出层
                layer.close(index);
                // 发送ajax删除
                $.ajax({
                    type:"post",
                    url:ctx+"/bus_rent/delete",
                    data:{"ids":data.saleid},
                    success:function (data){
                        if(data.code==200){
                            layer.msg("删除OK");
                            tableIns.reload();
                        }else{
                            layer.msg(data.msg);
                        }
                    }
                });
            });
        }else if(obj.event === "stl"){
            //传入当前对象的id
            openAddOrUpdateBusrentDialog(data.saleid);
        }
    });
});