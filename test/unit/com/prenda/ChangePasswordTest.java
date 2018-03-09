package unit.com.prenda;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "/unit/resources/applicationContext.xml" } )
@RunWith(SpringJUnit4ClassRunner.class)
public class ChangePasswordTest {
	
	@Test
	public void testEncoderChange(){
		Assert.assertEquals(0,0);
	}

}
