var page;
var uri="/admin/selectMovie";
var isPassMsg="通过";
var isPass="0";
$(function () {
    var dy_video_list=$(".dy-video-list");
    var pass=$("#pass");
    var noPass=$("#noPass");
    var info = $("#pagination3").pagination("getPage");
    var page=info.current;
    var form=$("#form");
    pass.click(function () {
        uri="/admin/noPassMovie";
        isPassMsg="不通过";
        isPass="1";
        page=info.current;
        form.attr("action","/admin/noPassMovie");
        ajax();
    });
    noPass.click(function () {
        uri="/admin/passMovie";
        isPassMsg="通过";
        isPass="0";
        page=info.current;
        form.attr("action","/admin/passMovie");
        ajax();
    });
    $("#pagination3").on("click",".ui-pagination-page-item" ,function() {
        var info = $("#pagination3").pagination("getPage");
        page=info.current;
        ajax();
    });
    $("#pagination3").on("click",".ui-pagination-page-btn" ,function() {
        var info = $("#pagination3").pagination("getPage");
        page=info.current;
        ajax();
    });
    function ajax() {
        $.ajax({
            dataType:"json",
            type:"post",
            url:"/admin/selectMovie?isPass="+isPass+"&page="+(parseInt(page)-1),
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
                //能到这里就说明有数据了
                dy_video_list.html("");
                $.each(data.data,function (i,n) {//这个是点击分页数字追加
                        append(n.mname,n.mhot,n.misPass,n.mid,n.msize,n.mduration,n.user.userName,n.mcreateTime,n.mstory,n.mfalsePicSavePath,n.mfalseSavePath,n.mpublicTime);
                    }
                );
            }
        })
    }
    function append(mname,mhot,misPass,mid,msize,mduration,userName,mcreateTime,mstory,mfalsePicSavePath,mfalseSavePath,mpublicTime) {
        dy_video_list.append("<li  data- class=\"dy-video-item dy-video-meta-right\">\n" +
            "              <div class=\"dy-video-meta\">\n" +
            "                <div class=\"dy-video-meta-bg\"> </div>\n" +
            "                <div class=\"dy-video-meta-dy\">\n" +
            "                  <h4 class=\"dy-video-title\"> <a> "+mname+" </a> </h4>\n" +
            "                  <ul class=\"dy-video-meta-list\">\n" +
            " <li class=\"dy-video-actors\"> " +
            "<span class=\"dy-video-tip\"> "+isPassMsg+": </span>\n" +
            "                            <input name=\"mId\" type=\"checkbox\" value='"+mid+"'>"+
            " </li>\n" +
            " <li class=\"dy-video-actors\"> " +
            "<span class=\"dy-video-tip\"> 是否审核通过: </span> " +
            "<a href= "+uri+"?&mId="+mid+">点击"+isPassMsg+"</a>" +
            " </li>\n" +
            "                    <li class=\"dy-video-actors\"> <span class=\"dy-video-tip\"> 大小: </span> <a >"+msize+"M</a> </li>\n" +
            "                    <li class=\"dy-video-actors\"> <span class=\"dy-video-tip\"> 时长: </span> <a >"+mduration+"</a> </li>\n" +
            "                    "+
            "                    <li class=\"dy-video-actors\"> <span class=\"dy-video-tip\"> 上传视频人: </span> <a >"+userName+"</a> </li>\n" +
            "                    <li class=\"dy-video-starts\"> <span class=\"dy-video-tip\">上传时间:</span> <span class=\"dy-video-meta-filter\"  >"+timeFmt(mcreateTime)+"</span> </li>\n" +
            "                  </ul>\n" +
            "                  <p class=\"dy-video-intro\" > "+mstory+" </p>\n" +
            "                </div>\n" +
            "                <div class=\"dy-video-meta-bg\"> </div>\n" +
            "              </div>\n" +
            "              <div class=\"dy-video-poster\">\n" +
            "                <a href='/admin/toShowMove?mId="+mid+"&mName="+mname+"&mFalseSavePath="+mfalseSavePath+"'  class=\"dy-video-link\">\n" +
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

