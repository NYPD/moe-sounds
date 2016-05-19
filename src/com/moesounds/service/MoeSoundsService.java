package com.moesounds.service;

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
	 * @return @see{Page}
	 */
	public Page getRandomPage();
}
