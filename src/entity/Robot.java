package entity;

import java.util.List;

public class Robot {
	private int robotId;
	private String name;
	private List<User> users;
	
	public Robot(int robotId, String name, List<User> users) {
		this.setRobotId(robotId);
		this.setName(name);
		this.setUsers(users);
	}

	public int getRobotId() {
		return robotId;
	}

	public void setRobotId(int robotId) {
		this.robotId = robotId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
