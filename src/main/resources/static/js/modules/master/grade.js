$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'master/grade/list',
        datatype: "json",
        colModel: [			
			{ label: '年级ID', name: 'gid', index: 'gid', width: 50, key: true },
			{ label: '年级', name: 'gname', index: 'gname', width: 80 },
			{ label: '描述', name: 'gdesc', index: 'gdesc', width: 80 },
            { label:'操作',name:'', width: 80, formatter:
                    function (value, row, index) {
                        return '<a class="btn btn-danger" onclick="singleDel('+row.rowId+')">删除</a> <a class="btn btn-info" onclick="singleUp('+row.rowId+')">更新</a>';
                    }}
        ],
		viewrecords: true,
        height: 585,
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		grade: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.grade = {};
		},
		update: function (event) {
			var gid = getSelectedRow();
			if(gid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "更新";
            console.log(typeof gid);
            vm.getInfo(gid)
		},
		saveOrUpdate: function (event) {
			if(!vm.validator()){
				return;
			}
			var url = vm.grade.gid == null ? "master/grade/save" : "master/grade/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.grade),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var gids = getSelectedRows();
			if(gids == null){
				return ;
			}
			
			confirm('你确定要删除吗 ？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "master/grade/delete",
                    contentType: "application/json",
				    data: JSON.stringify(gids),
				    success: function(r){
						if(r.code == 0){
							alert('删除成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(gid){
			$.get(baseURL + "master/grade/info/"+gid, function(r){
                vm.grade = r.grade;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
		validator:function () {
			if (isBlank(vm.grade.gname)){
				alert("年级名不可为空！");
				return false;
			}
			if (isBlank(vm.grade.gdesc)){
				alert("年级描述不可为空！");
				return false;
			}
			return true;
        }
	}
});

function singleUp(rowId) {
	console.log(typeof rowId);
    vm.showList = false;
    vm.title = "更新";
    vm.getInfo(rowId);
}

function singleDel(rowId) {
	var para = new Array();
	para[0] = rowId;
    confirm('你确定要删除吗 ？', function(){
        $.ajax({
            type: "POST",
            url: baseURL + "master/grade/delete",
            contentType: "application/json",
            data: JSON.stringify(para),
            success: function(r){
                if(r.code == 0){
                    alert('删除成功', function(index){
                        $("#jqGrid").trigger("reloadGrid");
                    });
                }else{
                    alert(r.msg);
                }
            }
        });
    });
}