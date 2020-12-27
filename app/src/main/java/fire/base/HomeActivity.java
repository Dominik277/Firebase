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

                //posto kad smo u ovom activity-i onda nam se prikaze mogucnost da se odjavimo i kada kliknemo gumb automatski se otvori
                //novi activity, a to je MainActivity i sada mi zelimo prilikom klika na gumb stvoriti funkcionalnost da se korisnik odjavi
                //iz Firebase sistema, a upravo to postizemo sa naredbom ispod, prvo pomocu metode getInstance() kreiramo instancu klase, a
                //nakon toga na tu instancu pozivamo metodu signOut() koja je zasluzena za odjavljivanje korisnika iz Firebase sustava
                //FirebaseAuth --> ovo je glavna klasa i polazna tocka sto se tice Firebase Authentication sustava i unutar nje se nalaze
                //                 sve metode koje su potrebne za razne operacije nad tim Authentication sistemom
                FirebaseAuth.getInstance().signOut();

                //ovdje smo rekli da prilikom klika na gumb se kreira objekt intToMain tipa Intent koji nam sluzi kako bi otvorili neki
                //drugi activity, znaci unutar ovog objkta smo pohranili sve podatke koji su potrebni da se otvori novi activity, a te
                //podatke smo opskrbili objektu kao parametri konstruktora, prvi paramatar konstrkutora nam predstavlja klasu unutar koje
                //se trenutno nalazimo, a drugi parametar nam predstavlja klasu activity-a koju zelimo da se otvori
                //startActivity() --> mi smo gore sve definirali unutar Intent objekta sta ce on raditi i koja ce biti njegova zadaca, ali
                //                    mi to jos nismo upogonili i upravo je to zadaca ove metode da joj predamo Intent objekt koji u sebi
                //                    sadrzi sve podatke potrebne za otvaranje novog activity-a te da to sve upogoni
                Intent intToMain = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intToMain);
            }
        });

    }
}