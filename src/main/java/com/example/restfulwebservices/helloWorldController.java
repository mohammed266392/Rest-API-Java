package com.example.restfulwebservices;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class helloWorldController {

    private MessageSource messageSource;

    public helloWorldController(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String helloWorld(){
        return "hello world";
    }


    @GetMapping("/hello-world-internationalization")
    public String helloWorldInternationalization(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Default message", locale);

    }
}
