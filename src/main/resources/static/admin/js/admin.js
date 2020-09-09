var page=0;
var isLastPage=false;
var infoOrPermOrStatusAjax=1;
$(function () {
   var last_regist_count= $("#last-regist-count");
    var today_user_login_count= $("#today-user-login-count");
    var user_count= $("#user-count");
    var last_movie_count= $("#last-movie-count");
    var movie_count= $("#movie-count");
    var pass_movie_count= $("#pass-movie-count");
    var no_pass_movie_count= $("#no-pass-movie-count");
    var tbody=$("tbody");
    var thead=$("thead");
    var prePage=$("#prePage");
    var nextPage=$("#nextPage");
    var modifyUserStatus=$("#modifyUserStatus");
    var modifyUserPerm=$("#modifyUserPerm");
    var inputUserPerm=$("#inputUserPerm");
   $.ajax({
       type:"post",
       dataType:"json",
       url:"/admin/selectMovieWebData",
       success:function (data) {
           last_regist_count.text(data.data.lastRegistCount);
           today_user_login_count.text(data.data.todayUserLoginCount);
           user_count.text(data.data.userCount);
           last_movie_count.text(data.data.lastMovieCount);
           movie_count.text(data.data.movieCount);
           pass_movie_count.text(data.data.passMovieCount);
           no_pass_movie_count.text(data.data.noPassMovieCount);
       }
   });

    prePage.click(function () {
        if(page==0){
            warning("已经是第一页了");
            return;
        } else if(page>0){
            page=page-1;
            userAjaxInfo();
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
            page=page-1;

            userAjaxInfo();


        }else {
            error("系统忙")
        }
    });
    userAjaxInfo();

    modifyUserPerm.click(function () {
        inputUserPerm.html("<form action=\"/admin/updateUserPerm\" method=\"post\" class=\"form-horizontal\" role=\"form\">\n" +
            "                <div class=\"form-group\">\n" +
            "                    <label for=\"inputEmail3\" class=\"col-sm-2 control-label\">用户Id</label>\n" +
            "                    <div class=\"col-sm-10\">\n" +
            "                        <input required  name=\"userId\" type=\"text\"   class=\"form-control\" id=\"inputEmail3\" />\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"form-group\">\n" +
            "                    <label for=\"inputPassword3\" class=\"col-sm-2 control-label\">权限</label>\n" +
            "                    <div class=\"col-sm-10\">\n" +
            "                        <input required name='userPerm' type=\"text\" class=\"form-control\"  />\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "<br>"+
            "                <div class=\"form-group\">\n" +
            "                    <div class=\"col-sm-offset-2 col-sm-10\">\n" +
            "                        <button type=\"submit\" class=\"btn btn-default\">提交修改</button>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </form>")
    });
    modifyUserStatus.click(function () {
        inputUserPerm.html("<form action=\"/admin/updateUserStatus\" method=\"post\" class=\"form-horizontal\" role=\"form\">\n" +
            "                <div class=\"form-group\">\n" +
            "                    <label for=\"inputEmail3\" class=\"col-sm-2 control-label\">用户Id</label>\n" +
            "                    <div class=\"col-sm-10\">\n" +
            "                        <input required  name=\"userId\" type=\"text\"   class=\"form-control\" id=\"inputEmail3\" />\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"form-group\">\n" +
            "                    <div class=\"col-sm-10\">\n" +
            "                        正常:<input required  name=\"userStatus\" type=\"radio\"  class=\"form-control\" value='1' />\n" +
            "                        冻结:<input required  name=\"userStatus\" type=\"radio\"   class=\"form-control\" value='0' />\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "<br>"+
            "                <div class=\"form-group\">\n" +
            "                    <div class=\"col-sm-offset-2 col-sm-10\">\n" +
            "                        <button type=\"submit\" class=\"btn btn-default\">提交修改</button>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </form>")
    });


    function userAjaxInfo() {
        $.ajax({
            url:"/admin/selectUsers",
            dataType:"json",
            data:"page="+page,
            type:"post",
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
                $.each(data.data,function (i,n) {//这个是点击分页数字追加
                    appendUserInfo(n.userId,n.userName,n.userPhone,timeFmt(n.userCreateTime),n.userStatus,n.userPerm);
                    }
                );
            }
        })
    }
    function appendUserInfo(userId,userName,userPhone,userCreateTime,userStatus,userPerm) {
        thead.html("<tr>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t用户编号\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t用户名\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t手机号\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t注册时间\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t状态\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t权限\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t</tr>");
        tbody.append("<tr >\n" +
            "                    <td >\n" +
            ""+userId+"\n" +
            "                    </td>\n" +
            "                    <td >\n" +
            ""+userName+"\n" +
            "                    </td>\n" +
            "                    <td >\n" +
            ""+userPhone+"\n" +
            "                    </td>\n" +
            "                    <td >\n" +
            ""+userCreateTime+"\n" +
            "                    </td>\n" +
            "                    <td >\n" +
            ""+userStatus+"\n" +
            "                    </td>\n" +
            "                    <td >\n" +
            ""+userPerm+"\n" +
            "                    </td>\n" +
            "                </tr>\n")
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
