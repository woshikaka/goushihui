package test;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ProductServiceTest {
	@Resource
	private ProductService productService;
	
	
	@Test
	public void testPageList() {
//		Page<Product> page = productService.pageList(10);
//		System.out.println(page);
	}
	
	@Test
	public void testBatchShangJia() {
		productService.batchShangJia(Arrays.asList(new Integer[]{1}));
	}
	
}
