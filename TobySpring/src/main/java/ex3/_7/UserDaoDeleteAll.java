package ex3._7;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

/**
 * 리스트 3-7 makeStatement()를 구현한 UserDao 서브클래스
 * @author eunji
 *
 */
public class UserDaoDeleteAll extends UserDao{

	protected PreparedStatement makeStatement(Connection c) throws SQLException {
		PreparedStatement ps = c.prepareStatement("delete from users");
		return ps;
	}

}
