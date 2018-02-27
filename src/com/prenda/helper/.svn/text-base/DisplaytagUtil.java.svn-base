package com.prenda.helper;

import javax.servlet.http.HttpServletRequest;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

public class DisplaytagUtil {
	private int page;
	private int order;
	private String sort;
	private int export;
	private String tableId;
	private HttpServletRequest request;
	
	public DisplaytagUtil(){
		
	}

	public int getPage() {
		String encoded = new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String value = request.getParameter(encoded);
		if(value==null || value.equals("")){
			page = 1;
		}else{
			page = Integer.parseInt(value);
		}
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public int getOrder() {
		String encoded = new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_ORDER);
		String value = request.getParameter(encoded);
		if(value==null || value.equals("")){
			order = 1;
		}else{
			order = Integer.parseInt(value);
		}
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getExport() {
		String encoded = new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_EXPORTTYPE);
		String value = request.getParameter(encoded);
		if(value==null || value.equals("")){
			export = 0;
		}else{
			export = Integer.parseInt(value);
		}
		return export;
	}

	public void setExport(int export) {
		this.export = export;
	}

	public String getSort() {
		String encoded = new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_SORT);
		String value = request.getParameter(encoded);
		if(value==null || value.equals("")){
			sort = "pawn.pt";
		}else{
			switch(Integer.parseInt(value)){
			case 2: sort="pawn.pt";
			break;
			case 6: sort="pawn.loan";
			break;
			}
		}
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
