package cn.qblank.springdemo.controller;

import cn.qblank.springdemo.service.SellerService;
import cn.qblank.springdemo.utils.AuthUtil;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Map;

@Controller
@RequestMapping("/seller/user")
@Slf4j
public class SellUserController {


    @Autowired
    private SellerService sellerService;

    /**
     * 登录
     * @param response
     * @param request
     * @throws Exception
     */
    @GetMapping("/login")
    public ModelAndView login(HttpServletResponse response,HttpServletRequest request,
                      Map<String,Object> map) throws Exception{


        String backUrl = "http://qblank.ticp.io/seller/callBack";

        String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + AuthUtil.APPID +
                "&redirect_uri=" + URLEncoder.encode(backUrl) +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        map.put("url",url);
        return new ModelAndView("user/login",map);
    }

    /**
     * 回调
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("/callBack")
    public ModelAndView callBack(HttpServletRequest request) throws Exception{
        String code = request.getParameter("code");
        String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + AuthUtil.APPID +
                "&secret=" + AuthUtil.APPSECRET +
                "&code=" + code +
                "&grant_type=authorization_code";
        JSONObject jsonObject = AuthUtil.getJson(url);
        String openid = jsonObject.getString("openid");
        String token = jsonObject.getString("access_token");
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+ token
                +"&openid=" + openid + "&lang=zh_CN";

        JSONObject userInfo = AuthUtil.getJson(infoUrl);
        System.out.println(userInfo);
        return null;
    }

}
