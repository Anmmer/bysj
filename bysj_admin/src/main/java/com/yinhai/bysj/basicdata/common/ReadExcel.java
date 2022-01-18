package com.yinhai.bysj.basicdata.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

//import jxl.Sheet;
//import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
    public static void main(String[] args) throws FileNotFoundException {
        ReadExcel obj = new ReadExcel();
//        // 此处为我创建Excel路径
        InputStream is = new FileInputStream("D:\\program\\idea\\bysj.xls");
//        List excelList = obj.readExcel(is);
//        System.out.println("list中的数据打印出来");
//        for (int i = 0; i < excelList.size(); i++) {
//            List list = (List) excelList.get(i);
//            for (int j = 0; j < list.size(); j++) {
//                System.out.print(list.get(j)+"  ");
//            }
//            System.out.println();
//        }

    }

    // 去读Excel的方法readExcel，该方法的入口参数为一个File对象
//    public List readExcel(InputStream is) {
//        try {
//            // jxl提供的Workbook类
//            Workbook wb = Workbook.getWorkbook(is);
//            // Excel的页签数量
//            int sheet_size = wb.getNumberOfSheets();
//            for (int index = sheet_size - 1; index >= 0; index--) {
//                List<List> outerList = new ArrayList<List>();
//                // 每个页签创建一个Sheet对象
//                Sheet sheet = wb.getSheet(index);
//                // sheet.getRows()返回该页的总行数
//                for (int i = 0; i < sheet.getRows(); i++) {
//                    List innerList = new ArrayList();
//                    // sheet.getColumns()返回该页的总列数
//                    for (int j = 0; j < sheet.getColumns(); j++) {
//                        String cellinfo = sheet.getCell(j, i).getContents();
//                        if (cellinfo.isEmpty()) {
//                            continue;
//                        }
//                        innerList.add(cellinfo);
////                        System.out.print(cellinfo);
//                    }
//                    outerList.add(i, innerList);
////                    System.out.println();
//                }
//                return outerList;
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (BiffException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public List<Map> getListFromExcel(String cols, InputStream inputStream) throws Exception {
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int lastRow = sheet.getLastRowNum();
        List<Map> personList = new ArrayList<>();
        String[] keys = cols.split(",");
        for (int i = 1; i <= lastRow; i++) {
            Map<String, Object> personMap = new HashMap<>();
            for (int j = 0; j < keys.length; j++) {
                String text = getCell(sheet, i, j);
                personMap.put(keys[j], text);
            }
            /*  T t1  = (T)ObjectUtil.map2Bean(t, personMap);*/
       /*     JSONObject jsonObject = (JSONObject) JSONObject.toJSON(personMap);
            T t1 = jsonObject.toJavaObject(t);*/
            personList.add(personMap);
        }
        if(inputStream != null){
            inputStream.close();
        }
        return personList;
    }

    private String getCell(Sheet sheet, int row, int col) {
        Cell cell = sheet.getRow(row).getCell(col);
        if (ObjectUtil.isEmpty(cell)) {
            return null;
        }
       /* if ("NUMERIC".equals(cell.getCellType().name())) {
            return String.valueOf(Double.valueOf(cell.getNumericCellValue()).intValue());
        }*/
        return cell.toString();
    }
}
