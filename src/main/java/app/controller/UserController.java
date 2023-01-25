package app.controller;

import app.dto.UserForm;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import app.service.UserService;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String showAllUser(Model model) {
        List<User> userlist = userService.searchAll();
        model.addAttribute("userlist",userlist);
        return "user/list";
    }

    @ModelAttribute
    public UserForm setupForm() {
        return new UserForm();
    }

    @GetMapping("/signup")
    String accountForm() {
        return "signup";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    String create(@Validated UserForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/signup";
        }
        User user = new User();
        user.setName(form.getName());
        userService.create(user, form.getPassword());
        return "redirect:/login";
    }

//    @RequestMapping(value = "account/complete", method = RequestMethod.GET)
//    String createFinish() {
//        return "/login";
//    }

//    @GetMapping( "/login")
//    public String getLogin() {
//        return "login";
//    }
    @GetMapping(path = "login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model, HttpSession session) {

        model.addAttribute("showErrorMsg", false);
        model.addAttribute("showLogoutedMsg", false);
        if (error != null) {
            if (session != null) {
                AuthenticationException ex = (AuthenticationException) session
                        .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
                if (ex != null) {
                    model.addAttribute("showErrorMsg", true);
                    model.addAttribute("errorMsg", ex.getMessage());
                }
            }
        } else if (logout != null) {
            model.addAttribute("showLogoutedMsg", true);
        }
        return "login";
    }


}





