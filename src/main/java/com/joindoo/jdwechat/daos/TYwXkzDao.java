package com.joindoo.jdwechat.daos;

import com.joindoo.jdwechat.data.DBHelper;
import com.joindoo.jdwechat.data.DataContext;
import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.beans.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
/**
* 业务_许可证   1 人力资源服务许可证  2 劳务派遣经营许可证
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/20.
*/

public class TYwXkzDao extends BaseDao {
	public TYwXkzDao(){super();}
	public TYwXkzDao(DataContext dataContext){
		super(dataContext);
	}


  @Override
	public boolean exist(BaseModel model) {
	    String sql=((TYwXkzModel)model).getExist();
      return DBHelper.isExist(DataContext.getCurrentConnection(), sql);
   }


   @Override
   public void insert(BaseModel model) {
       String sql=((TYwXkzModel)model).getInsert();
       DBHelper.executeNonQuery(DataContext.getCurrentConnection(),sql);
   }
  public boolean insertOnSubmit(BaseModel model) throws Exception {
	TYwXkzModel bean= (TYwXkzModel)model;
	 PreparedStatement statement=DBHelper.getPreparedStatement(DataContext.getCurrentConnection(),bean.SCRIPT_INSERT);
	 Object t=null;
	 int index=1;
	 t=bean.getxkz_xh();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getlx_dm();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getxkz_bh();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getjgdw_mc();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getlbzh();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.gettyshxydm();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getdzzs();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getfddbr_fzr();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getlxdh();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getzczb_je();
	 if (t==null) statement.setNull(index, Types.NUMERIC);
	 else statement.setObject(index, t, Types.NUMERIC);
	 index++;

	 t=bean.getjgxz();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getxkwh();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getfwfw();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getfzrq();
	 if (t==null) statement.setNull(index, Types.TIMESTAMP);
	 else statement.setObject(index, new java.sql.Date(((java.util.Date)t).getTime()), Types.TIMESTAMP);
	 index++;

	 t=bean.getyxrq_jz();
	 if (t==null) statement.setNull(index, Types.TIMESTAMP);
	 else statement.setObject(index, new java.sql.Date(((java.util.Date)t).getTime()), Types.TIMESTAMP);
	 index++;

	 t=bean.getyxrq_qs();
	 if (t==null) statement.setNull(index, Types.TIMESTAMP);
	 else statement.setObject(index, new java.sql.Date(((java.util.Date)t).getTime()), Types.TIMESTAMP);
	 index++;

	 t=bean.getfzjg();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getfj_xh();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getsfyx_bj();
	 if (t==null) statement.setNull(index, Types.INTEGER);
	 else statement.setObject(index, t, Types.INTEGER);
	 index++;

	 t=bean.getms();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getbz();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getlrry_xh();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getxgry_xh();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getlr_sj();
	 if (t==null) statement.setNull(index, Types.TIMESTAMP);
	 else statement.setObject(index, new java.sql.Date(((java.util.Date)t).getTime()), Types.TIMESTAMP);
	 index++;

	 t=bean.getxg_sj();
	 if (t==null) statement.setNull(index, Types.TIMESTAMP);
	 else statement.setObject(index, new java.sql.Date(((java.util.Date)t).getTime()), Types.TIMESTAMP);
	 index++;

	 int r= statement.executeUpdate();
	 DBHelper.free(statement);
	 return r > 0;
    }


