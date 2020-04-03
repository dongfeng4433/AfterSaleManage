var vm = new Vue({
    el: "#users",
    data: function () {
        return {
            dataList: [],   // 列表数据
            total: 0,       // 总数
            limit: 10,      // 每页显示个数
            page: 1,        // 当前页数

            mc: '',
            sj_hm: '',

            title: '添加',  // 添加编辑用户信息
            isAdd: false,
            params: {
                yhm: '',
                mm: '',
                mc: '',
                sj_hm: '',
                bm_dm:'',
                js_dm:'',
            },
            ry_xh: '',

            isBmAdd:false,//部门
            bmmc:'',
            bmdms: [], // 部门列表



            isJsAdd:false,//角色
            jsmc:'',
            jsdms:[],


            isMenu: false,
            menuParams: {
                ry_xh: '',
                xtmk_dms: [],
                menuTreeData:[],
            },
            defaultProps: {
                children: 'children',
                label: 'label'
            },
        };
    },
    components: {
        "nav-top": $component.navTop
    },
    mounted: function () {
        this.getDataList(true);
        this.loadMenuTree();
    },
    methods: {
        getTreeData(rows){
            function getChild(arr,xtmk_dm){
                let temp=[];
                for(let j=0;j<arr.length;j++){
                    let oo=arr[j];
                    if(oo.sj_xtmk_dm==xtmk_dm)
                        temp.push({id:oo.xtmk_dm,label:oo.mc})
                }
                return temp;
            }
            let data=[];
            for(let i =0;i<rows.length;i++){
                let o=rows[i];
                if(typeof o.sj_xtmk_dm=='undefined'){
                    let temp=getChild(rows,o.xtmk_dm);
                    if(temp.length>0)
                        data.push({id:o.xtmk_dm,label:o.mc,children:temp})
                    else
                        data.push({id:o.xtmk_dm,label:o.mc})
                }

            }
            return data;
        },
        loadMenuTree() {
            let _vue=this;
            let url=js.Web.GenerateUrl("Common","queryMenuTreeData");
            let loading = control.loading(this);
            js.Web.AjaxRequest(this, url, {}, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true&&data.rows) {
                        _vue.menuParams.menuTreeData=_vue.getTreeData(data.rows);
                    }
                }, function (success, data) {
                    loading.close();
                }, false, false, true)
        },
        handleNodeClick(data) {
            console.log(data);
        },
        // 获取列表
        getDataList: function (judge) {
            var _vue = this;
            var url = js.Web.GenerateUrl("Zhxx", "searchZhxxByQx");
            if(!judge) _vue.page = 1;
            var params = {
                start: (_vue.page-1)*10,
                pageSize: _vue.limit
            };
            if(this.mc.replace(/(^\s*)|(\s*$)/g, "")) params.mc = this.mc;
            if(this.sj_hm.replace(/(^\s*)|(\s*$)/g, "")) params.sj_hm = this.sj_hm;
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
        addUsersBtn: function (judge, row) {
            this.getBmdms();
            this.getJsdms();
            this.title = judge ? '编辑' : '添加';
            if (!judge) {
                this.ry_xh = '';
                this.params = {
                    yhm: '',
                    mm: '',
                    mc: '',
                    sj_hm: '',
                    bm_dm:'',
                    js_dm:''
                };
            } else {
                this.ry_xh = row.ry_xh;
                this.params = {
                    mc: row.mc,
                    sj_hm: row.sj_hm || ''
                };
            }
            this.isAdd = true;
        },
        // 添加 编辑 用户信息
        addUsers: function () {
            var _vue = this;
            var url = js.Web.GenerateUrl("Zhxx", "saveZhxx");
            var params = this.params;

            // 编辑
            if (this.ry_xh) {
                params.ry_xh = this.ry_xh;
            }
            // 添加
            else {
                if(!params.yhm.replace(/(^\s*)|(\s*$)/g, "")) {
                    this.$message.warning('请填写登录账号');
                    return;
                }
                if(!params.mm.replace(/(^\s*)|(\s*$)/g, "")) {
                    this.$message.warning('请填写密码');
                    return;
                }
            }

            if(!params.mc.replace(/(^\s*)|(\s*$)/g, "")) {
                this.$message.warning('请填写名称');
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

        // 释放菜单弹框
        setMenuBtn: function (row) {
            let _vue = this;
            this.menuParams.ry_xh = row.ry_xh;
            let url = js.Web.GenerateUrl("Zhxx", "queryRyQx");
            let params = {ry_xh: row.ry_xh};
            let loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.menuParams.xtmk_dms = [];
                        let temp = data.rows;
                        for(let i = 0; i < temp.length; i++) {
                            _vue.menuParams.xtmk_dms.push(temp[i].xtmk_dm);
                        }
                        _vue.isMenu = true;
                        if(_vue.$refs.menu_tree)
                            _vue.$refs.menu_tree.setCheckedKeys([]);
                        setTimeout(() => {
                            _vue.$refs.menu_tree.setCheckedKeys(_vue.menuParams.xtmk_dms);s
                        }, 500);

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
        deleteUser: function (row) {
            var _vue = this;
            let url = js.Web.GenerateUrl("Zhxx", "deleteUser");
            let params = {ry_xh: row.ry_xh};
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
        // 设置菜单
        setMenu: function () {
            let _vue = this;
            let halfselectids=this.$refs.menu_tree.getHalfCheckedKeys();
            this.menuParams.xtmk_dms=halfselectids.concat(this.$refs.menu_tree.getCheckedKeys()) ;
            if(this.menuParams.xtmk_dms.length <= 0) {
                this.$message.warning('请选择菜单权限');
                return;
            }

            let url = js.Web.GenerateUrl("Zhxx", "setRy2Xtmk");
            let params = {ry_xh: this.menuParams.ry_xh};
            let temp = JSON.parse(JSON.stringify(this.menuParams.xtmk_dms));
            params.xtmk_dms = temp.join(',');

            let loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.$message.success("设置成功！");
                        _vue.isMenu = false;
                        if(params.ry_xh === localStorage.getItem('userId')) {
                            if(_vue.menuParams.xtmk_dms.indexOf('2') > -1) {
                                window.location.reload();
                            }else {
                                localStorage.setItem('navActive', 1);
                                var url = js.Web.GenerateUrl("Home", "Index");
                                js.Web.LinkClicked(this, url, false, false, true);
                            }
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

        //添加部门
        addBmBtn: function () {
            this.title = '添加部门';
            this.isBmAdd = true;
        },

        addBm: function () {
            var _vue = this;
            var url = js.Web.GenerateUrl("Zhxx", "saveBmxx");
            if(!this.bmmc.replace(/(^\s*)|(\s*$)/g, "")) {
                this.$message.warning('请填写名称');
                return;
            }


            var loading = control.loading(this);
            js.Web.AjaxRequest(this, url, {mc:this.bmmc}, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.$message.success(_vue.title + "成功！");
                        _vue.isBmAdd = false;
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
        //部门列表
        getBmdms: function () {
            var _vue = this;
            var url = js.Web.GenerateUrl("Zhxx", "searchBm");
            js.Web.AjaxRequest(this, url, null, null,
                function (success, data) {
                    if (data.success === true) {
                        var temp = JSON.parse(JSON.stringify(data.rows));
                        _vue.bmdms = temp;

                    }
                }, function (success, data) {
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

        addJsBtn: function () {
            this.title = '添加角色';
            this.isJsAdd = true;
        },
        addJs: function () {
            var _vue = this;
            var url = js.Web.GenerateUrl("Zhxx", "saveJsxx");
            if(!this.jsmc.replace(/(^\s*)|(\s*$)/g, "")) {
                this.$message.warning('请填写名称');
                return;
            }
            var loading = control.loading(this);
            js.Web.AjaxRequest(this, url, {mc:this.jsmc}, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.$message.success(_vue.title + "成功！");
                        _vue.isJsAdd = false;
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
        getJsdms: function () {
            var _vue = this;
            var url = js.Web.GenerateUrl("Zhxx", "searchJs");
            js.Web.AjaxRequest(this, url, null, null,
                function (success, data) {
                    if (data.success === true) {
                        var temp = JSON.parse(JSON.stringify(data.rows));
                        _vue.jsdms = temp;

                    }
                }, function (success, data) {
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