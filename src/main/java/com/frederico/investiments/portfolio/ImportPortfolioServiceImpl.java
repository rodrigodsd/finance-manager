package com.frederico.investiments.portfolio;

import com.frederico.investiments.portfolio.domain.Portfolio;
import com.frederico.investiments.portfolio.domain.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.util.Tuple;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;

@Service
public class ImportPortfolioServiceImpl implements ImportPortfolioService {

    private static final Logger logger = LoggerFactory.getLogger(ImportPortfolioServiceImpl.class);

    @Value("${app.dir.import}")
    private String importPath;

    private final PortfolioService portfolioService;

    private final PositionService positionService;

    public ImportPortfolioServiceImpl(PortfolioService portfolioService, PositionService positionService) {
        this.portfolioService = portfolioService;
        this.positionService = positionService;
    }

    @Override
    public HashMap<String, Integer> importPortfolio(MultipartFile file, String portfolioName, Long investorId) throws Exception {

        // It puts the file in server directory
        //uploadFile(file);

        // Create Portfolio
        var portfolio = portfolioService.save(new Portfolio(null, investorId, portfolioName, null));

        // Parse file to Positions
        Tuple<List<Position>, HashMap<String, Integer>> tupleResult;
        try (InputStream is = file.getInputStream()) {
            tupleResult = ExcelParserUtil.parseExcelFile(is, portfolio);
            List<Position> positions = tupleResult._1();
            var results = positions.stream()
                    .map(positionService::save)
                    .toList();
            logger.info("{}", results.size());
        } catch (IOException e) {
            logger.error("IOException occurred while processing file", e);
            throw new IOException(e.getMessage(), e);
        }

        return tupleResult._2();

    }

    private void uploadFile(MultipartFile file) {
        try {
            Path copyLocation = Paths.get(importPath + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
    }
}
