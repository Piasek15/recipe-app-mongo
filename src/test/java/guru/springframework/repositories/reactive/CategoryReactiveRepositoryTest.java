package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    @Autowired
    CategoryReactiveRepository reactiveRepository;

    @Before
    public void setUp() throws Exception {
        reactiveRepository.deleteAll().block();
    }

    @Test
    public void testSaveCategory() throws Exception {
        Category category = new Category();
        category.setDescription("test");

        reactiveRepository.save(category).block();

        assertEquals(1L, reactiveRepository.count().block().longValue());
    }

    @Test
    public void testFindByDescriptionCategory() throws Exception {
        Category category = new Category();
        category.setDescription("test");

        reactiveRepository.save(category).block();

        assertEquals("test", reactiveRepository.findByDescription("test").block().getDescription());
        assertNotNull(reactiveRepository.findByDescription("test").block().getId());
    }
}