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

import com.edu.springboot.jdbc.IReviewService;
import com.edu.springboot.jdbc.ReviewDTO;
import com.edu.springboot.jdbc.SupportsDTO;

@Controller
public class ReviewController {

	@Autowired
	IReviewService daoo;
	
	@RequestMapping("/review/reviewList.do")
	public String review(Model model, HttpServletRequest req) {
		
		
		int totalRecordCount = 
				daoo.reviewcount();
		
		ReviewDTO totalstar = 
				daoo.starcount();
		
		ArrayList<ReviewDTO> lists = 
				daoo.reviewList();
		
		for (ReviewDTO dto : lists) {
			String temp = dto.getReview()
					.replace("\r\n", "<br/>");
			dto.setReview(temp);
		}
		
		model.addAttribute("totalstar", totalstar);
		model.addAttribute("lists", lists);
		System.out.println("출력얍"+totalstar);
		return "review/reviewList";
	}
	
	@RequestMapping("/review/review.do")
	public String reviewWrite(Model model, HttpSession session,
			HttpServletRequest req) {
		
		return "review/review";
	}
	
	// 글쓰기 처리
	@RequestMapping(value = "/review/reviewAction.do", method = RequestMethod.POST)
	public String writeAction(ReviewDTO reviewDTO, Model model, HttpServletRequest req, HttpSession session, Principal principal) {

		int applyRow = daoo.reviewWrite(reviewDTO);
		System.out.println("입력된행의갯수:" + applyRow);

		return "redirect:review.do";
	}
	
}
