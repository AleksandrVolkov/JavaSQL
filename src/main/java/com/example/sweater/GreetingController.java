package com.example.sweater;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {
    @GetMapping
    public String main(Map<String, Object> model) {
        List<Users> users = new ArrayList<Users>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:2020/postgres", "postgres", "1234");
            String sql = "SELECT * FROM USERS";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String fName = resultSet.getString("FIRST_NAME");
                String lName = resultSet.getString("LAST_NAME");
                String login = resultSet.getString("LOGIN");
                Users us = new Users(id, fName, lName, login);
                users.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.put("UsersList", users);
        return "main";
    }
}
