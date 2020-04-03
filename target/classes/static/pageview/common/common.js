var control = {
    menu_userManage: '2',
    menu_history: '1',
    menu_licence: '8',//许可证
    baseUrl: 'http://192.168.0.159:9000',
    signOut: function (vm) {
        var url = js.Web.GenerateUrl("Zhxx", "login");
        js.Web.LinkClicked(vm, url, false, false, false);
    },
    // 加载loading...
    loading: function (vm) {
        return vm.$loading({
            lock: true,
            text: 'Loading',
            spinner: 'el-icon-loading',
            background: 'rgba(230, 230, 230, 0.4)'
        });
    },
    // 在线预览
    preView: function (fjXh) {
        var curWwwPath = window.document.location.href;
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        var localhostPath = curWwwPath.substring(0,pos);
        window.open(localhostPath + "/Home/preview?fileName=" + fjXh);
        // window.open(localhostPath + "/js/generic/web/viewer.html?file=/Home/preview?fileName=" + fjXh);
    },
    // 年月日
    getTimeNYR: function (time) {
        if(time !== null && time !== undefined && time !=='') {
            var date = new Date(time);
            var m = date.getMonth() + 1;
            var d = date.getDate();
            if (m >= 1 && m <= 9) m = "0" + m;
            if (d >= 0 && d <= 9) d = "0" + d;
            return date.getFullYear() + '-' + m + "-" + d;
        }
        return '';
    },
    // 年月日时分秒
    getTimeDate: function (time) {
        if (time !== null && time !== undefined && time !== '') {
            var date = new Date(time);
            var m = date.getMonth() + 1;
            var d = date.getDate();
            var h = date.getHours();
            var f = date.getMinutes();
            var s = date.getSeconds();
            if (m >= 1 && m <= 9) m = "0" + m;
            if (d >= 0 && d <= 9) d = "0" + d;
            if (h >= 0 && h <= 9) h = '0' + h;
            if (f >= 0 && f <= 9) f = '0' + f;
            if (s >= 0 && s <= 9) s = '0' + s;
            return date.getFullYear() + '-' + m + "-" + d + " " + h + ':' + f + ":" + s;
        }
        return '';
    }
};
