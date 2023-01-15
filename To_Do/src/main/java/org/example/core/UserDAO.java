package org.example.core;


public interface UserDAO {

    boolean checkIfUserExist(String username);
    boolean checkIfUserExist(String username, String password);
    boolean insertUser(String username, String password);
    boolean updateUser(String username, String newPassword);
    boolean deleteUser(String username);
}
