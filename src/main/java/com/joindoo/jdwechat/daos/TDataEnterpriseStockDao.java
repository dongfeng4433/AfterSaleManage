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
* 主数据 - 企业 - 库存每个企业自身的货品配件库存数据
* Author: zhuqiang4433@gmail.com
* Version: CodeGenerator 1.1
* Memo: Auto Created by CodeGenerator on 2020/4/20.
*/

public class TDataEnterpriseStockDao extends BaseDao {
	public TDataEnterpriseStockDao(){super();}
	public TDataEnterpriseStockDao(DataContext dataContext){
		super(dataContext);
	}


  @Override
	public boolean exist(BaseModel model) {
	    String sql=((TDataEnterpriseStockModel)model).getExist();
      return DBHelper.isExist(DataContext.getCurrentConnection(), sql);
   }


   @Override
   public void insert(BaseModel model) {
       String sql=((TDataEnterpriseStockModel)model).getInsert();
       DBHelper.executeNonQuery(DataContext.getCurrentConnection(),sql);
   }
  public boolean insertOnSubmit(BaseModel model) throws Exception {
	TDataEnterpriseStockModel bean= (TDataEnterpriseStockModel)model;
	 PreparedStatement statement=DBHelper.getPreparedStatement(DataContext.getCurrentConnection(),bean.SCRIPT_INSERT);
	 Object t=null;
	 int index=1;
	 t=bean.getstock_id();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getgoods_id();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getenterprise_id();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getwarehouse_id();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getname();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getunit_id();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getquantity();
	 if (t==null) statement.setNull(index, Types.NUMERIC);
	 else statement.setObject(index, t, Types.NUMERIC);
	 index++;

	 t=bean.getunit_id2();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getquantity2();
	 if (t==null) statement.setNull(index, Types.NUMERIC);
	 else statement.setObject(index, t, Types.NUMERIC);
	 index++;

	 t=bean.getaddress();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getdescription();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getis_valid();
	 if (t==null) statement.setNull(index, Types.INTEGER);
	 else statement.setObject(index, t, Types.INTEGER);
	 index++;

	 t=bean.getcreate_time();
	 if (t==null) statement.setNull(index, Types.TIMESTAMP);
	 else statement.setObject(index, new java.sql.Date(((java.util.Date)t).getTime()), Types.TIMESTAMP);
	 index++;

	 t=bean.getlast_edit_time();
	 if (t==null) statement.setNull(index, Types.TIMESTAMP);
	 else statement.setObject(index, new java.sql.Date(((java.util.Date)t).getTime()), Types.TIMESTAMP);
	 index++;

	 t=bean.getcreation_user_id();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getlast_edit_user_id();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getprice();
	 if (t==null) statement.setNull(index, Types.NUMERIC);
	 else statement.setObject(index, t, Types.NUMERIC);
	 index++;

	 t=bean.getamount();
	 if (t==null) statement.setNull(index, Types.NUMERIC);
	 else statement.setObject(index, t, Types.NUMERIC);
	 index++;

	 t=bean.getunit_name();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 t=bean.getunit2_name();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 int r= statement.executeUpdate();
	 DBHelper.free(statement);
	 return r > 0;
    }


