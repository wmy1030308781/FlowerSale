layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    /*添加表单添加信息*/
    form.on("submit(addOrUpdatebusrent)",function(data){
        //提交的加载层
        var index=layer.msg("数据提交中，请稍后...",{
            icon:16,
            time:true,
            shade:0.8
        });

        //提交数据url
        var url=ctx+"/bus_rent/save";

        //发送ajax添加
       $.post(url,data.field,function(data){
           if(data.code==200){
                //添加成功了
               layer.msg("添加成功了");
               //关闭加载层
               layer.close(index);
               //iframe
               layer.closeAll("iframe");
               //重新加载
               parent.location.reload();
           }else{
               //失败了
               layer.msg(data.msg);
           }
       },"json");
        //阻止跳转
        return false;
    });

    /*取消*/
    $("#closeBtn").click(function (){
        //假设这是iframe页
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
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
        //layer.msg(rentflag)
        if(rentflag==0) {
            $("#rentflag").val("未出售")
        } else if(rentflag==1) {
            $("#rentflag").val("已出售")
        } else {
            $("#rentflag").val("未知")
        }
    }

    $(function (){
        // 格式化归还状态
        var rentflag=$("#rentflag").val()
        formatterRentflag(rentflag)

        /*发送ajax追加下拉框*/
        var carnumber= $("input[name='carnumber']").val();
        $.get(ctx+"/bus_car/cars",function(data){
            //遍历
            for (var x in data) {
                if(carnumber==data[x].carnumber){
                    $("#carnumber").append("<option value='"+data[x].flowernumber+"' selected>"+data[x].flowernumber+"</option>");
                }else{
                    $("#carnumber").append("<option value='"+data[x].flowernumber+"'>"+data[x].flowernumber+"</option>");
                }
            }
            //重新渲染select
            layui.form.render("select");
        },"json");
    })

});