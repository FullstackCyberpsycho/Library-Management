package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private final String url = "";
    private final String user = "";
    private final String password = "";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author, year) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor().getName());
            pstmt.setInt(3, book.getYear());
            pstmt.executeUpdate();
            //System.out.println("Книга добавлена");
            System.out.println("The book is added");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT id, title, author, year FROM books";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String authorName = rs.getString("author");
                int year = rs.getInt("year");

                Author author = new Author(authorName);
                Book book = new Book(id, title, author, year);
                books.add(book);
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
}

