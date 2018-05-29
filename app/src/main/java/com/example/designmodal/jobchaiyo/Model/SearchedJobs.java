package com.example.designmodal.jobchaiyo.Model;


import java.io.Serializable;

/**
 * Created by Kuldip on 5/15/2018.
 */

public class SearchedJobs implements Serializable
{

    private String status;
    private String job_id;
    private String employer_id;
    private String job_title;
    private String vacancy_no;
    private String education_id;
    private String experience;
    private String deadline;
    private String type_id;
    private String job_description;
    private String job_specification;
    private String datetime;
    private String jobtype_id;
    private String city_id;
    private String area_name;
    private String job_type;
    private String type_name;
    private String company_name;
    private String company_address;
    private String companynature_id;
    private String ownership_id;
    private String website;
    private String contact_person;
    private String office_no;
    private String mobile_no;
    private String email;
    private String opt_email;
    private String username;
    private String password;
    private String logo;
    private String ownership_name;
    private String city_name;
    private String education_name;
    private String company_nature;

    public SearchedJobs(String status,String job_id, String employer_id, String job_title, String vacancy_no, String education_id, String experience, String deadline, String type_id, String job_description, String job_specification, String datetime, String jobtype_id, String city_id, String area_name, String job_type, String type_name, String company_name, String company_address, String companynature_id, String ownership_id, String website, String contact_person, String office_no, String mobile_no, String email, String opt_email, String username, String password, String logo, String ownership_name, String city_name, String education_name, String company_nature) {
        this.status = status;
        this.job_id = job_id;
        this.employer_id = employer_id;
        this.job_title = job_title;
        this.vacancy_no = vacancy_no;
        this.education_id = education_id;
        this.experience = experience;
        this.deadline = deadline;
        this.type_id = type_id;
        this.job_description = job_description;
        this.job_specification = job_specification;
        this.datetime = datetime;
        this.jobtype_id = jobtype_id;
        this.city_id = city_id;
        this.area_name = area_name;
        this.job_type = job_type;
        this.type_name = type_name;
        this.company_name = company_name;
        this.company_address = company_address;
        this.companynature_id = companynature_id;
        this.ownership_id = ownership_id;
        this.website = website;
        this.contact_person = contact_person;
        this.office_no = office_no;
        this.mobile_no = mobile_no;
        this.email = email;
        this.opt_email = opt_email;
        this.username = username;
        this.password = password;
        this.logo = logo;
        this.ownership_name = ownership_name;
        this.city_name = city_name;
        this.education_name = education_name;
        this.company_nature = company_nature;
    }
//    private String status;
//    private String  message;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
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

    public String getVacancy_no() {
        return vacancy_no;
    }

    public void setVacancy_no(String vacancy_no) {
        this.vacancy_no = vacancy_no;
    }

    public String getEducation_id() {
        return education_id;
    }

    public void setEducation_id(String education_id) {
        this.education_id = education_id;
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

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
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

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getJobtype_id() {
        return jobtype_id;
    }

    public void setJobtype_id(String jobtype_id) {
        this.jobtype_id = jobtype_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
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

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getCompanynature_id() {
        return companynature_id;
    }

    public void setCompanynature_id(String companynature_id) {
        this.companynature_id = companynature_id;
    }

    public String getOwnership_id() {
        return ownership_id;
    }

    public void setOwnership_id(String ownership_id) {
        this.ownership_id = ownership_id;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getOffice_no() {
        return office_no;
    }

    public void setOffice_no(String office_no) {
        this.office_no = office_no;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpt_email() {
        return opt_email;
    }

    public void setOpt_email(String opt_email) {
        this.opt_email = opt_email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getOwnership_name() {
        return ownership_name;
    }

    public void setOwnership_name(String ownership_name) {
        this.ownership_name = ownership_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getEducation_name() {
        return education_name;
    }

    public void setEducation_name(String education_name) {
        this.education_name = education_name;
    }

    public String getCompany_nature() {
        return company_nature;
    }

    public void setCompany_nature(String company_nature) {
        this.company_nature = company_nature;
    }
}

