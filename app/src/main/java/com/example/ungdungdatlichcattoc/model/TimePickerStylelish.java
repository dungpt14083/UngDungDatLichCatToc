package com.example.ungdungdatlichcattoc.model;

import java.sql.Time;
import java.util.Date;

public class TimePickerStylelish {
    Integer id;
    String hours;
    Boolean block;
    Date time;

    public TimePickerStylelish() {
    }

    public TimePickerStylelish(Integer id, String hours, Boolean block, Date time) {
        this.id = id;
        this.hours = hours;
        this.block = block;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
