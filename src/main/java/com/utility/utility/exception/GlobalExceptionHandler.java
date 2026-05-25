package com.utility.utility.exception;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.ui.Model;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFound(
            ResourceNotFoundException ex,
            Model model
    ) {

        model.addAttribute("errorMessage", ex.getMessage());

        return "error";
    }

    @ExceptionHandler(PaymentFailedException.class)
    public String handlePaymentFailure(
            PaymentFailedException ex,
            Model model
    ) {

        model.addAttribute("errorMessage", ex.getMessage());

        return "error";
    }

    @ExceptionHandler(DisputeWindowExpiredException.class)
    public String handleDisputeWindowExpired(
            DisputeWindowExpiredException ex,
            Model model
    ) {

        model.addAttribute("errorMessage", ex.getMessage());

        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(
            Exception ex,
            Model model
    ) {

        model.addAttribute(
                "errorMessage",
                "An unexpected error occurred."
        );

        return "error";
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationException(
            MethodArgumentNotValidException ex,
            Model model
    ) {

        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
                        error.getField()
                                + ": "
                                + error.getDefaultMessage()
                )
                .collect(Collectors.joining(", "));

        model.addAttribute("errorMessage", errors);

        return "error";
    }
}