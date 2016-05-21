package com.moesounds.domain;

import org.springframework.util.StringUtils;

public class Page {

	private Integer pageId;
	private String pageName;
	private String css;
	private long clickCount;
	private PageMedia pageMedia;

	// For MyBatis
	protected Page(){};
	
	public Page(String pageName, String css) {
		this.updatePageName(pageName);
		this.updateCss(css);
	}
	
	public void updatePageName(String pageName) {
		
		boolean isEmpty = StringUtils.isEmpty(pageName);
		if(isEmpty) throw new IllegalArgumentException();
		
		this.pageName = pageName;
		
	}
	public void updateCss(String css) {
		this.css = css;
	}
	
	//Default Accessors
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
