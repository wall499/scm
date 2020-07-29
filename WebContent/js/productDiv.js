
var rowlength; //
var spxxTable;
var rowIndex;
function init() {
	var text=document.getElementById("productCode");
	if(text.value==""){
		text.readOnly=false;
	}
	rowlength = document.getElementById("headTable").rows[0].cells.length;
	spxxTable = document.getElementById("headTable");
}

function choiceAnonymous() {
	var len = spxxTable.rows.length;
	var returnValue;
	var i;
	for (i = 1; i < len - 1; i++) {
		//U+221A   SQUARE ROOT
		if (spxxTable.rows[i].cells[0].innerHTML == "\u221a") {
			returnValue = choice(i);
			setValue();
			hiddenDiv();
			return;
		}
	}
	alert("\u8bf7\u5148\u9009\u62e9\u5546\u54c1");
	function setValue() {
		var detailTable = document.getElementById("detailTable");
		var length = detailTable.rows.length;
		var spbm = document.getElementsByName("spbm");
		var spmc = document.getElementsByName("spmc");
		var sptj = document.getElementsByName("sptj");
		var spzl = document.getElementsByName("spzl");
		spbm[rowIndex].value = returnValue[0];
		spmc[rowIndex].value = returnValue[1];
		sptj[rowIndex].value = returnValue[2];
		spzl[rowIndex].value = returnValue[3];
	}
}
function selectItem(tr) {
	clearTable();
	tr.cells[0].innerHTML = "\u221a";
	var tds = tr.cells;
	for (var j = 0; j < tds.length; j += 1) {
		tds[j].style.backgroundColor = "#C1CDD8";
	}
}
function choice(index) {
	var row = document.getElementById("spxxTable").rows[index];
	var result = new Array(rowlength);
	var i;
	for (i = 1; i < rowlength; i++) {
		result[i - 1] = row.cells[i].innerHTML;
	}
	return result;
}
function choiceSpxx(rowIndex_) {
	showDiv();
	rowIndex = rowIndex_;
}
	//
function delItem(index) {
	var detailTable = document.getElementById("detailTable");
	detailTable.deleteRow(index);
	var rowNum = detailTable.rows.length;
	var rowlength = detailTable.rows[0].cells.length;
	for (i = index; i < rowNum; i++) {
		detailTable.rows[i].cells[0].innerHTML = i;
		detailTable.rows[i].cells[rowlength - 1].innerHTML = "<image src=\"../images/delete.gif\" class=\"LL\" onclick=\"delItem(" + i + ")\"/>";
	}
}
function hiddenDiv() {
	document.getElementById("productDiv").style.visibility = "hidden";
	clearTable();
}
function showDiv(){
	document.getElementById("productDiv").style.visibility = "visible";
	
}
 

function clearTable() {
	var trs = spxxTable.rows;
	for (var i = 1; i < trs.length - 1; i += 1) {
		trs[i].cells[0].innerHTML = "";
		var tds = trs[i].cells;
		for (var j = 0; j < tds.length; j += 1) {
			tds[j].style.backgroundColor = "#fff7e5";
		}
	}
	
}

