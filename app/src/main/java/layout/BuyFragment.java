package layout;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class BuyFragment extends Fragment {

    ListView buyListView;
    private ProgressDialog pDialog;
    JSONArray array;

    public BuyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_buy, container, false);

        buyListView = (ListView) v.findViewById(R.id.buy_listview);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);

        getData();

        return v;
    }

    private void getData() {
        showDialog();
        FormEncodingBuilder formBody = new FormEncodingBuilder();

        new APIRequest(getActivity(), formBody, AppConfig.URL_GET_BUY, new APIRequest.APIResponse() {
            @Override
            public void onSuccess(String result) {
                hideDialog();
                setupListView(result);

            }

            @Override
            public void onError(String error) {
                hideDialog();
                Toast.makeText(getActivity(), "Error " + error, Toast.LENGTH_LONG).show();

            }
        }).execute();
    }

    private void setupListView(String result){

        try {
            array = new JSONArray(result);
            buyListView.setAdapter(new BuyListViewAdapter(getActivity(), array));

            buyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    BuyInfoFragment f = new BuyInfoFragment();
                    f.setData(i, array );
                    selectFragment(f);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
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
