package com.edu.springboot.jdbc;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMainService {
	
	ArrayList<TotalJourneyDTO> adJourney_list();

}
