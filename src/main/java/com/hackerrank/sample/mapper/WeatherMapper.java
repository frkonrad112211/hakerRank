package com.hackerrank.sample.mapper;

import com.hackerrank.sample.domain.Weather;
import com.hackerrank.sample.dto.WeatherDto;

public class WeatherMapper {
    public static Weather toWeather(WeatherDto weatherDto){
        return new Weather(
                weatherDto.getDate(),
                weatherDto.getFirstName(),
                weatherDto.getLastName(),
                weatherDto.getPhoneNumber()
                );
    }
}
