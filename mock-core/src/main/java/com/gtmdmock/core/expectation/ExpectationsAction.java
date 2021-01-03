package com.gtmdmock.core.expectation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 期望集容器：用于管理core中所有的期望集
 */
public class ExpectationsAction {

    private final Logger logger = LoggerFactory.getLogger(ExpectationsAction.class);

    private List<ExpectationsTemplate> expectations;

    //生成当前所有配置的expectations
    public static List<ExpectationsTemplate> genAllExpectations() {
        return null;
    }

    //获取某project下所有的expectations
    public List<ExpectationsTemplate> getExpectations(Integer projectId){
        List<ExpectationsTemplate> templates = new ArrayList<>();

        for (ExpectationsTemplate template: this.expectations){
            if (Objects.equals(projectId,template.getProjectId())){
                templates.add(template);
            }
        }
        return templates;
    }

    //获取某个ExpectationsTemplate
    public ExpectationsTemplate getExpectationTemplate(Integer expectationsId){
        for (ExpectationsTemplate template: this.expectations){
            if (expectationsId.equals(template.getExpectationsId())){
                return template;
            }
        }
        logger.info("没有找到此id的expectationTemplate：[{}]",expectationsId);
        return null;
    }

    //获取某个project下所有的ExpectationsTemplate
    public List<ExpectationsTemplate> getExpectationsTemplatesByProjectId(Integer projectId){
        List<ExpectationsTemplate> templates = new ArrayList();
        for (ExpectationsTemplate template: this.expectations){
            if (projectId.equals(template.getProjectId())){
                templates.add(template);
            }
        }
        if (templates.isEmpty()){
            logger.info("{}项目的ExpectationsTemplate不存在",projectId);
        }
        return templates;
    }

    public List<ExpectationsTemplate> getAllExpectations() {
        return expectations;
    }

    public void setExpectations(List<ExpectationsTemplate> expectations) {
        this.expectations = expectations;
    }
}
