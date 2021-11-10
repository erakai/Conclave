package com.wclan.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Singleton class for interacting with the database.
 *
 * The database will be created at the top level of the file system in a conclave folder (ie /Conclave/conclave.db)
 * As a result it will not be seen by git so don't expect any data you put in there to stick until we release
 *
 * Also apparently gateway is the naming convention? idk https://martinfowler.com/eaaCatalog/tableDataGateway.html
 *
 * @author Kai Tinkess
 * @version Nov 10, 2021
 */
public class ConclaveGateway {
    private static ConclaveGateway instance;
    private ConclaveGateway() {}

    private static String url;

    public static ConclaveGateway instance() {
        if (instance == null) {
            instance = new ConclaveGateway();
            instance.generateURL();
        }

        return instance;
    }

    public void initializeTable() {

    }

    public void resetTable() {

    }

    public void addScheduleEntry() {

    }

    public String retrieveScheduleEntryByName(String name) {
        return null;
    }

    public String[] retrieveScheduleEntriesByGroup(String group) {
        return null;
    }

    public String[] retrieveAllEntries(String group) {
        return null;
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
