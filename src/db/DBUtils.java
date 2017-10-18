package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import operationObject.*;

public class DBUtils {

    public static boolean checkPermission(String roleName,String permissionName){
        try{
            Connection con = DBConnection.getInstance().getConnection();
            PreparedStatement statement = con.prepareStatement(
                    "SELECT NULL FROM Role,Operation "+
                            "WHERE Rolename = ? AND OperationName = ?" +
                            "AND EXISTS(SELECT NULL FROM Permission "+
                            "WHERE Permission.RoleID = Role.RoleID AND Permission.OperaID = Operation.OperaID" +
                            ")"
            );
            statement.setString(1,roleName);
            statement.setString(2,permissionName);

            return statement.executeQuery().next();
        }catch(SQLException exception){
            throw new InternalError();
        }
    }

    public static LoginStatus login(String username, String password) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT Rolename, User.UserID  FROM User INNER JOIN Role ON User.RoleID = Role.RoleID " +
                            "WHERE Username = ? AND Password = ?"
            );
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) return new LoginStatus(false);
            else return new LoginStatus(resultSet.getString("Rolename"), resultSet.getInt("UserID"));
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new InternalError();
        }
    }

    public static Object[] getHomeworkAssigNames() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT AssignID, Title FROM homework_assigned"
            );
            ResultSet resultSet = statement.executeQuery();
            ArrayList<HomeworkAssigned> homeworks = new ArrayList<>();
            while (resultSet.next()) {
                homeworks.add(new HomeworkAssigned(
                        resultSet.getInt("AssignID"),
                        resultSet.getString("Title")
                ));
            }
            return homeworks.toArray();
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new InternalError();
        }
    }

    public static Object[] getHomeworkNames(int assignid) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT Homework.HomeworkID , Username, Content, Evaluation, Grade FROM Homework INNER JOIN User ON Homework.UserID = User.UserID WHERE AssignID = ?"
            );
            statement.setInt(1, assignid);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Homework> homeworks = new ArrayList<>();
            while (resultSet.next()) {
                homeworks.add(new Homework(
                        resultSet.getInt("HomeworkID"),
                        resultSet.getString("Username"),
                        resultSet.getString("Content"),
                        resultSet.getString("Evaluation"),
                        resultSet.getInt("Grade")
                ));
            }
            return homeworks.toArray();
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new InternalError();
        }
    }

    public static Object[] getSelfHomeworkNames(int assignid , int userid) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT Homework.HomeworkID AS ID, Username, Content, Evaluation, Grade FROM Homework INNER JOIN User ON Homework.UserID = User.UserID WHERE AssignID = ? AND Homework.UserID = ?"
            );
            statement.setInt(1, assignid);
            statement.setInt(2, userid);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Homework> homeworks = new ArrayList<>();
            while (resultSet.next()) {
                homeworks.add(new Homework(
                        resultSet.getInt("ID"),
                        resultSet.getString("Username"),
                        resultSet.getString("Content"),
                        resultSet.getString("Evaluation"),
                        resultSet.getInt("Grade")
                ));
            }
            return homeworks.toArray();
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new InternalError();
        }
    }

    public static String getDescription(int assignid) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT Description FROM homework_assigned WHERE AssignID = ?"
            );
            statement.setInt(1, assignid);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) throw new InternalError();
            return resultSet.getString("Description");
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new InternalError();
        }
    }

    public static void addHomework(int userid, String content, int assignID) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Homework (UserID, Content, AssignID) " +
                            "VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE Content = ?, Evaluation = NULL, Grade = NULL"
            );
            statement.setInt(1, userid);
            statement.setString(2, content);
            statement.setInt(3, assignID);
            statement.setString(4, content);
            statement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new InternalError();
        }
    }

    public static void evaluateHomework(int homeworkID, String evaluation, int grade) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Homework SET Evaluation = ?, Grade = ? WHERE HomeworkID = ?"
            );
            statement.setString(1, evaluation);
            statement.setInt(2, grade);
            statement.setInt(3, homeworkID);
            statement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new InternalError();
        }
    }

    public static StatGrade getStat(int assignID) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT COUNT(*) AS submit_count, " +
                            "SUM(NOT ISNULL(Grade)) AS eval_count, " +
                            "AVG(Grade) AS avg_grade FROM Homework WHERE AssignID = ?"
            );
            statement.setInt(1, assignID);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) throw new InternalError();
            return new StatGrade(
                    resultSet.getInt("submit_count"),
                    resultSet.getInt("eval_count"),
                    resultSet.getDouble("avg_grade")
            );
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new InternalError();
        }
    }


}
