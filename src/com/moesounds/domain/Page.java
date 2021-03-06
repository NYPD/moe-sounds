package com.moesounds.domain;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.moesounds.domain.enums.DefaultBackground;
import com.moesounds.domain.enums.MediaType;

public class Page {

    private Integer pageId;
    private String pageName;
    private String css;
    private long clickCount;

    private DefaultBackground defaultBackground;
    private Map<MediaType, Media> media;

    public Page(String pageName, String css, DefaultBackground defaultBackground) {
        this.updatePage(pageName, css, defaultBackground);
    }

    // Modified Accessors ********************************
    public void updatePage(String pageName, String css, DefaultBackground defaultBackground) {

        boolean isEmpty = StringUtils.isEmpty(pageName);
        if (isEmpty) throw new IllegalArgumentException();

        this.pageName = pageName;
        this.css = css;
        this.defaultBackground = defaultBackground;

    }

    public Media getMediaWithMediaType(MediaType mediaType) {

        getMedia();// Media is lazy loaded and might not be present

        return this.media.get(mediaType);
    }

    public void removeMedia(MediaType mediaType) {

        getMedia();// Media is lazy loaded and might not be present

        Media mediaToRemove = media.remove(mediaType);
        if (mediaToRemove != null) mediaToRemove.setPage(null);
    }

    // Default Accessors *********************************
    public Integer getPageId() {
        return pageId;
    }
    public String getPageName() {
        return pageName;
    }
    public String getCss() {
        return css;
    }
    public DefaultBackground getDefaultBackground() {
        return defaultBackground;
    }
    public long getClickCount() {
        return clickCount;
    }
    public Map<MediaType, Media> getMedia() {
        return media;
    }

    // For MyBatis ***************************************
    protected Page() {};

    /**
     * Package-private setter - MyBatis only. Business logic should not call this method. This is
     * because MyBatis does not allow mapped fields to be of type java.util.Map
     */
    void setMedia(Collection<Media> media) {
        this.media = media.stream().collect(Collectors.toMap(Media::getMediaType, (p) -> p));
    }

    @Override
    public String toString() {

        return "Page [pageId=" + pageId + ", pageName=" + pageName + ", css=" + css + ", clickCount=" + clickCount
                + ", media=" + media + "]";
    }

}