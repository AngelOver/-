package controller;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Message;
import domain.Theme;
import domain.User;

import service.ThemeService;
import service.UserService;

@Controller
@RequestMapping("/theme")
public class ThemeController {
	@Resource
	private ThemeService themeService;
	
	
	@RequestMapping("/hello")
	public String  hello(){
		return "success";
	}
	
	@RequestMapping("/addTheme")
	public String  addTheme(Theme theme,HttpServletRequest request){
		HttpSession session=request.getSession();
		
		
		User user=(User) session.getAttribute("user");
		
		theme.setUser(user);
		Theme addTheme=themeService.add(theme);
	
			
		return "redirect:themeList.do";
		
		
	}
	@RequestMapping("/delTheme")
	public String  delTheme(String t_id,HttpServletRequest request){
	
		Theme theme =new Theme();
		theme.setId(t_id);
		themeService.del(theme);
	
		return "redirect:themeList.do";
		
		
	}
	
	@RequestMapping("/themeList")
	public String themeList(HttpServletRequest request){
	
		List<Theme> themeList=themeService.findAll();
		//添加倒置
		List<Theme> list = new ArrayList<Theme>();
		int lenght = themeList.size();
		for (int i = lenght -1;i>=0;i--) {
			list.add(themeList.get(i));
		}
		request.getSession().setAttribute("themeList",list);
		return "redirect:/jsps/themeList.jsp";
	}

	@RequestMapping("/setTheme")
	public String  setTheme(HttpServletRequest request){
		HttpSession session=request.getSession();
		
		String id = request.getParameter("id");
		
		Theme theme=themeService.findById(id);
		session.setAttribute("theme", theme);
		
	
			
		return "redirect:/message/messageList.do";
		
		
	}
	
	
}
