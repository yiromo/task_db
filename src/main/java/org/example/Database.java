package org.example;
import java.sql.*;

public class Database {


    static String Database_url = "jdbc:postgresql://localhost:5432/taskdb";
    static String Login = "postgres";
    static String Pass = "13012005aA";
    public static int wordCountDb(String text) {
        return text.split("\\s+").length;
    }
    public static void main(String[] args){
        try(Connection connection = DriverManager.getConnection(Database_url, Login, Pass);
            Statement statement = connection.createStatement()){
            String sql = "select dbtext from task1_table where length(dbtext) - length(replace(dbtext, ' ', '')) + 1 >= 5;" ;
            try (ResultSet resultSet = statement.executeQuery(sql)){
                while (resultSet.next()){
                    String full_text = resultSet.getString("dbtext");
                    int word_count = wordCountDb(full_text);
                    System.out.println("Text: " + full_text);
                    System.out.println("Word Count: " + word_count);
                }
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

}
