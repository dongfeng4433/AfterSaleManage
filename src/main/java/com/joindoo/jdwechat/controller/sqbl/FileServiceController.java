package com.joindoo.jdwechat.controller.sqbl;

import com.joindoo.jdwechat.SystemSetting;
import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.beans.BaseModel;
import com.joindoo.jdwechat.beans.TSqblFjModel;
import com.joindoo.jdwechat.cache.SessionModel;
import com.joindoo.jdwechat.codeGen.TSqblGdjbxxDtoModel;
import com.joindoo.jdwechat.controller.BaseController;
import com.joindoo.jdwechat.daos.TSqblFjDao;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.entity.config.SysProperties;
import com.joindoo.jdwechat.model.BaseResultModel;
import com.joindoo.jdwechat.model.query.BaseQueryModel;
import com.joindoo.jdwechat.utils.HttpContentTypes;
import com.joindoo.jdwechat.utils.ZipUtil;
import org.apache.logging.log4j.core.util.FileUtils;
import org.aspectj.util.FileUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("FileService")
public class FileServiceController extends BaseController {
    @Autowired
    DruidConfig druidConfig;

    @Autowired
    SysProperties sysProperties;

    /**
     * 文件上传 - 包含照片、音频
     * @param request
     * @param queryModel
     * @return
     */
    @RequestMapping(value = "uploadFile") // ,method = RequestMethod.POST
    public ResponseEntity<BaseResultModel> uploadFile(HttpServletRequest request, BaseQueryModel queryModel) {
        BaseResultModel baseResultModel = new BaseResultModel();
//        SessionModel sessionModel = this.getSessionModel(request);
//        if (!sessionModel.isLogin()) {
//            baseResultModel.setSuccess(false);
//            baseResultModel.setMsg("未登录或会话过期");
//            return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
//        }
        if(Utility.isNullOrEmpty( queryModel.getJd_token())|| !SystemSetting.SYS_API_KEY.equals(queryModel.getJd_token())){
            logger.info("api jd_token is error");
        }

        MultipartRequest multipartRequest = getMultipartRequest(request);
        Collection<List<MultipartFile>> multifiles = multipartRequest.getMultiFileMap().values();
        List<MultipartFile> files=new ArrayList<>();
        for(List<MultipartFile> temp:multifiles)
            files.addAll(temp);
        try {
            for (MultipartFile file : files) {
                String dt = Utility.formatDateTime(new Date(), true);
                int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                String path = Utility.Path_Combine(sysProperties.getDocs(), "Files" + File.separator + dt + File.separator + hour + File.separator);
                if (!Utility.File_Exists(path))
                    Utility.File_Mkdirs(path);

                String oldName = file.getOriginalFilename();
                String prefix = oldName.substring(oldName.lastIndexOf("."));
                String newName = Utility.createUniqueId() + prefix;

                file.transferTo(new File(path + newName));

                baseResultModel.setSuccess(true);
                baseResultModel.setTag(this.getAttachementId(dt, hour, newName));

                break;
            }
            //JSONObject jsonObject=new JSONObject("{\"\":\"\"}");
            return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
    }

    private String getAttachementId(String dt,int hour,String fileName){

        return dt + "-" + hour + "-" +fileName;
    }

    /**
     * 一个工单可能对应多个文件（照片，录音）
     * 将所有文件打包成一个压缩包下载
     *
     * @param gdbh
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/downloadFile")
    public ModelAndView downloadFile(HttpServletResponse response, HttpServletRequest request, String gdbh) throws Exception {
        if (null == gdbh) return null;

        DataService dataService = this.getDataService();
        DataContext dataContext = dataService.getDataContext(druidConfig);
        TSqblFjDao dao = new TSqblFjDao(dataContext);

        String sql_where = Utility.String_Format(" gdbh='{0}'", gdbh);
        List<BaseModel> baseModels = dao.findAll(sql_where);

        String tempPath=Utility.Path_Combine(sysProperties.getDocs(), "Temp"+File.separator);
        if(!Utility.File_Exists(tempPath))
            Utility.File_Mkdirs(tempPath);

        String zipPath = Utility.Path_Combine(tempPath, gdbh + ".zip");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipPath));
        try {
            for (BaseModel item : baseModels) {
                TSqblFjModel model = (TSqblFjModel) item;
                if (null == model.getfj_lj())
                    continue;
                File file = new File(model.getfj_lj());
                if (file.exists())
                    ZipUtil.doCompress(file, out);

            }
            out.close();
            File zipFile = new File(zipPath);
            if (new File(zipPath).exists())
                return this.file(response, request, zipFile.getPath(), HttpContentTypes.Application_Zip, gdbh + ".zip");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    @RequestMapping("/test")
    public ModelAndView testPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sqbl/testPage");
        return modelAndView;
    }

}
