package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.var_app.var_app.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BarcodeCalculatorFragment extends Fragment {


    public BarcodeCalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_barcode_calculator, container, false);
    }

}
