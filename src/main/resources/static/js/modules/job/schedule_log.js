$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/scheduleLog/list',
        datatype: "json",
        colModel: [			
            { label: 'logId', name: 'logId', width: 50, key: true },
			{ label: 'jobId', name: 'jobId', width: 50},
			{ label: 'beanName', name: 'beanName', width: 60 },
			{ label: 'methodName', name: 'methodName', width: 60 },
			{ label: 'params', name: 'params', width: 60 },
			{ label: 'status', name: 'status', width: 50, formatter: function(value, options, row){
				return value === 0 ? 
					'<span class="label label-success">SUCCESS</span>' :
					'<span class="label label-danger pointer" onclick="vm.showError('+row.logId+')">ERROR</span>';
			}},
			{ label: 'times(ms)', name: 'times', width: 70 },
			{ label: 'createTime', name: 'createTime', width: 80 }
        ],
		viewrecords: true,
        height: 685,
        rowNum: 10,
		rowList : [10,30,50,100,200],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: false,
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
			jobId: null
		}
	},
	methods: {
		query: function () {
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'jobId': vm.q.jobId},
                page:1 
            }).trigger("reloadGrid");
		},
		showError: function(logId) {
			$.get(baseURL + "sys/scheduleLog/info/"+logId, function(r){
				parent.layer.open({
				  title:'ERROR',
				  closeBtn:0,
				  content: r.log.error
				});
			});
		},
		back: function () {
			history.go(-1);
		}
	}
});

