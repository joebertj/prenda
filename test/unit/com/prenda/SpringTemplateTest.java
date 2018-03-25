package unit.com.prenda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prenda.Level;
import com.prenda.helper.CustomPasswordGenerator;
import com.prenda.model.obj.prenda.Users;
import com.prenda.service.UserService;
import com.prenda.service.data.DataLayerPrenda;
import com.prenda.service.data.DataLayerPrendaImpl;
import com.prenda.servlet.UserModify;
import org.junit.Assert;

@ContextConfiguration(locations = { "/unit/resources/prenda-servlet.xml", "/unit/resources/applicationContext.xml" } )
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTemplateTest extends UserModify {
	
	@Transactional
	protected String init(String targetUser) { // create a new Owner
		String password = CustomPasswordGenerator.getPassword(20,true);
		UserService us = new UserService();
		us.saveUser(targetUser,password,Level.OWNER,0,false);
		return password;
	}

	@Transactional
	protected void cleanUp(String targetUser) {
		UserService us = new UserService();
		Users user = us.getUser(targetUser);
		DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
		dataLayerPrenda.delete(user);
		dataLayerPrenda.flushAndClearSession();
	}
	
	@Test
	public void test() {
		Assert.assertTrue(true);
	}
	
}
