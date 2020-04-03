var vm = new Vue({
    el: "#external",
    data: function () {
        return {
            dataList: [],   // 列表数据
            queryType:'',
            queryText:''
        };
    },
    mounted: function () {

    },
    methods: {
        // 获取列表
        getDataList: function () {
            var _vue = this;
            var url = js.Web.GenerateUrl("YyzzTools", "queryYyzz");
            var params={};
            if(!_vue.queryType.replace(/(^\s*)|(\s*$)/g, "")){
                this.$message.warning('请选择企业/个体');
                return;
            }
            if(!_vue.queryText.replace(/(^\s*)|(\s*$)/g, "")){
                this.$message.warning('请输入社会信用代码或企业/个体户名称');
                return;
            }
            params.queryType = _vue.queryType;
            params.queryText = _vue.queryText;
            var loading = control.loading(this);
            js.Web.AjaxRequest(_vue, url, params, null,
                function (success, data) {
                    loading.close();
                    if (data.success === true) {
                        _vue.dataList = data.rows;
                    }
                }, function (success, data) {
                    loading.close();
                    _vue.$message.error(data.msg);
                }, true, false, true)
        },
        formatterColumn:function(row, column) {
            if(row.dzzp_BJ){
                return '<img class="span-img-icon-fj" v-show="scope.row.dzzp_BJ"  v-on:mouseover="viewDetails(scope.row)" v-on:mouseout="hiddenDetails(scope.row)"></img>&nbsp;';
            }
            return "";
        },
        formatterJBXXColumn:function(row, column) {
            var html="";
            if(row.zclx_DM=="1"){//个体户
                html+="<span>经营者："+row.jyz_XM+"</span><br>";
                html+="<span>核准日期："+row.HZ_RQ+"</span><br>";
                html+="<span>登记机构："+row.djjg+"</span><br>";
                html+="<span>个体户状态："+row.gtgsh_ZT+"</span><br>";
                html+="<span>经营场所："+row.jycs+"</span><br>";
                html+="<span>经营范围："+row.jyfw+"</span><br>";
                html+="<span>所属行业："+row.sshy+"</span><br>";
                html+="<span>信用等级："+row.xydj+"</span><br>";
                html+="<span>属地监管工商所："+row.sdjggss+"</span><br>";
                html+="<span>实际等级机构："+row.sjdjjg+"</span><br>";
                html+="<span>资金数额："+(row.zjse/100)+"（万元）</span><br>";
            }else if(row.zclx_DM=="2"){//公司成立
                html+="<span>法定代表人:"+row.fddbr_FZR+"</span><br>";
                html+="<span>电话号码:"+row.dh_HM+"</span><br>";
                html+="<span>登记机构:"+row.djjg+"</span><br>";
                html+="<span>企业状态:"+row.qy_ZT+"</span><br>";
                html+="<span>所属行业:"+row.ss_HY+"</span><br>";
                html+="<span>地址:"+row.zs+"</span><br>";
                html+="<span>注册资本:"+(row.zczb/100)+"（万元）</span><br>";
            }

            return html;
        },
        viewDetails:function(row){
            //var img = document.getElementById("image");
            //var x = event.clientX + document.body.scrollLeft + 20;
            //var y = event.clientY + document.body.scrollTop - 5;
            //
            //img.style.left = x + "px";
            //img.style.top = y + "px";
            //img.style.display = "block";
            //img.getElementsByTagName("img")[0].src="/YyzzTools/queryBlob";
            //console.log("show");
            toolTip("<img style='width: 300px;height: 400px' class='el-img-loading'><img class='el-img-default' onload='loadImage()'  id='"+row.zch+"'style='width: 300px;height: 400px' src='/YyzzTools/queryBlob?ZCH="+row.zch+"'>");
        },
        hiddenDetails:function(row){
            //var img = document.getElementById("image");
            //img.style.display = "none";
            console.log("hidden");
            toolTip();
        },
        viewDetails2:function(fj_xh){
            toolTip("<img style='width: 300px;height: 400px' class='el-img-loading'><img class='el-img-default' onload='loadImage()' id='"+fj_xh+"' style='width: 300px;height: 400px' src='http://localhost:8085/YSJ/DownloadPictures?FJ_XH="+fj_xh+"'>");
        },
        hiddenDetails2:function(fj_xh){
            toolTip();
        },
        downloadImage:function(type,params){
            if(type==1){
                var url='/YyzzTools/queryBlob?ZCH="+row.zch+"';
                js.Web.OpenWindow(this,url,"blank");
            }else if(type==2){
                var url="http://localhost:8085/YSJ/DownloadPictures?FJ_XH="+params;
                js.Web.OpenWindow(this,url,"blank");
            }
        },
        loadImage:function(){
            console.log("loaded");
            alert(1);
        },
        // 下载
        downloadFile: function (row) {
            var _vue = this;
            var fj_xh=row.fj_XH;
            console.log(fj_xh)
            if(fj_xh !== null && fj_xh !== undefined && fj_xh !=='') {
                var params ={FJ_XH: fj_xh,ZCH:row.zch,MC:row.mc};
                // var filePath = js.Web.GenerateUrl("YyzzTools", "downloadYyzz", null, params);
                // js.Web.OpenWindow(null, filePath, '_blank', '');
                var loading = control.loading(_vue);
                js.Web.AjaxRequest(_vue, js.Web.GenerateUrl("YyzzTools", "downloadYyzz"), params,
                    function (success, data) {
                        if (success && data) {
                            loading.close();
                            var o_data=data.tag;
                            _vue.$message.success("下载成功!路径:"+o_data);

                             var filePath = js.Web.GenerateUrl("YyzzTools", "downloadYyzz2", null, params);
                             js.Web.OpenWindow(null, filePath, '_blank', '');
                        }else {
                            loading.close();
                            _vue.$message.warning('失败');
                        }
                    }, null, null, true, false, true);
            }else{
                _vue.$message.error("没有可下载的文件");
            }

        },
        // 退出
        logOut: function () {
            control.signOut(this);
        }
    }
});