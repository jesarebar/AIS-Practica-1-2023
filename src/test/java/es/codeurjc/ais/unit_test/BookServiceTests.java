package es.codeurjc.ais.unit_test;

import es.codeurjc.ais.book.BookService;
import es.codeurjc.ais.book.OpenLibraryService.BookData;
import es.codeurjc.ais.book.OpenLibraryService;
import es.codeurjc.ais.notification.NotificationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


public class BookServiceTests {

    OpenLibraryService open_l;
    NotificationService n_ser;
    BookService book;

    @BeforeEach
    public void setUp(){
        //Given
        open_l = mock(OpenLibraryService.class);
        n_ser = mock(NotificationService.class);
        book = new BookService(open_l, n_ser);
    }
    @Test
    public void test1() {
        //When
        List<BookData> books = new ArrayList<>();
        Integer[] auxint = {1, 2, 3};
        String[] auxStr = {"a", "b", "c"};
        BookData bk1 = new BookData("magic1", "key1/sfg/hg", "Magic Book", auxint, auxStr);
        BookData bk2 = new BookData("magic2", "key2/fbh/fsg", "Magic 2ook", auxint, auxStr);
        books.add(bk1);
        books.add(bk2);

        when(open_l.searchBooks(anyString(), anyInt())).thenReturn(books);

        //Then
        assertEquals(2, book.findAll("magic").size());
        verify(n_ser).info(anyString());
        verify(open_l).searchBooks(anyString(), anyInt());
    }

    @Test
    public void test2(){

        //When
        when(open_l.getBook(anyString())).thenThrow(HttpClientErrorException.class);

        //Then
        assertTrue(book.findById("fake_id").isEmpty());
        verify(n_ser).error(anyString());
        verify(open_l).getBook(anyString());

    }
}
