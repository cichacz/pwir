# Projekt na Programowanie współbieżne i równoległe

## Autorzy

- Michał Szewczyk (196737)
- Damian Pieczara

## Opis zagadnienia

n równouprawnionych procesów A<sub>i...n</sub> generuje ciągi k liczb całkowitych, 
które następnie wysyła w porcjach po 10 do m procesów sumujących B<sub>j...m</sub>.  
Po zsumowaniu wynik jest zwracany do nadawcy.

## Sposób rozwiązania

Stworzone są dwie pule wątków: generatory i sumatory.  

#### Generatory

Otrzymują w konstruktorze nazwę (do wyświetlania), ilość liczb do wygenerowania, rozmiar kolejki oraz pulę wątków sumujących.  
W momencie wykonywania metody run() wykorzystywana jest kolejka, która po zapełnieniu jest wysyłana do sumatora.  
Po zakolejkowaniu wszystkich obliczeń wątek oczekuje na wyniki.  
Dzięki wykorzystaniu CompletionService proces bezpiecznie czeka na wyniki,
które wyświetlane są wtakiej kolejności w jakiej zostaną rozwiązane.  

#### Sumatory

Otrzymują listę liczb całkowitych, które sumują i zwracają wynik do nadawcy.

## Wykorzystane klasy

- ExecutorService  
  opis ...
- CompletionService  
  opis ...
- Random  
  opis ...
- BlockingQueue  
  opis ...
- List  
  opis ...
- podstawowe typy danych jak int (Integer), String

## Testowanie
#### Kompilacja
```java
javac ./src/uek/szewczyk/*.java
```
#### Uruchamianie
```java
java -cp src uek.szewczyk.Main k m n
```
##### Opis parametrów
- k - tyle liczb ma wygenerować każdy generator
- m - tyle generatorów ma zostać uruchomione
- n - tyle sumatorów jest dostępnych do dyspozycji generatorów