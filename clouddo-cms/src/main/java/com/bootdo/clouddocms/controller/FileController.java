package com.bootdo.clouddocms.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.clouddocommon.context.FilterContextHandler;
import com.bootdo.clouddocommon.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.bootdo.clouddocms.pojo.domain.FileDO;
import com.bootdo.clouddocms.pojo.dto.FileDTO;
import com.bootdo.clouddocms.pojo.dto.convert.FileConvert;
import com.bootdo.clouddocms.service.FileService;
import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.ApiResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-12 12:17:36
 */

@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${app.filePath}")
    String filePath;

    @Value("${app.pre}")
    String filePre;

    @Autowired
    private FileService fileService;

    @GetMapping("{id}")
    public ApiResult get(@PathVariable Long id) {
        FileDTO fileDTO = FileConvert.MAPPER.do2dto(fileService.get(id));
        return ApiResult.data(fileDTO);
    }

    @GetMapping("getToken")
    public ApiResult getToken() {
        return ApiResult.ok().put("token", FilterContextHandler.getToken()).put("url", "http://localhost:8002/api-cms/file/upload")
                .put("key", UUID.randomUUID().toString());
    }

    @PostMapping("upload")
    public ApiResult upload(MultipartFile file, String key) {
        try {
            String resPath = FileUtils.saveFile(file.getBytes(), filePath, key);
            fileService.save(new FileDO() {{
                setCreateDate(new Date());
                setUrl("http://localhost:8004" + filePre + "/"+resPath);
                setType(1);
            }});
            return ApiResult.ok().put("resPath", resPath);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResult.error("文件上传失败");
        }
    }

    /**
     * 分页查询
     */
    @GetMapping
    public ApiResult list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<FileDO> fileList = fileService.list(query);
//        List<FileDTO> fileDTOS = FileConvert.MAPPER.dos2dtos(fileList);
        int total = fileService.count(query);
//        PageUtils pageUtils = new PageUtils(fileDTOS, total);
        PageUtils pageUtils = new PageUtils(fileList, total);
        return ApiResult.page(pageUtils);
    }

    /**
     * 保存
     */
    @PostMapping
    public ApiResult save(FileDO file) {
        return ApiResult.operate(fileService.save(file) > 0);
    }

    /**
     * 修改
     */
    @PutMapping
    public ApiResult update(FileDO file) {
        return ApiResult.operate(fileService.update(file) > 0);
    }

    /**
     * 删除
     */
    @DeleteMapping
    public ApiResult remove(Long id) {
        return ApiResult.operate(fileService.remove(id) > 0);
    }

    /**
     * 删除
     */
    @DeleteMapping("/batchRemove")
    public ApiResult remove(@RequestParam("ids[]") Long[] ids) {
        return ApiResult.operate(fileService.batchRemove(ids) > 0);
    }
}
