# Hibernate System to Explore Various Cascade Types

This task is designed to help you understand and demonstrate how to use different cascading types - `PERSIST`, `MERGE`, `REMOVE`, `REFRESH`, and `DETACH` - and show how they influence the persistence context and entity lifecycle.

Duration: _30 minutes_

## Description

You will need to create an application that manages `Author` and `Book` entities with a one-to-many relationship. The task will explore various cascade types in this relationship.

In this task, you are given `Author` and `Book` classes for your entities and `BooksCascadeDao`, a class that acts as an interface for executing queries against a database (DAO). Also, you have the `Demo` class, where you'll need to instantiate Hibernate's `SessionFactory` object and demonstrate your code in action.

Use `resources/hibernate.cfg.xml` to configure Hibernate. Then, establish database connectivity, register entities, and set up `SessionFactory`.

Your database should be set up as follows:

- Create two tables: `author` and `book`
- The `author` table should have the following fields:
   - `id` (Primary Key, Integer)
   - `name` (Character varying)
- The `book` table should have the following fields:
   - `id` (Primary Key, Integer)
   - `title` (Character varying)
   - `authorId` (Integer, Foreign Key)

After setting up the database, proceed with setting up your Java entities - `Author` and `Book` - and providing a database connection for Hibernate. For instance, you can set all the cascade types for the `Author` entity and the collection of books associated with this author:

```java
{CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})}
```

You'll have to demonstrate each of these Cascade Types in a separate DAO method (operations). You will find stub methods in the template class of the `BooksCascadeDao` class. This means you'll have around five methods, each of which must demonstrate a particular `CascadeType`.

Additional Points:

- Implement logging to observe cascade operations.
- Ensure Hibernate sessions and transactions are handled properly.

## Requirements

- The one-to-many relationship between `Author` and their Books must be provided correctly and include all the required constraints.
- The work of all the Cascade Types must be demonstrated (a separate method for each one).

## Examples

Sample output for `demonstrateDetach()`:

```console
Hibernate: select a1_0.id,b1_0."authorId",b1_0.id,b1_0.title,a1_0.name from author a1_0 left join book b1_0 on a1_0.id=b1_0."authorId" where a1_0.id=?

Is Author Persistent now: true | Is Author's Book Entity Persistent: true
Is Author Persistent now: false | Is Author's Book Entity Persistent: false
```
