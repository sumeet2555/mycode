package com.example.sumeet.sample;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.transition.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Locale;


public class AboutUsFragment extends Fragment implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        Button dropMessage =  (Button) view.findViewById(R.id.drop_message);
        dropMessage.setOnClickListener(this);
        CardView map = view.findViewById(R.id.map_address);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 28.5086195,77.2495656);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                try{
                    startActivity(Intent.createChooser(intent, "Open Address"));
                }catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There is no Google Map.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CardView mail = view.findViewById(R.id.mail_contact);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"info@saiyaaraassociates.in"});

                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        LinearLayout phone1 = view.findViewById(R.id.phone_1);
        phone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:+91-8700004551"));
                try{
                    startActivity(callIntent);
                } catch (Exception e){
                    Toast.makeText(getActivity(),"Sorry Could not call on this number", Toast.LENGTH_SHORT).show();
                }

            }
        });
        LinearLayout phone2 = view.findViewById(R.id.phone_2);
        phone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:011-26044266"));
                try{
                    startActivity(callIntent);
                } catch (Exception e){
                    Toast.makeText(getActivity(),"Sorry Could not call on this number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        LinearLayout phone3 = view.findViewById(R.id.phone_2);
        phone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:9999131820"));
                try{
                    startActivity(callIntent);
                } catch (Exception e){
                    Toast.makeText(getActivity(),"Sorry Could not call on this number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
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
        switch (view.getId()){
            case R.id.drop_message:
                ContactFragment contactFragment = ((MainActivity)getActivity()).getContactFragment();
                View aboutUs = getActivity().findViewById(R.id.about_us);
                aboutUs.setVisibility(View.GONE);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, contactFragment).commit();
                getActivity().getSupportFragmentManager().popBackStack();
                break;

        }
    }
}
