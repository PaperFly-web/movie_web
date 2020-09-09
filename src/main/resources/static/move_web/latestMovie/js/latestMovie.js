var page;
$(function () {
    var dy_video_list=$(".dy-video-list");
    $("#pagination3").on("click",".ui-pagination-page-item" ,function() {

        var info = $("#pagination3").pagination("getPage");
        page=info.current;
        $.ajax({
            dataType:"json",
            type:"POST",
            data:"page="+(parseInt(page)-1),
            url:"/selectMovesByTime",
            success:function (data) {
                if(data.code==404){
                    setPageCount(1);
                    setShowPageCount(1);
                    warning("已经是最后一页了");
                    return;
                }else if(data.code==200){
                    setPageCount(data.data[0].count);
                    setShowPageCount(7);
                }else if(data.code==201){
                    setPageCount(data.data[0].count);
                    setShowPageCount(data.data[0].count);
                }

                //到这里就代表查询的有内容
                if(data.message=="用户没登录"){//用户没登录的显示
                    dy_video_list.html("");
                    $.each(data.data,function (i,n) {
                            noLoginAppend(n.mname,n.mhot,n.mid,n.mduration,n.user.userName,
                                n.mdirect,n.mmainRole,n.mcreateTime,n.mstory,
                                n.mtype,n.marea,
                                n.mfalsePicSavePath,n.mfalseSavePath,n.mpublicTime)
                        }
                    );
                }else {//用户登录的显示
                    dy_video_list.html("");
                    $.each(data.data,function (i,n) {
                            loginAppend(n.mname,n.mhot,n.mid,n.mduration,n.user.userName,
                                n.mdirect,n.mmainRole,n.mcreateTime,n.mstory,
                                n.mtype,n.marea,
                                n.mfalsePicSavePath,n.mfalseSavePath,n.mpublicTime)
                        }
                    );
                }

            }
        })
    });
    $("#pagination3").on("click",".ui-pagination-page-btn" ,function() {
        var info = $("#pagination3").pagination("getPage");
        page=info.current;
        $.ajax({
            dataType:"json",
            type:"post",
            data:"page="+(parseInt(page)-1),
            url:"/selectMovesByTime",
            success:function (data) {

                if(data.code==404){
                    setPageCount(1);
                    setShowPageCount(1);
                    warning("已经是最后一页了");
                    return;
                }else if(data.code==200){
                    setPageCount(data.data[0].count);
                    setShowPageCount(7);
                }else if(data.code==201){
                    setPageCount(data.data[0].count);
                    setShowPageCount(data.data[0].count);
                }

                //到这里就代表查询的有内容
                if(data.message=="用户没登录"){//用户没登录的显示
                    dy_video_list.html("");
                    $.each(data.data,function (i,n) {
                            noLoginAppend(n.mname,n.mhot,n.mid,n.mduration,n.user.userName,
                                n.mdirect,n.mmainRole,n.mcreateTime,n.mstory,
                                n.mtype,n.marea,
                                n.mfalsePicSavePath,n.mfalseSavePath,n.mpublicTime)
                        }
                    );
                }else {//用户登录的显示
                    dy_video_list.html("");
                    $.each(data.data,function (i,n) {
                            loginAppend(n.mname,n.mhot,n.mid,n.mduration,n.user.userName,
                                n.mdirect,n.mmainRole,n.mcreateTime,n.mstory,
                                n.mtype,n.marea,
                                n.mfalsePicSavePath,n.mfalseSavePath,n.mpublicTime)
                        }
                    );
                }
            }
        })
    });

    function loginAppend(mname,mhot,mid,mduration,userName,
                         mdirect,mmainRole,mcreateTime,mstory,
                         mtype,marea,
                         mfalsePicSavePath,mfalseSavePath,mpublicTime) {
        dy_video_list.append("<li  data- class=\"dy-video-item dy-video-meta-right\">\n" +
            "              <div class=\"dy-video-meta\">\n" +
            "                <div class=\"dy-video-meta-bg\"> </div>\n" +
            "                <div class=\"dy-video-meta-dy\">\n" +
            "                  <h4 class=\"dy-video-title\"> <a> "+mname+" </a> </h4>\n" +
            "                  <ul class=\"dy-video-meta-list\">\n" +
            "                    <li class=\"dy-video-actors\"> <span class=\"dy-video-tip\"> 时长: </span> <a >"+mduration+"</a> </li>\n" +
            "                    "+
            "                    <li class=\"dy-video-actors\"> <span class=\"dy-video-tip\"> 上传视频人: </span> <a >"+userName+"</a> </li>\n" +
            "                    <li class=\"dy-video-actors\"> <span class=\"dy-video-tip\"> 导演: </span> <a ></a>"+mdirect+" </li>\n" +
            "                    <li class=\"dy-video-actors\"> <span class=\"dy-video-tip\"> 主演: </span> <a ></a> "+mmainRole+"</li>\n" +
            "                    <li class=\"dy-video-types\"> <span class=\"dy-video-tip\">类型:</span> <span class=\"dy-video-meta-filter\" >"+mtype+"&nbsp;</span> </li>\n" +
            "                    <li class=\"dy-video-areas\"> <span class=\"dy-video-tip\">地区:</span> <span class=\"dy-video-meta-filter\" >"+marea+"</span> </li>\n" +
            "                    <li class=\"dy-video-starts\"> <span class=\"dy-video-tip\">年代:</span> <span class=\"dy-video-meta-filter\" >"+timeFmt(mpublicTime)+"</span> </li>\n" +
            "                  </ul>\n" +
            "                  <p class=\"dy-video-intro\" > "+mstory+" </p>\n" +
            "                </div>\n" +
            "                <div class=\"dy-video-meta-bg\"> </div>\n" +
            "              </div>\n" +
            "              <div class=\"dy-video-poster\">\n" +
            "                <a href=\"/toShowMove?mId="+mid+"&mName="+mname+"&mFalseSavePath="+mfalseSavePath+"\"  class=\"dy-video-link\">\n" +
            "                  <img class=\"dy-video-img\" src='"+mfalsePicSavePath+"' alt='"+mname+"' />\n" +
            "                  <span class=\"dy-video-complete\"></span>\n" +
            "                  <span class=\"dy-video-date\" > "+timeFmt(mpublicTime)+"</span>\n" +
            "                  <span class=\"dy-video-bg\"></span>\n" +
            "                </a>\n" +
            "              </div>\n" +
            "              <div class=\"dy-video-primary\">\n" +
            "                <h4 class=\"dy-video-title\"><a > "+mname+" </a> </h4>\n" +
            "                <span class=\"dy-video-rating\" >热度:"+mhot+"  </span> </div>\n" +
            "            </li>")
    }
    function noLoginAppend(mname,mhot,mid,mduration,userName,
                           mdirect,mmainRole,mcreateTime,mstory,
                           mtype,marea,
                           mfalsePicSavePath,mfalseSavePath,mpublicTime) {
        dy_video_list.append("<li  data- class=\"dy-video-item dy-video-meta-right\">\n" +
            "              <div class=\"dy-video-meta\">\n" +
            "                <div class=\"dy-video-meta-bg\"> </div>\n" +
            "                <div class=\"dy-video-meta-dy\">\n" +
            "                  <h4 class=\"dy-video-title\"> <a> "+mname+" </a> </h4>\n" +
            "                  <ul class=\"dy-video-meta-list\">\n" +
            "                    <li class=\"dy-video-actors\"> <span class=\"dy-video-tip\"> 时长: </span> <a >"+mduration+"</a> </li>\n" +
            "                    "+
            "                    <li class=\"dy-video-actors\"> <span class=\"dy-video-tip\"> 上传视频人: </span> <a >"+userName+"</a> </li>\n" +
            "                    <li class=\"dy-video-actors\"> <span class=\"dy-video-tip\"> 导演: </span> <a ></a>"+mdirect+" </li>\n" +
            "                    <li class=\"dy-video-actors\"> <span class=\"dy-video-tip\"> 主演: </span> <a ></a> "+mmainRole+"</li>\n" +
            "                    <li class=\"dy-video-types\"> <span class=\"dy-video-tip\">类型:</span> <span class=\"dy-video-meta-filter\" >"+mtype+"&nbsp;</span> </li>\n" +
            "                    <li class=\"dy-video-areas\"> <span class=\"dy-video-tip\">地区:</span> <span class=\"dy-video-meta-filter\" >"+marea+"</span> </li>\n" +
            "                    <li class=\"dy-video-starts\"> <span class=\"dy-video-tip\">年代:</span> <span class=\"dy-video-meta-filter\" >"+timeFmt(mpublicTime)+"</span> </li>\n" +
            "                  </ul>\n" +
            "                  <p class=\"dy-video-intro\" > "+mstory+" </p>\n" +
            "                </div>\n" +
            "                <div class=\"dy-video-meta-bg\"> </div>\n" +
            "              </div>\n" +
            "              <div class=\"dy-video-poster\">\n" +
            "                <a href=\"#\" onclick=\"warning('请先登录')\" class=\"dy-video-link\">\n" +
            "                  <img class=\"dy-video-img\" src='"+mfalsePicSavePath+"' alt='"+mname+"' />\n" +
            "                  <span class=\"dy-video-complete\"></span>\n" +
            "                  <span class=\"dy-video-date\" > "+timeFmt(mpublicTime)+"</span>\n" +
            "                  <span class=\"dy-video-bg\"></span>\n" +
            "                </a>\n" +
            "              </div>\n" +
            "              <div class=\"dy-video-primary\">\n" +
            "                <h4 class=\"dy-video-title\"><a > "+mname+" </a> </h4>\n" +
            "                <span class=\"dy-video-rating\" >热度:"+mhot+"  </span> </div>\n" +
            "            </li>")
    }
});
//时间格式化函数
function timeFmt(value) {
    var date=new Date(value);
    var year=date.getFullYear();
    var month=date.getMonth()+1;
    month=month>9?month:('0'+month);
    var day=date.getDate();
    day=day>9?day:('0'+day);
    var hh=date.getHours();
    hh=hh>9?hh:('0'+hh);
    var mm=date.getMinutes();
    mm=mm>9?mm:('0'+mm);
    var ss=date.getSeconds();
    ss=ss>9?ss:('0'+ss);
    var time=year+'-'+month+'-'+day;
    return time;
};
