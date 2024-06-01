package springbootlearning.exception.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springbootlearning.dto.error.ErrorResponseDto;
import springbootlearning.exception.BadRequestException;
import springbootlearning.exception.NotFoundException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundException notFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseDto.builder()
                .message(notFoundException.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleBadRequestException(BadRequestException badRequestException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto.builder()
                .message(badRequestException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build());
    }

    //    MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException) {

        Map<String, String> messages = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String messageDefault = error.getDefaultMessage();
            messages.put(field, messageDefault);
        });

        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .message(Arrays.toString(messages.entrySet().toArray()))
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    //    DataIntegrityViolationException
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleDataIntegrityViolationException(
            DataIntegrityViolationException dataIntegrityViolationException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto.builder()
                .message(dataIntegrityViolationException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build());
    }
}
