package ex6._43;

import static ex5._31.UserService.MIN_LOGCOUNT_FOR_SILVER;
import static ex5._31.UserService.MIN_RECCOMEND_FOR_GOLD;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ex5._50.Level;
import ex6._1.User;
import ex6._12.UserDao;
import ex6._12.UserService;
import ex6._12.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/ex6/_43/applicationContext.xml")
public class UserServiceTest {
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	MailSender mailSender;
	
	@Autowired
	ApplicationContext context;

	List<User> users; //테스트픽스처 
	
	@Before
	public void setUp() {
		//테스트에서는 가능한 한 경계 값을 사용하는것이 좋다.
		users = Arrays.asList(
				new User("bumjin", "박범진", "p1", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER -1, 0, "ejlee@narusec.com"),
				new User("joytouch", "강명성", "p2", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0, "ejlee@narusec.com"),
				new User("erwins", "신승한", "p3", Level.SILVER, 60, MIN_RECCOMEND_FOR_GOLD -1, "ejlee@narusec.com"),
				new User("madnite1", "이상호", "p4", Level.SILVER, 60, MIN_RECCOMEND_FOR_GOLD, "ejlee@narusec.com"),
				new User("green", "오민규", "p5", Level.GOLD, 100, Integer.MAX_VALUE, "ejlee@narusec.com")
		);
	}
	
	@Test
	public void mockUpgradeLevels() throws Exception {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		
		UserDao mockUserDao = mock(UserDao.class);
		when(mockUserDao.getAll()).thenReturn(this.users);
		userServiceImpl.setUserDao(mockUserDao);
		
		MailSender mockMailSender = mock(MailSender.class);
		userServiceImpl.setMailSender(mockMailSender);
		
		userServiceImpl.upgradeLevels();
		
		
		// 목 오브젝트가 제공하는 검증 기능을 통해서 어떤 메소드가 몇번 호출됫는지, 파라미터 무엇인지 확인할 수 있음
		verify(mockUserDao, times(2)).update(any(User.class));
		verify(mockUserDao, times(2)).update(any(User.class));
		verify(mockUserDao).update(users.get(1)); // users.get(1) 을 파라미터로 update()가 호출됬는
		assertThat(users.get(1).getLevel(), is(Level.SILVER));
		verify(mockUserDao).update(users.get(3));
		assertThat(users.get(3).getLevel(), is(Level.GOLD));
		
		ArgumentCaptor<SimpleMailMessage> mailMessageArg = ArgumentCaptor.forClass(SimpleMailMessage.class);
		
		verify(mockMailSender, times(2)).send(mailMessageArg.capture());//  파라미터를 정밀하게 검사하기 위해 캡쳐할 수도 있음
		List<SimpleMailMessage> mailMessages = mailMessageArg.getAllValues();
		System.out.println(mailMessages);
		assertThat(mailMessages.get(0).getTo()[0], is(users.get(1).getEmail()));
		assertThat(mailMessages.get(1).getTo()[0], is(users.get(3).getEmail()));
	
	}
	

	/**
	 * 
	 * @param user
	 * @param upgraded 어떤레벨로 바뀔 것인가가 아니라, 다음레벨로 업그레이드 될 것인가 아닌가를 지정한다.
	 */
	private void checkLevelUpgraded(User user, boolean upgraded) {
		User userUpdate = userDao.get(user.getId());
		if(upgraded) {
			assertThat(userUpdate.getLevel(), is(user.getLevel().nextLevel()));
		} else {
			assertThat(userUpdate.getLevel(), is(user.getLevel()));
		}
	}
	
	@Test
	public void add() {
		userDao.deleteAll();
		
		//GOLD레벨이 이미 지정된 User라면 레벨을 초기화하지 않아야 한다.
		User userWithLevel = users.get(4); //GOLD레벨 
		User userWithoutLevel = users.get(0);
		userWithoutLevel.setLevel(null);
		
		userServiceImpl.add(userWithLevel);
		userServiceImpl.add(userWithoutLevel);
		
		//DB에 저장된 결과를 가져와 확인한다.
		User userWithLevelRead = userDao.get(userWithLevel.getId());
		User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());
		
		assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel()));
		assertThat(userWithoutLevelRead.getLevel(), is(userWithoutLevelRead.getLevel()));
		
	}
	
	@Test
	@DirtiesContext
	public void upgradeAllOrNothing() throws Exception {
		//예외를 발생시킬 네번째 사용자의 id를 넣어서 테스트용 UserService 대역 오브젝트를 생성한다.
		TestUserService testUserService = new TestUserService(users.get(3).getId());
		System.out.println(mailSender.toString());
		testUserService.setUserDao(userDao);
		testUserService.setMailSender(mailSender);
		
		ProxyFactoryBean txProxyFactoryBean = context.getBean("&userService", ProxyFactoryBean.class);
		txProxyFactoryBean.setTarget(testUserService);
		UserService txUserService = (UserService) txProxyFactoryBean.getObject();

		userDao.deleteAll();
		for(User user: users) userDao.add(user);
		
		try {
			//TestUserService는 업그레이드 작업 중에 예외가 발생해야한다.
			//정상 종료라면 문제가 있으니 실패
			txUserService.upgradeLevels();
			fail("TestUserServiceException expected");
		} catch (TestUserServiceException e) {
			// e.printStackTrace();
			//TestUserService가 던져주는 예외를 잡아서 계속 진행되도록 한다. 그 외의 예외라면 테스트 실패
		}
		
		checkLevelUpgraded(users.get(1), false);
	}
	
	static class TestUserService extends UserServiceImpl {
		private String id;
		
		/**
		 * 
		 * @param id 예외를 발생시킬 User 오브젝트의 id를 지정할 수 있게 만든다.
		 */
		private TestUserService(String id) {
			this.id = id;
		}
		
		
		protected void upgradeLevel(User user) { //UserService의 메소드를 오버라이드 한다.
			//System.out.println("TestUserService upgradeLevel......");
			if(user.getId().equals(this.id)) throw new TestUserServiceException();
			super.upgradeLevel(user);
		}
	}
	
	static class TestUserServiceException extends RuntimeException {

	}
	
	static class MockMailSender implements MailSender {
			
		//UserService로부터 전송요청을 받은 메일 주소를 저장해 두고 이를 읽을 수 있게 한다.
		private List<String> requests = new ArrayList<String>();

		public List<String> getRequests() {
			return requests;
		}

		@Override
		public void send(SimpleMailMessage mailMessage) throws MailException {
			System.out.println("mailMessage.getTo()[0]: " + mailMessage.getTo()[0]);
			requests.add(mailMessage.getTo()[0]); //전송 요청을 받은 이메일 주소를 저장해둔다. 간단하게 첫 번째 수신자 메일 주소만 저장했다 .
		}

		@Override
		public void send(SimpleMailMessage... simpleMessages) throws MailException {
		
		}

	}
	
	static class MockUserDao implements UserDao {
		
		public List<User> users; // 레벨 업데이트 후보 User 오브젝트 목록
		public List<User> updated = new ArrayList<User>(); // 업그레이드 대상 오브젝트를 저장해둘 목록
		
		private MockUserDao(List<User> users) {
			this.users = users;
		}
		
		public List<User> getUpdated() {
			return this.updated;
		}

		// update메소드를 실행하면서 넘겨준 업그레이드 대상 User오브젝트를 저장해뒀다가
		// 검증을 위해 돌려주기 위한것 
		// upgradeLevels() 메소드가 실행되는 동안 업그레이드 대상으로 선정된 사용자가 어떤것인지 확인하는데 쓰임 
		public void update(User user) { // 목 오브젝트 기능 제공
			updated.add(user);
		}

		// 메소드가 호출되면 DB에서 가져온것처럼 돌려주는 용도
		public List<User> getAll() { // 스텁 기능 제공
			return this.users;
		}
		
		// 테스트에 사용되지 않는 메소드
		public void add(User user) { throw new UnsupportedOperationException(); }
		public void deleteAll() { throw new UnsupportedOperationException(); }
		public User get(String id) { throw new UnsupportedOperationException(); }
		public int getCount() { throw new UnsupportedOperationException(); }
	}
	
	
	
}
