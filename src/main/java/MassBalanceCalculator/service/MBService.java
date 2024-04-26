package MassBalanceCalculator.service;

import MassBalanceCalculator.model.Sale;
import MassBalanceCalculator.model.custom.IRMContentInFG;
import MassBalanceCalculator.repository.ISaleRepository;
import MassBalanceCalculator.helper.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//uses ExcelHelper and ISaleRepository methods to save Excel data to db
@Service
public class MBService {
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

    public List<String> getAllRMIndexes() {
        return ISaleRepository.findAllRMIndexes();
    }

    public Map<String, Integer> getIndexOccurs() {
        List<String> allRMIndexes = getAllRMIndexes();

        Map<String, Integer> resultMap = allRMIndexes.stream()
                .collect(Collectors.toMap(
                        index -> index,
                        index -> {
                            try {
                                return findIRMContentInProductOverall(index).size();
                            } catch (Exception e) {
                                // Handle exception, for now, set value to 0
                                e.printStackTrace();
                                return 0;
                            }
                        },
                        // Merge function to handle duplicate keys
                        (existingValue, newValue) -> existingValue
                ));
        return resultMap;
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

    public List<IRMContentInFG> findIRMContentInProductOverall(String index) {
        return Stream.of(
                ISaleRepository.findIRMContentInFG(index),
                ISaleRepository.findIRMContentInFGCakes(index),
                ISaleRepository.findIRMContentInFGFillings(index)
        ).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public List<IRMContentInFG> findIRMContentInProductOverallMultiplyBySalesVolume(String index) {
        List<IRMContentInFG> updatedList = findIRMContentInProductOverall(index).stream()
                .flatMap(irm -> {
                    Sale sale = getAllSales().stream()
                            .filter(s -> s.getIndeks().equals(irm.getIndeks_CMJ()))
                            .findFirst()
                            .orElse(null);
                    if (sale != null) {
                        return Stream.of(new IRMContentInFG() {
                            @Override
                            public String getIndeks_CMJ() {
                                return irm.getIndeks_CMJ();
                            }

                            @Override
                            public String getIndex() {
                                return irm.getIndex();
                            }

                            @Override
                            public String getNazwa_wyrobu() {
                                return irm.getNazwa_wyrobu();
                            }

                            @Override
                            public String getReceptura() {
                                return null;
                            }

                            @Override
                            public Float getAmount_for_primary() {
                                return irm.getAmount_for_primary() * sale.getIlosc();
                            }

                            @Override
                            public int getID_wyrobu() {
                                return irm.getID_wyrobu();
                            }
                        });
                    } else {
                        return Stream.empty(); // Skip null values
                    }
                })
                .collect(Collectors.toList());
        return updatedList;
    }
}
