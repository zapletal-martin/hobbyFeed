$(document).ready ->
    socketHandler = new window.webSocketHandler()
    socketHandler.openConnection()
    socketHandler.inputButton.click(socketHandler.sendMessage) #.bind(socketHandler));
    return

$(".feedArticle").click( ->
    $(this).addClass("rotated")
    return
)