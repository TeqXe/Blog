$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/schedule/list',
        datatype: "json",
        colModel: [			
			{ label: 'jobId', name: 'jobId', width: 60, key: true },
			{ label: 'beanName', name: 'beanName', width: 100 },
			{ label: 'methodName', name: 'methodName', width: 100 },
			{ label: 'params', name: 'params', width: 100 },
			{ label: 'cronExpression ', name: 'cronExpression', width: 100 },
			{ label: 'remark ', name: 'remark', width: 100 },
			{ label: 'status', name: 'status', width: 60, formatter: function(value, options, row){
				return value === 0 ? 
					'<span class="label label-success">success</span>' : 
					'<span class="label label-danger">danger</span>';
			}}
        ],
		viewrecords: true,
        height: 685,
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
			beanName: null
		},
		showList: true,
		title: null,
		schedule: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "ADD";
			vm.schedule = {};
		},
		update: function () {
			var jobId = getSelectedRow();
			if(jobId == null){
				return ;
			}
			
			$.get(baseURL + "sys/schedule/info/"+jobId, function(r){
				vm.showList = false;
                vm.title = "UPDATE";
				vm.schedule = r.schedule;
			});
		},
		saveOrUpdate: function () {
            if(vm.validator()){
                return ;
            }

			var url = vm.schedule.jobId == null ? "sys/schedule/save" : "sys/schedule/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.schedule),
			    success: function(r){
			    	if(r.code === 0){
						alert('SUCCESS', function(){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function () {
			var jobIds = getSelectedRows();
			if(jobIds == null){
				return ;
			}
			
			confirm('DELETE？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/schedule/delete",
                    contentType: "application/json",
				    data: JSON.stringify(jobIds),
				    success: function(r){
						if(r.code == 0){
							alert('SUCCESS', function(){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		pause: function () {
			var jobIds = getSelectedRows();
			if(jobIds == null){
				return ;
			}
			
			confirm('PAUSE？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/schedule/pause",
                    contentType: "application/json",
				    data: JSON.stringify(jobIds),
				    success: function(r){
						if(r.code == 0){
							alert('SUCCESS', function(){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		resume: function () {
			var jobIds = getSelectedRows();
			if(jobIds == null){
				return ;
			}
			
			confirm('RESUME？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/schedule/resume",
                    contentType: "application/json",
				    data: JSON.stringify(jobIds),
				    success: function(r){
						if(r.code == 0){
							alert('SUCCESS', function(){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		runOnce: function () {
			var jobIds = getSelectedRows();
			if(jobIds == null){
				return ;
			}
			
			confirm('RUN？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/schedule/run",
                    contentType: "application/json",
				    data: JSON.stringify(jobIds),
				    success: function(r){
						if(r.code == 0){
							alert('SUCCESS', function(){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'beanName': vm.q.beanName},
                page:page 
            }).trigger("reloadGrid");
		},
        validator: function () {
            if(isBlank(vm.schedule.beanName)){
                alert("bean can not be empty");
                return true;
            }

            if(isBlank(vm.schedule.methodName)){
                alert("methodName can not be empty");
                return true;
            }

            if(isBlank(vm.schedule.cronExpression)){
                alert("cron can not be empty");
                return true;
            }
        }
	}
});