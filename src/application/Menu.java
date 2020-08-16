package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.UserDao;
import dao.RobotDao;
import entity.User;
import entity.Robot;

public class Menu {
	
	private RobotDao robotDao = new RobotDao();
	private Scanner scanner = new Scanner(System.in);
	private UserDao userDao = new UserDao();
	private List<String> options = Arrays.asList(
			"Display Robots", "Display a robot", "create robot", "delete robot", "create robot user", "delete robot user");

	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			try {

				if(selection.contentEquals("1")) {
					displayRobots();
				}else if (selection.contentEquals("2")) {
					displayRobot();
				}
				else if (selection.contentEquals("3")) {
					createRobot();
				}
				else if (selection.contentEquals("4")) {
					deleteRobot();
				}
				else if (selection.contentEquals("5")) {
					createUser();
				}
				else if (selection.contentEquals("6")) {
					deleteUser();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("press enter to continue...");
			scanner.nextLine();
			
		} while (!selection.contentEquals("-1"));
	}

	private void createUser() throws SQLException {
		System.out.println("enter first name of new user: ");
		String firstName = scanner.nextLine();
		System.out.println("enter last name");
		String lastName = scanner.nextLine();
		System.out.println("Enter robot id of new user");
		int robotId = Integer.parseInt(scanner.nextLine());
		userDao.createNewUser(firstName, lastName, robotId);
		
	}

	private void deleteRobot() throws SQLException {
		System.out.println("enter robot id to delete");
		int id = Integer.parseInt(scanner.nextLine());
		robotDao.deleteRobotById(id);
	}

	private void createRobot()throws SQLException {
		System.out.println("enter new robot: ");
		String robotName = scanner.nextLine();
		robotDao.createNewRobot(robotName);
	}

	private void displayRobot() throws SQLException {
		System.out.println("Enter Robot ID: ");
		int id = Integer.parseInt(scanner.nextLine());
		Robot robot = robotDao.getRobotById(id);
		System.out.println(robot.getRobotId() + ": " + robot.getName());
		for( User user : robot.getUsers()) {
			System.out.println("\tUserId: " + user.getUserId() + " - Name: " + user.getFirstName() + " " + user.getLastName());
		}
	}

	private void displayRobots() throws SQLException {
		List<Robot> robots = robotDao.getRobots();
		for(Robot robot : robots) {
			System.out.println(robot.getRobotId() + ": " + robot.getName());
		}
	}

	private void deleteUser() throws SQLException {
		System.out.println("enter user id to delete");
		int id = Integer.parseInt(scanner.nextLine());
		userDao.deleteUserById(id);
		
	}
	private void printMenu() {
		System.out.println("Select an Option:\n...........................");
		for(int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}
}
