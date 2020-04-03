var vm = new Vue({
    el: "#external",
    data: function () {
        return {
            dataList: [],   // 列表数据
            total: 0,       // 总数
            limit: 10,      // 每页显示个数
            page: 1,        // 当前页数
            gdbh:'',        //工单编号
            nr:''
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
            if(!judge) _vue.page = 1;
            var url = js.Web.GenerateUrl("GdManage", "queryGdxx");
            var params = {
                start: (_vue.page-1)*10,
                pageSize: _vue.limit,
                sqnr:_vue.nr,
                gdbh:_vue.gdbh
            };
            var loading = control.loading(_vue);
            js.Web.AjaxRequest(_vue, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.dataList = data.rows;
                        if(_vue.page === 1) _vue.total = data.total;
                    }
                }, function (success, data) {
                    loading.close();
                    _vue.$message.error(data.msg);
                }, true, false, true)
        },
        // 列表分页
        changePages: function (val) {
            var _vue = this;
            _vue.page = val;
            _vue.getDataList(true);
        },
        // 导出
        exprot: function() {
            var _vue = this;
            var params = {
                sqnr:_vue.nr,
                gdbh:_vue.gdbh
            };
            js.Web.AjaxRequest(_vue, js.Web.GenerateUrl("GdManage", "expGdxxToExcel"), params,
                function (success, data) {
                    if (success && data) {
                        var o_data=data.tag;
                        _vue.$message.success("导出成功!路径:"+o_data.fj_path);
                    }else {
                        _vue.$message.warning('失败');
                    }
                }, null, null, true, false, true);
        },
        // 退出
        logOut: function () {
            control.signOut(this);
        }
    }
});