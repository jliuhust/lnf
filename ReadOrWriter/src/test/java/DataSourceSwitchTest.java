import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.qb.dynamic.TestDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class DataSourceSwitchTest{

	@Autowired
	private TestDao testDao;
	
	@Test
	public void testServiceOk() throws Exception {
		System.out.println("start");
		testDao.readData();	
		System.out.println("end");
	}
	
}
