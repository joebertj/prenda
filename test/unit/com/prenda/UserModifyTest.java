package unit.com.prenda;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.prenda.Level;
import com.prenda.helper.PasswordEncoderGenerator;
import com.prenda.helper.CustomPasswordGenerator;
import com.prenda.model.obj.prenda.Users;
import com.prenda.service.UserService;
import com.prenda.service.data.DataLayerPrenda;
import com.prenda.service.data.DataLayerPrendaImpl;
import org.junit.Assert;

public class UserModifyTest extends SpringTemplateTest {

	@Test
	@Transactional
	public void testVerifyPassword() {
		String targetUser = CustomPasswordGenerator.getPassword();
		String password = CustomPasswordGenerator.getPassword(20);
		String password2 = CustomPasswordGenerator.getPassword(20);
		String oldPassword = CustomPasswordGenerator.getPassword(20);
		init(targetUser,oldPassword);
		Assert.assertTrue(verifyPassword(targetUser, password, password, password, true));
		Assert.assertTrue(verifyPassword(targetUser, password2, password, password, true));
		Assert.assertFalse(verifyPassword(targetUser, password, password, password2, true));
		Assert.assertFalse(verifyPassword(targetUser, password2, password, password2, true));
		Assert.assertTrue(verifyPassword(targetUser, oldPassword, password, password, false));
		Assert.assertFalse(verifyPassword(targetUser, password, password, password, false));
		Assert.assertFalse(verifyPassword(targetUser, oldPassword, password, password2, false));
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
	
	@Test
	@Transactional
	public void testDeleteUser() {
		String targetOwner = CustomPasswordGenerator.getPassword();
		String targetManager = CustomPasswordGenerator.getPassword();
		String targetEncoder = CustomPasswordGenerator.getPassword();
		String password = CustomPasswordGenerator.getPassword(20,true);
		Users user = init(targetOwner);
		int branchId = user.getBranch();
		Assert.assertEquals("User added successfully", createNewUser(targetOwner,targetManager,password,password,Level.MANAGER, branchId, false));
		Assert.assertEquals("User added successfully", createNewUser(targetManager,targetEncoder,password,password,Level.ENCODER, branchId, false));
		Assert.assertEquals("Your restriction level does not allow you to perform such action", deleteUser("admin", "admin"));
		Assert.assertEquals("Your restriction level does not allow you to perform such action", deleteUser(targetOwner, targetOwner));
		Assert.assertEquals("Your restriction level does not allow you to perform such action", deleteUser(targetManager, targetManager));
		Assert.assertEquals("User " + targetEncoder + " deleted", deleteUser(targetManager, targetEncoder));
		Assert.assertEquals("User " + targetManager + " deleted", deleteUser(targetOwner, targetManager));
		Assert.assertEquals("Your restriction level does not allow you to perform such action", deleteUser(targetEncoder, targetManager));
		Assert.assertEquals("Your restriction level does not allow you to perform such action", deleteUser(targetManager, targetOwner));
		Assert.assertEquals("Your restriction level does not allow you to perform such action", deleteUser("owner", targetManager));
		cleanUp(targetOwner);
		cleanUp(targetManager);
		cleanUp(targetEncoder);
		
	}
	
}
