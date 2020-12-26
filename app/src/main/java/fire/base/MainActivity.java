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

public class MainActivity extends AppCompatActivity {

    //ovdje smo samo deklarirali imena te tipove varijabli View-ova koje imamo u XML-datotekama
    //znaci ukratko, tu smo naveli preko kojih imena cemo referencirati određene view-ove koji
    //se nalaze u activity_login.xml datoteci
    EditText emailId;
    EditText password;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        btnSignUp = findViewById(R.id.button2);
        tvSignIn = findViewById(R.id.textView);

        //u ovom activity-u imamo i gumb koji nam sluzi da bi se ulogirali u bazu podataka nakon sto unesemo
        //korisnicke podatke, setOnClickListener() metoda nam sluzi da kazemo kompajleru da ce ovaj view(gumb)
        //imati nekakvu funkcionalnost, jos nismo odredili kakvu, ali smo rekli da ce ju imati
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override

            //ovdje smo definirali sto ce se desiti prilikom klika na gumb Sign Up unutar activity-a kojeg se nalazimo
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
                    Toast.makeText(MainActivity.this,
                            "Fields Are Empty",Toast.LENGTH_LONG).show();
                }

                //sljedeci dio koda se obavlja u slucaju kada je situacija tocno onakava kakvu zelimo, odnosno kada je korisnik
                //kliknuo gumb za login nakon sto je popunio polje za email i za lozinku
                else if (!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()){
                                       Toast.makeText(MainActivity.this,
                                               "SignUp Unsuccessful,Try Again!",
                                               Toast.LENGTH_LONG)
                                               .show();
                                    }
                                    else{
                                        startActivity(new Intent(MainActivity.this,
                                                HomeActivity.class));
                                    }
                                }

                            });
                }

                //pokrili smo vise slucajeva koji se mogu desiti prilikom logiranja korisnika u sustav, ako se dogodi neka
                //pogreska koju nismo predvidili da bi se mogla desiti onda ce se na dnu ekrana pojaviti Toast poruka s
                //porukom "Error Ocurred"
                else {
                    Toast.makeText(MainActivity.this,
                            "Error Ocurred!",
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        //unutar MainActivity activity-a koji se otvara prilikom ulaska u aplikaciju imamo jedan gumb kojeg smo nazvali tvSignIn
        //i sada smo mu s pomocu setOnClickListener() metode dali nekakvu funkcionalnost, jos nismo definirali sta, ali ju ima
        tvSignIn.setOnClickListener(new View.OnClickListener() {

            //u sljedecem bloku koda smo definirali sta ce se desiti prilikom klika na gumb, a to je da ce se otvoriti novi activity
            //a to ce se odviti s pomocu objekta od Intent klase kojem cemo kao argument konstruktoru predati klasu unutar koje se
            //nalazi gumb te kao drugi argument cemo navesti klasu onog activity-a kojeg zelimo da se otvori nakon klika na gumb
            //sve to je definirano i sve te informacije su sadrzane unutar objekta Intent klase ali jos nije nista upogonjeno, ali
            //upravo to cemo uciniti pomocu metode startActivity() kojoj kao argument dajemo objekt Intent klase u koji smo unjeli
            //sve potadke koji su mu potrebni kako bi otvorio novi activity
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

    }
}