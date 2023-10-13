package s.volodymyr.onlinebookstore;

import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import s.volodymyr.onlinebookstore.model.Book;
import s.volodymyr.onlinebookstore.service.BookService;

@SpringBootApplication
public class OnlineBookStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineBookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(BookService bookService) {
        return args -> {
            Book testBook = new Book();
            testBook.setTitle("Cooking for dummies");
            testBook.setAuthor("Raphael Ambrosius Kusto");
            testBook.setIsbn("12345678");
            testBook.setPrice(BigDecimal.valueOf(90));
            testBook.setDescription("Awesome book");
            testBook.setCoverImage("Cover 1");

            bookService.save(testBook);
            bookService.findAll();
        };

    }
}
