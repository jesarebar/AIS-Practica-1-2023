package es.codeurjc.ais.unit_test;

import es.codeurjc.ais.book.BookService;
import es.codeurjc.ais.book.OpenLibraryService.BookData;
import es.codeurjc.ais.book.OpenLibraryService;
import es.codeurjc.ais.notification.NotificationService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


public class BookServiceTests {

    @Test
    public void test1() {
        //Given
        OpenLibraryService open_l = mock(OpenLibraryService.class);
        NotificationService n_ser = mock(NotificationService.class);

        //When
        List<BookData> books = new ArrayList<>();
        Integer[] auxint = {1, 2, 3};
        String[] auxStr = {"a", "b", "c"};
        BookData bk1 = new BookData("magic1", "key1/sfg/hg", "Magic Book", auxint, auxStr);
        BookData bk2 = new BookData("magic2", "key2/fbh/fsg", "Magic 2ook", auxint, auxStr);
        books.add(bk1);
        books.add(bk2);

        BookService book = new BookService(open_l, n_ser);
        when(open_l.searchBooks(anyString(), anyInt())).thenReturn(books);

        //Then
        assertTrue(book.findAll("magic").size() == 2);

    }

    @Test
    public void test2(){
        //Given
        OpenLibraryService open_l = mock(OpenLibraryService.class);
        NotificationService n_ser = mock(NotificationService.class);


        //When

        BookService book = new BookService(open_l, n_ser);

        when(book.findById("fake_id")).thenReturn(Optional.empty());

    }
}
