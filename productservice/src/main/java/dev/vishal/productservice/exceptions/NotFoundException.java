package dev.vishal.productservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) // another way of throwing exception through response headers
public class NotFoundException extends  Exception {

    public NotFoundException(String message){

        super(message);
    }
}
