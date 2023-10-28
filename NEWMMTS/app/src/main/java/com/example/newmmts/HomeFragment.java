package com.example.newmmts;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        LinearLayout training_btn =  (LinearLayout) v.findViewById(R.id.training_layout);
        training_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Training", Toast.LENGTH_SHORT).show();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new TrainingFragment());
                fr.commit();
            }
        });

        LinearLayout diag_btn =  (LinearLayout) v.findViewById(R.id.diag_layout);
        diag_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Diagnostic", Toast.LENGTH_SHORT).show();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new DiagnosticFragment());
                fr.commit();
            }
        });

        LinearLayout result_btn =  (LinearLayout) v.findViewById(R.id.results_layout);
        result_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Results", Toast.LENGTH_SHORT).show();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new ResultFragment());
                fr.commit();
            }
        });

        LinearLayout IP_btn =  (LinearLayout) v.findViewById(R.id.IP_layout);
        IP_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "IP Address", Toast.LENGTH_SHORT).show();

            }
        });

        LinearLayout about_btn =  (LinearLayout) v.findViewById(R.id.about_layout);
        about_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "About US", Toast.LENGTH_SHORT).show();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new AboutFragment());
                fr.commit();
            }
        });

        LinearLayout logout_btn =  (LinearLayout) v.findViewById(R.id.logout_layout);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Logged Out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), loginActivity.class);

                startActivity(intent);
                getActivity().finish();
            }
        });
        return v;
    }


}