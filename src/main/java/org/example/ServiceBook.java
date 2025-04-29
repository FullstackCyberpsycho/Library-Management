package org.example;

public class ServiceBook {
    private BookDAO dao = new BookDAO();

    public void addBook(String title, String authorName, String authorSurname, String authorPatronymic, int year) {
        Author author = new Author(authorName, authorSurname, authorPatronymic);
        Book book = new Book(title, author, year);
        dao.addBook(book);
    }

    public void updateBook(int id, String title) {
        dao.updateBook(id, title);
    }

    public void deleteBook(int id) {
        dao.deleteBook(id);
    }
}
