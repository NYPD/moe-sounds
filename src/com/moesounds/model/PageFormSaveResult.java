package com.moesounds.model;

import com.moesounds.domain.Media;
import com.moesounds.domain.Page;
import com.moesounds.domain.enums.MediaType;

public class PageFormSaveResult {

    private int pageId;
    private String smallCarouselFileType;
    private String smallCarouselAsBase64;
    private String pageName;
    private int missingMediaCount;
    private int clickCount = 0;

    public PageFormSaveResult(Page page) {
        this.pageId = page.getPageId();

        Media smallCarousel = page.getMediaWithMediaType(MediaType.CAROUSEL_IMAGE_SMALL);

        this.smallCarouselFileType = smallCarousel.getFileType();
        this.smallCarouselAsBase64 = smallCarousel.getFileDataAsBase64();

        this.pageName = page.getPageName();
        this.missingMediaCount = page.getMissingMediaCount();
    }

    public int getPageId() {
        return pageId;
    }

    public String getSmallCarouselFileType() {
        return smallCarouselFileType;
    }

    public String getSmallCarouselAsBase64() {
        return smallCarouselAsBase64;
    }

    public String getPageName() {
        return pageName;
    }

    public int getMissingMediaCount() {
        return missingMediaCount;
    }

    public int getClickCount() {
        return clickCount;
    }



}
