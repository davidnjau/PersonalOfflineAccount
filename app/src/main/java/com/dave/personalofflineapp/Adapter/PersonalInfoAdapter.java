package com.dave.personalofflineapp.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dave.personalofflineapp.Pojo.PersonalDetails;
import com.dave.personalofflineapp.R;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;

public class PersonalInfoAdapter extends RecyclerView.Adapter<PersonalInfoAdapter.ViewHolder>{

    private SharedPreferences preferences;

    private Context context;
    private ArrayList<PersonalDetails> PersonalDetailsArrayList;


    public PersonalInfoAdapter(Context context, ArrayList<PersonalDetails> PersonalDetailsArrayList) {
        this.context = context;
        this.PersonalDetailsArrayList = PersonalDetailsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_data, parent, false);
        ViewHolder holder = new ViewHolder(view);



        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.tvFName.setText(PersonalDetailsArrayList.get(position).getmFirstName());
        holder.tvLName.setText(PersonalDetailsArrayList.get(position).getmLastName());
        holder.tvPhone.setText(PersonalDetailsArrayList.get(position).getmPhone());

        byte [] image = PersonalDetailsArrayList.get(position).getmImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        System.out.println("-*-* "+ bitmap);
//        holder.imageView.setImageBitmap(bitmap);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }



    @Override
    public int getItemCount() {

        return PersonalDetailsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvFName, tvPhone, tvLName;

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);

            tvFName = itemView.findViewById(R.id.tvFName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvLName = itemView.findViewById(R.id.tvLName);

        }
    }


}
