package bell.yusipov.fileservice.controller;

import bell.yusipov.fileservice.service.file.FileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(FileRestController.class)
public class FileRestControllerTest {

    @Mock
    private FileService fileService;
    private MockMvc mockMvc;

    @Before
    public void set() {
        mockMvc = MockMvcBuilders.standaloneSetup(new FileRestController(fileService)).build();
    }

    /**
     * Проверка загрузки файла из БД
     */
    @Test
    public void downloadFileTest() throws Exception {
        Integer fileId = anyInt();
        mockMvc.perform(get("/download/{fileId}", fileId))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }
}
