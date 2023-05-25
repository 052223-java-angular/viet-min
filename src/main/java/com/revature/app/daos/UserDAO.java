package com.revature.app.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.app.models.User;
import com.revature.app.utils.ConnectionFactory;

public class UserDAO implements CrudDAO<User>{

    @Override
    public void save(User Object) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "insert into users (id, username, password, role_id) values (?, ?, ?, ?)";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, Object.getId());
                ps.setString(2, Object.getUsername());
                ps.setString(3, Object.getPassword());
                ps.setString(4, Object.getRoleId());
                ps.executeUpdate();
            }

        }catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db \n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties \n" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc \n" + e);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "update users set password='?' where id = '?'";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, sql);
                ps.setString(2, sql);
                ps.executeUpdate();
            }
        }catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db \n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties \n" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc \n" + e);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "delete from users where id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, id);
                ps.executeUpdate();
            }
        }catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db \n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties \n" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc \n" + e);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(String id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from users where id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, id);
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
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return new User();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from users";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        User user = new User();
                        user.setId("id");
                        user.setUsername("username");
                        user.setPassword("password");
                        user.setRoleId("role_id");
                        users.add(0, user);
                    }
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db \n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties \n" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc \n" + e);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return users;
    }

    public Optional<User> findByUsername(String username) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from users where username = ?";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, username);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        User user = new User();
                        user.setId("id");
                        user.setUsername("username");
                        user.setPassword("password");
                        user.setRoleId("role_id");
                        return Optional.of(user);
                    }
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db \n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties \n" + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load jdbc \n" + e);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return  Optional.empty();
    }
    
}
