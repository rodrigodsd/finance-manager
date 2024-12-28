package com.frederico.investiments.portfolio;

import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

public interface ImportPortfolioService {
    HashMap<String, Integer> importPortfolio(MultipartFile file, String portfolioName, Long investorId) throws Exception;

}
