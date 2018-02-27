function addRowToTable()
{
  var tbl = document.getElementById('cashdisburse');
  var lastRow = tbl.rows.length;
  var iteration = lastRow;
  var row = tbl.insertRow(lastRow);
  
  var cell0 = row.insertCell(0);
  var el0 = document.createElement('input');
  el0.type = 'text';
  //el0.name = 'code' + iteration;
  el0.name = 'code';
  el0.id = 'code' + iteration;
  el0.size = 5;
  cell0.appendChild(el0);
  
  var cell1 = row.insertCell(1);
  var el1 = document.createElement('input');
  el1.type = 'text';
  //el1.name = 'account' + iteration;
  el1.name = 'account';
  el1.id = 'account' + iteration;
  el1.size = 30;
  el1.disabled = 'disabled';
  cell1.appendChild(el1);
  
  var cell2 = row.insertCell(2);
  var el2 = document.createElement('input');
  el2.type = 'text';
  //el2.name = 'particulars' + iteration;
  el2.name = 'particulars';
  el2.id = 'particulars' + iteration;
  el2.size = 40;
  cell2.appendChild(el2);
  
  var cell3 = row.insertCell(3);
  var el3 = document.createElement('input');
  el3.type = 'text';
  //el3.name = 'amount' + iteration;
  el3.name = 'amount';
  el3.id = 'amount' + iteration;
  cell3.appendChild(el3);
  
  var aj_code = new AjaxJspTag.UpdateField(
"GetAccountName", {
parameters: "code={code"+iteration+"},limit=5",
action: "code"+iteration,
parser: new ResponseXmlParser(),
target: "account"+iteration,
source: "code"+iteration,
eventType: "blur"
}); 
 
}
function removeRowFromTable()
{
  var tbl = document.getElementById('cashdisburse');
  var lastRow = tbl.rows.length;
  if (lastRow > 2) tbl.deleteRow(lastRow - 1);
}
function validateRow(frm)
{
    var tbl = document.getElementById('cashdisburse');
    var lastRow = tbl.rows.length - 1;
    var i;
    for (i=1; i<=lastRow; i++) {
      var aRow = document.getElementById('code' + i);
      if (aRow.value.length <= 0) {
        alert('Row ' + i + ' is empty');
        return false;
      }
    }
    for (i=1; i<=lastRow; i++) {
    	 var aRow = document.getElementById('account' + i);
    	 aRow.disabled=false;
    }
    return true;
}