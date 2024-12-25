package com.frederico.investiments.user;

import com.frederico.investiments.user.domain.Investor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/investor")
public class InvestorController {

    private final InvestorService investorService;

    public InvestorController(InvestorService investorService) {
        this.investorService = investorService;
    }

    @PostMapping("/signin")
    public Investor saveInvestor(@RequestBody Investor investor){
        return investorService.saveInvestor(investor);
    }

    @GetMapping("{id}")
    public Investor getInvestor(@PathVariable Long id){
        return  investorService.getInvestor(id);
    }
}
