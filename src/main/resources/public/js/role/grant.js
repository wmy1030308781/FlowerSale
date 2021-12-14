
var zTreeObj;//权限树
$(function () {
    loadTree();
});

function loadTree() {
    var roleId= $("#roleId").val();
    $.ajax({
        type:"post",
        url:ctx+"/menu/getTree?roleId="+roleId,
        dataType:"json",
        success:function (data) {
            var setting ={
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                view:{
                    showLine: true,
                    //showIcon: true
                },
                check: {
                    enable: true,
                    chkboxType: { "Y": "ps", "N": "ps" }
                },
                // 回调函数
                callback:{
                    //监听单选框的钩取
                    onCheck: zTreeOnCheck
                }
            };

            //三个参数分别是,树的元素id,设定,数据(此行本来是放在预加载函数里),这一步是初始化权限树
            zTreeObj=$.fn.zTree.init($("#test1"),setting,data);
        }
    })
}

function zTreeOnCheck() {
   var roleId= $("#roleId").val();//拿到当前要操作的角色id
    console.log(roleId);
   console.log( zTreeObj.getCheckedNodes());
    var checkedNodes=zTreeObj.getCheckedNodes()//选中的模块id
    var mids=[];//为请求的参数拼接做准备
    for (var x in checkedNodes) {
        mids.push(checkedNodes[x].id);
    }

    // console.log(mids);
    //发送授权的请求
    $.ajax({
        type: "post",
        url:ctx+"/role/grant",
        data:{
            "mids":mids.toString(),
            "roleId":roleId
        },
        dataType: "json",
        success:function (data) {
            if(data.code==200){
               alert(data.msg)
            }else {
                alert(data.msg)
            }
        }
    });
}