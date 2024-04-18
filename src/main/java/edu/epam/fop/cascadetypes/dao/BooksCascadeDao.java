package edu.epam.fop.cascadetypes.dao;

import edu.epam.fop.cascadetypes.entity.Author;
import edu.epam.fop.cascadetypes.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class BooksCascadeDao {
    private SessionFactory sessionFactory;
    public BooksCascadeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int demonstratePersist(Author author) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(author);
            transaction.commit();
            return author.getId();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Exception occurred in Persist method:");
            e.printStackTrace();
        }
        return -1;
    }

    public void demonstrateMerge(Author author) {
        Transaction transaction = null;
         try (Session session = sessionFactory.openSession()) {
             transaction = session.beginTransaction();
             session.merge(author);
             transaction.commit();
        } catch (Exception e) {
             if (transaction != null && transaction.isActive()) {
                 transaction.rollback();
             }
            System.out.println("Exception occurred in Merge method:");
            e.printStackTrace();
        }
    }

    public int demonstrateRemove(String bookTitle) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Book book = (Book) session.createQuery("from Book where title like :bookName")
                    .setParameter("bookName", "%" + bookTitle + "%")
                    .uniqueResult();
            if (book != null) {
                session.remove(book);
                System.out.println("Book entity removed successfully.");
                transaction.commit();
                return book.getId();
            }
            System.out.println("Book not found with title: " + bookTitle);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Exception occurred in Remove method:");
            e.printStackTrace();
        }
        return -1;
    }

    public Author demonstrateRefresh(Author author) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.refresh(author); // CascadeType.REFRESH will refresh author and associated books
            transaction.commit();
            return author;
        } catch (Exception e) {
            System.out.println("Exception occurred in Refresh method:");
            e.printStackTrace();
            return null;
        }
    }

    public Author demonstrateDetach(String authorName) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Author author = (Author) session.createQuery("from Author where name like :authorName")
                    .setParameter("authorName", "%" + authorName + "%")
                    .uniqueResult();

            if (author != null) {
                session.detach(author);
                System.out.println("Author entity detached successfully.");
                transaction.commit();
                return author;
            }
            System.out.println("Author not found with name: " + authorName);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Exception occurred in Detach method:");
            e.printStackTrace();
        }
        return null;
    }
}
