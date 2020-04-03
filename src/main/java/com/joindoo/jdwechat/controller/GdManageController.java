package com.joindoo.jdwechat.controller;

import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.codeGen.TSqblGdjbxxDtoModel;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.entity.config.SysProperties;
import com.joindoo.jdwechat.model.BaseResultModel;
import com.joindoo.jdwechat.model.DowloadFileModel;
import com.joindoo.jdwechat.model.ResultListModel;
import com.joindoo.jdwechat.model.query.PagingOptions;
import com.joindoo.jdwechat.model.query.TSqblGdjbxxQueryModel;
import com.joindoo.jdwechat.service.HttpClient;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/GdManage")
public class GdManageController extends BaseController{
    @Autowired
    SysProperties sysProperties;
    @Autowired
    DruidConfig druidConfig;

    @Autowired
    HttpClient httpClient;

    @RequestMapping("/Index")
    public ModelAndView Index(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, Model model) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("gd/index");
        return modelAndView;
    }

    //查询所有账号信息
    @RequestMapping("/queryGdxx")
    public ResponseEntity<ResultListModel> queryGdxx(PagingOptions pagingOptions, TSqblGdjbxxQueryModel queryModel) throws Exception {
        ResultListModel resultListModel = new ResultListModel();
        DataService dataService = this.getDataService();
        DataContext dataContext = dataService.getDataContext(druidConfig);
        if (pagingOptions.getPageSize() == 0) pagingOptions.setPageSize(20);
        pagingOptions.setNeedTotal(true);
        List<TSqblGdjbxxDtoModel> dtoModelList = dataService.selectT_SQBL_GDJBXX(pagingOptions, queryModel);
        int start = pagingOptions.getStart();
        resultListModel.setRows(dtoModelList);
        resultListModel.setStart(start);
        resultListModel.setSuccess(true);
        resultListModel.setResultCount(dtoModelList.size());
        resultListModel.setTotal(pagingOptions.getTotal());
        dataContext.release();
        dataService.disposeInCurrentThread();
        return new ResponseEntity<>(resultListModel, HttpStatus.OK);
    }



    @RequestMapping("expGdxxToExcel")
    public ResponseEntity<BaseResultModel> expGdxxToExcel(PagingOptions pagingOptions,TSqblGdjbxxQueryModel queryModel) throws Exception {
        BaseResultModel baseResultModel = new BaseResultModel();
        DataService dataService = this.getDataService();
        List<TSqblGdjbxxDtoModel> dtoModelList = dataService.selectT_SQBL_GDJBXX(pagingOptions, queryModel);
        HSSFWorkbook work = new HSSFWorkbook();
        //title字体
        Font titleFont = work.createFont();
        titleFont.setFontName("微软雅黑");
        titleFont.setFontHeightInPoints((short) 10);
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //内容字体
        Font contentFont = work.createFont();
        contentFont.setFontHeightInPoints((short) 9);
        contentFont.setFontName("宋体");
        contentFont.setColor(HSSFColor.BLACK.index);

        //title样式
        CellStyle titleStyle = work.createCellStyle();
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        titleStyle.setFont(titleFont);
        titleStyle.setWrapText(true);
        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        titleStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        //内容样式
        CellStyle contentStyle = work.createCellStyle();
        contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        contentStyle.setFont(contentFont);
        contentStyle.setWrapText(true);
        contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);

        HSSFSheet sheet = work.createSheet("工单信息列表");
        // 默认列宽20
        sheet.setDefaultColumnWidth(20);
        // 第6列（申诉内容）列宽
        sheet.setColumnWidth(5, 40 * 256);
        // 第1行、第1列、第2列冻结
        sheet.createFreezePane(2, 1, 2, 1);
        HSSFRow titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(45);
        // A1到M1可以筛选
        CellRangeAddress cellRangeAddress = CellRangeAddress.valueOf("A1:M1");
        sheet.setAutoFilter(cellRangeAddress);

        HSSFCell cell = titleRow.createCell(0);
        cell.setCellStyle(titleStyle);
        cell.setCellValue("工单编号");

        cell = titleRow.createCell(1);
        cell.setCellStyle(titleStyle);
        cell.setCellValue("回访号码");

        cell = titleRow.createCell(2);
        cell.setCellStyle(titleStyle);
        cell.setCellValue("诉求类型");

        cell = titleRow.createCell(3);
        cell.setCellStyle(titleStyle);
        cell.setCellValue("诉求人姓名");

        cell = titleRow.createCell(4);
        cell.setCellStyle(titleStyle);
        cell.setCellValue("诉求人号码");

        cell = titleRow.createCell(5);
        cell.setCellStyle(titleStyle);
        cell.setCellValue("诉求内容");

        cell = titleRow.createCell(6);
        cell.setCellStyle(titleStyle);
        cell.setCellValue("诉求人地址");

        cell = titleRow.createCell(7);
        cell.setCellStyle(titleStyle);
        cell.setCellValue("发生时间");

        cell = titleRow.createCell(8);
        cell.setCellStyle(titleStyle);
        cell.setCellValue("事件发生地");


        cell = titleRow.createCell(9);
        cell.setCellStyle(titleStyle);
        cell.setCellValue("街道名称");

        cell = titleRow.createCell(10);
        cell.setCellStyle(titleStyle);
        cell.setCellValue("社区名称");

        cell = titleRow.createCell(11);
        cell.setCellStyle(titleStyle);
        cell.setCellValue("微信群名称");

        cell = titleRow.createCell(12);
        cell.setCellStyle(titleStyle);
        cell.setCellValue("微信号");



        for (int i = 0; i < dtoModelList.size(); i++) {
            TSqblGdjbxxDtoModel model = dtoModelList.get(i);
            HSSFRow contentRow = sheet.createRow(i + 1);
//
//            HSSFCell contentCell = contentRow.createCell(0);
//            contentCell.setCellStyle(contentStyle);
//            contentCell.setCellValue(i + 1);

            HSSFCell contentCell = contentRow.createCell(0);
            contentCell.setCellStyle(contentStyle);
            contentCell.setCellValue(model.getgdbh());

            contentCell = contentRow.createCell(1);
            contentCell.setCellStyle(contentStyle);
            contentCell.setCellValue(model.gethfhm());

            contentCell = contentRow.createCell(2);
            contentCell.setCellStyle(contentStyle);
            contentCell.setCellValue(model.getsqlx_dm());

            contentCell = contentRow.createCell(3);
            contentCell.setCellStyle(contentStyle);
            contentCell.setCellValue(model.getsqrxm());

            contentCell = contentRow.createCell(4);
            contentCell.setCellStyle(contentStyle);
            contentCell.setCellValue(model.getsqrhm());

            contentCell = contentRow.createCell(5);
            contentCell.setCellStyle(contentStyle);
            contentCell.setCellValue(model.getsqnr());

            contentCell = contentRow.createCell(6);
            contentCell.setCellStyle(contentStyle);
            contentCell.setCellValue(model.getsqrdz());

            contentCell = contentRow.createCell(7);
            contentCell.setCellStyle(contentStyle);
            contentCell.setCellValue(model.getsqsjfssj());

            contentCell = contentRow.createCell(8);
            contentCell.setCellStyle(contentStyle);
            contentCell.setCellValue(model.getsqsjfsdz());

            contentCell = contentRow.createCell(9);
            contentCell.setCellStyle(contentStyle);
            contentCell.setCellValue(model.getjd_mc());

            contentCell = contentRow.createCell(10);
            contentCell.setCellStyle(contentStyle);
            contentCell.setCellValue(model.getsq_mc());

            contentCell = contentRow.createCell(11);
            contentCell.setCellStyle(contentStyle);
            contentCell.setCellValue(model.getwxq_mc());

            contentCell = contentRow.createCell(12);
            contentCell.setCellStyle(contentStyle);
            contentCell.setCellValue(model.getwxh());
        }



        String path_downloadFileStorage =sysProperties.getDocs();
        File downloadFile = new File(path_downloadFileStorage);
        //如果downloadFileStorage文件夹不存在，创建文件夹
        if (!downloadFile.exists()) {
            downloadFile.mkdir();
        }
        String name=Utility.DefaultTimestampFormat.format(new Date())+ ".XLS";
        String path = Utility.Path_Combine(path_downloadFileStorage, name);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            work.write(fileOutputStream);
            fileOutputStream.close();

            DowloadFileModel dfm = new DowloadFileModel();
            dfm.setFj_path(path);
            dfm.setFj_mc(path);
            baseResultModel.setTag(dfm);
            baseResultModel.setSuccess(true);
            return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
        } catch (Exception e) {
            baseResultModel.setSuccess(false);
            return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
        }
    }

}
