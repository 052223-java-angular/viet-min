package com.revature.app.daos;

import java.io.IOError;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import com.revature.app.models.User;
import com.revature.app.utils.ConnectionFactory;

public class UserDAO implements CrudDAO<User>{

    @Override
    public void save(User Object) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "insert into users (id, username, password, role_id) values (?, ?, ?, ?)";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(0, sql);
                ps.setString(1, sql);
                ps.setString(2, sql);
                ps.setString(3, sql);
                ps.executeUpdate();
            }
        }catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db \n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties \n" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc \n" + e);
        }
    }

    @Override
    public void update(String id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "update users set username = '?', password='?' where id = '?'";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(0, sql);
                ps.setString(1, sql);
                ps.executeUpdate();
            }
        }catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db \n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties \n" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc \n" + e);
        }
    }

    @Override
    public void delete(String id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "delete from users where id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(0, sql);
                ps.executeUpdate();
            }
        }catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db \n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties \n" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc \n" + e);
        }
    }

    @Override
    public User findById(String id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from users where id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(0, sql);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        User user = new User();
                        user.setId("id");
                        user.setUsername("username");
                        user.setPassword("password");
                        user.setRoleId("role_id");
                        return user;
                    }
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db \n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties \n" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc \n" + e);
        }
        return new User();
    }

    @Override
    public List<User> findAll() {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from users";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                try(ResultSet rs = ps.executeQuery()){
                    List<User> users = new ArrayList<>();
                    while(rs.next()){
                        User user = new User();
                        user.setId("id");
                        user.setUsername("username");
                        user.setPassword("password");
                        user.setRoleId("role_id");
                        users.add(0, user);
                    }
                    return users;
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db \n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties \n" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc \n" + e);
        }
    }
    
}
