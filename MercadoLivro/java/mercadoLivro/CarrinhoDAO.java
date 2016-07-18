package mercadoLivro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoDAO {
	
	private static final String URL = "jdbc:derby:db;create=true";
	
	public static void criaTabela() throws SQLException{
		Connection conn = DriverManager.getConnection(URL);
		String sql = "create table carrinho(nome varchar(255), imagePath varchar(255), tipo varchar(255), preco double, estoque int, id int GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1))";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
	
	public static void deletaTabela() throws SQLException{
		Connection conn = DriverManager.getConnection(URL);
		String sql = "drop table carrinho";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	public static void inclui(String nome, String imagePath, String tipo, double preco, int estoque) throws SQLException {
		Connection conn = DriverManager.getConnection(URL);
		String sql = "insert into carrinho (nome, imagePath, tipo, preco, estoque) values (?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, nome);
		pstmt.setString(2, imagePath);
		pstmt.setString(3, tipo);
		pstmt.setDouble(4, preco);
		pstmt.setInt(5, estoque);		
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	public static void alterar(String nome, String imagePath, String tipo, double preco, int estoque, int id) throws SQLException {
		Connection conn = DriverManager.getConnection(URL);
		String sql = "update carrinho set nome=?, imagePath=?, tipo=?, preco=?, estoque=? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, nome);
		pstmt.setString(2, imagePath);
		pstmt.setString(3, tipo);
		pstmt.setDouble(4, preco);
		pstmt.setInt(5, estoque);
		pstmt.setInt(6, id);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	public static void excluir(int id) throws SQLException {
		Connection conn = DriverManager.getConnection(URL);
		String sql = "delete from carrinho where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}

	public static List<ItemCarrinho> listar() throws SQLException {
		Connection conn = DriverManager.getConnection(URL);
		String sql = "select id, nome, imagePath, tipo, preco, estoque from carrinho";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<ItemCarrinho> items = new ArrayList<>();
		while (rs.next()) {
			ItemCarrinho item = new ItemCarrinho(
									rs.getString("nome"),
									rs.getString("imagePath"),
									rs.getString("tipo"),
									rs.getDouble("preco"),
									rs.getInt("estoque"),
									rs.getInt("id")
								);
			items.add(item);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return items;
	}
	
	public static ItemCarrinho getItemByID(int id) throws SQLException {
		Connection conn = DriverManager.getConnection(URL);
		String sql = "select * from carrinho where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		ItemCarrinho item=null;
		if(rs.next()){
			item = new ItemCarrinho(
					rs.getString("nome"),
					rs.getString("imagePath"),
					rs.getString("tipo"),
					rs.getDouble("preco"),
					rs.getInt("estoque"),
					rs.getInt("id")
				);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return item;
	}
	
	public static ItemCarrinho getItemByNome(String nome) throws SQLException {
		Connection conn = DriverManager.getConnection(URL);
		String sql = "select * from carrinho where nome=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, nome);
		ResultSet rs = pstmt.executeQuery();
		ItemCarrinho item=null;
		if(rs.next()){
			item = new ItemCarrinho(
					rs.getString("nome"),
					rs.getString("imagePath"),
					rs.getString("tipo"),
					rs.getDouble("preco"),
					rs.getInt("estoque"),
					rs.getInt("id")
				);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return item;
	}
}
