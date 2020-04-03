var vm = new Vue({
    el: "#history",
    data: function () {
        return {
            dataList: [],   // 列表数据
            total: 0,       // 总数
            limit: 10,      // 每页显示个数
            page: 1,        // 当前页数

            isDetail: false,
            detailList: [], // 详情列表
            ryList: [],     // 人员列表
            row: '',
            lrry_xh: '',

            sqnr: ''
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
        // 获取人员列表
        getList: function () {
            var _vue = this;
            var url = js.Web.GenerateUrl("Zhxx", "searchZhxx");
            js.Web.AjaxRequest(this, url, null, null,
                function (success, data) {
                    if (data.success === true) {
                        _vue.ryList = data.rows;

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
        // 获取列表
        getDataList: function (judge) {
            var _vue = this;
            var url = js.Web.GenerateUrl("Home", "queryXxlrHistory");
            if(!judge) _vue.page = 1;
            var params = {
                start: (_vue.page-1)*10,
                pageSize: _vue.limit
            };
            if(this.sqnr.replace(/(^\s*)|(\s*$)/g, "")) params.sqnr = this.sqnr;
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
        //查看详情弹框
        detail: function (row) {
            var _vue = this;
            if(row) {
                _vue.row = row;
                _vue.lrry_xh = '';
            }
            var url = js.Web.GenerateUrl("Home", "queryChildXxlr");
            var params = {sj_xxlr_xh: _vue.row.xxlr_xh};
            if(this.lrry_xh.replace(/(^\s*)|(\s*$)/g, "")) params.ry_xh = this.lrry_xh;
            var loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.detailList = data.rows;
                        _vue.isDetail = true;
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

        // 判断格式
        judgeFile: function (row) {
            var arr = ['.jpeg', '.gif', '.jpg', '.png', '.bmp', '.pic', '.pdf', '.JPEG', '.GIF', '.JPG', '.PNG', '.BMP', '.PIC', '.PDF'];
            if(arr.indexOf(row.fj_wz) > -1) return true;
            return false;
        },
        // 在线预览
        online: function (row) {
            control.preView(row.fj_xh);
        }
    }
});