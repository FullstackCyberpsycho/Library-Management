package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private final String url = "jdbc:postgresql://localhost:5432/library_management";
    private final String user = "postgres";
    private final String password = "1512BDS7425";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void addBook(String title, String author, int year) {
        String sql = "INSERT INTO books (title, author, year) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setInt(3, year);
            pstmt.executeUpdate();
            //System.out.println("Книга добавлена");
            System.out.println("The book is added");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllBooks() {
        int count = 0;
        String sql = "SELECT * FROM books";
        List<String> books = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                int year = rs.getInt("year");
                /*books.add(String.valueOf(++count) + ":\n" + title + "\n"
                        + author + "\n"
                        + year);*/
                books.add(String.valueOf(++count) + ": " + title + ", " + author + ", " + year);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public List<String> getTitleBooks() {
        int count = 0;
        String sql = "SELECT id, title FROM books";
        List<String> titles = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String title = rs.getString("title");
                int id = rs.getInt("id");
                titles.add(id + ": " + title);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return titles;
    }

    public void updateBook(int id, String newTitle) {
        String sql = "UPDATE books SET title = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newTitle);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            //System.out.println("Название книги изменено");
            System.out.println("The name of the book is changed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            //System.out.println("Книга удалена");
            System.out.println("The book is deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAllBooks() {
        //System.out.println("Все книги:");
        System.out.println("All books: ");
        List<String> books = getAllBooks();
        for (String book : books) {
            System.out.println(book);
        }
    }

    public void printIDAndTitleBooks() {
        //System.out.println("ID и название книг:");
        System.out.println("ID and book name:");
        List<String> titles = getTitleBooks();
        for (String title_ : titles) {
            System.out.println(title_);
        }
    }

}