  @Override
  public void update(BaseModel model) {
	 String sql=((TYwXkzModel)model).getUpdate();
	 if(sql!=null&&sql!=""){
	 DBHelper.executeNonQuery(DataContext.getCurrentConnection(),sql);
	 }
  }
   public boolean updateOnSubmit(BaseModel model) throws Exception {
	 TYwXkzModel bean= (TYwXkzModel)model;
	 Map<String,Object> changeFields=bean.getChanges();
	 if(changeFields.size()==0)return false;
	 Object t=null;
	 StringBuilder sb=new StringBuilder();
	 if(changeFields.containsKey("lx_dm"))sb.append("lx_dm=?,");
	 if(changeFields.containsKey("xkz_bh"))sb.append("xkz_bh=?,");
	 if(changeFields.containsKey("jgdw_mc"))sb.append("jgdw_mc=?,");
	 if(changeFields.containsKey("lbzh"))sb.append("lbzh=?,");
	 if(changeFields.containsKey("tyshxydm"))sb.append("tyshxydm=?,");
	 if(changeFields.containsKey("dzzs"))sb.append("dzzs=?,");
	 if(changeFields.containsKey("fddbr_fzr"))sb.append("fddbr_fzr=?,");
	 if(changeFields.containsKey("lxdh"))sb.append("lxdh=?,");
	 if(changeFields.containsKey("zczb_je"))sb.append("zczb_je=?,");
	 if(changeFields.containsKey("jgxz"))sb.append("jgxz=?,");
	 if(changeFields.containsKey("xkwh"))sb.append("xkwh=?,");
	 if(changeFields.containsKey("fwfw"))sb.append("fwfw=?,");
	 if(changeFields.containsKey("fzrq"))sb.append("fzrq=?,");
	 if(changeFields.containsKey("yxrq_jz"))sb.append("yxrq_jz=?,");
	 if(changeFields.containsKey("yxrq_qs"))sb.append("yxrq_qs=?,");
	 if(changeFields.containsKey("fzjg"))sb.append("fzjg=?,");
	 if(changeFields.containsKey("fj_xh"))sb.append("fj_xh=?,");
	 if(changeFields.containsKey("sfyx_bj"))sb.append("sfyx_bj=?,");
	 if(changeFields.containsKey("ms"))sb.append("ms=?,");
	 if(changeFields.containsKey("bz"))sb.append("bz=?,");
	 if(changeFields.containsKey("lrry_xh"))sb.append("lrry_xh=?,");
	 if(changeFields.containsKey("xgry_xh"))sb.append("xgry_xh=?,");
	 if(changeFields.containsKey("lr_sj"))sb.append("lr_sj=?,");
	 if(changeFields.containsKey("xg_sj"))sb.append("xg_sj=?,");
	 sb.deleteCharAt(sb.length() - 1);
	 String sql= Utility.String_Format(bean.SCRIPT_UPDATE,sb.toString());
	 PreparedStatement statement=DBHelper.getPreparedStatement(DataContext.getCurrentConnection(),sql);
	 int index=1;
	 /*非主键*/
	 if(changeFields.containsKey("lx_dm")){
		 t=bean.getlx_dm();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("xkz_bh")){
		 t=bean.getxkz_bh();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("jgdw_mc")){
		 t=bean.getjgdw_mc();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("lbzh")){
		 t=bean.getlbzh();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("tyshxydm")){
		 t=bean.gettyshxydm();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("dzzs")){
		 t=bean.getdzzs();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("fddbr_fzr")){
		 t=bean.getfddbr_fzr();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("lxdh")){
		 t=bean.getlxdh();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("zczb_je")){
		 t=bean.getzczb_je();
		 if (t==null) statement.setNull(index, Types.NUMERIC);
		 else statement.setObject(index, t, Types.NUMERIC);
		 index++;
	 }
	 if(changeFields.containsKey("jgxz")){
		 t=bean.getjgxz();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("xkwh")){
		 t=bean.getxkwh();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("fwfw")){
		 t=bean.getfwfw();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("fzrq")){
		 t=bean.getfzrq();
		 if (t==null) statement.setNull(index, Types.TIMESTAMP);
		 else statement.setObject(index, new java.sql.Date(((java.util.Date)t).getTime()), Types.TIMESTAMP);
		 index++;
	 }
	 if(changeFields.containsKey("yxrq_jz")){
		 t=bean.getyxrq_jz();
		 if (t==null) statement.setNull(index, Types.TIMESTAMP);
		 else statement.setObject(index, new java.sql.Date(((java.util.Date)t).getTime()), Types.TIMESTAMP);
		 index++;
	 }
	 if(changeFields.containsKey("yxrq_qs")){
		 t=bean.getyxrq_qs();
		 if (t==null) statement.setNull(index, Types.TIMESTAMP);
		 else statement.setObject(index, new java.sql.Date(((java.util.Date)t).getTime()), Types.TIMESTAMP);
		 index++;
	 }
	 if(changeFields.containsKey("fzjg")){
		 t=bean.getfzjg();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("fj_xh")){
		 t=bean.getfj_xh();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("sfyx_bj")){
		 t=bean.getsfyx_bj();
		 if (t==null) statement.setNull(index, Types.INTEGER);
		 else statement.setObject(index, t, Types.INTEGER);
		 index++;
	 }
	 if(changeFields.containsKey("ms")){
		 t=bean.getms();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("bz")){
		 t=bean.getbz();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("lrry_xh")){
		 t=bean.getlrry_xh();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("xgry_xh")){
		 t=bean.getxgry_xh();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("lr_sj")){
		 t=bean.getlr_sj();
		 if (t==null) statement.setNull(index, Types.TIMESTAMP);
		 else statement.setObject(index, new java.sql.Date(((java.util.Date)t).getTime()), Types.TIMESTAMP);
		 index++;
	 }
	 if(changeFields.containsKey("xg_sj")){
		 t=bean.getxg_sj();
		 if (t==null) statement.setNull(index, Types.TIMESTAMP);
		 else statement.setObject(index, new java.sql.Date(((java.util.Date)t).getTime()), Types.TIMESTAMP);
		 index++;
	 }
	 /*主键条件*/
	 t=bean.getxkz_xh();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 int r= statement.executeUpdate();
	 DBHelper.free(statement);
	 return r > 0;
  }

