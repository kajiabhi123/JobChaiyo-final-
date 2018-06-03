package com.example.designmodal.jobchaiyo.DataManager;

import com.example.designmodal.jobchaiyo.Model.JobAttributes;
import com.example.designmodal.jobchaiyo.Model.ListofJobCategory;
import com.example.designmodal.jobchaiyo.Model.ListofJobEducation;
import com.example.designmodal.jobchaiyo.Model.ListofJobLocation;
import com.example.designmodal.jobchaiyo.Model.ListofJobOwnership;
import com.example.designmodal.jobchaiyo.Model.ListofJobTypes;
import com.example.designmodal.jobchaiyo.Model.ListofJobsPostedbyEmployer;
import com.example.designmodal.jobchaiyo.Model.ListofPayment;
import com.example.designmodal.jobchaiyo.Model.SearchedJobs;
import com.example.designmodal.jobchaiyo.Model.User;
import com.example.designmodal.jobchaiyo.Model.reqJob;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by compware on 5/15/2018.
 */

public interface ApiInterface
{
    @GET("postJobs.php")
    Call<List<JobAttributes>> getJobInfo();


//    @POST("ApplyNow.php")
//    @FormUrlEncoded
//    Call<ApplyNowModel> PerformApplyNow(@Field("job_id") String job_id, @Field("email") String AppliedEmail, @Field("full_name")
//            String AppliedName, @Field("contact") String AppliedPhone,
//                                        @Part("uploaded_file") RequestBody file, @Field("AppliedDateTime") String AppliedDateTime);


    //prem's portion
        @POST("contact.php")//this os a interface where we create base to hit url
        @FormUrlEncoded
        Call<String> getStringScaler(@Field("fullname") String name, @Field("email") String email,
                                     @Field("contact_no") String contact_no, @Field("msg") String msg);




    //Kuldip portion start
    @POST("job_posted_by_employer.php")
    @FormUrlEncoded
     Call<List<ListofJobsPostedbyEmployer>> listofJobsPostedbyEmployer(@Field("employer_id") String id);

    @POST("search.php")
    @FormUrlEncoded
    public  Call<List<SearchedJobs>> sendSelectedJob(@Field("jobSearch") String Search,
                                                     @Field("jobCategory") String Category,
                                                     @Field("jobEducation") String Education,
                                                     @Field("jobType") String Type,
                                                     @Field("jobLocation") String Location,
                                                     @Field("jobOwnership") String Ownership);



    //manjil portion start



    @POST("reqJob.php")
    @FormUrlEncoded
    Call<reqJob> performReqJob(@Field("id") String Id, @Field("title") String Title,
                               @Field("vacancy") String Vacancy, @Field("experience") String Experience,
                               @Field("area") String Area, @Field("description") String Description,
                               @Field("specification") String Specification, @Field("education") Integer Education,
                               @Field("city") Integer City, @Field("type") Integer Type,
                               @Field("payment") Integer Payment);


    @POST("register.php")
    @FormUrlEncoded
    Call<User> performRegistration(@Field("name") String Name, @Field("address") String Address,
                                   @Field("website") String Website, @Field("person") String Person,
                                   @Field("office") String Office, @Field("mobile") String Mobile,
                                   @Field("email") String Email, @Field("optemail") String Optemail,
                                   @Field("username") String Username, @Field("password") String Password,
                                   @Field("category") Integer JobCategoy, @Field("ownership") Integer JobOwnership);
    //  @Field("title") String Title, @Field("image") String Image);

//
//    @POST("upload.php")
//    @FormUrlEncoded
//    Call<ImageClass> uploadImage(@Field("title") String title, @Field("image") String image);


    @POST("login.php")
    @FormUrlEncoded
    Call<User> performUserLogin(@Field("email") String Email,@Field("password") String Password);

    @GET("job_ownership_list.php")
    Call<List<ListofJobOwnership>> getJobOwnershipList();

    @GET("job_category_list.php")
    Call<List<ListofJobCategory>> getJobCategoryList();


    @GET("list_education.php")
    Call<List<ListofJobEducation>> getListofEducation();

    @GET("job_type_list.php")
    Call<List<ListofJobTypes>> getListofJobType();

    @GET("job_location_list.php")
    Call<List<ListofJobLocation>> getListofJobLocation();

    @GET("list_payment.php")
    Call<List<ListofPayment>> getListofPayment();
}
