package com.moesounds.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moesounds.domain.Media;
import com.moesounds.domain.Page;
import com.moesounds.domain.enums.MediaType;
import com.moesounds.service.MoeSoundsService;

@RestController
public class HomeRestController {

    @Autowired
    private MoeSoundsService moeSoundsService;

    private final Logger logger = LoggerFactory.getLogger(HomeRestController.class);

    @RequestMapping(value = "/get-click-count", method = RequestMethod.GET)
    public long getClickCount(@RequestParam("pageId") int pageId) {
        return moeSoundsService.getClickCount(pageId);
    }

    @RequestMapping(value = "/update-click-count", method = RequestMethod.POST)
    public void updateClickCount(@RequestParam("pageId") int pageId) {
        moeSoundsService.updateClickCount(pageId);
    }

    @RequestMapping(value = "/get-page-media/{pageId}-{mediaType}", method = RequestMethod.GET)
    public void getPageMedia(@PathVariable("pageId") int pageId, @PathVariable("mediaType") MediaType mediaType, HttpServletResponse response) {

        Page specificPage = moeSoundsService.getSpecificPage(pageId);
        Media media = specificPage.getMediaWithMediaType(mediaType);

        if (media == null)
            return;

        try {

            byte[] fileData = media.getFileData();
            InputStream inputStream = new ByteArrayInputStream(fileData);

            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            logger.info("Error writing media file to output stream. Filename was '{}'", media.getFileName(), ex);
            throw new RuntimeException("IOError writing media file to output stream");
        }

    }

}
