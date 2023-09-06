package br.com.llealcruz.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Log4j2
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
        log.error(this.message);
    }

    public BusinessException(String message, Throwable thrwbl) {
        super(message, thrwbl);
        this.message = message;
        log.error(this.message);
        String error = thrwbl.toString();
        log.error(error);

    }

}
