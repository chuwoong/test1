package com.stitch.tumblr.member.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.stitch.tumblr.dbinterface.DBConnector;
import com.stitch.tumblr.member.crypt.BCrypt;
import com.stitch.tumblr.member.crypt.SHA256;

public class MemberDAO implements DBConnector{
	private Connection conn = null;
	
	//region Singleton
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
        return instance;
    }
	//endregion
	
	private MemberDAO() {
		
	}

	@Override
	public Connection getConnection() throws Exception {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource)envCtx.lookup("jdbc/stitch");
        return ds.getConnection();
	}

	@Override
	public void getClose() throws Exception {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) { 
				e.printStackTrace();
			}
		} 
	}
	
	public void memberInsert(MemberVO member) {
		PreparedStatement pstmt = null;
		SHA256 sha256 = SHA256.getInsatnce();
		
		try {
            conn = getConnection();
            String orgPass = member.getM_password();
            String shaPass = sha256.getSha256(orgPass.getBytes());
        	String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
        	
        	String sql = "insert into member(m_id, m_password, m_name, m_gender) values(?,?,?,?)";
        	pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getM_id());
            pstmt.setString(2, bcPass);
            pstmt.setString(3, member.getM_name());
            pstmt.setString(4, member.getM_gender());
            //pstmt.setDate(5, (java.sql.Date) member.getBirth());
            pstmt.executeUpdate();
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		
		
	}
	
	public int overlapId() {
		int x = 0;
		
		
		
		
		
		
		return x;
	}
	
}
