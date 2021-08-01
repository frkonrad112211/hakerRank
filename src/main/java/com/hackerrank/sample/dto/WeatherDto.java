package com.hackerrank.sample.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
public class WeatherDto {
    @NotNull
    private Date date;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Long phoneNumber;

}
