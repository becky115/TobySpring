package ex5._57;

import static ex5._31.UserService.MIN_LOGCOUNT_FOR_SILVER;
import static ex5._31.UserService.MIN_RECCOMEND_FOR_GOLD;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

import ex5._50.Level;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/ex5/_57/applicationContext.xml")
public class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	@Autowired
	MailSender mailSender;
	
	
	List<User> users; //테스트픽스처 
	
	@Before
	public void setUp(){
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
	@DirtiesContext //컨텍스트의 DI설정을 변경하는 테스트라는 것을 알려준다.
	public void upgradeLevels() throws Exception{
		userDao.deleteAll();
		
		for(User user: users) userDao.add(user);
		
		MockMailSender mockMailSender = new MockMailSender(); //메일 발송 결과를 테스트할 수 있도록 목 오브젝트를 만들어 userService의 의존오브젝트로 주입해준다.
		userService.setMailSender(mockMailSender);
		
		userService.upgradeLevels();
		
		checkLevelUpgraded(users.get(0), false); //업그레이드 테스트 메일 발송이 일어나
		checkLevelUpgraded(users.get(1), true);  //MockMailSender 오브젝트의 리스트에 그 결과가 저장된다.
		checkLevelUpgraded(users.get(2), false);
		checkLevelUpgraded(users.get(3), true);
		checkLevelUpgraded(users.get(4), false);
		
		List<String> request = mockMailSender.getRequests();
		assertThat(request.size(), is(2));
		assertThat(request.get(0), is(users.get(1).getEmail())); //목 오브젝트에 저장된 메일 수신자 목록을 가져와 업그레이드 대상과 일치하는지 확인한다.
		assertThat(request.get(1), is(users.get(3).getEmail()));
		
	}

	/**
	 * 
	 * @param user
	 * @param upgraded 어떤레벨로 바뀔 것인가가 아니라, 다음레벨로 업그레이드 될 것인가 아닌가를 지정한다.
	 */
	private void checkLevelUpgraded(User user, boolean upgraded){
		User userUpdate = userDao.get(user.getId());
		if(upgraded){
			assertThat(userUpdate.getLevel(), is(user.getLevel().nextLevel()));
		}else{
			assertThat(userUpdate.getLevel(), is(user.getLevel()));
		}
	}
	
	@Test
	public void add(){
		userDao.deleteAll();
		
		//GOLD레벨이 이미 지정된 User라면 레벨을 초기화하지 않아야 한다.
		User userWithLevel = users.get(4); //GOLD레벨 
		User userWithoutLevel = users.get(0);
		userWithoutLevel.setLevel(null);
		
		userService.add(userWithLevel);
		userService.add(userWithoutLevel);
		
		//DB에 저장된 결과를 가져와 확인한다.
		User userWithLevelRead = userDao.get(userWithLevel.getId());
		User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());
		
		assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel()));
		assertThat(userWithoutLevelRead.getLevel(), is(userWithoutLevelRead.getLevel()));
		
	}
	
	@Test
	public void upgradeAllOrNothing() throws Exception{
		//예외를 발생시킬 네번째 사용자의 id를 넣어서 테스트용 UserService 대역 오브젝트를 생성한다.
		UserService testUserService = new TestUserService(users.get(3).getId());
		System.out.println(mailSender.toString());
		testUserService.setMailSender(mailSender);
		
		//userService 빈의 프로퍼티 설정과 동일한 수동DI 
		testUserService.setUserDao(userDao);
		testUserService.setTransactionManager(transactionManager);
		
		userDao.deleteAll();
		for(User user: users) userDao.add(user);
		
		try{
			//TestUserService는 업그레이드 작업 중에 예외가 발생해야한다.
			//정상 종료라면 문제가 있으니 실패
			testUserService.upgradeLevels();
			fail("TestUserServiceException expected");
		}catch(TestUserServiceException e){
			//TestUserService가 던져주는 예외를 잡아서 계속 진행되도록 한다. 그 외의 예외라면 테스트 실패
		}
		
		checkLevelUpgraded(users.get(1), false);
	}
	
	static class TestUserService extends UserService{
		private String id;
		
		/**
		 * 
		 * @param id 예외를 발생시킬 User 오브젝트의 id를 지정할 수 있게 만든다.
		 */
		private TestUserService(String id){
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
	
	static class MockMailSender implements MailSender{
			
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


}
