package com.example.example1.controller;

import com.example.example1.model.Holiday;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidayController {
    @GetMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable String display, Model model){
//        logic for path variable
        if(display!=null && display.equals("all")){
            model.addAttribute("festival",true);
            model.addAttribute("federal",true);
        }else if(display!=null && display.equals("federal")){
            model.addAttribute("federal",true);
        }else if(display!=null && display.equals("festival")){
            model.addAttribute("festival",true);
        }


        List<Holiday> holidays= Arrays.asList(
                new Holiday("Jan 1","New Year",Holiday.Type.FEDERAL),
        new Holiday("Jan 31","Mothers Day",Holiday.Type.FESTIVAL),
        new Holiday("Feb 14","Talk Day",Holiday.Type.FEDERAL),
        new Holiday("May 21","Brothers Day",Holiday.Type.FESTIVAL),

        new Holiday("July 27","Life Day",Holiday.Type.FESTIVAL),
        new Holiday("August 15","BDay",Holiday.Type.FEDERAL),
        new Holiday("September 7","Fathers Day",Holiday.Type.FESTIVAL),
        new Holiday("October 2","Gandhi Jayanthi",Holiday.Type.FEDERAL)
        );

        Holiday.Type[] types=Holiday.Type.values();
        for(Holiday.Type type:types){
            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday->holiday.getType().equals(type)).collect(Collectors.toList())));
        }

        return "holidays.html";
    }
}
