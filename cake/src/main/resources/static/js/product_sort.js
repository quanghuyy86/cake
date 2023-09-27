function sortby(sortType) {
    switch(sortType) {
        case 'alpha-asc':
            sortByAlphaAsc();
            break;
        case 'alpha-desc':
            sortByAlphaDesc();
            break;
        case 'price-asc':
            sortByPriceAsc();
            break;
        case 'price-desc':
            sortByPriceDesc();
            break;
        case 'created-desc':
            sortByCreatedDesc();
            break;
        case 'created-asc':
            sortByCreatedAsc();
            break;
        default:
            sortByCreatedDesc();
            break;
    }
}

function sortByAlphaAsc() {
    sortByAsc('product-name');
}

function sortByAlphaDesc() {
    sortByDesc('product-name');
}

function sortByPriceAsc() {
    var sortByClassName = $('#input-view-mode').val() == 'list' ? 'price' : 'product-price';
    sortByAsc(sortByClassName);
}

function sortByPriceDesc() {
    var sortByClassName = $('#input-view-mode').val() == 'list' ? 'price' : 'product-price';
    sortByDesc(sortByClassName);
}

function sortByCreatedAsc() {
    sortByAsc('created-date');
}

function sortByCreatedDesc() {
    sortByDesc('created-date');
}

function sortByAsc(sortByClassName) {
    var $productList = $('.products-view').find('.row');
    var $productChildren = $productList.children('.item-parrent');
    /**
     * Bind $productChildren to the sort method so we don't have to travel up
     * all these properties more than once.
     */
    var sortList = Array.prototype.sort.bind($productChildren);

    sortList(function ( a, b ) {

        // Cache inner content from the first element (a) and the next sibling (b)
        var aText = $(a).find('.' + sortByClassName).text();
        var bText = $(b).find('.' + sortByClassName).text();
     
        // Returning -1 will place element `a` before element `b`
        if ( aText < bText ) {
            return -1;
        }

        // Returning 1 will do the opposite
        if ( aText > bText ) {
            return 1;
        }

        // Returning 0 leaves them as-is
        return 0;
    });
    $productList.append($productChildren);
}

function sortByDesc(sortByClassName) {
    var $productList = $('.products-view').find('.row');
    var $productChildren = $productList.children('.item-parrent');
    /**
     * Bind $productChildren to the sort method so we don't have to travel up
     * all these properties more than once.
     */
    var sortList = Array.prototype.sort.bind($productChildren);

    sortList(function ( a, b ) {

        // Cache inner content from the first element (a) and the next sibling (b)
        var aText = $(a).find('.' + sortByClassName).text();
        var bText = $(b).find('.' + sortByClassName).text();
     
        // Returning 1 will place element `a` before element `b`
        if ( aText < bText ) {
            return 1;
        }

        // Returning -1 will do the opposite
        if ( aText > bText ) {
            return -1;
        }

        // Returning 0 leaves them as-is
        return 0;
    });
    $productList.append($productChildren);
}


