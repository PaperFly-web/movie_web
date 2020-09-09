

//轮播图
var main_swiper = new Swiper('.swiper-banner.swiper-container', {
    direction: 'horizontal',
    slidesPerView: 1,
    pagination: {
      el: '.swiper-pagination',
      clickable: true,
    },
    on:{
        slideChangeTransitionStart: function(){
          $('#g'+(this.activeIndex+1)).addClass('active').siblings().removeClass('active');
        },
        init: function(){
            swiperAnimateCache(this); //隐藏动画元素 
            swiperAnimate(this); //初始化完成开始动画
        }, 
        slideChangeTransitionEnd: function(){ 
            swiperAnimate(this); //每个slide切换结束时也运行当前slide动画
        }
    }
});
var lis = $('.swiper-nav').find('li');
lis.each(function(i,el){
    $('#g'+(i+1)).mouseover(function(){
        main_swiper.slideTo(i, 1000);
        $(el).addClass('active').siblings().removeClass('active');
    });
});

$(function () {
    selectMoveForIndex();
});
function selectMoveForIndex() {
	$.ajax({
		type:"post",
		dataType:"json",
		url:"/selectMoveForIndex",
		success:function (data) {
			if(data.code!='444'){
				/*var moveData=JSON.parse(JSON.stringify(data.data));
                alert(JSON.stringify(data.data));*/
		/*		alert(data.data[0].mname);*/
                for(var i=0;i<data.data.length;i++){
					appendMove(i+1,data.data[i].mfalsePicSavePath,
						data.data[i].mfalseSavePath,data.data[i].mname,"",data.data[i].mid)
                }
			}else {
				alert(data.message);
			}

        }
	})
}
function appendMove(n,mPicPath,mPath,mName,mDetailLink,mId) {
    var img=$("#img"+n);
    var playBtn=$("#playBtn"+n);
    var moveName=$("#moveName"+n);
    var moveDetail=$("#moveDetail"+n);
    img.attr("src",mPicPath);
    playBtn.attr("onclick","window.open('/toShowMove?mId="+mId+"&mName="+mName+"&mFalseSavePath="+encodeURIComponent(mPath)+"','_self');");
    moveName.text(mName);
    moveDetail.attr("href",encodeURIComponent(mDetailLink));
}