package ex3._52;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * 리스트 3-52 getALl()에 대한 테스트
 * @author eunji
 */
public class UserDaoTest {
	UserDao dao;
	User user1;
	User user2;
	User user3;
	
	@Before public void setUp(){
		this.dao = new UserDao();
		
		user3 = new User();
		user3.setId("gyumee");
		user3.setName("gyumee name");
		user3.setPassword("password1");
		
		user2 = new User();
		user2.setId("leegw700");
		user2.setName("leegw700 name");
		user2.setPassword("password2");
		
		user3 = new User();
		user3.setId("bumjin");
		user3.setName("bumjin name");
		user3.setPassword("password3");
		
	}
	
	
	@Test
	public void getAll(){
		dao.deleteAll();
		
		/**
		 * 리스트3-54 데이터가 없는 경우에 대한 검증 코드가 추가된 getAll() 테스트
		 */
		List<User> users0 = dao.getAll();
		assertThat(users0.size(), is(0));//데이터가 없을때는 크기가 0인 리스트 오브젝트가 리턴돼야한다.
		
		dao.add(user1); //Id: gyumee
		List<User> users1 = dao.getAll();
		assertThat(users1.size(), is(1));
		checkSameUser(user1, users1.get(0));
		
		dao.add(user2); //Id: leegw700
		List<User> users2 = dao.getAll();
		assertThat(users2.size(), is(2));
		checkSameUser(user1, users2.get(0));
		checkSameUser(user2, users2.get(1));
		
		dao.add(user3); //Id: bumjin
		List<User> users3 = dao.getAll();
		assertThat(users3.size(), is(3));
		checkSameUser(user3, users3.get(0));
		checkSameUser(user1, users3.get(1));
		checkSameUser(user2, users3.get(2));
	}

	/**
	 * User 오브젝트 내용을 비교하는 검증코드, 테스트에서 반복적으로 사용되므로 분리
	 * @param user1
	 * @param user2
	 */
	private void checkSameUser(User user1, User user2) {
		assertThat(user1.getId(), is(user2.getId()));
		assertThat(user1.getName(), is(user2.getName()));
		assertThat(user1.getPassword(), is(user2.getPassword()));
	}
}
