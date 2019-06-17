package com.deloitte.services.fssc.util.excel;

import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.services.fssc.eums.FsscErrorType;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Excel导出工具,支持合并表头 设计思路 sheet = Title + Header + Data
 *
 * @author jawjiang
 */
public class MultipleExcelExportUtil<T> {

    private static final Logger logger = LoggerFactory.getLogger(MultipleExcelExportUtil.class);

    static final String DEFAULT_FONT_NAME = "Calibri";

    static final String DEFAULT_SHEET_NAME = "sheet1";

    /**
     * 生成的文件名
     */
    private String fileName;

    private String title;

    /**
     * 标题高度,默认为0
     */
    private Integer titleHeight = 0;

    /**
     * 支持多行的表头,最下一层header,必须是width、height都是1
     */
    private List<List<ExcelHeader>> headersList;

    private List<Object[]> datasList;

    private List<T> beanList;

    private List<List<Object>> dataListList;

    private Object[][] data2Array;

    private HttpServletResponse response;

    /**
     * 头部第一行宽度
     */
    private Integer firstHeaderWidth = 0;

    /**
     * 头部中使用的最大高度
     */
    private Integer maxHeaderHeight = 0;

    /**
     * 是否锁定sheet
     */
    private boolean isLockSheet = false;

    /**
     * 不需要锁定的列索引集合
     */
    private List<Integer> unLockColumnIndexList = new ArrayList<>();

    /**
     * 需要锁定的列索引集合
     */
    private List<Integer> lockColumnIndexList = new ArrayList<>();

    private List<Integer> columnIndexList = new ArrayList<>();

    private Map<Integer, ExcelHeader> columnHeaderMap = new HashMap<>();

    /**
     * 每行header的最小高度
     */

    private Map<Integer, Integer> headerMinSizeMap = new HashMap<>();

    public static MultipleExcelExportUtil getSimpleInstance(String[] columns,
            HttpServletResponse response) {
        MultipleExcelExportUtil exportUtil = new MultipleExcelExportUtil();
        exportUtil.headersList = ExcelHeader.getDefaultHeadersList(columns);
        exportUtil.response = response;
        exportUtil.setHeaderWidthAndHeight(exportUtil.headersList);
        exportUtil.setHeaderXYPosition();
        return exportUtil;
    }

    public static MultipleExcelExportUtil getSimpleInstance(List<String> columnList,
            HttpServletResponse response) {
        MultipleExcelExportUtil exportUtil = new MultipleExcelExportUtil();
        exportUtil.headersList = ExcelHeader.getDefaultHeadersList(columnList);
        exportUtil.response = response;
        exportUtil.setHeaderWidthAndHeight(exportUtil.headersList);
        exportUtil.setHeaderXYPosition();
        return exportUtil;
    }

    public static MultipleExcelExportUtil getSimpleInstance2(List<ExcelHeader> headerList,
            HttpServletResponse response) {
        MultipleExcelExportUtil exportUtil = new MultipleExcelExportUtil();
        exportUtil.headersList = ExcelHeader.getHeadersList(headerList);
        exportUtil.response = response;
        exportUtil.setHeaderWidthAndHeight(exportUtil.headersList);
        exportUtil.setHeaderXYPosition();
        return exportUtil;
    }

    public static MultipleExcelExportUtil getDefaultInstance(List<String> columnList,
            List<Object[]> datasList, HttpServletResponse response) {
        MultipleExcelExportUtil exportUtil = new MultipleExcelExportUtil();
        exportUtil.headersList = ExcelHeader.getDefaultHeadersList(columnList);
        exportUtil.datasList = datasList;
        exportUtil.response = response;
        exportUtil.setHeaderWidthAndHeight(exportUtil.headersList);
        exportUtil.setHeaderXYPosition();
        return exportUtil;
    }

