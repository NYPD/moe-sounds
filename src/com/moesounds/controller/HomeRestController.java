package com.moesounds.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.moesounds.dao.MoeSoundsDAO;
import com.moesounds.domain.Page;
import com.moesounds.domain.PageMedia;
import com.moesounds.model.PageForm;

@RestController
public class HomeRestController {

	@Autowired
	private MoeSoundsDAO moeSoundsDAO;
	
	@RequestMapping(value = "form-save", method = RequestMethod.POST)
	public void doUpload(PageForm pageForm) throws IOException {  
		
		
		String pageName = pageForm.getPageName();
		String css = pageForm.getCss();

		Page newPage = new Page(pageName, css);
		
		moeSoundsDAO.insertPage(newPage);
		
		
		byte[] carouselImageSmall = pageForm.getCarouselImageSmall().getBytes();
		byte[] carouselImageBig = pageForm.getCarouselImageBig().getBytes();
		byte[] backgroundPage = pageForm.getBackgroundPage().getBytes();
		byte[] backgroundInner = pageForm.getBackgroundInner().getBytes();
		
		PageMedia pageMedia = new PageMedia(newPage);
		pageMedia.setCarouselImageSmall(carouselImageSmall);
		pageMedia.setCarouselImageBig(carouselImageBig);
		pageMedia.setBackgroundPage(backgroundPage);
		pageMedia.setBackgroundInner(backgroundInner);
		
		newPage.setPageMedia(pageMedia);
		
		moeSoundsDAO.insertPageMedia(pageMedia);
		System.out.println(newPage.getPageId());
		
	}
	
	
	
}
