package mobile.s.todogoals.home.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobile.s.todogoals.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddingNewToDoFragment extends Fragment {


    public AddingNewToDoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adding_new_to_do, container, false);
    }

}