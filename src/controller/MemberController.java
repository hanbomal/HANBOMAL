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

	@RequestMapping("/join2")
	public String join(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int num = 0;
		

		if (request.getParameter("num") != null) {
			num = Integer.parseInt(request.getParameter("num"));
		}
		request.setAttribute("num", num);
		

		return "member/join2";
	}

	@RequestMapping("/joinPro")
	public String joinPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		
		

		MemberVO member = new MemberVO();
		MemberDAO dbpro = MemberDAO.getInstance();

		
		member.setNum(Integer.parseInt(request.getParameter("num")));
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
		
			HttpSession session = req.getSession();
			session.setAttribute("memberid", memberid);
		
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
		member.setMemberid(memberid);
		member.setNickname(req.getParameter("nickname"));
		member.setPasswd(req.getParameter("passwd"));
		
		member.setNum(Integer.parseInt(req.getParameter("num")));
		member.setPasswdq(req.getParameter("passwdq"));
		member.setPasswdkey(req.getParameter("passwdkey"));
		
		
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
	
		
		
		String memberid=req.getParameter("memberid");
		String passwd = req.getParameter("passwd");
		
		MemberDAO dbPro=MemberDAO.getInstance();
		int chk=dbPro.deleteMember(memberid, passwd);
		
		req.setAttribute("chk", chk);
		
		return "member/member_deletePro";
}
	
	
	@RequestMapping("/before_check")    //form 
    public String before_check(HttpServletRequest req, HttpServletResponse res)  throws Throwable {
    
       HttpSession session = req.getSession();
    
    String memberid=((String)session.getAttribute("memberid"));
    
       req.setAttribute("memberid", memberid); //
       
    
    return "member/before_check";
}
 
 @RequestMapping("/before_checkPro")    //form 
    public String before_checkPro(HttpServletRequest req, HttpServletResponse res)  throws Throwable {
    
    
    
    String memberid=req.getParameter("memberid");
    String passwd = req.getParameter("passwd");
    
    MemberDAO dbPro=MemberDAO.getInstance();
    
    int chk=dbPro.beforeCheck(memberid, passwd);
    req.setAttribute("chk",chk);
    
    return "member/before_checkPro";
}
 
 @RequestMapping("/findpasswdPro")    //form 
 public String findPasswdPro(HttpServletRequest req, HttpServletResponse res)  throws Throwable {
	
    MemberDAO dbPro=MemberDAO.getInstance();
	 

	 
	 String memberid=req.getParameter("memberid");
	 String passwdq=req.getParameter("passwdq");
	 String passwdkey=req.getParameter("passwdkey");
	 req.setAttribute("memberid", memberid);
	 req.setAttribute("passwdq", passwdq);
	 req.setAttribute("passwdkey", passwdkey);
//이건됨...
	 
	 int chk=dbPro.findPasswd(memberid, passwdq, passwdkey);
	 req.setAttribute("chk", chk);
	

	 
	 MemberVO member=dbPro.getMember(memberid);
	  
	    req.setAttribute("member", member);
	    
	    System.out.println(memberid +"....findpasswd 폼에서 입력한 멤버아이디");
	    System.out.println(chk +"정답일경우 1");
	 
   //it is right by...
  
 return "/member/go_passwd";//"member/go_passwd";//"member/go_passwd";
}
 

@RequestMapping("/go_passwd")    //form 
public String go_passwd(HttpServletRequest req, HttpServletResponse res)  throws Throwable {
	MemberDAO dbPro=MemberDAO.getInstance();
	String memberid=req.getParameter("memberid");
	req.setAttribute("memberid", memberid);
	System.out.println("memberid 가져왓다고치자"+memberid);
	 
	 MemberVO member=dbPro.getMember(memberid);
	 
	
	 String passwdq=req.getParameter("passwdq");
	 String passwdkey=req.getParameter("passwdkey");
	 
	 req.setAttribute("memberid", memberid);
	 req.setAttribute("passwdq", passwdq);
	 req.setAttribute("passwdkey", passwdkey);
		 

	System.out.println(memberid +"이부분 239번째줄 사용자가입력한 검증된 멤버아이디 ");//memberid==null ..can pick memberid
	
		
	
	
	

	

	// req.setAttribute("chk", chk);
	
	//여기서 chk를 받아야한다.
	//String passwdq=req.getParameter("passwdq");
	//String passwdkey=req.getParameter("passwdkey");
	
	req.setAttribute("member", member);
	

return "/member/go_passwd";


}

@RequestMapping("/findPasswd")    //form 
public String bfindPasswd(HttpServletRequest req, HttpServletResponse res)  throws Throwable {

return "member/findPasswd";

}
    
		
	

}
