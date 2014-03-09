SOCKETEVENTHANDLER = (($) ->

    WebSocketEventHandler = (() ->
        _self = this

        this.received = (event) ->
            $currentFeedData = $("#feedData")
            $newArticle = $("<div class=\"feedArticle newArticle\" >" + "Received " + event.data + "</div>")
            $newArticle.appendTo($currentFeedData)
            $newArticle.show("blind", null, 1000)
            $newArticle.removeClass("newArticle")
            return

        this.open = (event) ->
            $(".userInput").append("Opened")
            return

        this.closed = (event) ->
            $(".userInput").append("Closed")
            return

        return
    )

    window.webSocketEventHandler = WebSocketEventHandler
    return
)(jQuery)