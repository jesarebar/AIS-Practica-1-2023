package es.codeurjc.ais.integration;

import es.codeurjc.ais.book.OpenLibraryService;
import es.codeurjc.ais.book.OpenLibraryService.BookData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenLibraryServiceTests {
    @Test
    public void test1() {
        int n = 15;
        OpenLibraryService service = new OpenLibraryService();
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

    }
}
