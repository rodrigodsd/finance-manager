package com.frederico.investiments.portfolio;

import com.frederico.investiments.portfolio.domain.Portfolio;
import com.frederico.investiments.portfolio.domain.Position;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelParserUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelParserUtil.class);

    private ExcelParserUtil(){}

    public static List<Position> parseExcelFile(InputStream is, Portfolio portfolio) throws IOException {
        List<Position> positions = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(is);
        long start = System.currentTimeMillis();
        var sheets = workbook.iterator();

        Map<String, Integer> counter = new HashMap<>();

        while (sheets.hasNext()){

            Sheet next = sheets.next();
            for (Row row : next) {
                if (row.getRowNum() == 0) {
                    continue; // Skip header row
                }

                positions.add(PositionMapper.valueOf(next.getSheetName()).mapper(row, portfolio));
            }

            counter.put(next.getSheetName(), next.getLastRowNum());
        }

        workbook.close();
        logger.info("method=parseExcelFile, module=asset, message=, portfolio={}, elapse_time={}", portfolio.id(), System.currentTimeMillis() - start);
        return positions;
    }


}
