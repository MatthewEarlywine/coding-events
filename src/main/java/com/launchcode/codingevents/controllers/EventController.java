package com.launchcode.codingevents.controllers;


import com.launchcode.codingevents.data.EventData;
import com.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    //lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model){
        model.addAttribute("title", "Create Events");
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute Event newEvent){
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

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId){
        String name = EventData.getById(eventId).getName().toString();
        int id = EventData.getById(eventId).getId();
        model.addAttribute("title", "Edit Event " + name + " (id=" + id + ")");
        model.addAttribute("event", EventData.getById(eventId));
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String description){
        EventData.getById(eventId).setName(name);
        EventData.getById(eventId).setDescription(description);
        return "redirect:/events";
    }

}
