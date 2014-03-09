
var socket;

if (!window.WebSocket) {
    window.WebSocket = window.MozWebSocket;
}

if (window.WebSocket) {
    socket = new WebSocket("ws://localhost:8888/websocket");

    socket.onmessage = function(event) {
        addArticle("Hlava", "Telo")
    };

    socket.onopen = function(event) {
         $("#responseText").html("Opened");
    };

    socket.onclose = function(event) {
        $("#responseText").html("Closed");
    };

} else {
    alert("Your browser does not support Web Socket.");
}

function addArticle(articleHeading, articleText) {
    $mainContent = $(".mainContent");

    $mainContent.html($mainContent.html()
        +
        "<div>" +
            "<h3>" +
                articleHeading +
            "</h3>" +
            articleText +
       "<div>"
   )
}
