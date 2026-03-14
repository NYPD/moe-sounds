package com.moesounds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moesounds.service.MoeSoundsService;

@RestController
public class MoeListRestController {

    @Autowired
    private MoeSoundsService moeSoundsService;

    @RequestMapping(value = "get-total-click-count")
    public long getSpecificPage() {
        return moeSoundsService.getTotalClickCount();
    }
}
