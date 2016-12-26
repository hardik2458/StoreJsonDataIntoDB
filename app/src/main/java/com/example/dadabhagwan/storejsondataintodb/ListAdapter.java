package com.example.dadabhagwan.storejsondataintodb;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dadabhagwan on 11/16/2016.
 */

class ListAdapter extends BaseAdapter
{

    Context context;
    List<Actors> actorses;

    public ListAdapter(List<Actors> actorses, Context context)
    {
        this.context = context;
        this.actorses = actorses;

    }

    @Override
    public int getCount()
    {
        return this.actorses.size();
    }

    @Override
    public Actors getItem(int position)
    {
        return this.actorses.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewItem viewItem = null;
        if(convertView == null)
        {
            viewItem = new ViewItem();
            LayoutInflater layoutInfiater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            //LayoutInflater layoutInfiater = LayoutInflater.from(context);
            convertView = layoutInfiater.inflate(R.layout.raw_layout, null);

            viewItem.image = (ImageView) convertView.findViewById(R.id.ivImage);
            viewItem.name = (TextView)convertView.findViewById(R.id.tvName);
            viewItem.descr= (TextView) convertView.findViewById(R.id.tvDescriptionn);
            viewItem.dob= (TextView) convertView.findViewById(R.id.tvDateOfBirth);
            viewItem.country= (TextView) convertView.findViewById(R.id.tvCountry);
            viewItem.height= (TextView) convertView.findViewById(R.id.tvHeight);
            viewItem.spouse= (TextView) convertView.findViewById(R.id.tvSpouse);
            viewItem.child= (TextView) convertView.findViewById(R.id.tvChildren);
            convertView.setTag(viewItem);
        }
        else
        {
            viewItem = (ViewItem) convertView.getTag();
        }

        viewItem.name.setText(actorses.get(position).getName());
        viewItem.descr.setText(actorses.get(position).getDescription());
        viewItem.dob.setText(actorses.get(position).getDob());
        viewItem.country.setText(actorses.get(position).getCountry());
        viewItem.height.setText(actorses.get(position).getHeight());
        viewItem.spouse.setText(actorses.get(position).getSpouse());
        viewItem.child.setText(actorses.get(position).getChildren());
        Picasso.with(context).load(actorses.get(position).getImage()).into(viewItem.image);



        return convertView;
    }
}

class ViewItem
{
    ImageView image;
    TextView name;
    TextView descr;
    TextView dob;
    TextView country;
    TextView height;
    TextView spouse;
    TextView child;
}

