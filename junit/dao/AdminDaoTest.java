package dao;

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
import com.moesounds.dao.AdminDAO;
import com.moesounds.domain.User;

import configuration.EmbeddedDataSourceConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ApplicationConfiguration.class, EmbeddedDataSourceConfiguration.class})
@SqlGroup({
    @Sql(scripts = "/setup/create-moe-sounds-schema.sql"),
    @Sql(scripts = "/setup/dao/insert-admin-dao-test-info.sql"),
    @Sql(scripts = "/setup/drop-moe-sounds-schema.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
})
@ActiveProfiles("TEST")
@Transactional
public class AdminDaoTest {

	@Autowired
	private AdminDAO adminDAO;
	
	@Test
	public void shouldGetAdminUser() {
		
		List<User> allUsers = adminDAO.getAllUsers();
		assertThat(allUsers.size(), is(2));
		
		User user = allUsers.get(0);
		
		int userId = user.getUserId();
		String nickname = user.getNickname();
		String googleId = user.getGoogleId();
		
		assertThat(userId, is(1));
		assertThat(nickname, is("NYPD"));
		assertThat(googleId, is("8675309"));
		
		
	}
}
