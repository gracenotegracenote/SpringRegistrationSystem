package de.blum.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Guest {
    public enum GuestStatus {
        EXCEPTED,
        CANCELED,
        WAITLIST,
        INVITED
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String name;

	@Column (unique = true)		// wenn mehrere Events, dann email+eventID
    private String email;
    
    private GuestStatus guestStatus;
    
    //@ManyToOne
    //private Event event;
    
    private String accompaniment;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GuestStatus getGuestStatus() {
        return guestStatus;
    }

    public void setGuestStatus(GuestStatus guestStatus) {
        this.guestStatus = guestStatus;
    }

    public String getAccompaniment() {
        return accompaniment;
    }

    public void setAccompaniment(String accompaniment) {
        this.accompaniment = accompaniment;
    }
}
