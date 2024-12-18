public class MyClosedHashTableForInts {

    protected int[] tagArray;
    private int[] bucketArray;
    protected int size;
    protected int capacity;
    static final int EMPTY = 0;
    static final int OCCUPIED = 1;
    static final int DELETED = 2;

    // Konstruktor
    MyClosedHashTableForInts(int aCapacity) {
        this.capacity = aCapacity;
        tagArray = new int[capacity];
        bucketArray = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            tagArray[i] = EMPTY;
            bucketArray[i] = Integer.MAX_VALUE;
        }
        size = 0;
    }

    // Hilfsmethode: Hash-Funktion
    public int getInitialHashIndex(int key) {
        return key % capacity; // Modulo-Hashfunktion
    }

    // Methode zur Suche nach einem Schlüssel
    public int searchForKey(int key) {
        int index = getInitialHashIndex(key);
        int start = index;

        while (tagArray[index] != EMPTY) {
            if (tagArray[index] == OCCUPIED && bucketArray[index] == key) {
                return index; // Schlüssel gefunden
            }
            index = (index + 1) % capacity; // Lineares Sondieren
            if (index == start) { // Abbruchbedingung: Zurück am Startpunkt
                break;
            }
        }
        return -1; // Schlüssel nicht gefunden
    }

    // Methode zum Einfügen eines Schlüssels
    public void add(int key) {
        if (size == capacity) {
            System.out.println("Die Tabelle ist voll!");
            return;
        }

        int index = getInitialHashIndex(key);

        while (tagArray[index] == OCCUPIED) {
            index = (index + 1) % capacity; // Lineares Sondieren
        }

        bucketArray[index] = key;
        tagArray[index] = OCCUPIED;
        size++;
    }

    // Methode zum Entfernen eines Schlüssels
    public boolean remove(int key) {
        int index = searchForKey(key);
        if (index == -1) {
            return false; // Schlüssel nicht gefunden
        }

        tagArray[index] = DELETED;
        bucketArray[index] = Integer.MAX_VALUE;
        size--;
        return true;
    }

    // Methode zum Überprüfen, ob ein Schlüssel vorhanden ist
    public boolean find(int key) {
        return searchForKey(key) != -1;
    }

    // Ausgabe der Tabelle
    public void printTable() {
        for (int i = 0; i < getCapacity(); i++) {
            switch (tagArray[i]) {
                case EMPTY:
                    System.out.print("[   ]");
                    break;
                case OCCUPIED:
                    System.out.print("(" + bucketArray[i] + ")");
                    break;
                case DELETED:
                    System.out.print("{   }");
                    break;
            }
            System.out.print("  ");
        }
        System.out.println();
    }

    // Getter für Kapazität
    public int getCapacity() {
        return capacity;
    }

    // Größe der Tabelle
    public int size() {
        return size;
    }

    // Überprüfen, ob die Tabelle leer ist
    public boolean isEmpty() {
        return size() == 0;
    }

    // Testmethoden
    public static void testRoutines() {
        MyClosedHashTableForInts ht = new MyClosedHashTableForInts(11);
        ht.add(1);
        ht.add(2);
        ht.add(3);
        ht.add(4);
        ht.add(5);
        ht.add(6);
        ht.add(7);
        ht.add(8);
        ht.add(12);
        ht.add(15);
        ht.printTable();

        ht.add(16);
        ht.printTable();

        System.out.println(ht.find(9)); // false
        System.out.println(ht.find(3)); // true

        ht.remove(3);
        ht.remove(6);
        ht.remove(8);
        ht.printTable();
    }

    // Main-Methode
    public static void main(String[] args) {
        testRoutines();
    }
}

