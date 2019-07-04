package com.fountane.www.fountanehrapp.Utils;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.ArrayList;
import java.util.List;

public class dateDecorator implements DayViewDecorator {

    private final int color;
    private List<CalendarDay> list = new ArrayList<>();

    public dateDecorator(int color, List<CalendarDay> dates) {
        this.color = color;
        list = dates;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return list.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(color));
    }

//    public void setDate(Date date) {
//        this.date = CalendarDay.from(date);
//    }

}
