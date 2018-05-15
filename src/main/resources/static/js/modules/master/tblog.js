$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'master/tblog/list',
        datatype: "json",
        colModel: [			
			{ label: '博客编号', name: 'id', index: 'id', width: 50, key: true },
			{ label: '标题', name: 'title', index: 'title', width: 80 },
			{ label: '摘要', name: 'summary', index: 'summary', width: 80 },
			{ label: '发表日期', name: 'releasedate', index: 'releaseDate', width: 80 },
			{ label: '点击次数', name: 'clickhit', index: 'clickHit', width: 80 },
			{ label: '回复次数', name: 'replyhit', index: 'replyHit', width: 80 },
			//TODO 关联查询 查询类别名
			/*{ label: '', name: 'typeid', index: 'typeId', width: 80 },*/
			{ label: '关键字', name: 'keyword', index: 'keyWord', width: 80 }
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			summary:null
		},
		showList: true,
		title: null,
		tBlog: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "添加";
			vm.tBlog = {};
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
			var url = vm.tBlog.id == null ? "master/tblog/save" : "master/tblog/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.tBlog),
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
				    url: baseURL + "master/tblog/delete",
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
		getInfo: function(id){
			$.get(baseURL + "master/tblog/info/"+id, function(r){
                vm.tBlog = r.tBlog;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{'summary':vm.q.summary},
                page:page
            }).trigger("reloadGrid");
		}
	}
});