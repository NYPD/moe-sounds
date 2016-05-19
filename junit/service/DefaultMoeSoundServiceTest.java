package service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.moesounds.dao.MoeSoundsDAO;
import com.moesounds.model.PageForm;
import com.moesounds.service.DefaultMoeSoundsService;

public class DefaultMoeSoundServiceTest {

private DefaultMoeSoundsService defaultMoeSoundsService = new DefaultMoeSoundsService();
	
	@Test
	public void shouldInsertPageForm() {
		
		MoeSoundsDAO mockMoeSoundsDAO = mock(MoeSoundsDAO.class);
		defaultMoeSoundsService.setMoeSoundsDAO(mockMoeSoundsDAO);
		
		PageForm pageForm = new PageForm();
		
		defaultMoeSoundsService.savePageForm(pageForm);
		
		verify(mockMoeSoundsDAO).insertPage(any());
		verify(mockMoeSoundsDAO).insertPageMedia(any());
		
	}
}
