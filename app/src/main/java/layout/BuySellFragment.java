package layout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.var_app.var_app.BuyActivity;
import com.var_app.var_app.R;
import com.var_app.var_app.SellActivity;
import com.var_app.var_app.SellBuyActivity;

import static android.R.attr.fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class BuySellFragment extends Fragment {
    private ImageButton sellButton, buyButton;
    private Fragment fragment;


    public BuySellFragment() {
        // Required empty public constructor
    }

    private void selectFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("Back").commit();
        manager.executePendingTransactions();
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_buy_sell, container, false);

        sellButton = (ImageButton) v.findViewById(R.id.sellButton);
        buyButton = (ImageButton) v.findViewById(R.id.buyButton);



        sellButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View v){
        fragment = new SellFragment();
        selectFragment(fragment);

    }
    });

        buyButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View v){
            fragment = new BuyFragment();
            selectFragment(fragment);

    }
    });
    // Inflate the layout for this fragment
            return v;
}


}

