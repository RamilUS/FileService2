package bell.yusipov.fileservice.controller;

import bell.yusipov.fileservice.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Контроллер для работы с файлами
 */
@Controller
public class FileRestController {
    private static final Logger logger = LoggerFactory.getLogger(FilePagesController.class);
    private final FileService fileService;

    @Autowired
    public FileRestController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * Скачивание файла из БД
     * @param fileId индификатор файла
     * @return файл для закачки
     */
    @GetMapping("/download/{fileId}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable("fileId") Integer fileId) {
        logger.info("[CONTROLLER]: file Rest controller downloading file with id " + fileId);
        return fileService.download(fileId);
    }
}

