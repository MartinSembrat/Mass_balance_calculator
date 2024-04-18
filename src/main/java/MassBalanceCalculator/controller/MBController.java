package MassBalanceCalculator.controller;

import MassBalanceCalculator.model.Sale;
import MassBalanceCalculator.model.custom.IRMContentInFG;
import MassBalanceCalculator.service.MBService;
import MassBalanceCalculator.helper.ExcelHelper;
import MassBalanceCalculator.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
@RequestMapping("/api")
public class MBController {
    @Autowired
    MBService fileService;

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

    @GetMapping("/displayindexes")
    public ResponseEntity<List<String>> getAllRMIndexes() {
        try {
            List<String> indexes = fileService.getAllRMIndexes();
            if (indexes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(indexes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getIndexoccurs")
    public ResponseEntity<Map<String, Integer>> getIndexOccurs() {
        try {
            Map<String, Integer>  getIndexOccurs = fileService.getIndexOccurs();
            if (getIndexOccurs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(getIndexOccurs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rmcontentinfg")
    public ResponseEntity<List<IRMContentInFG>> getRMContentInFG(@RequestParam String index) {
        try {
            List<IRMContentInFG> irmContentInFG = fileService.findIRMContentInFG(index);
            if (irmContentInFG.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(irmContentInFG, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rmcontentinfgcakes")
    public ResponseEntity<List<IRMContentInFG>> findIRMContentInFGCakes(@RequestParam String index) {
        try {
            List<IRMContentInFG> irmContentInFGCakes = fileService.findIRMContentInFGCakes(index);
            if (irmContentInFGCakes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(irmContentInFGCakes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rmcontentinfgfillings")
    public ResponseEntity<List<IRMContentInFG>> findIRMContentInFillings(@RequestParam String index) {
        try {
            List<IRMContentInFG> irmContentInFillings = fileService.findIRMContentInFillings(index);
            if (irmContentInFillings.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(irmContentInFillings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rmcontentinfgsum")
    public ResponseEntity<List<IRMContentInFG>> findIRMContentInProductOverall(@RequestParam String index) {
        try {
            List<IRMContentInFG> irmContentInFillings = fileService.findIRMContentInProductOverall(index);
            if (irmContentInFillings.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(irmContentInFillings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/salerminfg")
    public ResponseEntity<List<IRMContentInFG>> findIRMContentInProductOverallMultiplyBySalesVolume(@RequestParam String index) {
        try {
            List<IRMContentInFG> findIRMContentInProductOverallMultiplyBySalesVolume = fileService.findIRMContentInProductOverallMultiplyBySalesVolume(index);
            if (findIRMContentInProductOverallMultiplyBySalesVolume.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(findIRMContentInProductOverallMultiplyBySalesVolume, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}