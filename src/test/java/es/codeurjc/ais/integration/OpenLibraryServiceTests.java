package es.codeurjc.ais.integration;

import es.codeurjc.ais.book.OpenLibraryService;
import es.codeurjc.ais.book.OpenLibraryService.BookData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class OpenLibraryServiceTests {

    OpenLibraryService service;
    @BeforeEach
    public void setUp(){
        service = new OpenLibraryService();
    }

    @Test
    public void test1() {
        int n = 15;
        ArrayList<List<BookData>> searchList = new ArrayList<>();
        searchList.add(service.searchBooks("drama", n));
        searchList.add(service.searchBooks("magic", n));
        searchList.add(service.searchBooks("fantasy", n));
        for (List<BookData> search: searchList
             ) {
            assertEquals(search.size(), n);
        }
    }

    @Test
    public void test2() {
        assertDoesNotThrow(() -> {
            BookData book = service.getBook("OL8479867W");
            assertEquals("The Name of the Wind", book.title);
        });
    }
}
