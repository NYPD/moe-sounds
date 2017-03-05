package dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

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
import com.moesounds.domain.UserApiIdentity;
import com.moesounds.domain.enums.ApiType;
import com.moesounds.domain.enums.UserRole;

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
        UserRole userRole = user.getUserRole();

        assertThat(userId, is(1));
        assertThat(nickname, is("NYPD"));
        assertThat(userRole, is(UserRole.ADMIN));

        UserApiIdentity userApiIdentity = user.getUserApiIdentity(ApiType.GOOGLE);

        String apiUserId = userApiIdentity.getApiUserId();
        assertThat(apiUserId, is("8675309"));
    }

    @Test
    public void shouldGetNYPDWithUserId() {

        User user = adminDAO.getUserWithUserId(1);

        int userId = user.getUserId();
        String nickname = user.getNickname();
        UserRole userRole = user.getUserRole();

        assertThat(userId, is(1));
        assertThat(nickname, is("NYPD"));
        assertThat(userRole, is(UserRole.ADMIN));

        UserApiIdentity userApiIdentity = user.getUserApiIdentity(ApiType.GOOGLE);

        String apiUserId = userApiIdentity.getApiUserId();
        assertThat(apiUserId, is("8675309"));
    }

    @Test
    public void shouldGetNYPDWithApiInfo() {

        User user = adminDAO.getUserWithApiInfo(ApiType.GOOGLE, "8675309");

        int userId = user.getUserId();
        String nickname = user.getNickname();
        UserRole userRole = user.getUserRole();

        assertThat(userId, is(1));
        assertThat(nickname, is("NYPD"));
        assertThat(userRole, is(UserRole.ADMIN));

        UserApiIdentity userApiIdentity = user.getUserApiIdentity(ApiType.GOOGLE);

        String apiUserId = userApiIdentity.getApiUserId();
        assertThat(apiUserId, is("8675309"));
    }
}
