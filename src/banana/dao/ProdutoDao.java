package banana.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import banana.model.Produto;

public class ProdutoDao {

	private String sql;
	private ConexaoMysql conexao;

	public ProdutoDao() throws ClassNotFoundException, SQLException {
		conexao = new ConexaoMysql();
	}

	public void cadastrarProduto(Produto produto) throws SQLException {
		sql = "insert into produto values (null, ?,?,?,?)";

		try {
			PreparedStatement pStatement = this.conexao.getConnection().prepareStatement(sql);
			pStatement.setString(1, produto.getDescricao());
			pStatement.setInt(2, produto.getQuantidade());
			pStatement.setDouble(3, produto.getPreco());
			pStatement.setBoolean(4, produto.isOnLine());
			pStatement.execute();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public Produto buscarProdutoPorId(int id) throws Exception {
		sql = "SELECT * FROM PRODUTO WHERE idProduto = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();
			if(resultSet != null && resultSet.next()) {
				Produto produto = new Produto(
						resultSet.getInt("idProduto"), 
						resultSet.getString("descricao"),
						resultSet.getInt("quantidade"), 
						resultSet.getDouble("preco"), 
						resultSet.getBoolean("onLine"));
				return produto;
			}else {
				return null;
			}
		} catch (Exception se) {
			throw se;
		}
	}

	public ArrayList<Produto> consultarProdutosPorDescricao(String descricao) throws SQLException {
		sql = "SELECT * FROM PRODUTO WHERE descricao LIKE '%" + descricao + "%'";
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<Produto> produtos = new ArrayList<>();
			while (resultSet.next()) {
				Produto produto = new Produto(resultSet.getInt("idProduto"), resultSet.getString("descricao"),
						resultSet.getInt("quantidade"), resultSet.getDouble("preco"), resultSet.getBoolean("onLine"));
				produtos.add(produto);
			}
			return produtos;
		} catch (SQLException se) {
			throw se;
		}
	}

	public void excluirProduto(int idProduto) throws SQLException {
		String sql = "DELETE FROM PRODUTO WHERE idProduto = ?";
		try {
			PreparedStatement pStatement = this.conexao.getConnection().prepareStatement(sql);
			pStatement.setInt(1, idProduto);
			pStatement.execute();
			this.conexao.commit();
		} catch (SQLException se) {
			this.conexao.rollback();
			throw se;
		}
	}

	public void alterarProduto(Produto produto) throws SQLException {
		String sql = "UPDATE PRODUTO SET descricao = ?, quantidade = ?, preco = ?, onLine = ? WHERE idProduto = ?";
		try {
			PreparedStatement pStatement = this.conexao.getConnection().prepareStatement(sql);
			pStatement.setString(1, produto.getDescricao());
			pStatement.setInt(2, produto.getQuantidade());
			pStatement.setDouble(3, produto.getPreco());
			pStatement.setBoolean(4, produto.isOnLine());
			pStatement.setInt(5, produto.getIdProduto());
			pStatement.execute();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

}
