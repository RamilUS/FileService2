package bell.yusipov.fileservice.service;

import bell.yusipov.fileservice.exception.FileUploadException;
import bell.yusipov.fileservice.repository.FileRepository;
import bell.yusipov.fileservice.model.File;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class FileServiceTest {
    private FileServiceImpl fileServiceImpl;
    private File file;
    private List fileList;
    @Mock
    private FileRepository fileRepository;

    @Before
    public void set() {
        fileServiceImpl = new FileServiceImpl(fileRepository);
        file = mock(File.class);
        fileList =mock(List.class);
    }

    /**
     * Проверка инъекций
     */
    @Test
    public void checkNull() {
        assertNotNull(fileRepository);
        assertNotNull(file);
    }

    /**
     * Проверка загрузки файлов
     */
    @Test
    public void uploadTest() {
        MultipartFile uploadFile = mock(MultipartFile.class);
        String description = "description";
        assertEquals("file is uploaded", fileServiceImpl.upload(uploadFile, description));
    }

    /**
     * Проверка загрузки пустых файлов
     */
    @Test(expected = IllegalArgumentException.class)
    public void uploadNullFileTest() throws IllegalArgumentException {
        MultipartFile uploadFile = null;
        String description = "description";
        fileServiceImpl.upload(uploadFile, description);
    }

    /**
     * Проверка загрузки файлов без описания
     */
    @Test(expected = IllegalArgumentException.class)
    public void uploadNoDescriptionTest() throws IllegalArgumentException {
        MultipartFile uploadFile = mock(MultipartFile.class);
        String description = "";
        fileServiceImpl.upload(uploadFile, description);
    }

    /**
     * Проверка исключения IOException
     */
    @Test(expected = FileUploadException.class)
    public void uploadWithIOException() throws IOException{
        MultipartFile uploadFile = mock(MultipartFile.class);
        String description = "description";
        when(uploadFile.getBytes()).thenThrow(IOException.class);
        fileServiceImpl.upload(uploadFile, description);
    }

    /**
     * Проверка получения файла по индификатору
     */
    @Test
    public void getByFileByIdTest() {
        when(fileRepository.getOne(anyInt())).thenReturn(file);
        File actualFile = fileServiceImpl.getByFileById(anyInt());
        assertEquals(file, actualFile);
    }

    /**
     * Проверка получения файла по null индификатору
     */
    @Test(expected = IllegalArgumentException.class)
    public void getByFileByNullIdTest() throws IllegalArgumentException {
        assertNull(fileServiceImpl.getByFileById(null));
    }

    /**
     * Проверка получения всех файлов
     */
    @Test
    public void getFileListTest() {
        when(fileRepository.findAll()).thenReturn(fileList);
        List actualList = fileServiceImpl.getFileList();
        verify(fileRepository, times(1)).findAll();
        assertEquals(fileList,actualList);
    }

    /**
     * Проверка удаления файлов
     */
    @Test
    public void removeTest() {
        when(fileRepository.getOne(anyInt())).thenReturn(file);
        assertEquals("File was deleted", fileServiceImpl.remove(anyInt()));
        verify(fileRepository, times(1)).delete(file);
    }

    /**
     * Проверка удаления null
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeNullTest() throws IllegalArgumentException {
        Integer fileId = null;
        fileServiceImpl.remove(fileId);
    }
}
