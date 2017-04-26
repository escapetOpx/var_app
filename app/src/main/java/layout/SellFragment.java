package layout;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.var_app.var_app.LoginActivity;
import com.var_app.var_app.MenuActivity;
import com.var_app.var_app.R;
import com.var_app.var_app.helper.APIRequest;
import com.var_app.var_app.helper.AppConfig;
import com.var_app.var_app.helper.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SellFragment extends Fragment {

    Spinner typeSpinner; //spinner56
    EditText priceEditText; //editText3
    EditText weightEditText; //editText2
    EditText detailEditText; //editText
    Spinner provinceSpinner; //spinner20
    EditText phoneEditText; //editText 312

    ProgressDialog pDialog;

    Button submitBtn;

    ArrayList<String> typeList = new ArrayList<>();
    ArrayList<String> typeIdList = new ArrayList<>();


    public SellFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sell, container, false);

        typeSpinner = (Spinner) v.findViewById(R.id.spinner56);
        priceEditText = (EditText) v.findViewById(R.id.editText3);
        weightEditText = (EditText) v.findViewById(R.id.editText2);
        detailEditText = (EditText) v.findViewById(R.id.editText);
        provinceSpinner = (Spinner) v.findViewById(R.id.spinner20);
        phoneEditText = (EditText) v.findViewById(R.id.editText312);
        submitBtn = (Button) v.findViewById(R.id.button);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
            }
        });

        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);

        initSpinner();

        return v;
    }

    private void  initSpinner(){

        FormEncodingBuilder formBody = new FormEncodingBuilder();


        new APIRequest(getActivity(), formBody, AppConfig.URL_GET_TRASH_TYPE, new APIRequest.APIResponse() {
            @Override
            public void onSuccess(String result) {
                hideDialog();

                try {

                    JSONArray jsonArray = new JSONArray(result);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        typeList.add(i, jsonArray.getJSONObject(i).getString("type_name"));
                        typeIdList.add(i,  jsonArray.getJSONObject(i).getString("trashtype_id"));
                    }

                    typeSpinner.setAdapter(new ArrayAdapter<>(
                            getActivity(),
                            R.layout.support_simple_spinner_dropdown_item,
                            typeList
                    ));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                hideDialog();
                Toast.makeText(getActivity(), "Error " + error, Toast.LENGTH_LONG).show();

            }
        }).execute();

    }

    private void submitData(){

        String type = typeIdList.get(typeSpinner.getSelectedItemPosition());
        String price = priceEditText.getText().toString().trim();
        String weight = weightEditText.getText().toString().trim();
        String details = detailEditText.getText().toString().trim();
        String province = provinceSpinner.getSelectedItem().toString().trim();
        String phone = phoneEditText.getText().toString().trim();

        if (!type.equals("") && !price.equals("") && !weight.equals("") && !details.equals("") && !province.equals("") && !phone.equals("")) {

            pDialog.setMessage("Loading ...");
            showDialog();

            FormEncodingBuilder formBody = new FormEncodingBuilder()
                    .add("user_id", UserInfo.getUserId())
                    .add("trashtype_id", type)
                    .add("dealing_netprice", price)
                    .add("dealing_totalweight", weight)
                    .add("dealing_details", details)
                    .add("dealing_address", province);


            new APIRequest(getActivity(), formBody, AppConfig.URL_SELL_CREATE, new APIRequest.APIResponse() {
                @Override
                public void onSuccess(String result) {
                    hideDialog();
                    selectFragment(new BuyFragment());

                }

                @Override
                public void onError(String error) {
                    hideDialog();
                    Toast.makeText(getActivity(), "Error " + error, Toast.LENGTH_LONG).show();

                }
            }).execute();

        }else{

            Toast.makeText(getActivity(), "Please input data", Toast.LENGTH_SHORT).show();

        }

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void selectFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("Back").commit();
        manager.executePendingTransactions();
    }

}
