package com.hackerrank.sample.controller;

import com.hackerrank.sample.domain.Weather;
import com.hackerrank.sample.dto.WeatherDto;
import com.hackerrank.sample.mapper.WeatherMapper;
import com.hackerrank.sample.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/endpoint")
public class WeatherController {
    @Autowired
    WeatherService weatherService;

    @PostMapping("/insert")
    public Weather insertNewRecord(@Valid @RequestBody WeatherDto weatherDto) {
        return weatherService.insert(WeatherMapper.toWeather(weatherDto));
    }

    @GetMapping("/select")
    public List<Weather> selectAll() {
        return weatherService.selectAll();
    }

    @GetMapping("/select/{id}")
    public Weather selectById(@PathVariable Long id) {
        return weatherService.selectById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        weatherService.deleteById(id);
    }
}
