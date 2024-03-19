package com.example.portfolio.controller.support

import java.text.MessageFormat

class ResourceNotFoundException : RuntimeException {
    constructor()

    constructor(message: String?) : super(message)

    constructor(message: String?, cause: Throwable?) : super(message, cause)

    constructor(cause: Throwable?) : super(cause)

    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(
        message,
        cause,
        enableSuppression,
        writableStackTrace
    )

    constructor(
        clazz: Class<*>,
        identifier: String?
    ) : super(
        MessageFormat.format(
            "Resource not found for given identifier. [Resource: {0}, Identifier: {1}]",
            clazz.simpleName,
            identifier
        )
    )
}
