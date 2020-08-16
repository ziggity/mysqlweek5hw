package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;

public class UserDao {
	
	private Connection connection;
	private final String GET_USERS_BY_ROBOT_ID_QUERY = "SELECT * FROM users WHERE robot_id = ?";
	private final String DELETE_USERS_BY_ROBOT_ID_QUERY = "DELETE FROM users WHERE robot_id = ?";
	private final String CREATE_NEW_USER_QUERY = "INSERT INTO users(first_name, last_name, robot_id) VALUES(?, ?, ?)";
	private final String DELETE_USER_BY_ID_QUERY = "DELETE FROM users WHERE id = ?";
	
	
	public UserDao() {
		connection = DBConnection.getConnection();
	}
	
	public List<User> getUsersByRobotId(int robotId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_USERS_BY_ROBOT_ID_QUERY);
		ps.setInt(1, robotId);
		ResultSet rs = ps.executeQuery();
		List<User> Users = new ArrayList<User>();
		while(rs.next()) {
			Users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3)));
		}
		return Users;
	}
	
	public void createNewUser(String firstName, String lastName, int robotId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_USER_QUERY);
		ps.setString(1, firstName);
		ps.setString(2, lastName);
		ps.setInt(3, robotId);
		ps.executeUpdate();
		
	}
	public void deleteUsersByRobotId(int robotId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_USERS_BY_ROBOT_ID_QUERY);
		ps.setInt(1, robotId);
		ps.executeUpdate();
	}
	
	
	public void deleteUserById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_USER_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

}


