var vm = new Vue({
    el: "#homePage",
    data: function () {
        return {
            isDialog: false,
            isDetail: false,
            isCode: false, // 二维码

            title: '添加',
            assignList: [], // 交办列表
            total2: 0,      // 总数
            limit2: 10,     // 每页显示个数
            page2: 1,       // 当前页数

            waitList: [],   // 待办列表
            total: 0,       // 总数
            limit: 10,      // 每页显示个数
            page: 1,        // 当前页数

            detailList: [], // 详情列表
            ryList: [],     // 查看详情专用
            detailRow: '',
            detailJudge: '',
            lrry_xh: '',

            params: {
                sqnr: '',
                fj_xh: [],
                ryxhs: [],
                hf_sj_string: '',
                ry_name: '',
                ry_zj_hm: ''
            },
            row: '',

            list: [], // 人员列表

            userId: $('#userId').text()
        };
    },
    components: {
        "nav-top":$component.navTop
    },
    mounted: function () {
        this.getList();
        this.getAssignList(true);
        this.getWaitList(true);
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
                        var temp = JSON.parse(JSON.stringify(data.rows));
                        for(var i = 0; i < temp.length; i++) {
                            if(temp[i].ry_xh === _vue.userId) {
                                temp.splice(i, 1);
                            }
                        }
                        _vue.list = temp;

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
        // 获取交办列表
        getAssignList: function (judge) {
            var _vue = this;
            var url = js.Web.GenerateUrl("Home", "queryXxlr_Fq");
            if(!judge) _vue.page2 = 1;
            var params = {
                start: (_vue.page2-1)*10,
                pageSize: _vue.limit2
            };
            var loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.assignList = data.rows;
                        if(_vue.page2 === 1) _vue.total2 = data.total;
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
        // 交办列表分页
        changePages2: function (val) {
            this.page2 = val;
            this.getAssignList(true);
        },
        // 获取待办列表
        getWaitList: function (judge) {
            var _vue = this;
            var url = js.Web.GenerateUrl("Home", "queryXxlr_Db");
            if(!judge) _vue.page = 1;
            var params = {
                start: (_vue.page-1)*10,
                pageSize: _vue.limit
            };
            var loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.waitList = data.rows;
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
        // 待办列表分页
        changePages: function (val) {
            this.page = val;
            this.getWaitList(true);
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
        // 释放添加弹框
        addReply: function (judge, row) {
            this.title = judge ? '回复' : '添加';
            this.params = {
                sqnr: '',
                fj_xh: [],
                ryxhs: [],
                hf_sj_string: '',
                ry_name: '',
                ry_zj_hm: ''
            };
            if (!judge) {
                this.row = '';
            } else {
                this.row = row;
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
            params.sqnr = this.params.sqnr;
            // 添加
            if (!this.row) {
                if(this.params.ryxhs.length === 0) {
                    this.$message.warning('请选择人员');
                    return;
                }
                if(!this.params.hf_sj_string) {
                    this.$message.warning('请选择回复时间');
                    return;
                }
                if(!params.sqnr.replace(/(^\s*)|(\s*$)/g, "")) {
                    this.$message.warning('请填写内容');
                    return;
                }

                url=js.Web.GenerateUrl("Home", "saveXxlr");
                params.ryxhs = this.params.ryxhs.join(',');
                params.hf_sj_string = this.params.hf_sj_string;
                if(this.params.ry_name.replace(/(^\s*)|(\s*$)/g, "")) params.ry_name = this.params.ry_name;
                if(this.params.ry_zj_hm.replace(/(^\s*)|(\s*$)/g, "")) params.ry_zj_hm = this.params.ry_zj_hm;
            }
            // 回复
            else {
                if(!params.sqnr.replace(/(^\s*)|(\s*$)/g, "") && this.params.fj_xh.length === 0) {
                    this.$message.warning('内容与附件请选择其中一个填写');
                    return;
                }

                url=js.Web.GenerateUrl("Home", "replyXxlr");
                // delete params.ryxhs;
                // delete params.ry_name;
                // delete params.ry_zj_hm;
                // delete params.hf_sj_string;
                params.sj_xxlr_xh = this.row.xxlr_xh;
                params.replyRyXh = this.row.lrry_xh;
                params.fj_xh = this.params.fj_xh.join('|');
            }
            var loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.$message.success(_vue.title + "成功！");
                        _vue.getAssignList(false);
                        _vue.getWaitList(false);
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
        //查看弹框
        detail: function (row, judge) {
            var _vue = this;

            if(row) {
                _vue.detailRow = row;
                _vue.detailJudge = judge;
                _vue.lrry_xh = '';
            }

            var url = js.Web.GenerateUrl("Home", "queryChildXxlr");
            var params = {sj_xxlr_xh: _vue.detailRow.xxlr_xh};
            if(this.lrry_xh.replace(/(^\s*)|(\s*$)/g, "")) params.ry_xh = this.lrry_xh;
            var loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.detailList = [];
                        var temp = data.rows;
                        if(_vue.detailJudge) {
                            for(var i = 0; i < temp.length; i++) {
                                if(temp[i].lrry_xh === _vue.userId) {
                                    _vue.detailList.push(temp[i])
                                }
                            }
                        }else {
                            _vue.detailList = temp;
                        }
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
        judgeFile: function (item) {
            var arr = ['.jpeg', '.gif', '.jpg', '.png', '.bmp', '.pic', '.pdf', '.JPEG', '.GIF', '.JPG', '.PNG', '.BMP', '.PIC', '.PDF'];
            if(arr.indexOf(item.fj_wz) > -1) return true;
            return false;
        },
        // 在线预览
        online: function (item) {
            control.preView(item.fj_xh);
        },
        // 撤回
        withdraw: function (row) {
            var _vue = this;
            var url = js.Web.GenerateUrl("Home", "delXxlr");
            var params = {xxlr_xh: row.xxlr_xh};
            var loading = control.loading(this);
            js.Web.AjaxRequest(this, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.$message.success("撤回成功！");
                        _vue.getAssignList(false);
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
        // 二维码
        getQRCode: function (row) {
            var myDate = new Date();//获取系统当前时间
            this.isCode = true;
            this.$nextTick(function () {
                $('#qrcode').html(""); // 清除之前的二维码
                var url = control.baseUrl + '/Mobile/ToMobile?loginId=' + localStorage.getItem('userId') +
                    '&replyRyXh=' + row.lrry_xh + '&sj_xxlr_xh=' + row.xxlr_xh+ '&time=' + myDate.getTime();
                var qrcode = new QRCode('qrcode',{
                    width: 150,
                    height: 150,
                    text: url
                });
            })
        }
    }
});