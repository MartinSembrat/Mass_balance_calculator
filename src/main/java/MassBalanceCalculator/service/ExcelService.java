package MassBalanceCalculator.service;
import MassBalanceCalculator.model.Sale;
import MassBalanceCalculator.repository.SaleRepository;
import MassBalanceCalculator.helper.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


//uses ExcelHelper and SaleRepository methods to save Excel data to db
@Service
public class ExcelService {
    @Autowired
    SaleRepository saleRepository;

    public void save(MultipartFile file){
        try {
            List<Sale> sales = ExcelHelper.excelToSales(file.getInputStream());
            saleRepository.saveAll(sales);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public List<Sale> getAllSales(){
        return saleRepository.findAll();
    }
}
