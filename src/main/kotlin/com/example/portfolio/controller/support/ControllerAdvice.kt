package com.example.portfolio.controller.support

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@org.springframework.web.bind.annotation.ControllerAdvice
class ControllerAdvice {
    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleProfileNotFoundException(e: ResourceNotFoundException): ResponseEntity<Any?> {
        logger.debug("Exception caught in controller: ", e)
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ControllerAdvice::class.java)
    }
}
