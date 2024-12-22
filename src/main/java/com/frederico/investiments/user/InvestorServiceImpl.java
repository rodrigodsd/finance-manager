package com.frederico.investiments.user;

import com.frederico.investiments.user.domain.Investor;
import org.springframework.stereotype.Service;

@Service
public class InvestorServiceImpl implements InvestorService {

    private InvestorRepository investorRepository;

    public InvestorServiceImpl(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    @Override
    public Investor getInvestor(Long id) {
        return investorRepository.findById(id).orElseThrow();
    }

    @Override
    public Investor getInvestor(Integer personalId) {
        return investorRepository.findByPersonalId(personalId).orElseThrow();
    }

    @Override
    public Investor saveInvestor(Investor investor) {
        return investorRepository.save(investor);
    }

    @Override
    public void deleteInvestor(Long id) {
        investorRepository.deleteById(id);
    }
}
