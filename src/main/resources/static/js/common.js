$.jgrid.defaults.width = 1000;
$.jgrid.defaults.responsive = true;
$.jgrid.defaults.styleUI = 'Bootstrap';

window.T = {};

var url = function(name) {
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
};
T.p = url;

var baseURL = "/blog/";

var token = localStorage.getItem("token");
if((token == 'null' || token == null) && parent.location.toString().indexOf("login")<0 ){
    parent.location.href = baseURL + 'login.html';
}

$.ajaxSetup({
	dataType: "json",
	cache: false,
    headers: {
        "token": token
    },
    xhrFields: {
	    withCredentials: true
    },
    complete: function(xhr) {
        if(xhr.responseJSON.code == 401){
            parent.location.href = baseURL + 'login.html';
        }
    }
});

$.extend($.jgrid.defaults, {
    ajaxGridOptions : {
        headers: {
            "token": token
        }
    }
});


window.alert = function(msg, callback){
	parent.layer.alert(msg, function(index){
		parent.layer.close(index);
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

window.confirm = function(msg, callback){
	parent.layer.confirm(msg, {btn: ['确定','取消']},
	function(){
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

function getSelectedRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
    	alert("请选择一行记录");
    	return ;
    }
    
    var selectedIDs = grid.getGridParam("selarrrow");
    if(selectedIDs.length > 1){
    	alert("仅有一行");
    	return ;
    }
    
    return selectedIDs[0];
}

function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
    	alert("请选择一行记录");
    	return ;
    }
    
    return grid.getGridParam("selarrrow");
}

function isBlank(value) {
    return !value || !/\S/.test(value)
}
