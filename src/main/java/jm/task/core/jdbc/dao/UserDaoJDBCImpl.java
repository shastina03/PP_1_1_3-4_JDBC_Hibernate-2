package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    private final Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(15)," +
                "last_name VARCHAR(25), age TINYINT, PRIMARY KEY (id))";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)){
            preparedStatement.execute(createTableSQL);
            System.out.println("Таблица создана или уже была");
        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        String dropUsersTableSQL = "DROP TABLE IF EXISTS users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(dropUsersTableSQL) ){

            preparedStatement.execute();
            System.out.println("Таблица users удалена, либо ее не было");
        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }

    }

    public void saveUser(String name, String lastName, byte age)  {

        String saveUserSQL = "INSERT INTO users (name,last_name,age) VALUES(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(saveUserSQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.printf("User c именем - %s добавлен в базу данных\n",name);
        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }

    }

    public void removeUserById(long id) {
        String removeUserByIdSQL = "DELETE FROM users WHERE ID = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(removeUserByIdSQL)) {
            preparedStatement.setLong(1,id);

            preparedStatement.executeUpdate();
            System.out.printf("User с id %d был удален из таблицы\n",id);
        } catch (SQLException e){
            System.err.println("SQLException " + e.getMessage());
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        String getAllUsersSQL = "SELECT * FROM users";
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(getAllUsersSQL);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }
        return userList;
    }

    public void cleanUsersTable() {
        String cleanUsersTable = "TRUNCATE users";
        try(PreparedStatement preparedStatement = connection.prepareStatement(cleanUsersTable)) {
            preparedStatement.executeUpdate();
            System.out.println("Таблица users очищена");
        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }
    }
}
