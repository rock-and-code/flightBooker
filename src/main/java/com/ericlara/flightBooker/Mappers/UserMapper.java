package com.ericlara.flightBooker.Mappers;
import java.util.List;

import org.mapstruct.Mapper;

import com.ericlara.flightBooker.Models.UserDto;
import com.ericlara.flightBooker.Models.UserEntity;
import com.ericlara.flightBooker.Models.UserXML;

@Mapper
public interface UserMapper {

    UserXML userEntityToUserXML(UserEntity userEntity);

    UserXML userDTOToUserXML(UserDto userDto);

    List<UserXML> userEntityListToUserXMLList(List<UserEntity> userEntityList);
    
}
