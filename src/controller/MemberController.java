package controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.MemberDAO;
import model.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {

	@RequestMapping("/join")
	public String join(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int num = 0;
		String listid = request.getParameter("listid");

		if (listid == null)
			listid = "1";

		if (request.getParameter("num") != null) {
			num = Integer.parseInt(request.getParameter("num"));
		}
		request.setAttribute("num", num);
		request.setAttribute("listid", listid);

		return "member/join";
	}

	@RequestMapping("/joinPro")
	public String joinPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		String listid = request.getParameter("listid");
		if (listid == null)
			listid = "1";

		MemberVO member = new MemberVO();
		MemberDAO dbpro = MemberDAO.getInstance();

		member.setListid(listid);
		member.setMemberid(request.getParameter("memberid"));
		member.setNickname(request.getParameter("nickname"));
		member.setPasswd(request.getParameter("passwd"));
		member.setPasswdq(request.getParameter("passwdq"));
		member.setPasswdkey(request.getParameter("passwdkey"));
		dbpro.insertMember(member);

		response.sendRedirect(request.getContextPath() + "/page/main");

		return null;
	}

	@RequestMapping("/loginPro")
	public String loginPro(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String memberid = req.getParameter("memberid");
		String passwd = req.getParameter("passwd");
		MemberDAO dbPro = MemberDAO.getInstance();
		int pwcheck = dbPro.login(memberid, passwd);
		if (pwcheck == 1) {
			// MemberVO mVO= dbPro.getMember(memberid);
			HttpSession session = req.getSession();
			session.setAttribute("memberid", memberid);
			// session.setAttribute("nickname",mVO.getNickname());
			//session.setAttribute("passwd", passwd);
			res.sendRedirect(req.getContextPath() + "/page/main");
		
		} else {
			req.setAttribute("pwcheck", pwcheck);
			return "member/loginPro";
		}
		return null;
	}
	
	@RequestMapping("/logoutPro")	
	   public String LogoutPro(HttpServletRequest req, HttpServletResponse res)  throws Throwable {
		      
	       HttpSession  session = req.getSession();
	      
	       session.invalidate(); //
	       res.sendRedirect(req.getContextPath() + "/page/main"); // ����ȭ������ �ٽ� ���ư���.
	      return null;
	   }
	
	
	
	@RequestMapping("/member_update")	 //form 
	   public String member_update(HttpServletRequest req, HttpServletResponse res)  throws Throwable {
		HttpSession session = req.getSession();
		
		String listid=req.getParameter("listid");
		

		try {
	
		 MemberDAO dbPro=MemberDAO.getInstance();
		MemberVO member=dbPro.getMember((String)session.getAttribute("memberid"));
		
		req.setAttribute("member", member);
		
		}catch(Exception e) {}
		
	      return "member/member_update";
	   }
	
	@RequestMapping("/member_updatePro")	
	   public String member_updatePro(HttpServletRequest req, HttpServletResponse res)  throws Throwable {
		     
		MemberVO member=new MemberVO();
		MemberDAO dbpro=MemberDAO.getInstance();
	
	String memberid=req.getParameter("memberid");
	
	String num= req.getParameter("num");
	
	try {
		member.setNickname(req.getParameter("nickname"));
		member.setPasswd(req.getParameter("passwd"));
		member.setListid(req.getParameter("listid"));
		member.setNum(Integer.parseInt(req.getParameter("num")));
		
		int chk=dbpro.updateMember(member);
		
		req.setAttribute("chk", chk);
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	return "member/member_updatePro";

}
	
	@RequestMapping("/member_delete")	 //form 
	   public String member_delete(HttpServletRequest req, HttpServletResponse res)  throws Throwable {
		HttpSession session = req.getSession();
		
		String memberid=((String)session.getAttribute("memberid"));
		
		   req.setAttribute("memberid", memberid); //
		
		
		return "member/member_delete";
}
	
	@RequestMapping("/member_deletePro")	 //form 
	   public String member_deletePro(HttpServletRequest req, HttpServletResponse res)  throws Throwable {
	
		String listid=req.getParameter("listid");
		
		String memberid=req.getParameter("memberid");
		String passwd = req.getParameter("passwd");
		
		MemberDAO dbPro=MemberDAO.getInstance();
		int chk=dbPro.deleteMember(memberid, passwd);
		
		req.setAttribute("chk", chk);
		
		return "member/member_deletePro";
}
	
	
	@RequestMapping("/before_check")	 //form 
	   public String before_check(HttpServletRequest req, HttpServletResponse res)  throws Throwable {
		
         HttpSession session = req.getSession();
		
		String memberid=((String)session.getAttribute("memberid"));
		
		   req.setAttribute("memberid", memberid); //
		
		return "member/before_check";
}
	
	@RequestMapping("/before_checkPro")	 //form 
	   public String before_checkPro(HttpServletRequest req, HttpServletResponse res)  throws Throwable {
		
       String listid=req.getParameter("listid");
		
		String memberid=req.getParameter("memberid");
		String passwd = req.getParameter("passwd");
		
		MemberDAO dbPro=MemberDAO.getInstance();
		
		int chk=dbPro.beforeCheck(memberid, passwd);
		req.setAttribute("chk",chk);
		
		return "member/before_checkPro";
}
		
	

}
