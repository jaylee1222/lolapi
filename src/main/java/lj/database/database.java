/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lj.database;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lj.lolapi.Champion;

/**
 *
 * @author lj
 */
public class database {
    String url = "jdbc:postgresql://localhost:5432/discordbot";
    String user;
    String password;
    
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
    
    public long insertChampions(List<Champion> champions) {
        String SQL = "INSERT INTO champions(player_id,championname,items) "
                + "VALUES(?,?,?)";

        long id = 0;

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {
                for (int i = 0; i < champions.size(); i++) {
                pstmt.setString(1, champions.get(i).getPlayerId());
                pstmt.setString(2, champions.get(i).getName());
                pstmt.setObject(3, champions.get(i).getItems());
                          
                int affectedRows = pstmt.executeUpdate();
                // check the affected rows 
                if (affectedRows > 0) {
                    // get the ID back
                    try (ResultSet rs = pstmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            id = rs.getLong(1);
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }
}
