var vm = new Vue({
    el: "#stock",
    data: function () {
        return {
            dataList: [],   // 列表数据
            total: 0,       // 总数
            limit: 10,      // 每页显示个数
            page: 1,        // 当前页数

			stock_id: '',
			name: '',


            title: '添加',  // 添加编辑企业信息
            isAdd: false,
            params: {
				stock_id: '',
				goods_id: '',
				enterprise_id: '',
				warehouse_id: '',
				name: '',
				unit_id: '',
				quantity: '',
				unit_id2: '',
				quantity2: '',
				address: '',
				description: '',
				is_valid: '',
				create_time: '',
				last_edit_time: '',
				creation_user_id: '',
				last_edit_user_id: '',
				price: '',
				amount: '',
				unit_name: '',
				unit2_name: '',
   
            }
        };
    },
    components: {
        "nav-top": $component.navTop
    },
    mounted: function () {
        this.getDataList(true);
    },
    methods: {
        // 获取列表
        getDataList: function (judge) {
            var _vue = this;
            var url = js.Web.GenerateUrl("TDataEnterpriseStock", "search");
            if(!judge) _vue.page = 1;
            var params = {
                start: (_vue.page-1)*10,
                pageSize: _vue.limit
            };
			if(this.name.replace(/(^\s*)|(\s*$)/g, "")) params.name = this.name;
            
            var loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.dataList = data.rows;
                        if(_vue.page === 1) _vue.total = data.total;
                    }
                }, function (success, data) {
                    loading.close();
                    if(data.loginStatus === 401) {
                        _vue.$message.error('请重新登录！');
                        setTimeout(function () {
                            control.signOut(_vue);
                        },1500)
                    }else {
                        _vue.$message.error(data.msg);
                    }
                }, true, false, true)
        },
        // 列表分页
        changePages: function (val) {
            this.page = val;
            this.getDataList(true);
        },
        // 释放添加弹框
        addStockBtn: function (judge, row) {
            this.title = judge ? '编辑' : '添加';
            if (!judge) {
				this.stock_id = '';

                this.params = {
					goods_id: '',
					enterprise_id: '',
					warehouse_id: '',
					name: '',
					unit_id: '',
					quantity: '',
					unit_id2: '',
					quantity2: '',
					address: '',
					description: '',
					is_valid: '',
					create_time: '',
					last_edit_time: '',
					creation_user_id: '',
					last_edit_user_id: '',
					price: '',
					amount: '',
					unit_name: '',
					unit2_name: '',

                };
            } else {
				this.stock_id=row.stock_id;

                this.params = {
					goods_id: row.goods_id,
					enterprise_id: row.enterprise_id,
					warehouse_id: row.warehouse_id,
					name: row.name,
					unit_id: row.unit_id,
					quantity: row.quantity,
					unit_id2: row.unit_id2,
					quantity2: row.quantity2,
					address: row.address,
					description: row.description,
					is_valid: row.is_valid,
					create_time: row.create_time,
					last_edit_time: row.last_edit_time,
					creation_user_id: row.creation_user_id,
					last_edit_user_id: row.last_edit_user_id,
					price: row.price,
					amount: row.amount,
					unit_name: row.unit_name,
					unit2_name: row.unit2_name,

                };
            }
            this.isAdd = true;
        },
        // 添加 编辑 企业信息
        addStock: function () {
            var _vue = this;
            var url = js.Web.GenerateUrl("TDataEnterpriseStock", "saveStock");
            var params = this.params;
            // 编辑
			if (this.stock_id) {
				params.stock_id = this.stock_id;
			}
            
$$edit_common_fields_check$$            
            var loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.$message.success(_vue.title + "成功！");
                        _vue.getDataList(false);
                        _vue.isAdd = false;
                    }
                }, function (success, data) {
                    loading.close();
                    if(data.loginStatus === 401) {
                        _vue.$message.error('请重新登录！');
                        setTimeout(function () {
                            control.signOut(_vue);
                        },1500)
                    }else {
                        _vue.$message.error(data.msg);
                    }
                }, true, false, true)
        },

        //删除账号
        deleteStock: function (row) {
            var _vue = this;
            let url = js.Web.GenerateUrl("TDataEnterpriseStock", "remove");
			let params = {stock_id: row.stock_id};

            let loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.$message.success("删除成功！");
                        _vue.getDataList(false);
                    }
                }, function (success, data) {
                    loading.close();
                    if(data.loginStatus === 401) {
                        _vue.$message.error('请重新登录！');
                        setTimeout(function () {
                            control.signOut(_vue);
                        },1500)
                    }else {
                        _vue.$message.error(data.msg);
                    }
                }, true, false, true)
        },
    }
});