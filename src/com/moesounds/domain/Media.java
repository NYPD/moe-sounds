package com.moesounds.domain;

import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

import com.moesounds.domain.enums.MediaType;
import com.moesounds.exception.FileReadException;

public class Media {

    private Integer mediaId;
    private Integer pageId;
    private MediaType mediaType;
    private String fileName;
    private String fileType;
    private byte[] fileData;
    private long fileSize;

    // For MyBatis
    protected Media() {};

    public Media(Page page, MultipartFile file, MediaType mediaType) {
        this.pageId = page.getPageId();
        this.mediaType = mediaType;
        this.updateMedia(file);
    }

    // Modified Accessors ********************************************
    public void updateMedia(MultipartFile file) {

        try {

            boolean nullOrEmptyFile = file == null || file.isEmpty();
            if (nullOrEmptyFile)
                throw new IllegalArgumentException("File can not be null or empty when updating media");

            boolean noContentType = file.getContentType() == null;
            if (noContentType) throw new IllegalArgumentException("File must have a content type");

            this.fileName = file.getOriginalFilename();
            this.fileType = file.getContentType();
            this.fileData = file.getBytes();
            this.fileSize = file.getSize();

        } catch (IOException e) {
            throw new FileReadException("Issues on getBytes() for the MultipartFile given to update", e);
        }
    }

    public String getFileDataAsBase64() {
        return Base64.getEncoder().encodeToString(this.fileData);
    }

    /**
     * Creates the link between a media and its parent page.
     * 
     * This is package-private since the relationship should be managed on the Page side, not this
     * side.
     * 
     * @param page
     *            The Page this media is associated with.
     */
    void setPage(Page page) {

        Integer pageId = page == null ? null : page.getPageId();

        if (page != null && pageId == null)
            throw new NullPointerException("Can not add a media to a Page that is not saved yet.");

        this.pageId = pageId;
    }

    // Default Accessors *********************************************
    public Integer getMediaId() {
        return mediaId;
    }
    public Integer getPageId() {
        return pageId;
    }
    public MediaType getMediaType() {
        return mediaType;
    }
    public String getFileName() {
        return fileName;
    }
    public String getFileType() {
        return fileType;
    }
    public byte[] getFileData() {
        return fileData;
    }
    public long getFileSize() {
        return fileSize;
    }

    @Override
    public String toString() {

        return "Media [mediaId=" + mediaId + ", pageId=" + pageId + ", mediaType=" + mediaType + ", fileName="
                + fileName + ", fileType=" + fileType + ", fileSize=" + fileSize + "]";
    }

}