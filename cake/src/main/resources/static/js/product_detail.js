$(document).ready(function (e) {
    $('.slickone_first_5').slick({
        autoplay: false,
        autoplaySpeed: 6000,
        dots: false,
        arrows: true,
        rows: 1,
        loop: false,
        infinite:false,
        speed: 300,
        slidesToShow: 5,
        slidesToScroll: 2,
        responsive: [
            {
                breakpoint: 1200,
                settings: {
                    slidesToShow: 4,
                    slidesToScroll: 2
                }
            },
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 4,
                    slidesToScroll: 2
                }
            },
            {
                breakpoint: 991,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 2
                }
            },
            {
                breakpoint: 767,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2
                }
            }
        ]
    });

    $('#gallery_02').slick({
        autoplay: true,
        autoplaySpeed: 6000,
        dots: false,
        arrows: true,
        infinite: true,
        speed: 300,
        slidesToShow: 3,
        slidesToScroll: 1,
        responsive: [
            {
                breakpoint: 1200,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 1
                }
            },
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 4,
                    slidesToScroll: 1
                }
            },
            {
                breakpoint: 991,
                settings: {
                    slidesToShow: 6,
                    slidesToScroll: 1
                }
            },
            {
                breakpoint: 767,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 1
                }
            }
        ]
    });

    $('#gallery_02 img').click(function(e){
        e.preventDefault();
        var thumbLargeimg = $(this).attr('data-img');
        $('.large-image .checkurl').attr('src', thumbLargeimg);

        $('.large-image .checkurl img').attr('src', thumbLargeimg);

        $('#gallery_02 .item').each(function(){
            var srcimage = $(this).find('a img').attr('data-img');
            if (srcimage == thumbLargeimg) {
                $(this).find('a').addClass('active');
            } else {
                $(this).find('a').removeClass('active');
            }
        });
    });

    $('#img_01').elevateZoom({
                gallery: 'gallery_02', 
                zoomWindowWidth:420,
                zoomWindowHeight:500,
                zoomWindowOffetx: 10,
                easing : true,
                scrollZoom : true,
                cursor: 'pointer', 
                galleryActiveClass: 'active', 
                imageCrossfade: true

            });   

    $(".not-dqtab").each( function(e){
        $(this).find('.tabs-title li:first-child').addClass('current');
        $(this).find('.tab-content').first().addClass('current');

        $(this).find('.tabs-title li').click(function(){
            if($(window).width()>315){  
                if($(this).hasClass('current')){

                }else{
                    var tab_id = $(this).attr('data-tab');
                    var url = $(this).attr('data-url');
                    $(this).closest('.e-tabs').find('.tab-viewall').attr('href',url);

                    $(this).closest('.e-tabs').find('.tabs-title li').removeClass('current');
                    $(this).closest('.e-tabs').find('.tab-content').removeClass('current');

                    $(this).addClass('current');
                    $(this).closest('.e-tabs').find("#"+tab_id).addClass('current');
                }
            }else{
                var tab_id = $(this).attr('data-tab');
                var url = $(this).attr('data-url');
                $(this).closest('.e-tabs').find('.tab-viewall').attr('href',url);

                $(this).closest('.e-tabs').find('.tabs-title li').removeClass('current');
                $(this).closest('.e-tabs').find('.tab-content').removeClass('current');

                $(this).addClass('current');
                $(this).closest('.e-tabs').find("#"+tab_id).addClass('current');

            }

        });    
    });

});