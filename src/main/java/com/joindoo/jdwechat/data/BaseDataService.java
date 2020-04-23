package com.joindoo.jdwechat.data;

import com.joindoo.jdwechat.Utility;
import com.joindoo.jdwechat.WeChatFields;
import com.joindoo.jdwechat.data.DataParamsHandler;
import com.joindoo.jdwechat.beans.*;
import com.joindoo.jdwechat.model.query.PagingOptions;
import com.joindoo.jdwechat.model.sys.ScriptItemModel;
import com.joindoo.jdwechat.codeGen.*;
import com.joindoo.jdwechat.model.query.*;
import com.joindoo.jdwechat.SystemSetting;
import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.Collection;
/**
* Author: zhuqiang4433@gmail.com
* Memo: Auto Created by CodeGenerator on 2020/4/17.
*/

public class BaseDataService extends com.joindoo.jdwechat.data.EdenDataService {
	//t_data_customer 主数据 - 客户  可以是个人也可以是企业
	public Collection<TDataCustomerDtoModel> SelectT_DATA_CUSTOMER(PagingOptions pagingOptions, TDataCustomerQueryModel queryModel , String sql) {
		if(Utility.isNullOrEmpty(sql)){
			sql=WeChatFields.Script_SelectT_DATA_CUSTOMER;
		}
		ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
		if(null==scriptItemModel){
			logger.info(sql+" 查询脚本没有找到");
			return null;
		}
		DataParamsHandler dataParamsHandler=new DataParamsHandler(){
			@Override
			public void resolveParams(ScriptItemModel scriptItemModel) {
				ArrayList<Object> params=new ArrayList<>();
				if(null!=queryModel){
					String where="";
					if(null!=queryModel.getcustomer_id()){
						where+=" and CUSTOMER_ID=?";
						params.add(queryModel.getcustomer_id());
					}
					if(null!=queryModel.getname()){
						where+=" and NAME LIKE ?";
						params.add("%"+queryModel.getname()+"%");
					}
					if(null!=queryModel.gettype_code()){
						where+=" and TYPE_CODE=?";
						params.add(queryModel.gettype_code());
					}
					if(null!=queryModel.gettelephone_number()){
						where+=" and TELEPHONE_NUMBER=?";
						params.add(queryModel.gettelephone_number());
					}
					if(null!=queryModel.getenterprise_id()){
						where+=" and ENTERPRISE_ID=?";
						params.add(queryModel.getenterprise_id());
					}
					scriptItemModel.setSqlWhere(where);
                }
				scriptItemModel.setParams(params.toArray());
			}
		};
		queryModel.dataParamsHandler=dataParamsHandler;
		Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TDataCustomerModel.class,scriptItemModel,pagingOptions,queryModel);
		Collection<TDataCustomerDtoModel> models=new ArrayList<>();
		for (IBaseModel b : collection){
			TDataCustomerDtoModel dto=new TDataCustomerDtoModel();
			BeanUtils.copyProperties((TDataCustomerModel)b,dto);
			models.add(dto);
        }
        return models;
    }
	//t_data_enterprise 主数据 - 企业  平台主要表，企业管理员必须关联本表
	public Collection<TDataEnterpriseDtoModel> SelectT_DATA_ENTERPRISE(PagingOptions pagingOptions, TDataEnterpriseQueryModel queryModel , String sql) {
		if(Utility.isNullOrEmpty(sql)){
			sql=WeChatFields.Script_SelectT_DATA_ENTERPRISE;
		}
		ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
		if(null==scriptItemModel){
			logger.info(sql+" 查询脚本没有找到");
			return null;
		}
		DataParamsHandler dataParamsHandler=new DataParamsHandler(){
			@Override
			public void resolveParams(ScriptItemModel scriptItemModel) {
				ArrayList<Object> params=new ArrayList<>();
				if(null!=queryModel){
					String where="";
					if(null!=queryModel.getenterprise_id()){
						where+=" and ENTERPRISE_ID=?";
						params.add(queryModel.getenterprise_id());
					}
					if(null!=queryModel.getname()){
						where+=" and NAME LIKE ?";
						params.add("%"+queryModel.getname()+"%");
					}
					if(null!=queryModel.gettelephone_number()){
						where+=" and TELEPHONE_NUMBER=?";
						params.add(queryModel.gettelephone_number());
					}
					scriptItemModel.setSqlWhere(where);
                }
				scriptItemModel.setParams(params.toArray());
			}
		};
		queryModel.dataParamsHandler=dataParamsHandler;
		Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TDataEnterpriseModel.class,scriptItemModel,pagingOptions,queryModel);
		Collection<TDataEnterpriseDtoModel> models=new ArrayList<>();
		for (IBaseModel b : collection){
			TDataEnterpriseDtoModel dto=new TDataEnterpriseDtoModel();
			BeanUtils.copyProperties((TDataEnterpriseModel)b,dto);
			models.add(dto);
        }
        return models;
    }
	//t_data_enterprise_goods 主数据 - 企业 - 货品   每个企业自身的货品配件等
	public Collection<TDataEnterpriseGoodsDtoModel> SelectT_DATA_ENTERPRISE_GOODS(PagingOptions pagingOptions, TDataEnterpriseGoodsQueryModel queryModel , String sql) {
		if(Utility.isNullOrEmpty(sql)){
			sql=WeChatFields.Script_SelectT_DATA_ENTERPRISE_GOODS;
		}
		ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
		if(null==scriptItemModel){
			logger.info(sql+" 查询脚本没有找到");
			return null;
		}
		DataParamsHandler dataParamsHandler=new DataParamsHandler(){
			@Override
			public void resolveParams(ScriptItemModel scriptItemModel) {
				ArrayList<Object> params=new ArrayList<>();
				if(null!=queryModel){
					String where="";
					if(null!=queryModel.getgoods_id()){
						where+=" and GOODS_ID=?";
						params.add(queryModel.getgoods_id());
					}
					if(null!=queryModel.getname()){
						where+=" and NAME LIKE ?";
						params.add("%"+queryModel.getname()+"%");
					}
					if(null!=queryModel.getbatch_no()){
						where+=" and BATCH_NO LIKE ?";
						params.add("%"+queryModel.getbatch_no()+"%");
					}
					if(null!=queryModel.getenterprise_id()){
						where+=" and ENTERPRISE_ID=?";
						params.add(queryModel.getenterprise_id());
					}
					scriptItemModel.setSqlWhere(where);
                }
				scriptItemModel.setParams(params.toArray());
			}
		};
		queryModel.dataParamsHandler=dataParamsHandler;
		Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TDataEnterpriseGoodsModel.class,scriptItemModel,pagingOptions,queryModel);
		Collection<TDataEnterpriseGoodsDtoModel> models=new ArrayList<>();
		for (IBaseModel b : collection){
			TDataEnterpriseGoodsDtoModel dto=new TDataEnterpriseGoodsDtoModel();
			BeanUtils.copyProperties((TDataEnterpriseGoodsModel)b,dto);
			models.add(dto);
        }
        return models;
    }
	//t_data_enterprise_order 主数据 - 企业 - 工单 
	public Collection<TDataEnterpriseOrderDtoModel> SelectT_DATA_ENTERPRISE_ORDER(PagingOptions pagingOptions, TDataEnterpriseOrderQueryModel queryModel , String sql) {
		if(Utility.isNullOrEmpty(sql)){
			sql=WeChatFields.Script_SelectT_DATA_ENTERPRISE_ORDER;
		}
		ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
		if(null==scriptItemModel){
			logger.info(sql+" 查询脚本没有找到");
			return null;
		}
		DataParamsHandler dataParamsHandler=new DataParamsHandler(){
			@Override
			public void resolveParams(ScriptItemModel scriptItemModel) {
				ArrayList<Object> params=new ArrayList<>();
				if(null!=queryModel){
					String where="";
					if(null!=queryModel.getorder_id()){
						where+=" and ORDER_ID=?";
						params.add(queryModel.getorder_id());
					}
					if(null!=queryModel.getorder_no()){
						where+=" and ORDER_NO=?";
						params.add(queryModel.getorder_no());
					}
					if(null!=queryModel.getname()){
						where+=" and NAME LIKE ?";
						params.add("%"+queryModel.getname()+"%");
					}
					if(null!=queryModel.getcustomer_id()){
						where+=" and CUSTOMER_ID=?";
						params.add(queryModel.getcustomer_id());
					}
					if(null!=queryModel.getenterprise_id()){
						where+=" and ENTERPRISE_ID=?";
						params.add(queryModel.getenterprise_id());
					}
					scriptItemModel.setSqlWhere(where);
                }
				scriptItemModel.setParams(params.toArray());
			}
		};
		queryModel.dataParamsHandler=dataParamsHandler;
		Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TDataEnterpriseOrderModel.class,scriptItemModel,pagingOptions,queryModel);
		Collection<TDataEnterpriseOrderDtoModel> models=new ArrayList<>();
		for (IBaseModel b : collection){
			TDataEnterpriseOrderDtoModel dto=new TDataEnterpriseOrderDtoModel();
			BeanUtils.copyProperties((TDataEnterpriseOrderModel)b,dto);
			models.add(dto);
        }
        return models;
    }
	//t_data_enterprise_order_2_goods 主数据 - 企业 - 工单 - 2 - 配件  本次工单所有的配件清单
	public Collection<TDataEnterpriseOrder2GoodsDtoModel> SelectT_DATA_ENTERPRISE_ORDER_2_GOODS(PagingOptions pagingOptions, TDataEnterpriseOrder2GoodsQueryModel queryModel , String sql) {
		if(Utility.isNullOrEmpty(sql)){
			sql=WeChatFields.Script_SelectT_DATA_ENTERPRISE_ORDER_2_GOODS;
		}
		ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
		if(null==scriptItemModel){
			logger.info(sql+" 查询脚本没有找到");
			return null;
		}
		DataParamsHandler dataParamsHandler=new DataParamsHandler(){
			@Override
			public void resolveParams(ScriptItemModel scriptItemModel) {
				ArrayList<Object> params=new ArrayList<>();
				if(null!=queryModel){
					String where="";
					if(null!=queryModel.getorder_details_id()){
						where+=" and ORDER_DETAILS_ID=?";
						params.add(queryModel.getorder_details_id());
					}
					if(null!=queryModel.getorder_id()){
						where+=" and ORDER_ID=?";
						params.add(queryModel.getorder_id());
					}
					if(null!=queryModel.getgoods_id()){
						where+=" and GOODS_ID=?";
						params.add(queryModel.getgoods_id());
					}
					if(null!=queryModel.getwarehouse_id()){
						where+=" and WAREHOUSE_ID=?";
						params.add(queryModel.getwarehouse_id());
					}
					scriptItemModel.setSqlWhere(where);
                }
				scriptItemModel.setParams(params.toArray());
			}
		};
		queryModel.dataParamsHandler=dataParamsHandler;
		Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TDataEnterpriseOrder2GoodsModel.class,scriptItemModel,pagingOptions,queryModel);
		Collection<TDataEnterpriseOrder2GoodsDtoModel> models=new ArrayList<>();
		for (IBaseModel b : collection){
			TDataEnterpriseOrder2GoodsDtoModel dto=new TDataEnterpriseOrder2GoodsDtoModel();
			BeanUtils.copyProperties((TDataEnterpriseOrder2GoodsModel)b,dto);
			models.add(dto);
        }
        return models;
    }
	//t_data_enterprise_order_workflow 主数据 - 企业 - 工单 - 2 - 流程
	public Collection<TDataEnterpriseOrderWorkflowDtoModel> SelectT_DATA_ENTERPRISE_ORDER_WORKFLOW(PagingOptions pagingOptions, TDataEnterpriseOrderWorkflowQueryModel queryModel , String sql) {
		if(Utility.isNullOrEmpty(sql)){
			sql=WeChatFields.Script_SelectT_DATA_ENTERPRISE_ORDER_WORKFLOW;
		}
		ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
		if(null==scriptItemModel){
			logger.info(sql+" 查询脚本没有找到");
			return null;
		}
		DataParamsHandler dataParamsHandler=new DataParamsHandler(){
			@Override
			public void resolveParams(ScriptItemModel scriptItemModel) {
				ArrayList<Object> params=new ArrayList<>();
				if(null!=queryModel){
					String where="";
					if(null!=queryModel.getworkflow_id()){
						where+=" and WORKFLOW_ID=?";
						params.add(queryModel.getworkflow_id());
					}
					if(null!=queryModel.getdescription()){
						where+=" and DESCRIPTION LIKE ?";
						params.add("%"+queryModel.getdescription()+"%");
					}
					scriptItemModel.setSqlWhere(where);
                }
				scriptItemModel.setParams(params.toArray());
			}
		};
		queryModel.dataParamsHandler=dataParamsHandler;
		Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TDataEnterpriseOrderWorkflowModel.class,scriptItemModel,pagingOptions,queryModel);
		Collection<TDataEnterpriseOrderWorkflowDtoModel> models=new ArrayList<>();
		for (IBaseModel b : collection){
			TDataEnterpriseOrderWorkflowDtoModel dto=new TDataEnterpriseOrderWorkflowDtoModel();
			BeanUtils.copyProperties((TDataEnterpriseOrderWorkflowModel)b,dto);
			models.add(dto);
        }
        return models;
    }
	//t_data_enterprise_stock 主数据 - 企业 - 库存每个企业自身的货品配件库存数据
	public Collection<TDataEnterpriseStockDtoModel> SelectT_DATA_ENTERPRISE_STOCK(PagingOptions pagingOptions, TDataEnterpriseStockQueryModel queryModel , String sql) {
		if(Utility.isNullOrEmpty(sql)){
			sql=WeChatFields.Script_SelectT_DATA_ENTERPRISE_STOCK;
		}
		ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
		if(null==scriptItemModel){
			logger.info(sql+" 查询脚本没有找到");
			return null;
		}
		DataParamsHandler dataParamsHandler=new DataParamsHandler(){
			@Override
			public void resolveParams(ScriptItemModel scriptItemModel) {
				ArrayList<Object> params=new ArrayList<>();
				if(null!=queryModel){
					String where="";
					if(null!=queryModel.getstock_id()){
						where+=" and STOCK_ID=?";
						params.add(queryModel.getstock_id());
					}
					if(null!=queryModel.getname()){
						where+=" and NAME LIKE ?";
						params.add("%"+queryModel.getname()+"%");
					}
					if(null!=queryModel.getgoods_id()){
						where+=" and GOODS_ID=?";
						params.add(queryModel.getgoods_id());
					}
					if(null!=queryModel.getenterprise_id()){
						where+=" and ENTERPRISE_ID=?";
						params.add(queryModel.getenterprise_id());
					}
					if(null!=queryModel.getwarehouse_id()){
						where+=" and WAREHOUSE_ID=?";
						params.add(queryModel.getwarehouse_id());
					}
					scriptItemModel.setSqlWhere(where);
                }
				scriptItemModel.setParams(params.toArray());
			}
		};
		queryModel.dataParamsHandler=dataParamsHandler;
		Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TDataEnterpriseStockModel.class,scriptItemModel,pagingOptions,queryModel);
		Collection<TDataEnterpriseStockDtoModel> models=new ArrayList<>();
		for (IBaseModel b : collection){
			TDataEnterpriseStockDtoModel dto=new TDataEnterpriseStockDtoModel();
			BeanUtils.copyProperties((TDataEnterpriseStockModel)b,dto);
			models.add(dto);
        }
        return models;
    }
	//t_data_enterprise_warehouse 主数据 - 企业 - 仓库  主要有企业管理员维护 
	public Collection<TDataEnterpriseWarehouseDtoModel> SelectT_DATA_ENTERPRISE_WAREHOUSE(PagingOptions pagingOptions, TDataEnterpriseWarehouseQueryModel queryModel , String sql) {
		if(Utility.isNullOrEmpty(sql)){
			sql=WeChatFields.Script_SelectT_DATA_ENTERPRISE_WAREHOUSE;
		}
		ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
		if(null==scriptItemModel){
			logger.info(sql+" 查询脚本没有找到");
			return null;
		}
		DataParamsHandler dataParamsHandler=new DataParamsHandler(){
			@Override
			public void resolveParams(ScriptItemModel scriptItemModel) {
				ArrayList<Object> params=new ArrayList<>();
				if(null!=queryModel){
					String where="";
					if(null!=queryModel.getwarehouse_id()){
						where+=" and WAREHOUSE_ID=?";
						params.add(queryModel.getwarehouse_id());
					}
					if(null!=queryModel.getname()){
						where+=" and NAME LIKE ?";
						params.add("%"+queryModel.getname()+"%");
					}
					if(null!=queryModel.gettelephone_number()){
						where+=" and TELEPHONE_NUMBER=?";
						params.add(queryModel.gettelephone_number());
					}
					if(null!=queryModel.getenterprise_id()){
						where+=" and ENTERPRISE_ID=?";
						params.add(queryModel.getenterprise_id());
					}
					scriptItemModel.setSqlWhere(where);
                }
				scriptItemModel.setParams(params.toArray());
			}
		};
		queryModel.dataParamsHandler=dataParamsHandler;
		Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TDataEnterpriseWarehouseModel.class,scriptItemModel,pagingOptions,queryModel);
		Collection<TDataEnterpriseWarehouseDtoModel> models=new ArrayList<>();
		for (IBaseModel b : collection){
			TDataEnterpriseWarehouseDtoModel dto=new TDataEnterpriseWarehouseDtoModel();
			BeanUtils.copyProperties((TDataEnterpriseWarehouseModel)b,dto);
			models.add(dto);
        }
        return models;
    }
	//t_data_supplier 主数据 - 供应商 可以是个人也可以是企业
	public Collection<TDataSupplierDtoModel> SelectT_DATA_SUPPLIER(PagingOptions pagingOptions, TDataSupplierQueryModel queryModel , String sql) {
		if(Utility.isNullOrEmpty(sql)){
			sql=WeChatFields.Script_SelectT_DATA_SUPPLIER;
		}
		ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
		if(null==scriptItemModel){
			logger.info(sql+" 查询脚本没有找到");
			return null;
		}
		DataParamsHandler dataParamsHandler=new DataParamsHandler(){
			@Override
			public void resolveParams(ScriptItemModel scriptItemModel) {
				ArrayList<Object> params=new ArrayList<>();
				if(null!=queryModel){
					String where="";
					if(null!=queryModel.getsupplier_id()){
						where+=" and SUPPLIER_ID=?";
						params.add(queryModel.getsupplier_id());
					}
					if(null!=queryModel.getname()){
						where+=" and NAME LIKE ?";
						params.add("%"+queryModel.getname()+"%");
					}
					if(null!=queryModel.gettelephone_number()){
						where+=" and TELEPHONE_NUMBER LIKE ?";
						params.add("%"+queryModel.gettelephone_number()+"%");
					}
					if(null!=queryModel.getenterprise_id()){
						where+=" and ENTERPRISE_ID=?";
						params.add(queryModel.getenterprise_id());
					}
					scriptItemModel.setSqlWhere(where);
                }
				scriptItemModel.setParams(params.toArray());
			}
		};
		queryModel.dataParamsHandler=dataParamsHandler;
		Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TDataSupplierModel.class,scriptItemModel,pagingOptions,queryModel);
		Collection<TDataSupplierDtoModel> models=new ArrayList<>();
		for (IBaseModel b : collection){
			TDataSupplierDtoModel dto=new TDataSupplierDtoModel();
			BeanUtils.copyProperties((TDataSupplierModel)b,dto);
			models.add(dto);
        }
        return models;
    }
	//t_xt_qx_bm 系统 - 权限 - 部门
	public Collection<TXtQxBmDtoModel> SelectT_XT_QX_BM(PagingOptions pagingOptions, TXtQxBmQueryModel queryModel , String sql) {
		if(Utility.isNullOrEmpty(sql)){
			sql=WeChatFields.Script_SelectT_XT_QX_BM;
		}
		ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
		if(null==scriptItemModel){
			logger.info(sql+" 查询脚本没有找到");
			return null;
		}
		DataParamsHandler dataParamsHandler=new DataParamsHandler(){
			@Override
			public void resolveParams(ScriptItemModel scriptItemModel) {
				ArrayList<Object> params=new ArrayList<>();
				if(null!=queryModel){
					String where="";
					if(null!=queryModel.getbm_dm()){
						where+=" and BM_DM=?";
						params.add(queryModel.getbm_dm());
					}
					if(null!=queryModel.getmc()){
						where+=" and MC LIKE ?";
						params.add("%"+queryModel.getmc()+"%");
					}
					if(null!=queryModel.getyyz_xh()){
						where+=" and YYZ_XH=?";
						params.add(queryModel.getyyz_xh());
					}
					scriptItemModel.setSqlWhere(where);
                }
				scriptItemModel.setParams(params.toArray());
			}
		};
		queryModel.dataParamsHandler=dataParamsHandler;
		Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TXtQxBmModel.class,scriptItemModel,pagingOptions,queryModel);
		Collection<TXtQxBmDtoModel> models=new ArrayList<>();
		for (IBaseModel b : collection){
			TXtQxBmDtoModel dto=new TXtQxBmDtoModel();
			BeanUtils.copyProperties((TXtQxBmModel)b,dto);
			models.add(dto);
        }
        return models;
    }
	//t_xt_qx_js 系统 - 权限 - 系统角色
	public Collection<TXtQxJsDtoModel> SelectT_XT_QX_JS(PagingOptions pagingOptions, TXtQxJsQueryModel queryModel , String sql) {
		if(Utility.isNullOrEmpty(sql)){
			sql=WeChatFields.Script_SelectT_XT_QX_JS;
		}
		ScriptItemModel scriptItemModel= SystemSetting.JD_ServerCache.getSciptItemModel(sql);
		if(null==scriptItemModel){
			logger.info(sql+" 查询脚本没有找到");
			return null;
		}
		DataParamsHandler dataParamsHandler=new DataParamsHandler(){
			@Override
			public void resolveParams(ScriptItemModel scriptItemModel) {
				ArrayList<Object> params=new ArrayList<>();
				if(null!=queryModel){
					String where="";
					if(null!=queryModel.getjs_dm()){
						where+=" and JS_DM=?";
						params.add(queryModel.getjs_dm());
					}
					if(null!=queryModel.getmc()){
						where+=" and MC LIKE ?";
						params.add("%"+queryModel.getmc()+"%");
					}
					if(null!=queryModel.getyyz_xh()){
						where+=" and YYZ_XH=?";
						params.add(queryModel.getyyz_xh());
					}
					scriptItemModel.setSqlWhere(where);
                }
				scriptItemModel.setParams(params.toArray());
			}
		};
		queryModel.dataParamsHandler=dataParamsHandler;
		Collection<IBaseModel> collection= SelectBaseData(DataContext.getCurrentConnection(),TXtQxJsModel.class,scriptItemModel,pagingOptions,queryModel);
		Collection<TXtQxJsDtoModel> models=new ArrayList<>();
		for (IBaseModel b : collection){
			TXtQxJsDtoModel dto=new TXtQxJsDtoModel();
			BeanUtils.copyProperties((TXtQxJsModel)b,dto);
			models.add(dto);
        }
        return models;
    }
}
