package com.moesounds.domain;

import java.util.Base64;

public class PageMedia {

	private Integer pageMediaId;
	private Integer pageId;
	private byte[] carouselImageSmall;
	private byte[] carouselImageBig;
	private byte[] backgroundPage;
	private byte[] backgroundInner;
	private byte[] soundFile;
	
	/** For MyBatis **/
	protected PageMedia() {};
	
	public PageMedia(Page page) {
		this.pageId = page.getPageId();
	}
	
	public Integer getPageMediaId() {
		return pageMediaId;
	}
	public Integer getPageId() {
		return pageId;
	}
	public void setCarouselImageSmall(byte[] carouselImageSmall) {
		this.carouselImageSmall = carouselImageSmall;
	}
	public void setCarouselImageBig(byte[] carouselImageBig) {
		this.carouselImageBig = carouselImageBig;
	}
	public void setBackgroundPage(byte[] backgroundPage) {
		this.backgroundPage = backgroundPage;
	}
	public void setBackgroundInner(byte[] backgroundInner) {
		this.backgroundInner = backgroundInner;
	}
	public void setSoundFile(byte[] soundFile) {
		this.soundFile = soundFile;
	}
	
	/** Modified Accessors **/
	public String getCarouselImageSmallAsBase64() {
		return Base64.getEncoder().encodeToString(this.carouselImageSmall);
	}
	public String getCarouselImageBigAsBase64() {
		return Base64.getEncoder().encodeToString(this.carouselImageBig);
	}
	public String getBackgroundPageAsBase64() {
		return Base64.getEncoder().encodeToString(this.backgroundPage);
	}
	public String getBackgroundInnerAsBase64() {
		return Base64.getEncoder().encodeToString(this.backgroundInner);
	}
	public String getSoundFileAsBase64() {
		return Base64.getEncoder().encodeToString(this.soundFile);
	}

	@Override
	public String toString() {
		return "PageMedia [pageMediaId=" + pageMediaId + ", pageId=" + pageId + "]";
	}
	
}
