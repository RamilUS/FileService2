package bell.yusipov.fileservice.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Обработчик ошибок
 */
@ControllerAdvice
public class FileExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Обработчик ошибки ошибки пустого индификатора файла
     * @param e ошибка
     * @return страница с текстом ошибки
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handlerFileIllegalArgumentException(IllegalArgumentException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", e.getMessage());
        mav.setViewName("error");
        return mav;
    }

    /**
     * Обработчик ошибки загрузки файла
     * @param e ошибка
     * @return страница с текстом ошибки
     */
    @ExceptionHandler(FileUploadException.class)
    public ModelAndView handlerFileUploadException(FileUploadException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", e.getMessage());
        mav.setViewName("error");
        return mav;
    }
}