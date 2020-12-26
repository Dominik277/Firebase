package fire.base;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //ovdje smo samo deklarirali imena te tipove varijabli View-ova koje imamo u XML-datotekama
    //znaci ukratko, tu smo naveli preko kojih imena cemo referencirati određene view-ove koji
    //se nalaze u activity_login.xml datoteci
    EditText emailId;
    EditText password;
    Button btnSignIn;
    TextView tvSignUp;
    FirebaseAuth mFirebaseAuth;


    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //gore smo deklarirali varijablu mFirebaseAuth tipa FirebaseAuth od kojeg smo napravili
        //instancu pomocu metode getInstance(), te smo definirali da cemo taj objekt koji je
        //kreiran u memoriji racunala referencirati preko imena mFirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();

        //u sljedeca cetiri reda smo pozvali varijable koje smo prethodno gore deklarirali te s
        //pomocu metode findViewById() otisli u XML, potrazili na osnovu ID-a postoji li takav element
        //kao sto smo mi naveli te rezultat trazenja te metode pohranili u objekt s lijeve strane, na
        //ovaj nacin smo od XML elemenata stvorili java objekte
        emailId = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        btnSignIn = findViewById(R.id.button2);
        tvSignUp = findViewById(R.id.textView);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    Toast.makeText(LoginActivity.this,
                            "You are logged in",
                            Toast.LENGTH_LONG)
                            .show();
                    Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(LoginActivity.this,
                            "Please Login",
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        };

        //u ovom activity-u imamo i gumb koji nam sluzi da bi se ulogirali u bazu podataka nakon sto unesemo
        //korisnicke podatke, setOnClickListener() metoda nam sluzi da kazemo kompajleru da ce ovaj view(gumb)
        //imati nekakvu funkcionalnost, jos nismo odredili kakvu, ali smo rekli da ce ju imati
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            //i sada dolazimo do onClick() metode unutar koje definiramo definiramo sta ce se desiti prilikom
            //klika na gumb
            @Override
            public void onClick(View v) {

                //posto u ovom activity-u imamo dva EditText-a koji su View-ovi unutr kojih se unosi tekst
                //u prvom slucaju imamo EditText u koji korisnik unosi email a napraili smo od njega java objekt
                //te ga nazvali emailId, i sada preko tog objekta mozemo pozvati metodu getString() koja vraca
                //ono sto je korisnik unio u taj EditText te smo pozvali i metodu toString() koja vraca string
                //odnosno tekstualni rezultat objekta, i sve to smo pohranili unutar String varijable email
                String email = emailId.getText().toString();

                //ova linija koda je u potpunosti jednaka liniji koda iznad, znaci u view koji nam predstavlja
                //EditText tamo gdje korisnik upisuje lozinku, od tog view-a smo kreirali objekt i nazvali ga
                //password kako bi mogli vrsiti neke radnje nad njim, a jedna od radnji koje radimo je da pomocu
                //metode getText() pohranjujemo u password objekt ono sto je korisnik unio u polje za lozinku, te
                //na kraju pozivamo metodu toString() koja vraca tekstualni odnosno string zapis objekta, te to sve
                //pohranjujemo u pwd varijablu tipa String
                String pwd = password.getText().toString();

                //sljedeci dobar dio koda nam sluzi za kontrolu onoga sto je korisnik unio u polje za email i lozinku
                //u sljedecem if bloku koda se provjerava slucaj ako je korisnik ostavio EditText polje za unos email-a
                //prazno a kliknuo dalje onda se pojavljuje na desnom kraju mali crveni usklicnik i iskace popup prozorcic
                //s porukom "Please enter email id", to je sve omoguceno sa metodom setError()
                //nakon toga imamo metodu requestFocus() koja nam sluzi kako bi fokus i nakon prestanka prikazivanja popup
                //poruke bio na tom EditText polju kako bi ga korisnik mogao popuniti
                if (email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }

                //u sljedecem if else bloku koda se provjerava slucaj ako je korisnik ostavio EditText polje za unos lozinke
                //prazno a kliknuo dalje onda se pojavljuje na desnom kraju mali crveni usklicnik i iskace popup prozorcic
                //s porukom "Please enter your password", to je sve omoguceno sa metodom setError()
                //nakon toga imamo metodu requestFocus() koja nam sluzi kako bi fokus i nakon prestanka prikazivanja popup
                //poruke bio na tom EditText polju kako bi ga korisnik mogao popuniti
                else if (pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }

                //sljedeci dio koda unutar else if bloka se obavlja kada je naredba unutar zagrada tocna, a naredba unutar
                //zagrada je istinita onoga trenutka kada je korisnik kliknuo gumb za Login, a polja za email i lozinku
                //su mu ostala prazna
                else if (email.isEmpty() && pwd.isEmpty()){

                    //u slucaju kada su obadva polja i za email i za lozinku prazna onda se pojavljuje Toast poruka na dnu
                    //ekrana s tekstom "Fields Are Empty"
                    Toast.makeText(LoginActivity.this,
                            "Fields Are Empty",Toast.LENGTH_LONG).show();
                }

                //sljedeci dio koda se obavlja u slucaju kada je situacija tocno onakava kakvu zelimo, odnosno kada je korisnik
                //kliknuo gumb za login nakon sto je popunio polje za email i za lozinku
                else if (!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email,pwd)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()){
                                        Toast.makeText(LoginActivity.this,
                                                "Login Error, Try Again!",Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Intent intToHome = new Intent(LoginActivity.this,HomeActivity.class);
                                        startActivity(intToHome);
                                    }
                                }
                            });
                }

                //pokrili smo vise slucajeva koji se mogu desiti prilikom logiranja korisnika u sustav, ako se dogodi neka
                //pogreska koju nismo predvidili da bi se mogla desiti onda ce se na dnu ekrana pojaviti Toast poruka s
                //porukom "Error Ocurred"
                else {
                    Toast.makeText(LoginActivity.this,
                            "Error Ocurred!",
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        //u ovom activity-u kao sto smo rekli imamo gumb kojem smo pomocu metode setOnClickListener() dali nekakvu funkcionalnost
        //jos nismo naveli sto tocno nego smo samo naveli da ce imati neku funkciju u trenutku kada se na njega klikne
        tvSignUp.setOnClickListener(new View.OnClickListener() {

            //ovdje smo naveli sto ce se desiti prilikom klika na gumb, a to je da cemo s pomocu Intent klase, odnosno objekta
            //Intent klase prilikom klika na gumb otvoriti novi activity, a novi activity koji ce se otvoriti je MainActivity
            //kao parametre Intent konstruktoru navodimo klasu unutar koje se nalazi gumb koji smo kliknuli, a kao drugi parametar
            //navodimo klasu koju zelimo da se otvori prilikom klika na gumb
            //startActivity() --> u liniji iznad ove metode smo definirali sta se treba desiti prilikom klika na gumb te smo to sve
            //                    pohranili unutar intSignUp objekta koji u sebi ima sve informacije potrebne za otvaranje nove klase
            //                    ali jos to nismo upogonili i upravo cemo to upogoniti s pomocu metode startActivity() kojoj kao
            //                    argument predajemo objekt Intent klase koji u sebi sadrzi sve potrebne podatke za otvaranje novog
            //                    activity-a
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intSignUp);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}