package com.example.example1.service;

import com.example.example1.controller.ContactController;
import com.example.example1.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Slf4j
@Service
//@RequestScope //bean created for every request,counter will be 1
//@SessionScope //bean is created only once for entire session,counter will be incremented
@ApplicationScope //beans is created only once at runtime
public class ContactService {

    private int counter=0;
    public ContactService(){
        System.out.println("contact service bean created!");
    }


    public boolean saveMessageDetails(Contact contact){
        boolean isSaved=true;
        log.info(contact.toString());
        return isSaved;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
