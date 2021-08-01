package com.hackerrank.sample.service;

import com.hackerrank.sample.domain.Weather;
import com.hackerrank.sample.error.WeatherNotFound;
import com.hackerrank.sample.repository.WeatherRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Data
@NoArgsConstructor
public class WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;

    public Weather insert(Weather weather){
        return weatherRepository.save(weather);
    }

    public List<Weather> selectAll() {
        return weatherRepository.findAll();
    }

    public Weather selectById(long id) {
        return weatherRepository.findById(id).orElseThrow(WeatherNotFound::new);
    }

    public void deleteById(Long id) {
        selectById(id);
        weatherRepository.deleteById(id);
    }
}
