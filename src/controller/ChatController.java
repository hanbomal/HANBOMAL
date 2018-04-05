package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import dao.GalleryDAO;
import model.Chatdata;
import model.GalleryVO;

@Controller
@RequestMapping("/chatcontroller")
public class ChatController {
GalleryDAO gPro=GalleryDAO.getInstance();

	@RequestMapping("/intro")
	public String intro(HttpServletRequest req, HttpServletResponse res) throws Throwable {
		 String cid="1";
		 String logPath = "C:\\save\\"+cid+".txt"; 
		
	     Path path = Paths.get(logPath);
	    
	     Charset cs = StandardCharsets.UTF_8;
	  
	     List<String> list = new ArrayList<String>();
	     List<Chatdata> chatd=new ArrayList<>();
	     
	     try{
	         list = Files.readAllLines(path,cs);
	     }catch(IOException e){
	         e.printStackTrace();
	     }
	     for(String readLine : list){
	    	 readLine.trim();
	        /* System.out.println(readLine);*/
	         
	         String[] tmp=readLine.substring(readLine.indexOf("[")+1, readLine.lastIndexOf("]")).split("\\]"+" "+"\\[");
	        List tmplist=new ArrayList();
	        for(int i=0;i<tmp.length;i++) {
	        	tmplist.add(tmp[i]);
	        }
	        if(tmp.length==2) {
	        	tmplist.add("");
	        }
	         
	         Chatdata cd=new Chatdata();
	         cd.setName((String)tmplist.get(0));
	         cd.setDate((String)tmplist.get(1));
	         cd.setContent((String)tmplist.get(2));
	      
	         chatd.add(cd);
	         
	     }

	    
			 
			  req.setAttribute("chatdata", chatd);
			  
		 
			  
		return "chat/websocketGroup";

	}
	

	@RequestMapping("/test")
	public String test(HttpServletRequest req, HttpServletResponse res) throws Throwable {
		 String cid="1";
		 String logPath = "C:\\save\\"+cid+".txt"; 
		
	     Path path = Paths.get(logPath);
	    
	     Charset cs = StandardCharsets.UTF_8;
	  
	     List<String> list = new ArrayList<String>();
	     List<Chatdata> chatd=new ArrayList<>();
	     
	     try{
	         list = Files.readAllLines(path,cs);
	     }catch(IOException e){
	         e.printStackTrace();
	     }
	     for(String readLine : list){
	    	 readLine.trim();
	        /* System.out.println(readLine);*/
	         
	         String[] tmp=readLine.substring(readLine.indexOf("[")+1, readLine.lastIndexOf("]")).split("\\]"+" "+"\\[");
	        List tmplist=new ArrayList();
	        for(int i=0;i<tmp.length;i++) {
	        	tmplist.add(tmp[i]);
	        }
	        if(tmp.length==2) {
	        	tmplist.add("");
	        }
	         
	         Chatdata cd=new Chatdata();
	         cd.setName((String)tmplist.get(0));
	         cd.setDate((String)tmplist.get(1));
	         cd.setContent((String)tmplist.get(2));
	      
	         chatd.add(cd);
	         
	     }

	    
			 
			  req.setAttribute("chatdata", chatd);
			  
		 
			  
		return "chat/websocketGroup2";

	}
	
	
	@RequestMapping(
		      value = "/fileUpload"
		      , method= RequestMethod.POST
		      , consumes ={"multipart/form-data"}
		)
	public String fileUpload(MultipartHttpServletRequest request,GalleryVO gallery,String studynum, 
			String name ,Model model)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		
		MultipartFile multi = request.getFile("uploadfile");
		String filename = multi.getOriginalFilename();
		System.out.println("filename:[" + filename + "]");
		if (filename != null && !filename.equals("")) {
			String uploadPath = request.getRealPath("/") + "fileSave";
			System.out.println(uploadPath);
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			gallery.setFilename(filename);
			gallery.setFilesize((int) multi.getSize());
		} else {
			gallery.setFilename("");
			gallery.setFilesize(0);
		}
		//나중에수정할부분들
		
		gallery.setTitle(gallery.getName()+"님이 올린 사진");
		
		
		System.out.println(gallery);
		
		gPro.addGallery(gallery);
		
		

		return "";
	}
	
}
