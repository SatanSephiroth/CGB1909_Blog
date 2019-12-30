package com.site.blog.my.core.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.blog.my.core.controller.vo.JsonResult;
import com.site.blog.my.core.entity.AdminUser;
import com.site.blog.my.core.service.AdminUserService;
import com.site.blog.my.core.service.BlogService;
import com.site.blog.my.core.service.CategoryService;
import com.site.blog.my.core.service.CommentService;
import com.site.blog.my.core.service.LinkService;
import com.site.blog.my.core.service.TagService;
import com.site.blog.my.core.util.ShiroUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Resource
	private AdminUserService adminUserService;
	@Resource
	private BlogService blogService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private LinkService linkService;
	@Resource
	private TagService tagService;
	@Resource
	private CommentService commentService;

	@GetMapping({"/doLogin" })
	public String login() {
		return "admin/login";
	}
	
	@RequestMapping("/regist")
	public String regist() {
		return "admin/regist";
	}

	@GetMapping({ "/test" })
	public String test() {
		return "admin/test";
	}

	@GetMapping({ "/index", "/index.html" })
	public String index(HttpServletRequest request, Model model) {
		request.setAttribute("path", "index");
		request.setAttribute("categoryCount", categoryService.getTotalCategories());
		request.setAttribute("blogCount", blogService.getTotalBlogsByUserId(ShiroUtil.getUserId()));
		request.setAttribute("linkCount", linkService.getTotalLinks());
		request.setAttribute("tagCount", tagService.getTotalTags());
		request.setAttribute("commentCount", commentService.getTotalComments());
		request.setAttribute("path", "index");
		model.addAttribute("userName", ShiroUtil.getLoginName());
		model.addAttribute("nickName", ShiroUtil.getNickName());
		return "admin/index";
	}

	@PostMapping(value = "/login")
	public String login(@RequestParam("userName") String userName, @RequestParam("password") String password,
			@RequestParam("verifyCode") String verifyCode, HttpSession session) {
		if (StringUtils.isEmpty(verifyCode)) {
			session.setAttribute("errorMsg", "验证码不能为空");
			return "admin/login";
		}
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			session.setAttribute("errorMsg", "用户名或密码不能为空");
			return "admin/login";
		}
		String kaptchaCode = session.getAttribute("verifyCode") + "";
		if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
			session.setAttribute("errorMsg", "验证码错误");
			return "admin/login";
		}
		AdminUser user = adminUserService.findByUserName(userName);
		if (user == null) {
			session.setAttribute("errorMsg", "用户名错误");
			return "admin/login";
		} else if (user.getLocked() != 0) {
			session.setAttribute("errorMsg", "账号已封停");
			return "admin/login";
		}
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		try {
			subject.login(token);
		} catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "密码错误");
			return "admin/login";
		}
		return "redirect:/admin/index";
	}

	@GetMapping("/profile")
	public String profile(HttpServletRequest request) {
		Integer loginUserId = ShiroUtil.getUserId();
		AdminUser adminUser = adminUserService.getUserDetailById(loginUserId);
		if (adminUser == null) {
			return "admin/login";
		}
		request.setAttribute("path", "profile");
		request.setAttribute("loginUserName", adminUser.getLoginUserName());
		request.setAttribute("nickName", adminUser.getNickName());
		return "admin/profile";
	}

	@PostMapping("/profile/password")
	@ResponseBody
	public String passwordUpdate(HttpServletRequest request, @RequestParam("originalPassword") String originalPassword,
			@RequestParam("newPassword") String newPassword) {
		if (StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)) {
			return "参数不能为空";
		}
		Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
		if (adminUserService.updatePassword(loginUserId, originalPassword, newPassword)) {
			// 修改成功后清空session中的数据，前端控制跳转至登录页
			request.getSession().removeAttribute("loginUserId");
			request.getSession().removeAttribute("loginUser");
			request.getSession().removeAttribute("errorMsg");
			return "success";
		} else {
			return "修改失败";
		}
	}

	@PostMapping("/profile/name")
	@ResponseBody
	public String nameUpdate(HttpServletRequest request, @RequestParam("loginUserName") String loginUserName,
			@RequestParam("nickName") String nickName) {
		if (StringUtils.isEmpty(loginUserName) || StringUtils.isEmpty(nickName)) {
			return "参数不能为空";
		}
		Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
		if (adminUserService.updateName(loginUserId, loginUserName, nickName)) {
			return "success";
		} else {
			return "修改失败";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("loginUserId");
		request.getSession().removeAttribute("loginUser");
		request.getSession().removeAttribute("errorMsg");
		return "admin/login";
	}

	@RequestMapping("/doRegist")
	@ResponseBody
	public JsonResult register(@RequestParam("userName") String userName, @RequestParam("password") String password,
			@RequestParam("cfgPassword") String cfgPassword, @RequestParam("nickName")String nickName) {
		adminUserService.insert(userName, password, cfgPassword, nickName);
		return new JsonResult("注册成功");
	}
}
