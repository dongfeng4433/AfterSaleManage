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

            tableData: [],
            multipleSelection: [],
            showEdit: [], //显示编辑框
            showBtn: [],
            showBtnOrdinary: true,
            goods:[],
            warehouses:[]
        };
    },
    components: {
        "nav-top": $component.navTop
    },
    mounted: function () {
        this.getDataList(true);
        this.loadSelectData();
        this.loadSelectData4Warehouse();
    },
    methods: {
        loadSelectData:function(){
            let _vue = this;
            let url = js.Web.GenerateUrl("TDataEnterpriseGoods", "search");
            let params = {};

            let loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        if(data.rows){
                            _vue.goods=data.rows;
                        }
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
        loadSelectData4Warehouse:function(){
            let _vue = this;
            let url = js.Web.GenerateUrl("TDataEnterpriseWarehouse", "search");
            let params = {};

            let loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        if(data.rows){
                            _vue.warehouses=data.rows;
                        }
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
            let _vue=this;
            this.title2 = judge ? '编辑' : '添加';
            _vue.tableData=[];
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

                let url= js.Web.GenerateUrl("TDataEnterpriseOrder", "searchOrderDetails");
                let loading = control.loading(this);
                js.Web.AjaxRequest(this, url, {order_id:row.order_id}, null,
                    function (success, data) {
                        loading.close();
                        if (data.success === true) {
                            _vue.tableData = data.rows;
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
            }
            this.isAddDetails = true;
        },
        addOrderDetails: function () {
            let _vue = this;
            let url = js.Web.GenerateUrl("TDataEnterpriseOrder", "saveOrderDetails");
            let params = {};
            // 编辑
            if (this.order_id) {
                params.order_id = this.order_id;
            }
            params.details=this.tableData;
            if(this.tableData.length===0){
                this.$message.warning('请录入明细数据');
                return;
            }
            let loading = control.loading(this);
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
        handleAddRow(){
            this.tableData.push({order_details_id:this.uuid()});
        },
        handleGoodsChange(row,goods_id){
            // console.log(row);
            // console.log(goods_id);
            for(let i=0;i<this.goods.length;i++){
                let o=this.goods[i];
                if(o.goods_id===goods_id){
                    row.name=o.name;
                    row.unit_name=o.unit_id;
                    row.quantity=1;
                    break;
                }
            }
            // console.log(row);
        },
        handleWarehouseChange(row,warehouse_id){
            // console.log(row);
            console.log(warehouse_id);
            for(let i=0;i<this.warehouses.length;i++){
                let o=this.warehouses[i];
                if(o.warehouse_id===warehouse_id){
                    row.warehouse_id=o.warehouse_id;
                    break;
                }
            }
            console.log(row);
        },
        uuid() {
            var s = [];
            var hexDigits = "0123456789abcdef";
            for (var i = 0; i < 36; i++) {
                s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
            }
            s[14] = "4";
            s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);
            s[8] = s[13] = s[18] = s[23] = "";//"-";

            this.uuidA = s.join("");
            console.log(s.join(""), 's.join("")');
            return this.uuidA;
        },
    }
});