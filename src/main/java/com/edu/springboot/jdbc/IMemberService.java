package com.edu.springboot.jdbc;





import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface IMemberService {

	
		public int insert(MemberDTO memberDTO);
		
		public MemberDTO select(MemberDTO memberDTO);
		
		public int checkEmail(MemberDTO memberDTO);
		
		public int membership(String loginId);

		public int cancelMembership(String loginId);
		
		public int joinMembership(String loginId);

		public String email(String loginId);
		
		public String name(String loginId);
		
		public String member_idx(String loginId);

		public int Sell_AuthorizedAction(SellerDTO sellerDTO);

		public ArrayList<SellRightDTO> situation_zero(List<String> appList);
		
		public int situation_one(SellRightDTO sellRightDTO);
		public int seller(SellRightDTO sellRightDTO);
	
		
		

		public int situation_two(SellRightDTO sellRightDTO);
		public int member(SellRightDTO sellRightDTO);
		
		

		public ArrayList<SellRightDTO> appView(int view);
		
		

		public ArrayList<SellRightDTO> sellerList(List<String> sList);

		public ArrayList<SellRightDTO> sellerView(int view);

		
		public ArrayList<SellRightDTO> blockList(List<String> bList);

		public ArrayList<SellRightDTO> blockView(int member_idx);

	
}