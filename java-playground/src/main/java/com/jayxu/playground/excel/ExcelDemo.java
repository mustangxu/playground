/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.excel;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.excel.EasyExcelFactory;

public class ExcelDemo {
    public static void main(String[] args) throws Exception {
        ExcelDemo.doReadNumbers("excel/demo.xlsx", 7).stream()
            .map(o -> Arrays.stream(o).map(BigDecimal::toPlainString)
                .collect(Collectors.joining("\t")))
            .forEach(System.out::println);
    }

    @SuppressWarnings("unchecked")
    private static List<BigDecimal[]> doReadNumbers(String fileName, int limit,
            int... columns) throws IOException {
        try (var is = ExcelDemo.class.getClassLoader()
            .getResourceAsStream(fileName);) {
            var list = EasyExcelFactory.read(is).sheet().doReadSync();

            return list.stream().limit(limit).peek(System.out::println)
                .map(o -> Arrays.stream(columns)
                    .mapToObj(c -> new BigDecimal(
                        ((Map<Integer, String>) o).get(c)).setScale(20, RoundingMode.HALF_UP))
                    .toArray(BigDecimal[]::new))
                .collect(Collectors.toList());
        }
    }
}