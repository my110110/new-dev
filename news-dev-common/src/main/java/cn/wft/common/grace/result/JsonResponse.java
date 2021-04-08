package cn.wft.common.grace.result;


import lombok.Data;

/**
 *
 * @Title: JsonResponse.java
 * @Package com.imooc.utils
 * @Description: 自定义响应数据结构
 * 				本类可提供给 H5/ios/安卓/公众号/小程序 使用
 * 				前端接受此类数据（json object)后，可自行根据业务去实现相关功能
 *
 * 				200：表示成功
 * 				500：表示错误，错误信息在msg字段中
 * 				501：bean验证错误，不管多少个错误都以map形式返回
 * 				502：拦截器拦截到用户token出错
 * 				555：异常抛出信息
 * 				556: 用户qq校验异常
 * 			    557: 校验用户是否在CAS登录，用户门票的校验
 * @Copyright: Copyright (c) 2020
 * @Company: www.wfting.cn
 * @author zyh
 * @version V1.0
 */
@Data
public class JsonResponse {

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    private String ok;	// 不使用

    public static JsonResponse build(Integer status, String msg, Object data) {
        return new JsonResponse(status, msg, data);
    }

    public static JsonResponse build(Integer status, String msg, Object data, String ok) {
        return new JsonResponse(status, msg, data, ok);
    }

    public static JsonResponse ok(Object data) {
        return new JsonResponse(data);
    }

    public static JsonResponse ok() {
        return new JsonResponse(null);
    }

    public static JsonResponse errorMsg(String msg) {
        return new JsonResponse(500, msg, null);
    }

    public static JsonResponse errorUserTicket(String msg) {
        return new JsonResponse(557, msg, null);
    }

    public static JsonResponse errorMap(Object data) {
        return new JsonResponse(501, "error", data);
    }

    public static JsonResponse errorTokenMsg(String msg) {
        return new JsonResponse(502, msg, null);
    }

    public static JsonResponse errorException(String msg) {
        return new JsonResponse(555, msg, null);
    }

    public static JsonResponse errorUserQQ(String msg) {
        return new JsonResponse(556, msg, null);
    }

    public JsonResponse() {

    }

    public JsonResponse(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public JsonResponse(Integer status, String msg, Object data, String ok) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.ok = ok;
    }

    public JsonResponse(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }


}
