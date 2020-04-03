var vm = new Vue({
    el: "#msg",
    data: function () {
        return {
            tip: '', // 暂无消息
            isData: false,
            isLoading: true,

            msgList: [],
            num: 0
        };
    },
    components: {
        "nav-top": $component.navTop
    },
    mounted: function () {
        this.getMsgData();
    },
    methods: {
        // 请求消息数据
        getMsgData: function () {
            var _vue = this;
            var url = js.Web.GenerateUrl("Tztx", "queryMsg");
            var params = {
                start: _vue.num,
                pageSize: 10
            };
            var loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.msgList = _vue.msgList.concat(data.rows);
                        if(_vue.num === 0) {
                            data.total === 0 ? _vue.isData = false : _vue.isData = true;
                            data.total === 0 ? _vue.tip = '暂无消息' : _vue.tip = '';
                        }else {
                            data.resultCount === 0 ? _vue.isLoading = false : _vue.isLoading = true;
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
        // 加载更多
        loadingMore: function () {
            this.num += 10;
            this.getMsgData();
        },
        // 已阅
        read: function (item) {
            var _vue = this;
            var url = js.Web.GenerateUrl("Tztx", "UptMsgState");
            var params = {
                tztxjl_xh: item.tztxjl_xh,
                ry_xh: localStorage.getItem('userId')
            };
            var loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        var tempMsg = _vue.msgList;
                        for(var i = 0; i < tempMsg.length; i++) {
                            if(tempMsg[i].tztxjl_xh === item.tztxjl_xh) tempMsg[i].clzt_dm = '1';
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
        }
    }
});