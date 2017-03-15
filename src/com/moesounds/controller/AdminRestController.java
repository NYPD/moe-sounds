package com.moesounds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.moesounds.domain.Page;
import com.moesounds.domain.enums.DefaultBackground;
import com.moesounds.domain.enums.MediaType;
import com.moesounds.model.PageForm;
import com.moesounds.model.PageFormSaveResult;
import com.moesounds.service.MoeSoundsService;

@RestController
@RequestMapping(value = "/admin")
public class AdminRestController {

    @Autowired
    private MoeSoundsService moeSoundsService;

    @RequestMapping(value = "/get-moe-page-form-modal")
    public ModelAndView getMoePageForm(@RequestParam(value = "pageId", required = false) Integer pageId) {

        ModelAndView modelAndView = new ModelAndView("modal-content/maintenance/moe-page-form");

        modelAndView.addObject("mediaTypes", MediaType.values());
        modelAndView.addObject("defaultBackgrounds", DefaultBackground.values());

        boolean noPageId = pageId == null;
        if (noPageId) return modelAndView;

        Page page = moeSoundsService.getSpecificPage(pageId);
        modelAndView.addObject("page", page);

        return modelAndView;
    }

    @RequestMapping(value = "/save-page-form", method = RequestMethod.POST)
    public PageFormSaveResult savePageForm(PageForm pageForm) {

        moeSoundsService.savePageForm(pageForm);

        Page page = moeSoundsService.getSpecificPage(pageForm.getPageId());

        return new PageFormSaveResult(page);

    }

    @RequestMapping(value = "/get-delete-moe-page-modal")
    public ModelAndView getDeleteMoePageModal(@RequestParam(value = "pageId") Integer pageId) {

        ModelAndView modelAndView = new ModelAndView("modal-content/maintenance/delete-moe-page");

        Page page = moeSoundsService.getSpecificPage(pageId);
        modelAndView.addObject("page", page);

        return modelAndView;
    }

    @RequestMapping(value = "/delete-page", method = RequestMethod.POST)
    public void deletePage(@RequestParam("pageId") int pageId) {
        moeSoundsService.deletePage(pageId);
    }

}
