var vm = new Vue({
    el: "#order",
    data: function () {
        return {
            dataList: [],   // 列表数据
            total: 0,       // 总数
            limit: 10,      // 每页显示个数
            page: 1,        // 当前页数

			order_id: '',
			order_no: '',
			name: '',
			customer_id: '',


            title: '添加',  // 添加编辑订单信息
            title2: '编辑',  // 添加编辑工单明细
            isAdd: false,
            isAddDetails :false,
            params: {
				order_id: '',
				order_no: '',
				name: '',
				customer_id: '',
				address: '',
				description: '',
				start_time: '',
				complete_time: '',
				plan_complete_time: '',
				work_user_id: '',
				order_status: '',
   
            },

            pickerOptions: {
                shortcuts: [{
                    text: '今天',
                    onClick(picker) {
                        picker.$emit('pick', new Date());
                    }
                }, {
                    text: '昨天',
                    onClick(picker) {
                        const date = new Date();
                        date.setTime(date.getTime() - 3600 * 1000 * 24);
                        picker.$emit('pick', date);
                    }
                }, {
                    text: '一周前',
                    onClick(picker) {
                        const date = new Date();
                        date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                        picker.$emit('pick', date);
                    }
                }]
            },

            tableData: [{
                date: '2016-05-03',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            }, {
                date: '2016-05-02',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            }, {
                date: '2016-05-04',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            }, {
                date: '2016-05-01',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            }, {
                date: '2016-05-08',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            }, {
                date: '2016-05-06',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            }, {
                date: '2016-05-07',
                name: '王小虎',
                address: '上海市普陀区金沙江路 1518 弄'
            }],
            multipleSelection: [],
            showEdit: [], //显示编辑框
            showBtn: [],
            showBtnOrdinary: true
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
            var url = js.Web.GenerateUrl("TDataEnterpriseOrder", "search");
            if(!judge) _vue.page = 1;
            var params = {
                start: (_vue.page-1)*10,
                pageSize: _vue.limit
            };
			if(this.order_no.replace(/(^\s*)|(\s*$)/g, "")) params.order_no = this.order_no;
			if(this.name.replace(/(^\s*)|(\s*$)/g, "")) params.name = this.name;
			if(this.customer_id.replace(/(^\s*)|(\s*$)/g, "")) params.customer_id = this.customer_id;
            
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
        addOrderBtn: function (judge, row) {
            this.title = judge ? '编辑' : '添加';
            if (!judge) {
				this.order_id = '';

                this.params = {
					order_no: '',
					name: '',
					customer_id: '',
					address: '',
					description: '',
					start_time: '',
					complete_time: '',
					plan_complete_time: '',
					work_user_id: '',
					order_status: '',

                };
            } else {
				this.order_id=row.order_id;

                this.params = {
					order_no: row.order_no,
					name: row.name,
					customer_id: row.customer_id,
					address: row.address,
					description: row.description,
					start_time: row.start_time,
					complete_time: row.complete_time,
					plan_complete_time: row.plan_complete_time,
					work_user_id: row.work_user_id,
					order_status: row.order_status,

                };
            }
            this.isAdd = true;
        },
        // 添加 编辑 企业信息
        addOrder: function () {
            var _vue = this;
            var url = js.Web.GenerateUrl("TDataEnterpriseOrder", "saveOrder");
            var params = this.params;
            // 编辑
			if (this.order_id) {
				params.order_id = this.order_id;
			}
            
			if(!params.name.replace(/(^\s*)|(\s*$)/g, "")) {
				this.$message.warning('请填写联系电话');
				return;
			}
			if(!params.customer_id.replace(/(^\s*)|(\s*$)/g, "")) {
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
        deleteOrder: function (row) {
            var _vue = this;
            let url = js.Web.GenerateUrl("TDataEnterpriseOrder", "remove");
			let params = {order_id: row.order_id};

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
        addOrderDetailsBtn: function (judge, row) {
            this.title2 = judge ? '编辑' : '添加';
            if (!judge) {
                this.order_id = '';

                this.params = {
                    order_no: '',
                    name: '',
                    customer_id: '',
                    address: '',
                    description: '',
                    start_time: '',
                    complete_time: '',
                    plan_complete_time: '',
                    work_user_id: '',
                    order_status: '',

                };
            } else {
                this.order_id=row.order_id;

                this.params = {
                    order_no: row.order_no,
                    name: row.name,
                    customer_id: row.customer_id,
                    address: row.address,
                    description: row.description,
                    start_time: row.start_time,
                    complete_time: row.complete_time,
                    plan_complete_time: row.plan_complete_time,
                    work_user_id: row.work_user_id,
                    order_status: row.order_status,

                };
            }
            this.isAddDetails = true;
        },
        addOrderDetails: function () {
            var _vue = this;
            var url = js.Web.GenerateUrl("TDataEnterpriseOrder", "saveOrderDetails");
            var params = this.params;
            // 编辑
            if (this.order_id) {
                params.order_id = this.order_id;
            }

            if(!params.name.replace(/(^\s*)|(\s*$)/g, "")) {
                this.$message.warning('请填写联系电话');
                return;
            }
            if(!params.customer_id.replace(/(^\s*)|(\s*$)/g, "")) {
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
        toggleSelection(rows) {
            if (rows) {
                rows.forEach(row => {
                    this.$refs.multipleTable.toggleRowSelection(row);
                });
            } else {
                this.$refs.multipleTable.clearSelection();
            }
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        //点击编辑
        handleEdit(index, row) {
            this.showEdit[index] = true;
            this.showBtn[index] = true;
            this.$set(this.showEdit,index,true)
            this.$set(this.showBtn,index,true)
        },
        //取消编辑
        handleCancel(index, row) {
            // this.getContentList();
            this.$set(this.showEdit,index,false)
            this.$set(this.showBtn,index,false)
        },
        //点击更新
        handleUpdate(formName) {

        },
        //点击删除
        handleDelete(index, row) {
            this.tableData.splice(index, 1);
        },
    }
});