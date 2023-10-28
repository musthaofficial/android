package com.example.newmmts;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class TrainingFragment extends Fragment {
    AutoCompleteTextView autoCompleteTextView;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_training, container, false);
        // Inflate the layout for this fragment
        autoCompleteTextView=v.findViewById(R.id.autoCompleteTextView);

       // autoCompleteTextView = autoCompleteTextView.findViewById(0,"autoCompleteTextView");

        //We will use this data to inflate the drop-down items
        String[] Subjects = new String[]{"Low", "High", "Medium"};

        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.dropdown_item, Subjects);
        autoCompleteTextView.setAdapter(adapter);

        //to get selected value add item click listener
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "" + autoCompleteTextView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        radioGroup = v.findViewById(R.id.radio_speed);
        btnDisplay = v.findViewById(R.id.button_sbmt);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.low:
                        // do operations specific to this selection
                        Toast.makeText(getActivity(),
                                "Loe", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.medium:
                        // do operations specific to this selection
                        Toast.makeText(getActivity(), "Medium", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.high:
                        // do operations specific to this selection
                        Toast.makeText(getActivity(), "Hight", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup


            }

        });
        return v;


    }


}