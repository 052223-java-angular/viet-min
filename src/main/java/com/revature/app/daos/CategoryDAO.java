package com.revature.app.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.app.models.Category;
import com.revature.app.utils.ConnectionFactory;

public class CategoryDAO implements CrudDAO {
    @Override
    public void save(Object obj) {
        // Stretch goals for admin role
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void update(String id) {
        // Stretch goals for admin role
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // Stretch goal for admin role
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Optional findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Category> findAll() {
        // Displays all categories
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "SELECT * FROM category";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                try(ResultSet rs = ps.executeQuery()){
                    List<Category> categorys = new ArrayList<>();
                    while(rs.next()){
                        Category category = new Category();
                        category.setId(rs.getInt("id"));
                        category.setName(rs.getString("name"));
                        
                        categorys.add(category);
                    }
                    return categorys;
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
