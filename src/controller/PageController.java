package controller;

import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import dao.BoardDAO;
import dao.RelationDAO;
import dao.StudyDAO;
import model.BoardTypeVO;
import model.GalleryVO;
import model.PositionVO;
import model.RelationVO;
import model.StudyVO;

@Controller
@RequestMapping("/page")
public class PageController {
	RelationDAO relationDB = RelationDAO.getInstance();
	StudyDAO studyDB = StudyDAO.getInstance();
	BoardDAO boardDB = BoardDAO.getInstance();
	String pageNum = "1";
	String group="1";
	String boardid = "1";

	@ModelAttribute
	public void addAttributes(String boardid,String group,String pageNum) {
		if (boardid != null)
			this.boardid = boardid;
		if (pageNum != null && pageNum != "")
			this.pageNum = pageNum;
		if (group != null)
			this.group = group;
	}
	
	public void HeaderInfo(HttpServletRequest req,Model mv) {
		List<StudyVO> groupList=studyDB.getGroupList(getSessionId(req));
		int reqcount = relationDB.requestCount(getSessionId(req));
		int rescount = relationDB.responseCount(getSessionId(req));
		mv.addAttribute("groupList",groupList);
		mv.addAttribute("reqcount",reqcount);
		mv.addAttribute("rescount",rescount);
	}
	
	
	// autoComplete Method
	public void autoComplete(Model mv) throws Throwable {
		// auto_complete
		List<StudyVO> allList = studyDB.getAllStudy();
		String nameList = "";
		for (StudyVO study : allList) {
			nameList += "\"" + study.getStudyName() + "\",";
		}
		mv.addAttribute("nameList", nameList);
	}

	// get Session ID Method
	public String getSessionId(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String memberid = (String) session.getAttribute("memberid");
		if (memberid == null) {
			memberid = "defaultID";
		}
		return memberid;
	}
	public String getSessionNick(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String nickname=(String) session.getAttribute("nickname");
		if (nickname == null) {
			nickname = "defaultNickname";
		}
		return nickname;
	}

	@RequestMapping("/main")
	public String main(Model mv, String studyName,HttpServletRequest req, String chk) throws Throwable {
		autoComplete(mv);
		HeaderInfo(req, mv);
		if (studyName == null) {
			studyName = "defaultName";
		}
		if(chk==null) {
			chk="";
		}
		String memberid = getSessionId(req);
		List<StudyVO> group = studyDB.resultList(studyName, memberid, chk);
		mv.addAttribute("group", group);
		mv.addAttribute("studyName", studyName);
		mv.addAttribute("chk",chk);
		return "page/main";
	}

	@RequestMapping("/requestJoin")
	public String requestJoin(String reqNum,String correctName,
			HttpServletRequest req,String studyName,String leader,String chk) throws Throwable {
		RelationDAO dbPro = RelationDAO.getInstance();
		if (reqNum == null) {
			reqNum = "";
		}
		if (reqNum.equals("1")) {
			dbPro.requestJoin(getSessionId(req),group,correctName, getSessionNick(req), leader);
			studyName = URLEncoder.encode(studyName, "UTF-8");
			return "redirect:/page/main?&studyName="+ studyName+"&chk="+chk;
		}
		return null;
	}