  @Override
  public void update(BaseModel model) {
	 String sql=((TDataEnterpriseStockModel)model).getUpdate();
	 if(sql!=null&&sql!=""){
	 DBHelper.executeNonQuery(DataContext.getCurrentConnection(),sql);
	 }
  }
   public boolean updateOnSubmit(BaseModel model) throws Exception {
	 TDataEnterpriseStockModel bean= (TDataEnterpriseStockModel)model;
	 Map<String,Object> changeFields=bean.getChanges();
	 if(changeFields.size()==0)return false;
	 Object t=null;
	 StringBuilder sb=new StringBuilder();
	 if(changeFields.containsKey("goods_id"))sb.append("goods_id=?,");
	 if(changeFields.containsKey("enterprise_id"))sb.append("enterprise_id=?,");
	 if(changeFields.containsKey("warehouse_id"))sb.append("warehouse_id=?,");
	 if(changeFields.containsKey("name"))sb.append("name=?,");
	 if(changeFields.containsKey("unit_id"))sb.append("unit_id=?,");
	 if(changeFields.containsKey("quantity"))sb.append("quantity=?,");
	 if(changeFields.containsKey("unit_id2"))sb.append("unit_id2=?,");
	 if(changeFields.containsKey("quantity2"))sb.append("quantity2=?,");
	 if(changeFields.containsKey("address"))sb.append("address=?,");
	 if(changeFields.containsKey("description"))sb.append("description=?,");
	 if(changeFields.containsKey("is_valid"))sb.append("is_valid=?,");
	 if(changeFields.containsKey("create_time"))sb.append("create_time=?,");
	 if(changeFields.containsKey("last_edit_time"))sb.append("last_edit_time=?,");
	 if(changeFields.containsKey("creation_user_id"))sb.append("creation_user_id=?,");
	 if(changeFields.containsKey("last_edit_user_id"))sb.append("last_edit_user_id=?,");
	 if(changeFields.containsKey("price"))sb.append("price=?,");
	 if(changeFields.containsKey("amount"))sb.append("amount=?,");
	 if(changeFields.containsKey("unit_name"))sb.append("unit_name=?,");
	 if(changeFields.containsKey("unit2_name"))sb.append("unit2_name=?,");
	 sb.deleteCharAt(sb.length() - 1);
	 String sql= Utility.String_Format(bean.SCRIPT_UPDATE,sb.toString());
	 PreparedStatement statement=DBHelper.getPreparedStatement(DataContext.getCurrentConnection(),sql);
	 int index=1;
	 /*非主键*/
	 if(changeFields.containsKey("goods_id")){
		 t=bean.getgoods_id();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("enterprise_id")){
		 t=bean.getenterprise_id();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("warehouse_id")){
		 t=bean.getwarehouse_id();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("name")){
		 t=bean.getname();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("unit_id")){
		 t=bean.getunit_id();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("quantity")){
		 t=bean.getquantity();
		 if (t==null) statement.setNull(index, Types.NUMERIC);
		 else statement.setObject(index, t, Types.NUMERIC);
		 index++;
	 }
	 if(changeFields.containsKey("unit_id2")){
		 t=bean.getunit_id2();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("quantity2")){
		 t=bean.getquantity2();
		 if (t==null) statement.setNull(index, Types.NUMERIC);
		 else statement.setObject(index, t, Types.NUMERIC);
		 index++;
	 }
	 if(changeFields.containsKey("address")){
		 t=bean.getaddress();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("description")){
		 t=bean.getdescription();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("is_valid")){
		 t=bean.getis_valid();
		 if (t==null) statement.setNull(index, Types.INTEGER);
		 else statement.setObject(index, t, Types.INTEGER);
		 index++;
	 }
	 if(changeFields.containsKey("create_time")){
		 t=bean.getcreate_time();
		 if (t==null) statement.setNull(index, Types.TIMESTAMP);
		 else statement.setObject(index, new java.sql.Date(((java.util.Date)t).getTime()), Types.TIMESTAMP);
		 index++;
	 }
	 if(changeFields.containsKey("last_edit_time")){
		 t=bean.getlast_edit_time();
		 if (t==null) statement.setNull(index, Types.TIMESTAMP);
		 else statement.setObject(index, new java.sql.Date(((java.util.Date)t).getTime()), Types.TIMESTAMP);
		 index++;
	 }
	 if(changeFields.containsKey("creation_user_id")){
		 t=bean.getcreation_user_id();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("last_edit_user_id")){
		 t=bean.getlast_edit_user_id();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("price")){
		 t=bean.getprice();
		 if (t==null) statement.setNull(index, Types.NUMERIC);
		 else statement.setObject(index, t, Types.NUMERIC);
		 index++;
	 }
	 if(changeFields.containsKey("amount")){
		 t=bean.getamount();
		 if (t==null) statement.setNull(index, Types.NUMERIC);
		 else statement.setObject(index, t, Types.NUMERIC);
		 index++;
	 }
	 if(changeFields.containsKey("unit_name")){
		 t=bean.getunit_name();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 if(changeFields.containsKey("unit2_name")){
		 t=bean.getunit2_name();
		 if (t==null) statement.setNull(index, Types.VARCHAR);
		 else statement.setObject(index, t, Types.VARCHAR);
		 index++;
	 }
	 /*主键条件*/
	 t=bean.getstock_id();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 int r= statement.executeUpdate();
	 DBHelper.free(statement);
	 return r > 0;
  }

    @Override
    public TDataEnterpriseStockModel find(BaseModel model) {
	 TDataEnterpriseStockModel tModel=(TDataEnterpriseStockModel)model;
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
		TDataEnterpriseStockModel tModel=new TDataEnterpriseStockModel();
		String sql=tModel.getSelectByCondition(condition);
       List<BaseModel> resultList=new ArrayList<BaseModel>();
        ResultSet set=DBHelper.executeQuery(DataContext.getCurrentConnection(),sql);
        getData2ModelList(set,resultList);
	    DBHelper.free(set);
        return resultList;
    }

    public List<BaseModel> findAll(String condition,Object... objects) {
		TDataEnterpriseStockModel tModel=new TDataEnterpriseStockModel();
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
 				TDataEnterpriseStockModel model=new TDataEnterpriseStockModel();
                model.fillModel(set);
				resultList.add(model);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    @Override
	public String getCountSql(String condition) {
		String sql="select count(*) as count from "+ TDataEnterpriseStockModel.TABLE_NAME;
		return sql;
	}



   @Override
   public void delete(BaseModel model) {
	 String sql=((TDataEnterpriseStockModel)model).getDelete();
	 DBHelper.executeNonQuery(DataContext.getCurrentConnection(),sql);
  }
   public boolean deleteOnSubmit(BaseModel model) throws Exception {
	 TDataEnterpriseStockModel bean= (TDataEnterpriseStockModel)model;
	 PreparedStatement statement=DBHelper.getPreparedStatement(DataContext.getCurrentConnection(),bean.SCRIPT_DELETE);
	 Object t=null;
	 int index=1;
	 t=bean.getstock_id();
	 if (t==null) statement.setNull(index, Types.VARCHAR);
	 else statement.setObject(index, t, Types.VARCHAR);
	 index++;

	 int r= statement.executeUpdate();
	 DBHelper.free(statement);
	 return r > 0;
 }

}