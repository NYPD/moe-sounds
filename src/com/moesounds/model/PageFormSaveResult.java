package com.moesounds.model;

import com.moesounds.domain.Media;
import com.moesounds.domain.Page;
import com.moesounds.domain.enums.MediaType;

public class PageFormSaveResult {

    private int pageId;
    private String thumbnailIconFileType;
    private String thumbnailIconAsBase64;
    private String pageName;
    private int clickCount = 0;

    public PageFormSaveResult(Page page) {
        this.pageId = page.getPageId();

        Media thumbnailIcon = page.getMediaWithMediaType(MediaType.THUMBNAIL_ICON);
        
        if(thumbnailIcon != null) {
        	this.thumbnailIconFileType = thumbnailIcon.getFileType();
            this.thumbnailIconAsBase64 = thumbnailIcon.getFileDataAsBase64();
        }

        this.pageName = page.getPageName();
    }

    public int getPageId() {
        return pageId;
    }

    public String getThumbnailIconFileType() {
        return thumbnailIconFileType;
    }

    public String getThumbnailIconAsBase64() {
        return thumbnailIconAsBase64;
    }

    public String getPageName() {
        return pageName;
    }
    public int getClickCount() {
        return clickCount;
    }

}
