var vm = new Vue({
    el: "#goods",
    data: function () {
        return {
            dataList: [],   // 列表数据
            total: 0,       // 总数
            limit: 10,      // 每页显示个数
            page: 1,        // 当前页数

			goods_id: '',
			name: '',
			description: '',


            title: '添加',  // 添加编辑企业信息
            isAdd: false,
            params: {
				goods_id: '',
				name: '',
				unit_id: '',
				unit_id2: '',
				address: '',
				description: '',
				sale_price: '',
				batch_no: '',
				brand: '',
   
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
            var url = js.Web.GenerateUrl("TDataEnterpriseGoods", "search");
            if(!judge) _vue.page = 1;
            var params = {
                start: (_vue.page-1)*10,
                pageSize: _vue.limit
            };
			if(this.name.replace(/(^\s*)|(\s*$)/g, "")) params.name = this.name;
			if(this.description.replace(/(^\s*)|(\s*$)/g, "")) params.description = this.description;
            
            if(this.name.replace(/(^\s*)|(\s*$)/g, "")) params.name = this.name;
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
        addGoodsBtn: function (judge, row) {
            this.title = judge ? '编辑' : '添加';
            if (!judge) {
				this.goods_id = '';

                this.params = {
					name: '',
					unit_id: '',
					unit_id2: '',
					address: '',
					description: '',
					sale_price: '',
					batch_no: '',
					brand: '',

                };
            } else {
				this.goods_id=row.goods_id;

                this.params = {
					name: row.name,
					unit_id: row.unit_id,
					unit_id2: row.unit_id2,
					address: row.address,
					description: row.description,
					sale_price: row.sale_price,
					batch_no: row.batch_no,
					brand: row.brand,

                };
            }
            this.isAdd = true;
        },
        // 添加 编辑 企业信息
        addGoods: function () {
            var _vue = this;
            var url = js.Web.GenerateUrl("TDataEnterpriseGoods", "saveGoods");
            var params = this.params;
            // 编辑
			if (this.goods_id) {
				params.goods_id = this.goods_id;
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
        deleteGoods: function (row) {
            var _vue = this;
            let url = js.Web.GenerateUrl("TDataEnterpriseGoods", "remove");
			let params = {goods_id: row.goods_id};

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