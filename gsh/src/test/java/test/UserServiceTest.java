package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sfmy.gsh.entity.User;
import com.sfmy.gsh.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserServiceTest {
	@Resource(name="userService")
	private UserService userService;
	
	@Test
	public void testSaveUser() {
		User u = new User();
		u.setName("admin");
		u.setPassword("sfmy66admin88");
		userService.saveUser(u);
	}
	
	@Test
	public void testDeleteUser() {
		User u = new User();
		u.setId(5);
		userService.deleteUser(u);
	}
	
	@Test
	public void testUpdateUser() {
		User u = new User();
		u.setId(5);
		u.setName("王五");
		userService.updateUser(u);
	}
}
