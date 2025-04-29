package org.example;

import java.util.List;

public class BookFormatter {
    private BookDAO dao = new BookDAO();

    public void printAllBooks() {
        List<Book> books = dao.getAllBooks();
        System.out.println("title, name, surname, patronymic, year:");
        books.stream().forEach(b -> System.out.println(b.getCount() + ": " + b.getTitle() +
                ", " + b.getAuthor().getName() + " " + b.getAuthor().getSurname() + " " +
                b.getAuthor().getPatronymic() + ", " + b.getYear()));
        Book.setZeroCount();
    }

    public void printTitleBooks() {
        List<String> titles = dao.getTitleBooks();
        titles.stream().forEach(System.out::println);
    }
}
