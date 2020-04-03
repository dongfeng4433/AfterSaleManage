package com.joindoo.jdwechat.controller;

import com.joindoo.jdwechat.WeChatFields;
import com.joindoo.jdwechat.codeGen.beans.TXtAqZhxxDtoModel;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.entity.config.SysProperties;
import com.joindoo.jdwechat.model.query.PagingOptions;
import com.joindoo.jdwechat.model.query.TXtAqZhxxQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Configuration
@EnableScheduling
@Controller
@RequestMapping("/TimedTask")
public class TimedTaskController extends BaseController {
    @Autowired
    SysProperties sysProperties;
    @Autowired
    DruidConfig druidConfig;
   // @Scheduled(cron = "0 0/10 * * * ?") // 每10分钟执行一次
//    @Scheduled(fixedRate = 10000) //10秒一次
    public void getToken() {
        DataService dataService = this.getDataService();
       try{
           //查询距离回复时间还有15分钟，没有回复的人员给出短信
           String dateStr =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
           TXtAqZhxxQueryModel queryModel=new TXtAqZhxxQueryModel();
           queryModel.setsfyx_bj(1);
           String sql= WeChatFields.Script_SelectSendRyByXxlr;
           List<TXtAqZhxxDtoModel> list = dataService.selectT_XT_AQ_ZHXX(new PagingOptions(), queryModel,sql);
//           TTxDxDxfsjlDao dao=new TTxDxDxfsjlDao();
           for(TXtAqZhxxDtoModel model :list){
               logger.info(model.getmc()+"有待办事项，请尽快登录系统处理"+dateStr);
//               TTxDxDxfsjlModel m=new TTxDxDxfsjlModel();
//               m.setdxfsjl_xh(Utility.createUniqueId());
//               m.setsj_hm(model.getsj_hm());
//               m.setnr(model.getmc()+"有待办事项，请尽快登录系统处理");
//               m.setfs_type(WeChatEnums._自动发送.getIndex());
//               m.setlr_sj(new Date());
//               dao.insertOnSubmit(m);
           }
       }catch(Exception e){
           logger.info(e.getMessage());
       }finally{
           dataService.disposeInCurrentThread();
       }
    }
}