    @Override
    public TYwXkzModel find(BaseModel model) {
	 TYwXkzModel tModel=(TYwXkzModel)model;
	 String sql=tModel.getSelect();
	 ResultSet set=DBHelper.executeQuery(DataContext.getCurrentConnection(),sql);
	 try {
	     while(set.next()){
	      tModel.fillModel(set);
	    }
	 } catch (SQLException e) {
	    e.printStackTrace();
	 }
	 return tModel;
  }

   @Override
    public List<BaseModel> findAll(String condition) {
		TYwXkzModel tModel=new TYwXkzModel();
		String sql=tModel.getSelectByCondition(condition);
       List<BaseModel> resultList=new ArrayList<BaseModel>();
        ResultSet set=DBHelper.executeQuery(DataContext.getCurrentConnection(),sql);
        getData2ModelList(set,resultList);
	    DBHelper.free(set);
        return resultList;
    }

    public List<BaseModel> findAll(String condition,Object... objects) {
		TYwXkzModel tModel=new TYwXkzModel();
		String sql=tModel.getSelectByCondition(condition);
       List<BaseModel> resultList=new ArrayList<BaseModel>();
        ResultSet set=DBHelper.executeQuery(DataContext.getCurrentConnection(),sql,objects);
        getData2ModelList(set,resultList);
	    DBHelper.free(set);
        return resultList;
    }

	@Override
	public void getData2ModelList(ResultSet set, List<BaseModel> resultList){
		try {
			if(null==set)return;
			while(set.next()){
 				TYwXkzModel model=new TYwXkzModel();
                model.fillModel(set);
				resultList.add(model);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    @Override
	public String getCountSql(String condition) {
		String sql="select count(*) as count from "+ TYwXkzModel.TABLE_NAME;
		return sql;
	}



   @Override
   public void delete(BaseModel model) {
	 String sql=((TYwXkzModel)model).getDelete();
	 DBHelper.executeNonQuery(DataContext.getCurrentConnection(),sql);
  }
   public boolean deleteOnSubmit(BaseModel model) throws Exception {
	 TYwXkzModel bean= (TYwXkzModel)model;
	 PreparedStatement statement=DBHelper.getPreparedStatement(DataContext.getCurrentConnection(),bean.SCRIPT_DELETE);
	 Object t=null;
	 int index=1;
	 t=bean.getxkz_xh();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 int r= statement.executeUpdate();
	 DBHelper.free(statement);
	 return r > 0;
 }

}