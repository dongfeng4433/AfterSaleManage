package com.joindoo.jdwechat.model.web;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by zhuqiang on 2018/3/8.
 */
public class ConcurrentModelAndView extends ModelAndView {
    @Override
    public Map<String, Object> getModel() {
        return new ModelMap();
    }
}
