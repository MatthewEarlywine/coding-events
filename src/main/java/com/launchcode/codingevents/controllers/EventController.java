package com.launchcode.codingevents.controllers;


import com.launchcode.codingevents.data.EventData;
import com.launchcode.codingevents.models.Event;
import com.launchcode.codingevents.models.EventType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model){
//        model.addAttribute("title", "All Events");
//        model.addAttribute("events", EventData.getAll());
//        return "events/index";

        HashMap<String, String> events = new HashMap<>();
        events.put("StrangeLoop", "Big ol' coding event t the Stifel,");
        events.put("Battle of the Bands", "Rock contest for amateurs");
        events.put("Weasel Stomping Day", "Just what ot sounds like.");
        return "events/index";
    }

    //lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model){
        model.addAttribute("title", "Create Events");
        model.addAttribute("event", new Event());
        model.addAttribute("types", EventType.values());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent,
                                         Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Create Events");
            return "events/create";
        }

        EventData.add(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds ){

        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }

        return "redirect:";
    }
}
