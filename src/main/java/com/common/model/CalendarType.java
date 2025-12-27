package com.common.model;

import lombok.Getter;

@Getter
public enum CalendarType {

    DAY("Day"),
    WEEK("Week"),
    MONTH("Month"),
    YEAR("Year");

    private final String label;

    CalendarType(String label){
        this.label = label;
    }

}
