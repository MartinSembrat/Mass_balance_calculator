package MassBalanceCalculator.service;

import MassBalanceCalculator.model.Sale;
import MassBalanceCalculator.repository.ISaleRepository;
import MassBalanceCalculator.helper.ExcelHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MBServiceTest {

    @Mock
    private ISaleRepository saleRepository;

    @InjectMocks
    private MBService mbService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() throws IOException {
        // Mock data
        byte[] content = "test data".getBytes();
        MultipartFile file = new MockMultipartFile("data", "filename.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", content);
        List<Sale> sales = Arrays.asList(new Sale(), new Sale()); // Mock list of sales

        // Mock behavior
        when(ExcelHelper.excelToSales(any())).thenReturn(sales);

        // Test
        mbService.save(file);

        // Verify
        verify(saleRepository, times(1)).saveAll(sales);
    }

    @Test
    public void testGetAllSales() {
        // Mock data
        List<Sale> expectedSales = Arrays.asList(new Sale(), new Sale()); // Mock list of sales

        // Mock behavior
        when(saleRepository.findAll()).thenReturn(expectedSales);

        // Test
        List<Sale> actualSales = mbService.getAllSales();

        // Verify
        assertEquals(expectedSales, actualSales);
    }
}