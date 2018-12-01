package zadrick.geron.com.geronzadrickfinallabexam;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button firstbutton;
    EditText firstname,lastname;
    EditText exam1,exam2;
    TextView result;
    DatabaseReference StudentRef;
    String random_number,saveCurrentTime,postRandomName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StudentRef =FirebaseDatabase.getInstance().getReference();
        firstbutton = (Button) findViewById(R.id.firstButton);
        firstname = (EditText)  findViewById(R.id.eFN);
        lastname = (EditText)findViewById(R.id.eLN);
        result = (TextView)findViewById(R.id.txtAve);
        exam1 = (EditText)findViewById(R.id.eExam1);
        exam2 = (EditText)findViewById(R.id.eExam2);

        firstbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Getaverage();
            }
        });
    }

    private void Getaverage() {
        String fname = firstname.getText().toString().trim();
        String lname = lastname.getText().toString().trim();
        Long exam = Long.parseLong(exam1.getText().toString().trim());
        Long exam11 = Long.parseLong(exam2.getText().toString().trim());
        final Long average = (exam + exam11) / 2;

        result.setText(Long.toString(average));

        final String name = fname;
        final String last = lname;
        final Long exm1 = exam;
        final Long exm2 = exam11;
        final Long ave = average;


        StudentRef.child("").child("").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Calendar calFordTime =Calendar.getInstance();
                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime = currentTime.format(calFordTime.getTime());

                postRandomName = saveCurrentTime;
                HashMap students = new HashMap();
                students.put("firstname",name);
                students.put("lastname",last);
                students.put("exam1", exm1);
                students.put("exam2",exm2);
                students.put("average",average);
                StudentRef.child("Student" + saveCurrentTime).child("Average").updateChildren(students)
                    .addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Succesfull hehe", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(MainActivity.this,"Error hehe", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });








    }
}