    public static MultipleExcelExportUtil getDefaultInstance(String title, List<String> columnList,
            List<Object[]> datasList, HttpServletResponse response) {
        MultipleExcelExportUtil exportUtil = new MultipleExcelExportUtil();
        exportUtil.title = title;
        exportUtil.titleHeight = 1;
        exportUtil.headersList = ExcelHeader.getDefaultHeadersList(columnList);
        exportUtil.datasList = datasList;
        exportUtil.response = response;
        exportUtil.setHeaderWidthAndHeight(exportUtil.headersList);
        exportUtil.setHeaderXYPosition();
        return exportUtil;
    }

    public static MultipleExcelExportUtil getInstance(List<List<ExcelHeader>> headersList,
            HttpServletResponse response) {
        MultipleExcelExportUtil exportUtil = new MultipleExcelExportUtil();
        exportUtil.headersList = headersList;
        exportUtil.response = response;
        exportUtil.setHeaderWidthAndHeight(exportUtil.headersList);
        exportUtil.setHeaderXYPosition();
        return exportUtil;
    }

    public static MultipleExcelExportUtil getInstance(String title,
            List<List<ExcelHeader>> headersList,
            HttpServletResponse response) {
        MultipleExcelExportUtil exportUtil = new MultipleExcelExportUtil();
        exportUtil.title = title;
        exportUtil.titleHeight = 1;
        exportUtil.headersList = headersList;
        exportUtil.response = response;
        exportUtil.setHeaderWidthAndHeight(exportUtil.headersList);
        exportUtil.setHeaderXYPosition();
        return exportUtil;
    }

    private MultipleExcelExportUtil() {

    }


    private void setHeaderWidthAndHeight(List<List<ExcelHeader>> headersList) {
        if (CollectionUtils.isEmpty(headersList)) {
            throw new ExcelException("Excel头部信息不为空");
        }
        this.firstHeaderWidth = 0;
        this.maxHeaderHeight = 0;
        for (int i = 0; i < headersList.size(); i++) {
            List<ExcelHeader> headers = headersList.get(i);
            Integer rowHeaderWidth = 0;
            for (ExcelHeader header : headers) {
                if (header.getWidth() == null || header.getWidth() < 1) {
                    throw new ExcelException("必须设置列宽,至少大于等于1");
                }
                rowHeaderWidth += header.getWidth();
                if (header.getHeight() == null || header.getHeight() < 1) {
                    throw new ExcelException("必须设置高度,至少大于等于1");
                }
                if (header.getHeight() > this.maxHeaderHeight) {
                    this.maxHeaderHeight = header.getHeight();
                }
                //最低层的header,宽度必须为1
                if (i == headersList.size() - 1 && header.getWidth() != 1) {
                    throw new ExcelException("最低层的header,宽度只能为1");
                }
            }
            if (i == 0) {
                this.firstHeaderWidth = rowHeaderWidth;
            }
        }
    }

