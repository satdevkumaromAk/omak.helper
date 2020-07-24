package com.example.omakhelper.aallHelpers;

import android.content.Context;

import com.example.omakhelper.aallHelpers.Retofit.ApiClient;
import com.example.omakhelper.aallHelpers.Retofit.ApiHelper;
import com.example.omakhelper.aallHelpers.Retofit.ApiInterface;
import com.kaopiz.kprogresshud.KProgressHUD;
import io.realm.Realm;
public class ApiUtils {
    public PreferenceFile preferenceFile;
    private Context context;
    private Realm realm;

    public ApiUtils(Context context) {
        this.context = context;
        realm =HelperFunctions.getRealm("messages", context);
    }

    public void serviceGetEmailOptions(final Context context, boolean forceForUpdate) {
        preferenceFile = new PreferenceFile();
        this.context = context;

        final KProgressHUD kProgressHUD = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(1)
                .setDimAmount(0.2f)
                .show();

        AlmightMainJSonModel task = new ApiClient().prepareMainJsModel("ap-admin", context);

        ApiInterface apiService = new ApiClient().getClient().create(ApiInterface.class);

//
//        Call<ResponseGetEmailDataModel> call = apiService.getEmailData(task);
//        ApiHelper.enqueueWithRetryWithoutDialog(context, call, new Callback<ResponseGetEmailDataModel>() {
//            @Override
//            public void onResponse(@NotNull Call<ResponseGetEmailDataModel> call, @NotNull Response<ResponseGetEmailDataModel> response) {
//                if (response.isSuccessful()) {
//                    if (kProgressHUD.isShowing()) {
//                        kProgressHUD.dismiss();
//                    }
//
//                    Alerts.toast(context, response.body() != null ? response.body().getMessage() : null);
//                    final List<RealmEmailOptionsModel> realmEmailOptionsModels = response.body().getData();
//                    realm.executeTransaction(new Realm.Transaction() {
//                        @Override
//                        public void execute(@NotNull Realm realm) {
//                            realm.insertOrUpdate(realmEmailOptionsModels);
//                        }
//                    });
//                    new RealmHelpers(context).setBooleanFlag(RealmHelpers.areEmailsUpdated, true);
//                }
//            }
//
//            @Override
//            public void onFailure(@NotNull Call<ResponseGetEmailDataModel> call, Throwable t) {
//                if (kProgressHUD.isShowing()) {
//                    kProgressHUD.dismiss();
//                }
//            }
//        });
    }


}