package com.joindoo.jdwechat.controller.sqbl;

import com.joindoo.jdwechat.controller.*;
import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.cache.SessionModel;
import com.joindoo.jdwechat.beans.*;
import com.joindoo.jdwechat.daos.*;
import com.joindoo.jdwechat.codeGen.*;
import com.joindoo.jdwechat.data.DBHelper;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.entity.config.SysProperties;
import com.joindoo.jdwechat.model.BaseResultModel;
import com.joindoo.jdwechat.model.ResultListModel;
import com.joindoo.jdwechat.model.query.*;
import com.joindoo.jdwechat.utils.EmojiFilter;
import com.joindoo.jdwechat.utils.JwtUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.sql.ResultSet;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Controller;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("Sqbl")
public class TSqblGdjbxxController  extends BaseController{
	 @Autowired
    DruidConfig druidConfig;

    @Autowired
    SysProperties sysProperties;

	@RequestMapping("/page")
	public ModelAndView page() {
		ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("sqbl/TSqblGdjbxx");
		 return modelAndView;
	}

	@RequestMapping(value = "/addOrEditPage",method = RequestMethod.GET)
	public ModelAndView addOrEditPage(String id) {
        ModelAndView modelAndView = new ModelAndView();
		TSqblGdjbxxModel viewModel=new TSqblGdjbxxModel();
		if (!StringUtils.isEmpty(id) && !"undefined".equals(id)) {
			DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
			TSqblGdjbxxDao dao = new TSqblGdjbxxDao(dataContext);
			String s =Utility.String_Format( "SELECT * FROM {0} WHERE id=?",TSqblGdjbxxModel.TABLE_NAME);
			 List<BaseModel> baseModels = dao.queryAll(s,id);
            if (!ObjectUtils.isEmpty(baseModels) && baseModels.size() > 0) {
                BaseModel baseModel = baseModels.get(0);
				viewModel= (TSqblGdjbxxModel)baseModel;
			}
			dataService.disposeInCurrentThread();
		}
		modelAndView.addObject("viewModel", viewModel);
		modelAndView.setViewName("sqbl/TSqblGdjbxxEdit");
		return modelAndView;
	}

	@RequestMapping(value = "/search",method = RequestMethod.GET)
	public ResponseEntity<ResultListModel> search(HttpServletRequest request,PagingOptions pagingOptions, TSqblGdjbxxQueryModel queryModel) {
		ResultListModel resultListModel = new ResultListModel();
        SessionModel sessionModel=this.getSessionModel(request);
        DataService dataService = this.getDataService();
        DataContext dataContext = dataService.getDataContext(druidConfig);

		if(pagingOptions.getPageSize()==0)pagingOptions.setPageSize(20);
		pagingOptions.setNeedTotal(true);
		List<TSqblGdjbxxDtoModel> dtoModelList=dataService.selectT_SQBL_GDJBXX(pagingOptions,queryModel);
		int start = pagingOptions.getStart();
		resultListModel.setRows(dtoModelList);
        resultListModel.setStart(start);
        resultListModel.setResultCount(dtoModelList.size());
        resultListModel.setTotal(pagingOptions.getTotal());
		dataContext.release();
        dataService.disposeInCurrentThread();
        return new ResponseEntity<>(resultListModel, HttpStatus.OK);
    }

