package de.blum.controller;

import de.blum.domain.Event;
import de.blum.domain.Guest;
import de.blum.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value="/")
public class OwnerController {

	@Autowired
	private EventRepository eventRepository;

	@RequestMapping(value="/showGuests", method= RequestMethod.GET)
	@ResponseBody
	public List<Guest> guestlist() {
		List<Guest> guests = eventRepository.findOne(1).getGuests();
		return guests;
	}

	@RequestMapping(value="/showWaitlist", method= RequestMethod.GET)
	@ResponseBody
	public List<Guest> waitlist() {
		List<Guest> waitlist = eventRepository.findOne(1).getWaitlist();
		return waitlist;
	}

	@RequestMapping(value="/changeStatus", method= RequestMethod.GET)
	public String changeStatus(Model model) {
		model.addAttribute(eventRepository.findOne(1));
		return "status";
	}

	@RequestMapping(value="/changeStatus", method= RequestMethod.POST)
	@ResponseBody
	public String statusChanged(Event event) {
		Event e = eventRepository.findOne(1);
		e.setRegStatus(event.getRegStatus());
		e = eventRepository.save(e);

		return "Status changed to " + e.getRegStatus();
	}
}
