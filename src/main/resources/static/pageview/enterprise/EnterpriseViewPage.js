var vm = new Vue({
    el: "#enterprise",
    data: function () {
        return {
            dataList: [],   // 列表数据
            total: 0,       // 总数
            limit: 10,      // 每页显示个数
            page: 1,        // 当前页数

			enterprise_id: '',
			name: '',
			telephone_number: '',


            title: '添加',  // 添加编辑企业信息
            title_admin:'设置管理员',//设置管理员
            isAdd: false,
            params: {
				enterprise_id: '',
				name: '',
				short_name: '',
				address: '',
				description: '',
				telephone_number: '',
				dd_corp_id: '',
   
            },

            isAdmainAdd :false,
            loading:false,
            adminParams:{
                enterprise_id:'',
                ry_xhs:[],
                options: [],
                value: [],
                list: [],
                states: []
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
            var url = js.Web.GenerateUrl("TDataEnterprise", "search");
            if (!judge) _vue.page = 1;
            var params = {
                start: (_vue.page - 1) * 10,
                pageSize: _vue.limit
            };
            if (this.name.replace(/(^\s*)|(\s*$)/g, "")) params.name = this.name;
            if (this.telephone_number.replace(/(^\s*)|(\s*$)/g, "")) params.telephone_number = this.telephone_number;

            var loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.dataList = data.rows;
                        if (_vue.page === 1) _vue.total = data.total;
                    }
                }, function (success, data) {
                    loading.close();
                    if (data.loginStatus === 401) {
                        _vue.$message.error('请重新登录！');
                        setTimeout(function () {
                            control.signOut(_vue);
                        }, 1500)
                    } else {
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
        addEnterpriseBtn: function (judge, row) {
            this.title = judge ? '编辑' : '添加';
            if (!judge) {
                this.enterprise_id = '';

                this.params = {
                    name: '',
                    short_name: '',
                    address: '',
                    description: '',
                    telephone_number: '',
                    dd_corp_id: '',

                };
            } else {
                this.enterprise_id = row.enterprise_id;

                this.params = {
                    name: row.name,
                    short_name: row.short_name,
                    address: row.address,
                    description: row.description,
                    telephone_number: row.telephone_number,
                    dd_corp_id: row.dd_corp_id,

                };
            }
            this.isAdd = true;
        },
        // 添加 编辑 企业信息
        addEnterprise: function () {
            var _vue = this;
            var url = js.Web.GenerateUrl("TDataEnterprise", "saveEnterprise");
            var params = this.params;
            // 编辑
            if (this.enterprise_id) {
                params.enterprise_id = this.enterprise_id;
            }

            if (!params.name.replace(/(^\s*)|(\s*$)/g, "")) {
                this.$message.warning('请填写联系电话');
                return;
            }
            if (!params.telephone_number.replace(/(^\s*)|(\s*$)/g, "")) {
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
                    if (data.loginStatus === 401) {
                        _vue.$message.error('请重新登录！');
                        setTimeout(function () {
                            control.signOut(_vue);
                        }, 1500)
                    } else {
                        _vue.$message.error(data.msg);
                    }
                }, true, false, true)
        },

        //删除账号
        deleteEnterprise: function (row) {
            var _vue = this;
            let url = js.Web.GenerateUrl("TDataEnterprise", "remove");
            let params = {enterprise_id: row.enterprise_id};

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
                    if (data.loginStatus === 401) {
                        _vue.$message.error('请重新登录！');
                        setTimeout(function () {
                            control.signOut(_vue);
                        }, 1500)
                    } else {
                        _vue.$message.error(data.msg);
                    }
                }, true, false, true)
        },
        remoteMethod:function(query) {
            let _vue=this;
            if (query !== '') {
                _vue.adminParams.loading = true;
                let url = js.Web.GenerateUrl("Zhxx", "searchZhxx");
                let params={mc:query,sj_hm:query,is_search_4_select:true};
                js.Web.AjaxRequest(this, url, params, null,
                    function (success, data) {
                        // loading.close();
                        if (data.success === true) {
                            _vue.adminParams.loading = false;
                            _vue.adminParams.options = data.rows.map(item => {
                                return { value: `${item.ry_xh}`, label: `用户:${item.mc}(${item.sj_hm})` };
                            });
                        }
                    }, function (success, data) {
                        loading.close();
                        if (data.loginStatus === 401) {
                            _vue.$message.error('请重新登录！');
                            setTimeout(function () {
                                control.signOut(_vue);
                            }, 1500)
                        } else {
                            _vue.$message.error(data.msg);
                        }
                    }, true, false, true)

            } else {
                this.adminParams.options = [];
            }
        },
        setAdminBtn: function (row) {
            let _vue = this;
            this.adminParams.enterprise_id = row.enterprise_id;
            let url = js.Web.GenerateUrl("TDataEnterprise", "getAdmin4Crop");
            let params = {enterprise_id: row.enterprise_id};
            let loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.isAdmainAdd = true;
                        //处理默认值
                        _vue.adminParams.options = data.rows.map(item => {
                            return { value: `${item.ry_xh}`, label: `用户:${item.mc}(${item.sj_hm})` };
                        });
                        _vue.adminParams.value =  data.rows.map(item => {
                            return `${item.ry_xh}`;
                        });
                    }
                }, function (success, data) {
                    loading.close();
                    if (data.loginStatus === 401) {
                        _vue.$message.error('请重新登录！');
                        setTimeout(function () {
                            control.signOut(_vue);
                        }, 1500)
                    } else {
                        _vue.$message.error(data.msg);
                    }
                }, true, false, true)
        },
        addAdmins: function () {
            let _vue = this;
            if (this.adminParams.value.length <= 0) {
                this.$message.warning('请选择用户');
                return;
            }

            let url = js.Web.GenerateUrl("TDataEnterprise", "setCropAdmins");
            let params = {enterprise_id: this.adminParams.enterprise_id};
            let temp = JSON.parse(JSON.stringify(this.adminParams.value));
            params.user_ids = temp.join(',');

            let loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.$message.success("设置成功！");
                        _vue.isAdmainAdd = false;
                    }
                }, function (success, data) {
                    loading.close();
                    if (data.loginStatus === 401) {
                        _vue.$message.error('请重新登录！');
                        setTimeout(function () {
                            control.signOut(_vue);
                        }, 1500)
                    } else {
                        _vue.$message.error(data.msg);
                    }
                }, true, false, true)
        }
    }
});