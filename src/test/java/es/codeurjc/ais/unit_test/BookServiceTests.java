package es.codeurjc.ais.unit_test;

import es.codeurjc.ais.book.BookService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class BookServiceTests {

    @Test
    public class magicTest(){
        List<Book>
        //Given
        BookService book=mock(BookService.class);
        //When
        when(book.findAll("Magic")).thenReturn();
        //Then
    }
}
