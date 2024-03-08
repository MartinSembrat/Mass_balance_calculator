package MassBalanceCalculator.controller;

import MassBalanceCalculator.model.Sale;
import MassBalanceCalculator.model.custom.IRMContentInFG;
import MassBalanceCalculator.model.custom.IRMContentInIndex;
import MassBalanceCalculator.service.ExcelService;
import MassBalanceCalculator.helper.ExcelHelper;
import MassBalanceCalculator.message.ResponseMessage;
//import MassBalanceCalculator.service.RMContentInIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/api/excel")
public class ExcelController {

    @Autowired
    ExcelService fileService;
//    RMContentInIndexService rmContentInIndexService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                fileService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";

                // to be removed
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/sales")
    public ResponseEntity<List<Sale>> getAllSales() {
        try {
            List<Sale> sales = fileService.getAllSales();

            if (sales.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(sales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sales2")
    public ResponseEntity<List<Sale>> getSalesByJM() {
        try {
            List<Sale> sales = fileService.getSalesByJM();

            if (sales.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(sales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rmcontentinindex")
    public ResponseEntity<List<IRMContentInIndex>> getRMContentInIndex() {
        try {
            List<IRMContentInIndex> irmContentInIndexList2 = fileService.findIRMContentInIndex();
            if (irmContentInIndexList2.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(irmContentInIndexList2, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rmcontentinfg")
    public ResponseEntity<List<IRMContentInFG>> getRMContentInFG() {
        try {
            List<IRMContentInFG> irmContentInFG = fileService.findIRMContentInFG();
            if (irmContentInFG.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(irmContentInFG, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rmcontentinfgcakes")
    public ResponseEntity<List<IRMContentInFG>> findIRMContentInFGCakes() {
        try {
            List<IRMContentInFG> irmContentInFGCakes = fileService.findIRMContentInFGCakes();
            if (irmContentInFGCakes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(irmContentInFGCakes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rmcontentinfgfillings")
    public ResponseEntity<List<IRMContentInFG>> findIRMContentInFillings() {
        try {
            List<IRMContentInFG> irmContentInFillings = fileService.findIRMContentInFillings();
            if (irmContentInFillings.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(irmContentInFillings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
