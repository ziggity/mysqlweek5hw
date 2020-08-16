package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Robot;

public class RobotDao {
	private UserDao UserDao;
	private Connection connection;
	private final String GET_ROBOTS_QUERY = "SELECT * FROM robots";
	private final String GET_ROBOT_BY_ID_QUERY = "SELECT * FROM robots WHERE id = ?";
	private final String CREATE_NEW_ROBOT_QUERY = "INSERT INTO robots(name) VALUES(?)";
	private final String DELETE_ROBOT_BY_ID_QUERY = "DELETE FROM robots WHERE id = ?";
	
	public RobotDao() {
		connection = DBConnection.getConnection();
		UserDao = new UserDao();
	}
	
	public List<Robot> getRobots() throws SQLException{
		ResultSet rs = connection.prepareStatement(GET_ROBOTS_QUERY).executeQuery();
		List<Robot> robots = new ArrayList<Robot>();
		
		while(rs.next()) {
			robots.add(populateRobot(rs.getInt(1), rs.getString(2)));
		}
		return robots;
	}
	public Robot getRobotById(int id) throws SQLException{
		PreparedStatement ps = connection.prepareStatement(GET_ROBOT_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateRobot(rs.getInt(1), rs.getString(2));
	}
	
	public void createNewRobot(String RobotName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_ROBOT_QUERY);
		ps.setString(1, RobotName);
		ps.executeUpdate();
	}
	public void deleteRobotById(int id) throws SQLException{
		UserDao.deleteUsersByRobotId(id);
		PreparedStatement ps = connection.prepareStatement(DELETE_ROBOT_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	private Robot populateRobot(int id, String name) throws SQLException {
		return new Robot(id, name, UserDao.getUsersByRobotId(id));
	}
}
