package com.example.android.mmiwok;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager);

        SimpleFragmentPagerAdapter adapter=new SimpleFragmentPagerAdapter(this,getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

  //      TextView numbers=(TextView)findViewById(R.id.numbers);
  //      TextView family=(TextView)findViewById(R.id.family);
   //     TextView colors=(TextView)findViewById(R.id.colors);
  //      TextView phrases=(TextView)findViewById(R.id.phrases);

   //     numbers.setOnClickListener(new View.OnClickListener() {
    //        @Override
    //        public void onClick(View view) {
              //  Toast.makeText(view.getContext(),
                //        "open the list of numbers",Toast.LENGTH_SHORT).show();

     //           Intent intent=new Intent(MainActivity.this,NumbersActivity.class);
     //           startActivity(intent);
    //        }
    //    });

     //   family.setOnClickListener(new View.OnClickListener() {
     //       @Override
     //       public void onClick(View view) {
     //           Intent familyintent=new Intent(MainActivity.this,FamilyActivity.class);
     //           startActivity(familyintent);
     //       }
     //   });

   //     colors.setOnClickListener(new View.OnClickListener() {
   ///         @Override
    //        public void onClick(View view) {
   //             Intent intent=new Intent(MainActivity.this,ColorsActivity.class);
   //             startActivity(intent);
     //       }
    //    });

    //    phrases.setOnClickListener(new View.OnClickListener() {
    //        @Override
        //    public void onClick(View view) {
    //            Intent intent=new Intent(MainActivity.this,PhrasesActivity.class);
     //           startActivity(intent);
     //       }
   ///     });
  }


}
