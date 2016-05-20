package service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

import static org.mockito.Mockito.doAnswer;

import java.lang.reflect.Field;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.moesounds.dao.MoeSoundsDAO;
import com.moesounds.domain.Page;
import com.moesounds.model.PageForm;
import com.moesounds.service.DefaultMoeSoundsService;

public class DefaultMoeSoundServiceTest {

private DefaultMoeSoundsService defaultMoeSoundsService = new DefaultMoeSoundsService();
	
	@Test
	public void shouldInsertPageForm() {
		
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
		
		PageForm pageForm = new PageForm();
		
		defaultMoeSoundsService.savePageForm(pageForm);
		Integer pageId = pageForm.getPageId();
		
		assertThat(pageId, is(420));
		verify(mockMoeSoundsDAO).insertPage(any());
		verify(mockMoeSoundsDAO).insertPageMedia(any());
		
	}
	
	@Test
	public void shouldDeletePageAndPageMedia() {
		
		MoeSoundsDAO mockMoeSoundsDAO = mock(MoeSoundsDAO.class);
		defaultMoeSoundsService.setMoeSoundsDAO(mockMoeSoundsDAO);
		
		defaultMoeSoundsService.deletePage(420);
		
		verify(mockMoeSoundsDAO).deletePageMediaWithPageId(eq(420));
		verify(mockMoeSoundsDAO).deletePage(eq(420));
		
	}
}
