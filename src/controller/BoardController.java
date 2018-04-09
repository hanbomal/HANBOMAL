package controller;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import dao.BoardDAO;
import model.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	// get Session ID Method
	public String getSessionId(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String memberid = (String) session.getAttribute("memberid");
		if (memberid == null) {
			memberid = "";
		}
		return memberid;
	}
	
	BoardDAO boardDB = BoardDAO.getInstance();
	
	String boardid = "1";
	String pageNum = "1";

	@ModelAttribute
	public void addAttributes(String boardid, String pageNum) {
		if (boardid != null)
			this.boardid = boardid;
		if (pageNum != null && pageNum != "")
			this.pageNum = pageNum;
	}
	@RequestMapping("/study_board")
	public String study_board(Model mv) throws Throwable {
		int pageSize = 5;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		List articleList = null;
		BoardDAO dbPro = BoardDAO.getInstance();
		count = dbPro.getArticleCount(boardid);
		if (count > 0) {
			articleList = dbPro.getArticles(startRow, endRow, boardid);
		}
		number = count - (currentPage - 1) * pageSize;
		
		int bottomLine = 3;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount)
			endPage = pageCount;
			
		mv.addAttribute("boardid",boardid);
		mv.addAttribute("pageCount",pageCount);
		mv.addAttribute("endPage",endPage);
		mv.addAttribute("bottomLine",bottomLine);
		mv.addAttribute("startPage",startPage);
		mv.addAttribute("currentPage",currentPage);
		mv.addAttribute("articleList",articleList);
		mv.addAttribute("number",number);
		mv.addAttribute("count",count);
		return "board/study_board";
	}
	
	@RequestMapping("/content")
	public String content(Model mv, int num) throws Throwable {
		BoardVO article = boardDB.getArticle(num, boardid,"content");
		mv.addAttribute("article", article);
		mv.addAttribute("num", num);
		mv.addAttribute("pageNum", pageNum);
		return "board/content";
	}
	@RequestMapping("/writeForm")
	public ModelAndView writeForm(BoardVO article,HttpServletRequest req) throws Exception {
		String memberid=getSessionId(req);
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardid", boardid);
		mv.addObject("num", article.getNum());
		mv.addObject("pageNum", pageNum);
		mv.addObject("memberid", memberid);
		mv.setViewName("board/writeForm");
		return mv;
	}

	@RequestMapping(
		      value = "/writePro"
		      , method= RequestMethod.POST
		      , consumes ={"multipart/form-data"}
		)
	public String writePro(MultipartHttpServletRequest request,BoardVO article, Model mv)throws Exception {
		MultipartFile multi = request.getFile("uploadfile");
		String filename = multi.getOriginalFilename();
		System.out.println("filename:[" + filename + "]");
		if (filename != null && !filename.equals("")) {
			String uploadPath = request.getRealPath("/") + "fileSave";
			System.out.println(uploadPath);
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			article.setFilename(filename);
			article.setFilesize((int) multi.getSize());
		} else {
			article.setFilename("");
			article.setFilesize(0);
		}
		System.out.println(article);
		boardDB.insertArticle(article);
		mv.addAttribute("pageNum", pageNum);
		return "redirect:/board/study_board";
	}
}
