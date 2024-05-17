package MassBalanceCalculator.service;

import MassBalanceCalculator.helper.ExcelHelper;
import MassBalanceCalculator.repository.ISaleRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import static org.mockito.Mockito.*;

class MBServiceTest {

    @Mock
    private ISaleRepository saleRepository;

    @InjectMocks
    private MBService mbService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() throws Exception {
        // Create a sample Excel file in memory
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(ExcelHelper.SHEET);

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("nr_Mag");
        headerRow.createCell(1).setCellValue("idPlatnika");
        headerRow.createCell(2).setCellValue("platnik_Nazwa");
        headerRow.createCell(3).setCellValue("idOdbiorcy");
        headerRow.createCell(4).setCellValue("odbiorca_Nazwa");
        headerRow.createCell(5).setCellValue("data_wystawienia");
        headerRow.createCell(6).setCellValue("wk");
        headerRow.createCell(7).setCellValue("indeks");
        headerRow.createCell(8).setCellValue("nazwa");
        headerRow.createCell(9).setCellValue("jm");
        headerRow.createCell(10).setCellValue("ilosc");
        headerRow.createCell(11).setCellValue("iloscKG");
        headerRow.createCell(12).setCellValue("wartoscNetto");

        // Create a sample data row
        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue(1); // nr_Mag (numeric)
        dataRow.createCell(1).setCellValue(2); // idPlatnika (numeric)
        dataRow.createCell(2).setCellValue("Platnik Name"); // platnik_Nazwa (string)
        dataRow.createCell(3).setCellValue(3); // idOdbiorcy (numeric)
        dataRow.createCell(4).setCellValue("Odbiorca Name"); // odbiorca_Nazwa (string)

        // Date cell
        Cell dateCell = dataRow.createCell(5);
        dateCell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").parse("2024-05-16"));
        dateCell.setCellStyle(workbook.createCellStyle());
        dateCell.getCellStyle().setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("yyyy-MM-dd"));

        dataRow.createCell(6).setCellValue(4); // wk (numeric)
        dataRow.createCell(7).setCellValue("Indeks1"); // indeks (string)
        dataRow.createCell(8).setCellValue("Nazwa1"); // nazwa (string)
        dataRow.createCell(9).setCellValue("Jm1"); // jm (string)

        // Numeric cells
        dataRow.createCell(10).setCellValue("10.1"); // ilosc (numeric)
        dataRow.createCell(11).setCellValue("20.1"); // iloscKG (numeric)
        dataRow.createCell(12).setCellValue(30.0); // wartoscNetto (numeric)

        // Write the workbook to a byte array
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        // Create a MockMultipartFile
        MockMultipartFile file = new MockMultipartFile("file", "test.xlsx", ExcelHelper.TYPE, out.toByteArray());

        // Mock repository behavior
        when(saleRepository.saveAll(anyList())).thenReturn(Collections.emptyList());

        // Call the service method
        mbService.save(file);

        // Verify the repository interaction
        verify(saleRepository, times(1)).saveAll(anyList());
    }
}