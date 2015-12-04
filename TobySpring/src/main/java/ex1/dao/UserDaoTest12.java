package ex1.dao;

import java.sql.SQLException;

import ex1.domain.User;

/**
 * 관계설정책임이 추가된 {@link UserDao11} 클라이언트인 main 메소드
 * @author ejlee
 *
 */
public class UserDaoTest12 {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ConnectionMaker8 connectionMaker8 = new DConnctionMaker9();
		UserDao11 userDao = new UserDao11(connectionMaker8);
		
		User user = userDao.get("id");
		System.out.println(user.toString());
	}
}