    /**
     * 设置每个Header的相应坐标 算法：从title后开始,如果只有1行Header根据width、height正常设置即可, 如果有header只是进行了宽度合并,也按照正常设置即可，
     * 如果有header进行了高度合并,再设置下一行的header坐标时,必须从上一行最新的height, 开始找到没有被占用的Y轴位置,如果新的header宽度超时1时,必须在全部宽度都能容纳的
     * 位置才可以分配，否则继续往后寻找，有2个终止条件 a. 直到找到合适位置 b.已经开始从超过上一行宽度的位置寻找合适的高度
     */
    private void setHeaderXYPosition() {
        //header接在title后开始
        int firstHeaderBeginIndexY = titleHeight;
        //存储头部信息中每列已经使用的Y轴坐标
        Map<Integer, Integer> headerXTakeUpYMap = new HashMap<>();
        //Y轴开始位置
        int beginIndexOfY = firstHeaderBeginIndexY;
        for (int i = 0; i < headersList.size(); i++) {
            List<ExcelHeader> headers = headersList.get(i);
            //每行X轴开始位置
            int beginIndexOfX = 0;
            //最小高度
            int preMinHeight = 0;
            //每行Header的高度集合
            List<Integer> rowHeightList = new ArrayList();
            for (int j = 0; j < headers.size(); j++) {
                ExcelHeader header = headers.get(j);
                //预计占用的Y轴结束位置
                int predictEndIndexOfY = beginIndexOfY + header.getHeight() - 1;
                for (int k = 0; k < header.getWidth(); k++) {
                    //预计占用的X轴位置
                    int predictEndIndexOfX = beginIndexOfX + k;
                    //Header 第一行,存储坐标信息
                    if (i == 0) {
                        headerXTakeUpYMap.put(predictEndIndexOfX, predictEndIndexOfY);
                    } else {
                        Integer existsTakeUpY = headerXTakeUpYMap.get(predictEndIndexOfX);
                        if (existsTakeUpY == null) {
                            //如果找不到相应Y轴位置,一定是超过了上一行的宽度
                            throw new ExcelException("经过自动适应高宽度后,第" + (i + 1) + "头部行宽度超过上一行宽度");
                        }
                        if (beginIndexOfY < existsTakeUpY || predictEndIndexOfY < existsTakeUpY) {
                            //没有适应到合适X坐标位置,需往后顺移一位重新再寻找合适位置,
                            beginIndexOfX++;
                            k--;
                            continue;
                        }
                        if (k == header.getWidth() - 1) {
                            //找到合适位置
                        }
                    }
                }
                //重置占用空间
                for (int p = 0; p < header.getWidth(); p++) {
                    int predictEndIndexOfX = beginIndexOfX + p;
                    Integer existsTakeUpY = headerXTakeUpYMap.get(predictEndIndexOfX);
                    headerXTakeUpYMap.put(predictEndIndexOfX, existsTakeUpY + 1);
                    if (header.isLock() && !lockColumnIndexList.contains(predictEndIndexOfX)) {
                        lockColumnIndexList.add(predictEndIndexOfX);
                    }
                    if (!columnIndexList.contains(predictEndIndexOfX)) {
                        columnIndexList.add(predictEndIndexOfX);
                        columnHeaderMap.put(predictEndIndexOfX, header);
                    }
                }
                int endIndexOfX = beginIndexOfX + header.getWidth() - 1;
                int endIndexOfY = beginIndexOfY + header.getHeight() - 1;
                // 设置坐标
                header.setStartX(beginIndexOfX);
                header.setEndX(endIndexOfX);
                header.setStartY(beginIndexOfY);
                header.setEndY(endIndexOfY);
                rowHeightList.add(header.getHeight());
                //重置X轴起始位置
                beginIndexOfX = endIndexOfX + 1;
            }
            Collections.sort(rowHeightList);
            preMinHeight = rowHeightList.get(0);
            headerMinSizeMap.put(i, preMinHeight);
            //重置Y轴起始位置
            beginIndexOfY += preMinHeight;
        }
        unLockColumnIndexList = new ArrayList<>(columnIndexList);
        unLockColumnIndexList.removeAll(lockColumnIndexList);
    }

