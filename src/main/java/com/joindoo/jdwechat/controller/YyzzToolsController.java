package com.joindoo.jdwechat.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joindoo.jdwechat.SystemSetting;
import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.WeChatFields;
import com.joindoo.jdwechat.beans.YyzzModel;
import com.joindoo.jdwechat.common.WeChatEnums;
import com.joindoo.jdwechat.data.DBHelper;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.entity.config.SysProperties;
import com.joindoo.jdwechat.model.BaseResultModel;
import com.joindoo.jdwechat.model.ResultListModel;
import com.joindoo.jdwechat.model.query.BaseResultQueryModel;
import com.joindoo.jdwechat.model.sys.FileModel;
import com.joindoo.jdwechat.service.HttpClient;
import com.joindoo.jdwechat.utils.HttpContentTypes;
import com.joindoo.jdwechat.utils.ZipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/YyzzTools")
public class YyzzToolsController extends BaseController{
    @Autowired
    SysProperties sysProperties;
    @Autowired
    DruidConfig druidConfig;

    @Autowired
    HttpClient httpClient;

    @RequestMapping("/Index")
    public ModelAndView Index(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse, Model model) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tools/index");
        return modelAndView;
    }

    //检索条件是否包含非法字符
    private boolean CheckInput(String input)
    {
        if (!Utility.isNullOrEmpty(input))
        {
            String[] invalidInput = new String[] { "-", "/", "\\", "'", "<", ">", "!", ";", "%", "CREATE", "DROP", "SELECT", "UPDATE", "DELETE", "INSERT" };
            for (String item: invalidInput   ) {
                if (input.contains(item))
                    return false;
            }
        }
        return true;
    }

    @ResponseBody
    @RequestMapping("/queryYyzz")
    public ResponseEntity<ResultListModel> queryYyzz(BaseResultQueryModel queryModel)throws Exception {
        ResultListModel resultListModel = new ResultListModel();
        if (this.CheckInput(queryModel.getQueryText()) == false)
        {
            resultListModel.setSuccess(false);
            resultListModel.setMsg("您的输入包含非法字符！");
            return new ResponseEntity<>(resultListModel, HttpStatus.OK);
        }
        String XH = "GTH_XH", MC = "GTH_MC", TABLE = "JT_YSJ_ZWDT_GTH";
        String ZCLX_DM="1";
        if(queryModel.getQueryType().equals(WeChatEnums._企业.getIndex())){
            XH = "GSCL_XH"; MC = "QY_MC"; TABLE = "JT_YSJ_ZWDT_GSCL";ZCLX_DM="2";
        }
        String AK_SK = Utility.String_Format("AK={0}&SK={1}", WeChatEnums._AK.getIndex(),WeChatEnums._SK.getIndex());
        String postParams = Utility.String_Format("FILTER={0}&XH={1}&MC={2}&TABLE={3}&{4}",queryModel.getQueryText(),XH,MC,TABLE,AK_SK);

//        String postParams = "FILTER="+queryModel.getQueryText()+"&XH="+XH+"&MC="+MC+"&TABLE="+TABLE+"&"+AK_SK+"";
//        String url = "http://localhost:9312/YSJ/GetListData?"+postParams;
        String url = SystemSetting.WEB_HOST+"/YSJ/GetListData?"+postParams;
//        MultiValueMap<String,String> params=new LinkedMultiValueMap<>();
        String jsonData= httpClient.client(url);
//        jsonData="[{\"XH\":\"d93b869f3267b042b459c999230d521d\",\"ZCH\":\"91320113MA1MF06RX9\",\"MC\":\"南京古稻河园林工程有限公司\",\"FJ_XH\":null},{\"XH\":\"10c879e79f778d43bb00c968c3b37f8d\",\"ZCH\":\"91320113MA1MEAGD0C\",\"MC\":\"南京谦合房产营销策划有限公司\",\"FJ_XH\":null}]";

        jsonData=jsonData.replace('"','\"');
        JSONArray jsonArray=JSONArray.parseArray(jsonData);
        List dtoModelList=new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            Object o = jsonArray.get(i);
            String s= JSON.toJSONString(o);
            JSONObject obs= JSONObject.parseObject(s);
            YyzzModel ym=new YyzzModel();
            ym.setZCLX_DM(ZCLX_DM);
            ym.fillModel(obs);
            dtoModelList.add(ym);
        }

        resultListModel.setRows(dtoModelList);
        resultListModel.setSuccess(true);
        return new ResponseEntity<>(resultListModel, HttpStatus.OK);
    }

    /**
     * 将一个字符串转化为输入流
     */
    public static InputStream getStringStream(String sInputString){
        if (sInputString != null && !sInputString.trim().equals("")){
            try{
                ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
                return tInputStringStream;
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
    public void inputstreamtofile(InputStream ins,File file)throws Exception{
        OutputStream os = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
    }
    @RequestMapping("/downloadYyzz")
    public ResponseEntity<BaseResultModel> downloadYyzz(HttpServletResponse response, HttpServletRequest request, YyzzModel model)throws Exception{
        BaseResultModel baseResultModel = new BaseResultModel();
        String attachment_id = model.getFJ_XH();
        String[] fjxhs = Utility.getSeparatedItems(attachment_id, ',');

        String targetPath=sysProperties.getDownload_file()+ File.separator;
        if(!Utility.File_Exists(targetPath))
            Utility.File_Mkdirs(targetPath);

        String zipPath = Utility.Path_Combine(targetPath, model.getMC()+ ".zip");
        if(Utility.File_Exists(zipPath)){
            baseResultModel.setSuccess(true);
            return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
        }
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipPath));

        HttpHeaders headers = new HttpHeaders();
        RestTemplate template = new RestTemplate();
        HttpEntity<Resource> httpEntity = new HttpEntity<Resource>(headers);


        for (String fj_xh : fjxhs) {
            String postParams = Utility.String_Format("FJ_XH={0}", fj_xh);
            String url = "http://"+ SystemSetting.SYS_GOV_HOST +":8085/YSJ/DownloadPictures?" + postParams;
            ResponseEntity<byte[]> rps = template.exchange(url, HttpMethod.GET,
                    httpEntity, byte[].class);

            try {
                File file = File.createTempFile(model.getMC()+"-", "." + rps.getHeaders().getContentType().getSubtype());
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(rps.getBody());
                fos.flush();
                fos.close();

                if (file.exists())
                    ZipUtil.doCompress(file, out);

                baseResultModel.setTag(zipPath);
                baseResultModel.setSuccess(true);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                logger.info(e.getMessage());
                baseResultModel.setSuccess(false);
            }
        }
        out.close();
//        File zipFile = new File(zipPath);
//        if (zipFile.exists())
//            return this.file(response, request, zipFile.getPath(), HttpContentTypes.Application_Zip, model.getMC() + ".zip");

        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }

    @RequestMapping("/downloadYyzz2")
    public ModelAndView downloadYyzz2(HttpServletResponse response, HttpServletRequest request,YyzzModel model)throws Exception{
        String attachment_id = model.getFJ_XH();
        String targetPath=sysProperties.getDownload_file()+ File.separator;
        if(!Utility.File_Exists(targetPath))
            Utility.File_Mkdirs(targetPath);
        String name=Utility.DefaultTimestampFormat.format(new Date());
        String zipPath = Utility.Path_Combine(targetPath, model.getMC()+ ".zip");
        File zipFile = new File(zipPath);
        if (new File(zipPath).exists())
            return this.file(response, request, zipFile.getPath(), HttpContentTypes.Application_Zip, model.getMC() + ".zip");
        return null;
    }



    @RequestMapping("/downloadYyzz1")
    public void downloadYyzz1(YyzzModel model) throws Exception {
        DataService dataService = this.getDataService();
        String attachment_id = model.getFJ_XH();
        String[] fjxhs = Utility.getSeparatedItems(attachment_id, ',');
        for (String fj_xh : fjxhs) {
            String postParams = Utility.String_Format("FJ_XH={0}", fj_xh);
            String url = "http://"+SystemSetting.SYS_GOV_HOST+":8085/YSJ/DownloadPictures?" + postParams;
            String opath=sysProperties.getDownload_file()+ File.separator;
//            String file_name= model.getMC()+"-"+ Utility.DefaultTimestampFormat.format(new Date());
            String file_name= model.getMC()+fj_xh;
//            httpClient.clientFile(url,opath+file_name);
            String o_value=httpClient.client(url);
            InputStream is=this.getStringStream(o_value);
            if (!Utility.File_Exists(opath))
                Utility.File_Mkdirs(opath);
            File file=new File(opath+file_name);
            inputstreamtofile(is,file);
        }
    }

    @RequestMapping(value = "/queryBlob",method = RequestMethod.GET)
    public ModelAndView queryBlob(HttpServletRequest request, HttpServletResponse response,YyzzModel model) throws UnsupportedEncodingException {
        byte[] fileData=new byte[]{};
        Connection connection=null;
        try {
            if(!Utility.isNullOrEmpty(model.getZCH())){
                connection=druidConfig.druidDataSource2().getConnection();
                String ZCH="91320113MA1ME1TE7T";
                String sqlQuery = "select DZZP from "+ SystemSetting.ORACLE_USER+".JT_YSJ_ZWDT_DZZP where ZCH=?";
                fileData= DBHelper.queryBlob(connection,sqlQuery, model.getZCH());
                connection.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            if(connection!=null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        FileModel fileModel=new FileModel();
        fileModel.setData(fileData);
        fileModel.setType("1");
        return onProcessFile(response,request,fileModel);
    }
}
