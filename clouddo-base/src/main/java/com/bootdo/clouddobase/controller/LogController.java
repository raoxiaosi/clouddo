package com.bootdo.clouddobase.controller;


import com.bootdo.clouddocommon.dto.LogDO;
import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.ApiResult;
import com.bootdo.clouddobase.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/log")
@RestController
public class LogController {
    @Autowired
    LogService logService;

    @GetMapping()
    ApiResult list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        return ApiResult.page(new PageUtils(logService.queryList(query), logService.count(query)));
    }

    @PostMapping("/save")
    ApiResult save(@RequestBody LogDO logDO) {
        if (logService.save(logDO) > 0) {
            return ApiResult.ok();
        }
        return ApiResult.error();
    }

    @DeleteMapping()
    ApiResult remove(Long id) {
        if (logService.remove(id) > 0) {
            return ApiResult.ok();
        }
        return ApiResult.error();
    }

    @PostMapping("/batchRemove")
    ApiResult batchRemove(@RequestParam("ids[]") Long[] ids) {
        int r = logService.batchRemove(ids);
        if (r > 0) {
            return ApiResult.ok();
        }
        return ApiResult.error();
    }


}
