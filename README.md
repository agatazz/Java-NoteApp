# NoteApp
Aplikacja która umożliwia założenie konta oraz tworzenie notatek.

## Technologie
1. Android SDK 4.0.0
2. Java 7
3. Firebase 16.0.1

## Rejestracja
- Aplikacja wymaga od użytkownika podania emaila i hasła(min.6 znaków)
- Nie jest możliwe wejście do systemu bez podania wszystkich właściwych wartości
- Jezeli użytkownik ma konto to może przejść do strony logowania
- W momenice poprawnej i nie poprawnej rejestracji wyskakują odpowiednie komunikaty

## Logowanie
- Uzytkownik musi podać istniejący w bazie email i hasło
- Nie jest możliwe wejście do systemu bez podania wszystkich właściwych wartości
- Jeżeli użytkownik nie ma konta to może przejść do strony rejestracji
- W momenice poprawnego i nie poprawnego logowania wyskakują odpowiednie komunikaty

## Home Page

- Te okienko zawiera przyciski Dodaj notatkę oraz wyloguj się
- Po przyciśnięciu na przycisk Dodaj notatkę użytkownik zostaje przeniesiony do okna tworzenia notatki
- Po przyciśnięciu przycisku wyloguj użytkownik zostaje przeniesiony do okna ponownego logowania
## Doda notatkę

- Użytkownik musi wprowadzić tytuł dla notatki
- Użytkownik musi wprowadzić treść dla notatki
- Bez podania wszystkich wymaganych wartości nie da się dodać notatki
- Użytkownik ma do dyspozycji przycisk Save,który zapisuje notatkę do sytemu
- Użytkownik ma do dyspozycji przycisk Delete który usuwa wprowadzone dane z systemu
- W przypadku poprawnego i nie poprawnego dodania oraz usunięcia notatki na ekranie ukazują się odpowiednie komunikaty


