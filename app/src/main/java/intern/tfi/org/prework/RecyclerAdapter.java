package intern.tfi.org.prework;

import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by basu on 4/16/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<VolunteeringOpportunities> volunteerList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView titleTextView,cityTextView,areaTextView,startTextView,endTextView,timeTextView,typeTextView,descriptionTextView;
        public MyViewHolder(View view){
            super(view);
            titleTextView=(TextView)view.findViewById(R.id.recycler_title_text_view);
            cityTextView=(TextView)view.findViewById(R.id.recycler_city_text_view);
            areaTextView=(TextView)view.findViewById(R.id.recycler_area_text_view);
            startTextView=(TextView)view.findViewById(R.id.recycler_start_text_view);
            endTextView=(TextView)view.findViewById(R.id.recycler_end_text_view);
            timeTextView=(TextView)view.findViewById(R.id.recycler_time_text_view);
            typeTextView=(TextView)view.findViewById(R.id.recycler_type_text_view);
            descriptionTextView=(TextView)view.findViewById(R.id.recycler_description_text_view);
        }
    }

    public RecyclerAdapter(List<VolunteeringOpportunities> volunteerList){
        this.volunteerList=volunteerList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        VolunteeringOpportunities volunteeringOpportunities=volunteerList.get(position);
        holder.titleTextView.setText(volunteeringOpportunities.getTitle());
        holder.cityTextView.setText("City: "+volunteeringOpportunities.getCity());
        holder.areaTextView.setText("Area: "+volunteeringOpportunities.getArea());
        holder.startTextView.setText("Start: "+volunteeringOpportunities.getStart());
        holder.endTextView.setText("End: "+volunteeringOpportunities.getEnd());
        holder.timeTextView.setText("Time(hours per day): "+volunteeringOpportunities.getTime());
        holder.typeTextView.setText("Type: "+volunteeringOpportunities.getType());
        holder.descriptionTextView.setText(volunteeringOpportunities.getDescription());
    }


    @Override
    public int getItemCount() {
        return volunteerList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
