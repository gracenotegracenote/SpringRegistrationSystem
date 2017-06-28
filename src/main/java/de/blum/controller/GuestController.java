package de.blum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.blum.domain.Event;
import de.blum.domain.Guest;
import de.blum.repository.EventRepository;
import de.blum.repository.GuestRepository;

@Controller
@RequestMapping(value="/")
public class GuestController {
    
    @Autowired
    private GuestRepository guestRepository;

	@Autowired
	private EventRepository eventRepository;


    @RequestMapping(value="/reg", method= RequestMethod.GET)
    public String registrationForm(Model model) {
        model.addAttribute("guest", new Guest());

        Event event = eventRepository.findOne(1);
        if (event.getRegStatus() == Event.RegStatus.CLOSED) {
            //model.addAttribute("error", "Registration is already closed.");
            return "regClosed";
        }

        return "registration";
    }

    @RequestMapping(value="/reg", method= RequestMethod.POST)
    public String registrationSubmit(@ModelAttribute Guest guest, Model model) {
        boolean nameEmpty = StringUtils.isEmpty(guest.getName());
        boolean emailEmpty = StringUtils.isEmpty(guest.getEmail());
        if (nameEmpty || emailEmpty) {
            model.addAttribute("error", "Empty name or email.");
            model.addAttribute("guest", guest);
            return "registration";
        }

        if (!nameEmpty) {
            Pattern pattern = Pattern.compile("[a-zA-Z]+");
            Matcher matcher = pattern.matcher(guest.getName());

            if (!matcher.matches()) {
                model.addAttribute("error", "Invalid name.");
                model.addAttribute("guest", guest);
                return "registration";
            }
        }

        if (!emailEmpty) {
            Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            Matcher matcher = pattern.matcher(guest.getEmail());

            if (!matcher.matches()) {
                model.addAttribute("error", "Invalid email.");
                model.addAttribute("guest", guest);
                return "registration";
            }
        }

        guestRepository.save(guest);

        // add guests to event
        Event event = eventRepository.findOne(1);
        List<Guest> list;
        if (event.getRegStatus() == Event.RegStatus.OPEN) {
            guest.setGuestStatus(Guest.GuestStatus.EXCEPTED);

            list = event.getGuests();
            list.add(guest);
            event.setGuests(list);
        } else {
            guest.setGuestStatus(Guest.GuestStatus.WAITLIST);

            list = event.getWaitlist();
            list.add(guest);
            event.setWaitlist(list);
        }

        eventRepository.save(event);

        return "regResult";
    }

    @RequestMapping(value="/search", method= RequestMethod.GET)
    public String searchForm(Model model) {
        model.addAttribute("guest", new Guest());
        return "search";
    }

    @RequestMapping(value="/search", method= RequestMethod.POST)
    public String deleteGuest(@ModelAttribute Guest guest, Model model) {
        Guest g = guestRepository.findByEmail(guest.getEmail());

        if (g == null || StringUtils.isEmpty(g.getName()) || StringUtils.isEmpty(g.getEmail()) || !g.getName().equals(guest.getName())) {
            model.addAttribute("error", "Wrong name or E-Mail./Not founded in database.");
            model.addAttribute("guest", guest);
            return "search";
        }

        guestRepository.delete(g);
        model.addAttribute(guest);

        return "deleteResult";
    }


    @RequestMapping(value="/get/{id}", method=RequestMethod.GET, produces="application/json")
    public Guest get(@PathVariable Integer id) {
        Guest guest = guestRepository.findOne(id);
        return guest;
    }
}
