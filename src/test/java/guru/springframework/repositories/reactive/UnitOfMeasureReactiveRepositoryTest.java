package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {

    @Autowired
    UnitOfMeasureReactiveRepository reactiveRepository;

    @Before
    public void setUp() throws Exception {
        reactiveRepository.deleteAll().block();
    }

    @Test
    public void testSaveUOM() throws Exception {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("new one");

        reactiveRepository.save(unitOfMeasure).block();

        assertEquals(1L, reactiveRepository.count().block().longValue());
    }

    @Test
    public void testFindByDescriptionUOM() throws Exception {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("new one");

        reactiveRepository.save(unitOfMeasure).block();

        assertEquals("new one", reactiveRepository.findByDescription("new one").block().getDescription());
        assertNotNull(reactiveRepository.findByDescription("new one").block().getId());
    }
}