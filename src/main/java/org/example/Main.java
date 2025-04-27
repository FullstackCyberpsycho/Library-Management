package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BookDAO dao = new BookDAO();
        String title, authorName;
        int year, id;

        while (true) {
            /*System.out.print("Консольное приложение 'Управление библиотекой'" +
                    "1 - Добавить книгу" +
                    "2 - Показать все книги" +
                    "3 - изменить название книги" +
                    "4 - Удалить книгу" +
                    "5 - Выход" +
                    "Ввод: ");*/
            System.out.print("The console application 'Library Management'\n" +
                    "1 - Add the book\n" +
                    "2 - Show all the books\n" +
                    "3 - change the name of the book\n" +
                    "4 - Delete the book\n" +
                    "5 - Output\n" +
                    "Enter: ");
            String choice = in.nextLine();

            switch (choice) {
                case "1":
                    /*System.out.println("Введите информацию о книге:");
                    System.out.print("Название: "); title = in.nextLine();
                    System.out.print("Автор: "); author = in.nextLine();
                    System.out.print("Год: "); year = in.nextInt();*/
                    System.out.println("Enter information about the book: ");
                    System.out.print("Name: "); title = in.nextLine();
                    System.out.print("Author: "); authorName = in.nextLine();
                    System.out.print("Year: "); year = in.nextInt();

                    Author author = new Author(authorName);
                    Book book = new Book(title, author, year);
                    dao.addBook(book);

                    break;
                case "2":
                    List<Book> books = dao.getAllBooks();
                    books.stream().forEach(b -> System.out.println(b.getCount() + ": " + b.getTitle() +
                            ", " + b.getAuthor().getName() + ", " + b.getYear()));
                    Book.setZeroCount();

                    break;
                case "3":
                    List<String> titles = dao.getTitleBooks();
                    titles.stream().forEach(System.out::println);
                    /*System.out.println("Введите ID и название книги для её изменения: ");
                    System.out.print("ID: "); id = in.nextInt();
                    System.out.print("Название: "); title = in.nextLine();*/
                    System.out.println("Enter the ID and name of the book to change it: ");
                    System.out.print("ID: "); id = in.nextInt(); in.nextLine();
                    System.out.print("Name: "); title = in.nextLine();
                    dao.updateBook(id, title);

                    break;
                case "4":
                    titles = dao.getTitleBooks();
                    titles.stream().forEach(System.out::println);
                    //System.out.println("Введите ID книги для её удаления: ");
                    System.out.println("Enter ID books to delete it: ");
                    System.out.print("ID: "); id = in.nextInt();
                    dao.deleteBook(id);

                    break;
                case "5":
                    System.out.println("You got out of the application");
                    //System.out.println("Вы вышли из приложения");
                    return;
            }
        }
    }
}