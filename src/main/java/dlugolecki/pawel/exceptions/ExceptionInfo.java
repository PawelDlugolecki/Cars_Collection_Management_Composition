package dlugolecki.pawel.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExceptionInfo {

    private ExceptionCode exceptionCode;
    private String message;
    private LocalDateTime timeOfException;

    public ExceptionInfo(ExceptionCode exceptionCode, String message) {
        this.exceptionCode = exceptionCode;
        this.message = message;
        timeOfException = LocalDateTime.now();
    }
}
