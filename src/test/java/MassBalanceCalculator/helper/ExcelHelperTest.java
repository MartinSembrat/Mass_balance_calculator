package MassBalanceCalculator.helper;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import MassBalanceCalculator.model.Sale;

public class ExcelHelperTest {

    @Test
    public void testHasExcelFormat_WithExcelFile_ReturnsTrue() {
        // Arrange
        MultipartFile excelFile = new MockMultipartFile("data", "test.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", new byte[0]);

        // Act
        boolean result = ExcelHelper.hasExcelFormat(excelFile);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testHasExcelFormat_WithNonExcelFile_ReturnsFalse() {
        // Arrange
        MultipartFile nonExcelFile = new MockMultipartFile("data", "test.txt", "text/plain", new byte[0]);

        // Act
        boolean result = ExcelHelper.hasExcelFormat(nonExcelFile);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testExcelToSales_WithValidExcelFile_ReturnsListOfSales() throws IOException {
        // Arrange
        // Create a new Excel workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sales");

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"nr_Mag", "idPlatnika", "platnik_Nazwa", "idOdbiorcy", "odbiorca_Nazwa", "data", "wk", "indeks", "nazwa", "jm", "ilosc", "iloscKG", "wartoscNetto"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Insert data rows
        String[] data = {
                "1,1001,Platnik1,2001,Odbiorca1,2024-03-25,123,ABC123,Product1,Pcs,10.5,20.5,100.00",
                "2,1002,Platnik2,2002,Odbiorca2,2024-03-26,456,DEF456,Product2,Kg,15.5,30.5,200.00"
        };
        for (int i = 0; i < data.length; i++) {
            String[] rowData = data[i].split(",");
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < rowData.length; j++) {
                Cell cell = row.createCell(j);
                if (j == 0 || j == 1 || j == 3 || j == 6 || j == 12) {
                    // Set cell type to numeric for numeric columns
                    cell.setCellValue(Double.parseDouble(rowData[j]));
                } else if (j == 5) {
                    try {
                        // Parse date string and set as date type
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(rowData[j]);
                        cell.setCellValue(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Set cell type to string for other columns
                    cell.setCellValue(rowData[j]);
                }
            }
        }

        // Write the workbook content to an output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // Convert output stream to input stream
        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        // Create a MockMultipartFile with the input stream
        MultipartFile excelFile = new MockMultipartFile("file", "test.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", inputStream);

        // Act
        List<Sale> sales = ExcelHelper.excelToSales(excelFile.getInputStream());

        // Assert
        assertEquals(2, sales.size());
        assertEquals(1, sales.get(0).getNr_Mag());
        assertEquals(1001, sales.get(0).getIdPlatnika());
        assertEquals("Platnik1", sales.get(0).getPlatnik_Nazwa());
        assertEquals(2001, sales.get(0).getIdOdbiorcy());
        assertEquals("Odbiorca1", sales.get(0).getOdbiorca_Nazwa());
        assertEquals(LocalDate.of(2024, 3, 25), sales.get(0).getDataWystawienia().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        assertEquals(123, sales.get(0).getWk());
        assertEquals("ABC123", sales.get(0).getIndeks());
        assertEquals("Product1", sales.get(0).getNazwa());
        assertEquals("Pcs", sales.get(0).getJm());
        assertEquals(10.5f, sales.get(0).getIlosc());
        assertEquals(20.5f, sales.get(0).getIloscKG());
        assertEquals(Double.parseDouble("100.00"), sales.get(0).getWartoscNetto().doubleValue(), 0.01);

        assertEquals(2, sales.get(1).getNr_Mag());
        assertEquals(1002, sales.get(1).getIdPlatnika());
        assertEquals("Platnik2", sales.get(1).getPlatnik_Nazwa());
        assertEquals(2002, sales.get(1).getIdOdbiorcy());
        assertEquals("Odbiorca2", sales.get(1).getOdbiorca_Nazwa());
        assertEquals(LocalDate.of(2024, 3, 26), sales.get(1).getDataWystawienia().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        assertEquals(456, sales.get(1).getWk());
        assertEquals("DEF456", sales.get(1).getIndeks());
        assertEquals("Product2", sales.get(1).getNazwa());
        assertEquals("Kg", sales.get(1).getJm());
        assertEquals(15.5f, sales.get(1).getIlosc());
        assertEquals(30.5f, sales.get(1).getIloscKG());
        assertEquals(Double.parseDouble("200.00"), sales.get(1).getWartoscNetto().doubleValue(), 0.01);
    }
}