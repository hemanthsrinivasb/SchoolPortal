package com.example.example1.controller;

import com.example.example1.model.Contact;
import com.example.example1.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Controller
public class ContactController {

    @RequestMapping("/contact")
    public String displayContactPage(Model model){
        model.addAttribute("contact",new Contact()); //this tells there is a validation which needs to be performed
        return "contact.html";
    }

//    METHOD 2
    private final ContactService contactService;
    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = "/saveMsg",method=POST)
    public String saveMessage(@Valid @ModelAttribute("contact")Contact contact, Errors errors){
        if(errors.hasErrors()){
            log.error("Contact form validation failed due to = "+errors.toString());
            return "contact.html";
        }
        contactService.saveMessageDetails(contact);
        contactService.setCounter(contactService.getCounter()+1);
        System.out.println("no.of times contact form is submitted : "+contactService.getCounter());
        return "redirect:/contact";
    }
}
