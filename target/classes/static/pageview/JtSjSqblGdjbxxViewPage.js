$(function(){
    var viewPages={};
	 viewPages.dialog = function(msg) {
        BootstrapDialog.alert({title: '提示', message: msg})
    };
	viewPages.Url_Remove='/JtSjSqblGdjbxx/remove';
	viewPages.Url_Add='/JtSjSqblGdjbxx/addOrEdit';
	viewPages.Url_Add_Page='/JtSjSqblGdjbxx/addOrEditPage';
	viewPages.Url_Search='/JtSjSqblGdjbxx/search';

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
							gdbh: $('#edit_gdbh').val(),
							sqmd: $('#edit_sqmd').val(),
							sqnr: $('#edit_sqnr').val(),
							sqlx: $('#edit_sqlx').val(),
							hflx: $('#edit_hflx').val(),
							gjz: $('#edit_gjz').val(),
							sjgk: $('#edit_sjgk').val(),
							sqhm: $('#edit_sqhm').val(),
							sqr: $('#edit_sqr').val(),
							ldsj: $('#edit_ldsj').val(),
							sqsjfssj: $('#edit_sqsjfssj').val(),
							sqdz: $('#edit_sqdz').val(),
							sfjj: $('#edit_sfjj').val(),
							sfbm: $('#edit_sfbm').val(),
							tbbj: $('#edit_tbbj').val(),
							guid: $('#edit_guid').val(),
							cbdw: $('#edit_cbdw').val(),
							cbdw2: $('#edit_cbdw2').val(),
							lxdh1: $('#edit_lxdh1').val(),
							lxdh2: $('#edit_lxdh2').val(),
							sqr_xb: $('#edit_sqr_xb').val(),
							sqlyqd: $('#edit_sqlyqd').val(),
							wjgk: $('#edit_wjgk').val(),
							hfjg: $('#edit_hfjg').val(),
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
   			{ field: 'id',title: window.eui.JtSjSqblGdjbxx.id,  visible:false,halign: 'center',align: 'center', height:2  },
   			{ field: 'gdbh',title: window.eui.JtSjSqblGdjbxx.gdbh,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'sqmd',title: window.eui.JtSjSqblGdjbxx.sqmd,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'sqnr',title: window.eui.JtSjSqblGdjbxx.sqnr,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'sqlx',title: window.eui.JtSjSqblGdjbxx.sqlx,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'hflx',title: window.eui.JtSjSqblGdjbxx.hflx,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'gjz',title: window.eui.JtSjSqblGdjbxx.gjz,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'sjgk',title: window.eui.JtSjSqblGdjbxx.sjgk,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'sqhm',title: window.eui.JtSjSqblGdjbxx.sqhm,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'sqr',title: window.eui.JtSjSqblGdjbxx.sqr,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'ldsj',title: window.eui.JtSjSqblGdjbxx.ldsj,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'sqsjfssj',title: window.eui.JtSjSqblGdjbxx.sqsjfssj,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'sqdz',title: window.eui.JtSjSqblGdjbxx.sqdz,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'sfjj',title: window.eui.JtSjSqblGdjbxx.sfjj,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'sfbm',title: window.eui.JtSjSqblGdjbxx.sfbm,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'tbbj',title: window.eui.JtSjSqblGdjbxx.tbbj,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'guid',title: window.eui.JtSjSqblGdjbxx.guid,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'cbdw',title: window.eui.JtSjSqblGdjbxx.cbdw,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'cbdw2',title: window.eui.JtSjSqblGdjbxx.cbdw2,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'lxdh1',title: window.eui.JtSjSqblGdjbxx.lxdh1,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'lxdh2',title: window.eui.JtSjSqblGdjbxx.lxdh2,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'sqr_xb',title: window.eui.JtSjSqblGdjbxx.sqr_xb,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'sqlyqd',title: window.eui.JtSjSqblGdjbxx.sqlyqd,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'wjgk',title: window.eui.JtSjSqblGdjbxx.wjgk,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'hfjg',title: window.eui.JtSjSqblGdjbxx.hfjg,  visible:true,halign: 'center',align: 'center', height:2  },
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

