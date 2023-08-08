package helper;

import MassBalanceCalculator.model.Sale;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
        List<Sale> listSales = new ArrayList<>();
        long start = System.currentTimeMillis();
        try {
            Workbook workBook = new XSSFWorkbook(is);
            Sheet firstSheet = workBook.getSheet(SHEET);
            Iterator<Row> rowIterator = firstSheet.iterator();
            //Omit column names in first row
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row currentRow = rowIterator.next();
                Sale sale = new Sale();
                Iterator<Cell> cellIterator = currentRow.cellIterator();
                int rowIndex = currentRow.getRowNum();

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
                            sale.setIlosc(BigDecimal.valueOf(currentCell.getNumericCellValue()).toBigInteger());
                            break;
                        case 11:
                            sale.setIloscKG(BigDecimal.valueOf(currentCell.getNumericCellValue()).toBigInteger());
                            break;
                        case 12:
                            sale.setWartoscNetto((float) currentCell.getNumericCellValue());
                            break;
                    } listSales.add(sale);
                }
            }
            workBook.close();
            long end = System.currentTimeMillis();
            System.out.printf("Import done in %d ms/n", (end - start));
        } catch (Exception e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
        return listSales;
    }


}
