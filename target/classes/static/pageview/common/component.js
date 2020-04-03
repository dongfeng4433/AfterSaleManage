(function(w) {

    var u = {

        /**
         * 页面头部导航
         */
        navTop: {
            name: 'navTop',
            // props: ['obj'],
            data: function() {
                return  {
                    systemName:'售后服务管理系统',
                    userName: localStorage.getItem('usermc'),
                    msg: 0,   // 消息数
                    isActive: 1,
                    menuData:'<nav><ul>' +
                        '<li><a href="#">我的首页</a></li>'+
                        '<li><a href="#">工单管理</a><ul>\n' +
                        '            <li><a href="#">工单列表</a></li>\n' +
                        '            <li><a href="#">工单状态</a></li>\n' +
                        '            <li><a href="#">工单流程</a></li>\n' +
                        '            <li><a href="#">二级导航</a></li>\n' +
                        '        </ul>'+
                        '<li><a href="#">进销存</a><ul>\n' +
                        '            <li><a href="#">仓库列表</a></li>\n' +
                        '            <li><a href="#">货品管理</a></li>\n' +
                        '            <li><a href="#">库存</a></li>\n' +
                        '            <li><a href="#">入库单</a></li>\n' +
                        '            <li><a href="#">出库单</a></li>\n' +
                        '        </ul>'+
                        '<li><a href="#">消息提醒</a></li>'+
                        '</ul></nav>',
                    isUsers: false,
                    isLicence: false,
                    isTaxpayer: false,
                    isOrder:true,
                    isHistory: false
                }
            },
            mounted: function () {
                this.noReadNum();
                this.menuJur();
                this.isActive = localStorage.getItem('navActive');
            },
            watch:{
                menuData: function() {
                    this.$nextTick(function(){
                        /*现在数据已经渲染完毕*/
                    })
                }
            },
            created(){
                let bridge=this;
                window.menuClickHandler=bridge.menuClickHandler;
            },
            methods: {
                arrSort:function(list,field){
                    if(typeof list == 'Array'){
                        for(let i=0;i<list.length;i++){
                            for(let j=0;j<list.length;j++){
                                let o=list[i];let o2=list[j];
                                if(o[field]>o2[field]){
                                    //交换数据
                                    let temp=o2;
                                    list[j]=o;
                                    list[i]=temp;
                                }
                            }
                        }
                    }
                    return list;
                },
                loadMenuData:function(me,rows){
                    function getSubItems(xtmk_dm,list){
                        let arr=[];
                        for(let i=0;i<list.length;i++){
                            let o=list[i];
                            if(typeof o.sj_xtmk_dm !='undefined'&&o.sj_xtmk_dm==xtmk_dm){
                                arr.push(o);
                            }
                        }
                        me.arrSort(arr);
                        return arr;
                    }
                    function getMenuStr(o,subItems){
                        let strTemp='';
                        if(subItems&&subItems.length>0){
                            strTemp='<li><a href="#" onclick="menuClickHandler(this)" data-action="'+o.url+'">'+o.mc+'</a>';
                            strTemp+='<ul>';
                            for(let i=0;i<subItems.length;i++){
                                strTemp+='<li><a href="#" onclick="menuClickHandler(this)" data-action="'+subItems[i].url+'">'+subItems[i].mc+'</a></li>';
                            }
                            strTemp+='</ul></li>';
                        }else {
                            strTemp='<li><a href="#" onclick="menuClickHandler(this)" data-action="'+o.url+'">'+o.mc+'</a></li>';
                        }
                        return strTemp;
                    }
                    let str='<nav><ul>' ;
                    for(let i = 0; i < rows.length; i++) {
                        let oo=rows[i];
                        if(!oo.sj_xtmk_dm||typeof oo.sj_xtmk_dm =='undefined'){
                            let subitems=getSubItems(oo.xtmk_dm,rows);
                            str+=getMenuStr(oo,subitems);
                        }
                    }
                    str+='</nav></ul>' ;
                    return str;
                },
                // 菜单权限
                menuJur: function () {
                    var _vue = this;
                    var url = js.Web.GenerateUrl("Zhxx", "queryRyQx");
                    var params = {ry_xh: localStorage.getItem('userId')};
                    js.Web.AjaxRequest(this, url, params, null,
                        function (success, data) {
                            if (data.success === true) {
                                var temp = data.rows;
                                _vue.menuData=_vue.loadMenuData(_vue,temp);
                                // for(var i = 0; i < temp.length; i++) {
                                //     if(temp[i].xtmk_dm === control.menu_userManage) _vue.isUsers = true;
                                //     if(temp[i].xtmk_dm === control.menu_history) _vue.isHistory = true;
                                //     if(temp[i].xtmk_dm === control.menu_licence) _vue.isLicence = true;
                                //     if(temp[i].xtmk_dm === control.menu_licence) _vue.isTaxpayer = true;
                                // }
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
                // 未读消息
                noReadNum: function () {
                    var _vue = this;
                    var url = js.Web.GenerateUrl("Tztx", "queryMsg");
                    var params = {clzt: 0};
                    js.Web.AjaxRequest(this, url, params, null,
                        function (success, data) {
                            if (data.success === true) {
                                _vue.msg = data.total;
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
                // 退出
                loginOut: function () {
                    var _vue = this;
                    var url = js.Web.GenerateUrl("Zhxx", "logout");
                    var loading = control.loading(this);
                    js.Web.AjaxRequest(this, url, null, null,
                        function (success, data) {
                            loading.close();
                            if (data.success === true) {
                                localStorage.clear();
                                var url1 = js.Web.GenerateUrl("Zhxx", "login");
                                js.Web.LinkClicked(_vue, url1, false, false, false);
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
                // 跳转首页
                homePage: function () {
                    localStorage.setItem('navActive', 1);
                    var url = js.Web.GenerateUrl("Home", "Index");
                    js.Web.LinkClicked(this, url, false, false, true);
                },
                // 用户管理
                userManager: function () {
                    localStorage.setItem('navActive', 2);
                    var url = js.Web.GenerateUrl("Zhxx", "zhxxManage");
                    js.Web.LinkClicked(this, url, false, false, true);
                },
                // 历史记录
                history: function () {
                    localStorage.setItem('navActive', 3);
                    var url = js.Web.GenerateUrl("Home", "xxlrHistory");
                    js.Web.LinkClicked(this, url, false, false, true);
                },
                // 许可证
                licence: function () {
                    localStorage.setItem('navActive', 6);
                    var url = js.Web.GenerateUrl("Home", "Licence");
                    js.Web.LinkClicked(this, url, false, false, true);
                },
                taxpayer: function () {
                    localStorage.setItem('navActive', 7);
                    var url = js.Web.GenerateUrl("Home", "Taxpayer");
                    js.Web.LinkClicked(this, url, false, false, true);
                },
                // 跳转消息页
                message: function () {
                    localStorage.setItem('navActive', 4);
                    var url = js.Web.GenerateUrl("Tztx", "ToMyMsg");
                    js.Web.LinkClicked(this, url, false, false, true);
                },
                // 跳转修改密码
                menuClick: function (item) {
                    localStorage.setItem('navActive', 5);
                    var url = js.Web.GenerateUrl("Zhxx", "ToUpdatePassword");
                    js.Web.LinkClicked(this, url, false, false, true);
                },
                menuClickHandler:function(item){
                    let data=item.dataset;
                    if (data.action=='undefined'||typeof data.action =='undefined'){

                    }else {
                       let arr=data.action.split('/');
                       console.log(arr)
                        var url = js.Web.GenerateUrl(arr[1], arr[2]);
                        js.Web.LinkClicked(this, url, false, false, true);
                    }

                }
            },
            template: '<div id="nav-top" >' +
                '<div class="nav-left">{{systemName}}</div>' +
                '<div class="nav-center"><div class="nav-menu" v-html="menuData"></div>' +

                '</div>' +
                '<div class="nav-center2">' +
                '<div class="center2-item" :class="{\'navActive\': isActive == 4}" v-on:click="message" style="padding-left: 40px;">' +
                '<img class="item-4" src="../../img/icon-01.png" />消息提醒' +
                '<div class="item-msg">{{msg}}</div>' +
                '</div>' +
                '</div>'+
                '<div class="nav-right">' +
                '<div class="right-user"><img src="../../img/icon-03.png" /></div>' +
                '<div>' +
                '<el-dropdown trigger="click" v-on:command="menuClick">' +
                '<span class="el-dropdown-link">{{userName}}<i class="el-icon-arrow-down el-icon--right"></i></span>' +
                '<el-dropdown-menu slot="dropdown">' +
                '<el-dropdown-item command="pwd">修改密码</el-dropdown-item>' +
                '</el-dropdown-menu>' +
                '</el-dropdown>' +
                '</div>' +
                '<div class="line"></div>' +
                '<div class="right-logout" v-on:click="loginOut"><img src="../../img/icon-02.png" /></div>' +
                '<div v-on:click="loginOut" style="cursor: pointer;">退出</div>' +
                '</div>' +
                '</div>',
            template2: '<div id="nav-top">' +
                '<div class="nav-left">{{systemName}}</div>' +
                '<div class="nav-center">' +
                    '<div class="center-item" :class="{\'navActive\': isActive == 1}" v-on:click="homePage"><img class="item-1" src="../../img/icon-05.png" />我的首页</div>' +
                '<div class="center-item" v-if="isOrder" :class="{\'navActive\': isActive == 7}" v-on:click="taxpayer"><img class="item-3" src="../../img/icon-06.png" />工单管理</div>' +
                    '<div class="center-item" v-if="isUsers" :class="{\'navActive\': isActive == 2}" v-on:click="userManager"><img class="item-2" src="../../img/icon-04.png" />用户管理</div>' +
            '<div class="center-item" v-if="isLicence" :class="{\'navActive\': isActive == 6}" v-on:click="licence"><img class="item-3" src="../../img/icon-06.png" />许可证管理</div>' +

            '<div class="center-item" v-if="isHistory" :class="{\'navActive\': isActive == 3}" v-on:click="history"><img class="item-3" src="../../img/icon-06.png" />历史记录</div>' +
            '<div class="center-item" :class="{\'navActive\': isActive == 4}" v-on:click="message" style="padding-left: 40px;">' +
                        '<img class="item-4" src="../../img/icon-01.png" />消息提醒' +
                        '<div class="item-msg">{{msg}}</div>' +
                    '</div>' +
                '</div>' +
                '<div class="nav-right">' +
                    '<div class="right-user"><img src="../../img/icon-03.png" /></div>' +
                    '<div>' +
                        '<el-dropdown trigger="click" v-on:command="menuClick">' +
                            '<span class="el-dropdown-link">{{userName}}<i class="el-icon-arrow-down el-icon--right"></i></span>' +
                            '<el-dropdown-menu slot="dropdown">' +
                                '<el-dropdown-item command="pwd">修改密码</el-dropdown-item>' +
                            '</el-dropdown-menu>' +
                        '</el-dropdown>' +
                    '</div>' +
                    '<div class="line"></div>' +
                    '<div class="right-logout" v-on:click="loginOut"><img src="../../img/icon-02.png" /></div>' +
                    '<div v-on:click="loginOut" style="cursor: pointer;">退出</div>' +
                '</div>' +
            '</div>'
        }

    };

    window.$component = u;

})(window);
