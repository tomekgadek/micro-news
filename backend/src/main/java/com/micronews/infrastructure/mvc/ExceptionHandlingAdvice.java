package com.micronews.infrastructure.mvc;

import com.micronews.activity.dto.ArticleUserNotFoundException;
import com.micronews.content.dto.ArticleNotFoundException;
import com.micronews.content.dto.SectionNotFoundException;
import com.micronews.identity.dto.UserNotFoundException;
import com.micronews.media.dto.ImageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class ExceptionHandlingAdvice {

    @ExceptionHandler({
            UserNotFoundException.class,
            ArticleNotFoundException.class,
            SectionNotFoundException.class,
            ArticleUserNotFoundException.class,
            ImageNotFoundException.class
    })
    ResponseEntity<ErrorMessage> handleNotFound(RuntimeException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    record ErrorMessage(String message, String details) {
        ErrorMessage(String message) {
            this(message, null);
        }
    }
}
