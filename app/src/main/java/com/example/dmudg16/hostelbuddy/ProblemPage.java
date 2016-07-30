package com.example.dmudg16.hostelbuddy;


import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ProblemPage extends NavigationDrawer implements View.OnClickListener,GestureDetector.OnGestureListener{
    EditText ed1;EditText ed2;EditText ed3;
    GestureDetector gd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1;Button b2;

        b1=(Button)findViewById(R.id.b1);
       ed1=(EditText)findViewById(R.id.editText);

        ed2=(EditText)findViewById(R.id.editText2);

        ed3=(EditText)findViewById(R.id.editText3);

        gd=new GestureDetector(this,this);

        b1.setOnClickListener(this );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
public  void written(){

    Toast.makeText(this,"Complaint Added",Toast.LENGTH_LONG).show();
}

    @Override
    public void onClick(View v) {

if(v.getId()==R.id.b1)
{sender s= new sender(this);
         s.setdata(ed1.getText().toString(),ed2.getText().toString(),ed3.getText().toString());
         s.execute();}
 /* else{
    Intent i=new Intent("com.example.dmudg16.hostelbuddy.showcomplaints");
    startActivity(i);
        }*/

    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        this.gd.onTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(e1.getY()-e2.getY()>100){}
        else if(e2.getY()-e1.getY()>100){}
        else if(e1.getX()-e2.getX()<100&&mDrawerLayout.isDrawerOpen(findViewById(R.id.drawer_list))==false&&e1.getX()>50){
           // Intent i=new Intent("com.example.dmudg16.hostelbuddy.homepage");

            //startActivity(i);
            this.finish();
            overridePendingTransition(R.anim.sliderightin, R.anim.sliderightout);
            }


        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
