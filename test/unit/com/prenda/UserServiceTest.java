package unit.com.prenda;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.prenda.Level;
import com.prenda.helper.CustomPasswordGenerator;
import com.prenda.helper.PasswordEncoderGenerator;
import com.prenda.model.obj.prenda.Users;
import com.prenda.service.UserService;

public class UserServiceTest extends SpringTemplateTest {
	
	@Test
	@Transactional
	public void testSaveUser() {
		String targetUser = CustomPasswordGenerator.getPassword();
		String password = CustomPasswordGenerator.getPassword(20,true);
		UserService us = new UserService();
		Users user = new Users();
		user=us.saveUser(targetUser, password, Level.OWNER, 0, false);
		Assert.assertTrue(PasswordEncoderGenerator.matches(password, user.getPassword()));
		cleanUp(targetUser);
	}
}
