$(function () {
   var backTipsCode= $("#backTipsCode");
    var backTipsCodeVal=backTipsCode.val();
    var backTipsMsg=$("#backTipsMsg");
    var backTipsMsgVal=backTipsMsg.val();
    if(backTipsCodeVal!='111'){
        if(backTipsCodeVal=='200'){
            success(backTipsMsgVal)
        }else {
            warning(backTipsMsgVal)
        }
    }

});