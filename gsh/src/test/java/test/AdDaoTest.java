package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sfmy.gsh.dao.AdDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AdDaoTest {
	@Autowired
	private AdDao adDao;
	
	@Test
	public void testSaveUser() {
		Integer[] ids = new Integer[]{1,2};
//		Map<String,List<Integer>> map = new HashMap<String,List<Integer>>();
//		map.put("ids",Arrays.asList(ids));
		adDao.updateIsUseNotInIds(false,Arrays.asList(ids));
		
	}
}
