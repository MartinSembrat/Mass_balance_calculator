package MassBalanceCalculator.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import MassBalanceCalculator.helper.ExcelHelper;
import MassBalanceCalculator.message.ResponseMessage;
import MassBalanceCalculator.model.Sale;
import MassBalanceCalculator.model.custom.IRMContentInFG;
import MassBalanceCalculator.service.MBService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
public class MBControllerTest {

    @Mock
    private MBService fileService;

    @InjectMocks
    private MBController mbController;

    @Test
    void testUploadFile_Success() {
        // Create a mock MultipartFile
        MultipartFile file = mock(MultipartFile.class);

        // Stub the behavior of getContentType() to return the expected content type
        when(file.getContentType()).thenReturn("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Test the uploadFile method of the controller
        ResponseEntity<ResponseMessage> response = mbController.uploadFile(file);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Uploaded the file successfully: "+ file.getOriginalFilename(), response.getBody().getMessage());
    }

    @Test
    public void testUploadFile_Failure() {
        // Mocking behavior
        MultipartFile file = mock(MultipartFile.class);
        when(file.getContentType()).thenReturn("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        when(ExcelHelper.hasExcelFormat(file)).thenReturn(true);
        doThrow(RuntimeException.class).when(fileService).save(any(MultipartFile.class));

        // Test
        ResponseEntity<ResponseMessage> response = mbController.uploadFile(file);

        // Assertion
        assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());
        assertEquals("Could not upload the file: "+ file.getOriginalFilename()+"!", response.getBody().getMessage());
    }

    @Test
    public void testUploadFile_InvalidFormat() {
        // Mocking behavior
        MultipartFile file = mock(MultipartFile.class);
        when(file.getContentType()).thenReturn("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
//        when(ExcelHelper.hasExcelFormat(file)).thenReturn(false);

        // Test
        ResponseEntity<ResponseMessage> response = mbController.uploadFile(file);

        // Assertion
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Please upload an excel file!", response.getBody().getMessage());
    }

    @Test
    public void testGetAllSales() {
        // Mocking behavior
        List<Sale> sales = new ArrayList<>();
        when(fileService.getAllSales()).thenReturn(sales);

        // Test
        ResponseEntity<List<Sale>> response = mbController.getAllSales();

        // Assertion
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetRMContentInFG() {
        // Mocking behavior
        String index = "test_index";
        List<IRMContentInFG> irmContentInFG = new ArrayList<>();
        when(fileService.findIRMContentInFG(index)).thenReturn(irmContentInFG);

        // Test
        ResponseEntity<List<IRMContentInFG>> response = mbController.getRMContentInFG(index);

        // Assertion
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testFindIRMContentInFGCakes() {
        // Mocking behavior
        String index = "test_index";
        List<IRMContentInFG> irmContentInFGCakes = new ArrayList<>();
        when(fileService.findIRMContentInFGCakes(index)).thenReturn(irmContentInFGCakes);

        // Test
        ResponseEntity<List<IRMContentInFG>> response = mbController.findIRMContentInFGCakes(index);

        // Assertion
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testFindIRMContentInFillings() {
        // Mocking behavior
        String index = "test_index";
        List<IRMContentInFG> irmContentInFillings = new ArrayList<>();
        when(fileService.findIRMContentInFillings(index)).thenReturn(irmContentInFillings);

        // Test
        ResponseEntity<List<IRMContentInFG>> response = mbController.findIRMContentInFillings(index);

        // Assertion
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testFindIRMContentInProductOverall() {
        // Mocking behavior
        String index = "test_index";
        List<IRMContentInFG> irmContentInFillings = new ArrayList<>();
        when(fileService.findIRMContentInProductOverall(index)).thenReturn(irmContentInFillings);

        // Test
        ResponseEntity<List<IRMContentInFG>> response = mbController.findIRMContentInProductOverall(index);

        // Assertion
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}