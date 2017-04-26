package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.var_app.var_app.R;
import com.var_app.var_app.helper.UserInfo;


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


        txtName = (TextView) v.findViewById(R.id.name);
        txtEmail = (TextView) v.findViewById(R.id.email);
        //usernameview = (TextView) findViewById(R.id.userview);

        txtName.setText(UserInfo.getFullName());
        txtEmail.setText(UserInfo.getEmail());

        return v;
    }

}
