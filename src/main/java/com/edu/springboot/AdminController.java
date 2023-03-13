package com.edu.springboot;

import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.springboot.jdbc.SupportsDTO;
import com.edu.springboot.jdbc.IMemberService;
import com.edu.springboot.jdbc.ISupportsService;
import com.edu.springboot.jdbc.ParameterSupportsDTO;
import com.edu.springboot.jdbc.SellRightDTO;

@Controller
public class AdminController {

	@Autowired
	ISupportsService daoo;

	@Autowired
	IMemberService member_dao;

	@RequestMapping("/admin/index.do")
	public String admin(Principal principal, HttpSession session) {

		// 오류나서 주석 처리 했더용 - 지훈
//		String loginId = principal.getName();
//		String member_idx = member_dao.member_idx(loginId);
//
//		SellRightDTO dto  = member_dao.LoginUser(member_idx);
//		String Authority = dto.getAuthority();
//
//		if(Authority.equals("Seller"))
//		{
//			return "redirect:/productInsert";
//		}

		return "/admin/index";
	}

	@RequestMapping("/admin/adminFaq.do")
	public String adminFaq(Model model, HttpServletRequest req) {
		String category = req.getParameter("categoryCode");
		String contact = req.getParameter("contact");
		int totalRecordCount = daoo.getTotalCountSearch("faq", category);

		ArrayList<SupportsDTO> lists = daoo.listPageSearch("faq", category);

		for (SupportsDTO dto : lists) {
			String temp = dto.getContents().replace("\r\n", "<br/>");
			dto.setContents(temp);
		}

		model.addAttribute("contact", contact);
		model.addAttribute("category", category);
		model.addAttribute("lists", lists);
		return "admin/adminFaq";
	}

	@RequestMapping("/admin/adminNotice.do")
	public String blank1(Model model, HttpServletRequest req) {
		String category = req.getParameter("categoryCode");
		int totalRecordCount = daoo.getTotalCountSearch("notice", category);

		ArrayList<SupportsDTO> lists = daoo.listPageSearch("notice", category);

		for (SupportsDTO dto : lists) {
			System.out.println("나 동작");
			String temp = dto.getContents().replace("\r\n", "<br/>");
			dto.setContents(temp);
			System.out.println("콘텐츠 >>>>> " + dto.getContents());
		}

		model.addAttribute("lists", lists);
		return "admin/adminNotice";
	}

	@RequestMapping("/admin/delete.do")
	public String delete(HttpServletRequest req, HttpSession session, Principal principal) {
		// 삭제는 본인만 가능하므로 로그인 확인을 진행한다.
		String email = principal.getName();
		session.setAttribute("siteUserInfo", email);
		if (session.getAttribute("siteUserInfo") == null) {
			return "redirect:/myLogin.do";
		}
		int applyRow = daoo.delete(req.getParameter("idx"), email);

		System.out.println("삭제된 행의 갯수 : " + applyRow);

		return "redirect:/admin/index.do";
	}

	@RequestMapping("/admin/write.do")
	public String writeSupports(Model model, HttpSession session, HttpServletRequest req, Principal principal) {

		return "/admin/write";
	}

	// 글쓰기 처리
	@RequestMapping(value = "/admin/writeSupportsAction.do", method = RequestMethod.POST)
	public String writeSupportsAction(Model model, HttpServletRequest req, HttpSession session, Principal principal) {

		if (session.getAttribute("siteUserInfo") == null) {
			return "redirect:/myLogin.do";
		}
		String email = principal.getName();
		int applyRow = daoo.writeSupports(req.getParameter("title"), email, req.getParameter("contents"),
				req.getParameter("categorycode"), req.getParameter("contact"));
		System.out.println("입력된행의갯수:" + applyRow);
		return "redirect:/admin/index.do";
	}

	// 수정페이지 매핑
	@RequestMapping("/admin/modify.do")
	public String modify(Model model, HttpServletRequest req, HttpSession session, SupportsDTO supportsDTO) {

		supportsDTO.setIdx(Integer.parseInt((String) req.getParameter("idx")));
		SupportsDTO dto = daoo.view(supportsDTO);

		model.addAttribute("dto", dto);
		return "admin/modify";
	}

	// 수정처리
	@RequestMapping("/admin/modifyAction.do")
	public String modifyAction(HttpSession session, SupportsDTO supportsDTO) {


		int applyRow = daoo.modifySupports(supportsDTO);
		System.out.println("수정된행의갯수:" + applyRow);

		return "redirect:/admin/index.do";
	}

	@RequestMapping("/admin/utilities-color.do")
	public String utilities1() {
		return "admin/utilities-color";
	}

	@RequestMapping("/admin/utilities-animation.do")
	public String utilities2() {
		return "admin/utilities-animation";
	}

	@RequestMapping("/admin/utilities-border.do")
	public String utilities3() {
		return "admin/utilities-border";
	}

	@RequestMapping("/admin/utilities-other.do")
	public String utilities4() {
		return "admin/utilities-other";
	}

	@RequestMapping("/admin/tables.do")
	public String tables() {
		return "admin/tables";
	}

	@RequestMapping("/admin/charts.do")
	public String charts() {
		return "admin/charts";
	}

	@RequestMapping("/admin/buttons.do")
	public String buttons() {
		return "admin/buttons";
	}

	@RequestMapping("/admin/cards.do")
	public String cards() {
		return "admin/cards";
	}

	@RequestMapping("/admin/404.do")
	public String error() {
		return "admin/404";
	}

	@RequestMapping("/admin/login.do")
	public String login() {
		return "admin/login";
	}

	@RequestMapping("/admin/register.do")
	public String register() {
		return "admin/register";
	}

	@RequestMapping("/admin/forgot-password.do")
	public String password() {
		return "admin/forgot-password";
	}

}
