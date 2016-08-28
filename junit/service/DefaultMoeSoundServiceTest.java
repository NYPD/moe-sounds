package service;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.web.multipart.MultipartFile;

import com.moesounds.dao.MoeSoundsDAO;
import com.moesounds.domain.Media;
import com.moesounds.domain.Page;
import com.moesounds.domain.enums.MediaType;
import com.moesounds.model.PageForm;
import com.moesounds.model.PageForm.PageFormFile;
import com.moesounds.service.DefaultMoeSoundsService;

public class DefaultMoeSoundServiceTest {

	private DefaultMoeSoundsService defaultMoeSoundsService;
	
	@Test
	public void shouldInsertPageForm() throws IOException {
		
		MoeSoundsDAO mockMoeSoundsDAO = mock(MoeSoundsDAO.class);
		
		doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				
				Object[] args = invocation.getArguments();
				Page page = (Page) args[0];
				
				Field declaredField = Page.class.getDeclaredField("pageId");
				declaredField.setAccessible(true);
				
				declaredField.set(page, 420);
				
				return null;
			}
			
			
		}).when(mockMoeSoundsDAO).insertPage(any());
		
		defaultMoeSoundsService.setMoeSoundsDAO(mockMoeSoundsDAO);
		
		MultipartFile mockMultipartFile = mock(MultipartFile.class);
		when(mockMultipartFile.isEmpty()).thenReturn(false);
		when(mockMultipartFile.getContentType()).thenReturn(".mp3");
		when(mockMultipartFile.getOriginalFilename()).thenReturn("cool-song");
		when(mockMultipartFile.getSize()).thenReturn(420L);
		when(mockMultipartFile.getBytes()).thenReturn(new byte[0]);
		
		PageFormFile formFile = new PageFormFile();
		formFile.setFile(mockMultipartFile);
		formFile.setMediaType(MediaType.SOUND_FILE);
		
		PageForm pageForm = new PageForm();
		pageForm.setPageName("Cool Page");
		pageForm.setFormFiles(Arrays.asList(formFile));
		
		defaultMoeSoundsService.savePageForm(pageForm);
		Integer pageId = pageForm.getPageId();
		
		assertThat(pageId, is(420));
		verify(mockMoeSoundsDAO, times(1)).insertPage(any());
		verify(mockMoeSoundsDAO, times(1)).insertMedia(any());
		
	}
	
	@Test
	public void shouldUpdatePageForm() throws IOException {
		
		Media mockMedia = mock(Media.class);
		
		Page mockPage = mock(Page.class);
		when(mockPage.getMediaWithMediaType(eq(MediaType.SOUND_FILE))).thenReturn(mockMedia);
		
		MoeSoundsDAO mockMoeSoundsDAO = mock(MoeSoundsDAO.class);
		when(mockMoeSoundsDAO.getPage(eq(1))).thenReturn(mockPage);
		
		defaultMoeSoundsService.setMoeSoundsDAO(mockMoeSoundsDAO);
		
		MultipartFile mockMultipartFile = mock(MultipartFile.class);
		when(mockMultipartFile.isEmpty()).thenReturn(false);
		when(mockMultipartFile.getContentType()).thenReturn(".mp3");
		when(mockMultipartFile.getOriginalFilename()).thenReturn("cool-song");
		when(mockMultipartFile.getSize()).thenReturn(420L);
		when(mockMultipartFile.getBytes()).thenReturn(new byte[0]);
		
		
		MultipartFile mockEmptyMultipartFile = mock(MultipartFile.class);
		when(mockEmptyMultipartFile.isEmpty()).thenReturn(true);
		
		PageFormFile formFileToInsert = new PageFormFile();
		formFileToInsert.setFile(mockMultipartFile);
		formFileToInsert.setMediaType(MediaType.SOUND_FILE);
		
		PageFormFile formFileToUpdate = new PageFormFile();
		formFileToUpdate.setMediaId(1);
		formFileToUpdate.setFile(mockMultipartFile);
		formFileToUpdate.setMediaType(MediaType.SOUND_FILE);
		
		PageFormFile formFileToDelete = new PageFormFile();
		formFileToDelete.setMediaId(2);
		formFileToDelete.setFile(mockEmptyMultipartFile);
		formFileToDelete.setMediaType(MediaType.SOUND_FILE);
		
		PageForm pageForm = new PageForm();
		pageForm.setPageId(1);
		pageForm.setPageName("Cool Page");
		pageForm.setFormFiles(Arrays.asList(formFileToInsert, formFileToUpdate, formFileToDelete));
		
		defaultMoeSoundsService.savePageForm(pageForm);
		
		verify(mockMoeSoundsDAO, times(1)).updatePage(any());
		verify(mockMoeSoundsDAO, times(1)).insertMedia(any());
		verify(mockMoeSoundsDAO, times(1)).updateMedia(any());
		verify(mockMoeSoundsDAO, times(1)).deleteMediaWithMediaId(eq(2));
		
	}
	
	@Test
	public void shouldDeletePageAndMedia() {
		
		MoeSoundsDAO mockMoeSoundsDAO = mock(MoeSoundsDAO.class);
		defaultMoeSoundsService.setMoeSoundsDAO(mockMoeSoundsDAO);
		
		defaultMoeSoundsService.deletePage(420);
		
		verify(mockMoeSoundsDAO).deleteMediaWithPageId(eq(420));
		verify(mockMoeSoundsDAO).deletePage(eq(420));
		
	}
	
	@Before
	public void setUp() {
		defaultMoeSoundsService = new DefaultMoeSoundsService();
	}
	
}