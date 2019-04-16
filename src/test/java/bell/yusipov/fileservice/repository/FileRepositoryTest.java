package bell.yusipov.fileservice.repository;

import bell.yusipov.fileservice.model.File;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
@DataJpaTest
public class FileRepositoryTest {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private TestEntityManager entityManager;

    /**
     * Тестирование обновления счетчика скачиваний
     */
    @Test
    public void repositoryUpdateTest() {
        File testFile = new File();
        testFile.setFileName("testFileName");
        testFile.setDescription("test description");
        testFile.setDownloadCount(1);
        String strTest = "some text for test";
        byte[] bytesFromString = strTest.getBytes();
        testFile.setFileData(bytesFromString);
        Integer id = entityManager.persistAndGetId(testFile, Integer.class);
        fileRepository.update(13, id);
        File secondTestFile = fileRepository.getOne(id);
        assertNotNull(id);
        assertTrue(secondTestFile.getDownloadCount() == 13);
    }
}
