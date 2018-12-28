package com.moesounds.dao;

import java.util.List;

import com.moesounds.annotation.MoeSoundsDatabase;
import com.moesounds.domain.Media;
import com.moesounds.domain.Page;

@MoeSoundsDatabase
public interface MoeSoundsDAO {

    public Page getPage(int pageId);

    public List<Page> getAllPages();

    public void insertPage(Page page);

    public void updatePage(Page page);

    public void deletePage(int pageId);

    public void insertMedia(Media media);

    public void updateMedia(Media media);

    public void deleteMediaWithPageId(int pageId);

    public void deleteMediaWithMediaId(int mediaId);

    public void updateClickCount(int pageId);

    public long getClickCount(int pageId);

    public long getTotalClickCount();
}
