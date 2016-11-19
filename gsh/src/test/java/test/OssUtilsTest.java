package test;

import java.io.ByteArrayInputStream;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sfmy.gsh.utils.MyWebUtils;
import com.sfmy.gsh.utils.OssUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OssUtilsTest {
	@Resource
	private MyWebUtils myWebUtils;
	
	@Test
	public void test() {
		System.err.println(System.getProperty("catalina.base"));
	}
}
