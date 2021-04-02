/**
  * Copyright 2018 aTool.org 
  */
package com.wx.exam.pojo.vo;
import java.util.List;

/**
 * Auto-generated: 2018-07-28 9:49:34
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class ExamAnswerVO {

    private String eid;
    private List<ExamAnswerQuestions> questions;
    public void setEid(String eid) {
         this.eid = eid;
     }
     public String getEid() {
         return eid;
     }

    public void setQuestions(List<ExamAnswerQuestions> questions) {
         this.questions = questions;
     }
     public List<ExamAnswerQuestions> getQuestions() {
         return questions;
     }

}