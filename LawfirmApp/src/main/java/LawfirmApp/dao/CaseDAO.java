package LawfirmApp.dao;

import LawfirmApp.database_connection;
import LawfirmApp.model.Case;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CaseDAO {

    public void createCase(Case caseObj) {
        String sql = "INSERT INTO cases(title, description, clientId) VALUES(?, ?, ?)";

        try (Connection conn = database_connection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, caseObj.getTitle());
            pstmt.setString(2, caseObj.getDescription());
            pstmt.setInt(3, caseObj.getClientId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Case getCase(int id) {
        String sql = "SELECT * FROM cases WHERE id = ?";
        Case caseObj = null;

        try (Connection conn = database_connection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                caseObj = new Case(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getInt("clientId"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return caseObj;
    }

    public List<Case> getAllCases() {
        String sql = "SELECT * FROM cases";
        List<Case> cases = new ArrayList<>();

        try (Connection conn = database_connection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                cases.add(new Case(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getInt("clientId")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cases;
    }

    public void updateCase(Case caseObj) {
        String sql = "UPDATE cases SET title = ?, description = ?, clientId = ? WHERE id = ?";

        try (Connection conn = database_connection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, caseObj.getTitle());
            pstmt.setString(2, caseObj.getDescription());
            pstmt.setInt(3, caseObj.getClientId());
            pstmt.setInt(4, caseObj.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCase(int id) {
        String sql = "DELETE FROM cases WHERE id = ?";

        try (Connection conn = database_connection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}