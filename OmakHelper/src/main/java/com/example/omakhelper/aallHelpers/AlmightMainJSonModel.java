package com.example.omakhelper.aallHelpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.List;

public class AlmightMainJSonModel {

    @SerializedName("client_id")
    @Expose
    private String client_id;

    @SerializedName("mail_for")
    @Expose
    private String mail_for;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("client_role")
    @Expose
    private String client_role;

    @SerializedName("role")
    @Expose
    private String role;

    @SerializedName("udid")
    @Expose
    private String udid;

    @SerializedName("apptoken")
    @Expose
    private String apptoken;

    @SerializedName("app_version")
    @Expose
    private String app_version;

    @SerializedName("referrer")
    @Expose
    private String referrer;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("first_name")
    @Expose
    private String first_name;

    @SerializedName("last_name")
    @Expose
    private String last_name;

    @SerializedName("useremail")
    @Expose
    private String useremail;

    @SerializedName("userphone")
    @Expose
    private String userphone;

    @SerializedName("subscription_id")
    @Expose
    private String subscriptionId;

    @SerializedName("business_id")
    @Expose
    private String businessId;

    @SerializedName("order_id")
    @Expose
    private String order_id;

    @SerializedName("file")
    @Expose
    private String image;

    @SerializedName("msg_name")
    @Expose
    private String msg_name;

    @SerializedName("msg_body")
    @Expose
    private String msg_body;

    @SerializedName("created_by")
    @Expose
    private String created_by;

    @SerializedName("msg_id")
    @Expose
    private String msg_id;

    @SerializedName("contact_id")
    @Expose
    private String contact_id;

    @SerializedName("userpass")
    @Expose
    private String userpass;

    @SerializedName("chosen_plan")
    @Expose
    private String chosen_plan;

    @SerializedName("chosen_plan_price")
    @Expose
    private String chosen_plan_price;

    @SerializedName("call_logs_limit")
    @Expose
    private String call_logs_limit;

    @SerializedName("login_id")
    @Expose
    private String login_id;

    @SerializedName("accept_terms")
    @Expose
    private Boolean accept_terms;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("team_member_id")
    @Expose
    private String team_member_id;

    @SerializedName("authtoken")
    @Expose
    private String authtoken;

    @SerializedName("device")
    @Expose
    private String device;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("call_id")
    @Expose
    private String callId;

    @SerializedName("new_userpass")
    @Expose
    private String newUserpass;

    @SerializedName("view_all_calls")
    @Expose
    private Boolean view_all_calls;

    @SerializedName("view_admin_calls")
    @Expose
    private Boolean view_admin_calls;

    @SerializedName("view_all_messages")
    @Expose
    private Boolean view_all_messages;

    @SerializedName("view_admin_messages")
    @Expose
    private Boolean view_admin_messages;

    @SerializedName("razorpay_order_id")
    @Expose
    private String razorpay_order_id;

    @SerializedName("razorpay_payment_id")
    @Expose
    private String razorpay_payment_id;

    @SerializedName("razorpay_signature")
    @Expose
    private String razorpay_signature;

    @SerializedName("keys")
    @Expose
    private JSONArray searchKeys;

    @SerializedName("otp")
    @Expose
    private String otp;

    @SerializedName("force_login")
    @Expose
    private String force_login;

    @SerializedName("phones")
    @Expose
    private String phone;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("notify_phones")
    @Expose
    private String notify_phone;

    @SerializedName("notify_emails")
    @Expose
    private String notify_email;

    @SerializedName("number")
    @Expose
    private String number;

    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("caller_id")
    @Expose
    private String callerId;

    @SerializedName("call_duration")
    @Expose
    private String callDuration;

    @SerializedName("destination")
    @Expose
    private String destination;

    @SerializedName("start_time")
    @Expose
    private String startTime;

    @SerializedName("end_time")
    @Expose
    private String endTime;

    @SerializedName("timezone")
    @Expose
    private String timezone;

    @SerializedName("hangup_cause")
    @Expose
    private String hangupCause;

    @SerializedName("longitude")
    @Expose
    private String longitude;

    @SerializedName("latitude")
    @Expose
    private String Latitude;

    @SerializedName("group_id")
    @Expose
    private String groupId;

    @SerializedName("lead_type")
    @Expose
    private String leadType;

    @SerializedName("key")
    @Expose
    private String key;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("remarks")
    @Expose
    private String remarks;

    @SerializedName("country_code")
    @Expose
    private String country_code;

    @SerializedName("resident")
    @Expose
    private String resident;

    @SerializedName("contact")
    @Expose
    private String contact;

    @SerializedName("project_id")
    @Expose
    private String projectId;

    @SerializedName("service_chosen")
    @Expose
    private String service_chosen;

    @SerializedName("academic_level")
    @Expose
    private String academic_level;

    @SerializedName("branch")
    @Expose
    private String branch;

    @SerializedName("no_of_pages")
    @Expose
    private String no_of_pages;

    @SerializedName("last_fetched_id")
    @Expose
    private Integer last_fetched_id;
    @SerializedName("ids")
    @Expose
    private List<String> calculation_ids;
    @SerializedName("calculation_ids")
    @Expose
    private List<String> calculationList;

