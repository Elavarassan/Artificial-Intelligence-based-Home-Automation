package com.example.elavarassan.sweethome;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class MainActivity extends AppCompatActivity {
Button on1;
Button off1;
Button on2;
Button off2;
Button on3;
Button off3;
Button on4;
Button off4;
TextView status1;
TextView status2;
TextView status3;
TextView status4;
TextView status5;
TextView status6;
DatabaseReference dref;
String status;
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
on1 = (Button) findViewById(R.id.on_1);
off1 =(Button) findViewById(R.id.off_1);
on2 = (Button) findViewById(R.id.on_2);
off2 = (Button) findViewById(R.id.off_2);
on3 = (Button) findViewById(R.id.on_3);
off3 = (Button) findViewById(R.id.off_3);
on4 = (Button) findViewById(R.id.on_4);
off4 = (Button) findViewById(R.id.off_4);
status1 = (TextView) findViewById(R.id.textView1);
status2 = (TextView) findViewById(R.id.textView2);
status3 = (TextView) findViewById(R.id.textView3);
status4 = (TextView) findViewById(R.id.textView4);
status5 = (TextView) findViewById(R.id.textView5);
status6 = (TextView) findViewById(R.id.textView6);
dref= FirebaseDatabase.getInstance().getReference();
dref.addValueEventListener(new ValueEventListener() {
public void onDataChange(DataSnapshot dataSnapshot) {
status=dataSnapshot.child("Device-1").getValue().toString();
if(status.equals("1"))
status = "ON";
else
status="OFF";
status1.setText(status);
status=dataSnapshot.child("Device-2").getValue().toString();
if(status.equals("1"))
status = "ON";else
status="OFF";
status2.setText(status);
status=dataSnapshot.child("Device-3").getValue().toString();
if(status.equals("1"))
status = "ON";
else
status="OFF";
status3.setText(status);
status=dataSnapshot.child("Device-4").getValue().toString();
if(status.equals("1"))
status = "ON";
else
status="OFF";
status4.setText(status);
status=dataSnapshot.child("temperature").getValue().toString();
status5.setText(status);
status=dataSnapshot.child("humidity").getValue().toString();
status6.setText(status);
}
public void onCancelled(DatabaseError databaseError) {
}
});
on1.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View view) {
FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference myRef = database.getReference("Device-1");
myRef.setValue("1");
}
});
off1.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View view) {
FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference myRef = database.getReference("Device-1");
myRef.setValue("0");
}
});
on2.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View view) {
FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference myRef = database.getReference("Device-2");
myRef.setValue("1");
}
});
off2.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View view) {FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference myRef = database.getReference("Device-2");
myRef.setValue("0");
}
});
on3.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View view) {
FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference myRef = database.getReference("Device-3");
myRef.setValue("1");
}
});
off3.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View view) {
FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference myRef = database.getReference("Device-3");
myRef.setValue("0");
}
});
on4.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View view) {
FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference myRef = database.getReference("Device-4");
myRef.setValue("1");
}
});
off4.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View view) {
FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference myRef = database.getReference("Device-4");
myRef.setValue("0");
}
});
}
}