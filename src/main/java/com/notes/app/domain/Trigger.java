package com.notes.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Trigger")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Trigger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "notes")
    private String notes;

    @Column(name = "point_status")
    private String pointStatus;

    @Override
    public String toString(){
        return " Trigger + id " + id + " Notes : " + notes + " Point Status " + pointStatus;
    }
}
