package com.stitch.tumblr.dbinterface;

import java.sql.Connection;

public interface DBConnector {
	public Connection getConnection() throws Exception;
	public void getClose() throws Exception;
}
