package bell.yusipov.fileservice.controller;

import bell.yusipov.fileservice.service.file.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Контроллер загрузки страниц
 */
@Controller
public class FilePagesController {
    private static final Logger logger = LoggerFactory.getLogger(FilePagesController.class);
    private FileService fileService;

    @Autowired
    public FilePagesController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * Загрузка файла в базу данных с описанием файла
     * @param uploadFile  згружаемый файл
     * @param description описание файла
     * @return страницу пользователя с сообщение о загрузки файла
     */
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile uploadFile,
                             @RequestParam("description") String description) {
        logger.info("[CONTROLLER]: file controller uploading file ");
        return "redirect:/userpage/" + fileService.upload(uploadFile, description);
    }

    /**
     * Удаление файла из БД
     * @param fileId индификатор файла
     * @return страницу пользователя с сообщение о удалении файла
     */
    @DeleteMapping("/remove/{fileId}")
    public String remove(@PathVariable("fileId") Integer fileId) {
        logger.info("[CONTROLLER] file controller deleting file with id" + fileId);
        return "redirect:/userpage/" + fileService.remove(fileId);
    }
}

