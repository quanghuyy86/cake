window.awe = window.awe || {};
awe.init = function () {
    awe.showPopup();
    awe.hidePopup();    
};
$(document).ready(function ($) {
    "use strict";
    awe_backtotop();
    awe_category();
    awe_menumobile();
    awe_lazyloadImage();
    awe_tab();
    $('.product-box-shock .time').each(function(e){
        awe_countDown($(this));
    });
    $('.filter-content .aside-item .aside-title').click(function(e){


        if ($(this).parent().hasClass('active')) {
            $('.filter-content .aside-item').removeClass('active');
        } else {
            $('.filter-content .aside-item').removeClass('active');
            $(this).parent().addClass('active');
        }
    })
});

function awe_lazyloadImage() {
    var ll = new LazyLoad({
        elements_selector: ".lazyload",
        load_delay: 500,
        threshold: 0
    });
}
window.awe_lazyloadImage = awe_lazyloadImage;


$(document).on('click', function(event) {

    if (!$(event.target).closest('aside.aside-item.collection-category').length) {
        $('aside.aside-item.collection-category').removeClass('active');        
    }
    
    if (!$(event.target).closest('.filter-content .aside-item').length) {
        $('.filter-content .aside-item').removeClass('active');     
    }   
    
});
$('.close-pop').click(function() {
    $('#popup-cart').removeClass('opencart');
    $('body').removeClass('opacitycart');
});
$(document).on('click','.overlay, .close-popup, .btn-continue, .fancybox-close', function() {   
    hidePopup('.awe-popup');    
    setTimeout(function(){
        $('.loading').removeClass('loaded-content');
    },500);
    return false;
})


function awe_showLoading(selector) {
    var loading = $('.loader').html();
    $(selector).addClass("loading").append(loading); 
}  window.awe_showLoading=awe_showLoading;
function awe_hideLoading(selector) {
    $(selector).removeClass("loading"); 
    $(selector + ' .loading-icon').remove();
}  window.awe_hideLoading=awe_hideLoading;
function awe_showPopup(selector) {
    $(selector).addClass('active');
}  window.awe_showPopup=awe_showPopup;
function awe_hidePopup(selector) {
    $(selector).removeClass('active');
}  window.awe_hidePopup=awe_hidePopup;
awe.hidePopup = function (selector) {
    $(selector).removeClass('active');
}

/********************************************************
# Countdown
********************************************************/
function awe_countDown(selector){
    // Set the date we're counting down to
    // Kiá»ƒu thá»i gian Ä‘áº·t tag endtime_4/15/2018 15:10:00
    var dataTime = selector.attr('data-time');
    var countDownDate = new Date(dataTime).getTime();
    // Update the count down every 1 second
    var x = setInterval(function() {
        // Get todays date and time
        var now = new Date().getTime();
        // Find the distance between now an the count down date
        var distance = countDownDate - now;
        // Time calculations for days, hours, minutes and seconds
        var days = Math.floor(distance / (1000 * 60 * 60 * 24));
        var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);
        // Display the result in the element
        selector.html("<span>"+days+"<p>NgÃ y</p></span>" +"<span>"+hours+"<p>Giá»</p></span>" + "<span>"+minutes+"<p>PhÃºt</p></span>" + "<span>"+seconds+"<p>GiÃ¢y</p></span>");
        // If the count down is finished, write some text
        if (distance > 0) {
            clearInterval(x);
            selector.html("<span>"+days+"<p>NgÃ y</p></span>" +"<span>"+hours+"<p>Giá»</p></span>" + "<span>"+minutes+"<p>PhÃºt</p></span>" + "<span>"+seconds+"<p>GiÃ¢y</p></span>");
        }
    }, 1000);
}

/********************************************************
# SIDEBAR CATEOGRY
********************************************************/
function awe_category(){
    $('.nav-category .fa-angle-down').click(function(e){
        $(this).parent().toggleClass('active');
    });
} window.awe_category=awe_category;

