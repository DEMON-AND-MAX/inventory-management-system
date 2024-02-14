import org.example.Movie;
import org.example.Product;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void testParameterizedConstructor() {
        int id = 1;
        String name = "ProductName";
        int price = 20;
        String director = "DirectorName";
        String publisher = "PublisherName";
        String genre = "Drama";

        Product product = new MockProduct(id, name, price, director, publisher, genre);

        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
        assertEquals(director, product.getDirector());
        assertEquals(publisher, product.getPublisher());
        assertEquals(genre, product.getGenre());
    }

    @Test
    public void testDefaultConstructor() {
        Product product = new MockProduct();

        assertEquals(0, product.getId());
        assertEquals("name", product.getName());
        assertEquals(0, product.getPrice());
        assertEquals("director", product.getDirector());
        assertEquals("publisher", product.getPublisher());
        assertEquals("genre", product.getGenre());
    }

    private static class MockProduct extends Product {
        public MockProduct() {
            super(0, "name", 0, "director", "publisher", "genre");
        }

        public MockProduct(int id, String name, int price, String director, String publisher, String genre) {
            super(id, name, price, director, publisher, genre);
        }
    }
    @Test
    public void testRentingMovie() {
        Movie movie = new Movie(1, "Inception", 15, "Christopher Nolan", "Warner Bros.", "Sci-Fi");

        assertFalse(movie.GetIsRented());

        assertTrue(movie.Rent());
        assertTrue(movie.GetIsRented());

        assertFalse(movie.Rent());
        assertTrue(movie.GetIsRented());
    }

    @Test
    public void testProductComparison() {
        Product product1 = new MockProduct(1, "Product1", 10, "Director1", "Publisher1", "Genre1");
        Product product2 = new MockProduct(2, "Product2", 20, "Director2", "Publisher2", "Genre2");

        assertTrue(product1.compareTo(product2) < 0);
    }

}
