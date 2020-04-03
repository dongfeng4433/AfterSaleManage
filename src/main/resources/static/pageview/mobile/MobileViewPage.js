var vm = new Vue({
    el: "#mobile",
    data: function () {
        return {
            params: {
                loginId: '',
                replyRyXh: '',
                sj_xxlr_xh: '',
                sqnr: '',
                fj_xh: []
            }
        };
    },
    mounted: function () {
        this.params.loginId = this.getParams('loginId');
        this.params.replyRyXh = this.getParams('replyRyXh');
        this.params.sj_xxlr_xh = this.getParams('sj_xxlr_xh');
    },
    methods: {
        // 获取地址栏参数
        getParams: function (name) {
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if(r!=null)return  unescape(r[2]); return null;
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
            this.params.fj_xh.push(response.tag);
        },
        // 文件移除
        handleRemove: function (file, fileList) {
            if (file && file.status === "success") {
                this.params.fj_xh.splice(this.params.fj_xh.indexOf(file.response.tag), 1);
            }
        },
        // 保存
        save: function () {
            var _vue = this;
            var url = js.Web.GenerateUrl("Mobile", "replyXxlr");
            var params = this.params;

            if(!params.sqnr.replace(/(^\s*)|(\s*$)/g, "") && this.params.fj_xh.length === 0) {
                this.$message.warning('内容与附件请选择其中一个填写');
                return;
            }

            params.fj_xh = params.fj_xh.join('|');

            var loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.$message.success("回复成功！");
                        _vue.$refs.upload.clearFiles();
                        _vue.params.sqnr = '';
                        _vue.params.fj_xh = [];
                    }
                }, function (success, data) {
                    loading.close();
                    _vue.$message.error(data.msg);
                }, true, false, true)
        }
    }
});