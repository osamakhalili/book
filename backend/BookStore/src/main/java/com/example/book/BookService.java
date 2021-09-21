package com.example.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addNewBook(Book book) {

        bookRepository.save(book);

    }

    public void patchBook (long id , Book book) {

        Book books =  bookRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));

        boolean needUpdate = false;


        if (StringUtils.hasLength(book.getName())) {
            books.setName(book.getName());
            needUpdate = true;
        }

        if (StringUtils.hasLength(book.getAuthor())) {
            books.setAuthor(book.getAuthor());
            needUpdate = true;
        }

        if (StringUtils.hasLength(book.getImage())) {
            books.setImage(book.getImage());
            needUpdate = true;
        }

        if (needUpdate) {
            bookRepository.save(books);
        }

    }

    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }
    public Optional<Book> findBookByID(long id ) {
        return bookRepository.findById(id);
    }

    public void deletBook(long bookId) {

        boolean exists= bookRepository.existsById(bookId);

        if (!exists) {
            throw  new IllegalStateException(
                    "Book with id "+ bookId +"does not exist");

        }

        bookRepository.deleteById(bookId);

    }
    @Transactional
    public void updateBook(Long bookId,String name, String author, String image ) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()-> new IllegalStateException("Book with id "+ bookId +"does not exist"));

        if (name != null &&name.length()>0 &&!Objects.equals(book.getName(),name)) {
            book.setName(name);
        }

        if (author != null &&author.length()>0 &&!Objects.equals(book.getAuthor(),author)) {
            book.setAuthor(author);
        }
        if (image != null &&image.length()>0 &&!Objects.equals(book.getImage(),image)) {
            book.setImage(image);
        }


    }
}