    public Integer getLast_fetched_id() {
        return last_fetched_id;
    }

    public void setLast_fetched_id(Integer last_fetched_id) {
        this.last_fetched_id = last_fetched_id;
    }

    public String getService_chosen() {
        return service_chosen;
    }

    public void setService_chosen(String service_chosen) {
        this.service_chosen = service_chosen;
    }

    public String getAcademic_level() {
        return academic_level;
    }

    public void setAcademic_level(String academic_level) {
        this.academic_level = academic_level;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getNo_of_pages() {
        return no_of_pages;
    }

    public void setNo_of_pages(String no_of_pages) {
        this.no_of_pages = no_of_pages;
    }

    public List<String> getCalculationList() {
        return calculationList;
    }

    public void setCalculationList(List<String> calculationList) {
        this.calculationList = calculationList;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<String> getCalculation_ids() {
        return calculation_ids;
    }

    public void setCalculation_ids(List<String> calculation_ids) {
        this.calculation_ids = calculation_ids;
    }

    public String getMail_for() {
        return mail_for;
    }

    public void setMail_for(String mail_for) {
        this.mail_for = mail_for;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getResident() {
        return resident;
    }

    public void setResident(String resident) {
        this.resident = resident;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getClient_role() {
        return client_role;
    }

    public void setClient_role(String client_role) {
        this.client_role = client_role;
    }

    public String getNotify_phone() {
        return notify_phone;
    }

    public void setNotify_phone(String notify_phone) {
        this.notify_phone = notify_phone;
    }

    public String getNotify_email() {
        return notify_email;
    }

    public void setNotify_email(String notify_email) {
        this.notify_email = notify_email;
    }

    public String getTeam_member_id() {
        return team_member_id;
    }

    public void setTeam_member_id(String team_member_id) {
        this.team_member_id = team_member_id;
    }

    public String getForce_login() {
        return force_login;
    }

    public void setForce_login(String force_login) {
        this.force_login = force_login;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMsg_name() {
        return msg_name;
    }

    public void setMsg_name(String msg_name) {
        this.msg_name = msg_name;
    }

    public String getMsg_body() {
        return msg_body;
    }

    public void setMsg_body(String msg_body) {
        this.msg_body = msg_body;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getContact_id() {
        return contact_id;
    }

    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getApptoken() {
        return apptoken;
    }

    public void setApptoken(String apptoken) {
        this.apptoken = apptoken;
    }

    public String getChosen_plan() {
        return chosen_plan;
    }

    public void setChosen_plan(String chosen_plan) {
        this.chosen_plan = chosen_plan;
    }

    public String getChosen_plan_price() {
        return chosen_plan_price;
    }

    public void setChosen_plan_price(String chosen_plan_price) {
        this.chosen_plan_price = chosen_plan_price;
    }

    public String getCall_logs_limit() {
        return call_logs_limit;
    }

    public void setCall_logs_limit(String call_logs_limit) {
        this.call_logs_limit = call_logs_limit;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public Boolean getAccept_terms() {
        return accept_terms;
    }

    public void setAccept_terms(Boolean accept_terms) {
        this.accept_terms = accept_terms;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getNewUserpass() {
        return newUserpass;
    }

    public void setNewUserpass(String newUserpass) {
        this.newUserpass = newUserpass;
    }

    public Boolean getView_all_calls() {
        return view_all_calls;
    }

    public void setView_all_calls(Boolean view_all_calls) {
        this.view_all_calls = view_all_calls;
    }

    public Boolean getView_admin_calls() {
        return view_admin_calls;
    }

    public void setView_admin_calls(Boolean view_admin_calls) {
        this.view_admin_calls = view_admin_calls;
    }

    public Boolean getView_all_messages() {
        return view_all_messages;
    }

    public void setView_all_messages(Boolean view_all_messages) {
        this.view_all_messages = view_all_messages;
    }

    public Boolean getView_admin_messages() {
        return view_admin_messages;
    }

    public void setView_admin_messages(Boolean view_admin_messages) {
        this.view_admin_messages = view_admin_messages;
    }

    public String getRazorpay_order_id() {
        return razorpay_order_id;
    }

    public void setRazorpay_order_id(String razorpay_order_id) {
        this.razorpay_order_id = razorpay_order_id;
    }

    public String getRazorpay_payment_id() {
        return razorpay_payment_id;
    }

    public void setRazorpay_payment_id(String razorpay_payment_id) {
        this.razorpay_payment_id = razorpay_payment_id;
    }

    public String getRazorpay_signature() {
        return razorpay_signature;
    }

    public void setRazorpay_signature(String razorpay_signature) {
        this.razorpay_signature = razorpay_signature;
    }

    public JSONArray getSearchKeys() {
        return searchKeys;
    }

    public void setSearchKeys(JSONArray searchKeys) {
        this.searchKeys = searchKeys;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getHangupCause() {
        return hangupCause;
    }

    public void setHangupCause(String hangupCause) {
        this.hangupCause = hangupCause;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getLeadType() {
        return leadType;
    }

    public void setLeadType(String leadType) {
        this.leadType = leadType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
