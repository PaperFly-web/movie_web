var leftBtn =document.getElementById("left");
var rightBtn =document.getElementById("right");
var cont = document.getElementsByClassName("banner")[0];
var items = document.getElementsByClassName("item");
var points = document.getElementsByClassName("point");
var index = 0;
var timer = null;

var clearActive = function(){
    for(var i = 0 ; i<items.length ;i++){
        items[i].className = 'item';
        points[i].className = 'point';
    }
}
var goIndex = function(){
    clearActive();
    items[index].className = 'item active';
    points[index].className= 'point active';
}
right.onclick = function(){
    if(index < items.length-1){
        index++;
    }else{
        index = 0;
    }
    goIndex();
}
left.onclick = function(){
    if(index > 0){
        index --;
    }else{
        index = items.length-1;
    }
    goIndex();
}

timer = setInterval(()=>{
    right.onclick();
},3000)

cont.onmouseover = function(){
    clearInterval(timer);
}
cont.onmouseout = function(){
    timer = setInterval(()=>{
        right.onclick();
    },3000)
}

for(var i = 0 ;i <points.length;i++){
    points[i].addEventListener('click' , function(){
        var ponintIndex = this.getAttribute("data-index");
        // console.log(ponintIndex);
        index = ponintIndex;
        goIndex();
    })
}