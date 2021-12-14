layui.use(['table', 'layer'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    /**
     * 用户列表展示
     */
    var tableIns = table.render({
        elem: '#carInfoList',
        url: ctx + '/bus_car/infoList',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "carListTable",
        cols: [[
            {field: "flowernumber", title: '花名', fixed: "true", width: 100},
            {field: 'flowertype', title: '种类', minWidth: 40, align: "center"},
            {field: 'color', title: '颜色', minWidth: 80, align: 'center'},
            // {field: 'Purchaseprice', title: '进价', minWidth: 80, align: 'center'},
            {field: 'price', title: '出售价格', align: 'center'},
            {field: 'deposit', title: '批发价', align: 'center', minWidth: 80},
            {field: 'issaleing', title: '出售状态', align: 'center', minWidth: 80,templet: function (d){
                    if (d.isrenting == 0){

                        return "<div style='color: red'>未出售</div>";
                    }else {
                        return "<div style='color: blue'>已出售</div>";
                    }
                }},
            {field: 'description', title: '出售描述', align: 'center', minWidth: 80},
            {field: 'flowerimg', title: '缩略图', align: 'center', minWidth: 80,templet: function (d){
                    return "<div id='img'><img src = '../images/car.jpg' width='50px' height='25px'' /></div>"
                }},
            {field: 'createtime', title: '录入时间', align: 'center', minWidth: 100},
            {title: '操作', minWidth: 200, templet: '#userListBar', fixed: "right", align: "center"}
        ]]
    });

    /*搜索操作*/
    $(".search_btn").click(function () {
        table.reload("carListTable", {
            page: {
                curr: 1
            },
            where: {
                "identity": $("input[name='identity']").val(),
            }
        })
    });

    // /*重置操作*/
    // $(".reset-btn").click(function () {
    //
    // });

    /*绑定行内工具栏*/
    table.on("tool(cars)", function (obj) {
        if (obj.event == 'show') {
            //查看大图
            showThePhoto();
        } else if (obj.event == 'rent') {
            //出租汽车
            deleteUserById(obj.data.id);
        }
    });


    function showThePhoto(){

    }

});