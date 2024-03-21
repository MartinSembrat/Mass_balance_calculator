package MassBalanceCalculator.service;

import MassBalanceCalculator.model.Sale;
import MassBalanceCalculator.model.custom.IRMContentInFG;
import MassBalanceCalculator.repository.ISaleRepository;
import MassBalanceCalculator.helper.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//uses ExcelHelper and ISaleRepository methods to save Excel data to db
@Service
public class ExcelService {
    @Autowired
    ISaleRepository ISaleRepository;

    public void save(MultipartFile file) {
        try {
            List<Sale> sales = ExcelHelper.excelToSales(file.getInputStream());
            ISaleRepository.saveAll(sales);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<Sale> getAllSales() {
        return ISaleRepository.findAll();
    }

    public List<IRMContentInFG> findIRMContentInFG(String index) {
        return ISaleRepository.findIRMContentInFG(index);
    }

    public List<IRMContentInFG> findIRMContentInFGCakes(String index) {
        return ISaleRepository.findIRMContentInFGCakes(index);
    }

    public List<IRMContentInFG> findIRMContentInFillings(String index) {
        return ISaleRepository.findIRMContentInFGFillings(index);
    }

    public List<IRMContentInFG> findIRMContentInProductOverall (String index){
        return Stream.of(
                ISaleRepository.findIRMContentInFG(index),
                ISaleRepository.findIRMContentInFGCakes(index),
                ISaleRepository.findIRMContentInFGFillings(index)
        ).flatMap(Collection::stream).collect(Collectors.toList());
    }
}
