package com.frederico.investiments.user;

import com.frederico.investiments.user.domain.Investor;

public interface InvestorService {

    public Investor getInvestor(Long id);
    public Investor getInvestor(Integer personal_id);
    Investor saveInvestor(Investor investor);
    void deleteInvestor(Long id);
}
