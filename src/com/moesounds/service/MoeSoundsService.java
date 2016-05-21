package com.moesounds.service;

import java.util.Collection;

import com.moesounds.domain.Page;
import com.moesounds.model.PageForm;

public interface MoeSoundsService {

	/**
	 * Takes a @see PageForm from the save/edit page modal and either insert a new @see Page and  @see PageMedia or updates an exiting
	 * page already found in the database.
	 * 
	 * @param pageForm Form from the front end
	 */
	public void savePageForm(PageForm pageForm);
	
	/**
	 * Returns a random @see Page from the database. Null if there is nothing
	 * 
	 * @return Random Page
	 */
	public Page getRandomPage();
	
	
	/**
	 * Get a specific page from the database
	 * @param pageId
	 * @return Specific Page requested
	 */
	public Page getSpecificPage(int pageId);
	
	/**
	 * Returns all the pages from the dataBase
	 * @return A Collection of all the Pages 
	 */
	public Collection<Page> getAllPages();
	
	/**
	 * Deletes both a PageMedia and Page row from the database based on the pageId passed in
	 * @param pageId The id of the page to delte
	 */
	public void deletePage(int pageId);
}
