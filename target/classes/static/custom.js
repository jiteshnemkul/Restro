function addR(itemName) {
    console.log("addRow clicked");
    var url = "/addToOrder/"+itemName;
    //console.log(url);
    $('#orderlist').load(url);
}
function removeOrder(itemName) {
    console.log("removeOrder clicked");
    var url = "/removeFromOrder/"+itemName;
    //console.log(url);
    $('#orderlist').load(url);
}

function increaseOrderNumber(itemName){
    console.log("increaseOrderNumber clicked");
    var url = "/increaseItemAmount/"+itemName;
    //console.log(url);
    $('#orderlist').load(url);
}

function decreaseOrderNumber(itemName){
    console.log("decreaseOrderNumber clicked");
    var url = "/decreaseItemAmount/"+itemName;
    //console.log(url);
    $('#orderlist').load(url);
}

function calculateTotal() {
    console.log("calculateTotal clicked");
    var url = "/calculateTotal" ;
    //console.log(url);
    $('#orderlist').load(url);
}


function saveOrder() {
    console.log("saveOrder clicked");
    var url = "/saveOrder" ;
    //console.log(url);
    $.post(url);
}


