import org.example.Movie;
import org.junit.Test;
import static org.junit.Assert.*;

public class MovieTest {

    @Test
    public void testParameterizedConstructor() {
        int id = 1;
        String name = "MovieName";
        int price = 10;
        String director = "DirectorName";
        String publisher = "PublisherName";
        String genre = "Action";

        Movie movie = new Movie(id, name, price, director, publisher, genre);

        assertEquals(id, movie.getId());
        assertEquals(name, movie.getName());
        assertEquals(price, movie.getPrice());
        assertEquals(director, movie.getDirector());
        assertEquals(publisher, movie.getPublisher());
        assertEquals(genre, movie.getGenre());
        assertFalse(movie.GetIsRented());
    }

    @Test
    public void testDefaultConstructor() {
        Movie movie = new Movie();

        assertEquals(0, movie.getId());
        assertEquals("name", movie.getName());
        assertEquals(0, movie.getPrice());
        assertEquals("director", movie.getDirector());
        assertEquals("publisher", movie.getPublisher());
        assertEquals("genre", movie.getGenre());
        assertFalse(movie.GetIsRented());
    }
}
