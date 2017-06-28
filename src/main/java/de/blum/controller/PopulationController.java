package de.blum.controller;

import de.blum.domain.Event;
import de.blum.domain.Guest;
import de.blum.domain.Owner;
import de.blum.repository.EventRepository;
import de.blum.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/")
public class PopulationController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @RequestMapping(value="/create", method= RequestMethod.GET)
    @ResponseBody
    public String create() {
        Event event = new Event();

        event.setName("my event");
        event.setRegStatus(Event.RegStatus.OPEN);

        String[] codes = {"abba", "baab"};  // TODO: create a table with codes
        event.setInviteCodes(codes);

        List<Guest> guests = new ArrayList<Guest>();
        event.setGuests(guests);

        List<Guest> waitlist = new ArrayList<Guest>();
        event.setWaitlist(waitlist);

        Owner owner = new Owner();

        owner.setName("owner");
        owner.setEmail("owner email");

        if (ownerRepository.findAll().iterator().hasNext() || eventRepository.findAll().iterator().hasNext()) {
            return "Owner and event are already in the database!";
        }

        owner = ownerRepository.save(owner);

        event.setOwner(ownerRepository.findOne(1));
        event = eventRepository.save(event);

        owner.setEvent(event);
        owner = ownerRepository.save(owner);

        return "Created an Event with ID:" + event.getId() + " and an Owner with ID:" + owner.getId();
    }

    @RequestMapping(value="/showEvent", method= RequestMethod.GET)
    @ResponseBody
    public String showEvent() {
        Event event = eventRepository.findOne(1);

        return "Event: \n id: " + event.getId() + "\n name: " + event.getName() + "\n status: " + event.getRegStatus();
    }
}
