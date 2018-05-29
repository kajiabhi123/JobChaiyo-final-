package com.example.designmodal.jobchaiyo.Model;

/**
 * Created by compware on 5/27/2018.
 */

public class ListofJobsPostedbyEmployer
{
    private String employer_id;
    private String job_title;
    private String experience;
    private String vacancy_no;
    private String deadline;
    private String job_description;
    private String job_specification;
    private String education_name;
    private String city_name;
    private String job_type;



    public ListofJobsPostedbyEmployer(String employer_id, String job_title, String experience, String vacancy_no, String deadline, String job_description, String job_specification, String education_name, String city_name, String job_type) {
        this.employer_id = employer_id;
        this.job_title = job_title;
        this.experience = experience;
        this.vacancy_no = vacancy_no;
        this.deadline = deadline;
        this.job_description = job_description;
        this.job_specification = job_specification;
        this.education_name = education_name;
        this.city_name = city_name;
        this.job_type = job_type;
    }
    public String getEmployer_id() {
        return employer_id;
    }

    public void setEmployer_id(String employer_id) {
        this.employer_id = employer_id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getVacancy_no() {
        return vacancy_no;
    }

    public void setVacancy_no(String vacancy_no) {
        this.vacancy_no = vacancy_no;
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

    public String getEducation_name() {
        return education_name;
    }

    public void setEducation_name(String education_name) {
        this.education_name = education_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

}
