$(function () {
   var backTipsCode=$(".backTipsCode").val();
   var backTipsMsg=$(".backTipsMsg").val();
   if(backTipsCode==200){
       success(backTipsMsg)
   }else if(backTipsCode==444){
       error(backTipsMsg)
   }
});