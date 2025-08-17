package com.tryhard.demo.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "due_date")
    private Date due_date;

    @Column(name = "person_id")
    private Integer person_id;

    @Column(name = "date_added")
    private Date date_added;

    public Date getDate_added() {
        return date_added;
    }

    public void setDate_added(Date date_added) {
        this.date_added = date_added;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }


    @Override
    public String toString() {
        return "Todo: " + name + " due at: " + due_date.toString() + " added at:  " + date_added.toString();
    }
}
