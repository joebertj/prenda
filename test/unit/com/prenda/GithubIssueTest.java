package unit.com.prenda;

import org.junit.Assert;
import org.junit.Test;

import com.prenda.Mode;
import com.prenda.helper.CustomPasswordGenerator;
import com.prenda.helper.GithubIssue;
import com.prenda.helper.KeyUtil;

public class GithubIssueTest extends GithubIssue{
	
	@Test
	public void testAuthenticate() {
		Assert.assertTrue(authenticate());
	}
	
	@Test
	public void testCreate() {
		String title = getATitle("joebertj","prenda");
		String body = CustomPasswordGenerator.getPassword(32)+"<br/>"+CustomPasswordGenerator.getPassword(32)+"<br/>"+CustomPasswordGenerator.getPassword(32);
		String [] label = {"bug"};
		String [] assignees = {"joebertj"};
		Assert.assertEquals(0, create(title,body,"joebertj","prenda",label,assignees,Mode.JWT,KeyUtil.getJws()));
	}
	
	@Test
	public void testExists() {
		Assert.assertTrue(exists("x6GdFuLdfP2EW1S7pLI56jXjieMRzO4aPvfrYEkPbYVZNqnAaTl3JwfdlPqbjpTz", "joebertj", "prenda"));
		Assert.assertTrue(exists("nIn1EtoUTpxlZTtT0JOqUdViIEQQ8iGJUWh0pPj4OAIati17rd1dsK7NVyCjSvFv", "joebertj", "prenda"));
	}
}
