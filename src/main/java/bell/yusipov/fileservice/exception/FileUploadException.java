package bell.yusipov.fileservice.exception;

/**
 * Ошибка загрузки файла
 */
public class FileUploadException extends RuntimeException {

    /**
     * Конструктор с сообщением об ошибке и передачей причины
     * @param message сообщение
     * @param e причина
     */
    public FileUploadException(String message, Throwable e) {
        super(message, e);
    }
}
