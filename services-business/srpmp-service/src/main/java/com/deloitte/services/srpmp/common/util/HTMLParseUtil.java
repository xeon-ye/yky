package com.deloitte.services.srpmp.common.util;

import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.project.budget.vo.ext.BudgetApplyVo;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixin on 04/03/2019.
 */
public class HTMLParseUtil {


    public static String extractCellFromTable(Element table, int rowIndex, int colIndex){
        return extractCellFromTable(table, rowIndex, colIndex, false);
    }

    public static String extractCellFromTable(Element table, int rowIndex, int colIndex, boolean includeHtmlStyle){
        if (table == null){
            throw new IllegalArgumentException("element cannot be null.");
        }
        if (!table.tagName().equals("table")){
            throw new IllegalArgumentException("element is not a table.");
        }
        //获取所有的行
        Elements rowList = table.getElementsByTag("tr");
        if (rowList.size() == 0){
            return null;
        }
        //获取指定行
        Element row = rowList.get(rowIndex);
        if (row == null){
            return null;
        }
        //获取指定单元格
        Element cell = row.getElementsByTag("td").get(colIndex);
        if (cell == null){
            return null;
        }
        if (includeHtmlStyle){
            return StringEscapeUtils.escapeHtml4(cell.html());//不编码了
//            return cell.html();
        }else{
            return cell.text();
        }
    }


    public static List<List<String>> extractListFromTable(Element table, int startRowIndex, int endRowSize){
        if (table == null){
            throw new IllegalArgumentException("element cannot be null.");
        }
        if (!table.tagName().equals("table")){
            throw new IllegalArgumentException("element is not a table.");
        }
        //获取所有的行
        Elements rowList = table.getElementsByTag("tr");
        if (rowList.size() == 0){
            return null;
        }
        List<List<String>> tableList = new ArrayList<>();
        for (int i = startRowIndex; i < rowList.size() - endRowSize; i++){
            List<String> rowStringList = new ArrayList<>();
            Element rowElement = rowList.get(i);
            Elements cellElements = rowElement.getElementsByTag("td");
            for (Element cellElement: cellElements){
                rowStringList.add(cellElement.text());
            }
            tableList.add(rowStringList);
        }
        return tableList;
    }

    public static List<List<String>> extractListTableRows(Element table, int startRowIndex, int endRowSize){
        if (table == null){
            throw new IllegalArgumentException("element cannot be null.");
        }
        if (!table.tagName().equals("table")){
            throw new IllegalArgumentException("element is not a table.");
        }
        //获取所有的行
        Elements rowList = table.getElementsByTag("tr");
        if (rowList.size() == 0){
            return null;
        }
        List<List<String>> tableList = new ArrayList<>();
        for (int i = startRowIndex; i < endRowSize; i++){
            List<String> rowStringList = new ArrayList<>();
            Element rowElement = rowList.get(i);
            Elements cellElements = rowElement.getElementsByTag("td");
            for (Element cellElement: cellElements){
                rowStringList.add(cellElement.text());
            }
            tableList.add(rowStringList);
        }
        return tableList;
    }


    public static void main(String[] args){
        String html = "<p>hello world</p>";
        String eshtml = "&lt;p&gt;hello world&lt;/p&gt;";
        eshtml = StringEscapeUtils.unescapeHtml4("&lt;p style=&quot;margin-top: 0px; margin-bottom: 8px; margin-left: 2px; white-space: normal; text-indent: 27px;&quot;&gt;1&#65294;Liu, Z., Sun M, Xiao J, et al. (2016). &quot;Predictors of metastasis to lymph nodes posterior to the right recurrent laryngeal nerve in differentiated thyroid carcinoma: A prospective study.&quot; Asian J Surg.&lt;/p&gt;");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("a", "&lt;p style=&quot;margin-top: 0px; margin-bottom: 8px; margin-left: 2px; white-space: normal; text-indent: 27px;&quot;&gt;1&#65294;Liu, Z., Sun M, Xiao J, et al. (2016). &quot;Predictors of metastasis to lymph nodes posterior to the right recurrent laryngeal nerve in differentiated thyroid carcinoma: A prospective study.&quot; Asian J Surg.&lt;/p&gt;");
        System.out.println(jsonObject.toJSONString());

        System.out.println(eshtml);

    }

}
