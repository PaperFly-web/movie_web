var second=60;
$('.form').find('input, textarea').on('keyup blur focus', function (e) {
  
  var $this = $(this),
      label = $this.prev('label');

	  if (e.type === 'keyup') {
			if ($this.val() === '') {
          label.removeClass('active highlight');
        } else {
          label.addClass('active highlight');
        }
    } else if (e.type === 'blur') {
    	if( $this.val() === '' ) {
    		label.removeClass('active highlight'); 
			} else {
		    label.removeClass('highlight');   
			}   
    } else if (e.type === 'focus') {
      
      if( $this.val() === '' ) {
    		label.removeClass('highlight'); 
			} 
      else if( $this.val() !== '' ) {
		    label.addClass('highlight');
			}
    }

});

$('.tab a,.links a').on('click', function (e) {
  
  e.preventDefault();
  
  $(this).parent().addClass('active');
  $(this).parent().siblings().removeClass('active');
  
  target = $(this).attr('href');

  $('.tab-content > div').not(target).hide();
  
  $(target).fadeIn(600);
  
});
function openme(){
    document.getElementById('div1').style.display='block';
    document.getElementById('div2').style.display='block';
}
function closeme(){
    document.getElementById('div1').style.display='none';
    document.getElementById('div2').style.display='none';
}
function logo_in(){alert()
    closeme();
}
$(function () {
    var pwdFlag=false;//判断密码是否一致
    var signFlag=false;//判断是否可以登录
    var rexPwd=false;//判断密码是否符合规范
    var register=$(".register");
    var signin=$(".sign-in");
    //注册验证密码强度
    $("#password").keyup(function () {
        var alertMsg=$("#alertMsg");
        var pwd=$("#password").val();
        var rex=/^\S{6,}$/;
        if(!rex.test(pwd)){
            // alert("密码bu符合规范")
            rexPwd=false;
            if(pwd!=''){
                alertMsg.text("密码不符合规定!")
            }

        }else {
            // alert("密码符合规范")
            rexPwd=true;
            alertMsg.text("")
        }
    });
    //注册验证密码是否一致
    $("#re-password").keyup(function () {
        var pwd=$("#password").val();
        var repwd=$("#re-password").val();
        var alertMsg=$("#alertMsg");
        if(pwd!=repwd){
            if(repwd!=''){
                alertMsg.text("密码不一致!")
            }
            pwdFlag=false;
        }else {
            pwdFlag=true;
            if(signFlag&&rexPwd){//如果手机号和密码都符合规范，可以注册
                register.removeAttr("disabled")
            }
            alertMsg.text("")
        }
    })
    //注册验证手机号
    $("#sign-up-no").keyup(function () {
        var no=$("#sign-up-no").val();
        var rex=/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/;
        var flag = rex.test(no);
        var alertMsg=$("#alertMsg");
        if(!flag){
            // alertMsg.removeAttr("visibility");
            signFlag=false;
            if(no!=''){
                alertMsg.text("您输入的不是手机号!");
            }

        }else {
            signFlag=true;
            alertMsg.text("");
            if(pwdFlag&&rexPwd){//如果密码验证和密码规范都正确，可以注册
                register.removeAttr("disabled");
            }

        }
    });
    //登录的验证
    $("#sign-in-no").keyup(function () {
        var no=$("#sign-in-no").val();
        var rex=/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/;
        var flag = rex.test(no);
        var alertMsg=$("#alertMsg");
        if(!flag){//如果手机号正确可以登录
            if(no!=''){
                alertMsg.text("您输入的不是手机号!");
            }
            signin.attr("disabled","disabled");
        }else {
            signin.removeAttr("disabled");
            alertMsg.text("");
        }
    });
    $(function () {//浏览器记住密码后，直接在这里验证
        var no=$("#sign-in-no").val();
        var rex=/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/;
        var flag = rex.test(no);
        var alertMsg=$("#alertMsg");
        if(!flag){//如果手机号正确可以登录
            signin.attr("disabled","disabled");
        }else {
            signin.removeAttr("disabled");
            alertMsg.text("");
        }
    });
    $(".register").click(function () {
        var pwd=$("#password").val().trim();
        var userName=$("#userName").val().trim();
        var no=$("#sign-up-no").val().trim();
        regist(userName,no,pwd);
    })

});
function regist(userName,userPhone,userPwd){
    $.ajax({
        type:"post",
        url:"/regist",
        dataType:"json",
        data:"userName="+userName+"&userPhone="+userPhone+"&userPwd="+userPwd,
        success:function (data) {
            if(data.code!=200){
                error(data.message);
            }else {
                success(data.message);
            }
        }
    });
}
//显示后端传过来的消息
$(function () {
    var backTipsCode= $(".backTipsCode");
    var backTipsCodeVal=backTipsCode.val();
    var backTipsMsg=$(".backTipsMsg");
    var backTipsMsgVal=backTipsMsg.val();
    if(backTipsCodeVal!='111'){
        if(backTipsCodeVal=='200'){
            success(backTipsMsgVal)
        }else {
            warning(backTipsMsgVal)
        }
    }

});
