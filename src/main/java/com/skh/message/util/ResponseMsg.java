package com.skh.message.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User: skh.
 * Date: 2018/8/1 Time: 15:43.
 * Description: 返回信息
 */
public class ResponseMsg extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public ResponseMsg() {
        put("code", 0);
        put("msg", "success");
    }

    public static ResponseMsg error() {
        return error(500, "未知异常");
    }

    public static ResponseMsg error(String msg) {
        return error(500, msg);
    }

    public static ResponseMsg error(int code, String msg) {
        ResponseMsg r = new ResponseMsg();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static ResponseMsg ok(String msg) {
        ResponseMsg r = new ResponseMsg();
        r.put("msg", msg);
        return r;
    }

    public static ResponseMsg ok(Map<String, Object> map) {
        ResponseMsg r = new ResponseMsg();
        r.putAll(map);
        return r;
    }

    public static ResponseMsg ok() {
        return new ResponseMsg();
    }

    @Override
    public ResponseMsg put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
