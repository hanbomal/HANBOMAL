package controller;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import dao.GalleryDAO;
import model.GalleryVO;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	GalleryDAO gPro=GalleryDAO.getInstance();
	String studynum = "1";
	String pageNum = "1";
	
	@ModelAttribute
	public void addAttributes(String studynum, String pageNum) {
		if (studynum != null)
			this.studynum = studynum;
		if (pageNum != null && pageNum != "")
			this.pageNum = pageNum;
	}
	
	@RequestMapping("/list")
	public String list(Model model) throws Exception {
		int pageSize = 8;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		List galleryList = null;
		count = gPro.getGalleryCount(studynum);
		if (count > 0) {
			galleryList = gPro.getImages(startRow, endRow, studynum);

			for(Object gallery:galleryList) {
				GalleryVO tmp=(GalleryVO)gallery;
				tmp.setFormatDate(sdf.format(tmp.getRegdate()));
			}
		}
		number = count - (currentPage - 1) * pageSize;

		int bottomLine = 3;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount)
			endPage = pageCount;

		model.addAttribute("studynum", studynum);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("endPage", endPage);
		model.addAttribute("bottomLine", bottomLine);
		model.addAttribute("startPage", startPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("sdf", sdf);
		model.addAttribute("galleryList", galleryList);
		model.addAttribute("number", number);
		model.addAttribute("count", count);
		
		return "gallery/study_gallery";
	}
	
	@RequestMapping(
		      value = "/imageView"
		      , method= RequestMethod.POST
		  		)
	public String imageView(HttpServletRequest request, Model model)
			throws Exception {
		
		String num=request.getParameter("num");
		
		GalleryVO gallery=gPro.getImage(num);
		
		model.addAttribute("gallery",gallery);
		
		
		return "gallery/viewPage";
	}
	
	@RequestMapping(
		      value = "/deleteImage"
		      , method= RequestMethod.POST
		  		)
	public String deleteImage(HttpServletRequest request)
			throws Exception {
		
		String num=request.getParameter("num");
		
		GalleryVO gallery=gPro.getImage(num);
		
		
		gallery.setFilename("deletedImage.png");
		gallery.setFilesize(0);
		gallery.setTitle("삭제된 사진");
		gallery.setContent("삭제된 사진입니다.");
		
		gPro.updateGallery(gallery);
		
		System.out.println("완료");
		
		return "gallery/comp";
		
	}
	
}
