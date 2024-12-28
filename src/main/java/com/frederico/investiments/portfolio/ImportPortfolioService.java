package com.frederico.investiments.portfolio;

import org.springframework.web.multipart.MultipartFile;

public interface ImportPortfolioService {
    void importPortfolio(MultipartFile file, String portfolioName, Long investorId) throws Exception;

}
