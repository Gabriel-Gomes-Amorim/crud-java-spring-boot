package crud.spring.boot.crudspringboot.domain.exceptions;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailException extends RuntimeException {
    public EmailException() {
        super("O email já foi cadastrado!");
    }
}
