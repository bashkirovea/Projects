package ru.bash.spring.BootLibraryApp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bash.spring.BootLibraryApp.models.Book;
import ru.bash.spring.BootLibraryApp.models.Person;
import ru.bash.spring.BootLibraryApp.repositories.BooksRepository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }


    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        if(sortByYear)
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }
    public List<Book> findAll(boolean sortByYear) {
        if(sortByYear)
            return booksRepository.findAll(Sort.by("year"));
        return booksRepository.findAll();
    }

    @Transactional
    public Person getBookOwner(int id) {
        return booksRepository.findById(id).map(Book::getOwner).orElse(null);
    }
    @Transactional
    public void release(int id) {
        booksRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(null);
                    book.setTakenAt(null);
        });
    }
    @Transactional
    public void assign(int id, Person selectedPerson) {
        booksRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(selectedPerson);
                    book.setTakenAt(new Date());
                });
    }


    public Book findOne(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        book.setTakenAt(new Date());
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Book bookToBeUpdated = booksRepository.findById(id).get();

        updatedBook.setId(id);
        updatedBook.setOwner(bookToBeUpdated.getOwner());

        booksRepository.save(updatedBook);
    }


    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }


    public List<Book> searchByTitle(String title) {
        return booksRepository.findByTitleStartingWith(title);
    }




}
