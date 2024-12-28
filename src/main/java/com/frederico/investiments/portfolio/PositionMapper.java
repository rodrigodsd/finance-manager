package com.frederico.investiments.portfolio;

import com.frederico.investiments.portfolio.domain.Portfolio;
import com.frederico.investiments.portfolio.domain.Position;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

public enum PositionMapper {
    ASSET("Acoes"),
    BDR("BDR") {
        @Override
        public Position parse(Row row, Portfolio portfolio) {
            var isin = getCellValueAsString(row.getCell(4));
            return new Position(null, portfolio.id(),
                    getCellValueAsString(row.getCell(3)),
                    StringUtils.isNoneEmpty(isin) ? StringUtils.left(isin, 12) : "",
                    getCellValueAsDouble(row.getCell(8)), null);
        }
    },
    ETF("ETF"),
    FII("Fundo de Investimento"),
    FIXED_INCOME("Renda Fixa") {
        @Override
        public Position parse(Row row, Portfolio portfolio) {
            return new Position(null, portfolio.id(),
                    getCellValueAsString(row.getCell(3)),
                    null,
                    getCellValueAsDouble(row.getCell(8)), null);
        }
    },
    TREASURIES("Tesouro Direto") {
        @Override
        public Position parse(Row row, Portfolio portfolio) {
            return new Position(null, portfolio.id(),
                    getCellValueAsString(row.getCell(2)),
                    null,
                    getCellValueAsDouble(row.getCell(5)), null);
        }
    };

    private final String assetName;

    PositionMapper(String sheetName) {
        assetName = sheetName;
    }

    public static Optional<PositionMapper> findByName(String name) {
        return Arrays.stream(values()).filter(mapper -> mapper.getAssetName().equalsIgnoreCase(name)).findFirst();
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getRichStringCellValue().getString().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString(); // Adjust date format if needed
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    private static BigDecimal getCellValueAsDouble(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                return BigDecimal.valueOf(cell.getNumericCellValue());
            case STRING:
                try {
                    return BigDecimal.valueOf(Double.parseDouble(cell.getRichStringCellValue().getString().trim()));
                } catch (NumberFormatException e) {
                    return null; // Handle if the string cannot be parsed as double
                }
            default:
                return null;
        }
    }

    //mapper for variable income asset
    public Position parse(Row row, Portfolio portfolio) {
        var isin = getCellValueAsString(row.getCell(5));
        return new Position(null, portfolio.id(),
                getCellValueAsString(row.getCell(3)),
                StringUtils.isNoneEmpty(isin) ? StringUtils.left(isin, 12) : "",
                getCellValueAsDouble(row.getCell(8)), null);
    }

    private String getAssetName() {
        return assetName;
    }
}
