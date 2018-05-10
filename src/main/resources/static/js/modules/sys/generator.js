$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/generator/list',
        datatype: "json",
        colModel: [			
			{ label: 'tableName', name: 'tableName', width: 100, key: true },
			{ label: 'Engine', name: 'engine', width: 70},
			{ label: 'tableComment', name: 'tableComment', width: 100 },
			{ label: 'createTime', name: 'createTime', width: 100 }
        ],
		viewrecords: true,
        height: 685,
        rowNum: 10,
		rowList : [10,30,50,100,200],
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
			tableName: null
		}
	},
	methods: {
		query: function () {
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'tableName': vm.q.tableName},
                page:1 
            }).trigger("reloadGrid");
		},
		generator: function() {
			var tableNames = getSelectedRows();
			if(tableNames == null){
				return ;
			}
			var token = localStorage.getItem("token");
			location.href = baseURL + "sys/generator/code?token=" + token + "&tables=" + JSON.stringify(tableNames);
		}
	}
});

