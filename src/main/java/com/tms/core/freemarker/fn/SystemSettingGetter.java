package com.tms.core.freemarker.fn;

import com.tms.core.front.SystemManager;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

/**
 * 获取系统参数的配置
 * Created by dylan on 15-1-15.
 */
public class SystemSettingGetter implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        return SystemManager.getInstance().getSystemSetting();
    }
}
