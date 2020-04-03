var vm = new Vue({
    el: "#pwd",
    data: function () {
        return {
            oldMm: '',
            mm: '',
            confirmMm: ''
        };
    },
    components: {
        "nav-top": $component.navTop
    },
    mounted: function () {

    },
    methods: {
        updatePwd: function () {
            var _vue = this;

            if(!this.oldMm.replace(/(^\s*)|(\s*$)/g, "")) {
                this.$message.warning('请填写旧密码');
                return;
            }
            if(!this.mm.replace(/(^\s*)|(\s*$)/g, "")) {
                this.$message.warning('请填写新密码');
                return;
            }
            if(!this.confirmMm.replace(/(^\s*)|(\s*$)/g, "")) {
                this.$message.warning('请填写确认新密码');
                return;
            }
            if(this.mm !== this.confirmMm) {
                this.$message.warning('新密码不一致');
                return;
            }

            var url = js.Web.GenerateUrl("Zhxx", "updatePwd");
            var params = {
                ry_xh: localStorage.getItem('userId'),
                oldMm: this.oldMm,
                mm: this.mm
            };
            var loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.$message.success('修改成功，请重新登录！');
                        setTimeout(function () {
                            localStorage.clear();
                            var url1 = js.Web.GenerateUrl("Zhxx", "login");
                            js.Web.LinkClicked(_vue, url1, false, false, false);
                        },2000)
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