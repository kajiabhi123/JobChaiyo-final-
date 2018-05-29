package com.example.designmodal.jobchaiyo.Model;

/**
 * Created by compware on 5/15/2018.
 */

public class JobAttributes
{
    private String job_title;
    private int vacancy_no;
    private String experience;
    private String deadline;
    private String job_description;
    private String job_specification;
    private String area_name;
    private String job_type;
    private String company_name;
    private String education_name;
    private String job_id;

    public JobAttributes(String job_id,String employer,String education,String job_type,String job_title, int vacancy_no, String experience, String deadline, String job_description, String job_specification, String area_name) {
        this.job_title = job_title;
        this.vacancy_no = vacancy_no;
        this.experience = experience;
        this.deadline = deadline;
        this.job_description = job_description;
        this.job_specification = job_specification;
        this.area_name = area_name;
        this.job_type = job_type;
        this.company_name = employer;
        this.education_name= education;
        this.job_id= job_id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public int getVacancy_no() {
        return vacancy_no;
    }

    public void setVacancy_no(int vacancy_no) {
        this.vacancy_no = vacancy_no;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getJob_specification() {
        return job_specification;
    }

    public void setJob_specification(String job_specification) {
        this.job_specification = job_specification;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getEducation_name() {
        return education_name;
    }

    public void setEducation_name(String education)
    {
        this.education_name = education;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }
}
