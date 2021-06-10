package com.ensias.shopping.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ensias.shopping.entity.res.ResultBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.Set;

/* Gestiondes exceptions
Pour les exceptions levées dans le Controller, les méthodes de traitement définies dans GlobalExceptionHandler peuvent fonctionner
 */
@ControllerAdvice
public class GlobalExceptionHandler implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //gestion par defaut d'exception
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBean<String> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        LOGGER.error(e.getMessage(), e);
        e.printStackTrace();
        ResultBean<String> r = new ResultBean<>(e);
        r.setData(req.getRequestURI());
        return r;
    }

    @ExceptionHandler(value = RuntimeException.class)
    public void runtimeExceptionHandler(HttpServletRequest req, HttpServletResponse res, Exception e) throws Exception {
        LOGGER.error(e.getMessage(), e);
        req.setAttribute("msg", e.getMessage());
        //page d erreur
        req.getRequestDispatcher("/mall/user/error.html").forward(req, res);
    }

    //exceptions de page de validation
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public ResultBean<String> validationExceptionHandler(HttpServletRequest req, ConstraintViolationException e) throws Exception {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            strBuilder.append(violation.getMessage() + ",");
        }
        LOGGER.error(strBuilder.toString(), e);
        ResultBean<String> r = new ResultBean(strBuilder.toString());
        r.setData(req.getRequestURI());
        return r;
    }


}
