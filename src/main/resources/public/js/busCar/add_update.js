layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;


    /*添加表单添加信息*/
    form.on("submit(addOrUpdateBusCar)",function(data){
        console.log(data.field);
        //提交的加载层
        var index=layer.msg("数据提交中，请稍后...",{
            icon:16,
            time:true,
            shade:0.8
        });
        //提交数据url
        var url=ctx+"/bus_car/save";
        //判断，当前页面的隐藏域有数据，说明做修改操作
        if($("input[name='hcarnumber']").val()){
            console.log("正在更新");
            url=ctx+"/bus_car/update";
        }
        // 发送ajax添加
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


});