//信息提示弹出层（图标）
function success(msg){ fyAlert.msg(msg,{icon:1,animateType:1} )};
function warning(msg){ fyAlert.msg(msg,{icon:2,animateType:2} )};
function error(msg){ fyAlert.msg(msg,{icon:3,animateType:3} )};
function load1(msg){ fyAlert.msg(msg,{icon:4,animateType:4} )};
function load2(msg){ fyAlert.msg(msg,{icon:5,animateType:4} )};


//内容提示弹出层
function content1(){
    fyAlert.alert({
        title:'文本提示框',
        content: '您觉得这个这个弹框动画怎么样',
        btns    : {                  //按钮组
            '很好' : function(obj){
                obj.destory();   //在页面上
                demo1();
            },
            '不好' : function(obj){
                obj.destory(); //销毁
                demo1();
            }
        },
    })
}
function content2(){
    fyAlert.alert({
        title:'DOM提示框',
        // area : ['250px','auto'],
        content: $("#contentText"),
        btns    : {                  //按钮组
            '很好' : function(obj){
                obj.destory();   //在页面上
                demo1();
            },
            '不好' : function(obj){
                obj.destory(); //销毁
                demo1();
            }
        },
    })
}
function content3(){
    fyAlert.alert({
        type:2,
        title:'文本提示框',
        animateType : 1,
        area : ['380px','90%'],
        content: 'https://www.baidu.com',
        btns    : {                  //按钮组
            '很好' : function(obj){
                obj.destory();   //在页面上
                demo1();
            },
            '不好' : function(obj){
                obj.destory(); //销毁
                demo1();
            }
        },
    })
}


//方向弹出层
function bottomFun(){
    fyAlert.alert({
        type:2,
        minmax:true,
        animateType : 1,
        direction:['center','bottom'],
        area : ['100%','50%'],
        content: 'https://www.baidu.com',
    })
}
function topFun(){
    fyAlert.alert({
        type:2,
        minmax:true,
        animateType : 2,
        direction:['center','top'],
        area : ['100%','50%'],
        content: 'https://www.baidu.com',
    })
}
function leftFun(){
    fyAlert.alert({
        type:2,
        minmax:true,
        animateType : 3,
        direction:['left','top'],
        area : ['400px','100%'],
        content: 'https://www.baidu.com',
    })
}
function rightFun(){
    fyAlert.alert({
        type:2,
        minmax:true,
        animateType : 4,
        direction:['right','top'],
        area : ['400px','100%'],
        content: 'https://www.baidu.com',
    })
}


//可拖动弹出层
function drag(){
    fyAlert.alert({
        title   : "点击拖动",
        drag    : true,
        content : '您如何看待开发人员',
        btns    : {                  //按钮组
            '确定' : function(obj){
                obj.destory();   //在页面上
                demo1();
            },
            '取消' : function(obj){
                obj.destory(); //销毁
                demo2();
            }
        },
    })
}

//自定义弹出层动画
function aniExtend(){
    fyAlert.alert({
        title   : "自定义弹出动画",
        aniExtend:'lightSpeedIn',
        content : '自定义弹出动画，隐藏动画添加_hide <p>例:显示动画为 opaicty;隐藏动画就为 opacity_hide</p> ',
        btns    : {                  //按钮组
            '确定' : function(obj){
                obj.destory();   //在页面上
                demo1();
            },
            '取消' : function(obj){
                obj.destory(); //销毁
                demo2();
            }
        },
    })
}

//开启全屏按钮
function minmaxScreen(){
    fyAlert.alert({
        minmax   : true,
        content  : '点击确定关闭弹框...',//$("#contentText"),    //内容
        btns     : {                  //按钮组
            '确定' : function(obj){
                obj.destory();   //在页面上
            }
        }
    })
}


//另一种风格
function skin(){
    fyAlert.alert({
        closeBtn : false,
        skin     : 'fyAlert-blue',
        content  : '点击确定关闭弹框，怎么样好看吧...',//$("#contentText"),    //内容
        btns     : {                  //按钮组
            '确定' : function(obj){
                obj.destory();   //在页面上
                skin2();
            }
        }
    })
}

//另一种风格
function skin2(){
    fyAlert.alert({
        closeBtn : true,
        skin     : 'fyAlert-green',
        content  : '点击右上角关闭弹框，怎么样好看吧...',//$("#contentText"),    //内容
        btns     : {                  //按钮组
            '确定' : function(obj){
                obj.destory();   //在页面上
                skin();
            }
        }
    })
}

function addMesk(){
    fyAlert.alert({
        minmax   : true,
        type     : 1,
        area     : ['450px','80%'],
        content  : '<div>寄过来送过来人生酒馆老人家管理局商量个事据了解厉害个人理解过来诗歌朗诵几个老师井冈山路老师给老师干活了施工临时 <button onclick="skin()">弹出框提示</button> </div>',//$("#contentText"),    //内容
        btns     : {                  //按钮组
            '确定' : function(obj){
                console.log(obj);
                obj.destory();   //在页面上
            },
            '取消' : function(obj){
                obj.destory(); //销毁
            }
        }
    })
}