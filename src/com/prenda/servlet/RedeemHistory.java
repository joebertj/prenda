/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.servlet;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import com.prenda.helper.DatabaseConnection;

/**
 * Servlet implementation class for Servlet: PawnHistory
 *
 */
 public class RedeemHistory extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5011776142581702707L;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public RedeemHistory() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type=new Integer(request.getParameter("type")).intValue();
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count[] = {0,0,0,0,0,0,0,0,0,0,0,0};
		try{
			pstmt = conn.prepareStatement("SELECT COUNT(redeem_date) FROM redeem WHERE year(redeem_date)=year(curdate())+? AND month(redeem_date)=?");
			for(int i=1;i<=12;i++){
				pstmt.setInt(1,type);
				pstmt.setInt(2,i);
				rs=pstmt.executeQuery();
				if(rs.first()){
					count[i-1]=rs.getInt(1);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		TimeSeries series = new TimeSeries("Total",Month.class);
		GregorianCalendar now=new GregorianCalendar();
		RegularTimePeriod t = new Month(1, now.get(GregorianCalendar.YEAR)+type);
		for(int i=0;i<12;i++){
			series.add(t, count[i]);
			t = t.next();
		}
		XYDataset dataset = new TimeSeriesCollection(series);
		JFreeChart chart = ChartFactory.createTimeSeriesChart
		                     ("Redeem History",
		                      "Month",
		                      "Number of Redeemed Items",
		                      dataset,
		                      true,
		                      true,
		                      true
		                     );
		ChartUtilities.saveChartAsJPEG(new File(getServletContext().getRealPath("/resources/img")+"/redeemhistory"+type+".jpg"), chart, 500, 300);
		response.sendRedirect("admin/redeemhistory.jsp?type="+type);
	}
}