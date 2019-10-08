package com.bootdo.clouddocms.pojo.dto.convert;


import com.bootdo.clouddocms.pojo.domain.FileDO;
import com.bootdo.clouddocms.pojo.dto.FileDTO;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper
public interface FileConvert {
    FileConvert MAPPER = Mappers.getMapper(FileConvert.class);

    public FileDTO do2dto(FileDO person);

    public List<FileDTO> dos2dtos(List<FileDO> list);
}