	@RequestMapping(value = "/remove",method = RequestMethod.GET)
	public ResponseEntity<BaseResultModel> remove(HttpServletRequest request, String ids) {
		BaseResultModel baseResultModel = new BaseResultModel();
		SessionModel sessionModel=this.getSessionModel(request);
		DataService dataService = this.getDataService();
		DataContext dataContext = dataService.getDataContext(druidConfig);
		TSqblGdjbxxDao dao = new TSqblGdjbxxDao(dataContext);
		String[] split = ids.split(",");
		String sql_where=Utility.String_Format(" id in ('{0}')",StringUtils.arrayToDelimitedString(split,"','"));
		List<BaseModel> baseModels= dao.findAll(sql_where);
		for (BaseModel model : baseModels) {
			try {
				dao.deleteOnSubmit(model);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		baseResultModel.setSuccess(true);
		dataService.disposeInCurrentThread();
		return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/SaveGdjbxx",method = RequestMethod.POST)
	public ResponseEntity<BaseResultModel> SaveGdjbxx(@RequestBody TSqblGdjbxxDtoModel dtoModel, HttpServletRequest request) {
		BaseResultModel baseResultModel = new BaseResultModel();
		SessionModel sessionModel=this.getSessionModel(request);
		DataService dataService = this.getDataService();
		DataContext dataContext = dataService.getDataContext(druidConfig);
		TSqblGdjbxxDao dao = new TSqblGdjbxxDao(dataContext);
		TSqblGdjbxxModel model=new TSqblGdjbxxModel();
		try {
			if(null!=dtoModel.getsfsj()){
				dtoModel.setsfsj(dtoModel.getsfsj().replace("T"," "));
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				dtoModel.setsqsjfssj(format.parse(dtoModel.getsfsj()));
			}
			BeanUtils.copyProperties(dtoModel,model);
			model.setid(Utility.createUniqueId());
			model.setgdbh(this.generateGdbh(dataService));
			model.setlr_sj(new Date());
			//处理一些表情字符
//			if(EmojiManager.isEmoji(model.getsqrxm()))
//				model.setsqrxm(EmojiParser.removeAllEmojis(model.getsqrxm()));
//			if(EmojiManager.isEmoji(model.getsqrdz()))
//				model.setsqrdz(EmojiParser.removeAllEmojis(model.getsqrdz()));
//			if(EmojiManager.isEmoji(model.getsqnr()))
//				model.setsqnr(EmojiParser.removeAllEmojis(model.getsqnr()));
//			if(EmojiManager.isEmoji(model.getsqsjfsdz()))
//				model.setsqsjfsdz(EmojiParser.removeAllEmojis(model.getsqsjfsdz()));
			model.setsqnr(EmojiFilter.filterEmoji(model.getsqnr()));
			dao.insertOnSubmit(model);
			baseResultModel.setSuccess(true);

			if(null!=dtoModel.getfj_xhs())
				this.SaveFjxx(dataService,model.getgdbh(),dtoModel.getfj_xhs());

		}catch (Exception err){
			err.printStackTrace();
			logger.error(err.getMessage());
			baseResultModel.setSuccess(false);
			baseResultModel.setMsg(err.getMessage());
		}

		dataService.disposeInCurrentThread();
		return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
	}

	/**
	 * 保存附件记录 - 一个工单对应多个附件
	 * @param dataService
	 * @param fj_xhs
	 * @return
	 */
	private Boolean SaveFjxx(DataService dataService,String gdbh,String fj_xhs){
		if(null==gdbh||null==fj_xhs)
			return  false;

		try {
			DataContext dataContext = dataService.getDataContext(druidConfig);
			TSqblFjDao dao = new TSqblFjDao(dataContext);
			String[] fjxhs = fj_xhs.split(";");
			for (String fj_xh : fjxhs) {
				if(null==fj_xh.trim()||""==fj_xh.trim())
					continue;
				TSqblFjModel model = new TSqblFjModel();
				model.setfj_xh(fj_xh);
				model.setgdbh(gdbh);
				model.setfj_lj(this.getFilePathByFjxh(fj_xh));
				dao.insertOnSubmit(model);
			}
		}
		catch (Exception err){
			logger.error(err.getMessage());
			return false;
		}

		return  true;
	}

	/**
	 * 根据附件序号，推算出文件路径
	 * 2018-06-21-11-ad380c7d736f45e082b562eb81c42e94.jpg
	 * @param fj_xh
	 * @return
	 */
	private String getFilePathByFjxh(String fj_xh){
		if(null==fj_xh) return null;
		String[] items = fj_xh.split("-");
		if(items.length<5) return null;

		String dt=items[0]+"-"+items[1]+"-"+items[2];
		String hour=items[3];
		String fileName=items[4];
		String path= Utility.Path_Combine( sysProperties.getDocs(),"Files"+ File.separator+dt+ File.separator+hour+File.separator+fileName);

		return path;
	}

	/**
	 * GDBH生成规则：WX 180620 0001
	 * WX:代表通过微信提交；改为YS 云社
	 * 180620：年月日；
	 * 0001：序号
	 */
	private String generateGdbh(DataService dataService){
		Calendar cal=Calendar.getInstance();
		String year=String.valueOf(cal.get(cal.YEAR)).substring(2);
		String month=String.valueOf(cal.get(cal.MONTH)+1);
		String day=String.valueOf(cal.get(cal.DATE));
		if(month.length()==1) month="0"+month;
		if(day.length()==1) day="0"+day;

		String gdbh="YS"+year+month+day;
		String sql="SELECT * FROM t_sqbl_gdjbxx WHERE GDBH LIKE '%"+gdbh+"%' ORDER BY GDBH DESC LIMIT 1";
		TSqblGdjbxxDao dao = new TSqblGdjbxxDao(dataService.getDataContext(druidConfig));

		int num=1;
		ResultSet rs= DBHelper.executeQuery(DataContext.getCurrentConnection(),sql);
		List<BaseModel> list=new ArrayList<BaseModel>();
		dao.getData2ModelList(rs,list);
		DBHelper.free(rs);
		if(null!=list&&list.size()>0){
			String maxGdbh=((TSqblGdjbxxModel) list.get(0)).getgdbh();
			num=Integer.parseInt(maxGdbh.substring(8))+1;
		}
		String xh="";
		if(num<10)xh="000"+num;
		else if(num<100)xh="00"+num;
		else if(num<1000)xh="0"+num;
		gdbh+=xh;
		return  gdbh;
	}

}

