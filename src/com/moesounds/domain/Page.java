package com.moesounds.domain;

public class Page {

	private int pageId;
	private String pageName;
	private String css;
	private long clickCount;
	private PageMedia pageMedia;
	
	

	/** Mybatis **/
	protected Page(){};
	
	public Page(String pageName, String css) {
		this.pageName = pageName;
		this.css = css;
	}
	
	public int getPageId() {
		return pageId;
	}
	public String getPageName() {
		return pageName;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	public long getClickCount() {
		return clickCount;
	}
	public PageMedia getPageMedia() {
		return pageMedia;
	}
	public void setPageMedia(PageMedia pageMedia) {
		this.pageMedia = pageMedia;
	}
	
	@Override
	public String toString() {
		return "Page [pageId=" + pageId + ", pageName=" + pageName + ", css=" + css + ", clickCount=" + clickCount
				+ "]";
	}
	
}
