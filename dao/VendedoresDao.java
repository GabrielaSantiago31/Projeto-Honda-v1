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

import model.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendedoresDao {
	public void adiciona (Connection connection, Vendedor vendedor) {
		String sql = "INSERT INTO vendedores (nome, cpf, sexo, salario, ano_contratacao, observacoes) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(sql)){

			statement.setObject(1, vendedor.getNome());
			statement.setObject(2, vendedor.getCpf());
			statement.setObject(3, vendedor.getSexo());
			statement.setObject(4, vendedor.getSalario());
			statement.setObject(5, vendedor.getAnoContratacao());
			statement.setObject(6, vendedor.getObservacoes());
			statement.execute();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void remove (Connection connection, int IdVendedor) {
		String sql = "DELETE FROM vendedores"
				+" WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			
			statement.setObject(1, IdVendedor);
			statement.execute();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera (Connection connection, Vendedor vendedor) {
		String sql = "UPDATE vendedores SET nome = ?, cpf = ?, sexo = ?, salario = ?, ano_contratacao = ?, observacoes = ?"
				+" WHERE id = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)){
		
			statement.setObject(1, vendedor.getNome());
			statement.setObject(2, vendedor.getCpf());
			statement.setObject(3, vendedor.getSexo());
			statement.setObject(4, vendedor.getSalario());
			statement.setObject(5, vendedor.getAnoContratacao());
			statement.setObject(6, vendedor.getObservacoes());
			statement.setObject(7, vendedor.getId());
			statement.execute();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Vendedor> lista (Connection connection) {
		List<Vendedor> lista = new ArrayList <>();
		String sql = "SELECT * FROM vendedores";
		
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.execute();
			
			try (ResultSet resultSet = statement.getResultSet()){

				while(resultSet.next()){
					Vendedor vendedor = new Vendedor("", "", "", null, 0, "");
					vendedor.setId(resultSet.getInt("id"));
					vendedor.setNome(resultSet.getString("nome"));
					vendedor.setCpf(resultSet.getString("cpf"));
					vendedor.setSexo(resultSet.getString("sexo"));
					vendedor.setSalario(resultSet.getBigDecimal("salario"));
					vendedor.setAnoContratacao(resultSet.getInt("ano_contratacao"));
					vendedor.setObservacoes(resultSet.getString("observacoes"));
					lista.add(vendedor);
				}
	
				resultSet.close();
				return lista;
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
	




