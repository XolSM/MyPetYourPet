package com.example.mypetyourpet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Reminders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reminderId;
    private int relatedReservationID;
    private int id; //user ID
    private Date sentDate;
    private String reminderType;

    public Reminders() {}
    public Reminders(Long resId, int relatedReservationID, int usId, Date sentDate,
                     String reminderType) {
        this.reminderId = resId;
        this.relatedReservationID = relatedReservationID;
        this.id = usId;
        this.sentDate = sentDate;
        this.reminderType = reminderType;
    }

    public Long getReminderId() {
        return reminderId;
    }

    public void setReminderId(Long reminderId) {
        this.reminderId = reminderId;
    }

    public int getRelatedReservationID() {
        return relatedReservationID;
    }

    public void setRelatedReservationID(int relatedReservationID) {
        this.relatedReservationID = relatedReservationID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public String getReminderType() {
        return reminderType;
    }

    public void setReminderType(String reminderType) {
        this.reminderType = reminderType;
    }
}
