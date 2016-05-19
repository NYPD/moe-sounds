package com.moesounds.model;

import org.springframework.web.multipart.MultipartFile;

public class PageForm {
	
	private Integer pageId;
	private String pageName;
	private String css;
	private MultipartFile carouselImageSmall;
	private MultipartFile carouselImageBig;
	private MultipartFile backgroundPage;
	private MultipartFile backgroundInner;
	private MultipartFile soundFile;
	
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
	public MultipartFile getCarouselImageSmall() {
		return carouselImageSmall;
	}
	public void setCarouselImageSmall(MultipartFile carouselImageSmall) {
		this.carouselImageSmall = carouselImageSmall;
	}
	public MultipartFile getCarouselImageBig() {
		return carouselImageBig;
	}
	public void setCarouselImageBig(MultipartFile carouselImageBig) {
		this.carouselImageBig = carouselImageBig;
	}
	public MultipartFile getBackgroundPage() {
		return backgroundPage;
	}
	public void setBackgroundPage(MultipartFile backgroundPage) {
		this.backgroundPage = backgroundPage;
	}
	public MultipartFile getBackgroundInner() {
		return backgroundInner;
	}
	public void setBackgroundInner(MultipartFile backgroundInner) {
		this.backgroundInner = backgroundInner;
	}
	public MultipartFile getSoundFile() {
		return soundFile;
	}
	public void setSoundFile(MultipartFile soundFile) {
		this.soundFile = soundFile;
	}
	
}
