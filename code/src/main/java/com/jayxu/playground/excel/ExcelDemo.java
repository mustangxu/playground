package com.jayxu.playground.excel;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.excel.EasyExcelFactory;

public class ExcelDemo {
	public static void main(String[] args) {
//        doReadNumbers("demo.xlsx", 7).forEach(System.out::println);

		ExcelDemo.doReadNumbers("excel/11-BCHA.xlsx", 50, 4, 5).stream()
				.map(o -> Arrays.stream(o).map(BigDecimal::toPlainString)
						.collect(Collectors.joining("\t")))
				.forEach(System.out::println);
	}

	@SuppressWarnings("unchecked")
	private static List<BigDecimal[]> doReadNumbers(String fileName, int limit,
			int... columns) {
		var list = EasyExcelFactory.read(
				ExcelDemo.class.getClassLoader().getResourceAsStream(fileName))
				.sheet().doReadSync();

		return list.stream().limit(limit).peek(System.out::println)
				.map(o -> Arrays.stream(columns)
						.mapToObj(c -> new BigDecimal(
								((Map<Integer, String>) o).get(c)).setScale(20))
						.toArray(BigDecimal[]::new))
				.collect(Collectors.toList());
	}
}