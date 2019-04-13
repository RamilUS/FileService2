package bell.yusipov.fileservice.controller;

import bell.yusipov.fileservice.config.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Основной контроллер
 */
@Controller
public class GeneralController {

    /**
     * Переход на главную страницу сервиса действующиго пользователя
     *
     * @param user  действующий пользователь
     * @param model модель
     * @return предстовление основной страницы сервиса
     */
    @GetMapping("/userpage")
    public String service(@AuthenticationPrincipal UserPrincipal user, Model model) {

        return "redirect:/userpage/" + user.getUsername();
    }

    /**
     * Переход на страницу пользователя
     *
     * @param userPrincipal активный пользователь
     * @param pageOwner     собственник страницы
     * @param model         модель
     * @return представление страницы пользователя
     */

    @GetMapping("/userpage/{pageOwner}")
    public String userPage(@AuthenticationPrincipal UserPrincipal userPrincipal,
                           @PathVariable("pageOwner") String pageOwner, Model model) {

        model.addAttribute("roleName", userPrincipal.getUser().getRole().getRoleName());
        model.addAttribute("pageOwner", pageOwner);
        model.addAttribute("userName", userPrincipal.getUser().getUserName());
        return "userpage";
    }

    /**
     * Переход на домашнюю страницу
     * @return домашняя страница
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
