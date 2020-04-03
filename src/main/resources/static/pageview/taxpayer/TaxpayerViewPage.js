var vm = new Vue({
    el: "#taxpayer",
    data: function () {
        return {
            dataList: [],   // 列表数据
            total: 0,       // 总数
            limit: 10,      // 每页显示个数
            page: 1,        // 当前页数

            title: '添加',
            isDialog: false,
            isDetail: false,
            isImportDialog:false,
            detailList: [], // 详情列表
            ryList: [],     // 人员列表
            row: '',

            sqnr: '',
            xkz_bh:'',
            jgdw_mc:'',
            params: {
                jgdw_mc: '',xkz_bh:'',tyshxydm:'',dzzs:'',fddbr_fzr:'',zczb_je:0,jgxz:'',xkwh:'',
                lx_dm:'',fzrq:'',yxrq_qs:'',yxrq_jz:'',fwfw:'',lxdh:'',lbzh:'',
                fj_xh: []
            },
            row: '',

            list: [], //

            userId: $('#userId').text()
        };
    },
    components: {
        "nav-top": $component.navTop
    },
    mounted: function () {
        this.getDataList(true);
        this.getList();
    },
    methods: {
        //时间格式化
        dateFormat:function(row, column) {
            var date = row[column.property];
            if (date == undefined) {
                return "";
            }
            return date.substr(0,10);
        },
        // 获取类型列表
        getList: function () {
            var _vue = this;
            _vue.list=new Array();
            _vue.list.push({'lx_dm':'1','lx_mc':'人力资源服务许可证'});
            _vue.list.push({'lx_dm':'2','lx_mc':'劳务派遣经营许可证'});
        },
        // 获取列表
        getDataList: function (judge) {
            var _vue = this;
            var url = js.Web.GenerateUrl("Taxpayer", "searchTaxpayer");
            if(!judge) _vue.page = 1;
            var params = {
                start: (_vue.page-1)*10,
                pageSize: _vue.limit
            };
            if(this.jgdw_mc.replace(/(^\s*)|(\s*$)/g, "")) params.jgdw_mc = this.jgdw_mc;
            if(this.xkz_bh.replace(/(^\s*)|(\s*$)/g, "")) params.xkz_bh = this.xkz_bh;
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
        // 文件上传，限制文件大小
        handlePre: function(file){
            var maxsize=10 * 1024 * 1024;
            if(file.size > maxsize){
                this.$message.error('上传图片大小不能超过 10MB!');
                return false;
            }else{
                return true;
            }
        },
        // 上传成功
        handleSuccess: function (response, file, fileList) {
            this.params.fj_xh=new Array();//只有一个文件
            this.params.fj_xh.push(response.tag);
        },
        handleSuccess2: function (response, file, fileList) {
            this.params.upload_fj_xh=response.tag;//只有一个文件
        },
        // 文件移除
        handleRemove: function (file, fileList) {
            if (file && file.status === "success") {
                this.params.fj_xh.splice(this.params.fj_xh.indexOf(file.response.tag), 1);
            }
        },
        // 释放添加弹框
        addLicence: function (isNew, row) {
            this.title = isNew ? '新增' : '编辑';
            this.params = {
                jgdw_mc: '',xkz_bh:'',tyshxydm:'',dzzs:'',fddbr_fzr:'',zczb_je:0,jgxz:'',xkwh:'',
                lx_dm:'',fzrq:'',yxrq_qs:'',yxrq_jz:'',fwfw:'',lxdh:'',xkzh:'',
                fj_xh: []
            };
            if (isNew) {
                this.row = '';
            } else {
                this.row = row;
                this.params.jgdw_mc=  this.row.jgdw_mc;
                this.params.xkz_bh= this.row.xkz_bh;
                this.params.tyshxydm= this.row.tyshxydm;this.params.dzzs= this.row.dzzs;
                this.params.fddbr_fzr= this.row.fddbr_fzr;this.params.zczb_je= this.row.zczb_je;
                this.params.jgxz= this.row.jgxz;this.params.xkwh= this.row.xkwh;
                this.params.lx_dm= this.row.lx_dm;this.params.fzrq= this.row.fzrq;
                this.params.yxrq_qs= this.row.yxrq_qs;this.params.yxrq_jz= this.row.yxrq_jz;
                this.params.fwfw= this.row.fwfw;
                this.params.lxdh= this.row.lxdh;
                this.params.lbzh= this.row.lbzh;
                if(this.row.fj_xh)
                    this.params.fj_xh.push(this.row.fj_xh);
            }
            this.isDialog = true;
        },
        // 关闭弹框
        closeDialog: function () {
            this.$refs.upload.clearFiles();
            this.isDialog = false;
        },
        // 确定
        ok: function () {
            var _vue = this;
            var url = '';
            var params = {};
            params.jgdw_mc = this.params.jgdw_mc;
            params.lx_dm = this.params.lx_dm;
            params.fzrq = this.params.fzrq;
            params.xkz_bh = this.params.xkz_bh;
            params.tyshxydm = this.params.tyshxydm;
            params.dzzs = this.params.dzzs;
            params.fddbr_fzr = this.params.fddbr_fzr;
            params.jgxz = this.params.jgxz;
            params.zczb_je = this.params.zczb_je;
            params.xkwh = this.params.xkwh;
            params.fwfw = this.params.fwfw;
            params.yxrq_qs = this.params.yxrq_qs;
            params.yxrq_jz = this.params.yxrq_jz;
            params.lxdh = this.params.lxdh;
            params.lbzh = this.params.lbzh;
            url=js.Web.GenerateUrl("Taxpayer", "saveTaxpayer");
            if(!this.params.lx_dm) {
                this.$message.warning('请选择类型');
                return;
            }
            if(!this.params.jgdw_mc) {
                this.$message.warning('请选择机构单位名称');
                return;
            }
            if(!this.params.fwfw.replace(/(^\s*)|(\s*$)/g, "")) {
                this.$message.warning('请填写服务范围内容');
                return;
            }
            // 添加
            if (!this.row) {
                params.fj_xh = this.params.fj_xh.join('|');
                if(this.params.fwfw.replace(/(^\s*)|(\s*$)/g, "")) params.fwfw = this.params.fwfw;
                if(this.params.jgdw_mc.replace(/(^\s*)|(\s*$)/g, "")) params.jgdw_mc = this.params.jgdw_mc;
            }
            // 修改
            else {
                params.xkz_xh=this.row.xkz_xh;
                // delete params.ryxhs;
                // delete params.ry_name;
                // delete params.ry_zj_hm;
                // delete params.hf_sj_string;
                params.fj_xh = this.params.fj_xh.join('|');
            }
            var loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.$message.success(_vue.title + "成功！");
                        _vue.getDataList(false);
                        _vue.closeDialog();
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

        openUpload:function(){
            this.isImportDialog=true;
        },
        closeUpload:function(){
          this.isImportDialog=false;
        },
        okImport: function () {
            var _vue = this;
            var url = '';
            var params = {};
            params.upload_fj_xh = this.params.upload_fj_xh;
            url=js.Web.GenerateUrl("Taxpayer", "importTaxpayer");
            if(!this.params.upload_fj_xh) {
                this.$message.warning('请选择Excel文件');
                return;
            }
            var loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.$message.success(_vue.title + "成功！");
                        _vue.getDataList(false);
                        _vue.closeUpload();
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
        // 下载
        downloadFile: function (row) {
            var filePath = js.Web.GenerateUrl("Home", "downloadFjxxFile", null, {fj_xh: row.fj_xh,fj_mc:row.fj_mc});
            js.Web.OpenWindow(null, filePath, '_blank', '');
        },
        deleteLicence:function(row){
            var _vue = this;
            var url = '';
            var params = {};
            params.xkz_xh = row.xkz_xh;
            url=js.Web.GenerateUrl("Taxpayer", "deleteTaxpayer");
            if(!params.xkz_xh) {
                this.$message.warning('请选择记录');
                return;
            }
            var loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.$message.success("删除成功！");
                        _vue.getDataList(false);
                        _vue.closeUpload();
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

        // 在线预览
        online: function (row) {
            control.preView(row.fj_xh);
        }
    }
});