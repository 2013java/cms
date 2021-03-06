package com.qatang.cms.validator.impl.user;

import com.qatang.cms.enums.Gender;
import com.qatang.cms.exception.validator.ValidateFailedException;
import com.qatang.cms.form.user.UserForm;
import com.qatang.cms.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by qatang on 14-6-12.
 */
@Component
public class UserValidator extends AbstractValidator<UserForm> {
    @Override
    public boolean validate(UserForm userForm) throws ValidateFailedException {
        logger.info("开始验证userForm参数");
        if (userForm == null) {
            String msg = String.format("userForm参数不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }

        if (StringUtils.isEmpty(userForm.getUsername())) {
            String msg = String.format("用户名不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (userForm.getUsername().length() < 6 || userForm.getUsername().length() > 16) {
            String msg = String.format("用户名长度必须在6-16个字符之间");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (StringUtils.isEmpty(userForm.getPassword())) {
            String msg = String.format("密码不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (userForm.getPassword().length() < 6 || userForm.getPassword().length() > 16) {
            String msg = String.format("密码长度必须在6-16个字符之间");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (StringUtils.isEmpty(userForm.getConPassword())) {
            String msg = String.format("确认密码不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (userForm.getConPassword().length() < 6 || userForm.getConPassword().length() > 16) {
            String msg = String.format("确认密码长度必须在6-16个字符之间");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (!userForm.getPassword().equals(userForm.getConPassword())) {
            String msg = String.format("两次输入密码不一致");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }

        if (StringUtils.isEmpty(userForm.getName())) {
            String msg = String.format("姓名不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (userForm.getName().length() < 2 || userForm.getName().length() > 6) {
            String msg = String.format("姓名长度必须在2-6个字符之间");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        if (StringUtils.isEmpty(userForm.getGenderValue())) {
            String msg = String.format("性别不能为空");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        int genderValue = 0;
        try {
            genderValue = Integer.valueOf(userForm.getGenderValue());
        } catch (Exception e) {
            String msg = String.format("性别字段格式不合法");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        Gender gender = Gender.get(genderValue);
        if (gender == null) {
            String msg = String.format("性别字段格式不合法");
            logger.error(msg);
            throw new ValidateFailedException(msg);
        }
        return true;
    }
}
