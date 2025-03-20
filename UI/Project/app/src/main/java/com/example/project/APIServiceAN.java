package com.example.project;

import retrofit2.Call;
import retrofit2.http.POST;

public interface APIServiceAN {
    @POST("")
    Call register(AccountRequest accountRequest);
}
