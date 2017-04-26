package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.var_app.var_app.R;
import com.var_app.var_app.helper.APIRequest;
import com.var_app.var_app.helper.AppConfig;
import com.var_app.var_app.helper.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {


    private TextView txtName;
    private TextView txtEmail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_home_page, container, false);


        txtName = (TextView) v.findViewById(R.id.home_name_text);
        txtEmail = (TextView) v.findViewById(R.id.home_email_text);

        final TextView[] Hprices = new TextView[10];
        Hprices[0] = (TextView) v.findViewById(R.id.price1);
        Hprices[1] = (TextView) v.findViewById(R.id.price2);
        Hprices[2] = (TextView) v.findViewById(R.id.price3);
        Hprices[3] = (TextView) v.findViewById(R.id.price4);
        Hprices[4] = (TextView) v.findViewById(R.id.price5);
        Hprices[5] = (TextView) v.findViewById(R.id.price6);

        FormEncodingBuilder formBody = new FormEncodingBuilder();

        new APIRequest(getActivity(), formBody, AppConfig.URL_GET_TRASH_TYPE, new APIRequest.APIResponse() {
            @Override
            public void onSuccess(String result) {
                Log.i("look: ", result);
                try {

                    JSONArray jsonArray = new JSONArray(result);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Hprices[i].setText(jsonArray.getJSONObject(i).getString("price/kg"));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                Toast.makeText(getActivity(), "Error " + error, Toast.LENGTH_LONG).show();

            }
        }).execute();



        txtName.setText(UserInfo.getFullName());
        txtEmail.setText(UserInfo.getEmail());

        return v;
    }

}
