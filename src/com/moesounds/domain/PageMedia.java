package com.moesounds.domain;

import java.util.Arrays;

public class PageMedia {

	private int pageMediaId;
	private int pageId;
	private byte[] carouselImageSmall;
	private byte[] carouselImageBig;
	private byte[] backgroundPage;
	private byte[] backgroundInner;
	private String soundFileLocation;
	
	protected PageMedia() {};
	
	public PageMedia(Page page) {
		this.pageId = page.getPageId();
	}
	
	public int getPageMediaId() {
		return pageMediaId;
	}
	public int getPageId() {
		return pageId;
	}
	public byte[] getCarouselImageSmall() {
		return carouselImageSmall;
	}
	public void setCarouselImageSmall(byte[] carouselImageSmall) {
		this.carouselImageSmall = carouselImageSmall;
	}
	public byte[] getCarouselImageBig() {
		return carouselImageBig;
	}
	public void setCarouselImageBig(byte[] carouselImageBig) {
		this.carouselImageBig = carouselImageBig;
	}
	public byte[] getBackgroundPage() {
		return backgroundPage;
	}
	public void setBackgroundPage(byte[] backgroundPage) {
		this.backgroundPage = backgroundPage;
	}
	public byte[] getBackgroundInner() {
		return backgroundInner;
	}
	public void setBackgroundInner(byte[] backgroundInner) {
		this.backgroundInner = backgroundInner;
	}
	public String getSoundFileLocation() {
		return soundFileLocation;
	}
	public void setSoundFileLocation(String soundFileLocation) {
		this.soundFileLocation = soundFileLocation;
	}
	
	@Override
	public String toString() {
		return "PageMedia [pageMediaId=" + pageMediaId + ", pageId=" + pageId + ", carouselImageSmall="
				+ Arrays.toString(carouselImageSmall) + ", carouselImageBig=" + Arrays.toString(carouselImageBig)
				+ ", backgroundPage=" + Arrays.toString(backgroundPage) + ", backgroundInner="
				+ Arrays.toString(backgroundInner) + ", soundFileLocation=" + soundFileLocation + "]";
	}
	
}
