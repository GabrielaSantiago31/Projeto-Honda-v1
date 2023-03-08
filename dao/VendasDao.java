package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Moto;
import model.Venda;
import model.Vendedor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendasDao {
	public void registra (Connection connection, Venda venda) {
		String sql = "INSERT INTO vendas (quantidade, total, data_hora, pagamento, id_vendedores, id_motos) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setObject(1, venda.getQtdMotosVendidas());
			statement.setObject(2, venda.getTotVenda());
			statement.setObject(3, venda.getDataHora().toString());
			statement.setObject(4, venda.getFormaPagamento());
			statement.setObject(5, venda.getVendedor().getId());
			statement.setObject(6, venda.getMoto().getId());
			statement.execute();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void remove (Connection connection, int IdVenda) {
		String sql = "DELETE FROM vendas"
				+" WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			
			statement.setObject(1, IdVenda);
			statement.execute();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Venda> lista (Connection connection) {
		List<Venda> lista = new ArrayList <>();
		String sql = "SELECT * FROM vendas a INNER JOIN vendedores b ON a.id_vendedores = b.id "
				+ "INNER JOIN motos c ON a.id_motos = c.id";
		
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.execute();
			
			try (ResultSet resultSet = statement.getResultSet()){

				while(resultSet.next()){
					Venda venda = new Venda(null, null, 0, null, "", null);
					venda.setId(resultSet.getInt("id"));
					venda.setFormasPagamento((resultSet.getString("pagamento")));
					venda.setQtdMotosVendidas(resultSet.getInt("quantidade"));
					venda.setTotVenda(resultSet.getBigDecimal("total"));
					venda.setVendedor(
							new Vendedor(
									resultSet.getString("nome"), 
									resultSet.getString("cpf"), 
									resultSet.getString("sexo"), 
									resultSet.getBigDecimal("salario"),
									resultSet.getInt("ano_contratacao"), 
									resultSet.getString("observacoes")
							));
					venda.setMoto(
							new Moto(
									resultSet.getString("marca"), 
									resultSet.getString("modelo"), 
									resultSet.getString("cor"),
									resultSet.getInt("cilindrada"), 
									resultSet.getInt("ano"),
									resultSet.getBigDecimal("preco"),
									resultSet.getInt("quantidade"), 
									resultSet.getString("observacoes")
									));
					lista.add(venda);
				}
	
				resultSet.close();
				return lista;
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}



