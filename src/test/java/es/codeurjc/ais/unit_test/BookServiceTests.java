package es.codeurjc.ais.unit_test;

import es.codeurjc.ais.book.BookService;
import es.codeurjc.ais.book.OpenLibraryService.BookData;
import es.codeurjc.ais.book.OpenLibraryService;
import es.codeurjc.ais.notification.NotificationService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


public class BookServiceTests {

   @Test
    public void magicTest(){
        //Given
        OpenLibraryService open_l=mock(OpenLibraryService.class);
        NotificationService nser=mock(NotificationService.class);

        //When
        List<BookData> libros=new ArrayList<>();
        Integer[] auxint={1,2,3};
        String[] auxStr={"a","b","c"};
        BookData bk1=new BookData("magia1","clave1","Libro de magia",auxint,auxStr);
        BookData bk2=new BookData("magia2","clave2","Libro de magi2",auxint,auxStr);
        libros.add(bk1);
        libros.add(bk2);

        BookService book=new BookService(open_l,nser);
        when(open_l.searchBooks(anyString(),anyInt())).thenReturn(libros);
        //Then
       assertTrue(book.findAll("magic").size()==2);

    }
}
