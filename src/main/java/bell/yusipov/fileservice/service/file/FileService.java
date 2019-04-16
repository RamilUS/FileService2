package bell.yusipov.fileservice.service.file;

import bell.yusipov.fileservice.model.File;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Сервис для работы с файлами
 */
public interface FileService {

    /**
     * Загрузка файла в БД
     * @param uploadFile  файл
     * @param description описание файлв
     */
    String upload(MultipartFile uploadFile, String description);

    /**
     * Получение файла по индификатору
     * @param fileId id файла
     * @return файл
     */
    File getByFileById(Integer fileId);

    /**
     * Получение списка всех файлов
     * @return список всех файлов
     */
    List<File> getFileList();

    /**
     * Загрузка файла из БД
     * @param fileId id файла
     */
    ResponseEntity<ByteArrayResource> download(Integer fileId);

    /**
     * Удаление файла из БД
     * @param fileId id файл
     */
    String remove(Integer fileId);
}
