package com.moesounds.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moesounds.annotation.MoeSoundsDatabase;
import com.moesounds.domain.Page;
import com.moesounds.domain.PageMedia;

@MoeSoundsDatabase
public interface MoeSoundsDAO {

	public Page getPage(@Param("pageId") int pageId);
	public List<Page> getAllPages();
	
	public void insertPage(Page page);
	public void updatePage(Page page);
	public void deletePage(@Param("pageId") int pageId);
	
	public void insertPageMedia(PageMedia pageMedia);
	public void updatePageMedia(PageMedia pageMedia);
	public void deletePageMediaWithPageId(@Param("pageId") int pageId);
	
	public void updateClickCount(@Param("pageId") int pageId);
	public long getClickCount(@Param("pageId") int pageId);
}
