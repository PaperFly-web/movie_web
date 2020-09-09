var page=0;
var isLastPage=false;
$(function () {
    var inputUserPerm=$("#inputUserPerm");//用于展示数据的区域
    var userSuggest=$("#userSuggest");
    var suggestTbody;
    userSuggest.click(function () {
        ajax();
    });

    inputUserPerm.on("click","#suggestPrePage",function () {
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
    inputUserPerm.on("click","#suggestNextPage",function () {
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
           url:"/selectSuggest",
           dataType:"json",
           type:"post",
           data:"page="+page,
           success:function (data) {
               htmlHead();
               suggestTbody=$("#suggestTbody");
               if(data.code==404){
                   warning("没有发现资源");
                   isLastPage=true;
                   htmlPage();
                   return;
               }else if(data.code==201){
                   isLastPage=true;
               }else {
                   isLastPage=false;
               }
               $.each(data.data,function (i,n) {
                   append(n.succId,n.succUserName,n.succPhone,n.succContent,timeFmt(n.succCreateTime));
                   }
               );
               htmlPage();
           }
       })
   }
   function append(id,name,phone,content,time) {
       suggestTbody.append("<tr>" +
           "<td>"+id+"</td>" +
           "<td>"+name+"</td>" +
           "<td>"+phone+"</td>" +
           "<td>"+content+"</td>" +
           "<td>"+time+ "</td><" +
           "/tr>")
   }
   function htmlHead() {
       inputUserPerm.html("<table class=\"table\">\n" +
           "\t\t\t\t\t<thead>\n" +
           "\t\t\t\t\t\t<th>建议编号</th>\n" +
           "\t\t\t\t\t\t<th>建议人</th>\n" +
           "\t\t\t\t\t\t<th>联系电话</th>\n" +
           "\t\t\t\t\t\t<th>建议内容</th>\n" +
           "\t\t\t\t\t\t<th>创建时间</th>\n" +
           "\t\t\t\t\t</thead>\n" +
           "<tbody id='suggestTbody'>"+
       "   </tbody>    " +
       "</table>" )
   }
   function htmlPage() {
       inputUserPerm.append(
           "     <ul class=\"pagination\">\n" +
           "                <li>\n" +
           "                    <a id=\"suggestPrePage\" >上一页</a>\n" +
           "                </li>\n" +
           "                <li>\n" +
           "                    <a id=\"suggestNextPage\" >下一页</a>\n" +
           "                </li>\n" +
           "            </ul>")
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
    var time=year+'-'+month+'-'+day+" "+hh+":"+mm+":"+ss;
    return time;
};