package bell.yusipov.fileservice.controller;

import bell.yusipov.fileservice.service.file.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Основной контроллер
 */
@Controller
public class GeneralController {
    private static final Logger logger = LoggerFactory.getLogger(FilePagesController.class);
    private final FileService fileService;

    @Autowired
    public GeneralController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * Предстовление главной страницы сервиса действующиго пользователя c сервисным сообщением
     * @param model  модель
     * @param report сервисное сообщение
     * @return предстовление основной страницы сервиса
     */
    @GetMapping({"/userpage", "/userpage/{report}"})
    public String service(Model model, @PathVariable(value = "report", required = false) String report) {
        logger.info("[CONTROLLER]: userpage controller with report " + report);
        model.addAttribute("report", report);
        model.addAttribute("files", fileService.getFileList());
        return "userpage";
    }
}

