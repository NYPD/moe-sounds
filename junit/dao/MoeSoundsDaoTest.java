package dao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

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
	public void shouldInsertPage() {
		
		Page page = new Page("Cool Page Name", "intense css");
		moeSoundsDAO.insertPage(page);
		
		assertThat(page.getPageId(), notNullValue());
		
	}
	
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
	public void shouldGetAllPages() {
		
		List<Page> allPages = moeSoundsDAO.getAllPages();
		
		assertThat(allPages.size(), is(2));
		
		Page page = allPages.get(0);
		
		String css = page.getCss();
		Integer pageId = page.getPageMedia().getPageId();
		
		assertThat(css, is("p{color=red;}"));
		assertThat(pageId, is(page.getPageId()));
		
	}
}
