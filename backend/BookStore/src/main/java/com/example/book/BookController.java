package com.example.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path="book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public List<Book> getBook() {
        return bookService.findAllBook();
    }

    @GetMapping(path = "/{bookId}")
    public Optional<Book> getBook(@PathVariable("bookId") long bookId) {
        return bookService.findBookByID(bookId);
    }

    @PostMapping
    public void saveBook (@RequestBody Book book){

        bookService.addNewBook(book);
    }
    @DeleteMapping(path = "/{bookId}")
    public void deleteBook (@PathVariable("bookId") long bookId){
        bookService.deletBook(bookId);

    }
    @PutMapping(path = "/{bookId}")
    public void updateBook (@PathVariable("bookId") long bookId,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String author,
                            @RequestParam(required = false) String image){

        bookService.updateBook(bookId,name,author,image);
    }
    @PatchMapping("/{bookId}")
    public void patchResource(@PathVariable long bookId, @RequestBody Book book) {
        bookService.patchBook(bookId, book);
    }

}