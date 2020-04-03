package com.joindoo.jdwechat.controller.api;

import com.joindoo.jdwechat.SystemSetting;
import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.model.web.ConcurrentModelAndView;
import com.joindoo.jdwechat.utils.HttpContentTypes;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created by zhuqiang on 2018/3/29.
 */
public class BaseApiController {
    private DataService dataService=null;
    protected final Logger logger = LoggerFactory.getLogger(BaseApiController.class);
    public static ModelAndView RESULT_DEFAULT=new ConcurrentModelAndView();

    public DataService getDataService() {
        if(null==dataService)
            dataService=new DataService();
        return dataService;
    }

    /**
     * 把一个文件的信息返回给客户端
     *
     * @param fileContents
     * @param contentType
     * @param downloadName
     * @return
     */
    protected ModelAndView file(HttpServletResponse response, HttpServletRequest request,
                                byte[] fileContents, String contentType, String downloadName) {
        if (Utility.isNullOrEmpty(contentType)) contentType = HttpContentTypes.Application_OctetStream;
        if (!Utility.isNullOrEmpty(downloadName)) {
            downloadName = Utility.createRfc2231HeaderValue(downloadName);
            response.addHeader("Content-Disposition", downloadName);//AddHeader("Content-Disposition", headerValue);
        }
        response.addHeader("Content-Length", String.valueOf(fileContents.length));
        try {
            OutputStream stream = response.getOutputStream();
            stream.write(fileContents, 0, fileContents.length);
            stream.flush();
            response.flushBuffer();
        } catch (Exception error) {
            logger.error(error.getMessage());
        }
        return RESULT_DEFAULT;
    }
    protected ModelAndView file(HttpServletResponse response, HttpServletRequest request,
                                String filePath, String contentType, String downloadName) throws UnsupportedEncodingException {
        return  file(response,request,filePath,contentType,downloadName,0);
    }
    /**
     * 返回给客户端一个文件中的数据
     *
     * @param filePath
     * @param contentType
     * @param downloadName
     * @param startPos     如果此参数大于0，则代表从某一个位置开始下载文件
     * @return
     */
    protected ModelAndView file(HttpServletResponse response, HttpServletRequest request,
                                String filePath, String contentType, String downloadName, int startPos) throws UnsupportedEncodingException {
        if (Utility.isNullOrEmpty(contentType)) contentType = HttpContentTypes.Application_OctetStream;
        final String userAgent = request.getHeader("USER-AGENT");
        String finalFileName = fileNameAdapter(userAgent,downloadName);
        if(StringUtils.contains(finalFileName,"attachment")){
            response.setHeader("Content-Disposition", finalFileName);
        }else
            response.setHeader("Content-Disposition", "attachment; filename=\""+ finalFileName+"\"");
        response.setContentType("application/octet-stream; charset=utf-8");
        File file = new File(filePath);
        long size = file.length();
        if (startPos < 0) startPos = 0;
        response.setHeader("Content-Length", String.valueOf(size - startPos));
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            if (startPos > 0) fileInputStream.skip(startPos);
            byte[] buffer = new byte[SystemSetting.System_BufferSize];
            OutputStream stream = response.getOutputStream();
            int len = 0;
            do {
                len = fileInputStream.read(buffer, 0, buffer.length);
                if(len>0)
                    stream.write(buffer, 0, len);
            } while (len >0);
            stream.flush();
            response.flushBuffer();
            return null;
        } catch (Exception error) {
            logger.error(error.getMessage());
        }
        return RESULT_DEFAULT;
    }
    private String fileNameAdapter(String userAgent,String downloadName) throws UnsupportedEncodingException {
        String finalFileName = null;
        if(StringUtils.contains(userAgent, "MSIE")){//IE浏览器
            finalFileName = URLEncoder.encode(downloadName,"UTF8");
        }else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
            // finalFileName = new String(downloadName.getBytes(), "ISO8859-1");
            finalFileName = Utility.createRfc2231HeaderValue(downloadName);
        }else{
            finalFileName = URLEncoder.encode(downloadName,"UTF8");//其他浏览器
        }
        return finalFileName;
    }
    public ModelAndView file(HttpServletResponse response, HttpServletRequest request, String file, String downloadName) throws IOException {
        final String userAgent =request.getHeader("USER-AGENT");
        OutputStream stream = response.getOutputStream();
        response.reset();
        String finalFileName = fileNameAdapter(userAgent,downloadName);

        if(StringUtils.contains(finalFileName,"attachment")){
            response.setHeader("Content-Disposition", finalFileName);
        }else
            response.setHeader("Content-Disposition", "attachment; filename=\""+ finalFileName+"\"");
        response.setContentType("application/octet-stream; charset=utf-8");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fileInputStream);
            byte[] bytes = new byte[bis.available()];
            bis.read(bytes);
            stream.write(bytes);
            stream.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            stream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return RESULT_DEFAULT;
    }

    public MultipartHttpServletRequest getMultipartRequest(HttpServletRequest request) {
        if (request instanceof MultipartHttpServletRequest) {
            return (MultipartHttpServletRequest) request;
        } else {
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartHttpServletRequest mrequest = resolver.resolveMultipart(request);
            return mrequest;
        }
    }
}
