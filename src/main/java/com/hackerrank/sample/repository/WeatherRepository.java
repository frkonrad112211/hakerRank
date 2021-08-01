package com.hackerrank.sample.repository;

import com.hackerrank.sample.domain.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

}
