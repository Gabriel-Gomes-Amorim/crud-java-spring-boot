package crud.spring.boot.crudspringboot.domain.exceptions;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(UUID id) {
        super("O " + id + " não foi encontrado!");
    }
}