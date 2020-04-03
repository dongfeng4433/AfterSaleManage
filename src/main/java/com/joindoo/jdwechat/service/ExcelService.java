package com.joindoo.jdwechat.service;

import com.joindoo.jdwechat.utils.ExcelImportUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by zhuqiang on 2018/5/22.
 */
public class ExcelService {
    /**
     * 上传excel文件到临时目录后并开始解析
     * @param fileName
     * @return
     */
    public HashMap<Integer,HashMap<Integer,Object>> getExcelData(String fileName){
        //初始化输入流
        InputStream is = null;
        try{

            //根据新建的文件实例化输入流
            is = new FileInputStream(fileName);

            //根据版本选择创建Workbook的方式
            Workbook wb = null;
            //根据文件名判断文件是2003版本还是2007版本
            if(ExcelImportUtils.isExcel2007(fileName)){
                wb = new XSSFWorkbook(is);
            }else{
                wb = new HSSFWorkbook(is);
            }
            //根据excel里面的内容读取知识库信息
            HashMap<Integer,HashMap<Integer,Object>> hashMapHashMap = new HashMap<>();
            hashMapHashMap = readExcelValue(wb);
            return hashMapHashMap;
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            if(is !=null)
            {
                try{
                    is.close();
                }catch(IOException e){
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     * 解析Excel里面的数据
     * @param wb
     * @return
     */
    private HashMap<Integer,HashMap<Integer,Object>> readExcelValue(Workbook wb){
        HashMap<Integer,HashMap<Integer,Object>> result=new HashMap<>();
        //错误信息接收器
        String errorMsg = "";
        //得到第一个shell
        Sheet sheet=wb.getSheetAt(0);
        //得到Excel的行数
        int totalRows=sheet.getPhysicalNumberOfRows();
        //总列数
        int totalCells = 0;
        //得到Excel的列数(前提是有行数)，从第二行算起
        if(totalRows>=2 && sheet.getRow(1) != null){
            totalCells=sheet.getRow(1).getPhysicalNumberOfCells();
        }

        String br = "<br/>";

        //循环Excel行数,从第二行开始。标题不入库
        for(int r=1;r<totalRows;r++){
            String rowMessage = "";
            Row row = sheet.getRow(r);
            if (row == null){
                errorMsg += br+"第"+(r+1)+"行数据有问题，请仔细检查！";
                continue;
            }
            //循环Excel的列
            HashMap<Integer,Object> rowHashMap=new HashMap<>();
            for(int c = 0; c <totalCells; c++){
                Cell cell = row.getCell(c);
                if (null != cell){
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    rowHashMap.put(c, cell.getStringCellValue());
                }else{
                    rowMessage += "第"+(c+1)+"列数据有问题，请仔细检查；";
                }
            }
            result.put(r-1,rowHashMap);
        }

        return result;
    }
}
