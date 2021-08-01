package com.hackerrank.sample.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Weather not found")
public class WeatherNotFound extends RuntimeException {
}
