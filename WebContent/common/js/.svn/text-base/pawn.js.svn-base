function AmtInWords(quantity,suffix)
{	
	var tens = new Array("","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","",
"Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen")
	var tees = new Array("","Ten","Twenty","Thirty","Fourty","Fifty","Sixty","Seventy","Eighty","Ninety")
	var million = 1000000; 
	var thousand = 1000;
	var inWords = "";
	var wtr;
	var twtr;
	var xwtr;
	if (quantity==0)
	{
		return ("Zero "+ suffix);
	}
	var tr = quantity;
	if (tr >= million) 
	{
		wtr = parseInt(tr/million);
		inWords += giveaway(wtr);
		inWords += " Million ";
		tr -= wtr * million;
	}
	if (tr >= thousand)
	{
		wtr = parseInt(tr/thousand);
		xwtr = wtr;
		if (wtr > 99) 
		{
			twtr = parseInt(wtr/100);
			inWords += giveaway(twtr) + " Hundred ";
			xwtr = wtr - (twtr*100);
		}
		inWords += giveaway(xwtr);
		inWords += " Thousand ";
		tr -= wtr * thousand;
	}
	if (tr > 0)
	{
		wtr = tr;
		if (wtr > 99) 
		{
			twtr = parseInt(wtr/100);
			inWords += giveaway(twtr) + " Hundred ";
			wtr = wtr - (twtr*100);
		}
		inWords += giveaway(wtr);
	}
	inWords += " "+ suffix;
	return (inWords);
}

function giveaway(prs)
{
	var tens = new Array("","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","",
"Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen")
	var tees = new Array("","Ten","Twenty","Thirty","Fourty","Fifty","Sixty","Seventy","Eighty","Ninety")
	if (prs == ((parseInt(prs/10))*10))
		return tees[parseInt(prs/10)];
	else if (prs < 20)
		return tens[prs];
	else
		var tprs = parseInt(prs/10);
	return (tees[tprs] + (tens[prs-(tprs*10)]));
}
function checkAll(perpage,obj){
	if(obj.pid[0].checked){
		for(i=1;i<=perpage;i++){	
			obj.pid[i].checked=true;
		}
	}else{
		for(i=1;i<=perpage;i++){	
			obj.pid[i].checked=false;
		}
	}	
}
function uncheckAll(checkstat){
	if(!checkstat){
		obj.pid[0].checked=false;
	}
}