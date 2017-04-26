package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.var_app.var_app.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatorFragment extends Fragment {

    private Spinner typeSpinner;
    EditText weightEdittext;
    Button calButton;
    TextView valueTextview;

    public CalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calculator, container, false);


        weightEdittext = (EditText) v.findViewById(R.id.wet);
        calButton = (Button) v.findViewById(R.id.calbutton);
        valueTextview = (TextView) v.findViewById(R.id.valuee);



        ArrayList<String> typeList = new ArrayList<>();
        final ArrayList<Integer> priceList = new ArrayList<>();

        typeList.add("กระดาษขาวดำ");
        typeList.add("กระดาษย่อย");
        typeList.add("หนังสือพิมพ์");
        typeList.add("PET ใส");
        typeList.add("PET สี");
        typeList.add("อลูมิเนียมกระป๋อง");
        priceList.add(13);
        priceList.add(3);
        priceList.add(8);
        priceList.add(13);
        priceList.add(1);
        priceList.add(38);

        typeSpinner = (Spinner) v.findViewById(R.id.tspinner);
        typeSpinner.setAdapter(new ArrayAdapter<>(
                getActivity(),
                R.layout.support_simple_spinner_dropdown_item,
                typeList
        ));

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                Log.i("position : ", String.valueOf(position));
                Log.i("price", String.valueOf(priceList.get(position)));
                calButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!weightEdittext.getText().toString().equals("")) {
                            int weight = Integer.parseInt(weightEdittext.getText().toString());
                            int i = weight * priceList.get(position);
                            Log.i("SUM", String.valueOf(i));
                            valueTextview.setText(String.valueOf(i));
                        } else {
                            Toast.makeText(getActivity(),
                                    "Please enter the weight!", Toast.LENGTH_LONG)
                                    .show();
                        }

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }
}