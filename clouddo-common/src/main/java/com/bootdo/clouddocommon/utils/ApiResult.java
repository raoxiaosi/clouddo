package com.bootdo.clouddocommon.utils;

import java.util.HashMap;
import java.util.Map;

public class ApiResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public ApiResult() {
        put("code", 200);
        put("msg", "操作成功");
    }


    public static ApiResult error() {
        return error(500, "操作失败");
    }

    public static ApiResult error(String msg) {
        return error(500, msg);
    }

    public static ApiResult error(int code, String msg) {
        ApiResult r = new ApiResult();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }


    public static ApiResult ok() {
        return new ApiResult();
    }

    public static ApiResult ok(String msg) {
        ApiResult r = new ApiResult();
        r.put("msg", msg);
        return r;
    }

    public static ApiResult ok(Map<String, Object> map) {
        ApiResult r = new ApiResult();
        r.putAll(map);
        return r;
    }


    public static ApiResult error401() {
        return error(401, "你还没有登录");
    }

    public static ApiResult error403() {
        return error(403, "你没有访问权限");
    }


    public static ApiResult operate(boolean b){
        return b ? ApiResult.ok() : ApiResult.error();
    }


    public static ApiResult data(Object data){
        return ApiResult.ok().put("data",data);
    }

    public static ApiResult page(Object page){
        return ApiResult.ok().put("page",page);
    }


    @Override
    public ApiResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
