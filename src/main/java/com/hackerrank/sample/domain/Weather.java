package com.hackerrank.sample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Weather {
    @Id
    @GeneratedValue
    private  Long id;
    private Date date;
    private String firstName;
    private String lastName;
    private Long phoneNumber;

    public Weather(Date date, String firstName, String lastName, Long phoneNumber) {
        this.date = date;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}

