package com.moesounds.domain;

public class Page {

	private int pageId;
	private String pageName;
	private String css;
	private long clickCount;
	
	
	public int getPageId() {
		return pageId;
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
	public long getClickCount() {
		return clickCount;
	}
	public void setClickCount(long clickCount) {
		this.clickCount = clickCount;
	}
	
	
	@Override
	public String toString() {
		return "Page [pageId=" + pageId + ", pageName=" + pageName + ", css=" + css + ", clickCount=" + clickCount
				+ "]";
	}
}
