package com.fantasybaby.spring.webflux.waiter.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@ReadingConverter
public class LocalDateTimeReadConverter implements Converter<LocalDateTime, Date> {
    @Override
    public Date convert(LocalDateTime source) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = source.atZone(zone).toInstant();
        return Date.from(instant);
    }
}