    public void exportExcel() {
        if (ArrayUtils.isEmpty(data2Array) && CollectionUtils.isEmpty(beanList) && CollectionUtils.isEmpty(dataListList)
                && CollectionUtils.isEmpty(datasList)) {
            //throw new ServiceException(FsscErrorType.EXPORT_DATA_SOURCE_EMPTY);
        }
        SXSSFWorkbook swb;
        try (OutputStream out = response.getOutputStream()) {
            swb = new SXSSFWorkbook(1000);
            SXSSFSheet sheet = null;
            if (StringUtils.isNotBlank(fileName)) {
                sheet = swb.createSheet(fileName);
            } else {
                sheet = swb.createSheet(DEFAULT_SHEET_NAME);
            }
            if (StringUtils.isNotBlank(title) && titleHeight > 0) {
                this.createExcelTitle(swb, sheet);
            }
            sheet.trackAllColumnsForAutoSizing();
            /** sheet样式定义 **/
            // 头样式
            CellStyle headerStyle = this.getTitleStyle(swb);
            CellStyle normalContentStyle = this.getContentStyle(swb);
            // 非锁定单元格样式
            CellStyle unLockContentStyle = this.getContentStyle(swb);
            unLockContentStyle.setLocked(false);
            //锁定的单元格样式
            CellStyle lockContentStyle = this.getLockContentStyle(swb);
            /** 生成header **/
            //header接在title后开始
            int headerHeightIndex = titleHeight;
            for (int i = 0; i < headersList.size(); i++) {
                List<ExcelHeader> headers = headersList.get(i);
                // 创建header行
                SXSSFRow headerRow = sheet.createRow(headerHeightIndex);
                CellStyle headerCellStyle = swb.createCellStyle();
                headerCellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
                headerRow.setRowStyle(headerCellStyle);
                for (ExcelHeader header : headers) {
                    SXSSFCell headerCell = headerRow.createCell(header.getStartX());
                    if (header.getStartX() == header.getEndX() && header.getStartY() == header
                            .getEndY()) {
                        //如果宽度和高度都为1,不做合并处理
                    } else {
                        CellRangeAddress region = new CellRangeAddress(header.getStartY(),
                                header.getEndY(),
                                header.getStartX(), header.getEndX());
                        sheet.addMergedRegion(region);
                        setBorderStyle(BorderStyle.THIN.getCode(), region, sheet);
                    }
                    RichTextString text = new XSSFRichTextString(header.getColumnName());
                    headerCell.setCellValue(text);
                    headerStyle.setAlignment(header.getAlign());
                    headerCell.setCellStyle(headerStyle);
                    headerCell.setCellType(CellType.STRING);
                }
                headerHeightIndex += (headerMinSizeMap.get(i));
            }
            for (Integer columnIndex : columnIndexList) {
                sheet.setDefaultColumnStyle(columnIndex, unLockContentStyle);
            }
            this.createCellData(sheet, normalContentStyle, unLockContentStyle, lockContentStyle);
            //自适应宽度
//            for (Integer columnIndex : columnIndexList) {
//                sheet.autoSizeColumn(columnIndex);
//            }
            //个性化设置宽度
            for (Integer columnIndex : columnIndexList) {
                if (columnHeaderMap.get(columnIndex) != null) {
                    ExcelHeader header = columnHeaderMap.get(columnIndex);
                    if (header.getOneCellWidth() != null) {
                        sheet.setColumnWidth(columnIndex, header.getOneCellWidth());
                    }
                }
            }
            if (isLockSheet) {
                sheet.protectSheet("123456");
            }
            if (CollectionUtils.isNotEmpty(beanList)) {
                beanList.clear();
            }
            if (CollectionUtils.isNotEmpty(dataListList)) {
                dataListList.clear();
            }
            if (CollectionUtils.isNotEmpty(datasList)) {
                datasList.clear();
            }
            if (swb != null) {
                this.fileName = (StringUtils.isNotBlank(this.fileName) ? this.fileName : DEFAULT_SHEET_NAME) + ".xls";
                // 设置编码格式 防止乱码
                String fileNameCoded = URLEncoder.encode(this.fileName, "UTF-8");
                // filename为全文件名
                String headStr = "attachment; filename=\"" + fileNameCoded + "\"";
                /**
                 * response.setContentType(MIME)的作用是使客户端浏览器， 区分不同种类的数据，
                 *  并根据不同的MIME调用浏览器内不同的程序嵌入模块来处理相应的数据。
                 * 设置MIME的类型为APPLICATION/OCTET-STREAM response.setHeader()解决下载中文文件名乱码问题
                 */
                response.setContentType("application/octet-stream;charset=UTF-8");
                // 返回 文件全名
                response.setHeader("Content-Disposition", headStr);
                // 写入文档
                swb.write(out);
                out.flush();
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void createCellData(SXSSFSheet sheet, CellStyle normalContentStyle,
            CellStyle unLockContentStyle,
            CellStyle lockContentStyle) {
        if (ArrayUtils.isNotEmpty(data2Array)){
            for (int i = 0; i < data2Array.length; i++) {
                logger.debug("{}行", i);
                Object[] objects = data2Array[i];
                SXSSFRow row = sheet.createRow(i + maxHeaderHeight + titleHeight);
                this.createOneRowCell(normalContentStyle, unLockContentStyle, lockContentStyle,
                        objects, row);
            }
        }

        if (CollectionUtils.isNotEmpty(beanList)) {
            for (int i = 0; i < beanList.size(); i++) {
                List<Field> fieldList = this.getExcelField(beanList.get(0).getClass());
                logger.debug("{}行", i);
                // 遍历每个对象
                T bean = beanList.get(i);
                //跳过表头和标题,创建所需的行数
                SXSSFRow row = sheet.createRow(i + maxHeaderHeight + titleHeight);
                Object[] objects = convertObjectArray(bean, fieldList);
                this.createOneRowCell(normalContentStyle, unLockContentStyle, lockContentStyle,
                        objects, row);
            }
        } else if (CollectionUtils.isNotEmpty(dataListList)) {
            for (int i = 0; i < dataListList.size(); i++) {
                logger.debug("{}行", i);
                List<Object> dataList = dataListList.get(i);
                SXSSFRow row = sheet.createRow(i + maxHeaderHeight + titleHeight);
                Object[] objects = dataList.toArray();
                this.createOneRowCell(normalContentStyle, unLockContentStyle, lockContentStyle,
                        objects, row);
            }
        } else if (CollectionUtils.isNotEmpty(datasList)) {
            for (int i = 0; i < datasList.size(); i++) {
                logger.debug("{}行", i);
                Object[] objects = datasList.get(i);
                SXSSFRow row = sheet.createRow(i + maxHeaderHeight + titleHeight);
                this.createOneRowCell(normalContentStyle, unLockContentStyle, lockContentStyle,
                        objects, row);
            }
        }
    }

    /**
     * 创建整行的单元格
     */
    private void createOneRowCell(CellStyle normalContentStyle, CellStyle unLockContentStyle,
            CellStyle lockContentStyle,
            Object[] objects, SXSSFRow row) {
        for (int j = 0; j < objects.length; j++) {
            // 设置单元格的数据类型
            SXSSFCell cell;
            cell = row.createCell(j, CellType.STRING);
            if (!"".equals(objects[j]) && objects[j] != null) {
                cell.setCellValue(objects[j].toString());
            } else {
                cell.setCellValue("  ");
            }
            if (this.isLockSheet) {
                if (unLockColumnIndexList.contains(j)) {
                    cell.setCellStyle(unLockContentStyle);
                } else {
                    cell.setCellStyle(lockContentStyle);
                }
            } else {
                cell.setCellStyle(normalContentStyle);
            }
        }
    }

    private void createExcelTitle(SXSSFWorkbook swb, SXSSFSheet sheet) {
        // 产生表格标题行
        SXSSFRow row = sheet.createRow(0);
        SXSSFCell cellTitle = row.createCell(0);
        // sheet样式定义
        CellStyle titleStyle = this.getTitleStyle(swb);
        /**
         * 参数说明 从0开始 第一行 第一列 都是从角标0开始 行 列 行列 (0,0,0,5) 合并第一行 第一列 到第一行 第六列 起始行，
         * 起始列，结束行，结束列 列的类型为short
         */
        // 标题,合并第一行的所有列 而且放入title
        sheet.addMergedRegion(new CellRangeAddress(0, titleHeight - 1, 0, firstHeaderWidth - 1));
        cellTitle.setCellStyle(titleStyle);
        cellTitle.setCellValue(title);
    }

    /**
     * 标题样式
     */
    private CellStyle getTitleStyle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        font.setFontName(DEFAULT_FONT_NAME);
        CellStyle style = workbook.createCellStyle();
        setStyle(font, style);
        return style;
    }

    private void setStyle(Font font, CellStyle style) {
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(HSSFColor.BLACK.index);
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(HSSFColor.BLACK.index);
        style.setFont(font);
        style.setWrapText(false);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    /**
     * 内容样式的style
     */
    private CellStyle getContentStyle(Workbook workBbook) {
        Font font = workBbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setFontName(DEFAULT_FONT_NAME);
        CellStyle style = workBbook.createCellStyle();
        DataFormat format = workBbook.createDataFormat();
        style.setDataFormat(format.getFormat("@"));
        setStyle(font, style);
        return style;
    }

    private CellStyle getLockContentStyle(Workbook workBook) {
        Font font = workBook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setFontName(DEFAULT_FONT_NAME);
        CellStyle style = workBook.createCellStyle();
        DataFormat format = workBook.createDataFormat();
        style.setDataFormat(format.getFormat("@"));
        setStyle(font, style);
        style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setLocked(true);
        return style;
    }

    private void setBorderStyle(int border, CellRangeAddress region, Sheet sheet) {
        //下边框
        RegionUtil.setBorderBottom(border, region, sheet);
        //左边框
        RegionUtil.setBorderLeft(border, region, sheet);
        //右边框
        RegionUtil.setBorderRight(border, region, sheet);
        //上边框
        RegionUtil.setBorderTop(border, region, sheet);
    }

    public void setTitleHeight(Integer titleHeight) {
        if (!Objects.equals(titleHeight, this.titleHeight) && Objects.nonNull(this.title)) {
            this.titleHeight = titleHeight;
            this.setHeaderWidthAndHeight(this.headersList);
            this.setHeaderXYPosition();
        }
    }

    public void setLockSheet(boolean isLockSheet) {
        this.isLockSheet = isLockSheet;
        if (isLockSheet) {
            this.setHeaderWidthAndHeight(this.headersList);
            this.setHeaderXYPosition();
        }
    }

    public void setData2Array(Object[][] data2Array){
        this.data2Array = data2Array;
    }

    public void setDatasList(List<Object[]> datasList) {
        this.datasList = datasList;
    }

    public void setBeanList(List<T> beanList) {
        this.beanList = beanList;
    }

    public void setDataListList(List<List<Object>> dataListList) {
        this.dataListList = dataListList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    /**
     * 获取已经排序的导出Field
     */
    private List<Field> getExcelField(Class targetClass) {
        Field[] fields = targetClass.getDeclaredFields();
        List<Field> orderFieldList = new ArrayList<>();
        Map<Integer, Field> fieldMap = new HashMap<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ExcelField.class)) {
                ExcelField excelField = field.getAnnotation(ExcelField.class);
                fieldMap.put(excelField.order(), field);
            }
        }
        Set<Integer> orderSet = fieldMap.keySet();
        List<Integer> orderList = new ArrayList<>(orderSet);
        Collections.sort(orderList);
        for (Integer order : orderList) {
            orderFieldList.add(fieldMap.get(order));
        }
        return orderFieldList;
    }

    /**
     * 对象属性转换为object数组
     *
     * @param targetObject 目标对象
     * @param fields 字段列表
     */
    private static Object[] convertObjectArray(Object targetObject, List<Field> fields) {
        List<Object> objectList = new ArrayList<>(fields.size());
        if (CollectionUtils.isEmpty(fields)) {
            throw new ExcelException("支持excel导出的字段为空");
        }
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object object = field.get(targetObject);
                objectList.add(Objects.nonNull(object) ? object : "");
            }
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
        return objectList.toArray();
    }
}
