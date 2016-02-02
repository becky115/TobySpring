package ex3._13;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ex3._8.StatementStrategy;

/**
 * 리스트 3-13 add()메소드의 PreparedStatement 생성로직을 분리한 클래스
 * @author eunji
 *
 */
public class AddStatement implements StatementStrategy{

	public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
		
		/*
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword()); //그런데 user는 어디서 가져올까?
		*/
		return ps;
	}

}
