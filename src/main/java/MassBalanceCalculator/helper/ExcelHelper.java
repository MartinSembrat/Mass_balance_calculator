package MassBalanceCalculator.helper;

import MassBalanceCalculator.model.Sale;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//provides functions to read Excel file
public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "nr_Mag", "idPlatnika", "platnik_Nazwa", "idOdbiorcy", "odbiorca_Nazwa", "data", "wystawienia", "wk", "indeks", "nazwa", "jm", "ilosc", "iloscKG", "wartoscNetto"};
    static String SHEET = "Sales";

    public static boolean hasExcelFormat(MultipartFile file){
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }return true;
    }

    public static List<Sale> excelToSales(InputStream is) {
        long start = System.currentTimeMillis();
        try {
            Workbook workBook = new XSSFWorkbook(is);
            Sheet sheet = workBook.getSheet(SHEET);
            //npe
            Iterator<Row> rowIterator = sheet.iterator();
            //Omit column names in first row
            rowIterator.next();
            List<Sale> listSales = new ArrayList<>();

            while (rowIterator.hasNext()) {
                Row currentRow = rowIterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                Sale sale = new Sale();
               // int rowIndex = currentRow.getRowNum();

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();

                    int columnIndex = currentCell.getColumnIndex();
                    switch (columnIndex) {
                        case 0:
                            sale.setNr_Mag((int) currentCell.getNumericCellValue());
                            break;
                        case 1:
                            sale.setIdPlatnika((int) currentCell.getNumericCellValue());
                            break;
                        case 2:
                            sale.setPlatnik_Nazwa(currentCell.getStringCellValue());
                            break;
                        case 3:
                            sale.setIdOdbiorcy((int) currentCell.getNumericCellValue());
                            break;
                        case 4:
                            sale.setOdbiorca_Nazwa(currentCell.getStringCellValue());
                            break;
                        case 5:
                            sale.setDataWystawienia(currentCell.getDateCellValue());
                            break;
                        case 6:
                            sale.setWk((int) currentCell.getNumericCellValue());
                            break;
                        case 7:
                            sale.setIndeks(currentCell.getStringCellValue());
                            break;
                        case 8:
                            sale.setNazwa(currentCell.getStringCellValue());
                            break;
                        case 9:
                            sale.setJm(currentCell.getStringCellValue());
                            break;
                        case 10:
                            sale.setIlosc(Float.parseFloat(currentCell.getStringCellValue()));
                            break;
                        case 11:
//                            sale.setIloscKG((float)currentCell.getNumericCellValue());
                            sale.setIloscKG(Float.parseFloat(currentCell.getStringCellValue()));
                            break;
                        case 12:
                            sale.setWartoscNetto(BigDecimal.valueOf(currentCell.getNumericCellValue()).toBigInteger());
                            break;
                        default: break;
                    } listSales.add(sale);
                }
            }
            workBook.close();
            long end = System.currentTimeMillis();
            System.out.printf("Import done in %d ms/n", (end - start));
            return listSales;
        } catch (Exception e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
