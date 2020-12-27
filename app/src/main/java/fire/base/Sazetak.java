/*

Ovo je jednostavna aplikacija koja nam pokazuje jednostavnost rada sa Firebase platformom.U ovoj aplikaciji smo
napravili tri aktivnosti koji nam sluze za logiranje, registriranje ako korisnik nema svoj racun te home activity
koja se otvara kada se korisnik ulogira da ga obavijesti da je napravio svoj korisnicki racun te da je unesen u
sustav.

--------------
MainActivity
--------------



--------------
LoginActivity
--------------
Na pocetku ove klase radimo klasicno kao i na pocetku svih klasa koje imaju svoj pripadajuci layout, a to je da
deklariramo sve view-ove koji se nalaze unutar tih layout-a.U nasem slucaju nas layout za logiranje ima dva EditText-a,
jedan za unos Email-a, a drugi za unos lozinke te imamo i gumb za unos podataka nakon sto ih je korisnik unio.Također
smo deklarirali i objekt klase FirebaseAuth koji je polazna tocka kada zelimo kreirati neki sistem upisivanja korisnika
u Firebase sustav.Nakon toga pozivamo klasu onAuthStateChanged() koja registrira da je doslo do nekakve promjene u sustavu
te sprema u objekt trenutnog korisnika koji se ulogirao, te mi sada nad tim korisnikom izvrsavamo nekakvu logiku uz pomoc
if i else blokova, uglavnom ako je korisnik unio podatke onda ispisujemo Toast poruku "You are logged" i vodimo korisnika
u novu aktivnost i to HomeActivity, te u else bloku provjeravamo ako korisnik nije unesen te mu ispisujemo Toast poruku
"Please login".Nakon toga implementiramo logiku nakon klika na gumb te radimo kontrole koje pregledavaju ako korisnik mozda
nije unio email, lozinku ili mozda oboje te ga obavijestava s pomocu setError() metode i u slucaju da je korisnik unio sve
podatke na pravilan nacin onda pozivamo metodu signInWithEmailAndPassword() kojoj kao argumente predajemo email i lozinku
koju je korisnik unio, unutar te metode također imamo kontrole definirane za slucaj kada je zadatak unosenja korisnika u
sustav prosao dobro, a imamo i kontrole za slucaj kada unosenje korisnika nije poslo prema planu


--------------
HomeActivity
--------------
U ovoj aktivnosti imamo samo jedan TextView unutar kojeg je upisan tekst i imamo gumb koji kada ga kliknemo nas vodi
u MainActivity aktivnost, i to je to sto se tice ove klase.

 */
