$(function(){
    var viewPages={};
	 viewPages.dialog = function(msg) {
        BootstrapDialog.alert({title: '提示', message: msg})
    };
	viewPages.Url_Remove='/TSqblGdjbxx/remove';
	viewPages.Url_Add='/TSqblGdjbxx/addOrEdit';
	viewPages.Url_Add_Page='/TSqblGdjbxx/addOrEditPage';
	viewPages.Url_Search='/TSqblGdjbxx/search';

	viewPages.bind=function(){
        $('#query-btn').click(function(){
            $('#tableActivity').bootstrapTable('refresh');
        });
        $('#add_btn').click(function(){
            viewPages.add();
        });
        $('#delete_btn').click(function(){
            viewPages.remove();
        });
    };
	viewPages.remove = function() {
        var rows = $('#tableActivity').bootstrapTable('getSelections');
        var _this = this;
        if (!rows.length) {
            this.dialog('请选择删除项');
            return;
        }
        var ids = '';
        for (var i = 0; i < rows.length; i++) {
            ids += rows[i]['id'] + ',';
        }
        ids = ids.substring(0, ids.length - 1);
        $.ajax({
				 url:  viewPages.Url_Remove+'?ids=' + ids,

            success:function(result) {
                if (result.success) {
                    _this.dialog('删除成功');
                    $('#tableActivity').bootstrapTable('refresh')
                } else {
                    _this.dialog(result.msg || '删除失败');
                }
            }
        });
    };
	 viewPages.delOne = function(id) {
        var _this = this;
        $.ajax({
		url:  viewPages.Url_Remove+'?ids=' + id,

            success: function(result){
                if (result.success) {
                    _this.dialog('删除成功');
                    $('#tableActivity').bootstrapTable('refresh');
                } else {
                    _this.dialog(result.msg || '删除失败');
                }
            }
        });
    };
	 viewPages.add = function(id) {
        var _this = this;
        BootstrapDialog.show({
            title: id ? '修改' : '新增',
		message: $('<div></div>').load(viewPages.Url_Add_Page+'?id=' + id),

            buttons: [{
                label: '提交',
                cssClass: 'btn-success',
                action: function(dialogRef){
                    $.ajax({
		        url: viewPages.Url_Add,

                        method: 'post',
                        contentType: 'application/json',
                        data:JSON.stringify({
							 id: id,
							id: $('#edit_id').val(),
							hfhm: $('#edit_hfhm').val(),
							gdbh: $('#edit_gdbh').val(),
							sqlx_dm: $('#edit_sqlx_dm').val(),
							sqrxm: $('#edit_sqrxm').val(),
							sqsjfssj: $('#edit_sqsjfssj').val(),
							sqrhm: $('#edit_sqrhm').val(),
							sqnr: $('#edit_sqnr').val(),
							sqrdz: $('#edit_sqrdz').val(),
							lr_sj: $('#edit_lr_sj').val(),
							sfbm_bj: $('#edit_sfbm_bj').val(),
					 }),
                        success: function (res) {
                            if (res.success) {
                                _this.dialog(id ? '修改成功' : '添加成功');
                                dialogRef.close();
                                $('#tableActivity').bootstrapTable('refresh');
                            } else {
                                _this.dialog(res.msg || '请求失败');
                            }
                        }
                    });
                }
            }]
        })
    };
	viewPages.loadData= function() {
        var me=this;
		me.tColumns=[
  			{ checkbox: true,align: 'center' },
   			{ field: 'id',title: window.eui.TSqblGdjbxx.id,  visible:false,halign: 'center',align: 'center', height:2  },
   			{ field: 'hfhm',title: window.eui.TSqblGdjbxx.hfhm,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'gdbh',title: window.eui.TSqblGdjbxx.gdbh,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'sqlx_dm',title: window.eui.TSqblGdjbxx.sqlx_dm,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'sqrxm',title: window.eui.TSqblGdjbxx.sqrxm,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'sqsjfssj',title: window.eui.TSqblGdjbxx.sqsjfssj,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'sqrhm',title: window.eui.TSqblGdjbxx.sqrhm,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'sqnr',title: window.eui.TSqblGdjbxx.sqnr,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'sqrdz',title: window.eui.TSqblGdjbxx.sqrdz,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'lr_sj',title: window.eui.TSqblGdjbxx.lr_sj,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'sfbm_bj',title: window.eui.TSqblGdjbxx.sfbm_bj,  visible:true,halign: 'center',align: 'center', height:2  },
			{ field: 'option', title: '操作', align: 'center', width: 160,
                formatter: function (value, rowData, rowIndex) {
				return "<input type='button' class='btn btn-primary modify' value='修改' data-id='"+rowData.id + "'>" +
				"<input type='button' class='btn btn-warning' value='删除' data-id='"+rowData.id + "'>";
				}
            }		]

		me.config={
			 url:  viewPages.Url_Search, // 获取表格数据的url
            cache: false, striped: true, pagination: true,
            pageList: [10, 20], pageSize: 10,pageNumber: 1,sidePagination: 'server', // 设置为服务器端分页
            queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
                return {
                    pageSize: params.limit, // 每页要显示的数据条数
                    start: params.offset, // 每页显示数据的开始行号
                    question: $('#question').val() // 额外添加的参数
                }
            },
            responseHandler: function(res) {
                return { 'total': res.total, 'rows': res.rows };
            },
            onClickCell:function(field, value, row, ele){
                if(field == 'option'){
                    if (event.target.value === '修改') {
                        viewPages.add(row.id);
                    } else if (event.target.value === '删除'){
                        viewPages.delOne(row.id);
                    }
                }
            },
            columns: me.tColumns		};

		$('#tableActivity').bootstrapTable( me.config);
	}

   viewPages.init=function(){
        viewPages.bind();
        viewPages.loadData();
    }
	viewPages.init();
});

