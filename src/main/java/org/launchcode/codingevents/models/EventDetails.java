package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.validation.constraints.*;

@Entity
public class EventDetails extends AbstractEntity{

    @Size(max = 500, message = "Description too long!")
    private String description;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid Email. Try Again.")
    private String contactEmail;

    @NotBlank(message = "Address is required.")
    @NotNull(message = "Address must be visible.")
    private String address;

    @Min(value = 1, message = "Someone must attend the event!")
    private int attendees;

    public EventDetails(@Size(max = 500, message = "Description too long!") String description,
                        @NotBlank(message = "Email is required.") @Email(message = "Invalid Email. Try Again.")String contactEmail,
                        @NotBlank(message = "Address is required.")  @NotNull(message = "Address must be visible.") String address,
                        @Min(value = 1, message = "Someone must attend the event!") int attendees) {
        this.description = description;
        this.contactEmail = contactEmail;
        this.address = address;
        this.attendees = attendees;
    }

    public EventDetails (){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAttendees() {
        return attendees;
    }

    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }
}
