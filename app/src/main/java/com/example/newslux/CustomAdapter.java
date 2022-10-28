package com.example.newslux;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    Context c;

    public CustomAdapter(Context c) {
        this.c = c;
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        return new ViewHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(c);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://newsdata.io/api/1/news?apikey=pub_11221b23adf539a20f080a3f90b161d554541&q=news&country=au,ca,in,gb,us&language=en&category=entertainment,environment,food,health,science",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(position);

                        holder.getTextView1().setText(jsonObject.getString("title"));
                        Glide.with(c).load(jsonObject.getString("image_url")).into(holder.getImageView1());

                        JSONArray author = jsonObject.getJSONArray("creator");
                        JSONArray country = jsonObject.getJSONArray("country");

                        holder.cardView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(view.getContext(),ShowNews.class);

                                try {
                                    intent.putExtra("desc",jsonObject.getString("content"));
                                    intent.putExtra("title",jsonObject.getString("title"));
                                    intent.putExtra("author",author.get(0).toString());
                                    intent.putExtra("country",country.get(0).toString());
                                    intent.putExtra("image",jsonObject.getString("image_url"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                view.getContext().startActivity(intent);
                            }
                        });
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(c,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    @Override
    public int getItemCount() {
        return 70;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView1;
        private final ImageView imageView1;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView1 = (TextView) itemView.findViewById(R.id.textView);
            this.imageView1 = (ImageView) itemView.findViewById(R.id.imageView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }

        public TextView getTextView1() {
            return textView1;
        }
        public ImageView getImageView1() {
            return imageView1;
        }
        public CardView getCardView() {
            return cardView;
        }

    }
}
