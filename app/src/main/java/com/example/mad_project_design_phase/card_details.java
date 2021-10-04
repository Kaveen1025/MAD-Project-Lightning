package com.example.mad_project_design_phase;

        import androidx.appcompat.app.ActionBarDrawerToggle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import androidx.drawerlayout.widget.DrawerLayout;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageButton;

        import com.firebase.ui.database.FirebaseRecyclerOptions;
        import com.google.android.material.navigation.NavigationView;
        import com.google.firebase.database.FirebaseDatabase;

public class card_details extends Working_Side {

    RecyclerView ryvCard_Details;

    CardDetailsAdapter cardDetailsAdapter;


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton notificationBtn,profileBtn,cartBtn;
    ImageButton addcard;

    String CustomerID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        notificationBtn = findViewById(R.id.notificationBtn);
        profileBtn = findViewById(R.id.profileBtn);
        cartBtn = findViewById(R.id.cartBtn);

        //CustomerID = CustomerDetails.getCustomerID();
        CustomerID = "DgaCUSQDSOOgGnoFmv3ojR3vpH73";
        drawerLayout = findViewById(R.id.drawerLayout2);
        navigationView = findViewById(R.id.navvd);
        toolbar = findViewById(R.id.toolbarss);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        addcard = findViewById(R.id.imageButton11);

        //Assigning recycler view
        ryvCard_Details = (RecyclerView) findViewById(R.id.ryvCard_Details);

        ryvCard_Details.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<CardDetailsModel> options =
                new FirebaseRecyclerOptions.Builder<CardDetailsModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Payment").child(CustomerID), CardDetailsModel.class)
                        .build();


        cardDetailsAdapter = new CardDetailsAdapter(options);

        ryvCard_Details.setAdapter(cardDetailsAdapter);


    }


    @Override
    protected void onStart() {
        super.onStart();

        cardDetailsAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cardDetailsAdapter.stopListening();
    }


    protected void onResume() {
        super.onResume();

        addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(card_details.this,addAcard.class);
                startActivity(i);
            }
        });

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(card_details.this,notification.class);
                startActivity(i);

            }
        });


        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(card_details.this,user_profile.class);
                startActivity(i);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(card_details.this,edit_cart.class);
                startActivity(i);
            }
        });
    }

}