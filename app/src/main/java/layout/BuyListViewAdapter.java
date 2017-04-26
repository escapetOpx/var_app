package layout;

import android.content.Context;
import android.renderscript.Double2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.var_app.var_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BuyListViewAdapter extends BaseAdapter {

    Context context;
    JSONArray jsonArray;

    TextView trashTypeText; //textView2023
    TextView weightText; //textView58007
    TextView priceText; //textView580104 
    TextView totalText; //textView50104
    TextView nameText; //textView501054
    TextView provinceText; //textView500174

    public BuyListViewAdapter(Context context, JSONArray jsonArray){
        this.context = context;
        this.jsonArray = jsonArray;
    }

    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        if (convertView == null){
            convertView = inflater.inflate(R.layout.buy_list_item, parent, false);
        }

        trashTypeText = (TextView) convertView.findViewById(R.id.textView0203);
        weightText = (TextView) convertView.findViewById(R.id.textView58007);
        totalText = (TextView) convertView.findViewById(R.id.textView50104);
        priceText = (TextView) convertView.findViewById(R.id.textView580104);
        nameText = (TextView) convertView.findViewById(R.id.textView501054);
        provinceText = (TextView) convertView.findViewById(R.id.textView500174);

        try {

            JSONObject object = jsonArray.getJSONObject(i);

            double weight = object.getDouble("dealing_totalweight");
            double price = object.getDouble("dealing_netprice/kg");
            double total = weight * price;

            trashTypeText.setText(object.getString("trashtype_name"));
            weightText.setText(String.valueOf(weight));
            priceText.setText(String.valueOf(price));
            nameText.setText(object.getString("user_name"));
            provinceText.setText(object.getString("dealing_details"));
            totalText.setText(String.valueOf(total));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
