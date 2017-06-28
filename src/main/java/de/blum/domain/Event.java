package de.blum.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Event {
    public enum RegStatus {
        OPEN,
        WAITLIST,
        CLOSED
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private RegStatus regStatus;
    private String[] inviteCodes;
    
    @OneToOne
    private Owner owner;
    
    @OneToMany
    private List<Guest> guests;
    
    @OneToMany
    private List<Guest> waitlist;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RegStatus getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(RegStatus regStatus) {
        this.regStatus = regStatus;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public List<Guest> getWaitlist() {
        return waitlist;
    }

    public void setWaitlist(List<Guest> waitlist) {
        this.waitlist = waitlist;
    }

    public String[] getInviteCodes() {
        return inviteCodes;
    }

    public void setInviteCodes(String[] inviteCodes) {
        this.inviteCodes = inviteCodes;
    }
}
