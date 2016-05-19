package com.moesounds.dao;

import java.util.List;

import com.moesounds.annotation.MoeSoundsDatabase;
import com.moesounds.domain.Page;
import com.moesounds.domain.PageMedia;

@MoeSoundsDatabase
public interface MoeSoundsDAO {

	public List<Page> getAllPages();
	
	public void insertPage(Page page);
	public void insertPageMedia(PageMedia pageMedia);
}
