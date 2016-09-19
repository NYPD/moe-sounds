package com.moesounds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moesounds.service.MoeSoundsService;

@RestController
public class HomeRestController {

    @Autowired
    private MoeSoundsService moeSoundsService;

    @RequestMapping(value = "/get-click-count", method = RequestMethod.GET)
    public long getClickCount(@RequestParam("pageId") int pageId) {

        return moeSoundsService.getClickCount(pageId);
    }

    @RequestMapping(value = "/update-click-count", method = RequestMethod.POST)
    public void updateClickCount(@RequestParam("pageId") int pageId) {

        moeSoundsService.updateClickCount(pageId);
    }

}
