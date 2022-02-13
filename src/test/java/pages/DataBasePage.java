package pages;

import org.testng.Assert;

import java.sql.*;

import java.sql.DriverManager;

public class DataBasePage {
    Connection co;

    public void open() {
        try {
            Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection(
                    "jdbc:sqlite:test.db");
            System.out.println("DB connected successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e + "");
        }

    }

    public void close() {
        try
        {
            co.close();
        }
        catch (SQLException e)
        {
            System.out.println(e + "");
        }
    }

    public void assert_density() {
        try {
            Statement statement = co.createStatement();
            String query = "SELECT Country, Population, Area " + "FROM Countries ";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String country = rs.getString("country");
                int population = rs.getInt("population");
                int area = rs.getInt("area");

                if (population/area < 50) {
                    Assert.assertEquals(country, "USA");
                    System.out.println("The country with population density less then 50 p/sq.km is " + country);
                }
            }
        } catch (SQLException e) {
            System.out.println(e + "");
        }
    }

    public void assert_population() {
        try {
            Statement statement = co.createStatement();
            String query = "SELECT Population " + "FROM Countries ";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int population = rs.getInt("population");

                Assert.assertTrue(population < 2000000000, String.valueOf(true));
            }
        } catch (SQLException e) {
            System.out.println(e + "");
        }
    }
}
