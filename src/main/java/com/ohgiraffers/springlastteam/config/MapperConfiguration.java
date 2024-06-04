package com.ohgiraffers.springlastteam.config;

import com.ohgiraffers.springlastteam.entity.BuyingUser;
import com.ohgiraffers.springlastteam.gonggu.dto.BuyingUserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        /*복합 키에 대한 매핑 설정*/
        /*modelMapper.addMappings(new PropertyMap<BuyingUser, BuyingUserDTO>() {
            @Override
            protected void configure() {
                map().getId().setBuyingNo(source.getId().getBuyingNo().getBuyingNo());
                map().getId().setUserNo(source.getId().getUserNo().getUserNo());
            }
        });*/
        return modelMapper;
    }
}
