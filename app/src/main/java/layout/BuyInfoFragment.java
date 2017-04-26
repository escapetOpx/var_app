package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.var_app.var_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuyInfoFragment extends Fragment {

    TextView trashTypeText; //textView2023
    TextView weightText; //textView58007
    TextView priceText; //textView580104 TextView totalText; //textView50104
    TextView nameText; //textView501054
    TextView provinceText; //textView500174
    TextView totalText;

    TextView detailText;
    TextView dateText;

    public int buyIndex;
    public JSONArray jsonArray;

    public BuyInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_buy_info, container, false);

        trashTypeText = (TextView) v.findViewById(R.id.buy_info_trashtype);
        weightText = (TextView) v.findViewById(R.id.buy_info_weight);
        totalText = (TextView) v.findViewById(R.id.buy_info_total);
        priceText = (TextView) v.findViewById(R.id.buy_info_price);
        nameText = (TextView) v.findViewById(R.id.buy_info_name);
        provinceText = (TextView) v.findViewById(R.id.buy_info_province);

        detailText = (TextView) v.findViewById(R.id.buy_info_details);
        dateText = (TextView) v.findViewById(R.id.buy_info_date);

        try {

            JSONObject object = jsonArray.getJSONObject(buyIndex);

            double weight = object.getDouble("dealing_totalweight");
            double price = object.getDouble("dealing_netprice/kg");
            double total = weight * price;

            trashTypeText.setText(object.getString("trashtype_name"));
            weightText.setText(String.valueOf(weight));
            priceText.setText(String.valueOf(price));
            nameText.setText(object.getString("user_name"));
            provinceText.setText(object.getString("dealing_address"));
            totalText.setText(String.valueOf(total));

            detailText.setText(object.getString("dealing_details"));
            dateText.setText(object.getString("dealing_date"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return v;
    }

    public void setData(int buyIndex, JSONArray array) {
        this.buyIndex = buyIndex;
        this.jsonArray = array;
    }

}
