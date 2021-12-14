layui.use(['form', 'jquery', 'jquery_cookie', 'table','formSelects'], function () {
    var form = layui.form,
        layer = layui.layer,
        table = layui.table,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);
    var formSelects=layui.formSelects;

    //表单提交事件,addOrUpdateUser是提交按钮的filter
    form.on("submit(addOrUpdateUser)",function (data) {
        var key=data.field;
        var index = layer.msg("数据提交中,请稍后...",{
            icon:16, // 图标
            time:false, // 不关闭
            shade:0.8 // 设置遮罩的透明度
        });
        //确定路径
        var url=ctx+"/user/save";
        //说明隐藏域里有id,是更新
        if($("input[name=userid]").val()){
            url=ctx+"/user/updateUserMsg";
        }
        //发送请求
        $.ajax({
            type:"post",
            url:url,
            data:key,
            dataType:"json",
            success:function (res){
                console.log("ajax<<<<<<<<<<")
                if(res.code==200){
                    layer.msg("操作成功!",{icon:6});
                    layer.close(index);
                    layer.closeAll("iframe");
                    parent.location.reload();
                }else {
                    console.log("ajax<<<<<<<<<<")
                    layer.msg(res.msg,{icon:5})
                }
            }
        });
        return false;
    });

    /**
     * 取消按钮的事件
     */
    $("#closeBtn").click(function (){
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    });


    /**
     * 角色下拉
     * @type {jQuery|string|undefined}
     */
    var id= $("input[name=userid]").val();
    formSelects.config('selectId',{
        type: 'post',
        searchUrl:ctx+'/role/getList?userId='+id,
        keyName: 'rolename',
        keyVal: 'roleid'
    },true);

    /**
     * 性别的回显(没效果)
     */
    // $(function () {
    //     var sex=$("input[name=sexVal]");
    //     // alert(sex.val())
    //     if(sex.val()==1){
    //         // alert(sex.val())
    //         $("#man").prop('checked',true)
    //     }
    //     if(sex.val()==0){
    //         // alert(sex.val())
    //         $("#woman").prop('checked',true)
    //     }
    // });
});