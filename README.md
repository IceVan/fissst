# fissst


Model podzieliłem tak żeby możliwe było dosyć łatwe dodawanie nowych funkcjonalności w obrębie kontekstu, oraz żeby nie duplikować niepotrzebnie danych. Można wydzielić w nim konteksty: Brand/Model, Parts, Servicing, Sales. Nazwy tabel w bazie odwzorowują ten podział poprzez odpowiedni suffix. Same encje są w osobnym pakiecie razem z generycznymi klasami DAO oraz jedną pomocniczą adnotacją. Dzięki temu po stworzeniu z nigo modułu można będzie wrzucić go na zdalne repozytorium i wykorzystywać w innych applikacjach.
Nie wliczając pakietu z konfiguracjami, pozostałe pakiety są również podzielone na konteksty ze względu na ich funkcjonalności.

Lista pakietów:
<ul>
  <li>Brand - funkcjonalności związane z marką i modelem</li>
  <li>CarParts - funkcjonalności związane z częściami. PartInfo zawiera ogólne informacje nt typu części. Part jest z kolei konkretną częścią należącą do danego typu. Moje rozumowanie: mamy kilka takich samych silników, różnią się jednak akcjami serwisowymi, ceną oraz dostępnością.</li>
  <li>Configuration - pliki konfigurujące AOP, Swagger i uchwyty do wyjątków</li>
  <li>Model - model bazy; adnotacja @External która służy tylko do oznaczenia odwołań warstwy servisu do servisu w innym pakiecie; Generyczne klasy DAO z najbardziej podstawowymi funkcjonalnościami po których powinny dziedziczyć właściwe DAO'sy w pakiecie, z wyspecjalizowanymi funkcjonalnościami </li>
  <li>Sales - funkcjonalności związane ze sprzedażą i argumentami sprzedażowymi</li>
  <li>Servicing - funkcjonalności związane z serwisowaniem części</li>
  <li>Stock - funkcjonalności związane z dostępnością na magazynie</li>
</ul>

Poszczególne pakiety podzielone są na warstwy klasTransferowych, kontrolera, serwisów i dao. 

Mam nadzieję że niczego nie przeoczyłem.