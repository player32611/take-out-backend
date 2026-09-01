package com.player32611.utils;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;


/**
 * Excel样式工具类
 */
public class ExcelUtil {


    /**
     * 创建标题样式
     *
     * @param workbook 工作簿
     */
    public static CellStyle createTitleStyle(Workbook workbook){

        CellStyle style =
                workbook.createCellStyle();


        style.setAlignment(
                HorizontalAlignment.CENTER
        );

        style.setVerticalAlignment(
                VerticalAlignment.CENTER
        );


        Font font =
                workbook.createFont();


        font.setFontName("宋体");

        font.setFontHeightInPoints(
                (short)18
        );


        style.setFont(font);


        return style;
    }

    /**
     * 创建区域标题样式
     * 例如：
     * 概览数据
     * 明细数据
     */
    public static CellStyle createSectionStyle(Workbook workbook){

        CellStyle style =
                workbook.createCellStyle();


        style.setAlignment(
                HorizontalAlignment.CENTER
        );


        style.setVerticalAlignment(
                VerticalAlignment.CENTER
        );


        style.setFillForegroundColor(
                IndexedColors.LIGHT_YELLOW.getIndex()
        );


        style.setFillPattern(
                FillPatternType.SOLID_FOREGROUND
        );


        setBorder(style);


        Font font =
                workbook.createFont();


        font.setBold(true);


        style.setFont(font);


        return style;

    }

    /**
     * 创建普通数据样式
     */
    public static CellStyle createDataStyle(Workbook workbook){

        CellStyle style =
                workbook.createCellStyle();


        style.setAlignment(
                HorizontalAlignment.CENTER
        );


        style.setVerticalAlignment(
                VerticalAlignment.CENTER
        );


        setBorder(style);


        return style;
    }

    /**
     * 创建表头样式
     */
    public static CellStyle createHeaderStyle(Workbook workbook){

        CellStyle style =
                workbook.createCellStyle();


        style.setAlignment(
                HorizontalAlignment.CENTER
        );


        style.setVerticalAlignment(
                VerticalAlignment.CENTER
        );


        style.setFillForegroundColor(
                IndexedColors.LIGHT_BLUE.getIndex()
        );


        style.setFillPattern(
                FillPatternType.SOLID_FOREGROUND
        );


        setBorder(style);



        Font font =
                workbook.createFont();


        font.setBold(true);


        style.setFont(font);


        return style;

    }

    /**
     * 设置边框
     */
    private static void setBorder(CellStyle style){

        style.setBorderTop(
                BorderStyle.THIN
        );

        style.setBorderBottom(
                BorderStyle.THIN
        );

        style.setBorderLeft(
                BorderStyle.THIN
        );

        style.setBorderRight(
                BorderStyle.THIN
        );

    }

    /**
     * 创建合并标题
     *
     * @param sheet sheet
     * @param text 内容
     * @param row 行
     * @param startCol 开始列
     * @param endCol 结束列
     */
    public static void createMergeTitle(Sheet sheet, String text, int row, int startCol, int endCol, CellStyle style){

        Row currentRow =
                sheet.createRow(row);


        Cell cell =
                currentRow.createCell(startCol);


        cell.setCellValue(text);


        cell.setCellStyle(style);



        sheet.addMergedRegion(
                new CellRangeAddress(
                        row,
                        row,
                        startCol,
                        endCol
                )
        );

    }

    /**
     * 创建一行数据
     */
    public static void createRow(Sheet sheet, int rowIndex, String[] values, CellStyle style){

        Row row =
                sheet.createRow(rowIndex);



        for(int i=0;i<values.length;i++){

            Cell cell =
                    row.createCell(i);


            cell.setCellValue(
                    values[i]
            );


            cell.setCellStyle(style);

        }

    }

    /**
     * 设置列宽
     */
    public static void setColumnWidth(Sheet sheet, int[] widths){

        for(int i=0;i<widths.length;i++){

            sheet.setColumnWidth(
                    i,
                    widths[i]*256
            );

        }

    }
}