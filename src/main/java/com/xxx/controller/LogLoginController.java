package com.xxx.controller;

import com.xxx.base.BaseController;
import com.xxx.base.ResultInfo;
import com.xxx.bean.LogLogin;
import com.xxx.service.LogLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/logInfo")
public class LogLoginController extends BaseController {

    @Resource
    private LogLoginService logLoginService;

    @RequestMapping("/index")
    public String toLogLogin(){
        return "logInfo/logInfo";
    }

    /**
     * 展示所有日志
     * @param logLogin
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> queryLogLoginByParams(LogLogin logLogin){
        return logLoginService.queryByParamsForTable(logLogin);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultInfo deleteLog(Integer[] ids){
        logLoginService.deleteBatch(ids);

        return success("日志删除成功！");
    }
}
