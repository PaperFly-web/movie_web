var page=0;
var isLastPage=false;
var recordOrView=true;
var uri="/selectMyselfMoves";
$(function () {
    var prePage=$("#prePage");
    var nextPage=$("#nextPage");
    var tbody=$("tbody");
    var uploadRecord=$("#uploadRecord");
    var viewHistory=$("#viewHistory");
    uploadRecord.click(function () {
        page=0;
        isLastPage=false;
        uri="/selectMyselfMoves";
        recordOrView=true;
        ajax();
    });
    viewHistory.click(function () {
        page=0;
        isLastPage=false;
        uri="/selectViewsHistory";
        recordOrView=false;
        ajax();
    });
    prePage.click(function () {
       if(page==0){
           warning("已经是第一页了");
           return;
       } else if(page>0){
           page=page-1;
           ajax();
       }else {
           error("系统忙")
       }
    });
    nextPage.click(function () {
        if(isLastPage){
            warning("已经是最后一页了");
            return;
        }
        if(page>=0){
            page=page+1;
            ajax();
        }else {
            error("系统忙")
        }
    });
    function ajax() {
        $.ajax({
            url:uri,
            dataType:"json",
            type:"post",
            data:"page="+page,
            success:function (data) {
                if(data.code==404){
                    warning("没有发现资源");
                    isLastPage=true;
                    tbody.html("");
                    return;
                }else if(data.code==201){
                    isLastPage=true;
                }else {
                    isLastPage=false;
                }
                tbody.html("");
                if(recordOrView){
                    $.each(data.data,function (i,n) {
                            append(n.mid,n.mname,timeFmt(n.mcreateTime),n.misPass)
                        }
                    );
                }else {
                    $.each(data.data,function (i,n) {
                            append(n.mid,n.move.mname,timeFmt(n.vcreateTime),'无')
                        }
                    );
                }

            }
        })
    }
    function append(mId,mName,mCreateTime,mIsPass) {

        tbody.append("<tr >\n" +
            "                    <td >\n" +
            ""+mId+"\n" +
            "                    </td>\n" +
            "                    <td >\n" +
            ""+mName+"\n" +
            "                    </td>\n" +
            "                    <td >\n" +
            ""+mCreateTime+"\n" +
            "                    </td>\n" +
            "                    <td >\n" +
            ""+mIsPass+"\n" +
            "                    </td>\n" +
            "                </tr>")
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
    var time=year+'-'+month+'-'+day+' '+hh+':'+mm+':'+ss;
    return time;
};