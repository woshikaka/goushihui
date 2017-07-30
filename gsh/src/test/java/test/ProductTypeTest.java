package test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sfmy.gsh.dao.ProductTypeDao;
import com.sfmy.gsh.entity.ProductSecType;
import com.sfmy.gsh.entity.ProductThirdType;
import com.sfmy.gsh.entity.ProductType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ProductTypeTest {
	@Resource
	private ProductTypeDao productTypeDao;
	
	@Test
	public void testSaveJINKOU() {
		ProductType productType = new ProductType();
		productType.setName("进口商品");
		productType.setIndex(1);
		
		List<ProductSecType> productSecTypes = new ArrayList<ProductSecType>();
		
		ProductSecType secType = new ProductSecType();
		secType.setName("进口零食");
		List<ProductThirdType> thirdTypes = new ArrayList<ProductThirdType>();
		thirdTypes.add(new ProductThirdType("进口饼干糕点"));
		thirdTypes.add(new ProductThirdType("进口糖果"));
		thirdTypes.add(new ProductThirdType("进口冲调"));
		secType.setThirdTypes(thirdTypes);
		productSecTypes.add(secType);
		
		ProductSecType secType1 = new ProductSecType();
		secType1.setName("进口酒水饮料");
		List<ProductThirdType> thirdTypes1 = new ArrayList<ProductThirdType>();
		thirdTypes1.add(new ProductThirdType("进口酒水"));
		thirdTypes1.add(new ProductThirdType("进口饮料"));
		thirdTypes1.add(new ProductThirdType("进口奶制品"));
		secType1.setThirdTypes(thirdTypes1);
		productSecTypes.add(secType1);
		
		ProductSecType secType2 = new ProductSecType();
		secType2.setName("进口粮油调料");
		List<ProductThirdType> thirdTypes2 = new ArrayList<ProductThirdType>();
		thirdTypes2.add(new ProductThirdType("进口粮油"));
		thirdTypes2.add(new ProductThirdType("进口干货"));
		thirdTypes2.add(new ProductThirdType("进口方便食品"));
		secType2.setThirdTypes(thirdTypes2);
		productSecTypes.add(secType2);
		
		
		productType.setProductSecTypes(productSecTypes);
		
		productTypeDao.save(productType);
	}
	@Test
	public void testSaveFUSHILINGSHI() {
		ProductType productType = new ProductType();
		productType.setName("副食零食");
		productType.setIndex(2);
		
		List<ProductSecType> productSecTypes = new ArrayList<ProductSecType>();
		
		ProductSecType secType = new ProductSecType();
		secType.setName("饼干糕点");
		List<ProductThirdType> thirdTypes = new ArrayList<ProductThirdType>();
		thirdTypes.add(new ProductThirdType("常规饼干"));
		thirdTypes.add(new ProductThirdType("装饰饼干"));
		thirdTypes.add(new ProductThirdType("蛋卷"));
		thirdTypes.add(new ProductThirdType("沙琪玛"));
		thirdTypes.add(new ProductThirdType("派类面包"));
		thirdTypes.add(new ProductThirdType("糕饼"));
		thirdTypes.add(new ProductThirdType("威化"));
		thirdTypes.add(new ProductThirdType("夹心饼干"));
		thirdTypes.add(new ProductThirdType("曲奇饼干"));
		thirdTypes.add(new ProductThirdType("进口饼干糕点"));
		secType.setThirdTypes(thirdTypes);
		productSecTypes.add(secType);
		
		ProductSecType secType1 = new ProductSecType();
		secType1.setName("休闲食品");
		List<ProductThirdType> thirdTypes1 = new ArrayList<ProductThirdType>();
		thirdTypes1.add(new ProductThirdType("膨化食品"));
		thirdTypes1.add(new ProductThirdType("坚果类"));
		thirdTypes1.add(new ProductThirdType("素食零食"));
		thirdTypes1.add(new ProductThirdType("海产品"));
		thirdTypes1.add(new ProductThirdType("豆制品"));
		thirdTypes1.add(new ProductThirdType("果冻"));
		thirdTypes1.add(new ProductThirdType("果脯蜜饯"));
		thirdTypes1.add(new ProductThirdType("橄榄"));
		thirdTypes1.add(new ProductThirdType("梅类"));
		thirdTypes1.add(new ProductThirdType("薯片"));
		thirdTypes1.add(new ProductThirdType("瓜子"));
		thirdTypes1.add(new ProductThirdType("花生制品"));
		thirdTypes1.add(new ProductThirdType("猪肉制品"));
		thirdTypes1.add(new ProductThirdType("蛋制品"));
		thirdTypes1.add(new ProductThirdType("凤爪"));
		thirdTypes1.add(new ProductThirdType("禽肉制品"));
		thirdTypes1.add(new ProductThirdType("牛肉制品"));
		thirdTypes1.add(new ProductThirdType("面制品"));
		thirdTypes1.add(new ProductThirdType("进口休闲食品"));
		thirdTypes1.add(new ProductThirdType("鱼制品"));
		secType1.setThirdTypes(thirdTypes1);
		productSecTypes.add(secType1);
		
		ProductSecType secType2 = new ProductSecType();
		secType2.setName("糖果");
		List<ProductThirdType> thirdTypes2 = new ArrayList<ProductThirdType>();
		thirdTypes2.add(new ProductThirdType("巧克力"));
		thirdTypes2.add(new ProductThirdType("硬糖"));
		thirdTypes2.add(new ProductThirdType("口香糖"));
		thirdTypes2.add(new ProductThirdType("玩具糖"));
		thirdTypes2.add(new ProductThirdType("棒棒糖"));
		thirdTypes2.add(new ProductThirdType("软糖"));
		thirdTypes2.add(new ProductThirdType("夹心糖"));
		thirdTypes2.add(new ProductThirdType("进口糖果"));
		secType2.setThirdTypes(thirdTypes2);
		productSecTypes.add(secType2);
		
		ProductSecType secType3 = new ProductSecType();
		secType3.setName("冲调品");
		List<ProductThirdType> thirdTypes3 = new ArrayList<ProductThirdType>();
		thirdTypes3.add(new ProductThirdType("奶茶"));
		thirdTypes3.add(new ProductThirdType("咖啡"));
		thirdTypes3.add(new ProductThirdType("茶叶"));
		thirdTypes3.add(new ProductThirdType("蜂蜜"));
		thirdTypes3.add(new ProductThirdType("糊粉饮品"));
		thirdTypes3.add(new ProductThirdType("保健品"));
		thirdTypes3.add(new ProductThirdType("进口冲调"));
		secType3.setThirdTypes(thirdTypes3);
		productSecTypes.add(secType3);
		
		productType.setProductSecTypes(productSecTypes);
		
		productTypeDao.save(productType);
	}
	
	@Test
	public void testSaveJIUSHUIYINLIAO() {
		ProductType productType = new ProductType();
		productType.setName("酒水饮料");
		productType.setIndex(3);
		
		List<ProductSecType> productSecTypes = new ArrayList<ProductSecType>();
		
		ProductSecType secType = new ProductSecType();
		secType.setName("酒水");
		List<ProductThirdType> thirdTypes = new ArrayList<ProductThirdType>();
		thirdTypes.add(new ProductThirdType("高度白酒>50°"));
		thirdTypes.add(new ProductThirdType("高度白酒<50°"));
		thirdTypes.add(new ProductThirdType("葡萄酒"));
		thirdTypes.add(new ProductThirdType("啤酒"));
		thirdTypes.add(new ProductThirdType("黄酒"));
		thirdTypes.add(new ProductThirdType("养生酒"));
		thirdTypes.add(new ProductThirdType("陈年老酒"));
		thirdTypes.add(new ProductThirdType("预调酒"));
		thirdTypes.add(new ProductThirdType("米酒"));
		thirdTypes.add(new ProductThirdType("进口酒水"));
		secType.setThirdTypes(thirdTypes);
		productSecTypes.add(secType);
		
		ProductSecType secType1 = new ProductSecType();
		secType1.setName("饮料");
		List<ProductThirdType> thirdTypes1 = new ArrayList<ProductThirdType>();
		thirdTypes1.add(new ProductThirdType("饮料水"));
		thirdTypes1.add(new ProductThirdType("果蔬饮料"));
		thirdTypes1.add(new ProductThirdType("功能饮料"));
		thirdTypes1.add(new ProductThirdType("碳酸饮料"));
		thirdTypes1.add(new ProductThirdType("茶饮料"));
		thirdTypes1.add(new ProductThirdType("咖啡饮料"));
		thirdTypes1.add(new ProductThirdType("醋饮料"));
		thirdTypes1.add(new ProductThirdType("进口饮料"));
		secType1.setThirdTypes(thirdTypes1);
		productSecTypes.add(secType1);
		
		ProductSecType secType2 = new ProductSecType();
		secType2.setName("奶制品");
		List<ProductThirdType> thirdTypes2 = new ArrayList<ProductThirdType>();
		thirdTypes2.add(new ProductThirdType("纯牛奶"));
		thirdTypes2.add(new ProductThirdType("含乳饮料"));
		thirdTypes2.add(new ProductThirdType("植物蛋白饮料"));
		thirdTypes2.add(new ProductThirdType("酸奶"));
		thirdTypes2.add(new ProductThirdType("进口奶制品"));
		secType2.setThirdTypes(thirdTypes2);
		productSecTypes.add(secType2);
		
		productType.setProductSecTypes(productSecTypes);
		
		productTypeDao.save(productType);
	}
	
	@Test
	public void testSaveLIANGYOUTIAOLIAO() {
		ProductType productType = new ProductType();
		productType.setName("粮油调料");
		productType.setIndex(4);
		
		List<ProductSecType> productSecTypes = new ArrayList<ProductSecType>();
		
		ProductSecType secType = new ProductSecType();
		secType.setName("粮油制品");
		List<ProductThirdType> thirdTypes = new ArrayList<ProductThirdType>();
		thirdTypes.add(new ProductThirdType("食用油"));
		thirdTypes.add(new ProductThirdType("面粉"));
		thirdTypes.add(new ProductThirdType("米"));
		thirdTypes.add(new ProductThirdType("杂粮"));
		thirdTypes.add(new ProductThirdType("面条类"));
		thirdTypes.add(new ProductThirdType("年糕"));
		thirdTypes.add(new ProductThirdType("进口粮油制品"));
		secType.setThirdTypes(thirdTypes);
		productSecTypes.add(secType);
		
		ProductSecType secType1 = new ProductSecType();
		secType1.setName("调味品");
		List<ProductThirdType> thirdTypes1 = new ArrayList<ProductThirdType>();
		thirdTypes1.add(new ProductThirdType("酱油"));
		thirdTypes1.add(new ProductThirdType("醋"));
		thirdTypes1.add(new ProductThirdType("糖"));
		thirdTypes1.add(new ProductThirdType("调味汁"));
		thirdTypes1.add(new ProductThirdType("调味粉"));
		thirdTypes1.add(new ProductThirdType("调味精"));
		thirdTypes1.add(new ProductThirdType("盐"));
		thirdTypes1.add(new ProductThirdType("进口调味"));
		thirdTypes1.add(new ProductThirdType("辣椒酱"));
		secType1.setThirdTypes(thirdTypes1);
		productSecTypes.add(secType1);
		
		ProductSecType secType2 = new ProductSecType();
		secType2.setName("干货");
		List<ProductThirdType> thirdTypes2 = new ArrayList<ProductThirdType>();
		thirdTypes2.add(new ProductThirdType("干货"));
		thirdTypes2.add(new ProductThirdType("干海鲜"));
		thirdTypes2.add(new ProductThirdType("菌类"));
		thirdTypes2.add(new ProductThirdType("进口干货"));
		secType2.setThirdTypes(thirdTypes2);
		productSecTypes.add(secType2);
		
		ProductSecType secType3 = new ProductSecType();
		secType3.setName("速冻/方便食品");
		List<ProductThirdType> thirdTypes3 = new ArrayList<ProductThirdType>();
		thirdTypes3.add(new ProductThirdType("速冻食品"));
		thirdTypes3.add(new ProductThirdType("方便面"));
		thirdTypes3.add(new ProductThirdType("方便粥"));
		thirdTypes3.add(new ProductThirdType("酱菜"));
		thirdTypes3.add(new ProductThirdType("腐乳"));
		thirdTypes3.add(new ProductThirdType("家禽蛋类"));
		thirdTypes3.add(new ProductThirdType("肉松"));
		thirdTypes3.add(new ProductThirdType("香肠"));
		thirdTypes3.add(new ProductThirdType("进口方便食品"));
		secType3.setThirdTypes(thirdTypes3);
		productSecTypes.add(secType3);
		
//		ProductSecType secType4 = new ProductSecType();
//		secType4.setName("罐头");
//		List<ProductThirdType> thirdTypes4 = new ArrayList<ProductThirdType>();
//		thirdTypes4.add(new ProductThirdType("肉类罐头"));
//		thirdTypes4.add(new ProductThirdType("水产罐头"));
//		thirdTypes4.add(new ProductThirdType("水果罐头"));
//		thirdTypes4.add(new ProductThirdType("进口罐头"));
//		secType4.setThirdTypes(thirdTypes4);
//		productSecTypes.add(secType4);
		
		productType.setProductSecTypes(productSecTypes);
		
		productTypeDao.save(productType);
	}
}
