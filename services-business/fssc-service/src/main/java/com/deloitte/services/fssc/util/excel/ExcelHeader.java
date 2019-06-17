package com.deloitte.services.fssc.util.excel;

import java.util.Arrays;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Excel 头部列名
 * @author jawjiang
 *
 */
public class ExcelHeader {
	
	private String columnName;
	
	private Integer width; //横向占用列数

	private Integer height;
	
	private HorizontalAlignment align;
	
	private boolean isLock;

	private int startX; // X轴起始坐标

	private int endX; // X轴终止坐标

	private int startY; // Y轴起始坐标

	private int endY; // X轴起始坐标

	private Integer oneCellWidth; //设置暂用的每一列的真实宽度

	public ExcelHeader(String columnName){
		this.columnName = columnName;
		this.width = 1;
		this.height = 1;
		this.align = HorizontalAlignment.CENTER;
		this.setLock(false);
	}
	
	public ExcelHeader(String columnName,Integer width){
		this.columnName = columnName;
		this.width = width;
		this.height = 1;
		this.align = HorizontalAlignment.CENTER;
		this.setLock(false);
	} 
	
	public ExcelHeader(String columnName,Integer width,Integer height){
		this.columnName = columnName;
		this.width = width;
		this.height = height;
		this.align = HorizontalAlignment.CENTER;
		this.setLock(false);
	} 
	
	public boolean isLock() {
		return isLock;
	}

	public ExcelHeader setLock(boolean isLock) {
		this.isLock = isLock;
		return this;
	}
	
	public String getColumnName() {
		return columnName;
	}

	public ExcelHeader setColumnName(String columnName) {
		this.columnName = columnName;
		return this;
	}

	public Integer getWidth() {
		return width;
	}

	public ExcelHeader setWidth(Integer width) {
		this.width = width;
		return this;
	}

	public Integer getHeight() {
		return height;
	}

	public ExcelHeader setHeight(Integer height) {
		this.height = height;
		return this;
	}

	public HorizontalAlignment getAlign() {
		return align;
	}

	public ExcelHeader setAlign(HorizontalAlignment align) {
		this.align = align;
		return this;
	}

	public int getStartX() {
		return startX;
	}

	public ExcelHeader setStartX(int startX) {
		this.startX = startX;
		return this;
	}

	public int getEndX() {
		return endX;
	}

	public ExcelHeader setEndX(int endX) {
		this.endX = endX;
		return this;
	}

	public int getStartY() {
		return startY;
	}

	public ExcelHeader setStartY(int startY) {
		this.startY = startY;
		return this;
	}

	public int getEndY() {
		return endY;
	}

	public ExcelHeader setEndY(int endY) {
		this.endY = endY;
		return this;
	}

	public static ExcelHeader getDefaultInstance(String columnName){
		return new ExcelHeader(columnName);
	}

	public static List<List<ExcelHeader>> getDefaultHeadersList(List<String> columnList){
		List<ExcelHeader> headerList = new ArrayList<>();
		if(CollectionUtils.isEmpty(columnList)){
			throw new ExcelException("列集合为空");
		}
		for(String columnName : columnList){
			ExcelHeader header = new ExcelHeader(columnName);
			headerList.add(header);
		}
		List<List<ExcelHeader>> headersList = new ArrayList<>();
		headersList.add(headerList);
		return headersList;
	}

	public static List<List<ExcelHeader>> getHeadersList(List<ExcelHeader> headerList){
		List<List<ExcelHeader>> headersList = new ArrayList<>();
		headersList.add(headerList);
		return headersList;
	}

	public static List<List<ExcelHeader>> getDefaultHeadersList(String[] columnList){
		List<ExcelHeader> headerList = new ArrayList<>();
		if(ArrayUtils.isEmpty(columnList)){
			throw new ExcelException("列集合为空");
		}
		for(String columnName : columnList){
			ExcelHeader header = new ExcelHeader(columnName);
			headerList.add(header);
		}
		List<List<ExcelHeader>> headersList = new ArrayList<>();
		headersList.add(headerList);
		return headersList;
	}

	public Integer getOneCellWidth() {
		return oneCellWidth;
	}

	public ExcelHeader setOneCellWidth(Integer oneCellWidth) {
		this.oneCellWidth = oneCellWidth;
		return this;
	}
}
