package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        Author[] updatedAutors = new Author[count() + 1];
        if (count() >= 0) System.arraycopy(authors, 0, updatedAutors, 0, count());
        updatedAutors[count()] = author;
        authors = updatedAutors;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (int i = 0; i < count(); i++) {
            if ((authors[i].getName().equals(name)) && authors[i].getLastName().equals(lastname)) {
                return authors[i];
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        }
        Author[] updatedAutors = new Author[count() - 1];
        int count = 0;
        for (int i = 0; i < count(); i++) {
            if (!(author.equals(authors[i]))) {
                updatedAutors[count] = authors[i];
                count++;
            }
        }
        authors = updatedAutors;
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
