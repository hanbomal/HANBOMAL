package controller;

import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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
import model.BoardVO;
import model.RelationVO;
import model.StudyVO;

@Controller
@RequestMapping("/page")
public class PageController {
	RelationDAO relationDB = RelationDAO.getInstance();
	StudyDAO studyDB = StudyDAO.getInstance();
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
			memberid = "";
		}
		return memberid;
	}

	@RequestMapping("/main")
	public String main(Model mv, String studyName,HttpServletRequest req, String chk) throws Throwable {
		autoComplete(mv);
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
			dbPro.requestJoin(getSessionId(req), correctName, "member_nick", "회원", leader);
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
			String studyName, String study_intro) throws Throwable {
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
		System.out.println(study);
		studyDB.makingStudy(study);
		return "redirect:/page/main";
	}

	@RequestMapping("/about")
	public String about(HttpServletRequest req, HttpServletResponse res) throws Throwable {

		return "page/about";
	}
	
	@RequestMapping("/RequestPage")
	public String RequestPage(Model mv,HttpServletRequest req, String leader, String studyName) throws Throwable {
		autoComplete(mv);
		String memberid = getSessionId(req);
		List<RelationVO> reqList = relationDB.requestList(memberid);
		mv.addAttribute("reqList", reqList);
		return "page/RequestPage";
	}
	
	@RequestMapping("/ResponsePage")
	public String ResponsePage(Model mv,HttpServletRequest req) throws Throwable {
		autoComplete(mv);
	
		String memberid = getSessionId(req);
		List<RelationVO> resList = relationDB.responseList(memberid);
		mv.addAttribute("resList", resList);
		return "page/ResponsePage";
	}
	@RequestMapping("/sendRedirect")
	public String sendRedirect(Model mv,HttpServletRequest req
			,String memberId, String answer, String studyName) throws Throwable {
		autoComplete(mv);
		String leader = getSessionId(req);
		if(answer.equals("yes")) {
			relationDB.answerYes(memberId, leader,studyName);
		}else if(answer.equals("no")) {
			relationDB.answerNo(memberId, leader,studyName);
		}
		List<RelationVO> resList = relationDB.responseList(leader);
		mv.addAttribute("resList", resList);
		return "page/ResponsePage";
	}
	@RequestMapping("/study_album")
	public String study_album(HttpServletRequest req, HttpServletResponse res) throws Throwable {

		return "page/study_album";
	}

	@RequestMapping("/study_making")
	public String study_making(HttpServletRequest req, HttpServletResponse res) throws Throwable {

		return "page/study_making";
	}
	
	@RequestMapping("/test")
	public String test(HttpServletRequest req, HttpServletResponse res) throws Throwable {

		return "page/study_test";
	}
	
	@RequestMapping("/test2")
	public String test2(HttpServletRequest req, HttpServletResponse res) throws Throwable {

		return "page/study_test2";
	}
	
	@RequestMapping("/study_info")
	public String study_info(HttpServletRequest req, HttpServletResponse res) throws Throwable {

		return "study/study_info";
	}
	
	@RequestMapping("/study_admin")
	public String study_admin(HttpServletRequest req, HttpServletResponse res) throws Throwable {

		return "study/study_admin";
	}
	
	@RequestMapping("/study_gallery")
	public String study_gallery(HttpServletRequest req, HttpServletResponse res) throws Throwable {

		return "gallery/study_gallery";
	}
	

}
