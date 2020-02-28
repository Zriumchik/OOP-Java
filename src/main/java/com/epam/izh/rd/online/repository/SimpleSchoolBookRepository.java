package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] newSchoolBooks = new SchoolBook[count() + 1];
        System.arraycopy(schoolBooks, 0, newSchoolBooks, 0, count());
        newSchoolBooks[count()] = book;
        schoolBooks = newSchoolBooks;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int numberOfBook = 0;
        for (int i = 0; i < count(); i++) {
            if (schoolBooks[i].getName().equals(name)) {
                numberOfBook++;
            }
        }
        if (numberOfBook != 0) {
            SchoolBook[] foundedBooks = new SchoolBook[numberOfBook];
            int numberOfBookCount = 0;
            for (int i = 0; i < count(); i++) {
                if (schoolBooks[i].getName().equals(name)) {
                    foundedBooks[numberOfBookCount] = schoolBooks[i];
                    numberOfBookCount++;
                }
            }
            return foundedBooks;
        }
        return new SchoolBook[0];
    }

    @Override
    public boolean removeByName(String name) {
        int bookCounter = findByName(name).length;
        if (bookCounter == 0) {
            return false;
        }
        SchoolBook[] newSchoolBooks = new SchoolBook[count() - bookCounter];
        bookCounter = 0;
        for (int i = 0; i < count(); i++) {
            if (!schoolBooks[i].getName().equals(name)) {
                newSchoolBooks[bookCounter] = schoolBooks[i];
                bookCounter++;
            }
        }
        schoolBooks = newSchoolBooks;
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
