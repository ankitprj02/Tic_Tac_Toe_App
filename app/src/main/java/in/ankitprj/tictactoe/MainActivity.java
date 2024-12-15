package in.ankitprj.tictactoe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    int flag = 0;
    int count = 0;
    String b1, b2, b3, b4, b5, b6, b7, b8, b9;
    String status;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        mediaPlayer = MediaPlayer.create(this, R.raw.claps);


    }

    private void init() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

    }


    public void Check(View view) {
        Button btnCurrent = (Button) view;
        if (btnCurrent.getText().toString().isEmpty()) {
            count++;
            if (flag == 0) {
                btnCurrent.setText("x");
                btnCurrent.setTextColor(getResources().getColor(R.color.red));
                flag = 1;

            } else {
                btnCurrent.setText("o");
                btnCurrent.setTextColor(getResources().getColor(R.color.green));
                flag = 0;
            }
            if (count > 4) {
                b1 = btn1.getText().toString();
                b2 = btn2.getText().toString();
                b3 = btn3.getText().toString();
                b4 = btn4.getText().toString();
                b5 = btn5.getText().toString();
                b6 = btn6.getText().toString();
                b7 = btn7.getText().toString();
                b8 = btn8.getText().toString();
                b9 = btn9.getText().toString();


                if (b1.equals(b2) && b2.equals(b3) && !b1.isEmpty()) {
                    //1
                    status = b1;
                    dialogBox();
                    newGame();

                } else if (b4.equals(b5) && b5.equals(b6) && !b4.isEmpty()) {
                    //2
                    status = b4;
                    dialogBox();
                    newGame();


                } else if (b7.equals(b8) && b8.equals(b9) && !b7.isEmpty()) {
                    //3
                    status = b7;
                    dialogBox();
                    newGame();

                } else if (b1.equals(b4) && b4.equals(b7) && !b1.isEmpty()) {
                    //4
                    status = b1;
                    dialogBox();
                    newGame();

                } else if (b2.equals(b5) && b5.equals(b8) && !b2.isEmpty()) {
                    //5
                    status = b2;
                    dialogBox();
                    newGame();

                } else if (b3.equals(b6) && b6.equals(b9) && !b3.isEmpty()) {
                    //6
                    status = b3;
                    dialogBox();
                    newGame();

                } else if (b1.equals(b5) && b5.equals(b9) && !b1.isEmpty()) {
                    //7
                    status = b1;
                    dialogBox();
                    newGame();

                } else if (b3.equals(b5) && b5.equals(b7) && !b3.isEmpty()) {
                    //8
                    status = b3;
                    dialogBox();
                    newGame();
                } else if (!b1.isEmpty() && !b2.isEmpty() && !b3.isEmpty()
                        && !b4.isEmpty() && !b5.isEmpty() && !b6.isEmpty()
                        && !b7.isEmpty() && !b8.isEmpty() && !b9.isEmpty()) {
                    status = "z";
                    dialogBox();
                    newGame();

                }

            }

        } else {
            Toast.makeText(this, "You Cannot change value...", Toast.LENGTH_SHORT).show();
        }


    }

    private void newGame() {
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");


    }


    public void Restart(View view) {
        newGame();
        Toast.makeText(this, "Restart Game...", Toast.LENGTH_SHORT).show();
    }

    public void dialogBox() {
        //dialog Initialize;
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.win_dialog_layout);
        dialog.show();
        dialog.setCancelable(false);
        Button btnDone = dialog.findViewById(R.id.done);
        TextView title = dialog.findViewById(R.id.dialogTitle);
        TextView disc = dialog.findViewById(R.id.dialogDiscription);
        ImageView icon = dialog.findViewById(R.id.statusIcon);
        if (status.equals("x")) {
            icon.setImageDrawable(getResources().getDrawable(R.drawable.win));
            title.setText("Congratulations X");
            title.setTextColor(getResources().getColor(R.color.red));
            disc.setText("X is Winner...");

            // Play sound
            if (mediaPlayer != null) {
                mediaPlayer.seekTo(0); // Restart sound from beginning
                mediaPlayer.start();
            }


        } else if (status.equals("o")) {
            icon.setImageDrawable(getResources().getDrawable(R.drawable.win));
            title.setText("Congratulations O");
            title.setTextColor(getResources().getColor(R.color.green));
            disc.setText("O is Winner...");

            // Play sound
            if (mediaPlayer != null) {
                mediaPlayer.seekTo(0); // Restart sound from beginning
                mediaPlayer.start();
            }


        } else if (status.equals("z")) {
            icon.setImageDrawable(getResources().getDrawable(R.drawable.draw));
            title.setText("Game Draw");
            title.setTextColor(getResources().getColor(R.color.red));
            disc.setText("Draw match....");


        }


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                newGame();
                // Stop sound
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    public void exit(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are You Sure You Want to Exit?")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                    }
                }).create().show();

    }


}