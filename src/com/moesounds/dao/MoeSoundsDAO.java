package com.moesounds.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moesounds.annotation.MoeSoundsDatabase;
import com.moesounds.domain.Media;
import com.moesounds.domain.Page;

@MoeSoundsDatabase
public interface MoeSoundsDAO {

	public Page getPage(@Param("pageId") int pageId);
	public List<Page> getAllPages();
	
	public void insertPage(Page page);
	public void updatePage(Page page);
	public void deletePage(@Param("pageId") int pageId);
	
	public void insertMedia(Media media);
	public void updateMedia(Media media);
	public void deleteMediaWithPageId(@Param("pageId") int pageId);
	public void deleteMediaWithMediaId(@Param("mediaId") int mediaId);
	
	public void updateClickCount(@Param("pageId") int pageId);
	public long getClickCount(@Param("pageId") int pageId);
}
