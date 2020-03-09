package me.ayonel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;


@ControllerAdvice(annotations = {RestController.class})
public class WebExceptionHandler extends ResponseEntityExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(WebExceptionHandler.class);

    /**
     * handle ServiceException
     *
     * @param ex            the exception
     * @param webRequest    the current request
     */
    @ExceptionHandler(value = {ServiceException.class})
    public final ResponseEntity<WebRestResult> handleServiceException(ServiceException ex, WebRequest webRequest) {
        log.error("happened service exception!", ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        WebRestResult restResult = new WebRestResult(-1, ex.getMessage(), null);
        return new ResponseEntity<>(restResult, headers, HttpStatus.OK);
    }

    /**
     * A single place to customize the response body of all Exception types.
     * <p>The default implementation sets the {@link WebUtils#ERROR_EXCEPTION_ATTRIBUTE}
     * request attribute and creates a {@link ResponseEntity} from the given
     * body, headers, and status.
     *
     * @param ex      the exception
     * @param body    the body for the response
     * @param headers the headers for the response
     * @param status  the response status
     * @param request the current request
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        WebRestResult restResult = new WebRestResult(status.value(), ex.getMessage(), null);
        return new ResponseEntity<>(restResult, headers, status);
    }
}
