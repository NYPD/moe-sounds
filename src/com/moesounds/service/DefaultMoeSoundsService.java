package com.moesounds.service;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.moesounds.dao.MoeSoundsDAO;
import com.moesounds.domain.Media;
import com.moesounds.domain.Page;
import com.moesounds.domain.enums.MediaType;
import com.moesounds.model.PageForm;
import com.moesounds.model.PageForm.PageFormFile;

@Service
public class DefaultMoeSoundsService implements MoeSoundsService {

	@Autowired
	private MoeSoundsDAO moeSoundsDAO;

	@Transactional
	@Override
	public void savePageForm(PageForm pageForm) {

		Integer pageId = pageForm.getPageId();
		boolean shouldInsert = pageId == null;
		
		String pageName = pageForm.getPageName();
		String css = pageForm.getCss();
		Collection<PageFormFile> formFiles = pageForm.getFormFiles();
		
		Page page = null;
		
		if(shouldInsert) {
			
			page = new Page(pageName, css);
			moeSoundsDAO.insertPage(page);
			
			Integer newlyGenratedPageId = page.getPageId();
			pageForm.setPageId(newlyGenratedPageId);
			
		}else {
			
			page = moeSoundsDAO.getPage(pageId);
			page.updatePage(pageName, css);
			
			moeSoundsDAO.updatePage(page);
		}
		
		this.handlePageFormMedia(page, formFiles);
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
		//Delete Page Media first due to foreign key constraint
		moeSoundsDAO.deleteMediaWithPageId(pageId);
		moeSoundsDAO.deletePage(pageId);
	}
  	
	@Override
	public void updateClickCount(int pageId) {
		moeSoundsDAO.updateClickCount(pageId);
	}
	
	@Override
	public long getClickCount(int pageId) {
		return moeSoundsDAO.getClickCount(pageId);
	}
	
	
	//Private helper methods ****************************************
	
	/**
	 * Given a Page and a Collection of PageFormFiles, loops through all the formFiles determining
	 * whether it should insert, update, or delete the media from the database. This is done with individual
	 * dao calls to avoid sending the database a big payload.
	 * 
	 * @param page
	 * @param formFiles
	 */
	private void handlePageFormMedia(Page page, Collection<PageFormFile> formFiles) {
		
		for (PageFormFile pageFormFile : formFiles) {
			
			Integer mediaId = pageFormFile.getMediaId();
			MediaType mediaType = pageFormFile.getMediaType();
			MultipartFile file = pageFormFile.getFile();
			
			boolean noFileChange = file == null; //If the file comes back as null, we assume the user did not change it 
			if(noFileChange) continue;
			
			boolean userWishesToDelete = file.isEmpty(); //If they file is empty, we assume the user wants to delete it
			
			if(userWishesToDelete) {
				page.removeMedia(mediaType);
				moeSoundsDAO.deleteMediaWithMediaId(mediaId);
				continue;
			}
			
			boolean userWishesToUpdate = mediaId != null;
			
			if(userWishesToUpdate) {
				Media mediaToUpdate = page.getMediaWithMediaType(mediaType);
				mediaToUpdate.updateMedia(file);
				
				moeSoundsDAO.updateMedia(mediaToUpdate);
			}else {
				//Must be an insert
				Media mediaToAdd = new Media(page, file, mediaType);
				moeSoundsDAO.insertMedia(mediaToAdd);
			}
		}
	}
	
	// For JUnit Tests ************************************
	public void setMoeSoundsDAO(MoeSoundsDAO moeSoundsDAO) {
		this.moeSoundsDAO = moeSoundsDAO;
	}

}
