package com.prenda.helper;

import java.util.ListResourceBundle;

public class ApplicationDb extends ListResourceBundle {
	public Object[][] getContents() {
	      return contents;
	   }
	   static final Object[][] contents = {
	      {"driver", "com.mysql.jdbc.Driver"},
	      {"dburl", "jdbc:mysql://localhost/prenda?useSSL=false&useUnicode=true&characterEncoding=UTF-8"},
	      {"dbuser", "prenda"},
	      {"dbpass", "prenda"},
	   };
}
