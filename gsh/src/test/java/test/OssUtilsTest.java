package test;

import java.io.ByteArrayInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sfmy.gsh.utils.OssUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OssUtilsTest {
	@Test
	public void test() {
		String content = "Hello OSS test";
		OssUtils.upload("test1",new ByteArrayInputStream(content.getBytes()));
	}
}
