function switchView(viewType) {
    if ($('#input-view-mode').val() === viewType) {
        return;
    }
    $('#input-view-mode').val(viewType);
    if (viewType == 'list') {
        switchListView();
    } else {
        switchGridView();
    }
}

function switchGridView() {
    $('.view-mode-list').removeClass('active');
    $('.view-mode-grid').addClass('active');
    var itemParrentGrid = $('.products-view').find('.item-sample').find('.item-parrent-grid');
    var productsViewParrent = $('.products-view').find('.row');
    var data = $(productsViewParrent).find('.item-parrent');
    $('.products-view').find('.row').empty();
    for (var i = 0; i < data.length; i++) {
        var itemParrent = $(itemParrentGrid).clone();
        var itemList = data[i];
        $(itemParrent).find('.created-date').text($(itemList).find('.created-date').text());
        $(itemParrent).find('.product-thumbnail').empty();
        $(itemList).find('.product-thumbnail').children().appendTo($(itemParrent).find('.product-thumbnail'));
        $(itemParrent).find('.product-name').empty();
        $(itemList).find('.product-name').children().appendTo($(itemParrent).find('.product-name'));
        $(itemParrent).find('.product-price').text($(itemList).find('.price').text());
        if ($(itemList).find('.compare-price').text().trim().length > 0) {
            $(itemParrent).find('.product-price-old').text($(itemList).find('.compare-price').text());
        } else {
            $(itemParrent).find('.product-price-old').remove();
        }
        if ($(itemList).find('.price-sale-flash').text().trim().length > 0) {
            $(itemParrent).find('.price-sale-flash').text($(itemList).find('.price-sale-flash').text());
        } else {
            $(itemParrent).find('.price-sale-flash').remove();
        }
        $(itemParrent).find('input[class="desproduct"]').val($(itemList).find('.desproduct').text());
        $(itemParrent).find('.product-action').empty();
        $(itemList).find('.product-action').children().appendTo($(itemParrent).find('.product-action'));
        $(productsViewParrent).append(itemParrent);
    }
}

function switchListView() {
    $('.view-mode-list').addClass('active');
    $('.view-mode-grid').removeClass('active');
    var itemParrentList = $('.products-view').find('.item-sample').find('.item-parrent-list');
    var productsViewParrent = $('.products-view').find('.row');
    var data = $(productsViewParrent).find('.item-parrent');
    $('.products-view').find('.row').empty();
    for (var i = 0; i < data.length; i++) {
        var itemParrent = $(itemParrentList).clone();
        var itemGrid = data[i];
        $(itemParrent).find('.created-date').text($(itemGrid).find('.created-date').text());
        $(itemParrent).find('.product-thumbnail').empty();
        $(itemGrid).find('.product-thumbnail').children().appendTo($(itemParrent).find('.product-thumbnail'));
        $(itemParrent).find('.product-name').empty();
        $(itemGrid).find('.product-name').children().appendTo($(itemParrent).find('.product-name'));
        $(itemParrent).find('.price').text($(itemGrid).find('.product-price').text());
        if ($(itemGrid).find('.product-price-old').text().trim().length > 0) {
            $(itemParrent).find('.compare-price').text($(itemGrid).find('.product-price-old').text());
        } else {
            $(itemParrent).find('.compare-price').remove();
        }
        if ($(itemGrid).find('.price-sale-flash').text().trim().length > 0) {
            $(itemParrent).find('.price-sale-flash').text($(itemGrid).find('.price-sale-flash').text());
        } else {
            $(itemParrent).find('.price-sale-flash').remove();
        }
        $(itemParrent).find('.desproduct').text($(itemGrid).find('input[class="desproduct"]').val());
        $(itemParrent).find('.product-action').empty();
        $(itemGrid).find('.product-action').children().appendTo($(itemParrent).find('.product-action'));
        $(productsViewParrent).append(itemParrent);
    }
}
