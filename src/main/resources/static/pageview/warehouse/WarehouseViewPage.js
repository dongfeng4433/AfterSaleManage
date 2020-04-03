var vm = new Vue({
    el: "#warehouse",
    data: function () {
        return {
            dataList: [],   // 列表数据
            total: 0,       // 总数
            limit: 10,      // 每页显示个数
            page: 1,        // 当前页数

			warehouse_id: '',
			name: '',
			short_name: '',
			telephone_number: '',


            title: '添加',  // 添加编辑企业信息
            isAdd: false,
            params: {
				warehouse_id: '',
				name: '',
				short_name: '',
				address: '',
				description: '',
				telephone_number: '',
   
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
            var url = js.Web.GenerateUrl("TDataEnterpriseWarehouse", "search");
            if(!judge) _vue.page = 1;
            var params = {
                start: (_vue.page-1)*10,
                pageSize: _vue.limit
            };
			if(this.name.replace(/(^\s*)|(\s*$)/g, "")) params.name = this.name;
			if(this.short_name.replace(/(^\s*)|(\s*$)/g, "")) params.short_name = this.short_name;
			if(this.telephone_number.replace(/(^\s*)|(\s*$)/g, "")) params.telephone_number = this.telephone_number;
            
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
        addWarehouseBtn: function (judge, row) {
            this.title = judge ? '编辑' : '添加';
            if (!judge) {
				this.warehouse_id = '';

                this.params = {
					name: '',
					short_name: '',
					address: '',
					description: '',
					telephone_number: '',

                };
            } else {
				this.warehouse_id=row.warehouse_id;

                this.params = {
					name: row.name,
					short_name: row.short_name,
					address: row.address,
					description: row.description,
					telephone_number: row.telephone_number,

                };
            }
            this.isAdd = true;
        },
        // 添加 编辑 企业信息
        addWarehouse: function () {
            var _vue = this;
            var url = js.Web.GenerateUrl("TDataEnterpriseWarehouse", "saveWarehouse");
            var params = this.params;
            // 编辑
			if (this.warehouse_id) {
				params.warehouse_id = this.warehouse_id;
			}
            
			if(!params.name.replace(/(^\s*)|(\s*$)/g, "")) {
				this.$message.warning('请填写联系电话');
				return;
			}
            
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
        deleteWarehouse: function (row) {
            var _vue = this;
            let url = js.Web.GenerateUrl("TDataEnterpriseWarehouse", "remove");
			let params = {warehouse_id: row.warehouse_id};

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