package com.moesounds.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.moesounds.domain.enums.DefaultBackground;
import com.moesounds.domain.enums.MediaType;

public class PageForm {

    private Integer pageId;
    private String pageName;
    private String css;
    private DefaultBackground defaultBackground;

    private List<PageFormFile> formFiles;

    public Integer getPageId() {
        return pageId;
    }
    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }
    public String getPageName() {
        return pageName;
    }
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
    public String getCss() {
        return css;
    }
    public void setCss(String css) {
        this.css = css;
    }
    public DefaultBackground getDefaultBackground() {
        return defaultBackground;
    }
    public void setDefaultBackground(DefaultBackground defaultBackground) {
        this.defaultBackground = defaultBackground;
    }
    public List<PageFormFile> getFormFiles() {
        return formFiles;
    }
    public void setFormFiles(List<PageFormFile> formFiles) {
        this.formFiles = formFiles;
    }

    public static class PageFormFile {

        private Integer mediaId;
        private MediaType mediaType;
        private MultipartFile file;

        public Integer getMediaId() {
            return mediaId;
        }
        public void setMediaId(Integer mediaId) {
            this.mediaId = mediaId;
        }
        public MediaType getMediaType() {
            return mediaType;
        }
        public void setMediaType(MediaType mediaType) {
            this.mediaType = mediaType;
        }
        public MultipartFile getFile() {
            return file;
        }

        public void setFile(MultipartFile file) {

            this.file = file;
        }

        @Override
        public String toString() {

            return "PageFormFile [mediaId=" + mediaId + ", mediaType=" + mediaType + ", file=" + file + "]";
        }

    }

}
