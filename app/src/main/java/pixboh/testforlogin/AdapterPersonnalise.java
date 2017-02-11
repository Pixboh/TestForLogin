package pixboh.testforlogin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by PIXBOH on 09/02/2017.
 */

public class AdapterPersonnalise extends RecyclerView.Adapter<AdapterPersonnalise.ViewHolder> {
    ArrayList<Personne> personnes;
    public AdapterPersonnalise(ArrayList<Personne> personnes){
        this.personnes=personnes;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View ligne= LayoutInflater.from(parent.getContext()).inflate(R.layout.clientview,parent,false);
        return new ViewHolder(ligne);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Personne p=personnes.get(position);
        holder.textViewNom.setText((p.getPrenom()+" "+p.getNom()).toUpperCase(Locale.FRENCH));
        holder.textViewUsername.setText("Username: "+p.getUsername());
        holder.textViewNumero.setText("Tel: "+p.getNumero_tel());
        holder.textViewEmail.setText("Email: "+p.getEmail());

    }

    @Override
    public int getItemCount() {
        return personnes.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNom,textViewEmail,textViewNumero,textViewUsername;
        private ImageView imageViewClient;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewEmail=(TextView)itemView.findViewById(R.id.textViewEmail);
            textViewNom=(TextView)itemView.findViewById(R.id.textViewnomclient);
            textViewNumero=(TextView)itemView.findViewById(R.id.textViewNumero);
            textViewUsername=(TextView)itemView.findViewById(R.id.textViewUsername);
            imageViewClient=(ImageView)itemView.findViewById(R.id.imageclient);



        }
    }
}
