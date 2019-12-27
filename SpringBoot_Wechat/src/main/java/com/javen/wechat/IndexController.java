package com.javen.wechat;

import com.jfinal.weixin.sdk.api.AccessTokenApi;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.SnsAccessToken;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.weixin.properties.DreamWeixinProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@Slf4j
public class IndexController {
    @Autowired
    private DreamWeixinProperties weixinProperties;

    @GetMapping("")
    @ResponseBody
    public String index(){
        return "欢迎使用spring-boot-wechat By Javen";
    }

    @GetMapping("/getProperties")
    @ResponseBody
    public DreamWeixinProperties getProperties(){
        return weixinProperties;
    }

    @RequestMapping("/toOauth")
    public void toOauth(HttpServletResponse response){
        try {
            String url=SnsAccessTokenApi.getAuthorizeURL(ApiConfigKit.getApiConfig().getAppId(),
                    "http://mac.javen.1mfy.cn/oauth",
                    "123",
                    false);
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/oauth",method = RequestMethod.GET)
    public ModelAndView oauth(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code, @RequestParam("state") String state){
        try {
            log.info("state:"+state+" code:"+code);
            SnsAccessToken snsAccessToken=SnsAccessTokenApi.getSnsAccessToken(ApiConfigKit.getApiConfig().getAppId(),ApiConfigKit.getApiConfig().getAppSecret(),code);
            String openId=snsAccessToken.getOpenid();
            request.getSession().setAttribute("openId", openId);
            return new ModelAndView("redirect:/towxpay");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/towxpay")
    public String towxpay(ModelMap map) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("openId:"+request.getSession().getAttribute("openId"));
        map.addAttribute("msssage", "欢迎使用IJPay -By Javen");
        return "index";
    }
}
