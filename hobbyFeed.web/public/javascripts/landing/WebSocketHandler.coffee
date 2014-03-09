SOCKETHANDLER = (($) ->
    # this wrapper function is not neccessary in coffeescript. It wraps everything in a function anyway on compilation

    WebSocketHandler = (() ->
        _self = this

        this.address = "ws://localhost:8888/feed/1234"
        this.input = $('#keyword')
        this.socket = ""
        this.inputButton = $("#submitFeed")

        this.handler = new window.webSocketEventHandler()

        this.openConnection = ->
            if (!window.WebSocket)
                window.WebSocket = window.MozWebSocket
            if (window.WebSocket)
                this.socket = new WebSocket(this.address)

                this.socket.onmessage = this.onMessageReceived
                this.socket.onopen = this.onSocketOpen
                this.socket.onclose = this.onSocketClose
                return
            else
                alert("Your browser does not support Web Socket.")
                return

        this.sendMessage = ->
            _self.socket.send(_self.input.val())
            return

        this.onMessageReceived = (event) ->
             _self.handler.received(event)
             return

        this.onSocketOpen = (event) ->
             _self.handler.open(event)
             return

        this.onSocketClose = (event) ->
             _self.handler.close(event)
             return

        return
    )

    window.webSocketHandler = WebSocketHandler
    return
)(jQuery)

