package com.javen.wechat;

import com.javen.vo.AjaxResult;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.MenuApi;
import com.jfinal.weixin.sdk.api.TemplateData;
import com.jfinal.weixin.sdk.api.TemplateMsgApi;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.weixin.annotation.WxApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@WxApi("/weixin/api")
@Slf4j
public class WeixinApiController {
    private AjaxResult result = new AjaxResult();

    @GetMapping("menu")
    @ResponseBody
    public String getMenu() {
        ApiResult apiResult = MenuApi.getMenu();
        return apiResult.getJson();
    }



    @ResponseBody
    @RequestMapping("/sendTemp")
    public AjaxResult sendTemp(HttpServletRequest request) {
        try {
            String jsonStr = HttpKit.readData(request);
            log.info("request data>" + jsonStr);
            if (StrKit.isBlank(jsonStr)) {
                return result.addError("para is null");
            }
            ApiResult apiResult = TemplateMsgApi.send(jsonStr);
            log.info("sendMessage result>" + apiResult.getJson());
            boolean succeed = apiResult.isSucceed();
            if (!succeed) {
                return result.addError(apiResult.getErrorMsg());
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result.addError("系统异常");
        }
    }

}