package com.frederico.investiments.portfolio;

import com.frederico.investiments.portfolio.domain.Portfolio;
import com.frederico.investiments.portfolio.domain.Position;
import io.micrometer.common.util.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.util.Tuple;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelParserUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelParserUtil.class);

    private ExcelParserUtil() {
    }

    public static Tuple<List<Position>, HashMap<String, Integer>> parseExcelFile(InputStream is, Portfolio portfolio) throws IOException {
        Workbook workbook = WorkbookFactory.create(is);
        List<Position> positions = new ArrayList<>();
        var sheets = workbook.iterator();
        var counter = new HashMap<String, Integer>();
        long start = System.currentTimeMillis();

        while (sheets.hasNext()) {

            Sheet sheet = sheets.next();
            String sheetName = sheet.getSheetName();

            PositionMapper.findByName(sheetName).ifPresentOrElse(it -> {
                var rowCount = 0;
                for (Row row : sheet) {
                    if (row.getRowNum() == 0) {
                        continue; // Skip header row
                    }

                    var position = it.parse(row, portfolio);

                    if (StringUtils.isEmpty(position.code()))
                        continue;

                    rowCount++;
                    positions.add(position);

                }
                counter.put(sheetName, rowCount);
                rowCount = 0;
            }, () -> logger.info("The sheet {} is not supported", sheetName));
        }

        workbook.close();

        logger.info("method=parseExcelFile, module=asset, message=, portfolio={}, elapse_time={}", portfolio.id(), System.currentTimeMillis() - start);

        return new Tuple<>(positions, counter);
    }


}
