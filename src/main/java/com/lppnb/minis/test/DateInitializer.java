package com.lppnb.minis.test;

import java.util.Date;

import com.lppnb.minis.web.bind.WebDataBinder;
import com.lppnb.minis.web.bind.support.WebBindingInitializer;

public class DateInitializer implements WebBindingInitializer {
    @Override
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(Date.class,"yyyy-MM-dd", false));
    }
}
