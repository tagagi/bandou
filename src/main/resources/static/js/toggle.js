$('.toggle').click(function(){
    const slide=$('.slide').eq(0);
    slide.width()==0?slide.width(200):slide.width(0);
});