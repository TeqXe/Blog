$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'master/tcomment/list',
        datatype: "json",
        colModel: [			
			{ label: '编号', name: 'id', index: 'id', width: 20, key: true },
			{ label: '用户ip', name: 'userip', index: 'userIp', width: 30 },
			{ label: '所属博客ID', name: 'blogid', index: 'blogId', width: 30 },
			{ label: '评论内容', name: 'content', index: 'content', width: 120 },
			{ label: '评论时间', name: 'commentdate', index: 'commentDate', width: 40 },
			{ label: '状态', name: 'state', index: 'state', width: 50,align:'center',formatter: function(value, options, row){
                    return value === 0 ?
                        '<span style="font-size: 100%" class="label label-warning">待审核</span>' :value === 1?'<span style="font-size: 100%" class="label label-success">审核通过</span>':
                        '<span style="font-size: 100%" class="label label-danger">审核不通过</span>';
                }},
            { label:'操作',name:'', width: 40, formatter:
                    function (value, row, rowObject) {
                        return '<a class="btn btn-primary" onclick="verifyPass('+rowObject["state"]+","+row.rowId+')">审核通过</a> <a class="btn btn-warning" onclick="verifyDeny('+rowObject["state"]+","+row.rowId+')">审核拒绝</a> ';
                    }}
        ],
		viewrecords: true,
        height: 520,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});
function verifyPass(state,rowId) {
	//console.log(state); 0 待审核 1 审核通过 2 审核不通过
	console.log(rowId);
	if(state != 0){
		alert("该评论已被审核，请确认！")
	}
	var para = [];
	para.push(1);
    para.push(rowId);
    confirm('你确定要审核通过吗 ？', function(){
        $.ajax({
            type: "POST",
            url: baseURL + "master/tcomment/verify",
            contentType: "application/json",
            data: JSON.stringify(para),
            success: function(r){
                if(r.code == 0){
                    alert('审核成功', function(index){
                        $("#jqGrid").trigger("reloadGrid");
                    });
                }else{
                    alert(r.msg);
                }
            }
        });
    });
}
function verifyDeny(state,rowId) {
    //console.log(state); 0 待审核 1 审核通过 2 审核不通过
    console.log(rowId);
    if(state != 0){
        alert("该评论已被审核，请确认！")
    }
    var para = [];
    para.push(2);
    para.push(rowId);
    confirm('你确定要审核拒绝吗 ？', function(){
        $.ajax({
            type: "POST",
            url: baseURL + "master/tcomment/verify",
            contentType: "application/json",
            data: JSON.stringify(para),
            success: function(r){
                if(r.code == 0){
                    alert('审核成功', function(index){
                        $("#jqGrid").trigger("reloadGrid");
                    });
                }else{
                    alert(r.msg);
                }
            }
        });
    });
}
var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
            content: null
        },
		showList: true,
		title: null,
		tComment: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "添加";
			vm.tComment = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "更新";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.tComment.id == null ? "master/tcomment/save" : "master/tcomment/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.tComment),
			    success: function(r){
			    	if(r.code === 0){
						alert('OK', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			confirm('你确定吗 ？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "master/tcomment/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('OK', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
        verifyPassBatch: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}

			confirm('你确定要审核通过这些评论吗 ？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "master/tcomment/verifyPassBatch",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('批量审核通过成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
        verifyDenyBatch: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}

			confirm('你确定要审核拒绝这些评论吗 ？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "master/tcomment/verifyDenyBatch",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('批量审核拒绝成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "master/tcomment/info/"+id, function(r){
                vm.tComment = r.tComment;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'content': vm.q.content},
                page:page
            }).trigger("reloadGrid");
		}
	}
});