function addR(itemName) {
    console.log("addRow clicked");
    itemName=itemName.split(' ').join('-');
    var url = "/addToOrder/"+itemName;
    //console.log(url);
    $('#orderlist').load(url);
}
function removeOrder(itemName) {
    console.log("removeOrder clicked");
    itemName=itemName.split(' ').join('-');
    var url = "/removeFromOrder/"+itemName;
    //console.log(url);
    $('#orderlist').load(url);
}

function increaseOrderNumber(itemName){
    console.log("increaseOrderNumber clicked");
    itemName=itemName.split(' ').join('-');
    var url = "/increaseItemAmount/"+itemName;
    //console.log(url);
    $('#orderlist').load(url);
}

function decreaseOrderNumber(itemName){
    console.log("decreaseOrderNumber clicked");
    itemName=itemName.split(' ').join('-');
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

function enterCategory(id) {
    console.log("enterCategory clicked");
    var url = "/enterCategory/"+id;
    //console.log(url);
    $('#menuDisplay').load(url);
}

function backToCategory(){
    console.log("backToCategory clicked");
    var url = "/backToCategory";
    //console.log(url);
    $('#menuDisplay').load(url);
}
