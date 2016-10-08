package dao;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;

import com.moesounds.configuration.ApplicationConfiguration;
import com.moesounds.dao.MoeSoundsDAO;
import com.moesounds.domain.Media;
import com.moesounds.domain.Page;
import com.moesounds.domain.enums.MediaType;

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
		Map<MediaType, Media> media = page.getMedia();
		
		assertThat(page.getPageName(), is("Rip"));
		assertThat(page.getCss(), is("p{color=red;}"));
		assertThat(media, notNullValue());
		
	}
	
	@Test
	public void shouldGetAllPages() {
		
		List<Page> allPages = moeSoundsDAO.getAllPages();
		
		assertThat(allPages.size(), is(2));
		
		Page page = allPages.get(0);
		
		String css = page.getCss();
		Integer pageId = page.getMedia().get(MediaType.BACKGROUND_INNER).getPageId();
		
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
		
		page.updatePage("Cool beans", "div{color=blue;}");
		moeSoundsDAO.updatePage(page);
		
		page = moeSoundsDAO.getPage(2);
		
		assertThat(page.getPageName(), is("Cool beans"));
		assertThat(page.getCss(), is("div{color=blue;}"));
	}
	
	
	@Test
	public void shouldDeletePage() {
		
		List<Page> allPages = moeSoundsDAO.getAllPages();
		assertThat(allPages.size(), is(2));
		
		moeSoundsDAO.deleteMediaWithPageId(1);
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
	public void shouldInsertMedia() throws IOException {
		
		Page page = new Page("Cool Page Name", "intense css");
		moeSoundsDAO.insertPage(page);
		
		
		MultipartFile mockMultipartFile = mock(MultipartFile.class);
		when(mockMultipartFile.isEmpty()).thenReturn(false);
		when(mockMultipartFile.getContentType()).thenReturn(".disco");
		when(mockMultipartFile.getOriginalFilename()).thenReturn("beans");
		when(mockMultipartFile.getSize()).thenReturn(420L);
		when(mockMultipartFile.getBytes()).thenReturn(new byte[0]);
		
		Media media = new Media(page, mockMultipartFile, MediaType.BACKGROUND_INNER);
		
		moeSoundsDAO.insertMedia(media);
		
		assertThat(media.getMediaId(), notNullValue());
		assertThat(media.getPageId(), is(page.getPageId()));
		
	}
	
	@Test
	public void shouldUpdateMedia() throws IOException {
		
		Page page = moeSoundsDAO.getPage(1);
		Media media = page.getMedia().get(MediaType.BACKGROUND_INNER);
		
		String fileName = media.getFileName();
		String fileType = media.getFileType();
		
		assertThat(fileName, is("test"));
		assertThat(fileType, is("mp3"));
		
		MultipartFile mockMultipartFile = mock(MultipartFile.class);
		when(mockMultipartFile.isEmpty()).thenReturn(false);
		when(mockMultipartFile.getContentType()).thenReturn(".disco");
		when(mockMultipartFile.getOriginalFilename()).thenReturn("beans");
		when(mockMultipartFile.getSize()).thenReturn(420L);
		when(mockMultipartFile.getBytes()).thenReturn(new byte[0]);
		
		media.updateMedia(mockMultipartFile);
		
		moeSoundsDAO.updateMedia(media);
		
		moeSoundsDAO.getPage(1);
		media = page.getMedia().get(MediaType.BACKGROUND_INNER);
		
		fileName = media.getFileName();
		fileType = media.getFileType();
		
		assertThat(fileName, is("beans"));
		assertThat(fileType, is(".disco"));
	}
	
	@Test
	public void shouldDeleteMediaWithPageId() {
		
		Page page = moeSoundsDAO.getPage(1);
		Map<MediaType,Media> media = page.getMedia();
		
		assertThat(media.size(), is(1));
		
		moeSoundsDAO.deleteMediaWithPageId(1);
		
		page = moeSoundsDAO.getPage(1);
		media = page.getMedia();
		
		assertThat(media.size(), is(0));
		
	}

	@Test
	public void shouldDeleteMediaWithMediaId() {
		
		Page page = moeSoundsDAO.getPage(2);
		Map<MediaType,Media> media = page.getMedia();
		
		assertThat(media.size(), is(2));
		
		moeSoundsDAO.deleteMediaWithMediaId(3);
		
		page = moeSoundsDAO.getPage(2);
		media = page.getMedia();
		
		assertThat(media.size(), is(1));
		
	}
	
	
	
}
