package com.bootdo.clouddoadmin.pojo.vo.do2vo;


import com.bootdo.clouddoadmin.pojo.domain.UserDO;
import com.bootdo.clouddoadmin.pojo.vo.UserVO;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserVO userToVo(UserDO user);

    List<UserVO> userListToVoList(List<UserDO> userList);
}
