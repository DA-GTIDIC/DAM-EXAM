package cat.udl.tidic.amd.dam_tips.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.models.Account;

class UserAdapter extends ListAdapter<Account, UserAdapter.UserHolder> {

    private final static String TAG = "UserAdapter";
    private OnItemClickListener eventItemListener;

    class UserHolder extends RecyclerView.ViewHolder {
        private TextView username;
        private TextView puntuacion;
        private ImageView photo;

        public UserHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.textview_username);
            photo = itemView.findViewById(R.id.img_perf);
            puntuacion = itemView.findViewById(R.id.textview_puntuacion);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (eventItemListener != null && position != RecyclerView.NO_POSITION) {
                        eventItemListener.onItemClick(getItem(position));
                    }
                }
            });
        }

    }

    protected UserAdapter(@NonNull DiffUtil.ItemCallback<Account> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items_users, parent, false);
        return new UserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        Account current_player = getItem(position);
        holder.username.setText(current_player.getUsername());
        holder.puntuacion.setText(current_player.getPoints());
    }

    public interface OnItemClickListener {
        void onItemClick(Account account);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.eventItemListener = listener;
    }
}
