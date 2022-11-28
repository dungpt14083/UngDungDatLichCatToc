package com.example.ungdungdatlichcattoc.model;

import java.sql.Time;

public class TimePickerStylelish {
    Integer id ;
    String hours;
    Boolean block;
   // Time time;

    public TimePickerStylelish() {
    }

    public TimePickerStylelish(Integer id, String hours, Boolean block) {
        this.id = id;
        this.hours = hours;
        this.block = block;

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
}
