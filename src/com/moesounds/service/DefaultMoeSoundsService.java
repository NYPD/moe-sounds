package com.moesounds.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moesounds.dao.MoeSoundsDAO;
import com.moesounds.domain.Page;
import com.moesounds.domain.PageMedia;
import com.moesounds.exception.PageFormFileReadException;
import com.moesounds.model.PageForm;

@Service
public class DefaultMoeSoundsService implements MoeSoundsService {

	@Autowired
	private MoeSoundsDAO moeSoundsDAO;

	@Transactional
	@Override
	public void savePageForm(PageForm pageForm) {

		boolean shouldInsert = pageForm.getPageId() == null;
		
		if(shouldInsert) {
			
			String pageName = pageForm.getPageName();
			String css = pageForm.getCss();

			Page newPage = new Page(pageName, css);
			moeSoundsDAO.insertPage(newPage);
			
			PageMedia pageMedia = null; 
			
			try {
				
				//These should never be null if the form in the front end is mapped correct, but do some sanity checks
				byte[] carouselImageSmall = pageForm.getCarouselImageSmall() == null? new byte[0]: pageForm.getCarouselImageSmall().getBytes();
				byte[] carouselImageBig = pageForm.getCarouselImageBig() == null? new byte[0]: pageForm.getCarouselImageBig().getBytes();
				byte[] backgroundPage = pageForm.getBackgroundPage() == null? new byte[0]: pageForm.getBackgroundPage().getBytes();
				byte[] backgroundInner = pageForm.getBackgroundInner() == null? new byte[0]: pageForm.getBackgroundInner().getBytes();
				byte[] soundFile = pageForm.getSoundFile() == null? new byte[0]: pageForm.getSoundFile().getBytes();
				
				pageMedia = new PageMedia(newPage);
				pageMedia.setCarouselImageSmall(carouselImageSmall);
				pageMedia.setCarouselImageBig(carouselImageBig);
				pageMedia.setBackgroundPage(backgroundPage);
				pageMedia.setBackgroundInner(backgroundInner);
				pageMedia.setSoundFile(soundFile);
				
			} catch (IOException e) {
				throw new PageFormFileReadException(e);
			}
			
			moeSoundsDAO.insertPageMedia(pageMedia);
			
			Integer newlyGenratedPageId = newPage.getPageId();
			pageForm.setPageId(newlyGenratedPageId);
		}else {
			
		}
		
		
		
		
	}
	
	@Override
	public Page getRandomPage() {
		
		List<Page> allPages = moeSoundsDAO.getAllPages();
		
		boolean noPages = allPages.size() == 0;
		if(noPages) return null;
		
		int randomIndex = new Random().nextInt(allPages.size());
		
		Page page = allPages.get(randomIndex);
		
		return page;
	}
	
	@Override
	public Page getSpecificPage(int pageId) {
		Page page = moeSoundsDAO.getPage(pageId);
		return page;
	}
	
	@Override
	public Collection<Page> getAllPages() {

		List<Page> allPages = moeSoundsDAO.getAllPages();
		
		return allPages;
	}
	
	@Transactional
	@Override
	public void deletePage(int pageId) {
		//Delete Page Media first due to foreign key contraint
		moeSoundsDAO.deletePageMediaWithPageId(pageId);
		moeSoundsDAO.deletePage(pageId);
	}
  	
	// For JUnit Tests ************************************
	public void setMoeSoundsDAO(MoeSoundsDAO moeSoundsDAO) {
		this.moeSoundsDAO = moeSoundsDAO;
	}


	


	


	
	

}
