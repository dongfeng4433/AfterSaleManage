package com.joindoo.jdwechat.controller.sj;

import com.joindoo.jdwechat.controller.*;
import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.cache.SessionModel;
import com.joindoo.jdwechat.beans.*;
import com.joindoo.jdwechat.daos.*;
import com.joindoo.jdwechat.codeGen.*;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.data.DataService;
import com.joindoo.jdwechat.entity.config.DruidConfig;
import com.joindoo.jdwechat.entity.config.SysProperties;
import com.joindoo.jdwechat.model.BaseResultModel;
import com.joindoo.jdwechat.model.ResultListModel;
import com.joindoo.jdwechat.model.query.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("JtSjSqblGdjbxx")
public class JtSjSqblGdjbxxController  extends BaseController{
	 @Autowired
    DruidConfig druidConfig;

    @Autowired
    SysProperties sysProperties;

	@RequestMapping("/page")
	public ModelAndView page() {
		ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("sj/JtSjSqblGdjbxx");
		 return modelAndView;
	}
	@RequestMapping(value = "/addOrEditPage",method = RequestMethod.GET)
	public ModelAndView addOrEditPage(String id) {
        ModelAndView modelAndView = new ModelAndView();
		JtSjSqblGdjbxxModel viewModel=new JtSjSqblGdjbxxModel();
		if (!StringUtils.isEmpty(id) && !"undefined".equals(id)) {
			DataService dataService = this.getDataService();
            DataContext dataContext = dataService.getDataContext(druidConfig);
			JtSjSqblGdjbxxDao dao = new JtSjSqblGdjbxxDao(dataContext);
			String s =Utility.String_Format( "SELECT * FROM {0} WHERE id=?",JtSjSqblGdjbxxModel.TABLE_NAME);
			 List<BaseModel> baseModels = dao.queryAll(s,id);
            if (!ObjectUtils.isEmpty(baseModels) && baseModels.size() > 0) {
                BaseModel baseModel = baseModels.get(0);
				viewModel= (JtSjSqblGdjbxxModel)baseModel;
			}
			dataService.disposeInCurrentThread();
		}
		modelAndView.addObject("viewModel", viewModel);
		modelAndView.setViewName("sj/JtSjSqblGdjbxxEdit");
		return modelAndView;
	}
	@RequestMapping(value = "/search",method = RequestMethod.GET)
	public ResponseEntity<ResultListModel> search(HttpServletRequest request,PagingOptions pagingOptions, JtSjSqblGdjbxxQueryModel queryModel) {
		ResultListModel resultListModel = new ResultListModel();
        SessionModel sessionModel=this.getSessionModel(request);
        DataService dataService = this.getDataService();
        DataContext dataContext = dataService.getDataContext(druidConfig);

		if(pagingOptions.getPageSize()==0)pagingOptions.setPageSize(20);
		pagingOptions.setNeedTotal(true);
		List<JtSjSqblGdjbxxDtoModel> dtoModelList=dataService.selectJT_SJ_SQBL_GDJBXX(pagingOptions,queryModel);
		int start = pagingOptions.getStart();
		resultListModel.setRows(dtoModelList);
        resultListModel.setStart(start);
        resultListModel.setResultCount(dtoModelList.size());
        resultListModel.setTotal(pagingOptions.getTotal());
		dataContext.release();
        dataService.disposeInCurrentThread();
        return new ResponseEntity<>(resultListModel, HttpStatus.OK);
    }
	@RequestMapping(value = "/addOrEdit",method = RequestMethod.POST)
	public ResponseEntity<BaseResultModel> addOrEdit(@RequestBody JtSjSqblGdjbxxDtoModel dtoModel, HttpServletRequest request) {
		BaseResultModel baseResultModel = new BaseResultModel();
		SessionModel sessionModel=this.getSessionModel(request);
		DataService dataService = this.getDataService();
		DataContext dataContext = dataService.getDataContext(druidConfig);
		JtSjSqblGdjbxxDao dao = new JtSjSqblGdjbxxDao(dataContext);
		JtSjSqblGdjbxxModel model=new JtSjSqblGdjbxxModel();
		model.setid(dtoModel.getid());
		boolean exist = dao.exist(model);
		try {
			if(exist){
				model=dao.find(model);
				model.doUpdate();
				model.setid(dtoModel.getid());
				model.setgdbh(dtoModel.getgdbh());
				model.setsqmd(dtoModel.getsqmd());
				model.setsqnr(dtoModel.getsqnr());
				model.setsqlx(dtoModel.getsqlx());
				model.sethflx(dtoModel.gethflx());
				model.setgjz(dtoModel.getgjz());
				model.setsjgk(dtoModel.getsjgk());
				model.setsqhm(dtoModel.getsqhm());
				model.setsqr(dtoModel.getsqr());
				model.setldsj(dtoModel.getldsj());
				model.setsqsjfssj(dtoModel.getsqsjfssj());
				model.setsqdz(dtoModel.getsqdz());
				model.setsfjj(dtoModel.getsfjj());
				model.setsfbm(dtoModel.getsfbm());
				model.settbbj(dtoModel.gettbbj());
				model.setguid(dtoModel.getguid());
				model.setcbdw(dtoModel.getcbdw());
				model.setcbdw2(dtoModel.getcbdw2());
				model.setlxdh1(dtoModel.getlxdh1());
				model.setlxdh2(dtoModel.getlxdh2());
				model.setsqr_xb(dtoModel.getsqr_xb());
				model.setsqlyqd(dtoModel.getsqlyqd());
				model.setwjgk(dtoModel.getwjgk());
				model.sethfjg(dtoModel.gethfjg());
				dao.updateOnSubmit(model);
			}else{
				BeanUtils.copyProperties(dtoModel,model);
				model.setid(Utility.createUniqueId());
				dao.insertOnSubmit(model);
			}
			baseResultModel.setSuccess(true);
		}catch (Exception err){
			logger.error(err.getMessage());
			baseResultModel.setSuccess(false);
			baseResultModel.setMsg(err.getMessage());
		}

		dataService.disposeInCurrentThread();
		return new ResponseEntity<>(baseResultModel, HttpStatus.OK);
	}
	@RequestMapping(value = "/remove",method = RequestMethod.GET)
	public ResponseEntity<BaseResultModel> remove(HttpServletRequest request, String ids) {
		BaseResultModel baseResultModel = new BaseResultModel();
		SessionModel sessionModel=this.getSessionModel(request);
		DataService dataService = this.getDataService();
		DataContext dataContext = dataService.getDataContext(druidConfig);
		JtSjSqblGdjbxxDao dao = new JtSjSqblGdjbxxDao(dataContext);
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
	

}

