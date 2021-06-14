package com.small.goal.login.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.stream.Collectors;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.small.goal.login.entity.EasyExcelTestVO;
import com.small.goal.login.entity.SmallGoalUser;
import com.small.goal.login.mapper.SmallGoalUserMapper;
import com.small.goal.login.service.SmallGoalUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Description: 用户表服务实现
 * Created on 2020年09月24日
 *
 * @author 念着倒才子傻
 */
@Service
public class SmallGoalUserServiceImpl implements SmallGoalUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmallGoalUserServiceImpl.class);


//    @Autowired
//    private SmallGoalUserMapper smallGoalUserMapper;


    @Override
    public void getExcelTest(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "20103213.xlsx";
//        fileName = URLEncoder.encode(fileName,"UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setContentType("multipart/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        try (ServletOutputStream out = response.getOutputStream()) {
            EasyExcel.write(out).head(head()).sheet("模板").doWrite(dataList());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("");
        head0.add("机构");
        List<String> head1 = new ArrayList<String>();
        head1.add("奖品1");
        head1.add("库存");
        List<String> head2 = new ArrayList<String>();
        head2.add("奖品1");
        head2.add("剩余");
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }

    private List<List<Object>> dataList() {
        List<List<Object>> list = new ArrayList<List<Object>>();
        for (int i = 0; i < 10; i++) {
            List<Object> data = new ArrayList<Object>();
            data.add("字符串" + i);
            data.add(new Date());
            data.add(0.56);
            list.add(data);
        }
        return list;
    }
}
















