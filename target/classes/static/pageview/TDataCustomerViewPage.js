$(function(){
    var viewPages={};
	 viewPages.dialog = function(msg) {
        BootstrapDialog.alert({title: '提示', message: msg})
    };
	viewPages.Url_Remove='/TDataCustomer/remove';
	viewPages.Url_Add='/TDataCustomer/addOrEdit';
	viewPages.Url_Add_Page='/TDataCustomer/addOrEditPage';
	viewPages.Url_Search='/TDataCustomer/search';

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
							enterprise_id: $('#edit_enterprise_id').val(),
							name: $('#edit_name').val(),
							short_name: $('#edit_short_name').val(),
							address: $('#edit_address').val(),
							type_code: $('#edit_type_code').val(),
							description: $('#edit_description').val(),
							telephone_number: $('#edit_telephone_number').val(),
							is_valid: $('#edit_is_valid').val(),
							create_time: $('#edit_create_time').val(),
							last_edit_time: $('#edit_last_edit_time').val(),
							creation_user_id: $('#edit_creation_user_id').val(),
							last_edit_user_id: $('#edit_last_edit_user_id').val(),
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
   			{ field: 'customer_id',title: window.eui.TDataCustomer.customer_id,  visible:false,halign: 'center',align: 'center', height:2  },
   			{ field: 'enterprise_id',title: window.eui.TDataCustomer.enterprise_id,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'name',title: window.eui.TDataCustomer.name,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'short_name',title: window.eui.TDataCustomer.short_name,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'address',title: window.eui.TDataCustomer.address,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'type_code',title: window.eui.TDataCustomer.type_code,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'description',title: window.eui.TDataCustomer.description,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'telephone_number',title: window.eui.TDataCustomer.telephone_number,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'is_valid',title: window.eui.TDataCustomer.is_valid,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'create_time',title: window.eui.TDataCustomer.create_time,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'last_edit_time',title: window.eui.TDataCustomer.last_edit_time,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'creation_user_id',title: window.eui.TDataCustomer.creation_user_id,  visible:true,halign: 'center',align: 'center', height:2  },
   			{ field: 'last_edit_user_id',title: window.eui.TDataCustomer.last_edit_user_id,  visible:true,halign: 'center',align: 'center', height:2  },
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

