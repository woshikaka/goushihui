package test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sfmy.gsh.entity.ProductType;
import com.sfmy.gsh.service.ProductTypeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ProductTypeServiceTest {
	@Resource
	private ProductTypeService productTypeService;
	
	@Test
	public void testSaveUser() {
		List<ProductType> types = productTypeService.findAll();
		System.out.println(types.get(0).getProductSecTypes().get(0).getName());
	}
}
