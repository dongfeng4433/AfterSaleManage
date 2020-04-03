var vm = new Vue({
    el: "#login",
    data: function () {
        return {
            loginParams: {
                username: '',
                password: '',
                pro_type:''
            },

            isLoading: false,
            proOptions:[]
        };
    },
    mounted: function () {
       this.getProOptions();

    },
    methods: {
        loginBtn: function () {
            var _vue = this;
            this.isLoading = true;
            var url = js.Web.GenerateUrl("Zhxx", "loginIn");
            var params = this.loginParams;
            console.log(params);
            console.log(params.pro_type);
            if(""==params.pro_type){
                js.Web.AjaxRequest(this, url, params, null,
                    function (success, data) {
                        _vue.isLoading = false;
                        if (data.success === true) {
                            localStorage.setItem('usermc', data.tag.usermc);
                            localStorage.setItem('userId', data.tag.userid);
                            localStorage.setItem('navActive', 1);
                            var url = js.Web.GenerateUrl("Home", "Index");
                            js.Web.LinkClicked(this, url, false, false, true);
                        }
                    }, function (success, data) {
                        _vue.isLoading = false;
                        js.Web.ShowErrorMsg(this, data.msg, "错误");
                    }, true, false, true)
            }else  if("1"===params.pro_type){
                js.Web.AjaxRequest(this, url, params, null,
                    function (success, data) {
                        _vue.isLoading = false;
                        if (data.success === true) {
                            localStorage.setItem('usermc', data.tag.usermc);
                            localStorage.setItem('userId', data.tag.userid);
                            localStorage.setItem('navActive', 1);
                            var url = js.Web.GenerateUrl("Home", "Index");
                            js.Web.LinkClicked(this, url, false, false, true);
                        }
                    }, function (success, data) {
                        _vue.isLoading = false;
                        js.Web.ShowErrorMsg(this, data.msg, "错误");
                    }, true, false, true)
            }else  if("2"===params.pro_type){
                //工单查询
                var url = js.Web.GenerateUrl("GdManage", "Index");
                js.Web.LinkClicked(this, url, false, false, true);
            }else  if("3"===params.pro_type){
                //营业执照辅助下载工具
                var url = js.Web.GenerateUrl("YyzzTools", "Index");
                js.Web.LinkClicked(_vue, url, false, false, true);
            }

        },
        getProOptions: function () {
            var _vue = this;
            _vue.proOptions= [{
                value: '1',
                label: '信息核验'
            }, {
                value: '2',
                label: '小程序工单查询'
            }, {
                value: '3',
                label: '营业执照检索'
            }, {
                value: '4',
                label: '批管同步'
            }, {
                value: '5',
                label: '绩效考核平台'
            }]
        },
        onChange:function(val){
        }
    }
});