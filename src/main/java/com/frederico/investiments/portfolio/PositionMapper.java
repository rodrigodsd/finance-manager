package com.frederico.investiments.portfolio;

import com.frederico.investiments.portfolio.domain.Portfolio;
import com.frederico.investiments.portfolio.domain.Position;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import java.math.BigDecimal;

public enum PositionMapper {
    ASSET("Acoes"),
    BDR("BDR"),
    ETF("ETF"),
    FII("Fundo de Investimento"),
    FIXED_INCOME("Renda Fixa"){
        @Override
        public Position mapper(Row row, Portfolio portfolio) {
            return new Position(null, portfolio.id(),
                    getCellValueAsString(row.getCell(3)),
                    null,
                    getCellValueAsDouble(row.getCell(8)),null);
        }
    },
    TREASURIES("Tesouro Direto"){
        @Override
        public Position mapper(Row row, Portfolio portfolio) {
            return new Position(null, portfolio.id(),
                    getCellValueAsString(row.getCell(2)),
                    null,
                    getCellValueAsDouble(row.getCell(5)),null);
        }
    };

    private String assetType;

    PositionMapper(String sheetName) {
        assetType = sheetName;
    }

    //mapper for variable income asset
    public Position mapper(Row row, Portfolio portfolio){
      return new Position(null, portfolio.id(),
              getCellValueAsString(row.getCell(3)),
              getCellValueAsString(row.getCell(5)),
              getCellValueAsDouble(row.getCell(8)),null);
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

    public String getAssetType() {
        return assetType;
    }
}
