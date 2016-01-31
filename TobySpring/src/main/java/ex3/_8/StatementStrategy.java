package ex3._8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * 리스트 3-8 StatementStrategy 인터페이스
 * @author eunji
 *
 */
public interface StatementStrategy {
	PreparedStatement makePreparedStatement(Connection c) throws SQLException;
}
