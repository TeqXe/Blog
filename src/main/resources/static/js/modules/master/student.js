$(function () {
    var myChart = echarts.init(document.getElementById('chart'));

    // 指定图表的配置项和数据
    option = {
        tooltip : {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        },
        legend: {
            data:['発電量','消費量','蓄電残量']
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        grid: {
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                axisLabel:{
                    interval:0,
                    rotate:45,
                },
//    	            data : ['周一','周二','周三','周四','周五','周六','周日']
                data : ['5.00','6.00','7.00','8.00','9.00','10.00','11.00','12.00','13.00','14.00','15.00','16.00','17.00','18.00','19.00','20.00','21.00','22.00','23.00','0.00','1.00','2.00','3.00','4.00',]
            }
        ],
        yAxis : [
            {
                type: 'value',
                min: 0,
                max: 4,
                interval: 1,
                axisLabel: {
                    formatter: '{value} KWH'
                }
            }
        ]
       /* series : [
            {
                name:'蓄電残量',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:[0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0]
            },
            {
                name:'消費量',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:[1 , 1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
            },
            {
                name:'発電量',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:[2.1, 2.0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2]
            }
        ]*/
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    loadData();
    $("#reloadEcharts").on("click",function () {
		loadData();
    })
    function loadData() {
    	myChart.showLoading({
			text:'データの読み込み'
		});
		$.ajax({
            type: "POST",
			async:true,
            url: baseURL + 'master/student/getEchartsInfo',
            contentType: "application/json",
            data: {},
            success: function(result){
                if(result.code === 0){
                    myChart.setOption({
                        series : [
                            {
                                name:'蓄電残量',
                                type:'line',
                                stack: '总量',
                                areaStyle: {normal: {}},
                                data:result.resultArr1
                            },
                            {
                                name:'消費量',
                                type:'line',
                                stack: '总量',
                                areaStyle: {normal: {}},
                                data:result.resultArr2
                            },
                            {
                                name:'発電量',
                                type:'line',
                                stack: '总量',
                                areaStyle: {normal: {}},
                                data:result.resultArr3
                            }
                        ]
					})
                    myChart.hideLoading();
                }
            }
		})

    }

    $("#jqGrid").jqGrid({
        url: baseURL + 'master/student/list',
        datatype: "json",
        colModel: [			
			{ label: '学号', name: 'sid', index: 'sid', width: 50, key: true },
			{ label: '姓名', name: 'sname', index: 'sname', width: 80 },
			{ label: '性别', name: 'sex', index: 'sex', width: 80, formatter: function(value, options, row){
                    return value === 'M' ?
                        '<span>男</span>' :  '<span>女</span>';
                }},
			{ label: '出生日期', name: 'birth', index: 'birth', width: 80, formatter:"date", formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d'}},
			{ label: '年级', name: 'gid', index: 'gid', width: 80, formatter: function(value, options, row){
                    return value === 10007 ?
                        '<span>七年级</span>' : (value === 10008 ?
                            '<span>八年级</span>' : (value ===10009 ? '<span>九年级</span>':(value ===10010 ? '<span>十年级</span>':(value ===10011 ?
                                '<span>十一年级</span>':(value ===10012 ? '<span>十二年级</span>':'<span style="color: red">火星来的？</span>')))));
                } },
            { label:'操作',name:'', width: 80, formatter:
                    function (value, row, index) {
                        return '<a class="btn btn-danger" onclick="singleDel('+row.rowId+')">删除</a> <a class="btn btn-info" onclick="singleUp('+row.rowId+')">更新</a>';
                    }}
        ],
		viewrecords: true,
        height: 585,
        rowNum: 10,
		rowList : [10,20,30,50],
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

    $(".form_datetime:first").datetimepicker({
        language: "zh-CN",
        format: 'yyyy-mm-dd',
        minView: "month",
        autoclose: true,
		clearBtn:true,
		todayBtn:true,
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		student: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.student = {};
            $.get(baseURL + "master/grade/list/", function(r){
				var gradeList = r.page.list;
                //console.log(gradeList);
                $("#gradeSelector").empty();
				$.each(gradeList,function (index) {
					if (index ==0){
                        $("#gradeSelector").append("<option value="+''+">"+'请选择年级'+"</option>");
					}
                    $("#gradeSelector").append("<option value="+$(this)[0].gid+">"+$(this)[0].gname+"</option>");
                })

            });

		},
		update: function (event) {
			var sid = getSelectedRow();
			if(sid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "更新";
            vm.getInfo(sid);
            $.get(baseURL + "master/grade/list/", function(r){
                var gradeList = r.page.list;
                //console.log(gradeList);
                $("#gradeSelector").empty();
                $.each(gradeList,function (index) {
                    if (index ==0){
                        $("#gradeSelector").append("<option value="+''+">"+'请选择年级'+"</option>");
                    }
                    $("#gradeSelector").append("<option value="+$(this)[0].gid+">"+$(this)[0].gname+"</option>");
                })

            });
		},
		saveOrUpdate: function (event) {
		    if(vm.validator()){
		        return;
            }
		    //console.log( vm.student.birth); //提交前需要拼接日期 否则后台parse xxxx-xx-xx 格式的日期会发生错误
			//console.log($(".form_datetime").val()); vm.student.birth 为undefined 只有重新取值
			if(vm.student.birth == undefined){
                vm.student.birth = $(".form_datetime").val();
			}
            vm.student.birth=vm.student.birth+" 00:00:00";
            //console.log( vm.student.birth);
			var url = vm.student.sid == null ? "master/student/save" : "master/student/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.student),
			    success: function(r){
			    	if(r.code === 0){
						alert(vm.student.sid == null ? '新增成功':'更新成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var sids = getSelectedRows();
			if(sids == null){
				return ;
			}
			
			confirm('你确定要删除吗 ？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "master/student/delete",
                    contentType: "application/json",
				    data: JSON.stringify(sids),
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
		getInfo: function(sid){
			$.get(baseURL + "master/student/info/"+sid, function(r){
				//格式化  xxxx-xx-xx 00:00:00 为 xxxx-xx-xx
				r.student.birth = r.student.birth.substring(0,r.student.birth.lastIndexOf(" ")+1);
                vm.student = r.student;
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
            if (isBlank(vm.student.sname)){
                alert("学生姓名不可为空！");
                return true;
            }
            if (isBlank(vm.student.sex)){
                alert("学生性别不可为空！");
                return true;
            }
            if (isBlank(vm.student.birth) && $(".form_datetime").val() == ""){
                alert("学生出生日期不可为空！");
                return true;
            }
            if (isBlank(vm.student.gid)){
                alert("学生年级不可为空！");
                return true;
            }
        }
	}
});

function singleUp(rowId) {
    vm.showList = false;
    vm.title = "更新";
    vm.getInfo(rowId);
    $.get(baseURL + "master/grade/list/", function(r){
        var gradeList = r.page.list;
        //console.log(gradeList);
        $("#gradeSelector").empty();
        $.each(gradeList,function (index) {
            if (index ==0){
                $("#gradeSelector").append("<option value="+''+">"+'请选择年级'+"</option>");
            }
            $("#gradeSelector").append("<option value="+$(this)[0].gid+">"+$(this)[0].gname+"</option>");
        })

    });
}

function singleDel(rowId) {
    var para = new Array();
    para[0] = rowId;
    confirm('你确定要删除吗 ？', function(){
        $.ajax({
            type: "POST",
            url: baseURL + "master/student/delete",
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