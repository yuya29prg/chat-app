package app.controller;

import app.dto.MessageRequest;
import app.model.Message;
import app.service.MessageService;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/message/list", method = RequestMethod.GET)
    public String showAllMessage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Principalからログインユーザの情報を取得
        String userName = auth.getName();
        model.addAttribute("showLogin",false);
        if(userName == null){
            model.addAttribute("showLogin",true);
        }
        List<Message> messagelist = messageService.searchAll();
        model.addAttribute("messagelist",messagelist);
        return "message/list";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {

        List<Message> messagelist = messageService.searchAll();
        model.addAttribute("messagelist",messagelist);
        return "message/list";
    }

    @GetMapping(value = "/message/new")
    public String displayAdd(Model model){
        model.addAttribute("messageRequest",new MessageRequest());
        return "message/new";
    }

    @RequestMapping(value = "/message/create",method = RequestMethod.POST)
    public String create(@ModelAttribute MessageRequest messageRequest, BindingResult result, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Principalからログインユーザの情報を取得
        String userName = auth.getName();
        messageService.create(messageRequest,userName);
        return "redirect:/message/list";
    }

}
