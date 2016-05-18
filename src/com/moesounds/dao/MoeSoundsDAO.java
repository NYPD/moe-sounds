package com.moesounds.dao;

import com.moesounds.annotation.MoeSoundsDatabase;
import com.moesounds.domain.Page;
import com.moesounds.domain.PageMedia;

@MoeSoundsDatabase
public interface MoeSoundsDAO {

	public PageMedia getLastPageMedia();
	
	public void insertPage(Page page);
	public void insertPageMedia(PageMedia pageMedia);
}
