package com.stitch.tumblr.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stitch.tumblr.member.bean.MemberDAO;
import com.stitch.tumblr.member.bean.MemberVO;

public class MemberInsertPro implements CommandInterface {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		String m_id = request.getParameter("m_id");
		String m_password = request.getParameter("m_password");
		String m_name = request.getParameter("m_name");
		String m_gender = request.getParameter("m_gender");
/*		
		String yy = request.getParameter("year");
	    String mm  = request.getParameter("month");
	    String dd  = request.getParameter("day");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String yymmdd = yy+mm+dd; //문자열
		Date birth = df.parse(yymmdd); //yyyy-MM-dd 이형식으로 바꺼라. 
		*/
		MemberVO memberVO = new MemberVO();
		
		memberVO.setM_id(m_id);
		memberVO.setM_password(m_password);
		memberVO.setM_name(m_name);
		memberVO.setM_gender(m_gender);
		//memberVO.setBirth(birth);
		
	    MemberDAO memberDAO = MemberDAO.getInstance();
        memberDAO.memberInsert(memberVO);

		return "/jsp/memberInsertPro.jsp";
		
	}

}
