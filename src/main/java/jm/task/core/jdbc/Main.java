package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Наталья", "Шастина", (byte) 19);
        userService.saveUser("Вася", "Пупкин", (byte) 23);
        userService.saveUser("Иван", "Иванов", (byte) 20);
        userService.saveUser("Петр", "Петров", (byte) 35);

        List<User> users = userService.getAllUsers();
        users.forEach(System.out::println);

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}

