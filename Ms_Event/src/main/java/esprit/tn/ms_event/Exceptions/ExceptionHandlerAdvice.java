package esprit.tn.ms_event.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public Map<String, String> handleException(IllegalAccessException exception) {
        Map<String, String> map = new HashMap<>();
        map.put("error", exception.getMessage());
        return map;
    }
}
