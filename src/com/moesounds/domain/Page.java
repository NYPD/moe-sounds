package com.moesounds.domain;

public class Page {

	private Integer pageId;
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
	
	public Integer getPageId() {
		return pageId;
	}
	public String getPageName() {
		return pageName;
	}
	public String getCss() {
		return css;
	}
	public long getClickCount() {
		return clickCount;
	}
	public PageMedia getPageMedia() {
		return pageMedia;
	}

	@Override
	public String toString() {
		return "Page [pageId=" + pageId + ", pageName=" + pageName + ", css=" + css + ", clickCount=" + clickCount
				+ ", pageMedia=" + pageMedia + "]";
	}
	
}
