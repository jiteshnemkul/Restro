function changeCookingStatusOfOrder(orderId) {
    console.log("changeCookingStatusOfOrder called");
    $.ajax({
        type: 'get',
        url: "/changeCookingStatusOfOrder/" + orderId,
        success: function (data) {
            /*<![CDATA[*/
            $('#orderDisplay').html(data);
            /*]]>*/
        }
    });

}

function changeCookingStatusOfItem(itemId) {
    console.log("changeCookingStatusOfItem called");
    $.ajax({
        type: 'get',
        url: "/changeCookingStatusOfItem/" + itemId,
        success: function (data) {
            /*<![CDATA[*/
            $('#orderDisplay').html(data);
            /*]]>*/
        }
    });

}



window.onload=function () {
        setInterval(function () {
            console.log("onLoadFunction called");
            $.ajax({
                type: 'get',
                url: "/refreshActiveOrders/",
                success: function (data) {
                    /*<![CDATA[*/
                    $('#orderDisplay').html(data);
                    /*]]>*/
                }
            });

        }, 15000);
}