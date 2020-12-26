package fire.base;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    //ovdje smo samo definirali varijable te njihove tipove podataka koje cemo kasnije
    //koristiti u ovoj klasi
    Button btnLogout;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //u layout-u koji smo koristili za ovu klasu imamo gumb koji smo deklarirali, i
        //s pomocu metode findViewById() odlazmo u XML, nalazimo taj element s tim Id-om
        //koji smo naveli kao argument ovoj metodi te ga pohranjujemo u Java objekt, znaci
        //s pomocu ove metode smo od XML elementa kreirali java objekt
        btnLogout = findViewById(R.id.logout);

        //u ovom activity-u kao sto smo rekli imamo gumb kojem smo pomocu metode setOnClickListener() dali nekakvu funkcionalnost
        //jos nismo naveli sto tocno nego smo samo naveli da ce imati neku funkciju u trenutku kada se na njega klikne
        btnLogout.setOnClickListener(new View.OnClickListener() {

            //s pomocu ove onClick() metode smo definirali sta ce se desiti prilikom klika na gumb, a to je da ce se kreirati objekt
            //od klase Intent s imenom intToMain te da ce on imati za zadatak otvoriti novi activity, a taj novi activity koji ce se
            //otvoriti je MainActivity, taj objekt Intent klase ima sve informacije potrebne kako bi otvorio novi activity, ali jos
            //uvijek nije upogonjen, a upravo za to nam sluzi metoda startActivity() kojoj kao argument prilazemo objekt Intent klase
            //koji u sebi ima sve potrebne informacije za otvaranje nove aktivnosti
            @Override
            public void onClick(View v) {

                //?????????????????
                FirebaseAuth.getInstance().signOut();


                Intent intToMain = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intToMain);
            }
        });

    }
}