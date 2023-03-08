package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Moto;
import model.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MotosDao {
	public void adiciona (Connection connection, Moto moto) {
		String sql = "INSERT INTO motos (marca, modelo, cor, cilindrada, ano, preco, quantidade, observacoes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(sql)){

			statement.setObject(1, moto.getMarca());
			statement.setObject(2, moto.getModelo());
			statement.setObject(3, moto.getCor());
			statement.setObject(4, moto.getCilindrada());
			statement.setObject(5, moto.getAno());
			statement.setObject(6, moto.getPreco());
			statement.setObject(7, moto.getQuantidade());
			statement.setObject(8, moto.getObservacoes());
			statement.execute();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void remove (Connection connection, int IdMoto) {
		String sql = "DELETE FROM motos"
				+" WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			
			statement.setObject(1, IdMoto);
			statement.execute();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera (Connection connection, Moto moto) {
		String sql = "UPDATE motos SET marca = ?, modelo = ?, cor = ?, cilindrada = ?, ano = ?, preco = ?, quantidade = ?, observacoes = ?"
				+" WHERE id = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)){
		
			statement.setObject(1, moto.getMarca());
			statement.setObject(2, moto.getModelo());
			statement.setObject(3, moto.getCor());
			statement.setObject(4, moto.getCilindrada());
			statement.setObject(5, moto.getAno());
			statement.setObject(6, moto.getPreco());
			statement.setObject(7, moto.getQuantidade());
			statement.setObject(8, moto.getObservacoes());
			statement.setObject(9, moto.getId());
			statement.execute();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Moto> lista (Connection connection) {
		List<Moto> lista = new ArrayList <>();
		String sql = "SELECT * FROM motos";
		
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.execute();
			
			try (ResultSet resultSet = statement.getResultSet()){

				while(resultSet.next()){
					Moto moto = new Moto("", "", "", 0, 0, null, 0, "");
					moto.setId(resultSet.getInt("id"));
					moto.setMarca(resultSet.getString("marca"));
					moto.setModelo(resultSet.getString("modelo"));
					moto.setCor(resultSet.getString("cor"));
					moto.setCilindrada(resultSet.getInt("cilindrada"));
					moto.setAno(resultSet.getInt("ano"));
					moto.setPreco(resultSet.getBigDecimal("preco"));
					moto.setQuantidade(resultSet.getInt("quantidade"));
					moto.setObservacoes(resultSet.getString("observacoes"));
					lista.add(moto);
				}
	
				resultSet.close();
				return lista;
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}



