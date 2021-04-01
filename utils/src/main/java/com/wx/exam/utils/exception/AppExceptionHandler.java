package com.wx.exam.utils.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wx.exam.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(HttpServletRequest req, HttpServletResponse resp, Exception ex) throws IOException {
        ex.printStackTrace();

        if (req.getHeader("x-requested-with") != null && req.getHeader("x-requested-with")
                .equalsIgnoreCase("XMLHttpRequest")) { //如果是ajax请求响应头会有x-requested-with
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=utf-8");

            //ajax请求
            PrintWriter out = resp.getWriter();
            ObjectMapper objectMapper = new ObjectMapper();
            Result result;
            if (ex instanceof BusinessException) {
                BusinessException exception = (BusinessException) ex;
                result = new Result(exception.getCode(), exception.getMessage());
            } else if (ex instanceof ParameterException) {
                ParameterException exception = (ParameterException) ex;
                result = new Result(exception.getCode(), exception.getMessage());
            } else {
                result = new Result(500, "未知错误，请联系管理员");
            }
            String json = objectMapper.writeValueAsString(result);
            out.write(json);
            return null;
        } else {
            //非ajax请求时
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("ex", ex);
            // 根据不同错误转向不同页面
            if (ex instanceof BusinessException) {
                return new ModelAndView("error/500", model);
            }
            else if(ex instanceof  PageCodeException){
                PageCodeException pce=(PageCodeException)ex;
                String requestURI = req.getRequestURI();
                return new ModelAndView("redirect:"+requestURI+"?pageCode="+((PageCodeException) ex).getMaxPageCode());
            }


            else if (ex instanceof ParameterException) {
                return new ModelAndView("error/500", model);
            } else {
                return new ModelAndView("error/500", model);
            }
        }
    }
}
