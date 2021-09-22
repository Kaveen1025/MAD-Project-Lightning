package com.example.mad_project_design_phase;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class My_Review_adapter extends FirebaseRecyclerAdapter<RCustomerReview,My_Review_adapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public My_Review_adapter(@NonNull @NotNull FirebaseRecyclerOptions<RCustomerReview> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull @NotNull myViewHolder holder, int position, @NonNull @NotNull RCustomerReview model) {

        holder.rName.setText(model.getName());
        holder.Review.setText(model.getReview());
        //holder.ratingBar.setRating(Float.parseFloat(model.getNoOfStars().trim()));


        Glide.with(holder.logo.getContext())
                .load(model.getLogo())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                //.circleCrop()

                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.logo);

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheet = new BottomSheetDialog(holder.rName.getContext());
                bottomSheet.setContentView(R.layout.update_my_restaurant);
                bottomSheet.setCanceledOnTouchOutside(false);


                Button updateButton,DeleteButton;

                EditText Review;
                RatingBar RatingBars;
               // ImageView
                updateButton = bottomSheet.findViewById(R.id.updateReview);
                DeleteButton = bottomSheet.findViewById(R.id.deleteReview);
                Review = bottomSheet.findViewById(R.id.updateReviewsss);
                RatingBars = bottomSheet.findViewById(R.id.updateRatings);
                TextView restName= bottomSheet.findViewById(R.id.UprestName);

                restName.setText("Matara");

                //restName.setText(model.getRestName());
                //Review.setText(model.getReview());
                //RatingBars.setRating(Float.parseFloat(model.getNoOfStars()));
                Review.setText("good");
                RatingBars.setRating(4.0f);
                bottomSheet.show();

                ImageButton closeDialog = bottomSheet.findViewById(R.id.closeDialog);
                closeDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheet.cancel();
                    }
                });

                DeleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // need confirmation

                        // learn position deletion
                       DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("CustomerReviews").child("C1").child("R1");
                       DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1").child("Reviews").child("Customers").child("C1");

                        ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                if(task.isSuccessful()){

                                    bottomSheet.cancel();
                                    Toast.makeText(holder.rName.getContext(), "Review Deleted Successfully!", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(holder.rName.getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                                }
                                }

                        });
                    }
                });


                updateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditText Review = bottomSheet.findViewById(R.id.updateReviewsss);
                        RatingBar RatingBars = bottomSheet.findViewById(R.id.updateRatings);

                        String reviewDes = Review.getText().toString().trim();
                        String rating = String.valueOf(RatingBars.getRating());


                        HashMap RCustomers = new HashMap();
                        RCustomers.put("noOfStars",rating);
                        RCustomers.put("review",reviewDes);

                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("CustomerReviews").child("C1").child("R1");
                        ref.updateChildren(RCustomers).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task task) {
                                if(task.isSuccessful()){
                                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Restaurant").child("Restaurant1").child("Reviews").child("Customers").child("C1");
                                        ref.updateChildren(RCustomers).addOnCompleteListener(new OnCompleteListener() {
                                            @Override
                                            public void onComplete(@NonNull @NotNull Task task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(holder.rName.getContext(), "Review Updated Successfully!", Toast.LENGTH_SHORT).show();
                                                }else{
                                                    Toast.makeText(holder.rName.getContext(), "Update Failed Please try again!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                }else{
                                    Toast.makeText(holder.rName.getContext(), "Update Failed Please try again!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                    }
                });

            }
        });



    }

    @NonNull
    @NotNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        //Bind myViewHolder & return it
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_review_card, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView logo;
        TextView rName,Review;
        RatingBar ratingBar;
        Button editBtn;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);


            logo = itemView.findViewById(R.id.logo12);
            rName = itemView.findViewById(R.id.rName);
            Review = itemView.findViewById(R.id.Review);
            ratingBar = itemView.findViewById(R.id.ratings22);
            editBtn = itemView.findViewById(R.id.editBtn);


        }
    }
}