/********************************************************
# MENU MOBILE
********************************************************/
function awe_menumobile(){
    $('.menu-bar').click(function(e){
        e.preventDefault();
        $('.wrapmenu_right').toggleClass('open_sidebar_menu');
        $('.cate-overlay').toggleClass('open');
    });
    $('.cate-overlay').click(function(e){
        e.preventDefault();
        $('.wrapmenu_right').toggleClass('open_sidebar_menu');
        $('.cate-overlay').toggleClass('open');
    });

    $('#nav .fa').click(function(e){        
        e.preventDefault();
        $(this).parent().parent().toggleClass('open');
    });
} window.awe_menumobile=awe_menumobile;


$(document).on('click','.overlay, .close-window, .btn-continue, .fancybox-close', function() {   
    awe.hidePopup('.awe-popup'); 
    setTimeout(function(){
        $('.loading').removeClass('loaded-content');
    },500);
    return false;
})
var wDWs = $(window).width();
if (wDWs < 1199) {
    /*Remove html mobile*/
    $('.quickview-product').remove();
}
function awe_convertVietnamese(str) { 
    str= str.toLowerCase();
    str= str.replace(/Ã |Ã¡|áº¡|áº£|Ã£|Ã¢|áº§|áº¥|áº­|áº©|áº«|Äƒ|áº±|áº¯|áº·|áº³|áºµ/g,"a"); 
    str= str.replace(/Ã¨|Ã©|áº¹|áº»|áº½|Ãª|á»|áº¿|á»‡|á»ƒ|á»…/g,"e"); 
    str= str.replace(/Ã¬|Ã­|á»‹|á»‰|Ä©/g,"i"); 
    str= str.replace(/Ã²|Ã³|á»|á»|Ãµ|Ã´|á»“|á»‘|á»™|á»•|á»—|Æ¡|á»|á»›|á»£|á»Ÿ|á»¡/g,"o"); 
    str= str.replace(/Ã¹|Ãº|á»¥|á»§|Å©|Æ°|á»«|á»©|á»±|á»­|á»¯/g,"u"); 
    str= str.replace(/á»³|Ã½|á»µ|á»·|á»¹/g,"y"); 
    str= str.replace(/Ä‘/g,"d"); 
    str= str.replace(/!|@|%|\^|\*|\(|\)|\+|\=|\<|\>|\?|\/|,|\.|\:|\;|\'| |\"|\&|\#|\[|\]|~|$|_/g,"-");
    str= str.replace(/-+-/g,"-");
    str= str.replace(/^\-+|\-+$/g,""); 
    return str; 
} window.awe_convertVietnamese=awe_convertVietnamese;
function awe_category(){
    $('.nav-category .fa-angle-down').click(function(e){
        $(this).toggleClass('fa-angle-up fa-angle-down');
        $(this).parent().toggleClass('active');
    });
    $('.nav-category .fa-angle-up').click(function(e){
        $(this).toggleClass('fa-angle-down');
        $(this).parent().toggleClass('active');
    });
    
} window.awe_category=awe_category;


function awe_backtotop() { 
    $(window).scroll(function() {
        $(this).scrollTop() > 200 ? $('.backtop').addClass('show') : $('.backtop').removeClass('show')
    });
    $('.backtop').click(function() {
        return $("body,html").animate({
            scrollTop: 0
        }, 800), !1
    });
} window.awe_backtotop=awe_backtotop;
function awe_tab() {
    $(".e-tabs:not(.not-dqtab)").each( function(){
        $(this).find('.tabs-title li:first-child').addClass('current');
        $(this).find('.tab-content').first().addClass('current');
        $(this).find('.tabs-title li').click(function(e){
            var tab_id = $(this).attr('data-tab');
            var url = $(this).attr('data-url');
            $(this).closest('.e-tabs').find('.tab-viewall').attr('href',url);
            $(this).closest('.e-tabs').find('.tabs-title li').removeClass('current');
            $(this).closest('.e-tabs').find('.tab-content').removeClass('current');
            $(this).addClass('current');
            $(this).closest('.e-tabs').find("#"+tab_id).addClass('current');

        });    
    });
} window.awe_tab=awe_tab;
$('.dropdown-toggle').click(function() {
    $(this).parent().toggleClass('open');   
}); 
$('.btn-close').click(function() {
    $(this).parents('.dropdown').toggleClass('open');
}); 
$(document).on('keydown','#qty, .number-sidebar',function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
$(document).on('click','.qtyplus',function(e){
    e.preventDefault();   
    fieldName = $(this).attr('data-field'); 
    var currentVal = parseInt($('input[data-field='+fieldName+']').val());
    if (!isNaN(currentVal)) { 
        $('input[data-field='+fieldName+']').val(currentVal + 1);
    } else {
        $('input[data-field='+fieldName+']').val(0);
    }
});
$(document).on('click','.qtyminus',function(e){
    e.preventDefault(); 
    fieldName = $(this).attr('data-field');
    var currentVal = parseInt($('input[data-field='+fieldName+']').val());
    if (!isNaN(currentVal) && currentVal > 1) {          
        $('input[data-field='+fieldName+']').val(currentVal - 1);
    } else {
        $('input[data-field='+fieldName+']').val(1);
    }
});
$('.open-filters').click(function(e){
    e.stopPropagation();
    $(this).toggleClass('openf');
    $('.dqdt-sidebar').toggleClass('openf');
    $('.opacity_menus').toggleClass('open_opacity');
    $('#open-filters').removeClass('aaa');
});
$('.opacity_menus').click(function(){
    $('.dqdt-sidebar').removeClass('openf');
    $('.opacity_menus').removeClass('open_opacity');
    $('.open-filters').removeClass('openf');
    $('#open-filters').removeClass('aaa');
    
});
$('.menubutton').click(function(e){
    e.stopPropagation();
    $('.wrapmenu_right').toggleClass('open_sidebar_menu');
    $('.opacity_menu').toggleClass('open_opacity');
    $('#open-filters').addClass('aaa');
});

$('.opacity_menu').click(function(e){
    $('.wrapmenu_right').removeClass('open_sidebar_menu');
    $('.opacity_menu').removeClass('open_opacity');
});
$(".menubar_pc").click(function(){ 
    $('.wrapmenu_full').slideToggle('fast');
    $('.wrapmenu_full, .cloed').toggleClass('open_menu');
    $('.dqdt-sidebar, .open-filters').removeClass('openf')
});
$(".cloed").click(function(){ 
    $(this).toggleClass('open_menu');
    $('.wrapmenu_full').slideToggle('fast');
});
$(".opacity_menu").click(function(){ 
    $('.opacity_menu').removeClass('open_opacity');
});
if ($('.dqdt-sidebar').hasClass('openf')) {
    $('.wrapmenu_full').removeClass('open_menu');
    $('#open-filters').removeClass('openf');
} 
$('.ul_collections li > .fa').click(function(){
    $(this).parent().toggleClass('current');
    $(this).toggleClass('fa-plus fa-minus');
    $(this).next('ul').slideToggle("fast");
    $(this).next('div').slideToggle("fast");
});
$('.searchion').mouseover(function() {
    $('.searchmini input').focus();                    
})
$('.quenmk').on('click', function() {
    $('#login').toggleClass('hidden');
    $('.h_recover').slideToggle();
});
$('a[data-toggle="collapse"]').click(function(e){
    if ($(window).width() >= 767) { 
        // Should prevent the collapsible and default anchor linking 
        // behavior for screen sizes equal or larger than 768px.
        e.preventDefault();
        e.stopPropagation();
    }    
});

$(document).ready(function(){

    $(".wrap_tab_index").each( function(){
        var newthis = $(this);
        $(this).find(".button_show_tab").click(function(){ 
            if($(newthis).find('.link_tab_check_click').hasClass('open')){
                $('.link_tab_check_click').removeClass('open');
            }else {
                $('.link_tab_check_click').removeClass('open');
                $(newthis).find('.link_tab_check_click').addClass('open');
            }
        });
    });



});


/* top search */

$('.search_text').click(function(){
    $(this).next().slideToggle(200);
    $('.list_search').show();
})

$('#mb_search').click(function(){
    $('.mb_header_search').slideToggle('fast');
});

$('.fi-title.drop-down').click(function(){
    $(this).toggleClass('opentab');
});