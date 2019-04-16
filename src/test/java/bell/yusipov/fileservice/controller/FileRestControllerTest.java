package bell.yusipov.fileservice.controller;

import bell.yusipov.fileservice.service.FileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(FileRestController.class)
public class FileRestControllerTest {

    @Mock
    private FileService fileService;
    @Mock
    private ResponseEntity<ByteArrayResource> responseEntity;
    private ByteArrayResource resource;
    @Autowired
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
        String testString = "Some text for test";
        byte[] data = testString.getBytes();
        resource = new ByteArrayResource(data);
        when(fileService.download(fileId)).thenReturn(responseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "testFile")
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(data.length)
                .body(resource));

       MvcResult actualresponse= mockMvc.perform(get("/download/{fileId}", fileId))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        assertEquals(data.length,actualresponse.getResponse().getContentLength());
        assertEquals(testString, actualresponse.getResponse().getContentAsString());
    }
}
