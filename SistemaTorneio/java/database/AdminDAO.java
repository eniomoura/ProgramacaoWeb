package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
	public static final String URL="jdbc:derby:db;create=true";
	
	public static void criaTabela() throws SQLException{
		Connection conn = DriverManager.getConnection(URL);
		String sql = "create table admin(usuario varchar(255), senha varchar(255))";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
	public static void deletaTabela() throws SQLException{
		Connection conn = DriverManager.getConnection(URL);
		String sql = "drop table admin";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
	public static void inclui(String usuario, String senha) throws SQLException{
		Connection conn = DriverManager.getConnection(URL);
		String sql = "insert into admin(usuario, senha) values (?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, usuario);
		pstmt.setString(2, senha);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
	public static void excluir(String usuario) throws SQLException{
		Connection conn = DriverManager.getConnection(URL);
		String sql = "delete from admin where usuario=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, usuario);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	public static boolean consultar(String usuario, String senha) throws SQLException{
		Connection conn = DriverManager.getConnection(URL);
		String sql = "select * from admin where ?=usuario and ?=senha";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, usuario);
		pstmt.setString(2, senha);
		ResultSet rs = pstmt.executeQuery();
		boolean exists=rs.next();
		pstmt.close();
		conn.close();
		return exists;
	}
	
	public static boolean consultar(String usuario) throws SQLException{
		Connection conn = DriverManager.getConnection(URL);
		String sql = "select * from admin where ?=usuario";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, usuario);
		ResultSet rs = pstmt.executeQuery();
		boolean exists=rs.next();
		pstmt.close();
		conn.close();
		return exists;
	}
}