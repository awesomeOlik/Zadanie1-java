# Zadanie 1 - Monopoly lite
B-OOP 2021

Vašou úlohou je naprogramovať zjednodušenú hru Monopoly ako konzolovú hru v jazyku Java. Veľkosť hracieho poľa je 24 políčok, z čoho sú:

* 4 rohové políčka (Štart, Väzenie, Polícia (Choď do väzenia) a Platba dane),
* 4 políčka šance (treba mať minimálne 5 kariet),
* 16 nehnuteľností.

Hra začína výberom počtu hráčov a ich mien. Po zadaní mien všetkých hráčov hra začína, a prvý hráč hádže kockou. Po hode kockou sa hráč posunie o toľko políčok, koľko hodil na kocke. Keď hráč stúpi na políčko, vykoná sa akcia daného políčka. Definované akcie:

* Väzenie - Návšteva väzenia
* Polícia - Uväznenie hráča do väzenia na X kôl
* Platba Dane - Hráč musí zaplatiť určitú čiastku
* Políčko Šanca - Hráč si potiahne kartu a vykoná akciu, ktorá je na karte definovaná (z balíčku si hráč potiahne vždy novú kartu, nie náhodnú, keď balíček minie, začne ho používať od znova)
* Nehnuteľnosť - Hráč môže dané políčko kúpiť za určitú sumu, ak ho ešte nik nevlastní a ak má na neho peniaze. Ak už políčko patrí inému hráčovi, musí mu zaplatiť "stojné". Každá nehnuteľnosť má predom definovanú cenu a výšku stojného.

Každý hráč obdrží určitú sumu pri prechode štartom. Hráč, ktorý nemá peniaze na zaplatenie daní alebo stojného, prehral. Hra končí, keď ostáva v hre už len jeden hrajúci hráč.

## Hodnotenie

Zadanie je hodnotené 10 bodmi. **Odovzdaný program musí byť skompilovateľný, inak je
hodnotený 0 bodmi**. Skompilovateľnosť zadania kontroluje aj github pipeline. Hlavný dôraz v hodnotení sa kladie na objektový prístup a princípy,
okrem iného:

* vhodné pomenovanie tried a metód v jednotnom jazyku (názvy tried s veľkým počiatočným písmenom, názvy metód s malým),
* vhodné použitie modifikátorov prístupu (public, private, poprípade protected) na obmedzenie prístupu k metódam a atribútom,
* využitie dedenia a polymorfizmu,
* použitie výnimiek na ošetrenie nedovoleného správania (nehádzať a nezachytávať všeobecnú triedu Exception),
* nepoužívajte nested classy,
* vo vašich triedach nevytvárajte statické metódy (v zadaní nie sú potrebné),
* v hlavnej triede (main) nevytvárajte žiadnu logiku, iba vytvorte nový objekt.

## Odovzdávanie
Zadanie si naklonujte z repozitára zadania. Svoje vypracovanie nahrajte do vášho repozitára pre toto zadanie pomocou programu Git (git commit + git push). Vypracovanie môžete "pusho-vať" priebežne. Hodnotiť sa bude iba _master_ branch. Zadanie je nutné vypracovať do **26.3.2021 23:00**.

V projekte upravujte iba súbory v priečinku _src/main_ a jeho podpriečinkoch. Ostatné súbory je upravovať zakázané (predovšetkým súbory _pom.xml_ a súbory obsahujúce github pipeline-y).

Vo svojom github účte si nastavte svoje meno (settings > profile > name), aby bolo možné priradiť riešenie ku študentovy. **Pokiaľ nebude možné spárovať študenta s riešením je zadanie hodnotené 0 bodmi!**
