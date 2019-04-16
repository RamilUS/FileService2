package bell.yusipov.fileservice.controller;

import bell.yusipov.fileservice.service.file.FileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(FilePagesController.class)
public class FilePagesControllerTest {

    @Mock
    private FileService fileService;
    private MockMvc mockMvc;

    @Before
    public void set() {
        mockMvc = MockMvcBuilders.standaloneSetup(new FilePagesController(fileService))
                .build();
    }

    /**
     * Проверка получения редиректа при загруженом файле
     */
    @Test
    public void uploaded() throws Exception {
        String description = "description";
        MockMultipartFile fileMock = new MockMultipartFile("file",
                "filename.txt", "text/plain", "some text".getBytes());
        when(fileService.upload(fileMock, description)).thenReturn("file is uploaded");
        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                .file(fileMock)
                .param("description", description))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/userpage/file is uploaded"))
                .andDo(print());
        verify(fileService, times(1)).upload(fileMock, description);
    }

    /**
     * Проверка  отображении страници при загруженом файле
     */
    @Test
    public void remove() throws Exception {
        Integer fileId = anyInt();
        when(fileService.remove(fileId)).thenReturn("File was deleted");
        mockMvc.perform(delete("/remove/{fileId}", fileId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/userpage/File was deleted"))
                .andDo(print());
        verify(fileService, times(1)).remove(fileId);
    }
}
