package unit.com.prenda;

import org.junit.Assert;
import org.junit.Test;

import com.prenda.service.LoanService;

public class LoanServiceTest extends LoanService {
	
	@Test
	public void testGetNetProceeds() {
		Assert.assertEquals(791,getNetProceeds(1000, 1, 100, 100), 0.01);
		Assert.assertEquals(1000,getNetProceeds(1100, 0, 0, 100), 0.01);
		Assert.assertEquals(1023.22,getNetProceeds(1234.56, 1, 100, 100), 0.01);
		Assert.assertEquals(1006.19,getNetProceeds(1234.56, 2.5, 100, 100), 0.01);
	}
	
	@Test
	public void testGetRedeemAmount() {
		Assert.assertEquals(936,getRedeemAmount(900,4),0.01);
		Assert.assertEquals(1020,getRedeemAmount(1000,2),0.01);
		Assert.assertEquals(1179.94,getRedeemAmount(1134.56,4),0.01);
		Assert.assertEquals(1162.92,getRedeemAmount(1134.56,2.5),0.01);
	}
	
	@Test
	public void testInterest() {
		Assert.assertEquals(20,getInterest(2100,1,100),0.01);
	}
}
