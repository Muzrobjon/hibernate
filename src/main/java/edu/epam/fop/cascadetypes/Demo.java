package edu.epam.fop.cascadetypes;

import edu.epam.fop.cascadetypes.dao.BooksCascadeDao;
import edu.epam.fop.cascadetypes.entity.Author;
import edu.epam.fop.cascadetypes.entity.Book;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Demo {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        BooksCascadeDao booksCascadeDao = new BooksCascadeDao(sessionFactory);


        //creating author and book
        generator(booksCascadeDao);


        //We can retrieve the author by name using the detach method. It's more interesting than using an ID.
        Author mika = booksCascadeDao.demonstrateDetach("Micha");
       //We need to refresh the authors so that all related books are also fetched.
        booksCascadeDao.demonstrateRefresh(mika);

        System.out.println(mika); // make sure everything is correct

        Book newBook = new Book();
        newBook.setTitle("Harry Potter and the Philosopher's Stone");
        newBook.setAuthor(mika);
        mika.setBooks(newBook);

       //We can add new book to the author and merge all changes, which will be stored in the database.
        booksCascadeDao.demonstrateMerge(mika);

        System.out.println(mika);// see if it worked

        // removing the book "Creed" from Arnold Schwarzenegger, because the book belongs to Stallone
        Author arno = booksCascadeDao.demonstrateDetach("Arnold");

        booksCascadeDao.demonstrateRefresh(arno);

        booksCascadeDao.demonstrateMerge(arno);

        System.out.println(arno);
        // removing the book "Creed" from Arnold Schwarzenegger, because the book belongs to Stallone
        System.out.println(booksCascadeDao.demonstrateRemove("Creed"));

        Author newRrno = booksCascadeDao.demonstrateDetach("Arnold");
        booksCascadeDao.demonstrateRefresh(newRrno);
        System.out.println(newRrno);

    }

    private static void generator(BooksCascadeDao booksCascadeDao) {
        Author author = new Author();
        author.setName("Michael jackson");
        Book book = new Book();
        book.setTitle("The Lord of the Rings");
        book.setAuthor(author);
        author.setBooks(book);
        booksCascadeDao.demonstratePersist(author);

        Author author2 = new Author();
        author2.setName("Arnold Schwarzenegger");
        Book book1 = new Book();
        book1.setTitle("Rocky");
        book1.setAuthor(author2);
        Book book2 = new Book();
        book2.setTitle("Rocky 2");
        book2.setAuthor(author2);
        Book book3 = new Book();
        book3.setTitle("Rocky 3");
        book3.setAuthor(author2);
        Book book4 = new Book();
        book4.setTitle("Creed");
        book4.setAuthor(author2);
        author2.setBooks(book1);
        author2.setBooks(book2);
        author2.setBooks(book3);
        author2.setBooks(book4);
        booksCascadeDao.demonstratePersist(author2);
    }
}
