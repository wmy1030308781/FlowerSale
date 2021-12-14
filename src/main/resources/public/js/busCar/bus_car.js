layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 车辆运营列表展示
     */
    var  tableIns = table.render({
        elem: '#busCarList', // 表格绑定的ID
        url : ctx + '/bus_car/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "busCarListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "flowernumber", title:'花名',fixed:"true"},
            {field: 'flowertype', title: '种类',align:"center"},
            {field: 'color', title: '颜色',  align:'center'},
            // {field: 'Purchaseprice', title: '进价', align:'center'},
            {field: 'price', title: '售价', align:'center'},
            {field: 'deposit', title: '库存',  align:'center'},
            {field: 'issaleing', title: '出售状态', align:'center',templet:function(d){
                    return formatterIsRenting(d.issaleing);
                }},
            {field: 'description', title: '花卉备注',  align:'center'},
            {field: 'createtime', title: '录入时间',  align:'center'},
            {title: '操作', templet:'#busCarListBar',fixed:"right",align:"center", minWidth:260}
        ]]
    });

    /**
     * 格式化分配状态
     *  0 - 未分配
     *  1 - 已分配
     *  其他 - 未知
     * @param state
     * @returns {string}
     */
    function formatterIsRenting(isrenting){
        if(isrenting==0) {
            return "<div style='color: blue'>未出售</div>";
        } else if(isrenting==1) {
            return "<div style='color: green'>已出售</div>";
        } else {
            return "<div style='color: red'>未知</div>";
        }
    }

    /*选择元素绑定事件*/

    $(".search_btn").click(function(){
        //上述方法等价于
        table.reload('busCarListTable', {
            where: { //设定异步数据接口的额外参数，任意设
                "flowernumber":$("input[name='carnumber']").val(),
                "flowertype": $("input[name='cartype']").val(),
                "color": $("input[name='color']").val(),
                "description": $("input[name='description']").val(),
                "issaleing":$("#isrenting").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        }); //只重载数据
    });

    /*绑定头部工具栏*/
    //头工具栏事件
    table.on('toolbar(busCars)', function(obj){

        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus.data)
        switch(obj.event){
            case 'add':
                //alert("添加OK");
                openAddOrUpdateBusCarDialog();
                break;
            case 'del':
                //删除
                deleteBusCar(checkStatus.data);
                break;

        };
    });


    /**
     * 删除
     */
    function  deleteBusCar(data){
        if(data.length==0){
            layer.msg("请选择要删除的数据?");
            return ;
        }

        //发送ajax删除
        layer.confirm("你确定要删除这些数据吗？",{
            btn:["确定","取消"],
        },function(index){
            //关闭弹出框
            layer.close(index);
            //收集数据
            var ids=[];
            //循环
            for (var i = 0; i <data.length ; i++) {
                ids.push(data[i].carnumber);
            }
            //ids=1&ids=2
            console.log(ids.toString()+"<<<")
            //发送ajax删除
            $.ajax({
                type:"post",
                url:ctx+"/bus_car/delete",
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

    /**
     * 添加，更新的函数
     * @param saleChanceId
     */
    function openAddOrUpdateBusCarDialog(busCarNumber){
        var title="<h3>花卉添加模块</h3>";
        var url=ctx+"/bus_car/addOrUpdateBusCarPage";
        //判断
        console.log(busCarNumber);
        if(busCarNumber){
            title="<h3>花卉更新模块</h3>";
            url+="?busCarNumber="+busCarNumber;
            console.log(url)
        }

        /*弹出层*/
        layui.layer.open({
            title:title,
            type:2,
            content:url,
            area:["500px","620px"],
            maxmin:true
        })

    }




    /*绑定行内工具栏*/
    //监听行工具事件
    table.on('tool(busCars)', function(obj){
        var data = obj.data;

        console.log(obj)
        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                //关闭弹出层
                layer.close(index);
                //发送ajax删除
                $.ajax({
                    type:"post",
                    url:ctx+"/bus_car/delete",
                    data:{"ids":data.flowernumber},
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
        } else if(obj.event === 'edit'){
            //传入当前对象的id
            openAddOrUpdateBusCarDialog(data.flowernumber);
            console.log(data.flowernumber)
        }

    });


});