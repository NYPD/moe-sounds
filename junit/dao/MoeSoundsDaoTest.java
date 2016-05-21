package dao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.moesounds.configuration.ApplicationConfiguration;
import com.moesounds.dao.MoeSoundsDAO;
import com.moesounds.domain.Page;
import com.moesounds.domain.PageMedia;

import configuration.EmbeddedDataSourceConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ApplicationConfiguration.class, EmbeddedDataSourceConfiguration.class})
@SqlGroup({
    @Sql(scripts = "/setup/create-moe-sounds-schema.sql"),
    @Sql(scripts = "/setup/dao/insert-moe-sounds-dao-test-info.sql"),
    @Sql(scripts = "/setup/drop-moe-sounds-schema.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD )
})
@ActiveProfiles("TEST")
@Transactional
public class MoeSoundsDaoTest {

	@Autowired
	private MoeSoundsDAO moeSoundsDAO;
	
	
	@Test
	public void shouldGetPage() {
		
		Page page = moeSoundsDAO.getPage(1);
		PageMedia pageMedia = page.getPageMedia();
		
		assertThat(page.getPageName(), is("Rip"));
		assertThat(page.getCss(), is("p{color=red;}"));
		assertThat(pageMedia, notNullValue());
		
	}
	
	@Test
	public void shouldGetAllPages() {
		
		List<Page> allPages = moeSoundsDAO.getAllPages();
		
		assertThat(allPages.size(), is(2));
		
		Page page = allPages.get(0);
		
		String css = page.getCss();
		Integer pageId = page.getPageMedia().getPageId();
		
		assertThat(css, is("p{color=red;}"));
		assertThat(pageId, is(page.getPageId()));
		
	}
	
	
	@Test
	public void shouldInsertPage() {
		
		Page page = new Page("Cool Page Name", "intense css");
		moeSoundsDAO.insertPage(page);
		
		assertThat(page.getPageId(), notNullValue());
		
	}
	
	@Test
	public void shouldUpdatePage() {
		
		Page page = moeSoundsDAO.getPage(2);
		
		assertThat(page.getPageName(), is("Rip2"));
		assertThat(page.getCss(), is("p{color=blue;}"));
		
		page.updatePageName("Cool beans");
		page.updateCss("div{color=blue;}");
		moeSoundsDAO.updatePage(page);
		
		page = moeSoundsDAO.getPage(2);
		
		assertThat(page.getPageName(), is("Cool beans"));
		assertThat(page.getCss(), is("div{color=blue;}"));
	}
	
	
	@Test
	public void shouldDeletePage() {
		
		List<Page> allPages = moeSoundsDAO.getAllPages();
		assertThat(allPages.size(), is(2));
		
		moeSoundsDAO.deletePageMediaWithPageId(1);
		moeSoundsDAO.deletePage(1);
		
		allPages = moeSoundsDAO.getAllPages();
		assertThat(allPages.size(), is(1));
		
	}
	
	@Test
	public void shouldUpdateClickCount() {
		
		Page page = moeSoundsDAO.getPage(2);
		long clickCount = page.getClickCount();
		
		assertThat(clickCount, is(0L));
		
		moeSoundsDAO.updateClickCount(2);
		
		page = moeSoundsDAO.getPage(2);
		clickCount = page.getClickCount();
		
		assertThat(clickCount, is(1L));
		
	}
	
	@Test
	public void shouldGetClickCount() {
		
		moeSoundsDAO.updateClickCount(2);
		moeSoundsDAO.updateClickCount(2);
		moeSoundsDAO.updateClickCount(2);
		moeSoundsDAO.updateClickCount(2);
		moeSoundsDAO.updateClickCount(2);
		
		long clickCount = moeSoundsDAO.getClickCount(2);
		
		
		assertThat(clickCount, is(5L));
		
	}
	
	
	
	//Page Media Stuff ******************************************************************
	@Test
	public void shouldInsertPageMedia() {
		
		Page page = new Page("Cool Page Name", "intense css");
		moeSoundsDAO.insertPage(page);
		
		PageMedia pageMedia = new PageMedia(page);
		
		moeSoundsDAO.insertPageMedia(pageMedia);
		
		assertThat(pageMedia.getPageMediaId(), notNullValue());
		assertThat(pageMedia.getPageId(), is(page.getPageId()));
		
	}
	
	@Test
	public void shouldDeletePageMedia() {
		
		Page page = moeSoundsDAO.getPage(1);
		PageMedia pageMedia = page.getPageMedia();
		
		assertThat(pageMedia, notNullValue());
		
		moeSoundsDAO.deletePageMediaWithPageId(1);
		
		page = moeSoundsDAO.getPage(1);
		pageMedia = page.getPageMedia();
		
		assertThat(pageMedia, nullValue());
		
	}
	
	
}
