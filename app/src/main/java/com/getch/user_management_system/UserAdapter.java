package com.getch.user_management_system;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context usercontext;
    private View.OnClickListener userClickListner;
    private View.OnLongClickListener userLongClickListner;
    private ArrayList<UserContainer> userContainerList;

    public UserAdapter(Context usercontext, ArrayList<UserContainer> userContainerList) {
        this.usercontext = usercontext;
        this.userContainerList = userContainerList;
    }

    public void setUserClickListner(View.OnClickListener userClickListner) {
        this.userClickListner = userClickListner;
    }

    public void setUserLongClickListner(View.OnLongClickListener userLongClickListner) {
        this.userLongClickListner = userLongClickListner;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(usercontext);
        View view=   inflater.inflate(R.layout.user_lists, null);
        return new UserViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
         UserContainer userContainer=userContainerList.get(position);
         holder.userfulname.setText(userContainer.getFulname());
    }

    @Override
    public int getItemCount() {
        return userContainerList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userfulname;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userfulname=itemView.findViewById(R.id.id_userlists);
            itemView.setTag(this);
          itemView.setOnClickListener(userClickListner);
         itemView.setOnLongClickListener(userLongClickListner);
        }
    }
}
