package unit.com.prenda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prenda.Level;
import com.prenda.helper.PasswordEncoderGenerator;
import com.prenda.helper.CustomPasswordGenerator;
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
		String targetUser = CustomPasswordGenerator.getPassword();
		String password = CustomPasswordGenerator.getPassword(20);
		String password2 = CustomPasswordGenerator.getPassword(20);
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
		String targetUser = CustomPasswordGenerator.getPassword();
		String password = CustomPasswordGenerator.getPassword(20,true);
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
		String targetUser = CustomPasswordGenerator.getPassword();
		String password = CustomPasswordGenerator.getPassword(20,true);
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
		String targetUser = CustomPasswordGenerator.getPassword();
		String password = CustomPasswordGenerator.getPassword(20,true);
		String password2 = CustomPasswordGenerator.getPassword(20,true);
		Assert.assertEquals("Your restriction level does not allow you to perform such action", createNewUser("admin", targetUser, password, password, Level.ADMIN, 1, false));
		Assert.assertEquals("User added successfully", createNewUser("admin", targetUser, password, password, Level.OWNER, 1, false));
		cleanUp(targetUser);
		Assert.assertEquals("New password does not match", createNewUser("admin", targetUser, password, password2, Level.OWNER, 1, false));
		Assert.assertEquals("Your restriction level does not allow you to perform such action", createNewUser("owner", targetUser, password, password, Level.ADMIN, 1, false));
		Assert.assertEquals("Your restriction level does not allow you to perform such action", createNewUser("owner", targetUser, password, password, Level.OWNER, 1, false));
		String targetManager = CustomPasswordGenerator.getPassword();
		Assert.assertEquals("User added successfully", createNewUser("owner", targetManager, password, password, Level.MANAGER, 2, false));
		String targetEncoder = CustomPasswordGenerator.getPassword();
		Assert.assertEquals("User added successfully", createNewUser("admin",targetEncoder,password,password,Level.ENCODER, 1, false));
		String targetManager2 = CustomPasswordGenerator.getPassword();
		Assert.assertEquals("User added successfully", createNewUser("admin",targetManager2,password,password,Level.MANAGER, 1, false));
		Assert.assertEquals("Your restriction level does not allow you to perform such action", createNewUser(targetEncoder,targetUser,password,password,Level.ENCODER, 2, false));
		Assert.assertEquals("Your restriction level does not allow you to perform such action", createNewUser(targetManager,targetUser,password,password,Level.MANAGER, 2, false));
		Assert.assertEquals("User added successfully", createNewUser(targetManager,targetUser,password,password,Level.ENCODER, 2, false));
		cleanUp(targetManager);
		cleanUp(targetEncoder);
		cleanUp(targetManager2);
		cleanUp(targetUser);
	}

	@Test
	@Transactional
	public void testChangePassword() {
		String targetUser = CustomPasswordGenerator.getPassword();
		String password = CustomPasswordGenerator.getPassword(20,true);
		String password2 = CustomPasswordGenerator.getPassword(20,true);
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
		cleanUp(targetUser);
		Assert.assertEquals("User added successfully", createNewUser("owner",targetUser,password,password,Level.ENCODER, 2, false));
		Assert.assertEquals("Password changed successfully", changePassword(targetUser, targetUser, password, password2, password2));
		Assert.assertEquals("Either the old password is not correct or the new password does not match", changePassword(targetUser, targetUser, password, password, password));
		cleanUp(targetUser);
		Assert.assertEquals("Invalid user", changePassword(targetUser, targetUser, password, password2, password2));
	}
}
