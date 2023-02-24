package es.codeurjc.ais.unit_test;

import es.codeurjc.ais.book.BookService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class magicTest {

    @Test
    public class magicTest(){

        //Given
        BookService book=mock(BookService.class);
        //When
        when(book.findAll("Magic"))
        //Then
    }
}
