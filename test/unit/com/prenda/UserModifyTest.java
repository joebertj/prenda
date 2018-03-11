package unit.com.prenda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prenda.Level;
import com.prenda.helper.PasswordEncoderGenerator;
import com.prenda.helper.PasswordGenerator;
import com.prenda.model.obj.prenda.Users;
import com.prenda.service.UserService;
import com.prenda.services.data.DataLayerPrenda;
import com.prenda.services.data.DataLayerPrendaImpl;
import com.prenda.servlet.UserModify;

import org.junit.Assert;

@ContextConfiguration(locations = { "/unit/resources/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserModifyTest extends UserModify {

	public UserModifyTest() {

	}

	@Transactional
	private void init(String targetUser) {
		UserService us = new UserService();
		Users user = us.getUser("owner");
		String password = "123";
		Users newUser;
		try {
			newUser = user.clone();
			newUser.setUsername(targetUser);
			saveUser(newUser, password);
		} catch (CloneNotSupportedException e) {
			Assert.fail(e.toString());
		}
	}

	@Transactional
	private void cleanUp(String targetUser) {
		UserService us = new UserService();
		Users user = us.getUser(targetUser);
		DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
		dataLayerPrenda.delete(user);
		dataLayerPrenda.flushAndClearSession();
	}

	@Test
	@Transactional
	public void testVerifyPassword() {
		String targetUser = PasswordGenerator.getPassword();
		String password = PasswordGenerator.getPassword();
		String password2 = PasswordGenerator.getPassword();
		init(targetUser);
		Assert.assertTrue(verifyPassword(targetUser, password, password, password, true));
		Assert.assertTrue(verifyPassword(targetUser, password2, password, password, true));
		Assert.assertFalse(verifyPassword(targetUser, password, password, password2, true));
		Assert.assertFalse(verifyPassword(targetUser, password2, password, password2, true));
		Assert.assertTrue(verifyPassword(targetUser, "123", password, password, false));
		Assert.assertFalse(verifyPassword(targetUser, password, password, password, false));
		Assert.assertFalse(verifyPassword(targetUser, "123", password, password2, false));
		Assert.assertFalse(verifyPassword(targetUser, password, password, password2, false));
		cleanUp(targetUser);
	}

	@Test
	@Transactional
	public void testSavePassword() {
		String targetUser = PasswordGenerator.getPassword();
		String password = PasswordGenerator.getPassword();
		init(targetUser);
		UserService us = new UserService();
		Users user = us.getUser(targetUser);
		Assert.assertEquals("Password changed successfully", savePassword(user, password));
		Assert.assertTrue(PasswordEncoderGenerator.matches(password, user.getPassword()));
		cleanUp(targetUser);
	}

	@Test
	@Transactional
	public void testSaveUser() {
		String targetUser = PasswordGenerator.getPassword();
		String password = PasswordGenerator.getPassword();
		init(targetUser);
		UserService us = new UserService();
		Users user = us.getUser(targetUser);
		Assert.assertEquals("User added successfully", saveUser(user, password));
		Assert.assertTrue(PasswordEncoderGenerator.matches(password, user.getPassword()));
		cleanUp(targetUser);
	}

	@Test
	@Transactional
	public void testCreateNewUser() {
		String targetUser = PasswordGenerator.getPassword();
		String password = PasswordGenerator.getPassword();
		String password2 = PasswordGenerator.getPassword();
		Assert.assertEquals("Your restriction level does not allow you to perform such action", createNewUser("admin", "admincopy", password, password, Level.ADMIN, 1, false));
		Assert.assertEquals("User added successfully", createNewUser("admin", targetUser, password, password, Level.OWNER, 1, false));
		Assert.assertEquals("New password does not match", createNewUser("admin", targetUser, password, password2, Level.OWNER, 1, false));
		Assert.assertEquals("Your restriction level does not allow you to perform such action", createNewUser("owner", "admincopy", password, password, Level.ADMIN, 1, false));
		Assert.assertEquals("Your restriction level does not allow you to perform such action", createNewUser("owner", targetUser, password, password, Level.OWNER, 1, false));
		Assert.assertEquals("User added successfully", createNewUser("owner", "manager", password, password, Level.MANAGER, 1, false));
		Assert.assertEquals("User added successfully", createNewUser("admin","encoder",password,password,Level.ENCODER, 1, false));
		Assert.assertEquals("User added successfully", createNewUser("admin","managercopy",password,password,Level.MANAGER, 1, false));
		Assert.assertEquals("Your restriction level does not allow you to perform such action", createNewUser("encoder","encodercopy",password,password,Level.ENCODER, 1, false));
		Assert.assertEquals("Your restriction level does not allow you to perform such action", createNewUser("manager","managercopy",password,password,Level.MANAGER, 1, false));
		Assert.assertEquals("User added successfully", createNewUser("manager","encodercopy",password,password,Level.ENCODER, 1, false));
		cleanUp("encoder");
		cleanUp("encodercopy");
		cleanUp("manager");
		cleanUp("managercopy");
		cleanUp(targetUser);
	}

	@Test
	@Transactional
	public void testChangePassword() {
		String targetUser = PasswordGenerator.getPassword();
		String password = PasswordGenerator.getPassword();
		String password2 = PasswordGenerator.getPassword();
		init(targetUser);
		UserService us = new UserService();
		Users user = us.getUser("admin");
		String adminPassword = user.getPassword();
		savePassword(user, "123");
		Assert.assertEquals("Either the old password is not correct or the new password does not match", changePassword("admin", "admin", password, password, password));
		Assert.assertEquals("Either the old password is not correct or the new password does not match", changePassword("admin", "admin", adminPassword, password, password2));
		Assert.assertEquals("Password changed successfully", changePassword("admin", "admin", "123", password, password));
		Assert.assertEquals("New password does not match", changePassword("admin", targetUser, password, password, password2));
		Assert.assertEquals("Password changed successfully", changePassword("admin", targetUser, password, password, password));
		Assert.assertEquals("New password does not match",changePassword("admin", targetUser, password2, password, password2));
		Assert.assertEquals("Your restriction level does not allow you to perform such action", changePassword("owner", targetUser, password, password, password));
		Assert.assertEquals("Your restriction level does not allow you to perform such action", changePassword("owner", targetUser, password, password, password));
		Assert.assertEquals("Your restriction level does not allow you to perform such action", changePassword("owner", targetUser, password, password, password));
		user.setPassword(adminPassword);
		DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
		dataLayerPrenda.update(user);
		dataLayerPrenda.flushAndClearSession();
		Assert.assertEquals("User added successfully", createNewUser("owner","encoder",password,password,Level.ENCODER, 1, false));
		Assert.assertEquals("Password changed successfully", changePassword("encoder", "encoder", password, password2, password2));
		Assert.assertEquals("Either the old password is not correct or the new password does not match", changePassword("encoder", "encoder", password, password, password));
		cleanUp("encoder");
		Assert.assertEquals("Invalid user", changePassword("encoder", "encoder", password, password2, password2));
		cleanUp(targetUser);
		Assert.assertEquals("Invalid user", changePassword(targetUser, targetUser, password, password2, password2));
	}
}
