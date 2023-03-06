package es.codeurjc.ais.integration;

import es.codeurjc.ais.book.OpenLibraryService;
import es.codeurjc.ais.book.OpenLibraryService.BookData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenLibraryServiceTests {
    @Test
    public void Test1() {
        int n = 15;
        OpenLibraryService servicio = new OpenLibraryService();
        List<BookData> drama_list = servicio.searchBooks("drama", n);
        List<BookData> magic_list = servicio.searchBooks("magic", n);
        List<BookData> fantasy_list = servicio.searchBooks("fantasy", n);
        //Tenemos que comprobar que la etiqueta de los libros esta bien
        assertEquals(drama_list.size(), n);
        assertEquals(magic_list.size(), n);
        assertEquals(fantasy_list.size(), n);
    }
}
