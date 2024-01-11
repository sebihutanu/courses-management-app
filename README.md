README TEMA2
===================
In implementarea temei am folosit urmatoarele clase:
- **Secretariat** 
* Am creat un ArrayList de tip Student pentru a retine studentii inscrisi;
* Am creat un ArrayList de tip Curs<?> (am folosit genericitatea pentru a putea pune in acest ArrayList atat cursurile de licenta cat si cele de master) pentru a retine cursurile disponibile;
* Am creat metoda adaugaStudent(String program_de_studii, String nume) care adauga un student in lista de studenti inscrisi in cazul in care acesta nu este deja inscris (daca este inscris va arunca o exceptie);
* Am creat metoda adaugaCurs(String program_de_studii, String nume_curs, int capacitatea_maxima) care adauga un curs in lista de cursuri disponibile;
* Am creat metoda citesteNota(String nume, double medie) care cauta studentul cu numele dat si ii atribuie media;
* Am creat metoda adaugaPreferinta(Student student, Curs<?> curs) care adauga un curs in lista de preferinte a studentului, verificand daca acesta nu este deja inscris la acel curs si daca tipul cursului este compatibil cu programul de studii al studentului;
* Am creat metoda InscriereStudent(Student student, Curs<?> curs) care inscrie un student la un curs;

- **Student**
* Contine campurile: nume, medie, un boolean "inrolat" pentru a retine daca studentul este inrolat la un curs, un ArrayList de tip Curs<?> in care retine preferintele sale si un ArrayList de tip Curs<?> in care retine cursurile la care este inscris;
* Am creat settere si gettere pentru campurile clasei;
* In implementarea temei am mai folosit doua clase care extind clasa Student si anume StudentLicenta si StudentMaster pentru putea face diferenta intre studentii de licenta si cei de master;

- **Curs**
* Contine campurile: numeCurs, capacitateMaxima, ultimaMedie si un ArrayList de tip Student in care retine studentii inscrisi la curs;
* Pe langa gettere si settere am creat metoda InscriereStudent(Student student) care adauga un student in lista de studenti inscrisi la curs, verificand daca nu a fost deja atinsa capacitatea maxima dar si daca ultima medie este diferita de media studentului; in acest caz se arunca o exceptie;
* In implementarea temei am mai folosit doua care care extind clasa Curs si anume CursLicenta si CursMaster pentru a putea face diferenta intre cursurile de licenta si cele de master;

- **ComparatorStudenti si ComparatorStudentiAlfabetic**
* Am creat aceste clase pentru a putea sorta studentii dupa medie si dupa nume;
* Prima clasa sorteaza studentii dupa medie, iar in cazul in care mediile sunt egale, dupa nume;
* A doua clasa sorteaza studentii strict dupa nume;

- **Implementarea clasei Main:**
* In implementarea temei am avut de citit dintr-un fisier de intrare diferite comenzi si de afisat in fisierul de iesire rezultatele acestora;
* Comanda adauga_student: se citeste numele studentului si programul de studii si se adauga studentul in lista de studenti inscrisi in cazul in care acesta nu este deja inscris;
* Comanda adauga_curs: se citeste numele cursului, programul de studii si capacitatea maxima si se adauga cursul in lista de cursuri disponibile;
* Comanda citeste_mediile: se citeste numele studentului si media acestuia si se atribuie media studentului;
* Comanda posteaza_mediile: se sorteaza lista de studenti in functie de mediesi se scrie in fisierul de output numele studentilor si media acestora;
* Comanda contestatie: se citeste numele studentului si noua medie, iar apoi se modifica in lista de studenti;
* Comanda adauga_preferinte: se citeste numele studentului si se cauta in lista de studenti, apoi se citesc numele cursurilor si se adauga in lista de preferinte daca acestea sunt compatibile cu programul de studii al studentului;
* Comanda "repartizeaza": se sorteaza lista de studenti in functie de medie, iar apoi se parcurge lista de studenti si se inscrie fiecare student la primul curs din lista de preferinte la care mai sunt locuri disponibile;
* Comanda "posteaza_curs": se sorteaza lista de studenti a cursului in functie de medie, iar apoi se scrie in fisierul de output numele cursului si capacitatea maxima, apoi numele studentilor si media acestora;
* Comanda "posteaza_student": se cauta studentul si se afiseaza in fisierul de output numele acestuia, media si cursul la care este inscris;