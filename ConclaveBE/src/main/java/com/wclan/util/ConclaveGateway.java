package com.wclan.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * DEPRECATED. DO NOT USE.
 *
 * Singleton class for interacting with the database.
 *
 * The database will be created at the top level of the file system in a conclave folder (ie /Conclave/conclave.db)
 * As a result it will not be seen by git so don't expect any data you put in there to stick until we release
 *
 * Also apparently gateway is the naming convention? idk https://martinfowler.com/eaaCatalog/tableDataGateway.html
 *
 * You are also able to open this database with intellij to poke around if you want
 *
 * TODO: Sanitize everything so u can't name yourself something too based
 *
 * @author Kai Tinkess
 * @version Nov 10, 2021
 */
public class ConclaveGateway {
    private static ConclaveGateway instance;
    private ConclaveGateway() {}

    private static String url;

    /**
     * @deprecated
     * dont use this!!!!!!!!!!!!!!
     */
    public static ConclaveGateway instance() {
        if (instance == null) {
            instance = new ConclaveGateway();
            instance.generateURL();
        }

        return instance;
    }

    public void initializeTable() {
        execute("CREATE TABLE Conclave (" +
                    "Name string," +
                    "Group string," +
                    "Schedule string" +
                ");");
    }

    public void resetTable() {
        execute("DROP TABLE Conclave"); //this is the most based command
    }

    public void addScheduleEntry(String name, String group, String schedule) {
        execute(String.format("INSERT INTO Conclave (Name, Group, Schedule)"
                + "VALUES ('%s', '%s', '%s')", name, group, schedule));
    }

    public String retrieveScheduleEntryByName(String name) throws SQLException {
        String sql = "SELECT Group, Schedule FROM Conclave WHERE Name = ?";

        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt  = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();

        StringBuilder results = new StringBuilder();
        while (rs.next()) {
            results.append(rs.getString("Group")).append("\t");
            results.append(rs.getString("Schedule")).append("\n");
        }
        return results.toString().trim();
    }

    public Map<String, String> retrieveScheduleEntriesByGroup(String group) throws SQLException{
        String sql = "SELECT Name, Schedule FROM Conclave WHERE Group = ?";

        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt  = conn.prepareStatement(sql);
        pstmt.setString(1, group);
        ResultSet rs = pstmt.executeQuery();

        Map<String, String> results = new HashMap<>();
        while (rs.next()) results.put(rs.getString("Name"), rs.getString("Schedule"));
        return results;
    }

    public String retrieveAllEntries() throws SQLException {
        String sql = "SELECT * FROM Conclave";

        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt  = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        StringBuilder results = new StringBuilder();
        while (rs.next()) {
            results.append(rs.getString("Name")).append("\t");
            results.append(rs.getString("Group")).append("\t");
            results.append(rs.getString("Schedule")).append("\n");
        }
        return results.toString().trim();
    }

    protected void generateURL() {
        try {
            Files.createDirectories(Paths.get("conclave"));
        } catch (IOException e) {e.printStackTrace();}

        url = "jdbc:sqlite://conclave/conclave.db";
    }

    private void execute(String sql) {
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement statement = conn.createStatement();
            statement.execute(sql);
        } catch (SQLException ex) { ex.printStackTrace(); }
    }


}
