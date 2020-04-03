package com.joindoo.jdwechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.joindoo.jdwechat.SystemSetting;
import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.beans.*;
import com.joindoo.jdwechat.daos.*;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.model.BaseResultModel;
import com.joindoo.jdwechat.model.query.BaseQueryModel;
import com.joindoo.jdwechat.service.ExcelService;
import com.joindoo.jdwechat.utils.FileToBase64;
import com.joindoo.jdwechat.utils.JDUtil;
import com.joindoo.jdwechat.utils.QrCodeUtils;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * Created by zhuqiang on 2018/4/1.
 */
@Controller
public class TestController extends BaseController{
    @Autowired
    DruidConfig druidConfig;

    @RequestMapping("/")
    public ModelAndView index(Model model) {

//        UserEntity boy = new UserEntity();
//        boy.setUserName("weber");
//        boy.setNumber("1235");
//
//        model.addAttribute("user", boy);
//        return "index";
        return redirectTo("/Zhxx/login");
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public ResponseEntity<BaseResultModel> test(HttpServletRequest request, BaseQueryModel queryModel){
        BaseResultModel baseResultModel = new BaseResultModel();
        if(Utility.isNullOrEmpty( queryModel.getJd_token())|| !SystemSetting.SYS_API_KEY.equals(queryModel.getJd_token())){
            logger.info("api jd-token is error");
        }
        try {
            Map map=JDUtil.getWsResultByXmlStr96106("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Resualt><headTitle>工单信息提交后，如果提交成功回复值为ok</headTitle><msg>ok</msg></Resualt>");
//            String base64=FileToBase64.encodeBase64File("F:\\22.jpg");
//            FileToBase64.decoderBase64File(base64,"F:\\1.jpg");
            System.out.println(map);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        DataService dataService=this.getDataService();
//        DataContext dataContext=dataService.getDataContext(druidConfig);
//        JtSjSqblGdjbxxDao dao=new JtSjSqblGdjbxxDao(dataContext);
//        JtSjSqblGdjbxxModel model=new JtSjSqblGdjbxxModel();
//        model.setgdbh("DH2018060601");
//        model.setcbdw("民政局");
//        try {
//            dao.insertOnSubmit(model);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        dataService.disposeInCurrentThread();
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }

    private String getTitle(String nr){
        if(Utility.isNullOrEmpty(nr))return "";
        if(nr.length()>25)return nr.substring(0,25);
        return nr;
    }
    private String getXml4SaveGD( TSqblGdjbxxModel gdjbxxModel,String atta){
        String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        xml+="<request>";
        xml+="<headTitle>直通12345小程序工单</headTitle>";
        xml+="<data>";
        xml+="<col name=\"primaeval_id\" value=\""+gdjbxxModel.getgdbh()+"\" dis=\"办件编号：是否唯一键  不能为空\" />";
        xml+="<col name=\"case_source\" value=\"14\" dis=\"工单来源 14对应的是微信\" />";
        xml+="<col name=\"request_title\" value=\""+getTitle(gdjbxxModel.getsqnr())+"\" dis=\"标题：不能为空\" />";
        xml+="<col name=\"request_content\" value=\""+gdjbxxModel.getsqnr()+"\" dis=\"内容：不能为空\" />";
        xml+="<col name=\"request_name\" value=\""+gdjbxxModel.getsqrxm()+"\" dis=\"姓名：不能为空\" />";
        TXtWxyhModel xtWxyhModel=new TXtWxyhModel();
        xtWxyhModel.setopenid(gdjbxxModel.getwxh());
        if(Utility.isNullOrEmpty(gdjbxxModel.getwxh())||!SystemSetting.JD_ServerCache.isExistWxyh(xtWxyhModel)) {
            xml += "<col name=\"pri_field4\" value=\"0\" dis=\"性别：0男 1女    不能为空 默认男\" />";
        }else {
            xtWxyhModel=SystemSetting.JD_ServerCache.getWxyhCacheModel(gdjbxxModel.getwxh());
            if(null!=xtWxyhModel){
                xml += "<col name=\"pri_field4\" value=\""+(xtWxyhModel.getgender().equals("2")?"1":"0")+"\" dis=\"性别：0男 1女    不能为空 默认男\" />";
            }
        }
        xml+="<col name=\"is_need_reply\" value=\""+(null!=gdjbxxModel.getsfhf_bj()&&gdjbxxModel.getsfhf_bj()?1:0)+"\" dis=\"是否回访 1回访 0不回访\" />";
        xml+="<col name=\"pri_field1\" value=\""+gdjbxxModel.gethfhm()+"\" dis=\"联系电话：\" />";
        xml+="<col name=\"pri_field3\" value=\""+gdjbxxModel.getsqrhm()+"\" dis=\"手机号码：\" />";
        xml+="<col name=\"request_email\" value=\"\" dis=\"E-MAIL：\" />";
        xml+="<col name=\"request_add\" value=\""+getAddress(gdjbxxModel)+"\" dis=\"联系地址：\" />";
        xml+="<col name=\"register_date\" value=\""+Utility.formatDateTime(gdjbxxModel.getlr_sj(),false)+"\" dis=\"受理时间：不能为空\" />";
        xml+="<col name=\"event_time\" value=\""+Utility.formatDateTime(gdjbxxModel.getsqsjfssj(),false)+"\" dis=\"事发时间：不能为空\" />";
        xml+="<col name=\"is_secrecy\" value=\""+(gdjbxxModel.getsfbm_bj()?"1":"0")+"\" dis=\"是否保密 1保密 0不保密\" />";
        xml+="<col name=\"file_url\" value=\""+atta+"\" dis=\"附件\" />";
        xml+="</data>";
        xml+="</request>";
        return xml;
    }
    private String getAddress(TSqblGdjbxxModel gdjbxxModel){
        String str=gdjbxxModel.getsqsjfsdz();
        if(!Utility.isNullOrEmpty(gdjbxxModel.getjd_mc())&&
                !Utility.isNullOrEmpty(gdjbxxModel.getsq_mc())&&
                !Utility.isNullOrEmpty(gdjbxxModel.getwxq_mc())){
            str+="[微信群:"+gdjbxxModel.getjd_mc()+"->"+gdjbxxModel.getsq_mc()+"->"+gdjbxxModel.getwxq_mc()+"]";
        }
        return str;
    }
    @RequestMapping(value = "/loadGroupData",method = RequestMethod.GET)
    public ResponseEntity<BaseResultModel> loadGroupData(HttpServletRequest request, BaseQueryModel queryModel){
        BaseResultModel baseResultModel = new BaseResultModel();
        if(Utility.isNullOrEmpty( queryModel.getJd_token())|| !SystemSetting.SYS_API_KEY.equals(queryModel.getJd_token())){
            logger.info("api jd_token is error");
        }
        String filePath= Utility.Path_Combine(sysProperties.getDocs(),"group_data.xlsx");
        if(Utility.File_Exists(filePath)){
            ExcelService service=new ExcelService();
            HashMap<Integer,HashMap<Integer,Object>> hashMapHashMap = new HashMap<>();
            hashMapHashMap = service.getExcelData(filePath);
            if(null!=hashMapHashMap){
                HashMap<String,TXtGyJdModel> jdModelHashMap=new HashMap<>();
                HashMap<String,TXtGySqModel> sqModelHashMap=new HashMap<>();
                HashMap<String,TXtGyQzModel> qzModelHashMap=new HashMap<>();
                for(HashMap<Integer,Object> map : hashMapHashMap.values()){
                    TXtGyQzModel model= getModelByHashMap(map);
                    if(!qzModelHashMap.containsKey(model.getgroup_id()))
                        qzModelHashMap.put(model.getgroup_id(),model);
                    TXtGyJdModel jdModel=new TXtGyJdModel();
                    jdModel.setstreet_id(model.getstreet_id());
                    jdModel.setstreet_name(model.getstreet_name());
                    if(!jdModelHashMap.containsKey(jdModel.getstreet_id()))
                        jdModelHashMap.put(jdModel.getstreet_id(),jdModel);

                    TXtGySqModel sqModel=new TXtGySqModel();
                    sqModel.setcommunity_name(model.getcommunity_name());
                    sqModel.setcommunity_id(model.getcommunity_id());
                    sqModel.setstreet_id(model.getstreet_id());
                    sqModel.setstreet_name(model.getstreet_name());
                    if(!sqModelHashMap.containsKey(sqModel.getcommunity_id())){
                        sqModelHashMap.put(sqModel.getcommunity_id(),sqModel);
                    }
                }

                //持久化
                DataService dataService=this.getDataService();
                DataContext dataContext=dataService.getDataContext(druidConfig);
                TXtGyQzDao qzDao=new TXtGyQzDao(dataContext);
                TXtGySqDao sqDao=new TXtGySqDao(dataContext);
                TXtGyJdDao jdDao=new TXtGyJdDao(dataContext);
                try{
                    for(TXtGyQzModel qzModel: qzModelHashMap.values()){
                        qzModel.setcreate_time(new Date());
                        qzModel.setis_valid(1);
                        qzDao.deleteOnSubmit(qzModel);
                        qzDao.insertOnSubmit(qzModel);
                    }
                    for(TXtGySqModel sqModel:sqModelHashMap.values()){
                        sqModel.setis_valid(1);
                        sqModel.setcreate_time(new Date());
                        sqDao.deleteOnSubmit(sqModel);
                        sqDao.insertOnSubmit(sqModel);
                    }
                    for (TXtGyJdModel jdModel:jdModelHashMap.values()){
                        jdModel.setcreate_time(new Date());
                        jdModel.setis_valid(1);
                        jdDao.deleteOnSubmit(jdModel);
                        jdDao.insertOnSubmit(jdModel);
                    }
                }catch (Exception err){
                    err.printStackTrace();
                    logger.error(err.getMessage());
                }finally {
                    dataContext.release();
                    dataService.disposeInCurrentThread();
                }
            }
        }
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }

    private TXtGyQzModel getModelByHashMap(HashMap<Integer,Object> map){
        TXtGyQzModel model=new TXtGyQzModel();
        model.setstreet_id(map.get(0).toString());
        model.setstreet_name(map.get(1).toString());
        model.setcommunity_id(map.get(2).toString());
        model.setcommunity_name(map.get(3).toString());
        model.setgroup_id(map.get(4).toString());
        model.setgroup_name(map.get(5).toString());
        return model;
    }

    @RequestMapping(value = "/loadGroupCache",method = RequestMethod.GET)
    public ResponseEntity<BaseResultModel> loadGroupCache(HttpServletRequest request, BaseQueryModel queryModel){
        BaseResultModel baseResultModel = new BaseResultModel();
        if(Utility.isNullOrEmpty( queryModel.getJd_token())|| !SystemSetting.SYS_API_KEY.equals(queryModel.getJd_token())){
            logger.info("api jd_token is error");
        }

        DataService dataService=this.getDataService();
        DataContext dataContext=dataService.getDataContext(druidConfig);
        TXtGyQzDao qzDao=new TXtGyQzDao(dataContext);
        TXtGySqDao sqDao=new TXtGySqDao(dataContext);
        TXtGyJdDao jdDao=new TXtGyJdDao(dataContext);
        List<BaseModel> list= qzDao.findAll(" is_valid = 1");
        List<BaseModel> list2= sqDao.findAll(" is_valid = 1");
        List<BaseModel> list3= jdDao.findAll(" is_valid = 1");
        for(BaseModel baseModel:list){
            TXtGyQzModel qzModel=(TXtGyQzModel)baseModel;
            SystemSetting.JD_ServerCache.updateQzCacheModel(qzModel);
        }
        for(BaseModel baseModel:list2){
            TXtGySqModel sqModel=(TXtGySqModel)baseModel;
            SystemSetting.JD_ServerCache.updateSqCacheModel(sqModel);
        }
        for(BaseModel baseModel:list3){
            TXtGyJdModel jdModel=(TXtGyJdModel)baseModel;
            SystemSetting.JD_ServerCache.updateJdCacheModel(jdModel);
        }
        dataContext.release();
        dataService.disposeInCurrentThread();
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }
    private boolean createQrcodeSaveImage(String group_id,String path){
        boolean result=false;
        if(Utility.isNullOrEmpty(SystemSetting.SYS_WECHAT_TOKEN))return false;
        try {
            URL url = new URL("https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token="+SystemSetting.SYS_WECHAT_TOKEN);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            paramJson.put("path", "pages/complain/main?group_id="+group_id);
            paramJson.put("width", 430);
            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());

            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            //buff用于存放循环读取的临时数据
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = bis.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            ByteArrayInputStream inputStream= new ByteArrayInputStream(swapStream.toByteArray());
            BufferedImage image = ImageIO.read(inputStream);
            /**裁剪原图  目前访问微信 微信返回的是 470*535 像素 170620*/
            BufferedImage subImage = image.getSubimage(0, 0, image.getWidth(), (int) (image.getHeight() * 0.85));
            System.out.println(QrCodeUtils.decodeQrcode(subImage));
            BufferedImage inputbig = new BufferedImage(256, 256, BufferedImage.TYPE_INT_BGR);
            Graphics2D g = (Graphics2D) inputbig.getGraphics();
            g.drawImage(subImage, 0, 0,256,256,null); //画图
            g.dispose();
            inputbig.flush();
            System.out.println(path);
            ImageIO.write(inputbig, "jpg", new File(path));
            result=true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(value = "/createQrcode",method = RequestMethod.GET)
    public ResponseEntity<BaseResultModel> createQrcode(HttpServletRequest request, BaseQueryModel queryModel){
        BaseResultModel baseResultModel = new BaseResultModel();
        if(Utility.isNullOrEmpty( queryModel.getJd_token())|| !SystemSetting.SYS_API_KEY.equals(queryModel.getJd_token())){
            logger.info("api jd_token is error");
            baseResultModel.setSuccess(false);
            baseResultModel.setMsg("参数异常，请检查");
            return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
        }

        DataService dataService=this.getDataService();
        DataContext dataContext=dataService.getDataContext(druidConfig);
        TXtGyQzDao qzDao=new TXtGyQzDao(dataContext);
        List<BaseModel> list=qzDao.findAll("qrcode_path is null limit 300");
        try {
            if(list.size()>0){
                int index=0;
                Calendar calendar=Calendar.getInstance();
                String dt=Utility.formatDateTime(new Date(),true);
                for(BaseModel model:list){
                    TXtGyQzModel qzModel=(TXtGyQzModel)model;
                    String dir_path=sysProperties.getDocs()+File.separator+"ys_qrcode"+File.separator+dt+File.separator+calendar.get(Calendar.HOUR_OF_DAY);
                    String path=dir_path+File.separator+qzModel.getgroup_id()+".png";
                    if(!Utility.Directory_Exists(dir_path)){
                        Utility.File_Mkdirs(dir_path);
                    }
                    if(!Utility.File_Exists(path)){
                        if(createQrcodeSaveImage(qzModel.getgroup_id(),path)){
                            qzModel.doUpdate();
                            qzModel.setqrcode_create_time(new Date());
                            qzModel.setqrcode_path(path);
                            qzDao.updateOnSubmit(qzModel);
                        }
                    }
                    index++;
                    if(index%10==0){
                        System.out.println(index+"/"+list.size());
                    }
                }
                System.out.println("process is end");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }finally {
            dataContext.release();
            dataService.disposeInCurrentThread();
        }
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }
}
