package com.moesounds.service;

import java.util.Collection;

import com.moesounds.domain.Page;
import com.moesounds.model.PageForm;

public interface MoeSoundsService {

    /**
     * Takes a PageForm from the save/edit page modal and either insert a new Page and the
     * associated Media or updates an exiting Page and Media already found in the database.
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
     * 
     * @param pageId
     * @return Specific Page requested
     */
    public Page getSpecificPage(int pageId);

    /**
     * Returns all the pages from the dataBase
     * 
     * @return A Collection of all the Pages
     */
    public Collection<Page> getAllPages();

    /**
     * Deletes the Page and all the Media associated with the Page from the database based on the
     * pageId passed in
     * 
     * @param pageId
     *            The id of the page to delete
     */
    public void deletePage(int pageId);

    /**
     * Increase the click count of the Page by 1, done in SQL for now
     * 
     * @param pageId
     *            The Id of the page to update the click count
     */
    public void updateClickCount(int pageId);

    /**
     * Get the click count of the request page, this is done rather than the
     * {@code getSpecificPage()} method because this call will be spammed, so we want to make it as
     * lean as possible
     * 
     * @param pageId
     *            The Id of the page to retrieve the click count
     */
    public long getClickCount(int pageId);

    /**
     * Get the total click count across all pages
     * 
     */
    public long getTotalClickCount();

}