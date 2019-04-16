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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(GeneralController.class)
public class GeneralControllerTest {
    @Mock
    private FileService fileService;
    private MockMvc mockMvc;

    @Before
    public void set() {
        mockMvc = MockMvcBuilders.standaloneSetup(new GeneralController(fileService)).build();
    }

    /**
     * Проверка отображения страницы с сервисным сообщением
     */
    @Test
    public void serviceTest() throws Exception {
        String testReport = "testReport";
        mockMvc.perform(get("/userpage/{report}", testReport))
                .andExpect(status().isOk())
                .andExpect(view().name("userpage"))
                .andDo(print());
    }

    /**
     * Проверка отображении страницы без сервесного сообщения
     */
    @Test
    public void userPageTest() throws Exception {
        mockMvc.perform(get("/userpage/"))
                .andExpect(status().isOk())
                .andExpect(view().name("userpage"))
                .andDo(print());
    }
}