	@RequestMapping("/cancelJoin")
	public String cancelJoin(HttpServletRequest req, HttpServletResponse res,
			String studyName, String correctName, String chk) throws Throwable {
		String delNum = req.getParameter("delNum");
		if (delNum == null) {
			delNum = "";
		}
		if (delNum.equals("1")) {
			relationDB.cancelJoin(getSessionId(req), correctName);
			studyName = URLEncoder.encode(studyName, "UTF-8");
			return "redirect:/page/main?studyName="+ studyName+"&chk="+chk ;
		}
		return null;
	}
	
	
	@RequestMapping("/makingPro")
	public String makingPro(MultipartHttpServletRequest req,  
			String studyName, String study_intro, Model mv) throws Throwable {
		autoComplete(mv);
		StudyVO  study = new StudyVO();
		MultipartFile multi1 = req.getFile("study_pro");
		MultipartFile multi2 = req.getFile("study_back");
		String pro_name = multi1.getOriginalFilename();
		String back_name = multi2.getOriginalFilename();
		if (pro_name != null && !pro_name.equals("")) {
			String uploadPath = req.getRealPath("/") + "fileSave";
			FileCopyUtils.copy(multi1.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi1.getOriginalFilename()));
			study.setStudy_pro(pro_name);
			study.setProSize((int) multi1.getSize());
		} else {
			study.setStudy_pro("");
			study.setProSize(0);
		}
		if (back_name != null && !back_name.equals("")) {
			String uploadPath = req.getRealPath("/") + "fileSave";
			FileCopyUtils.copy(multi2.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi2.getOriginalFilename()));
			study.setStudy_back(back_name);
			study.setBackSize((int) multi2.getSize());
		} else {
			study.setStudy_back("");
			study.setBackSize(0);
		}
		study.setStudyName(studyName);
		study.setStudy_intro(study_intro);
		study.setPeopleCount(1);
		study.setLeader(getSessionId(req));
		studyDB.makingStudy(study,getSessionNick(req));
		return "redirect:/page/main";
	}

	@RequestMapping("/about")
	public String about(HttpServletRequest req, HttpServletResponse res, Model mv) throws Throwable {
		autoComplete(mv);
		HeaderInfo(req, mv);
		return "page/about";
	}
	
	@RequestMapping("/RequestPage")
	public String RequestPage(Model mv,HttpServletRequest req) throws Throwable {
		autoComplete(mv);
		HeaderInfo(req, mv);
		
		int pageSize = 8;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		List<RelationVO> reqList =null;
		count = relationDB.requestCount(getSessionId(req));
		if (count > 0) {
			reqList=relationDB.requestList(startRow,endRow,getSessionId(req));
		}
		number = count - (currentPage - 1) * pageSize;
		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount)
			endPage = pageCount;

		mv.addAttribute("studynum", group);
		mv.addAttribute("pageCount", pageCount);
		mv.addAttribute("endPage", endPage);
		mv.addAttribute("bottomLine", bottomLine);
		mv.addAttribute("startPage", startPage);
		mv.addAttribute("currentPage", currentPage);
		mv.addAttribute("reqList", reqList);
		mv.addAttribute("number", number);
		mv.addAttribute("count", count);
		mv.addAttribute("memberid", getSessionId(req));
		
		return "page/RequestPage";
	}
	
	@RequestMapping("/ResponsePage")
	public String ResponsePage(Model mv,HttpServletRequest req) throws Throwable {
		autoComplete(mv);
		HeaderInfo(req, mv);
		int pageSize = 8;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		List<RelationVO> resList =null;
		count = relationDB.responseCount(getSessionId(req));
		if (count > 0) {
			resList=relationDB.responseList(startRow,endRow,getSessionId(req));
		}
		number = count - (currentPage - 1) * pageSize;
		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount)
			endPage = pageCount;

		mv.addAttribute("studynum", group);
		mv.addAttribute("pageCount", pageCount);
		mv.addAttribute("endPage", endPage);
		mv.addAttribute("bottomLine", bottomLine);
		mv.addAttribute("startPage", startPage);
		mv.addAttribute("currentPage", currentPage);
		mv.addAttribute("resList", resList);
		mv.addAttribute("number", number);
		mv.addAttribute("count", count);
		mv.addAttribute("memberid", getSessionId(req));
		
		
		return "page/ResponsePage";
	}
	@RequestMapping("/sendRedirect")
	public String sendRedirect(Model mv,HttpServletRequest req
			,String memberId, String yes,String no, String studyName) throws Throwable {
		autoComplete(mv);
		String leader = getSessionId(req);
		if(yes==null) yes="";
		if(no==null) no="";
		if(yes.equals("yes")) {
			relationDB.answerYes(memberId, leader,studyName);
		}else if(no.equals("no")) {
			relationDB.answerNo(memberId, leader,studyName);
		}
		HeaderInfo(req, mv);
		return "redirect:/page/ResponsePage";
	}
	@RequestMapping("/study_album")
	public String study_album(HttpServletRequest req, HttpServletResponse res,Model mv) throws Throwable {
		HeaderInfo(req, mv);
		return "page/study_album";
	}

	@RequestMapping("/study_making")
	public String study_making(HttpServletRequest req, HttpServletResponse res,Model mv) throws Throwable {
		HeaderInfo(req, mv);
		return "page/study_making";
	}
	
	@RequestMapping("/test")
	public String test(HttpServletRequest req,Model mv) throws Throwable {
		autoComplete(mv);
		HeaderInfo(req, mv);
		String memberid=getSessionId(req);
		List<BoardTypeVO> typeList=boardDB.getTypeList(group);
		mv.addAttribute("memberid",memberid);
		StudyVO study= studyDB.getOneStudy(group);
		mv.addAttribute("study", study);
		mv.addAttribute("typeList", typeList);
		mv.addAttribute("group",group);

		return "page/study_test";
	}
	
	@RequestMapping("/test2")
	public String test2(HttpServletRequest req, HttpServletResponse res,Model mv) throws Throwable {
		autoComplete(mv);
		HeaderInfo(req, mv);
		return "page/study_test2";
	}
	
	@RequestMapping("/study_info")
	public String study_info(HttpServletRequest req, HttpServletResponse res,Model mv) throws Throwable {
		autoComplete(mv);
		HeaderInfo(req, mv);
		String memberid = getSessionId(req);
		
		String studynum=req.getParameter("studynum");
		
		StudyVO study=studyDB.getOneStudy(studynum);
		
		List members=relationDB.getJoinMemberList(study.getStudyName());
		
		
		mv.addAttribute("members",members);
		mv.addAttribute("study",study);
		mv.addAttribute("memberCount",members.size());
		mv.addAttribute("memberid",memberid);
	
		
		
		return "study/study_info";
	}
	
	@RequestMapping("/addPosition")
	public String addPosition(PositionVO position,Model mv) throws Throwable {
		group=position.getStudynum()+"";
		position.setStudynum(Integer.parseInt(group));
		studyDB.addPosition(position);
		return "redirect:/page/study_admin";
	}
	@RequestMapping("/addBoardType")
	public String addBoardType(BoardTypeVO board,Model mv,HttpServletRequest req) throws Throwable {
		group=board.getStudynum()+"";
		boardid=boardDB.getNextBoardid(group)+"";
		board.setBoardid(boardid);
		board.setStudynum(Integer.parseInt(group));
		boardDB.addBoard(board);
		//typeList 
		HttpSession session = req.getSession();
		List<BoardTypeVO> typeList=(List)session.getAttribute("typeList");
		System.out.println("group:"+group);
		typeList=boardDB.getTypeList(group);
		session.setAttribute("typeList", typeList);
		mv.addAttribute("boardid",boardid);
		mv.addAttribute("group",group);
		return "redirect:/page/study_admin";
	}
	@RequestMapping("/updateBoardType")
	public String updateBoardType(BoardTypeVO board) throws Throwable {
		boardDB.updateBoardType(board);
		return "redirect:/page/study_admin";
	}
	@RequestMapping("/deleteBoardType")
	public String deleteBoardType() throws Throwable {
		boardDB.deleteBoard(boardid,group);
		return "redirect:/page/study_admin";
	}
	
	@RequestMapping("/updatePosition")
	public String updatePosition(String groupposition) throws Throwable {
		boardDB.updatePosition(group,groupposition);
		return "redirect:/page/study_admin";
	}
	@RequestMapping("/deletePosition")
	public String deletePosition() throws Throwable {
		boardDB.deletePosition(group);
		return "redirect:/page/study_admin";
	}
	
	
	@RequestMapping("/study_admin")
	public String study_admin(HttpServletRequest req, HttpServletResponse res,Model mv) throws Throwable {
		autoComplete(mv);
		HeaderInfo(req, mv);
		String memberid = getSessionId(req);
		List<BoardTypeVO> typeList=boardDB.getTypeList(group);
		
		StudyVO study=studyDB.getOneStudy(group);
		
		List members=relationDB.getJoinMemberList(study.getStudyName());
		List<PositionVO> position = studyDB.getAllPosition(group);
		mv.addAttribute("positionList",position);
		mv.addAttribute("members",members);
		mv.addAttribute("typeList",typeList);
		mv.addAttribute("study",study);
		mv.addAttribute("memberCount",members.size());
		mv.addAttribute("memberid",memberid);
		mv.addAttribute("group",group);
	
		return "study/study_admin";
	}
	
	@RequestMapping("/study_gallery")
	public String study_gallery(HttpServletRequest req, HttpServletResponse res, Model mv) throws Throwable {
		autoComplete(mv);
		HeaderInfo(req, mv);
		return "gallery/study_gallery";
	}
	
	
	@RequestMapping("/showMyInfo")
	public String showMyInfo(HttpServletRequest req, HttpServletResponse res,Model mv) throws Throwable {
		autoComplete(mv);
		HeaderInfo(req, mv);
		String memberid=req.getParameter("name");
		String group=req.getParameter("group");
		StudyVO study=studyDB.getOneStudy(group);
		RelationVO memberInfo=relationDB.getMemberInfo(study.getStudyName(), memberid);
		mv.addAttribute("memberInfo",memberInfo);
		return "study/viewMyInfo";
	}
	
	@RequestMapping("/boardInfo")
	public String boardInfo(HttpServletRequest req, HttpServletResponse res,Model mv) throws Throwable {
		autoComplete(mv);
		HeaderInfo(req, mv);
		String boardid=req.getParameter("boardid");
		String studynum=req.getParameter("studynum");
		String boardname=req.getParameter("boardname");
		
		mv.addAttribute("boardid",boardid);
		mv.addAttribute("studynum",studynum);
		mv.addAttribute("boardname",boardname);
		
		return "study/viewBoardInfo";
	}
	
	@RequestMapping("/PositionInfo")
	public String PositionInfo(HttpServletRequest req, HttpServletResponse res,Model mv) throws Throwable {
		autoComplete(mv);
		HeaderInfo(req, mv);
		String studynum=req.getParameter("studynum");
		String groupposition=req.getParameter("groupposition");
		mv.addAttribute("studynum",studynum);
		mv.addAttribute("groupposition",groupposition);
		return "study/viewPositionInfo";
	}
	

}
