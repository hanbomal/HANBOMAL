package controller;

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
			session.setAttribute("passwd", passwd);
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
	      
	       session.invalidate(); // ��缼������ ����
	       res.sendRedirect(req.getContextPath() + "/page/main"); // ����ȭ������ �ٽ� ���ư���.
	      return null;
	   }
	
	@RequestMapping("/member_update")	 //form 
	   public String member_update(HttpServletRequest req, HttpServletResponse res)  throws Throwable {
		     
		HttpSession session = req.getSession();
		
	 String memberid=session.getAttribute("memberid").toString();
	 String passwd=session.getAttribute("passwd").toString(); //memberid�� passwd�� ������
	
		try {
	
		 MemberDAO dbPro=MemberDAO.getInstance();
		MemberVO article=dbPro.getMember(memberid);
		
		req.setAttribute("article", article);
		
		 
		
	       //req.setAttribute("article", article);
		}catch(Exception e) {}
		
	      return "member/member_update";
	   }
	
	/*@RequestMapping("/member_updatePro")	
	   public String member_updatePro(HttpServletRequest req, HttpServletResponse res)  throws Throwable {
		     
		
	
	String memberid=req.getParameter("memberid");
	
	MemberVO article=new MemberVO();
	
	article.setNum(Integer.parseInt(req.getParameter("num")));

	//unique constant is num and memberid.........
	//it's time for update from view
	
	MemberDAO dbPro=MemberDAO.getInstance();
	
	try {
		article.setNickname(req.getParameter("nickname"));
		article.setPasswd(req.getParameter("passwd"));
		article.setListid(req.getParameter("listid"));
		
		
		int chk=dbPro.
		
		req.setAttribute("chk", chk);
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	return "member/member_updatePro";

}*/
	
	@RequestMapping("/member_delete")	 //form 
	   public String member_delete(HttpServletRequest req, HttpServletResponse res)  throws Throwable {
	
		HttpSession session = req.getSession();
		
		 int num = Integer.parseInt(req.getParameter("num"));
		 String memberid=session.getAttribute("memberid").toString();
		
		 
		 req.setAttribute("num", num);
		
		
		return "member/member_delete";
}
	
	@RequestMapping("/member_deletePro")	 //form 
	   public String member_deletePro(HttpServletRequest req, HttpServletResponse res)  throws Throwable {
	
		
		return "member/member_deletePro";
}
}
