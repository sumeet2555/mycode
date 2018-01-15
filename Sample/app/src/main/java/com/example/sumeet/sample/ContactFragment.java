package com.example.sumeet.sample;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.plus.PlusOneButton;

import java.nio.file.Files;

public class ContactFragment extends Fragment implements View.OnClickListener{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        Button send =  (Button) view.findViewById(R.id.submit);
        send.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onClick(View view) {
        EditText subject = (EditText) getActivity().findViewById(R.id.contact_subject);
        EditText message = (EditText) getActivity().findViewById(R.id.message);
        EditText name = (EditText) getActivity().findViewById(R.id.your_name);
        EditText phone = (EditText) getActivity().findViewById(R.id.your_phone);
        EditText email = (EditText) getActivity().findViewById(R.id.your_email);
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.SpinnerFeedbackType);
        switch (view.getId()){
            case R.id.submit:

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"info@saiyaaraassociates.in"});
                i.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString() + " " + String.valueOf(spinner.getSelectedItem()));
                i.putExtra(Intent.EXTRA_TEXT   , String.valueOf(spinner.getSelectedItem()) + "\n \n"+message.getText().toString() + " \n Your Details: \n Name: " +  name.getText().toString() + "\n Your email: " + email.getText().toString() +" \n Your mobile: "+ phone.getText().toString());

                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
                getActivity().onBackPressed();
                break;

        }
    }
}
