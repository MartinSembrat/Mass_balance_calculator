package MassBalanceCalculator.controller;

import MassBalanceCalculator.model.Sale;
import MassBalanceCalculator.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/web")
public class HomeController {
    @RequestMapping("/home")
    public String homePage(){
        return "HomePage";
    }

    @Autowired
    private SaleRepository saleRepository;

    @GetMapping("/import/excel")
    @ResponseBody
    public void exportToExcel (HttpServletResponse response){
        response.setContentType("application/stream");
        String headerKey = "Content-disposition";
        String headervalue = "attachment; filename=Sale_info.xlsx";

        response.setHeader(headerKey,headervalue);
        List<Sale> salesList = saleRepository.findAll();


    }


}
